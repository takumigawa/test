#!/bin/sh

PATH=/home/arap14/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games

LOG_OUT=/bin/sib/spd003/log/log_`date '+%Y%m%d'`.txt

pyear=`date "+%Y"`
pmonth=`date "+%m"`
pday=`date "+%d"`
phour=`date "+%H"`
pminute=`date "+%M"`

echo `date +%F_%T` 実行パラメータ >>${LOG_OUT}
echo `date +%F_%T` 年：${pyear} >>${LOG_OUT}
echo `date +%F_%T` 月：$pmonth >>${LOG_OUT}
echo `date +%F_%T` 日：$pday >>${LOG_OUT}
echo `date +%F_%T` 時：$phour >>${LOG_OUT}
echo `date +%F_%T` 分：$pminute >>${LOG_OUT}

echo `date +%F_%T` spd003　起動します。 >>$LOG_OUT

cd /bin/sib/spd003/

/usr/bin/java -jar /bin/sib/spd003/sibschmail.jar $pyear $pmonth $pday $phour $pminute >>$LOG_OUT

rm -rf `find /bin/sib/spd003/log/ -daystart -ctime +15`

echo `date +%F_%T` spd003　終了します。 >>$LOG_OUT
