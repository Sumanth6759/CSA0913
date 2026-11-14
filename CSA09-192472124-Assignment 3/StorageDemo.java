public class StorageDemo {
    static class StorageFullException extends Exception {
        StorageFullException(String message) { super(message); }
    }

    static class Storage {
        private final int capacity;
        private int used = 0;

        Storage(int capacity) { this.capacity = capacity; }

        void save(int size) throws StorageFullException {
            if (size <= 0) {
                throw new IllegalArgumentException("Size must be positive.");
            }
            if (used + size > capacity) {
                throw new StorageFullException(
                    "Not enough storage. Available: " + (capacity - used) + " MB");
            }
            used += size;
            System.out.println("Saved " + size + " MB successfully.");
        }

        int getUsed() { return used; }
    }

    static void saveUserFile(Storage storage, int size)
            throws StorageFullException {
        saveToStorage(storage, size); // exception propagates to caller
    }

    static void saveToStorage(Storage storage, int size)
            throws StorageFullException {
        storage.save(size);
    }

    public static void main(String[] args) {
        Storage storage = new Storage(100);
        try {
            saveUserFile(storage, 60);
            saveUserFile(storage, 50);
        } catch (StorageFullException e) {
            System.out.println("Storage error handled in main: " + e.getMessage());
        }
        System.out.println("Used storage: " + storage.getUsed() + " MB");
    }
}
