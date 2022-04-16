package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.EmployeeDAO;
import com.xiaomi.pojo.Employee;
import com.xiaomi.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDAO, Employee> implements EmployeeService {

}
