package com.github.groovy;

/*
    闭包
 */

// 无参
def running = {
    println("running start ... ")
    println("running end ... ")
}

running()
running.call()

// 带参闭包
def running2 = { who ->
    println(" $who running start ... ")
    println("running end ... $who ")
}

running2("朱标")
running2.call("朱由检")

def running3(Closure closure) {
    println("running start")
    closure()
    println("running end ... ")
}

running3({ println("古娜拉黑暗之神") })

def running4(n1, n2, Closure closure) {

    def n3 = 30

    println("n1 -> ${n1} -- n2 -> ${n2}")

    n3 = n1
    n1 = n2
    n2 = n3

    closure(n1, n2)
}

running4(10, 20, { k, v -> println("n1: $k\nn2: $v") })
running4(10, 20, ){ k, v -> println("$k + $v = ${k + v}") }

