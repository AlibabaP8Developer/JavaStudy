package com.github.groovy;

// 脚本
def username ="大明太祖爷高皇帝朱元璋"
println(username)

def hello= Actor.get()
println(hello)

def actor = new Actor()
def name = actor.getList("大清康熙圣祖爷爱新觉罗玄烨")
println(name)

def result = actor.sale 100
println(result)

actor.age = 20
actor["name"] = "刘季"
actor.setGender(true)
println(actor.age)
println(actor.name)
println(actor.gender)

def obj = new Actor(age: 10, gender: '女', name: "武则天")
println(obj.name + "\t" +obj.gender)

class Book {

}