package com.test.ans;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class StockProcesser {
    private  SqlSession session;
    //初始化
    public StockProcesser(){
        String resource= "conf.xml";
        InputStream is= Main.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(is);
        session=sessionFactory.openSession();
    }
    //插入新的股票信息
    public  void insertNewStock(Stock stock){
        String statement="com.test.ans.userMapper.getStock";
        Stock lastItem=session.selectOne(statement,stock.getStock_code());
        String insertInvalid="com.test.ans.userMapper.insertStock";
        //如果数据库中不包含某条stock的数据，就存入，否则不存入
        if(lastItem==null){
            session.insert(insertInvalid,stock);
        }
        //数据库包含该条股票的信息，将该股票的数据设置为无效
        else{
            stock.setValid(false);
            session.insert(insertInvalid,stock);
        }
        session.commit();
    }
    //更新已经存在股票的信息
    public  void insertExistStock(Stock stock,double offset){
        String statement="com.test.ans.userMapper.getStock";
        Stock lastItem=session.selectOne(statement,stock.getStock_code());
        if(lastItem==null) {
            stock.setValid(false);
            insertNewStock(stock);
            return;
        }
        if(!lastItem.isValid()) return;
        //如果数据前后两次的close_price不一致，标记为invalid数据
        if(stock.getClose_price()!=lastItem.getClose_price()){
            String insertInvalid="com.test.ans.userMapper.insertStock";
            stock.setValid(false);
            //更新为无效数据
            session.insert(insertInvalid,stock);
            session.commit();
            return;
        }
        //添加含有offset偏移量的数据
        stock.setClose_price(stock.getClose_price()+offset);
        stock.setValid(true);
        String insertInvalid="com.test.ans.userMapper.insertStock";
        session.insert(insertInvalid,stock);
        session.commit();
    }
    //通过主键获取股票信息
    public String getStockInfoByPK(int pk){
        String getData="com.test.ans.userMapper.getStockByPK";
        Stock stock = session.selectOne(getData, pk);
        if(stock==null) return "can not find "+pk;
        if(!stock.isValid()) return stock.getStock_code()+" "+stock.getTrading_date()+
                " close_price error";
        return stock.getStock_code()+" "+stock.getTrading_date()+
                " close_price "+stock.getClose_price();
    }
}
