/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.kedacom.thrift.gen;


public enum Number implements org.apache.thrift.TEnum {
  One(1),
  Two(2),
  Three(3);

  private final int value;

  private Number(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Number findByValue(int value) { 
    switch (value) {
      case 1:
        return One;
      case 2:
        return Two;
      case 3:
        return Three;
      default:
        return null;
    }
  }
}
