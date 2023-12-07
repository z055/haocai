import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haocai.module.consumableslist.param.ConsumableslistParam;
import com.haocai.module.inCon.vo.InConVo;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Test {
    @org.junit.Test
    public void test3(){
            float price=7.3025f;
           float number=10f;
        BigDecimal bigDecimal=new BigDecimal("2.6565");
        BigDecimal bigDecimal1=new BigDecimal("5");
        System.out.println(bigDecimal1.multiply(bigDecimal).toString());
    }

    public int test4(int number){
        ConsumableslistParam param=new ConsumableslistParam();
        String name=param.getName();
        System.out.println("根据课程名获取课程集合");
        //课成集合实训项目
        List courListName=new ArrayList();
        for (Object o : courListName) {

            System.out.println("根据实训项目模糊遍历查询耗材目录表");
        }

        List resulet=new ArrayList();
       return 1;
    }
}
