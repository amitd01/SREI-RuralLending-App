package jaanpehchan.rural.srei;

/**
 * Created by 1515012 on 08-05-2018.
 */

public class FakeData {
    int id;
    String status,remark;

    FakeData(int id, String status, String remark) {
        this.id = id;
        this.status =status;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }
}
