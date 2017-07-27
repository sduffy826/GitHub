package com.corti.graphql.github;

import java.sql.Timestamp;

public class RateLimit {  
  private int cost;
  private int limit;
  private int remaining;
  private Timestamp resetAt;

  public RateLimit() {
    super();
    this.cost = 0;
    this.limit = 0;
    this.remaining = 0;
    this.resetAt = null;
  }
  
  public RateLimit(int cost, int limit, int remaining, Timestamp resetAt) {
    super();
    this.cost = cost;
    this.limit = limit;
    this.remaining = remaining;
    this.resetAt = resetAt;
  }
  
  public int getCost() {
    return cost;
  }

  public int getLimit() {
    return limit;
  }

  public int getRemaining() {
    return remaining;
  }

  public Timestamp getResetAt() {
    return resetAt;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public void setRemaining(int remaining) {
    this.remaining = remaining;
  }

  public void setResetAt(Timestamp resetAt) {
    this.resetAt = resetAt;
  }

  @Override
  public String toString() {
    return "RateLimit [cost=" + cost + ", limit=" + limit + ", remaining="
        + remaining + ", resetAt=" + resetAt + "]";
  }  
}
