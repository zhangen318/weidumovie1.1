package com.bw.movie.bean;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:14:13
 *@Description:${DESCRIPTION}
 **/

import java.util.List;

public class IdTimeYingYuanBean {

    /**
     * result : [{"address":"新街口外大街25号","cinemaId":14,"logo":"http://172.17.8.100/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店","price":0},{"address":"崇文门外大街18号国瑞城首层、地下二层","cinemaId":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店","price":0},{"address":"中关村大街19号新中关购物中心B1层","cinemaId":15,"logo":"http://172.17.8.100/images/movie/logo/jy.jpg","name":"金逸北京中关村店","price":0},{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院","price":0}]
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
         * address : 新街口外大街25号
         * cinemaId : 14
         * logo : http://172.17.8.100/images/movie/logo/zygj.jpg
         * name : 中影国际影城北京小西天店
         * price : 0
         */

        private String address;
        private int cinemaId;
        private String logo;
        private String name;
        private int price;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
