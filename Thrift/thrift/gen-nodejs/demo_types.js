//
// Autogenerated by Thrift Compiler (0.11.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
"use strict";

var thrift = require('thrift');
var Thrift = thrift.Thrift;
var Q = thrift.Q;

var references_ttypes = require('./references_types');


var ttypes = module.exports = {};
ttypes.Number = {
  'One' : 1,
  'Two' : 2,
  'Three' : 3
};
var CustomException = module.exports.CustomException = function(args) {
  Thrift.TException.call(this, "CustomException");
  this.name = "CustomException";
  this.id = null;
  this.name = null;
  this.description = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.name !== undefined && args.name !== null) {
      this.name = args.name;
    }
    if (args.description !== undefined && args.description !== null) {
      this.description = args.description;
    }
  }
};
Thrift.inherits(CustomException, Thrift.TException);
CustomException.prototype.name = 'CustomException';
CustomException.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.name = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.description = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CustomException.prototype.write = function(output) {
  output.writeStructBegin('CustomException');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.name !== null && this.name !== undefined) {
    output.writeFieldBegin('name', Thrift.Type.STRING, 2);
    output.writeString(this.name);
    output.writeFieldEnd();
  }
  if (this.description !== null && this.description !== undefined) {
    output.writeFieldBegin('description', Thrift.Type.STRING, 3);
    output.writeString(this.description);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

ttypes.hello = {
  'hello' : 'world'
};
