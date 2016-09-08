package com.graphhopper.api;


/**
 * @author Robin Boldt
 */
public class GHGEntry
{
        private Long osmId;
        private String osmType;

        private Point point;

        private String name;
        private String country;
        private String city;
        private String state;
        private String street;
        private String houseNumber;

        public GHGEntry() {

        }

        public GHGEntry(Long osmId, String type, double lat, double lng, String name, String country, String city, String state, String street, String houseNumber) {
            this.osmId = osmId;
            this.osmType = type;
            this.point = new Point(lat, lng);
            this.name = name;
            this.country = country;
            this.city = city;
            this.state = state;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public Long getOsmId() {
            return osmId;
        }

        public void setOsmId(Long osmId) {
            this.osmId = osmId;
        }

        public String getOsmType() {
            return osmType;
        }

        public void setOsmType(String type) {
            this.osmType = type;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public class Point {

            private double lat;
            private double lng;

            public Point(double lat, double lng) {
                this.lat = lat;
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }


}
