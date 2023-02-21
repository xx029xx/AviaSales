package ru.netology.repository;

import ru.netology.domain.AviaSales;

public class AviaSalesRepository {
    private AviaSales[] items = new AviaSales[0];

    public void save(AviaSales item) {
        int length = items.length + 1;
        AviaSales[] tmp = new AviaSales[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public AviaSales[] removeById(int id) {
        int length = items.length - 1;
        AviaSales[] tmp = new AviaSales[length];
        int index = 0;
        for (AviaSales item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
        return tmp;
    }

    public AviaSales[] findAll() {
        return items;
    }
}
