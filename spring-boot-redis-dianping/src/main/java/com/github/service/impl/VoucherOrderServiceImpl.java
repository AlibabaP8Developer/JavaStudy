package com.github.service.impl;

import com.github.dto.Result;
import com.github.pojo.SeckillVoucher;
import com.github.pojo.VoucherOrder;
import com.github.mapper.VoucherOrderMapper;
import com.github.service.ISeckillVoucherService;
import com.github.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.utils.RedisIdWorker;
import com.github.utils.UserHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker idWorker;

    @Override
    public Result seckillVoucher(Long voucherId) {

        // 1.查询优惠券信息
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        // 2.判断秒杀是否开始
        LocalDateTime beginTime = voucher.getBeginTime();
        boolean after = beginTime.isAfter(LocalDateTime.now());
        if (after) {
            // 未开始，直接返回
            return Result.fail("秒杀尚未开始！");
        }

        // 3.判断秒杀是否已经结束
        LocalDateTime endTime = voucher.getEndTime();
        boolean before = endTime.isBefore(LocalDateTime.now());
        if (before) {
            // 已经结束
            return Result.fail("秒杀已经结束！");
        }

        // 4.判断库存是否充足
        Integer stock = voucher.getStock();
        if (stock < 1) {
            // 库存不足
            return Result.fail("库存不足！");
        }

        // 7.返回订单id
        return createVoucher(voucherId);
    }

    @Transactional
    public Result createVoucher(Long voucherId) {
        // 5.一人一单
        // 用户id
        Long userId = UserHolder.getUser().getId();

        synchronized (userId.toString()) {
            // 查询订单
            int count = query().eq("user_id", userId)
                    .eq("voucher_id", voucherId).count();
            // 判断是否存在
            if (count > 0) {
                // 用户已经购买过了
                return Result.fail("用户已经购买过了！ ");
            }

            // 6.扣减库存
            boolean success = seckillVoucherService.update().setSql("stock = stock - 1")
                    .eq("voucher_id", voucherId)
                    .gt("stock", 0)
                    .update();

            if (!success) {
                // 扣减失败
                return Result.fail("库存不足！");
            }

            // 7.创建订单
            VoucherOrder voucherOrder = new VoucherOrder();
            // 7.1订单id
            long orderId = idWorker.nextId("order");
            voucherOrder.setId(orderId);
            voucherOrder.setUserId(userId);
            // 7.2代金券id
            voucherOrder.setVoucherId(voucherId);
            this.save(voucherOrder);
            return Result.ok(orderId);
        }
    }
}
