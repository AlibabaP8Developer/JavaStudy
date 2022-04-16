package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.common.EmployeeEnums;
import com.xiaomi.common.R;
import com.xiaomi.pojo.Employee;
import com.xiaomi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        /*
            1.将页面提交的密码进行md5加密处理
            2.根据页面提交的用户名查询数据库
            3.如果没有查询到则返回登陆失败结果
            4.密码比较时，如果不一致则返回登陆失败结果
            5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
            6.登陆成功，将员工ID存入session并返回登陆成功结果
         */
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if (emp == null) {
            return R.error(EmployeeEnums.NOTPASSWORD.getMsg());
        }
        if (!emp.getPassword().equals(password)) {
            return R.error(EmployeeEnums.NOTPASSWORD.getMsg());
        }
        if (emp.getStatus() == EmployeeEnums.LOCK.getCode()) {
            return R.error(EmployeeEnums.LOCK.getMsg());
        }
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    @PostMapping
    public R<String> save(@RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.save(employee);
        return R.success("保存员工成功！");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={}, pagesize={}, name={}", page, pageSize, name);
        // 构造分页构造器
        Page pagInfo = new Page(page, pageSize);
        // 构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        employeeService.page(pagInfo, queryWrapper);
        return R.success(pagInfo);
    }

    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request) {
//        Long userId = (Long) request.getSession().getAttribute("employee");
        employeeService.updateById(employee);
        return R.success("修改成功！");
    }

    @GetMapping("{id}")
    public R<Employee> findById(@PathVariable("id") String id) {
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
}
