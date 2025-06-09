package io.valkey.params;

import io.valkey.CommandArguments;
import io.valkey.Protocol.Keyword;

import java.util.Objects;

/**
 * Parameters for ZINCRBY commands. In fact, Redis doesn't have parameters for ZINCRBY. Instead
 * Redis has INCR parameter for ZADD.
 * <p>
 * When users call ZADD with INCR option, its restriction (only one member) and return type is same
 * to ZINCRBY. Document page for ZADD also describes INCR option to act like ZINCRBY. So we decided
 * to wrap "ZADD with INCR option" to ZINCRBY.
 * <p>
 * Works with Redis 3.0.2 and onwards.
 */
public class ZIncrByParams implements IParams {

  private Keyword existence;

  public ZIncrByParams() {
  }

  public static ZIncrByParams zIncrByParams() {
    return new ZIncrByParams();
  }

  /**
   * Only set the key if it does not already exist.
   * @return ZIncrByParams
   */
  public ZIncrByParams nx() {
    this.existence = Keyword.NX;
    return this;
  }

  /**
   * Only set the key if it already exist.
   * @return ZIncrByParams
   */
  public ZIncrByParams xx() {
    this.existence = Keyword.XX;
    return this;
  }

  @Override
  public void addParams(CommandArguments args) {
    if (existence != null) {
      args.add(existence);
    }

    args.add(Keyword.INCR);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ZIncrByParams that = (ZIncrByParams) o;
    return existence == that.existence;
  }

  @Override
  public int hashCode() {
    return Objects.hash(existence);
  }
}
