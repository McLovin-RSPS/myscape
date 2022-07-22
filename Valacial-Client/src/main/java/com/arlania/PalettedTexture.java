package com.arlania;

public class PalettedTexture extends Texture {
   public int[] palette;
   public byte[] indices;

   public PalettedTexture(int width, int height, ByteBuffer buffer, boolean alpha) {
      super(width, height);
      int paletteSize = buffer.readUnsignedByte();
      int[] palette = this.palette = new int[1 + paletteSize];

      int count;
      for(count = 0; count != paletteSize; ++count) {
         int pixel = buffer.getMedium();
         if (!alpha) {
            pixel |= -16777216;
         }

         palette[1 + count] = pixel;
      }

      count = width * height;
      byte[] indices = this.indices = new byte[count];

      for(int i = 0; i != count; ++i) {
         indices[i] = buffer.readSignedByte();
         if (!alpha && indices[i] == 0) {
            this.opaque = false;
         }
      }

   }

   public int getPixel(int i) {
      return this.palette[this.indices[i] & 255];
   }

   public byte[] getIndices() {
      return this.indices;
   }

   public void setIndices(byte[] indices) {
      this.indices = indices;
   }

   public String getType() {
      return "0";
   }

   public String toString() {
      return this.getType() + "\t" + super.toString() + "\t" + (this.palette.length - 1);
   }
}
