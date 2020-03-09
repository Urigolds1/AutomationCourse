package main.infrastructure;

public interface Devices {
    class Device {
        public String udid;
        public String type;

        Device(String udid, String type) {
            this.udid = udid;
            this.type = type;
        }
    }

    Device SHEEP = new Device("2305e21cec8a158d66b01513945328d2da581412", "iPhone 8+");
    Device HORSE = new Device("00008020-0004441A0E9A002E", "iPhone XR");
    Device COW = new Device("428ac4c3", "Redmi Note 7");
    Device GOOSE = new Device("6PQ0218209001044", "Huawei P10");
    Device GOAT = new Device("86ae576e", "OnePlus 5T");
}
