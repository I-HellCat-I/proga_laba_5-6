package Classes;

import java.io.*;
import java.util.*;


public class StructureStorage {
    protected static Stack<Flat> collection = new Stack<>();

    public synchronized void sort() {
        collection.sort(null);
    }


    public void load() {
        try {
            collection.addAll((Collection<? extends Flat>) FileManager.loadCollection());
        } catch (NullPointerException e){
            // ignore
        }
    }

    public Stack<Flat> getCollection() {
        return collection;
    }

    public boolean removeFlatById(Integer id) {
        for (Flat flat : collection) {
            if (Objects.equals(flat.getId(), id)) {
                flat.markForDeletion();
                collection.remove(flat);
                return true;
            }
        }
        return false;
    }

    public boolean removeLastFlat() {
        collection.pop();
        return true;
    }

    public boolean removeFlatAt(int pos) {
        collection.remove(pos).markForDeletion();
        return true;
    }

    public boolean addFlat(Flat f) {
        return collection.add(f);
    }

    public int getSize() {
        return collection.size();
    }

    public Flat getFlatById(int id) {
        Flat ans = null;
        for (Flat flat : collection) {
            if (Objects.equals(flat.getId(), id)) {
                ans = flat;
                break;
            }
        }
        return ans;
    }

    public void clearCollection() {
        collection.clear();
    }

    public int getNumberOfRoomsSum() {
        int cnt = 0;
        for (Flat f : collection) {
            cnt += f.getNumberOfRooms();
        }
        return cnt;
    }

    public int countLTFurnish(int amount) {
        int res = 0;
        for (Flat f : collection) {
            if (f.getFurnish().getAmount() < amount) res++;
        }
        return res;
    }

    public ArrayList<House> getUniqueHouse() {
        ArrayList<House> ans = new ArrayList<>();
        for (Flat f : collection) {
            if (!ans.contains(f.getHouse())) {
                ans.add(f.getHouse());
            }
        }
        return ans;
    }
}
