package com.bw.movie.bean;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:17:19
 *@Description:${DESCRIPTION}
 **/

import java.util.List;

public class XuanZuoBean {

    /**
     * result : [{"row":"1","seat":"1","status":2},{"row":"1","seat":"2","status":1},{"row":"1","seat":"3","status":1},{"row":"1","seat":"4","status":2},{"row":"1","seat":"5","status":1},{"row":"1","seat":"6","status":1},{"row":"1","seat":"7","status":1},{"row":"1","seat":"8","status":1},{"row":"1","seat":"9","status":1},{"row":"2","seat":"1","status":1},{"row":"2","seat":"2","status":1},{"row":"2","seat":"3","status":1},{"row":"2","seat":"4","status":1},{"row":"2","seat":"5","status":1},{"row":"2","seat":"6","status":1},{"row":"2","seat":"7","status":1},{"row":"2","seat":"8","status":1},{"row":"2","seat":"9","status":1},{"row":"3","seat":"1","status":2},{"row":"3","seat":"2","status":1},{"row":"3","seat":"3","status":1},{"row":"3","seat":"4","status":1},{"row":"3","seat":"5","status":2},{"row":"3","seat":"6","status":1},{"row":"3","seat":"7","status":2},{"row":"3","seat":"8","status":2},{"row":"3","seat":"9","status":1},{"row":"4","seat":"1","status":1},{"row":"4","seat":"2","status":1},{"row":"4","seat":"3","status":2},{"row":"4","seat":"4","status":2},{"row":"4","seat":"5","status":2},{"row":"4","seat":"6","status":1},{"row":"4","seat":"7","status":1},{"row":"4","seat":"8","status":1},{"row":"4","seat":"9","status":1},{"row":"5","seat":"1","status":1},{"row":"5","seat":"2","status":1},{"row":"5","seat":"3","status":1},{"row":"5","seat":"4","status":1},{"row":"5","seat":"5","status":1},{"row":"5","seat":"6","status":1},{"row":"5","seat":"7","status":2},{"row":"5","seat":"8","status":1},{"row":"5","seat":"9","status":1},{"row":"6","seat":"1","status":1},{"row":"6","seat":"2","status":1},{"row":"6","seat":"3","status":1},{"row":"6","seat":"4","status":2},{"row":"6","seat":"5","status":1},{"row":"6","seat":"6","status":2},{"row":"6","seat":"7","status":2},{"row":"6","seat":"8","status":1},{"row":"6","seat":"9","status":1},{"row":"7","seat":"1","status":1},{"row":"7","seat":"2","status":1},{"row":"7","seat":"3","status":1},{"row":"7","seat":"4","status":1},{"row":"7","seat":"5","status":1},{"row":"7","seat":"6","status":1},{"row":"7","seat":"7","status":2},{"row":"7","seat":"8","status":1},{"row":"7","seat":"9","status":1},{"row":"8","seat":"1","status":2},{"row":"8","seat":"2","status":2},{"row":"8","seat":"3","status":1},{"row":"8","seat":"4","status":1},{"row":"8","seat":"5","status":1},{"row":"8","seat":"6","status":1},{"row":"8","seat":"7","status":2},{"row":"8","seat":"8","status":1},{"row":"8","seat":"9","status":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * row : 1
         * seat : 1
         * status : 2
         */

        private String row;
        private String seat;
        private int status;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
