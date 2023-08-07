#!/bin/bash
pid=`ps -ef|grep dream-single-web |grep -v grep|awk '${print $2}'`
# 暴力停服
if [[ -z ""]];then
	echo application is already stopped
else
	echo kill $pid
	kill -9 $pid
fi