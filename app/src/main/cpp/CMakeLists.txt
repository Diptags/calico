cmake_minimum_required(VERSION 3.10.2)

project("calico")

add_library(calico-lib
        SHARED
        calico.c)

find_library(
        log-lib
        log )

target_link_libraries(
        calico-lib
        ${log-lib} )