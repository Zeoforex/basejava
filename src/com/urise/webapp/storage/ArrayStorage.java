package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("Такое" + resume.getUuid() + "резюме уже есть");
        } else if (size >= storage.length) {
            System.out.println("Заполнено всё");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("Нет " + uuid + " такого резюме");
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Нет " + resume.getUuid() + " такого резюме");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("Нет " + uuid + " такого резюме");
        } else {
            storage[index] = storage[size - 1];
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


    private int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
