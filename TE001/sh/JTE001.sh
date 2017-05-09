#!/bin/sh

PATH=/home/arap14/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/bin/sib/spd001/jdk7/bin:/bin/sib/spd001/jdk7/lib

HOME_PATH=/bin/sib/tejob

OUT_PATH=$HOME_PATH/pdf/
LOG_PATH=$HOME_PATH/log
LOG_OUT=$LOG_PATH/log_`date '+%Y%m%d'`.txt

PDF_ZIP=$OUT_PATH/交通費申請書`date '+%Y'`年`date '+%m'`月.zip

echo `date +%F_%T` 起動します。 >>$LOG_OUT

echo `date +%F_%T` テンポラリ削除 >>$LOG_OUT
rm $OUT_PATH*

echo `date +%F_%T` te001　起動します。 >>$LOG_OUT
#/usr/bin/java -jar $HOME_PATH/bin/TE001.jar >>$LOG_OUT
echo `date +%F_%T` te001　実行終了 >>$LOG_OUT

echo `date +%F_%T` te002　起動します。 >>$LOG_OUT
/usr/bin/java -jar $HOME_PATH/bin/TE002.jar >>$LOG_OUT
echo `date +%F_%T` te002　実行終了 >>$LOG_OUT

echo `date +%F_%T` ファイル名 UTF-8 shif-jis変換 開始 >>$LOG_OUT
sudo convmv -f utf8 -t sjis ${OUT_PATH}* --notest >>$LOG_OUT
echo `date +%F_%T` ファイル名変換　完了 >>$LOG_OUT

echo `date +%F_%T` 明細圧縮 >>$LOG_OUT
cd $OUT_PATH
sudo zip $PDF_ZIP *
echo `date +%F_%T` 圧縮終了 >>$LOG_OUT

if [ -e $PDF_ZIP ]; then
   echo `date +%F_%T` te003　起動します。 >>$LOG_OUT
   cd $HOME_PATH/bin
   /usr/bin/java -jar $HOME_PATH/bin/TE003.jar $PDF_ZIP >>$LOG_OUT
   echo `date +%F_%T` te003　実行終了 >>$LOG_OUT
fi

#ログ削除 30日以前のログを削除
rm -rf `find $LOG_PATH -daystart -ctime +30`
