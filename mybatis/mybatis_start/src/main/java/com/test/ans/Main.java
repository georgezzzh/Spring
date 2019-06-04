package com.test.ans;

import java.time.LocalDate;
public class Main {

    public static void main(String[]args){
        /**
         * 接受到的数据为以下两种格式，如果不是json对象，采用数组分割进行数据处理:
         * 000001 2019-02-01 close_price 1.00
         * 000001 2019-02-03 close_price 1.02 1 close_price 0.02
         */
        StockProcesser stockProcesser=new StockProcesser();
        String receiveMsg[]=new String[]{
        "000001 2019-02-01 close_price 1.00",
                "000001 2019-02-01 close_price 1.00 1 close_price 0.02",
                "000001 2019-02-02 close_price 1.02 1 close_price 0.03",
                "000001 2019-02-03 close_price 1.02 1 close_price 0.03",
                "000002 2019-02-03 close_price 1.02",
                "000003 2019-02-03 close_price 1.02 1 close_price 0.03"
        };
        for(String tmp:receiveMsg){
            String[]stockArgs=tmp.split(" ");
            Stock stock=new Stock();
            //新的数据
            if(stockArgs.length==4){

                stock.setValid(true);
                stock.setStock_code(stockArgs[0]);
                stock.setTrading_date(LocalDate.parse(stockArgs[1]));
                stock.setClose_price(Double.parseDouble(stockArgs[3]));
                stockProcesser.insertNewStock(stock);
            }
            //采用offset之后的数据
            if(stockArgs.length==7){
                stock.setValid(true);
                stock.setStock_code(stockArgs[0]);
                stock.setTrading_date(LocalDate.parse(stockArgs[1]));
                stock.setClose_price(Double.parseDouble(stockArgs[3]));
                double offset=Double.parseDouble(stockArgs[6]);
                stockProcesser.insertExistStock(stock,offset);
            }
            //数据的长度不一致，废弃，不做入库处理
        }
        //进行get
        for(int i=1;i<=7;i++){
            String info=stockProcesser.getStockInfoByPK(i);
            System.out.println(info);
        }
    }
}
