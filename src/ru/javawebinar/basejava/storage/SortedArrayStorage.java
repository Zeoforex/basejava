package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume r, int index) {
        storage[size] = r; // не знаю как реализовать
    }

    @Override
    protected void deletedElement(int index) {
        int deleteIndex = index - 1;
        System.arraycopy(storage, index + 1, storage, index, size - deleteIndex);
        storage[size - 1] = null;
    }

}