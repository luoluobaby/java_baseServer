package com.service;

//import com.model.po.TbTradesOne;
//import com.model.po.TbTradesTwo;
//import com.model.vo.DataOne;
//import com.model.vo.TradeDataToData;
import com.model.po.TbTradesOne;
import com.model.po.TbTradesTwo;
import com.model.vo.DataOne;
import com.model.vo.DataTwo;
        import com.service.model.impl.TbTradesOneServiceImpl;
import com.service.model.impl.TbTradesTwoServiceImpl;
        import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jimmy
 * @date 2018/9/10 10:44
 */
@Service("tradeDataService")
public class TradeDataService {
    @Resource
    private TbTradesOneServiceImpl tbTradesOneImpl ;
    @Resource
    private TbTradesTwoServiceImpl tbTradesTwoImpl ;

    /**
     * 插入一类表格数据
     * @return
     */
//    public boolean InertTradeOneData(List<TradeDataToData> datas)
//    {
//        if (null !=datas && 0 < datas.size())
//        {
//            for ( TradeDataToData trade:datas ) {
//                TbTradesOne t = new TbTradesOne(trade.getTitle());
//                Integer id=(Integer)tbTradesOneImpl.insert(t);
//                if (null != id)
//                {
//                    t= tbTradesOneImpl.queryByKey(id);
//                    for(TradeTwoDataToData trade2 :trade.getItem())
//                    {
//                        TbTradesTwo two = new TbTradesTwo(trade2.getName() , t );
//                        tbTradesTwoImpl.insert(two);
//                    }
//                }
//                else
//                    return  false;
//            }
//            return  true;
//        }
//        else
//            return  false;
//    }
//    public TbTradesOne getOne(Integer id){
//        return tbTradesOneImpl.queryByKey(id);
//    }

    /**
     * 添加商品数据
     * @param dataOnes
     * @param
     */
    public String InsertGoodsData(List<DataOne> dataOnes , List<DataTwo> dataTwos)
    {
        String str="";
        if (null != dataOnes && 0 < dataOnes.size() && null != dataTwos && 0 < dataTwos.size())
        {
            for ( DataOne dataone : dataOnes )
            {
                TbTradesOne t = new TbTradesOne(dataone.getName());
                Integer id=(Integer)tbTradesOneImpl.insert(t);
                if (null != id)
                {
                    t= tbTradesOneImpl.queryByKey(id);
                    DataTwo currTwo = FindByParentId(dataone.getCid() , dataTwos);
                    if (null != currTwo && null != currTwo.getItems() && 0 < currTwo.getItems().size())
                    {
                        for ( String name : currTwo.getItems() )
                        {
                            TbTradesTwo two = new TbTradesTwo(name , t) ;
                            Integer twoId= (Integer) tbTradesTwoImpl.insert(two);
                            str+=twoId+",";
                        }
                    }
                }

            }
        }
        return  str;
    }

    /**
     * 获取到当前的two
     * @param parentId
     * @param twos
     * @return
     */
    private  DataTwo FindByParentId(String parentId , List<DataTwo> twos)
    {
        if (null != twos && 0 <twos.size())
        {
            for (DataTwo t : twos)
            {
                if (t.getParentId().equals(parentId))
                {
                    return  t;
                }
            }
        }
        return  null;
    }

}
