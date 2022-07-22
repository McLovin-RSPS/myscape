package com.arlania;

    public class MysteryBox {

        public MysteryBox() { }

        public static final int INTERFACE_ID = 47000;
        public static final int BOXES64 = 28; // 28 * 64 boxes
        private boolean spinClick;
        private int spins;
        private int spinNum;

        public void setSpinClick(boolean spinClick) { this.spinClick = spinClick; }

        public void spin() {
            if (Client.instance.openInterfaceID != INTERFACE_ID || spinClick == false) {
                return;
            }

            RSInterface items = RSInterface.interfaceCache[47100];
            RSInterface boxes = RSInterface.interfaceCache[47200];
            if (spins < 100) {
                shift(items, boxes, 8);
            }
            else if (spins < 200) {
                shift(items, boxes, 5);
            }
            else if (spins < 300) {
                shift(items, boxes, 4);
            }
            else if (spins < 400) {
                shift(items, boxes, 3);
            }
            else if (spins < 488) {
                shift(items, boxes, 2);
            }
            else if (spins < 562) {
                shift(items, boxes, 1);
            }
            else {
                spinComplete();
            }
        }

        private void shift(RSInterface items, RSInterface boxes, int shiftAmount) {
            items.childX[0] -= shiftAmount;
            for(int i=0; i<BOXES64; i++){ boxes.childX[i] -= shiftAmount; }
            spins++;
        }

        private void spinComplete() {
            // Reset
            spins = 0;
            spinClick = false;
            spinNum++;
            // Notify server: spin complete
            Client.instance.stream.createFrame(145);
            Client.instance.stream.writeUnsignedWordA(696969);
            Client.instance.stream.writeUnsignedWordA(0);
            Client.instance.stream.writeUnsignedWordA(0);
        }

        public boolean handledPacket34(int frame) {
            if (Client.instance.openInterfaceID != INTERFACE_ID) {
                return false;
            }

            RSInterface items = RSInterface.interfaceCache[frame];
            while (Client.instance.inStream.currentOffset < Client.instance.pktSize) {
                int amount = Client.instance.inStream.method422();
                int itemId = Client.instance.inStream.readUnsignedWord();
                Client.instance.inStream.readUnsignedByte();
                int slot = Client.instance.inStream.readDWord();

                if (slot >= 0 && slot < items.inv.length) {
                    items.inv[slot] = itemId;
                    items.invStackSizes[slot] = amount;
                }
            }
            return true;
        }

        public void reset() {
            if (spinClick) {
                return;
            }

            spinNum = 0;
            RSInterface items = RSInterface.interfaceCache[47100];
            RSInterface boxes = RSInterface.interfaceCache[47200];
            items.childX[0] = 0;
            int x = 0;
            for (int z=0; z<BOXES64; z++) {
                boxes.childX[z] = x;
                x += 2880;
            }
        }
}
