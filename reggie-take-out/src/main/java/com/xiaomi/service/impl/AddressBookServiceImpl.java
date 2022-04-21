package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.AddressBookDAO;
import com.xiaomi.pojo.AddressBook;
import com.xiaomi.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookDAO, AddressBook> implements AddressBookService {

}
