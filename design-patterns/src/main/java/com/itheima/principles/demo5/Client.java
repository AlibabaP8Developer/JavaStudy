package com.itheima.principles.demo5;

public class Client {
    public static void main(String[] args) {
        Agent agent = new Agent();
        Star star = new Star("小西行长");
        Fans fans = new Fans("丰臣秀吉");
        agent.setStar(star);
        agent.setFans(fans);
        Company company = new Company("阿里巴巴");
        agent.setCompany(company);

        agent.meeting();
        agent.business();
    }
}
