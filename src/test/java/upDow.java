import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class upDow {
    @RequestMapping("/up")
    public String up(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) throws IOException{
//        获取文件名字
        String uploadFileName=file.getOriginalFilename();
        if ("".equals(uploadFileName)){
            return "文件名为空上传失败";
        }
        //上传地址
     String path=  request.getSession().getServletContext().getRealPath("/upload");
        File realPath=new File(path);
        if (!realPath.exists()){//不存在就创建
                realPath.mkdir();
        }
        System.out.println("上传的路径为"+realPath);
        InputStream in=file.getInputStream();
        OutputStream ou=new FileOutputStream(new File(realPath,uploadFileName));//这里可与使用UUID
        int len=0;
        byte[] data=new byte[1024];
        while ((len=in.read(data))!=-1){
            ou.write(data,0,len);
        }
        in.close();
        ou.close();
        request.getSession().setAttribute("mage","上传成功");
        return "/index.jsp";
    }
    @RequestMapping("/up2")
    public String up2(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) throws IOException{
        String path=  request.getSession().getServletContext().getRealPath("/upload");
        File realPath=new File(path);
        if (!realPath.exists()){//不存在就创建
            realPath.mkdir();
        }
        System.out.println("上传的路径为"+realPath);
        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));//直接上传  可以使用uuid
        request.getSession().setAttribute("mage","上传成功");
        return "/index.jsp";
    }
    @RequestMapping("/dow")
    public String dow(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //要下载的文件地址
        String path=request.getSession().getServletContext().getRealPath("/upload");
        String filename="wallhaven-m9ogwy.jpg";
        //设置response相应头
        response.reset();//设置页面不缓存
        response.setCharacterEncoding("utf-8");//设置相应编码
        response.setContentType("multipart/form-data");//二进制传输流
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(filename,"utf-8"));
        File file=new File(path,filename);
        InputStream inputStream=new FileInputStream(file);
        OutputStream outputStream=response.getOutputStream();
        byte[] data=new byte[1024];
        int len=0;
        while ((len=inputStream.read(data))!=-1){
            outputStream.write(data,0,len);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
        return "/index.jsp";
    }
}
