package com.aitsuki.navigator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * <table>
 *     <th>类型</th><th>argType 语法</th><th>是否支持默认值？</th><th>是否支持 null 值？</th>
 *     <tr><td>整数</td><td>integer</td><td>是</td><td>否</td>
 *     <tr><td>浮点数</td><td>float</td><td>是</td><td>否</td>
 *     <tr><td>长整数</td><td>long</td><td>是 -默认值必须始终以“L”后缀结尾（例如“123L”）</td><td>否</td>
 *     <tr><td>布尔值</td><td>boolean</td><td>是 -“true”或“false”</td><td>否</td>
 *     <tr><td>字符串</td><td>string</td><td>是</td><td>是 -@null</td>
 *     <tr><td>资源引用</td><td>reference</td><td>是 - 默认值必须为“@resourceType/resourceName”格式（例如，“@style/myCustomStyle”）或“0”</td><td>否</td>
 *     <tr><td>自定义 Parcelable</td><td>完全限定类名称</td><td>支持默认值“@null”。不支持其他默认值</td><td>是</td>
 *     <tr><td>自定义 Serializable</td><td>完全限定类名称</td><td>支持默认值“@null”。不支持其他默认值</td><td>是</td>
 *     <tr><td>自定义 Enum</td><td>完全限定类名称</td><td>是 - 默认值必须与非限定名称匹配（例如，“SUCCESS”匹配 MyEnum.SUCCESS）</td><td>否</td>
 * <table/>
 * <p>
 * 数组：不支持枚举数组和资源引用数组。不论基础类型的 null 性如何，数组始终可为 null。数组仅支持一个默认值，即“@null”。数组不支持任何其他默认值。
 *
 * @see <a href=https://developer.android.com/guide/navigation/navigation-pass-data#supported_argument_types>
 * 在目的地之间传递数据-支持的参数类型<a/>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {

    String TYPE_INTEGER = "integer";
    String TYPE_FLOAT = "float";
    String TYPE_LONG = "long";
    String TYPE_BOOLEAN = "boolean";
    String TYPE_STRING = "string";
    String TYPE_REFERENCE = "reference";

    String VALUE_NULL = "@null";
    String VALUE_UNDEFINE = "@undefine";
    String VALUE_TRUE = "true";
    String VALUE_FALSE = "false";

    String argType();

    String name();

    /**
     * 没有默认值并且非空类型的参数将出现在navigator的构造函数中，等同于require
     */
    String defaultValue() default VALUE_UNDEFINE;

    /**
     * 没有默认值并且非空类型的参数将出现在navigator的构造函数中，等同于require
     */
    boolean nullable() default false;

    /**
     * 是否是数组类型
     */
    boolean isArray() default false;
}
