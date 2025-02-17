package com.ganten.tethys.leetcode75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class N841 {

    private final HashSet<Integer> visitable = new HashSet<>();
    private List<List<Integer>> rooms;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        this.rooms = rooms;
        this.visit(Collections.singletonList(0));

        return visitable.size() == rooms.size();
    }

    public void visit(List<Integer> roomNumber) {
        if (roomNumber.isEmpty()) {
            return;
        }
        visitable.addAll(roomNumber);
        List<Integer> newRoomNumber = new ArrayList<>();
        for (Integer i : roomNumber) {
            newRoomNumber.addAll(rooms.get(i));
        }
        newRoomNumber.removeIf(visitable::contains);
        this.visit(newRoomNumber);
    }
}
