package Learning;

public class BloomFlitter {
    private final byte[] data;

    public BloomFlitter(int size) {
        data = new byte[size * 2];
    }

    public void add(int key) {
        int i1 = Math.abs(hash1(key) % data.length);
        int i2 = Math.abs(hash2(key) % data.length);
        int i3 = Math.abs(hash3(key) % data.length);
        data[i1] = data[i2] = data[i3] = 1;
    }

    public boolean exists(int key) {
        int i1 = Math.abs(hash1(key) % data.length);
        int i2 = Math.abs(hash2(key) % data.length);
        int i3 = Math.abs(hash3(key) % data.length);

        return data[i1] == 1 && data[i2] == 1 && data[i3] == 1;
    }

    private int hash1(Integer key) {
        return key.hashCode();
    }

    private int hash2(Integer key) {
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 3);
    }

    private int hash3(Integer key) {
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }
}
