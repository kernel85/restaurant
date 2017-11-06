package restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

public class CookedDish {

    public static CookedDish of(int table, Dish dish, Chef chef) {
        return new CookedDish(table, dish.getName(), chef.getName(), chef.getTotalCookingTime(), chef.getTotalCookingTime() + dish.getCookingTime());
    }

    public CookedDish(int table, String food, String cookedBy, int startedAt, int finishedAt) {
        this.table      = table;
        this.food       = requireNonNull(food);
        this.cookedBy   = requireNonNull(cookedBy);
        this.startedAt  = startedAt;
        this.finishedAt = finishedAt;
    }

    @JsonProperty("table")       private final int    table;
    @JsonProperty("food")        private final String food;
    @JsonProperty("cooked_by")   private final String cookedBy;
    @JsonProperty("started_at")  private final int    startedAt;
    @JsonProperty("finished_at") private final int    finishedAt;

    public int    getTable()      { return table; }
    public String getFood()       { return food; }
    public String getCookedBy()   { return cookedBy; }
    public int    getStartedAt()  { return startedAt; }
    public int    getFinishedAt() { return finishedAt; }

    @Override public int hashCode() {
        return Integer.hashCode(table)
             + 31 * food.hashCode()
             + 31 * cookedBy.hashCode()
             + 31 * Integer.hashCode(startedAt)
             + 31 * Integer.hashCode(finishedAt);
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CookedDish other = (CookedDish) obj;
        return table == other.getTable()
            && food.equals(other.getFood())
            && cookedBy.equals(other.getCookedBy())
            && startedAt == other.getStartedAt()
            && finishedAt == other.getFinishedAt();
    }

    @Override public String toString() {
        return "table: "      + table     + " - "
             + "food: "       + food      + " - "
             + "cookedBy: "   + cookedBy  + " - "
             + "startedAt: "  + startedAt + " - "
             + "finishedAt: " + finishedAt;
    }
}
