package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void save(Resume r) {
        int number = searchIndex(r.getUuid());
        if (number != -1) {
            System.out.println("Такое резюме уже есть");
        } else if (size >= storage.length) {
            System.out.println("Заполнено всё");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int number = searchIndex(uuid);
        if (number == -1) {
            System.out.println("Нет такого резюме");
            return null;
        } else {
            return storage[number];
        }
    }

    public void update(Resume r) {
        int number = searchIndex(r.getUuid());
        if (number == -1) {
            System.out.println("Нет такого резюме");
        } else {
            storage[number] = r;
        }
    }

    public void delete(String uuid) {
        int number = searchIndex(uuid);
        if (number == -1) {
            System.out.println("Нет такого резюме ");
        } else {
            storage[number] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }


    public int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
