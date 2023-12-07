package com.haocai.module.BorrowingManagement.vo.param;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量修改状态的数据类
 */
public class UpParam {
    private List<String> ids;
    private String status;
    public void setIds(List<String> ids) {
        this.ids = ids;
    }
    public List<String> getIds() {
        return ids;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public UpParam(ArrayList<String> ids, String status) {
        this.ids = ids;
        this.status = status;
    }

    public UpParam() {
    }

    @Override
    public String toString() {
        return "UpParam{" +
                "ids=" + ids +
                ", status='" + status + '\'' +
                '}';
    }
}

