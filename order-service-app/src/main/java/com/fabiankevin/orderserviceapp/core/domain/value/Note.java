package com.fabiankevin.orderserviceapp.core.domain.value;

import com.fabiankevin.orderserviceapp.core.guards.Guards;

import java.util.Objects;

public final class Note {
    private final int NOTE_LENGTH_LIMIT = 500;
    private String value;

    public Note(String value) {
        this.value = value;
        if(value == null || value.isEmpty()){
            Guards.guard(value).againstLongEnough(NOTE_LENGTH_LIMIT, "Note is too long");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return NOTE_LENGTH_LIMIT == note.NOTE_LENGTH_LIMIT && Objects.equals(value, note.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NOTE_LENGTH_LIMIT, value);
    }

    public String value() {
        return value;
    }
}
