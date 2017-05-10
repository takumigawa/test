#!/bin/sh

PATH=/home/arap14/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/bin/sib/spd001/jdk7/bin:/bin/sib/spd001/jdk7/lib

REPORT_PATH=/bin/sib/spd001/report/
OUT_PATH=/bin/sib/spd001/pdf/
HOME_PATH=/bin/sib/spd001
LOG_OUT=/bin/sib/spd001/bin/log/log_`date '+%Y%m%d'`.txt
PDF_ZIP=/bin/sib/spd001/pdf/給与明細`date '+%Y'`年`date '+%m'`月.zip

echo `date +%F_%T` 起動します。 >>$LOG_OUT

echo `date +%F_%T` テンポラリ削除 >>$LOG_OUT
rm $OUT_PATH*

$HOME_PATH/jdk7/bin/java -jar $HOME_PATH/bin/spd001.jar 0000 00  $REPORT_PATH $OUT_PATH >>$LOG_OUT
echo `date +%F_%T` spd001　実行終了 >>$LOG_OUT

echo `date +%F_%T` spd002　起動します。 >>$LOG_OUT
$HOME_PATH/jdk7/bin/java -jar $HOME_PATH/bin/spd002.jar 0000 00 >>$LOG_OUT
echo `date +%F_%T` spd002　実行終了 >>$LOG_OUT

echo `date +%F_%T` ファイル名 UTF-8 shif-jis変換 開始 >>$LOG_OUT
sudo convmv -f utf8 -t sjis ${OUT_PATH}* --notest >>$LOG_OUT
echo `date +%F_%T` ファイル名変換　完了 >>$LOG_OUT

echo `date +%F_%T` 明細圧縮 >>$LOG_OUT
cd $OUT_PATH
sudo zip $PDF_ZIP *
echo `date +%F_%T` 圧縮終了 >>$LOG_OUT

#echo `date +%F_%T` spd002　起動します。 >>$LOG_OUT
#$HOME_PATH/jdk7/bin/java -jar $HOME_PATH/bin/spd002.jar 0000 00 >>$LOG_OUT
#echo `date +%F_%T` spd002　実行終了 >>$LOG_OUT

if [ -e $PDF_ZIP ]; then
   echo `date +%F_%T` spd004　起動します。 >>$LOG_OUT
   $HOME_PATH/jdk7/bin/java -jar $HOME_PATH/bin/spd004.jar $PDF_ZIP >>$LOG_OUT
   echo `date +%F_%T` spd004　実行終了 >>$LOG_OUT
fi

#ログ削除 30日以前のログを削除
rm -rf `find /bin/sib/spd001/bin/log/ -daystart -ctime +30`
