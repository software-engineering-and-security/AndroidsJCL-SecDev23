<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: db82be4e049c src/share/classes/java/io/ObjectInputStream.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/db82be4e049c">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/db82be4e049c">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/db82be4e049c">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/db82be4e049c/src/share/classes/java/io/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/io/ObjectInputStream.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/db82be4e049c/src/share/classes/java/io/ObjectInputStream.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/db82be4e049c/src/share/classes/java/io/ObjectInputStream.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/db82be4e049c/src/share/classes/java/io/ObjectInputStream.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/db82be4e049c/src/share/classes/java/io/ObjectInputStream.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/db82be4e049c/src/share/classes/java/io/ObjectInputStream.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/io/ObjectInputStream.java @ 13884:db82be4e049c</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8224541: Better mapping of serial ENUMs
Reviewed-by: mschoene, rhalade, robm, rriggs, smarks, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#98;&#99;&#104;&#114;&#105;&#115;&#116;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Tue, 21 Jan 2020 10:56:30 -0800</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/033462472c28/src/share/classes/java/io/ObjectInputStream.java">033462472c28</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/091e23df5148/src/share/classes/java/io/ObjectInputStream.java">091e23df5148</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1996, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">package java.io;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.ObjectStreamClass.WeakClassKey;</span><a href="#l28"></a>
<span id="l29">import java.lang.ref.ReferenceQueue;</span><a href="#l29"></a>
<span id="l30">import java.lang.reflect.Array;</span><a href="#l30"></a>
<span id="l31">import java.lang.reflect.Modifier;</span><a href="#l31"></a>
<span id="l32">import java.lang.reflect.Proxy;</span><a href="#l32"></a>
<span id="l33">import java.security.AccessControlContext;</span><a href="#l33"></a>
<span id="l34">import java.security.AccessController;</span><a href="#l34"></a>
<span id="l35">import java.security.PrivilegedAction;</span><a href="#l35"></a>
<span id="l36">import java.security.PrivilegedActionException;</span><a href="#l36"></a>
<span id="l37">import java.security.PrivilegedExceptionAction;</span><a href="#l37"></a>
<span id="l38">import java.util.Arrays;</span><a href="#l38"></a>
<span id="l39">import java.util.HashMap;</span><a href="#l39"></a>
<span id="l40">import java.util.Objects;</span><a href="#l40"></a>
<span id="l41">import java.util.concurrent.ConcurrentHashMap;</span><a href="#l41"></a>
<span id="l42">import java.util.concurrent.ConcurrentMap;</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">import static java.io.ObjectStreamClass.processQueue;</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">import sun.misc.SharedSecrets;</span><a href="#l46"></a>
<span id="l47">import sun.misc.ObjectInputFilter;</span><a href="#l47"></a>
<span id="l48">import sun.misc.ObjectStreamClassValidator;</span><a href="#l48"></a>
<span id="l49">import sun.misc.SharedSecrets;</span><a href="#l49"></a>
<span id="l50">import sun.reflect.misc.ReflectUtil;</span><a href="#l50"></a>
<span id="l51">import sun.misc.JavaOISAccess;</span><a href="#l51"></a>
<span id="l52">import sun.util.logging.PlatformLogger;</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">/**</span><a href="#l54"></a>
<span id="l55"> * An ObjectInputStream deserializes primitive data and objects previously</span><a href="#l55"></a>
<span id="l56"> * written using an ObjectOutputStream.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> * &lt;p&gt;ObjectOutputStream and ObjectInputStream can provide an application with</span><a href="#l58"></a>
<span id="l59"> * persistent storage for graphs of objects when used with a FileOutputStream</span><a href="#l59"></a>
<span id="l60"> * and FileInputStream respectively.  ObjectInputStream is used to recover</span><a href="#l60"></a>
<span id="l61"> * those objects previously serialized. Other uses include passing objects</span><a href="#l61"></a>
<span id="l62"> * between hosts using a socket stream or for marshaling and unmarshaling</span><a href="#l62"></a>
<span id="l63"> * arguments and parameters in a remote communication system.</span><a href="#l63"></a>
<span id="l64"> *</span><a href="#l64"></a>
<span id="l65"> * &lt;p&gt;ObjectInputStream ensures that the types of all objects in the graph</span><a href="#l65"></a>
<span id="l66"> * created from the stream match the classes present in the Java Virtual</span><a href="#l66"></a>
<span id="l67"> * Machine.  Classes are loaded as required using the standard mechanisms.</span><a href="#l67"></a>
<span id="l68"> *</span><a href="#l68"></a>
<span id="l69"> * &lt;p&gt;Only objects that support the java.io.Serializable or</span><a href="#l69"></a>
<span id="l70"> * java.io.Externalizable interface can be read from streams.</span><a href="#l70"></a>
<span id="l71"> *</span><a href="#l71"></a>
<span id="l72"> * &lt;p&gt;The method &lt;code&gt;readObject&lt;/code&gt; is used to read an object from the</span><a href="#l72"></a>
<span id="l73"> * stream.  Java's safe casting should be used to get the desired type.  In</span><a href="#l73"></a>
<span id="l74"> * Java, strings and arrays are objects and are treated as objects during</span><a href="#l74"></a>
<span id="l75"> * serialization. When read they need to be cast to the expected type.</span><a href="#l75"></a>
<span id="l76"> *</span><a href="#l76"></a>
<span id="l77"> * &lt;p&gt;Primitive data types can be read from the stream using the appropriate</span><a href="#l77"></a>
<span id="l78"> * method on DataInput.</span><a href="#l78"></a>
<span id="l79"> *</span><a href="#l79"></a>
<span id="l80"> * &lt;p&gt;The default deserialization mechanism for objects restores the contents</span><a href="#l80"></a>
<span id="l81"> * of each field to the value and type it had when it was written.  Fields</span><a href="#l81"></a>
<span id="l82"> * declared as transient or static are ignored by the deserialization process.</span><a href="#l82"></a>
<span id="l83"> * References to other objects cause those objects to be read from the stream</span><a href="#l83"></a>
<span id="l84"> * as necessary.  Graphs of objects are restored correctly using a reference</span><a href="#l84"></a>
<span id="l85"> * sharing mechanism.  New objects are always allocated when deserializing,</span><a href="#l85"></a>
<span id="l86"> * which prevents existing objects from being overwritten.</span><a href="#l86"></a>
<span id="l87"> *</span><a href="#l87"></a>
<span id="l88"> * &lt;p&gt;Reading an object is analogous to running the constructors of a new</span><a href="#l88"></a>
<span id="l89"> * object.  Memory is allocated for the object and initialized to zero (NULL).</span><a href="#l89"></a>
<span id="l90"> * No-arg constructors are invoked for the non-serializable classes and then</span><a href="#l90"></a>
<span id="l91"> * the fields of the serializable classes are restored from the stream starting</span><a href="#l91"></a>
<span id="l92"> * with the serializable class closest to java.lang.object and finishing with</span><a href="#l92"></a>
<span id="l93"> * the object's most specific class.</span><a href="#l93"></a>
<span id="l94"> *</span><a href="#l94"></a>
<span id="l95"> * &lt;p&gt;For example to read from a stream as written by the example in</span><a href="#l95"></a>
<span id="l96"> * ObjectOutputStream:</span><a href="#l96"></a>
<span id="l97"> * &lt;br&gt;</span><a href="#l97"></a>
<span id="l98"> * &lt;pre&gt;</span><a href="#l98"></a>
<span id="l99"> *      FileInputStream fis = new FileInputStream(&quot;t.tmp&quot;);</span><a href="#l99"></a>
<span id="l100"> *      ObjectInputStream ois = new ObjectInputStream(fis);</span><a href="#l100"></a>
<span id="l101"> *</span><a href="#l101"></a>
<span id="l102"> *      int i = ois.readInt();</span><a href="#l102"></a>
<span id="l103"> *      String today = (String) ois.readObject();</span><a href="#l103"></a>
<span id="l104"> *      Date date = (Date) ois.readObject();</span><a href="#l104"></a>
<span id="l105"> *</span><a href="#l105"></a>
<span id="l106"> *      ois.close();</span><a href="#l106"></a>
<span id="l107"> * &lt;/pre&gt;</span><a href="#l107"></a>
<span id="l108"> *</span><a href="#l108"></a>
<span id="l109"> * &lt;p&gt;Classes control how they are serialized by implementing either the</span><a href="#l109"></a>
<span id="l110"> * java.io.Serializable or java.io.Externalizable interfaces.</span><a href="#l110"></a>
<span id="l111"> *</span><a href="#l111"></a>
<span id="l112"> * &lt;p&gt;Implementing the Serializable interface allows object serialization to</span><a href="#l112"></a>
<span id="l113"> * save and restore the entire state of the object and it allows classes to</span><a href="#l113"></a>
<span id="l114"> * evolve between the time the stream is written and the time it is read.  It</span><a href="#l114"></a>
<span id="l115"> * automatically traverses references between objects, saving and restoring</span><a href="#l115"></a>
<span id="l116"> * entire graphs.</span><a href="#l116"></a>
<span id="l117"> *</span><a href="#l117"></a>
<span id="l118"> * &lt;p&gt;Serializable classes that require special handling during the</span><a href="#l118"></a>
<span id="l119"> * serialization and deserialization process should implement the following</span><a href="#l119"></a>
<span id="l120"> * methods:</span><a href="#l120"></a>
<span id="l121"> *</span><a href="#l121"></a>
<span id="l122"> * &lt;pre&gt;</span><a href="#l122"></a>
<span id="l123"> * private void writeObject(java.io.ObjectOutputStream stream)</span><a href="#l123"></a>
<span id="l124"> *     throws IOException;</span><a href="#l124"></a>
<span id="l125"> * private void readObject(java.io.ObjectInputStream stream)</span><a href="#l125"></a>
<span id="l126"> *     throws IOException, ClassNotFoundException;</span><a href="#l126"></a>
<span id="l127"> * private void readObjectNoData()</span><a href="#l127"></a>
<span id="l128"> *     throws ObjectStreamException;</span><a href="#l128"></a>
<span id="l129"> * &lt;/pre&gt;</span><a href="#l129"></a>
<span id="l130"> *</span><a href="#l130"></a>
<span id="l131"> * &lt;p&gt;The readObject method is responsible for reading and restoring the state</span><a href="#l131"></a>
<span id="l132"> * of the object for its particular class using data written to the stream by</span><a href="#l132"></a>
<span id="l133"> * the corresponding writeObject method.  The method does not need to concern</span><a href="#l133"></a>
<span id="l134"> * itself with the state belonging to its superclasses or subclasses.  State is</span><a href="#l134"></a>
<span id="l135"> * restored by reading data from the ObjectInputStream for the individual</span><a href="#l135"></a>
<span id="l136"> * fields and making assignments to the appropriate fields of the object.</span><a href="#l136"></a>
<span id="l137"> * Reading primitive data types is supported by DataInput.</span><a href="#l137"></a>
<span id="l138"> *</span><a href="#l138"></a>
<span id="l139"> * &lt;p&gt;Any attempt to read object data which exceeds the boundaries of the</span><a href="#l139"></a>
<span id="l140"> * custom data written by the corresponding writeObject method will cause an</span><a href="#l140"></a>
<span id="l141"> * OptionalDataException to be thrown with an eof field value of true.</span><a href="#l141"></a>
<span id="l142"> * Non-object reads which exceed the end of the allotted data will reflect the</span><a href="#l142"></a>
<span id="l143"> * end of data in the same way that they would indicate the end of the stream:</span><a href="#l143"></a>
<span id="l144"> * bytewise reads will return -1 as the byte read or number of bytes read, and</span><a href="#l144"></a>
<span id="l145"> * primitive reads will throw EOFExceptions.  If there is no corresponding</span><a href="#l145"></a>
<span id="l146"> * writeObject method, then the end of default serialized data marks the end of</span><a href="#l146"></a>
<span id="l147"> * the allotted data.</span><a href="#l147"></a>
<span id="l148"> *</span><a href="#l148"></a>
<span id="l149"> * &lt;p&gt;Primitive and object read calls issued from within a readExternal method</span><a href="#l149"></a>
<span id="l150"> * behave in the same manner--if the stream is already positioned at the end of</span><a href="#l150"></a>
<span id="l151"> * data written by the corresponding writeExternal method, object reads will</span><a href="#l151"></a>
<span id="l152"> * throw OptionalDataExceptions with eof set to true, bytewise reads will</span><a href="#l152"></a>
<span id="l153"> * return -1, and primitive reads will throw EOFExceptions.  Note that this</span><a href="#l153"></a>
<span id="l154"> * behavior does not hold for streams written with the old</span><a href="#l154"></a>
<span id="l155"> * &lt;code&gt;ObjectStreamConstants.PROTOCOL_VERSION_1&lt;/code&gt; protocol, in which the</span><a href="#l155"></a>
<span id="l156"> * end of data written by writeExternal methods is not demarcated, and hence</span><a href="#l156"></a>
<span id="l157"> * cannot be detected.</span><a href="#l157"></a>
<span id="l158"> *</span><a href="#l158"></a>
<span id="l159"> * &lt;p&gt;The readObjectNoData method is responsible for initializing the state of</span><a href="#l159"></a>
<span id="l160"> * the object for its particular class in the event that the serialization</span><a href="#l160"></a>
<span id="l161"> * stream does not list the given class as a superclass of the object being</span><a href="#l161"></a>
<span id="l162"> * deserialized.  This may occur in cases where the receiving party uses a</span><a href="#l162"></a>
<span id="l163"> * different version of the deserialized instance's class than the sending</span><a href="#l163"></a>
<span id="l164"> * party, and the receiver's version extends classes that are not extended by</span><a href="#l164"></a>
<span id="l165"> * the sender's version.  This may also occur if the serialization stream has</span><a href="#l165"></a>
<span id="l166"> * been tampered; hence, readObjectNoData is useful for initializing</span><a href="#l166"></a>
<span id="l167"> * deserialized objects properly despite a &quot;hostile&quot; or incomplete source</span><a href="#l167"></a>
<span id="l168"> * stream.</span><a href="#l168"></a>
<span id="l169"> *</span><a href="#l169"></a>
<span id="l170"> * &lt;p&gt;Serialization does not read or assign values to the fields of any object</span><a href="#l170"></a>
<span id="l171"> * that does not implement the java.io.Serializable interface.  Subclasses of</span><a href="#l171"></a>
<span id="l172"> * Objects that are not serializable can be serializable. In this case the</span><a href="#l172"></a>
<span id="l173"> * non-serializable class must have a no-arg constructor to allow its fields to</span><a href="#l173"></a>
<span id="l174"> * be initialized.  In this case it is the responsibility of the subclass to</span><a href="#l174"></a>
<span id="l175"> * save and restore the state of the non-serializable class. It is frequently</span><a href="#l175"></a>
<span id="l176"> * the case that the fields of that class are accessible (public, package, or</span><a href="#l176"></a>
<span id="l177"> * protected) or that there are get and set methods that can be used to restore</span><a href="#l177"></a>
<span id="l178"> * the state.</span><a href="#l178"></a>
<span id="l179"> *</span><a href="#l179"></a>
<span id="l180"> * &lt;p&gt;Any exception that occurs while deserializing an object will be caught by</span><a href="#l180"></a>
<span id="l181"> * the ObjectInputStream and abort the reading process.</span><a href="#l181"></a>
<span id="l182"> *</span><a href="#l182"></a>
<span id="l183"> * &lt;p&gt;Implementing the Externalizable interface allows the object to assume</span><a href="#l183"></a>
<span id="l184"> * complete control over the contents and format of the object's serialized</span><a href="#l184"></a>
<span id="l185"> * form.  The methods of the Externalizable interface, writeExternal and</span><a href="#l185"></a>
<span id="l186"> * readExternal, are called to save and restore the objects state.  When</span><a href="#l186"></a>
<span id="l187"> * implemented by a class they can write and read their own state using all of</span><a href="#l187"></a>
<span id="l188"> * the methods of ObjectOutput and ObjectInput.  It is the responsibility of</span><a href="#l188"></a>
<span id="l189"> * the objects to handle any versioning that occurs.</span><a href="#l189"></a>
<span id="l190"> *</span><a href="#l190"></a>
<span id="l191"> * &lt;p&gt;Enum constants are deserialized differently than ordinary serializable or</span><a href="#l191"></a>
<span id="l192"> * externalizable objects.  The serialized form of an enum constant consists</span><a href="#l192"></a>
<span id="l193"> * solely of its name; field values of the constant are not transmitted.  To</span><a href="#l193"></a>
<span id="l194"> * deserialize an enum constant, ObjectInputStream reads the constant name from</span><a href="#l194"></a>
<span id="l195"> * the stream; the deserialized constant is then obtained by calling the static</span><a href="#l195"></a>
<span id="l196"> * method &lt;code&gt;Enum.valueOf(Class, String)&lt;/code&gt; with the enum constant's</span><a href="#l196"></a>
<span id="l197"> * base type and the received constant name as arguments.  Like other</span><a href="#l197"></a>
<span id="l198"> * serializable or externalizable objects, enum constants can function as the</span><a href="#l198"></a>
<span id="l199"> * targets of back references appearing subsequently in the serialization</span><a href="#l199"></a>
<span id="l200"> * stream.  The process by which enum constants are deserialized cannot be</span><a href="#l200"></a>
<span id="l201"> * customized: any class-specific readObject, readObjectNoData, and readResolve</span><a href="#l201"></a>
<span id="l202"> * methods defined by enum types are ignored during deserialization.</span><a href="#l202"></a>
<span id="l203"> * Similarly, any serialPersistentFields or serialVersionUID field declarations</span><a href="#l203"></a>
<span id="l204"> * are also ignored--all enum types have a fixed serialVersionUID of 0L.</span><a href="#l204"></a>
<span id="l205"> *</span><a href="#l205"></a>
<span id="l206"> * @author      Mike Warres</span><a href="#l206"></a>
<span id="l207"> * @author      Roger Riggs</span><a href="#l207"></a>
<span id="l208"> * @see java.io.DataInput</span><a href="#l208"></a>
<span id="l209"> * @see java.io.ObjectOutputStream</span><a href="#l209"></a>
<span id="l210"> * @see java.io.Serializable</span><a href="#l210"></a>
<span id="l211"> * @see &lt;a href=&quot;../../../platform/serialization/spec/input.html&quot;&gt; Object Serialization Specification, Section 3, Object Input Classes&lt;/a&gt;</span><a href="#l211"></a>
<span id="l212"> * @since   JDK1.1</span><a href="#l212"></a>
<span id="l213"> */</span><a href="#l213"></a>
<span id="l214">public class ObjectInputStream</span><a href="#l214"></a>
<span id="l215">    extends InputStream implements ObjectInput, ObjectStreamConstants</span><a href="#l215"></a>
<span id="l216">{</span><a href="#l216"></a>
<span id="l217">    /** handle value representing null */</span><a href="#l217"></a>
<span id="l218">    private static final int NULL_HANDLE = -1;</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">    /** marker for unshared objects in internal handle table */</span><a href="#l220"></a>
<span id="l221">    private static final Object unsharedMarker = new Object();</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">    /** table mapping primitive type names to corresponding class objects */</span><a href="#l223"></a>
<span id="l224">    private static final HashMap&lt;String, Class&lt;?&gt;&gt; primClasses</span><a href="#l224"></a>
<span id="l225">        = new HashMap&lt;&gt;(8, 1.0F);</span><a href="#l225"></a>
<span id="l226">    static {</span><a href="#l226"></a>
<span id="l227">        primClasses.put(&quot;boolean&quot;, boolean.class);</span><a href="#l227"></a>
<span id="l228">        primClasses.put(&quot;byte&quot;, byte.class);</span><a href="#l228"></a>
<span id="l229">        primClasses.put(&quot;char&quot;, char.class);</span><a href="#l229"></a>
<span id="l230">        primClasses.put(&quot;short&quot;, short.class);</span><a href="#l230"></a>
<span id="l231">        primClasses.put(&quot;int&quot;, int.class);</span><a href="#l231"></a>
<span id="l232">        primClasses.put(&quot;long&quot;, long.class);</span><a href="#l232"></a>
<span id="l233">        primClasses.put(&quot;float&quot;, float.class);</span><a href="#l233"></a>
<span id="l234">        primClasses.put(&quot;double&quot;, double.class);</span><a href="#l234"></a>
<span id="l235">        primClasses.put(&quot;void&quot;, void.class);</span><a href="#l235"></a>
<span id="l236">    }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">    private static class Caches {</span><a href="#l238"></a>
<span id="l239">        /** cache of subclass security audit results */</span><a href="#l239"></a>
<span id="l240">        static final ConcurrentMap&lt;WeakClassKey,Boolean&gt; subclassAudits =</span><a href="#l240"></a>
<span id="l241">            new ConcurrentHashMap&lt;&gt;();</span><a href="#l241"></a>
<span id="l242"></span><a href="#l242"></a>
<span id="l243">        /** queue for WeakReferences to audited subclasses */</span><a href="#l243"></a>
<span id="l244">        static final ReferenceQueue&lt;Class&lt;?&gt;&gt; subclassAuditsQueue =</span><a href="#l244"></a>
<span id="l245">            new ReferenceQueue&lt;&gt;();</span><a href="#l245"></a>
<span id="l246">    }</span><a href="#l246"></a>
<span id="l247"></span><a href="#l247"></a>
<span id="l248">    static {</span><a href="#l248"></a>
<span id="l249">        /* Setup access so sun.misc can invoke package private functions. */</span><a href="#l249"></a>
<span id="l250">        JavaOISAccess javaOISAccess = new JavaOISAccess() {</span><a href="#l250"></a>
<span id="l251">            public void setObjectInputFilter(ObjectInputStream stream, ObjectInputFilter filter) {</span><a href="#l251"></a>
<span id="l252">                stream.setInternalObjectInputFilter(filter);</span><a href="#l252"></a>
<span id="l253">            }</span><a href="#l253"></a>
<span id="l254"></span><a href="#l254"></a>
<span id="l255">            public ObjectInputFilter getObjectInputFilter(ObjectInputStream stream) {</span><a href="#l255"></a>
<span id="l256">                return stream.getInternalObjectInputFilter();</span><a href="#l256"></a>
<span id="l257">            }</span><a href="#l257"></a>
<span id="l258"></span><a href="#l258"></a>
<span id="l259">            public void checkArray(ObjectInputStream stream, Class&lt;?&gt; arrayType, int arrayLength)</span><a href="#l259"></a>
<span id="l260">                throws InvalidClassException</span><a href="#l260"></a>
<span id="l261">            {</span><a href="#l261"></a>
<span id="l262">                stream.checkArray(arrayType, arrayLength);</span><a href="#l262"></a>
<span id="l263">            }</span><a href="#l263"></a>
<span id="l264">        };</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">        sun.misc.SharedSecrets.setJavaOISAccess(javaOISAccess);</span><a href="#l266"></a>
<span id="l267">    }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">    /*</span><a href="#l269"></a>
<span id="l270">     * Separate class to defer initialization of logging until needed.</span><a href="#l270"></a>
<span id="l271">     */</span><a href="#l271"></a>
<span id="l272">    private static class Logging {</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">        /*</span><a href="#l274"></a>
<span id="l275">         * Logger for ObjectInputFilter results.</span><a href="#l275"></a>
<span id="l276">         * Setup the filter logger if it is set to INFO or WARNING.</span><a href="#l276"></a>
<span id="l277">         * (Assuming it will not change).</span><a href="#l277"></a>
<span id="l278">         */</span><a href="#l278"></a>
<span id="l279">        private static final PlatformLogger traceLogger;</span><a href="#l279"></a>
<span id="l280">        private static final PlatformLogger infoLogger;</span><a href="#l280"></a>
<span id="l281">        static {</span><a href="#l281"></a>
<span id="l282">            PlatformLogger filterLog = PlatformLogger.getLogger(&quot;java.io.serialization&quot;);</span><a href="#l282"></a>
<span id="l283">            infoLogger = (filterLog != null &amp;&amp;</span><a href="#l283"></a>
<span id="l284">                filterLog.isLoggable(PlatformLogger.Level.INFO)) ? filterLog : null;</span><a href="#l284"></a>
<span id="l285">            traceLogger = (filterLog != null &amp;&amp;</span><a href="#l285"></a>
<span id="l286">                filterLog.isLoggable(PlatformLogger.Level.FINER)) ? filterLog : null;</span><a href="#l286"></a>
<span id="l287">        }</span><a href="#l287"></a>
<span id="l288">    }</span><a href="#l288"></a>
<span id="l289"></span><a href="#l289"></a>
<span id="l290">    /** filter stream for handling block data conversion */</span><a href="#l290"></a>
<span id="l291">    private final BlockDataInputStream bin;</span><a href="#l291"></a>
<span id="l292">    /** validation callback list */</span><a href="#l292"></a>
<span id="l293">    private final ValidationList vlist;</span><a href="#l293"></a>
<span id="l294">    /** recursion depth */</span><a href="#l294"></a>
<span id="l295">    private long depth;</span><a href="#l295"></a>
<span id="l296">    /** Total number of references to any type of object, class, enum, proxy, etc. */</span><a href="#l296"></a>
<span id="l297">    private long totalObjectRefs;</span><a href="#l297"></a>
<span id="l298">    /** whether stream is closed */</span><a href="#l298"></a>
<span id="l299">    private boolean closed;</span><a href="#l299"></a>
<span id="l300"></span><a href="#l300"></a>
<span id="l301">    /** wire handle -&gt; obj/exception map */</span><a href="#l301"></a>
<span id="l302">    private final HandleTable handles;</span><a href="#l302"></a>
<span id="l303">    /** scratch field for passing handle values up/down call stack */</span><a href="#l303"></a>
<span id="l304">    private int passHandle = NULL_HANDLE;</span><a href="#l304"></a>
<span id="l305">    /** flag set when at end of field value block with no TC_ENDBLOCKDATA */</span><a href="#l305"></a>
<span id="l306">    private boolean defaultDataEnd = false;</span><a href="#l306"></a>
<span id="l307"></span><a href="#l307"></a>
<span id="l308">    /** buffer for reading primitive field values */</span><a href="#l308"></a>
<span id="l309">    private byte[] primVals;</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">    /** if true, invoke readObjectOverride() instead of readObject() */</span><a href="#l311"></a>
<span id="l312">    private final boolean enableOverride;</span><a href="#l312"></a>
<span id="l313">    /** if true, invoke resolveObject() */</span><a href="#l313"></a>
<span id="l314">    private boolean enableResolve;</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    /**</span><a href="#l316"></a>
<span id="l317">     * Context during upcalls to class-defined readObject methods; holds</span><a href="#l317"></a>
<span id="l318">     * object currently being deserialized and descriptor for current class.</span><a href="#l318"></a>
<span id="l319">     * Null when not during readObject upcall.</span><a href="#l319"></a>
<span id="l320">     */</span><a href="#l320"></a>
<span id="l321">    private SerialCallbackContext curContext;</span><a href="#l321"></a>
<span id="l322"></span><a href="#l322"></a>
<span id="l323">    /**</span><a href="#l323"></a>
<span id="l324">     * Filter of class descriptors and classes read from the stream;</span><a href="#l324"></a>
<span id="l325">     * may be null.</span><a href="#l325"></a>
<span id="l326">     */</span><a href="#l326"></a>
<span id="l327">    private ObjectInputFilter serialFilter;</span><a href="#l327"></a>
<span id="l328"></span><a href="#l328"></a>
<span id="l329">    /**</span><a href="#l329"></a>
<span id="l330">     * Creates an ObjectInputStream that reads from the specified InputStream.</span><a href="#l330"></a>
<span id="l331">     * A serialization stream header is read from the stream and verified.</span><a href="#l331"></a>
<span id="l332">     * This constructor will block until the corresponding ObjectOutputStream</span><a href="#l332"></a>
<span id="l333">     * has written and flushed the header.</span><a href="#l333"></a>
<span id="l334">     *</span><a href="#l334"></a>
<span id="l335">     * &lt;p&gt;If a security manager is installed, this constructor will check for</span><a href="#l335"></a>
<span id="l336">     * the &quot;enableSubclassImplementation&quot; SerializablePermission when invoked</span><a href="#l336"></a>
<span id="l337">     * directly or indirectly by the constructor of a subclass which overrides</span><a href="#l337"></a>
<span id="l338">     * the ObjectInputStream.readFields or ObjectInputStream.readUnshared</span><a href="#l338"></a>
<span id="l339">     * methods.</span><a href="#l339"></a>
<span id="l340">     *</span><a href="#l340"></a>
<span id="l341">     * @param   in input stream to read from</span><a href="#l341"></a>
<span id="l342">     * @throws  StreamCorruptedException if the stream header is incorrect</span><a href="#l342"></a>
<span id="l343">     * @throws  IOException if an I/O error occurs while reading stream header</span><a href="#l343"></a>
<span id="l344">     * @throws  SecurityException if untrusted subclass illegally overrides</span><a href="#l344"></a>
<span id="l345">     *          security-sensitive methods</span><a href="#l345"></a>
<span id="l346">     * @throws  NullPointerException if &lt;code&gt;in&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;</span><a href="#l346"></a>
<span id="l347">     * @see     ObjectInputStream#ObjectInputStream()</span><a href="#l347"></a>
<span id="l348">     * @see     ObjectInputStream#readFields()</span><a href="#l348"></a>
<span id="l349">     * @see     ObjectOutputStream#ObjectOutputStream(OutputStream)</span><a href="#l349"></a>
<span id="l350">     */</span><a href="#l350"></a>
<span id="l351">    public ObjectInputStream(InputStream in) throws IOException {</span><a href="#l351"></a>
<span id="l352">        verifySubclass();</span><a href="#l352"></a>
<span id="l353">        bin = new BlockDataInputStream(in);</span><a href="#l353"></a>
<span id="l354">        handles = new HandleTable(10);</span><a href="#l354"></a>
<span id="l355">        vlist = new ValidationList();</span><a href="#l355"></a>
<span id="l356">        serialFilter = ObjectInputFilter.Config.getSerialFilter();</span><a href="#l356"></a>
<span id="l357">        enableOverride = false;</span><a href="#l357"></a>
<span id="l358">        readStreamHeader();</span><a href="#l358"></a>
<span id="l359">        bin.setBlockDataMode(true);</span><a href="#l359"></a>
<span id="l360">    }</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362">    /**</span><a href="#l362"></a>
<span id="l363">     * Provide a way for subclasses that are completely reimplementing</span><a href="#l363"></a>
<span id="l364">     * ObjectInputStream to not have to allocate private data just used by this</span><a href="#l364"></a>
<span id="l365">     * implementation of ObjectInputStream.</span><a href="#l365"></a>
<span id="l366">     *</span><a href="#l366"></a>
<span id="l367">     * &lt;p&gt;If there is a security manager installed, this method first calls the</span><a href="#l367"></a>
<span id="l368">     * security manager's &lt;code&gt;checkPermission&lt;/code&gt; method with the</span><a href="#l368"></a>
<span id="l369">     * &lt;code&gt;SerializablePermission(&quot;enableSubclassImplementation&quot;)&lt;/code&gt;</span><a href="#l369"></a>
<span id="l370">     * permission to ensure it's ok to enable subclassing.</span><a href="#l370"></a>
<span id="l371">     *</span><a href="#l371"></a>
<span id="l372">     * @throws  SecurityException if a security manager exists and its</span><a href="#l372"></a>
<span id="l373">     *          &lt;code&gt;checkPermission&lt;/code&gt; method denies enabling</span><a href="#l373"></a>
<span id="l374">     *          subclassing.</span><a href="#l374"></a>
<span id="l375">     * @throws  IOException if an I/O error occurs while creating this stream</span><a href="#l375"></a>
<span id="l376">     * @see SecurityManager#checkPermission</span><a href="#l376"></a>
<span id="l377">     * @see java.io.SerializablePermission</span><a href="#l377"></a>
<span id="l378">     */</span><a href="#l378"></a>
<span id="l379">    protected ObjectInputStream() throws IOException, SecurityException {</span><a href="#l379"></a>
<span id="l380">        SecurityManager sm = System.getSecurityManager();</span><a href="#l380"></a>
<span id="l381">        if (sm != null) {</span><a href="#l381"></a>
<span id="l382">            sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);</span><a href="#l382"></a>
<span id="l383">        }</span><a href="#l383"></a>
<span id="l384">        bin = null;</span><a href="#l384"></a>
<span id="l385">        handles = null;</span><a href="#l385"></a>
<span id="l386">        vlist = null;</span><a href="#l386"></a>
<span id="l387">        serialFilter = ObjectInputFilter.Config.getSerialFilter();</span><a href="#l387"></a>
<span id="l388">        enableOverride = true;</span><a href="#l388"></a>
<span id="l389">    }</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">    /**</span><a href="#l391"></a>
<span id="l392">     * Read an object from the ObjectInputStream.  The class of the object, the</span><a href="#l392"></a>
<span id="l393">     * signature of the class, and the values of the non-transient and</span><a href="#l393"></a>
<span id="l394">     * non-static fields of the class and all of its supertypes are read.</span><a href="#l394"></a>
<span id="l395">     * Default deserializing for a class can be overridden using the writeObject</span><a href="#l395"></a>
<span id="l396">     * and readObject methods.  Objects referenced by this object are read</span><a href="#l396"></a>
<span id="l397">     * transitively so that a complete equivalent graph of objects is</span><a href="#l397"></a>
<span id="l398">     * reconstructed by readObject.</span><a href="#l398"></a>
<span id="l399">     *</span><a href="#l399"></a>
<span id="l400">     * &lt;p&gt;The root object is completely restored when all of its fields and the</span><a href="#l400"></a>
<span id="l401">     * objects it references are completely restored.  At this point the object</span><a href="#l401"></a>
<span id="l402">     * validation callbacks are executed in order based on their registered</span><a href="#l402"></a>
<span id="l403">     * priorities. The callbacks are registered by objects (in the readObject</span><a href="#l403"></a>
<span id="l404">     * special methods) as they are individually restored.</span><a href="#l404"></a>
<span id="l405">     *</span><a href="#l405"></a>
<span id="l406">     * &lt;p&gt;Exceptions are thrown for problems with the InputStream and for</span><a href="#l406"></a>
<span id="l407">     * classes that should not be deserialized.  All exceptions are fatal to</span><a href="#l407"></a>
<span id="l408">     * the InputStream and leave it in an indeterminate state; it is up to the</span><a href="#l408"></a>
<span id="l409">     * caller to ignore or recover the stream state.</span><a href="#l409"></a>
<span id="l410">     *</span><a href="#l410"></a>
<span id="l411">     * @throws  ClassNotFoundException Class of a serialized object cannot be</span><a href="#l411"></a>
<span id="l412">     *          found.</span><a href="#l412"></a>
<span id="l413">     * @throws  InvalidClassException Something is wrong with a class used by</span><a href="#l413"></a>
<span id="l414">     *          serialization.</span><a href="#l414"></a>
<span id="l415">     * @throws  StreamCorruptedException Control information in the</span><a href="#l415"></a>
<span id="l416">     *          stream is inconsistent.</span><a href="#l416"></a>
<span id="l417">     * @throws  OptionalDataException Primitive data was found in the</span><a href="#l417"></a>
<span id="l418">     *          stream instead of objects.</span><a href="#l418"></a>
<span id="l419">     * @throws  IOException Any of the usual Input/Output related exceptions.</span><a href="#l419"></a>
<span id="l420">     */</span><a href="#l420"></a>
<span id="l421">    public final Object readObject()</span><a href="#l421"></a>
<span id="l422">        throws IOException, ClassNotFoundException {</span><a href="#l422"></a>
<span id="l423">        return readObject(Object.class);</span><a href="#l423"></a>
<span id="l424">    }</span><a href="#l424"></a>
<span id="l425"></span><a href="#l425"></a>
<span id="l426">    /**</span><a href="#l426"></a>
<span id="l427">     * Reads a String and only a string.</span><a href="#l427"></a>
<span id="l428">     *</span><a href="#l428"></a>
<span id="l429">     * @return  the String read</span><a href="#l429"></a>
<span id="l430">     * @throws  EOFException If end of file is reached.</span><a href="#l430"></a>
<span id="l431">     * @throws  IOException If other I/O error has occurred.</span><a href="#l431"></a>
<span id="l432">     */</span><a href="#l432"></a>
<span id="l433">    private String readString() throws IOException {</span><a href="#l433"></a>
<span id="l434">        try {</span><a href="#l434"></a>
<span id="l435">            return (String) readObject(String.class);</span><a href="#l435"></a>
<span id="l436">        } catch (ClassNotFoundException cnf) {</span><a href="#l436"></a>
<span id="l437">            throw new IllegalStateException(cnf);</span><a href="#l437"></a>
<span id="l438">        }</span><a href="#l438"></a>
<span id="l439">    }</span><a href="#l439"></a>
<span id="l440"></span><a href="#l440"></a>
<span id="l441">    /**</span><a href="#l441"></a>
<span id="l442">     * Internal method to read an object from the ObjectInputStream of the expected type.</span><a href="#l442"></a>
<span id="l443">     * Called only from {@code readObject()} and {@code readString()}.</span><a href="#l443"></a>
<span id="l444">     * Only {@code Object.class} and {@code String.class} are supported.</span><a href="#l444"></a>
<span id="l445">     *</span><a href="#l445"></a>
<span id="l446">     * @param type the type expected; either Object.class or String.class</span><a href="#l446"></a>
<span id="l447">     * @return an object of the type</span><a href="#l447"></a>
<span id="l448">     * @throws  IOException Any of the usual Input/Output related exceptions.</span><a href="#l448"></a>
<span id="l449">     * @throws  ClassNotFoundException Class of a serialized object cannot be</span><a href="#l449"></a>
<span id="l450">     *          found.</span><a href="#l450"></a>
<span id="l451">     */</span><a href="#l451"></a>
<span id="l452">    private final Object readObject(Class&lt;?&gt; type)</span><a href="#l452"></a>
<span id="l453">        throws IOException, ClassNotFoundException</span><a href="#l453"></a>
<span id="l454">    {</span><a href="#l454"></a>
<span id="l455">        if (enableOverride) {</span><a href="#l455"></a>
<span id="l456">            return readObjectOverride();</span><a href="#l456"></a>
<span id="l457">        }</span><a href="#l457"></a>
<span id="l458"></span><a href="#l458"></a>
<span id="l459">        if (! (type == Object.class || type == String.class))</span><a href="#l459"></a>
<span id="l460">            throw new AssertionError(&quot;internal error&quot;);</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">        // if nested read, passHandle contains handle of enclosing object</span><a href="#l462"></a>
<span id="l463">        int outerHandle = passHandle;</span><a href="#l463"></a>
<span id="l464">        try {</span><a href="#l464"></a>
<span id="l465">            Object obj = readObject0(type, false);</span><a href="#l465"></a>
<span id="l466">            handles.markDependency(outerHandle, passHandle);</span><a href="#l466"></a>
<span id="l467">            ClassNotFoundException ex = handles.lookupException(passHandle);</span><a href="#l467"></a>
<span id="l468">            if (ex != null) {</span><a href="#l468"></a>
<span id="l469">                throw ex;</span><a href="#l469"></a>
<span id="l470">            }</span><a href="#l470"></a>
<span id="l471">            if (depth == 0) {</span><a href="#l471"></a>
<span id="l472">                vlist.doCallbacks();</span><a href="#l472"></a>
<span id="l473">            }</span><a href="#l473"></a>
<span id="l474">            return obj;</span><a href="#l474"></a>
<span id="l475">        } finally {</span><a href="#l475"></a>
<span id="l476">            passHandle = outerHandle;</span><a href="#l476"></a>
<span id="l477">            if (closed &amp;&amp; depth == 0) {</span><a href="#l477"></a>
<span id="l478">                clear();</span><a href="#l478"></a>
<span id="l479">            }</span><a href="#l479"></a>
<span id="l480">        }</span><a href="#l480"></a>
<span id="l481">    }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">    /**</span><a href="#l483"></a>
<span id="l484">     * This method is called by trusted subclasses of ObjectOutputStream that</span><a href="#l484"></a>
<span id="l485">     * constructed ObjectOutputStream using the protected no-arg constructor.</span><a href="#l485"></a>
<span id="l486">     * The subclass is expected to provide an override method with the modifier</span><a href="#l486"></a>
<span id="l487">     * &quot;final&quot;.</span><a href="#l487"></a>
<span id="l488">     *</span><a href="#l488"></a>
<span id="l489">     * @return  the Object read from the stream.</span><a href="#l489"></a>
<span id="l490">     * @throws  ClassNotFoundException Class definition of a serialized object</span><a href="#l490"></a>
<span id="l491">     *          cannot be found.</span><a href="#l491"></a>
<span id="l492">     * @throws  OptionalDataException Primitive data was found in the stream</span><a href="#l492"></a>
<span id="l493">     *          instead of objects.</span><a href="#l493"></a>
<span id="l494">     * @throws  IOException if I/O errors occurred while reading from the</span><a href="#l494"></a>
<span id="l495">     *          underlying stream</span><a href="#l495"></a>
<span id="l496">     * @see #ObjectInputStream()</span><a href="#l496"></a>
<span id="l497">     * @see #readObject()</span><a href="#l497"></a>
<span id="l498">     * @since 1.2</span><a href="#l498"></a>
<span id="l499">     */</span><a href="#l499"></a>
<span id="l500">    protected Object readObjectOverride()</span><a href="#l500"></a>
<span id="l501">        throws IOException, ClassNotFoundException</span><a href="#l501"></a>
<span id="l502">    {</span><a href="#l502"></a>
<span id="l503">        return null;</span><a href="#l503"></a>
<span id="l504">    }</span><a href="#l504"></a>
<span id="l505"></span><a href="#l505"></a>
<span id="l506">    /**</span><a href="#l506"></a>
<span id="l507">     * Reads an &quot;unshared&quot; object from the ObjectInputStream.  This method is</span><a href="#l507"></a>
<span id="l508">     * identical to readObject, except that it prevents subsequent calls to</span><a href="#l508"></a>
<span id="l509">     * readObject and readUnshared from returning additional references to the</span><a href="#l509"></a>
<span id="l510">     * deserialized instance obtained via this call.  Specifically:</span><a href="#l510"></a>
<span id="l511">     * &lt;ul&gt;</span><a href="#l511"></a>
<span id="l512">     *   &lt;li&gt;If readUnshared is called to deserialize a back-reference (the</span><a href="#l512"></a>
<span id="l513">     *       stream representation of an object which has been written</span><a href="#l513"></a>
<span id="l514">     *       previously to the stream), an ObjectStreamException will be</span><a href="#l514"></a>
<span id="l515">     *       thrown.</span><a href="#l515"></a>
<span id="l516">     *</span><a href="#l516"></a>
<span id="l517">     *   &lt;li&gt;If readUnshared returns successfully, then any subsequent attempts</span><a href="#l517"></a>
<span id="l518">     *       to deserialize back-references to the stream handle deserialized</span><a href="#l518"></a>
<span id="l519">     *       by readUnshared will cause an ObjectStreamException to be thrown.</span><a href="#l519"></a>
<span id="l520">     * &lt;/ul&gt;</span><a href="#l520"></a>
<span id="l521">     * Deserializing an object via readUnshared invalidates the stream handle</span><a href="#l521"></a>
<span id="l522">     * associated with the returned object.  Note that this in itself does not</span><a href="#l522"></a>
<span id="l523">     * always guarantee that the reference returned by readUnshared is unique;</span><a href="#l523"></a>
<span id="l524">     * the deserialized object may define a readResolve method which returns an</span><a href="#l524"></a>
<span id="l525">     * object visible to other parties, or readUnshared may return a Class</span><a href="#l525"></a>
<span id="l526">     * object or enum constant obtainable elsewhere in the stream or through</span><a href="#l526"></a>
<span id="l527">     * external means. If the deserialized object defines a readResolve method</span><a href="#l527"></a>
<span id="l528">     * and the invocation of that method returns an array, then readUnshared</span><a href="#l528"></a>
<span id="l529">     * returns a shallow clone of that array; this guarantees that the returned</span><a href="#l529"></a>
<span id="l530">     * array object is unique and cannot be obtained a second time from an</span><a href="#l530"></a>
<span id="l531">     * invocation of readObject or readUnshared on the ObjectInputStream,</span><a href="#l531"></a>
<span id="l532">     * even if the underlying data stream has been manipulated.</span><a href="#l532"></a>
<span id="l533">     *</span><a href="#l533"></a>
<span id="l534">     * &lt;p&gt;ObjectInputStream subclasses which override this method can only be</span><a href="#l534"></a>
<span id="l535">     * constructed in security contexts possessing the</span><a href="#l535"></a>
<span id="l536">     * &quot;enableSubclassImplementation&quot; SerializablePermission; any attempt to</span><a href="#l536"></a>
<span id="l537">     * instantiate such a subclass without this permission will cause a</span><a href="#l537"></a>
<span id="l538">     * SecurityException to be thrown.</span><a href="#l538"></a>
<span id="l539">     *</span><a href="#l539"></a>
<span id="l540">     * @return  reference to deserialized object</span><a href="#l540"></a>
<span id="l541">     * @throws  ClassNotFoundException if class of an object to deserialize</span><a href="#l541"></a>
<span id="l542">     *          cannot be found</span><a href="#l542"></a>
<span id="l543">     * @throws  StreamCorruptedException if control information in the stream</span><a href="#l543"></a>
<span id="l544">     *          is inconsistent</span><a href="#l544"></a>
<span id="l545">     * @throws  ObjectStreamException if object to deserialize has already</span><a href="#l545"></a>
<span id="l546">     *          appeared in stream</span><a href="#l546"></a>
<span id="l547">     * @throws  OptionalDataException if primitive data is next in stream</span><a href="#l547"></a>
<span id="l548">     * @throws  IOException if an I/O error occurs during deserialization</span><a href="#l548"></a>
<span id="l549">     * @since   1.4</span><a href="#l549"></a>
<span id="l550">     */</span><a href="#l550"></a>
<span id="l551">    public Object readUnshared() throws IOException, ClassNotFoundException {</span><a href="#l551"></a>
<span id="l552">        // if nested read, passHandle contains handle of enclosing object</span><a href="#l552"></a>
<span id="l553">        int outerHandle = passHandle;</span><a href="#l553"></a>
<span id="l554">        try {</span><a href="#l554"></a>
<span id="l555">            Object obj = readObject0(Object.class, true);</span><a href="#l555"></a>
<span id="l556">            handles.markDependency(outerHandle, passHandle);</span><a href="#l556"></a>
<span id="l557">            ClassNotFoundException ex = handles.lookupException(passHandle);</span><a href="#l557"></a>
<span id="l558">            if (ex != null) {</span><a href="#l558"></a>
<span id="l559">                throw ex;</span><a href="#l559"></a>
<span id="l560">            }</span><a href="#l560"></a>
<span id="l561">            if (depth == 0) {</span><a href="#l561"></a>
<span id="l562">                vlist.doCallbacks();</span><a href="#l562"></a>
<span id="l563">            }</span><a href="#l563"></a>
<span id="l564">            return obj;</span><a href="#l564"></a>
<span id="l565">        } finally {</span><a href="#l565"></a>
<span id="l566">            passHandle = outerHandle;</span><a href="#l566"></a>
<span id="l567">            if (closed &amp;&amp; depth == 0) {</span><a href="#l567"></a>
<span id="l568">                clear();</span><a href="#l568"></a>
<span id="l569">            }</span><a href="#l569"></a>
<span id="l570">        }</span><a href="#l570"></a>
<span id="l571">    }</span><a href="#l571"></a>
<span id="l572"></span><a href="#l572"></a>
<span id="l573">    /**</span><a href="#l573"></a>
<span id="l574">     * Read the non-static and non-transient fields of the current class from</span><a href="#l574"></a>
<span id="l575">     * this stream.  This may only be called from the readObject method of the</span><a href="#l575"></a>
<span id="l576">     * class being deserialized. It will throw the NotActiveException if it is</span><a href="#l576"></a>
<span id="l577">     * called otherwise.</span><a href="#l577"></a>
<span id="l578">     *</span><a href="#l578"></a>
<span id="l579">     * @throws  ClassNotFoundException if the class of a serialized object</span><a href="#l579"></a>
<span id="l580">     *          could not be found.</span><a href="#l580"></a>
<span id="l581">     * @throws  IOException if an I/O error occurs.</span><a href="#l581"></a>
<span id="l582">     * @throws  NotActiveException if the stream is not currently reading</span><a href="#l582"></a>
<span id="l583">     *          objects.</span><a href="#l583"></a>
<span id="l584">     */</span><a href="#l584"></a>
<span id="l585">    public void defaultReadObject()</span><a href="#l585"></a>
<span id="l586">        throws IOException, ClassNotFoundException</span><a href="#l586"></a>
<span id="l587">    {</span><a href="#l587"></a>
<span id="l588">        SerialCallbackContext ctx = curContext;</span><a href="#l588"></a>
<span id="l589">        if (ctx == null) {</span><a href="#l589"></a>
<span id="l590">            throw new NotActiveException(&quot;not in call to readObject&quot;);</span><a href="#l590"></a>
<span id="l591">        }</span><a href="#l591"></a>
<span id="l592">        Object curObj = ctx.getObj();</span><a href="#l592"></a>
<span id="l593">        ObjectStreamClass curDesc = ctx.getDesc();</span><a href="#l593"></a>
<span id="l594">        bin.setBlockDataMode(false);</span><a href="#l594"></a>
<span id="l595">        defaultReadFields(curObj, curDesc);</span><a href="#l595"></a>
<span id="l596">        bin.setBlockDataMode(true);</span><a href="#l596"></a>
<span id="l597">        if (!curDesc.hasWriteObjectData()) {</span><a href="#l597"></a>
<span id="l598">            /*</span><a href="#l598"></a>
<span id="l599">             * Fix for 4360508: since stream does not contain terminating</span><a href="#l599"></a>
<span id="l600">             * TC_ENDBLOCKDATA tag, set flag so that reading code elsewhere</span><a href="#l600"></a>
<span id="l601">             * knows to simulate end-of-custom-data behavior.</span><a href="#l601"></a>
<span id="l602">             */</span><a href="#l602"></a>
<span id="l603">            defaultDataEnd = true;</span><a href="#l603"></a>
<span id="l604">        }</span><a href="#l604"></a>
<span id="l605">        ClassNotFoundException ex = handles.lookupException(passHandle);</span><a href="#l605"></a>
<span id="l606">        if (ex != null) {</span><a href="#l606"></a>
<span id="l607">            throw ex;</span><a href="#l607"></a>
<span id="l608">        }</span><a href="#l608"></a>
<span id="l609">    }</span><a href="#l609"></a>
<span id="l610"></span><a href="#l610"></a>
<span id="l611">    /**</span><a href="#l611"></a>
<span id="l612">     * Reads the persistent fields from the stream and makes them available by</span><a href="#l612"></a>
<span id="l613">     * name.</span><a href="#l613"></a>
<span id="l614">     *</span><a href="#l614"></a>
<span id="l615">     * @return  the &lt;code&gt;GetField&lt;/code&gt; object representing the persistent</span><a href="#l615"></a>
<span id="l616">     *          fields of the object being deserialized</span><a href="#l616"></a>
<span id="l617">     * @throws  ClassNotFoundException if the class of a serialized object</span><a href="#l617"></a>
<span id="l618">     *          could not be found.</span><a href="#l618"></a>
<span id="l619">     * @throws  IOException if an I/O error occurs.</span><a href="#l619"></a>
<span id="l620">     * @throws  NotActiveException if the stream is not currently reading</span><a href="#l620"></a>
<span id="l621">     *          objects.</span><a href="#l621"></a>
<span id="l622">     * @since 1.2</span><a href="#l622"></a>
<span id="l623">     */</span><a href="#l623"></a>
<span id="l624">    public ObjectInputStream.GetField readFields()</span><a href="#l624"></a>
<span id="l625">        throws IOException, ClassNotFoundException</span><a href="#l625"></a>
<span id="l626">    {</span><a href="#l626"></a>
<span id="l627">        SerialCallbackContext ctx = curContext;</span><a href="#l627"></a>
<span id="l628">        if (ctx == null) {</span><a href="#l628"></a>
<span id="l629">            throw new NotActiveException(&quot;not in call to readObject&quot;);</span><a href="#l629"></a>
<span id="l630">        }</span><a href="#l630"></a>
<span id="l631">        Object curObj = ctx.getObj();</span><a href="#l631"></a>
<span id="l632">        ObjectStreamClass curDesc = ctx.getDesc();</span><a href="#l632"></a>
<span id="l633">        bin.setBlockDataMode(false);</span><a href="#l633"></a>
<span id="l634">        GetFieldImpl getField = new GetFieldImpl(curDesc);</span><a href="#l634"></a>
<span id="l635">        getField.readFields();</span><a href="#l635"></a>
<span id="l636">        bin.setBlockDataMode(true);</span><a href="#l636"></a>
<span id="l637">        if (!curDesc.hasWriteObjectData()) {</span><a href="#l637"></a>
<span id="l638">            /*</span><a href="#l638"></a>
<span id="l639">             * Fix for 4360508: since stream does not contain terminating</span><a href="#l639"></a>
<span id="l640">             * TC_ENDBLOCKDATA tag, set flag so that reading code elsewhere</span><a href="#l640"></a>
<span id="l641">             * knows to simulate end-of-custom-data behavior.</span><a href="#l641"></a>
<span id="l642">             */</span><a href="#l642"></a>
<span id="l643">            defaultDataEnd = true;</span><a href="#l643"></a>
<span id="l644">        }</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">        return getField;</span><a href="#l646"></a>
<span id="l647">    }</span><a href="#l647"></a>
<span id="l648"></span><a href="#l648"></a>
<span id="l649">    /**</span><a href="#l649"></a>
<span id="l650">     * Register an object to be validated before the graph is returned.  While</span><a href="#l650"></a>
<span id="l651">     * similar to resolveObject these validations are called after the entire</span><a href="#l651"></a>
<span id="l652">     * graph has been reconstituted.  Typically, a readObject method will</span><a href="#l652"></a>
<span id="l653">     * register the object with the stream so that when all of the objects are</span><a href="#l653"></a>
<span id="l654">     * restored a final set of validations can be performed.</span><a href="#l654"></a>
<span id="l655">     *</span><a href="#l655"></a>
<span id="l656">     * @param   obj the object to receive the validation callback.</span><a href="#l656"></a>
<span id="l657">     * @param   prio controls the order of callbacks;zero is a good default.</span><a href="#l657"></a>
<span id="l658">     *          Use higher numbers to be called back earlier, lower numbers for</span><a href="#l658"></a>
<span id="l659">     *          later callbacks. Within a priority, callbacks are processed in</span><a href="#l659"></a>
<span id="l660">     *          no particular order.</span><a href="#l660"></a>
<span id="l661">     * @throws  NotActiveException The stream is not currently reading objects</span><a href="#l661"></a>
<span id="l662">     *          so it is invalid to register a callback.</span><a href="#l662"></a>
<span id="l663">     * @throws  InvalidObjectException The validation object is null.</span><a href="#l663"></a>
<span id="l664">     */</span><a href="#l664"></a>
<span id="l665">    public void registerValidation(ObjectInputValidation obj, int prio)</span><a href="#l665"></a>
<span id="l666">        throws NotActiveException, InvalidObjectException</span><a href="#l666"></a>
<span id="l667">    {</span><a href="#l667"></a>
<span id="l668">        if (depth == 0) {</span><a href="#l668"></a>
<span id="l669">            throw new NotActiveException(&quot;stream inactive&quot;);</span><a href="#l669"></a>
<span id="l670">        }</span><a href="#l670"></a>
<span id="l671">        vlist.register(obj, prio);</span><a href="#l671"></a>
<span id="l672">    }</span><a href="#l672"></a>
<span id="l673"></span><a href="#l673"></a>
<span id="l674">    /**</span><a href="#l674"></a>
<span id="l675">     * Load the local class equivalent of the specified stream class</span><a href="#l675"></a>
<span id="l676">     * description.  Subclasses may implement this method to allow classes to</span><a href="#l676"></a>
<span id="l677">     * be fetched from an alternate source.</span><a href="#l677"></a>
<span id="l678">     *</span><a href="#l678"></a>
<span id="l679">     * &lt;p&gt;The corresponding method in &lt;code&gt;ObjectOutputStream&lt;/code&gt; is</span><a href="#l679"></a>
<span id="l680">     * &lt;code&gt;annotateClass&lt;/code&gt;.  This method will be invoked only once for</span><a href="#l680"></a>
<span id="l681">     * each unique class in the stream.  This method can be implemented by</span><a href="#l681"></a>
<span id="l682">     * subclasses to use an alternate loading mechanism but must return a</span><a href="#l682"></a>
<span id="l683">     * &lt;code&gt;Class&lt;/code&gt; object. Once returned, if the class is not an array</span><a href="#l683"></a>
<span id="l684">     * class, its serialVersionUID is compared to the serialVersionUID of the</span><a href="#l684"></a>
<span id="l685">     * serialized class, and if there is a mismatch, the deserialization fails</span><a href="#l685"></a>
<span id="l686">     * and an {@link InvalidClassException} is thrown.</span><a href="#l686"></a>
<span id="l687">     *</span><a href="#l687"></a>
<span id="l688">     * &lt;p&gt;The default implementation of this method in</span><a href="#l688"></a>
<span id="l689">     * &lt;code&gt;ObjectInputStream&lt;/code&gt; returns the result of calling</span><a href="#l689"></a>
<span id="l690">     * &lt;pre&gt;</span><a href="#l690"></a>
<span id="l691">     *     Class.forName(desc.getName(), false, loader)</span><a href="#l691"></a>
<span id="l692">     * &lt;/pre&gt;</span><a href="#l692"></a>
<span id="l693">     * where &lt;code&gt;loader&lt;/code&gt; is determined as follows: if there is a</span><a href="#l693"></a>
<span id="l694">     * method on the current thread's stack whose declaring class was</span><a href="#l694"></a>
<span id="l695">     * defined by a user-defined class loader (and was not a generated to</span><a href="#l695"></a>
<span id="l696">     * implement reflective invocations), then &lt;code&gt;loader&lt;/code&gt; is class</span><a href="#l696"></a>
<span id="l697">     * loader corresponding to the closest such method to the currently</span><a href="#l697"></a>
<span id="l698">     * executing frame; otherwise, &lt;code&gt;loader&lt;/code&gt; is</span><a href="#l698"></a>
<span id="l699">     * &lt;code&gt;null&lt;/code&gt;. If this call results in a</span><a href="#l699"></a>
<span id="l700">     * &lt;code&gt;ClassNotFoundException&lt;/code&gt; and the name of the passed</span><a href="#l700"></a>
<span id="l701">     * &lt;code&gt;ObjectStreamClass&lt;/code&gt; instance is the Java language keyword</span><a href="#l701"></a>
<span id="l702">     * for a primitive type or void, then the &lt;code&gt;Class&lt;/code&gt; object</span><a href="#l702"></a>
<span id="l703">     * representing that primitive type or void will be returned</span><a href="#l703"></a>
<span id="l704">     * (e.g., an &lt;code&gt;ObjectStreamClass&lt;/code&gt; with the name</span><a href="#l704"></a>
<span id="l705">     * &lt;code&gt;&quot;int&quot;&lt;/code&gt; will be resolved to &lt;code&gt;Integer.TYPE&lt;/code&gt;).</span><a href="#l705"></a>
<span id="l706">     * Otherwise, the &lt;code&gt;ClassNotFoundException&lt;/code&gt; will be thrown to</span><a href="#l706"></a>
<span id="l707">     * the caller of this method.</span><a href="#l707"></a>
<span id="l708">     *</span><a href="#l708"></a>
<span id="l709">     * @param   desc an instance of class &lt;code&gt;ObjectStreamClass&lt;/code&gt;</span><a href="#l709"></a>
<span id="l710">     * @return  a &lt;code&gt;Class&lt;/code&gt; object corresponding to &lt;code&gt;desc&lt;/code&gt;</span><a href="#l710"></a>
<span id="l711">     * @throws  IOException any of the usual Input/Output exceptions.</span><a href="#l711"></a>
<span id="l712">     * @throws  ClassNotFoundException if class of a serialized object cannot</span><a href="#l712"></a>
<span id="l713">     *          be found.</span><a href="#l713"></a>
<span id="l714">     */</span><a href="#l714"></a>
<span id="l715">    protected Class&lt;?&gt; resolveClass(ObjectStreamClass desc)</span><a href="#l715"></a>
<span id="l716">        throws IOException, ClassNotFoundException</span><a href="#l716"></a>
<span id="l717">    {</span><a href="#l717"></a>
<span id="l718">        String name = desc.getName();</span><a href="#l718"></a>
<span id="l719">        try {</span><a href="#l719"></a>
<span id="l720">            return Class.forName(name, false, latestUserDefinedLoader());</span><a href="#l720"></a>
<span id="l721">        } catch (ClassNotFoundException ex) {</span><a href="#l721"></a>
<span id="l722">            Class&lt;?&gt; cl = primClasses.get(name);</span><a href="#l722"></a>
<span id="l723">            if (cl != null) {</span><a href="#l723"></a>
<span id="l724">                return cl;</span><a href="#l724"></a>
<span id="l725">            } else {</span><a href="#l725"></a>
<span id="l726">                throw ex;</span><a href="#l726"></a>
<span id="l727">            }</span><a href="#l727"></a>
<span id="l728">        }</span><a href="#l728"></a>
<span id="l729">    }</span><a href="#l729"></a>
<span id="l730"></span><a href="#l730"></a>
<span id="l731">    /**</span><a href="#l731"></a>
<span id="l732">     * Returns a proxy class that implements the interfaces named in a proxy</span><a href="#l732"></a>
<span id="l733">     * class descriptor; subclasses may implement this method to read custom</span><a href="#l733"></a>
<span id="l734">     * data from the stream along with the descriptors for dynamic proxy</span><a href="#l734"></a>
<span id="l735">     * classes, allowing them to use an alternate loading mechanism for the</span><a href="#l735"></a>
<span id="l736">     * interfaces and the proxy class.</span><a href="#l736"></a>
<span id="l737">     *</span><a href="#l737"></a>
<span id="l738">     * &lt;p&gt;This method is called exactly once for each unique proxy class</span><a href="#l738"></a>
<span id="l739">     * descriptor in the stream.</span><a href="#l739"></a>
<span id="l740">     *</span><a href="#l740"></a>
<span id="l741">     * &lt;p&gt;The corresponding method in &lt;code&gt;ObjectOutputStream&lt;/code&gt; is</span><a href="#l741"></a>
<span id="l742">     * &lt;code&gt;annotateProxyClass&lt;/code&gt;.  For a given subclass of</span><a href="#l742"></a>
<span id="l743">     * &lt;code&gt;ObjectInputStream&lt;/code&gt; that overrides this method, the</span><a href="#l743"></a>
<span id="l744">     * &lt;code&gt;annotateProxyClass&lt;/code&gt; method in the corresponding subclass of</span><a href="#l744"></a>
<span id="l745">     * &lt;code&gt;ObjectOutputStream&lt;/code&gt; must write any data or objects read by</span><a href="#l745"></a>
<span id="l746">     * this method.</span><a href="#l746"></a>
<span id="l747">     *</span><a href="#l747"></a>
<span id="l748">     * &lt;p&gt;The default implementation of this method in</span><a href="#l748"></a>
<span id="l749">     * &lt;code&gt;ObjectInputStream&lt;/code&gt; returns the result of calling</span><a href="#l749"></a>
<span id="l750">     * &lt;code&gt;Proxy.getProxyClass&lt;/code&gt; with the list of &lt;code&gt;Class&lt;/code&gt;</span><a href="#l750"></a>
<span id="l751">     * objects for the interfaces that are named in the &lt;code&gt;interfaces&lt;/code&gt;</span><a href="#l751"></a>
<span id="l752">     * parameter.  The &lt;code&gt;Class&lt;/code&gt; object for each interface name</span><a href="#l752"></a>
<span id="l753">     * &lt;code&gt;i&lt;/code&gt; is the value returned by calling</span><a href="#l753"></a>
<span id="l754">     * &lt;pre&gt;</span><a href="#l754"></a>
<span id="l755">     *     Class.forName(i, false, loader)</span><a href="#l755"></a>
<span id="l756">     * &lt;/pre&gt;</span><a href="#l756"></a>
<span id="l757">     * where &lt;code&gt;loader&lt;/code&gt; is that of the first non-&lt;code&gt;null&lt;/code&gt;</span><a href="#l757"></a>
<span id="l758">     * class loader up the execution stack, or &lt;code&gt;null&lt;/code&gt; if no</span><a href="#l758"></a>
<span id="l759">     * non-&lt;code&gt;null&lt;/code&gt; class loaders are on the stack (the same class</span><a href="#l759"></a>
<span id="l760">     * loader choice used by the &lt;code&gt;resolveClass&lt;/code&gt; method).  Unless any</span><a href="#l760"></a>
<span id="l761">     * of the resolved interfaces are non-public, this same value of</span><a href="#l761"></a>
<span id="l762">     * &lt;code&gt;loader&lt;/code&gt; is also the class loader passed to</span><a href="#l762"></a>
<span id="l763">     * &lt;code&gt;Proxy.getProxyClass&lt;/code&gt;; if non-public interfaces are present,</span><a href="#l763"></a>
<span id="l764">     * their class loader is passed instead (if more than one non-public</span><a href="#l764"></a>
<span id="l765">     * interface class loader is encountered, an</span><a href="#l765"></a>
<span id="l766">     * &lt;code&gt;IllegalAccessError&lt;/code&gt; is thrown).</span><a href="#l766"></a>
<span id="l767">     * If &lt;code&gt;Proxy.getProxyClass&lt;/code&gt; throws an</span><a href="#l767"></a>
<span id="l768">     * &lt;code&gt;IllegalArgumentException&lt;/code&gt;, &lt;code&gt;resolveProxyClass&lt;/code&gt;</span><a href="#l768"></a>
<span id="l769">     * will throw a &lt;code&gt;ClassNotFoundException&lt;/code&gt; containing the</span><a href="#l769"></a>
<span id="l770">     * &lt;code&gt;IllegalArgumentException&lt;/code&gt;.</span><a href="#l770"></a>
<span id="l771">     *</span><a href="#l771"></a>
<span id="l772">     * @param interfaces the list of interface names that were</span><a href="#l772"></a>
<span id="l773">     *                deserialized in the proxy class descriptor</span><a href="#l773"></a>
<span id="l774">     * @return  a proxy class for the specified interfaces</span><a href="#l774"></a>
<span id="l775">     * @throws        IOException any exception thrown by the underlying</span><a href="#l775"></a>
<span id="l776">     *                &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l776"></a>
<span id="l777">     * @throws        ClassNotFoundException if the proxy class or any of the</span><a href="#l777"></a>
<span id="l778">     *                named interfaces could not be found</span><a href="#l778"></a>
<span id="l779">     * @see ObjectOutputStream#annotateProxyClass(Class)</span><a href="#l779"></a>
<span id="l780">     * @since 1.3</span><a href="#l780"></a>
<span id="l781">     */</span><a href="#l781"></a>
<span id="l782">    protected Class&lt;?&gt; resolveProxyClass(String[] interfaces)</span><a href="#l782"></a>
<span id="l783">        throws IOException, ClassNotFoundException</span><a href="#l783"></a>
<span id="l784">    {</span><a href="#l784"></a>
<span id="l785">        ClassLoader latestLoader = latestUserDefinedLoader();</span><a href="#l785"></a>
<span id="l786">        ClassLoader nonPublicLoader = null;</span><a href="#l786"></a>
<span id="l787">        boolean hasNonPublicInterface = false;</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">        // define proxy in class loader of non-public interface(s), if any</span><a href="#l789"></a>
<span id="l790">        Class&lt;?&gt;[] classObjs = new Class&lt;?&gt;[interfaces.length];</span><a href="#l790"></a>
<span id="l791">        for (int i = 0; i &lt; interfaces.length; i++) {</span><a href="#l791"></a>
<span id="l792">            Class&lt;?&gt; cl = Class.forName(interfaces[i], false, latestLoader);</span><a href="#l792"></a>
<span id="l793">            if ((cl.getModifiers() &amp; Modifier.PUBLIC) == 0) {</span><a href="#l793"></a>
<span id="l794">                if (hasNonPublicInterface) {</span><a href="#l794"></a>
<span id="l795">                    if (nonPublicLoader != cl.getClassLoader()) {</span><a href="#l795"></a>
<span id="l796">                        throw new IllegalAccessError(</span><a href="#l796"></a>
<span id="l797">                            &quot;conflicting non-public interface class loaders&quot;);</span><a href="#l797"></a>
<span id="l798">                    }</span><a href="#l798"></a>
<span id="l799">                } else {</span><a href="#l799"></a>
<span id="l800">                    nonPublicLoader = cl.getClassLoader();</span><a href="#l800"></a>
<span id="l801">                    hasNonPublicInterface = true;</span><a href="#l801"></a>
<span id="l802">                }</span><a href="#l802"></a>
<span id="l803">            }</span><a href="#l803"></a>
<span id="l804">            classObjs[i] = cl;</span><a href="#l804"></a>
<span id="l805">        }</span><a href="#l805"></a>
<span id="l806">        try {</span><a href="#l806"></a>
<span id="l807">            return Proxy.getProxyClass(</span><a href="#l807"></a>
<span id="l808">                hasNonPublicInterface ? nonPublicLoader : latestLoader,</span><a href="#l808"></a>
<span id="l809">                classObjs);</span><a href="#l809"></a>
<span id="l810">        } catch (IllegalArgumentException e) {</span><a href="#l810"></a>
<span id="l811">            throw new ClassNotFoundException(null, e);</span><a href="#l811"></a>
<span id="l812">        }</span><a href="#l812"></a>
<span id="l813">    }</span><a href="#l813"></a>
<span id="l814"></span><a href="#l814"></a>
<span id="l815">    /**</span><a href="#l815"></a>
<span id="l816">     * This method will allow trusted subclasses of ObjectInputStream to</span><a href="#l816"></a>
<span id="l817">     * substitute one object for another during deserialization. Replacing</span><a href="#l817"></a>
<span id="l818">     * objects is disabled until enableResolveObject is called. The</span><a href="#l818"></a>
<span id="l819">     * enableResolveObject method checks that the stream requesting to resolve</span><a href="#l819"></a>
<span id="l820">     * object can be trusted. Every reference to serializable objects is passed</span><a href="#l820"></a>
<span id="l821">     * to resolveObject.  To insure that the private state of objects is not</span><a href="#l821"></a>
<span id="l822">     * unintentionally exposed only trusted streams may use resolveObject.</span><a href="#l822"></a>
<span id="l823">     *</span><a href="#l823"></a>
<span id="l824">     * &lt;p&gt;This method is called after an object has been read but before it is</span><a href="#l824"></a>
<span id="l825">     * returned from readObject.  The default resolveObject method just returns</span><a href="#l825"></a>
<span id="l826">     * the same object.</span><a href="#l826"></a>
<span id="l827">     *</span><a href="#l827"></a>
<span id="l828">     * &lt;p&gt;When a subclass is replacing objects it must insure that the</span><a href="#l828"></a>
<span id="l829">     * substituted object is compatible with every field where the reference</span><a href="#l829"></a>
<span id="l830">     * will be stored.  Objects whose type is not a subclass of the type of the</span><a href="#l830"></a>
<span id="l831">     * field or array element abort the serialization by raising an exception</span><a href="#l831"></a>
<span id="l832">     * and the object is not be stored.</span><a href="#l832"></a>
<span id="l833">     *</span><a href="#l833"></a>
<span id="l834">     * &lt;p&gt;This method is called only once when each object is first</span><a href="#l834"></a>
<span id="l835">     * encountered.  All subsequent references to the object will be redirected</span><a href="#l835"></a>
<span id="l836">     * to the new object.</span><a href="#l836"></a>
<span id="l837">     *</span><a href="#l837"></a>
<span id="l838">     * @param   obj object to be substituted</span><a href="#l838"></a>
<span id="l839">     * @return  the substituted object</span><a href="#l839"></a>
<span id="l840">     * @throws  IOException Any of the usual Input/Output exceptions.</span><a href="#l840"></a>
<span id="l841">     */</span><a href="#l841"></a>
<span id="l842">    protected Object resolveObject(Object obj) throws IOException {</span><a href="#l842"></a>
<span id="l843">        return obj;</span><a href="#l843"></a>
<span id="l844">    }</span><a href="#l844"></a>
<span id="l845"></span><a href="#l845"></a>
<span id="l846">    /**</span><a href="#l846"></a>
<span id="l847">     * Enable the stream to allow objects read from the stream to be replaced.</span><a href="#l847"></a>
<span id="l848">     * When enabled, the resolveObject method is called for every object being</span><a href="#l848"></a>
<span id="l849">     * deserialized.</span><a href="#l849"></a>
<span id="l850">     *</span><a href="#l850"></a>
<span id="l851">     * &lt;p&gt;If &lt;i&gt;enable&lt;/i&gt; is true, and there is a security manager installed,</span><a href="#l851"></a>
<span id="l852">     * this method first calls the security manager's</span><a href="#l852"></a>
<span id="l853">     * &lt;code&gt;checkPermission&lt;/code&gt; method with the</span><a href="#l853"></a>
<span id="l854">     * &lt;code&gt;SerializablePermission(&quot;enableSubstitution&quot;)&lt;/code&gt; permission to</span><a href="#l854"></a>
<span id="l855">     * ensure it's ok to enable the stream to allow objects read from the</span><a href="#l855"></a>
<span id="l856">     * stream to be replaced.</span><a href="#l856"></a>
<span id="l857">     *</span><a href="#l857"></a>
<span id="l858">     * @param   enable true for enabling use of &lt;code&gt;resolveObject&lt;/code&gt; for</span><a href="#l858"></a>
<span id="l859">     *          every object being deserialized</span><a href="#l859"></a>
<span id="l860">     * @return  the previous setting before this method was invoked</span><a href="#l860"></a>
<span id="l861">     * @throws  SecurityException if a security manager exists and its</span><a href="#l861"></a>
<span id="l862">     *          &lt;code&gt;checkPermission&lt;/code&gt; method denies enabling the stream</span><a href="#l862"></a>
<span id="l863">     *          to allow objects read from the stream to be replaced.</span><a href="#l863"></a>
<span id="l864">     * @see SecurityManager#checkPermission</span><a href="#l864"></a>
<span id="l865">     * @see java.io.SerializablePermission</span><a href="#l865"></a>
<span id="l866">     */</span><a href="#l866"></a>
<span id="l867">    protected boolean enableResolveObject(boolean enable)</span><a href="#l867"></a>
<span id="l868">        throws SecurityException</span><a href="#l868"></a>
<span id="l869">    {</span><a href="#l869"></a>
<span id="l870">        if (enable == enableResolve) {</span><a href="#l870"></a>
<span id="l871">            return enable;</span><a href="#l871"></a>
<span id="l872">        }</span><a href="#l872"></a>
<span id="l873">        if (enable) {</span><a href="#l873"></a>
<span id="l874">            SecurityManager sm = System.getSecurityManager();</span><a href="#l874"></a>
<span id="l875">            if (sm != null) {</span><a href="#l875"></a>
<span id="l876">                sm.checkPermission(SUBSTITUTION_PERMISSION);</span><a href="#l876"></a>
<span id="l877">            }</span><a href="#l877"></a>
<span id="l878">        }</span><a href="#l878"></a>
<span id="l879">        enableResolve = enable;</span><a href="#l879"></a>
<span id="l880">        return !enableResolve;</span><a href="#l880"></a>
<span id="l881">    }</span><a href="#l881"></a>
<span id="l882"></span><a href="#l882"></a>
<span id="l883">    /**</span><a href="#l883"></a>
<span id="l884">     * The readStreamHeader method is provided to allow subclasses to read and</span><a href="#l884"></a>
<span id="l885">     * verify their own stream headers. It reads and verifies the magic number</span><a href="#l885"></a>
<span id="l886">     * and version number.</span><a href="#l886"></a>
<span id="l887">     *</span><a href="#l887"></a>
<span id="l888">     * @throws  IOException if there are I/O errors while reading from the</span><a href="#l888"></a>
<span id="l889">     *          underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l889"></a>
<span id="l890">     * @throws  StreamCorruptedException if control information in the stream</span><a href="#l890"></a>
<span id="l891">     *          is inconsistent</span><a href="#l891"></a>
<span id="l892">     */</span><a href="#l892"></a>
<span id="l893">    protected void readStreamHeader()</span><a href="#l893"></a>
<span id="l894">        throws IOException, StreamCorruptedException</span><a href="#l894"></a>
<span id="l895">    {</span><a href="#l895"></a>
<span id="l896">        short s0 = bin.readShort();</span><a href="#l896"></a>
<span id="l897">        short s1 = bin.readShort();</span><a href="#l897"></a>
<span id="l898">        if (s0 != STREAM_MAGIC || s1 != STREAM_VERSION) {</span><a href="#l898"></a>
<span id="l899">            throw new StreamCorruptedException(</span><a href="#l899"></a>
<span id="l900">                String.format(&quot;invalid stream header: %04X%04X&quot;, s0, s1));</span><a href="#l900"></a>
<span id="l901">        }</span><a href="#l901"></a>
<span id="l902">    }</span><a href="#l902"></a>
<span id="l903"></span><a href="#l903"></a>
<span id="l904">    /**</span><a href="#l904"></a>
<span id="l905">     * Read a class descriptor from the serialization stream.  This method is</span><a href="#l905"></a>
<span id="l906">     * called when the ObjectInputStream expects a class descriptor as the next</span><a href="#l906"></a>
<span id="l907">     * item in the serialization stream.  Subclasses of ObjectInputStream may</span><a href="#l907"></a>
<span id="l908">     * override this method to read in class descriptors that have been written</span><a href="#l908"></a>
<span id="l909">     * in non-standard formats (by subclasses of ObjectOutputStream which have</span><a href="#l909"></a>
<span id="l910">     * overridden the &lt;code&gt;writeClassDescriptor&lt;/code&gt; method).  By default,</span><a href="#l910"></a>
<span id="l911">     * this method reads class descriptors according to the format defined in</span><a href="#l911"></a>
<span id="l912">     * the Object Serialization specification.</span><a href="#l912"></a>
<span id="l913">     *</span><a href="#l913"></a>
<span id="l914">     * @return  the class descriptor read</span><a href="#l914"></a>
<span id="l915">     * @throws  IOException If an I/O error has occurred.</span><a href="#l915"></a>
<span id="l916">     * @throws  ClassNotFoundException If the Class of a serialized object used</span><a href="#l916"></a>
<span id="l917">     *          in the class descriptor representation cannot be found</span><a href="#l917"></a>
<span id="l918">     * @see java.io.ObjectOutputStream#writeClassDescriptor(java.io.ObjectStreamClass)</span><a href="#l918"></a>
<span id="l919">     * @since 1.3</span><a href="#l919"></a>
<span id="l920">     */</span><a href="#l920"></a>
<span id="l921">    protected ObjectStreamClass readClassDescriptor()</span><a href="#l921"></a>
<span id="l922">        throws IOException, ClassNotFoundException</span><a href="#l922"></a>
<span id="l923">    {</span><a href="#l923"></a>
<span id="l924">        ObjectStreamClass desc = new ObjectStreamClass();</span><a href="#l924"></a>
<span id="l925">        desc.readNonProxy(this);</span><a href="#l925"></a>
<span id="l926">        return desc;</span><a href="#l926"></a>
<span id="l927">    }</span><a href="#l927"></a>
<span id="l928"></span><a href="#l928"></a>
<span id="l929">    /**</span><a href="#l929"></a>
<span id="l930">     * Reads a byte of data. This method will block if no input is available.</span><a href="#l930"></a>
<span id="l931">     *</span><a href="#l931"></a>
<span id="l932">     * @return  the byte read, or -1 if the end of the stream is reached.</span><a href="#l932"></a>
<span id="l933">     * @throws  IOException If an I/O error has occurred.</span><a href="#l933"></a>
<span id="l934">     */</span><a href="#l934"></a>
<span id="l935">    public int read() throws IOException {</span><a href="#l935"></a>
<span id="l936">        return bin.read();</span><a href="#l936"></a>
<span id="l937">    }</span><a href="#l937"></a>
<span id="l938"></span><a href="#l938"></a>
<span id="l939">    /**</span><a href="#l939"></a>
<span id="l940">     * Reads into an array of bytes.  This method will block until some input</span><a href="#l940"></a>
<span id="l941">     * is available. Consider using java.io.DataInputStream.readFully to read</span><a href="#l941"></a>
<span id="l942">     * exactly 'length' bytes.</span><a href="#l942"></a>
<span id="l943">     *</span><a href="#l943"></a>
<span id="l944">     * @param   buf the buffer into which the data is read</span><a href="#l944"></a>
<span id="l945">     * @param   off the start offset of the data</span><a href="#l945"></a>
<span id="l946">     * @param   len the maximum number of bytes read</span><a href="#l946"></a>
<span id="l947">     * @return  the actual number of bytes read, -1 is returned when the end of</span><a href="#l947"></a>
<span id="l948">     *          the stream is reached.</span><a href="#l948"></a>
<span id="l949">     * @throws  IOException If an I/O error has occurred.</span><a href="#l949"></a>
<span id="l950">     * @see java.io.DataInputStream#readFully(byte[],int,int)</span><a href="#l950"></a>
<span id="l951">     */</span><a href="#l951"></a>
<span id="l952">    public int read(byte[] buf, int off, int len) throws IOException {</span><a href="#l952"></a>
<span id="l953">        if (buf == null) {</span><a href="#l953"></a>
<span id="l954">            throw new NullPointerException();</span><a href="#l954"></a>
<span id="l955">        }</span><a href="#l955"></a>
<span id="l956">        int endoff = off + len;</span><a href="#l956"></a>
<span id="l957">        if (off &lt; 0 || len &lt; 0 || endoff &gt; buf.length || endoff &lt; 0) {</span><a href="#l957"></a>
<span id="l958">            throw new IndexOutOfBoundsException();</span><a href="#l958"></a>
<span id="l959">        }</span><a href="#l959"></a>
<span id="l960">        return bin.read(buf, off, len, false);</span><a href="#l960"></a>
<span id="l961">    }</span><a href="#l961"></a>
<span id="l962"></span><a href="#l962"></a>
<span id="l963">    /**</span><a href="#l963"></a>
<span id="l964">     * Returns the number of bytes that can be read without blocking.</span><a href="#l964"></a>
<span id="l965">     *</span><a href="#l965"></a>
<span id="l966">     * @return  the number of available bytes.</span><a href="#l966"></a>
<span id="l967">     * @throws  IOException if there are I/O errors while reading from the</span><a href="#l967"></a>
<span id="l968">     *          underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l968"></a>
<span id="l969">     */</span><a href="#l969"></a>
<span id="l970">    public int available() throws IOException {</span><a href="#l970"></a>
<span id="l971">        return bin.available();</span><a href="#l971"></a>
<span id="l972">    }</span><a href="#l972"></a>
<span id="l973"></span><a href="#l973"></a>
<span id="l974">    /**</span><a href="#l974"></a>
<span id="l975">     * Closes the input stream. Must be called to release any resources</span><a href="#l975"></a>
<span id="l976">     * associated with the stream.</span><a href="#l976"></a>
<span id="l977">     *</span><a href="#l977"></a>
<span id="l978">     * @throws  IOException If an I/O error has occurred.</span><a href="#l978"></a>
<span id="l979">     */</span><a href="#l979"></a>
<span id="l980">    public void close() throws IOException {</span><a href="#l980"></a>
<span id="l981">        /*</span><a href="#l981"></a>
<span id="l982">         * Even if stream already closed, propagate redundant close to</span><a href="#l982"></a>
<span id="l983">         * underlying stream to stay consistent with previous implementations.</span><a href="#l983"></a>
<span id="l984">         */</span><a href="#l984"></a>
<span id="l985">        closed = true;</span><a href="#l985"></a>
<span id="l986">        if (depth == 0) {</span><a href="#l986"></a>
<span id="l987">            clear();</span><a href="#l987"></a>
<span id="l988">        }</span><a href="#l988"></a>
<span id="l989">        bin.close();</span><a href="#l989"></a>
<span id="l990">    }</span><a href="#l990"></a>
<span id="l991"></span><a href="#l991"></a>
<span id="l992">    /**</span><a href="#l992"></a>
<span id="l993">     * Reads in a boolean.</span><a href="#l993"></a>
<span id="l994">     *</span><a href="#l994"></a>
<span id="l995">     * @return  the boolean read.</span><a href="#l995"></a>
<span id="l996">     * @throws  EOFException If end of file is reached.</span><a href="#l996"></a>
<span id="l997">     * @throws  IOException If other I/O error has occurred.</span><a href="#l997"></a>
<span id="l998">     */</span><a href="#l998"></a>
<span id="l999">    public boolean readBoolean() throws IOException {</span><a href="#l999"></a>
<span id="l1000">        return bin.readBoolean();</span><a href="#l1000"></a>
<span id="l1001">    }</span><a href="#l1001"></a>
<span id="l1002"></span><a href="#l1002"></a>
<span id="l1003">    /**</span><a href="#l1003"></a>
<span id="l1004">     * Reads an 8 bit byte.</span><a href="#l1004"></a>
<span id="l1005">     *</span><a href="#l1005"></a>
<span id="l1006">     * @return  the 8 bit byte read.</span><a href="#l1006"></a>
<span id="l1007">     * @throws  EOFException If end of file is reached.</span><a href="#l1007"></a>
<span id="l1008">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1008"></a>
<span id="l1009">     */</span><a href="#l1009"></a>
<span id="l1010">    public byte readByte() throws IOException  {</span><a href="#l1010"></a>
<span id="l1011">        return bin.readByte();</span><a href="#l1011"></a>
<span id="l1012">    }</span><a href="#l1012"></a>
<span id="l1013"></span><a href="#l1013"></a>
<span id="l1014">    /**</span><a href="#l1014"></a>
<span id="l1015">     * Reads an unsigned 8 bit byte.</span><a href="#l1015"></a>
<span id="l1016">     *</span><a href="#l1016"></a>
<span id="l1017">     * @return  the 8 bit byte read.</span><a href="#l1017"></a>
<span id="l1018">     * @throws  EOFException If end of file is reached.</span><a href="#l1018"></a>
<span id="l1019">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1019"></a>
<span id="l1020">     */</span><a href="#l1020"></a>
<span id="l1021">    public int readUnsignedByte()  throws IOException {</span><a href="#l1021"></a>
<span id="l1022">        return bin.readUnsignedByte();</span><a href="#l1022"></a>
<span id="l1023">    }</span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">    /**</span><a href="#l1025"></a>
<span id="l1026">     * Reads a 16 bit char.</span><a href="#l1026"></a>
<span id="l1027">     *</span><a href="#l1027"></a>
<span id="l1028">     * @return  the 16 bit char read.</span><a href="#l1028"></a>
<span id="l1029">     * @throws  EOFException If end of file is reached.</span><a href="#l1029"></a>
<span id="l1030">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1030"></a>
<span id="l1031">     */</span><a href="#l1031"></a>
<span id="l1032">    public char readChar()  throws IOException {</span><a href="#l1032"></a>
<span id="l1033">        return bin.readChar();</span><a href="#l1033"></a>
<span id="l1034">    }</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">    /**</span><a href="#l1036"></a>
<span id="l1037">     * Reads a 16 bit short.</span><a href="#l1037"></a>
<span id="l1038">     *</span><a href="#l1038"></a>
<span id="l1039">     * @return  the 16 bit short read.</span><a href="#l1039"></a>
<span id="l1040">     * @throws  EOFException If end of file is reached.</span><a href="#l1040"></a>
<span id="l1041">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1041"></a>
<span id="l1042">     */</span><a href="#l1042"></a>
<span id="l1043">    public short readShort()  throws IOException {</span><a href="#l1043"></a>
<span id="l1044">        return bin.readShort();</span><a href="#l1044"></a>
<span id="l1045">    }</span><a href="#l1045"></a>
<span id="l1046"></span><a href="#l1046"></a>
<span id="l1047">    /**</span><a href="#l1047"></a>
<span id="l1048">     * Reads an unsigned 16 bit short.</span><a href="#l1048"></a>
<span id="l1049">     *</span><a href="#l1049"></a>
<span id="l1050">     * @return  the 16 bit short read.</span><a href="#l1050"></a>
<span id="l1051">     * @throws  EOFException If end of file is reached.</span><a href="#l1051"></a>
<span id="l1052">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1052"></a>
<span id="l1053">     */</span><a href="#l1053"></a>
<span id="l1054">    public int readUnsignedShort() throws IOException {</span><a href="#l1054"></a>
<span id="l1055">        return bin.readUnsignedShort();</span><a href="#l1055"></a>
<span id="l1056">    }</span><a href="#l1056"></a>
<span id="l1057"></span><a href="#l1057"></a>
<span id="l1058">    /**</span><a href="#l1058"></a>
<span id="l1059">     * Reads a 32 bit int.</span><a href="#l1059"></a>
<span id="l1060">     *</span><a href="#l1060"></a>
<span id="l1061">     * @return  the 32 bit integer read.</span><a href="#l1061"></a>
<span id="l1062">     * @throws  EOFException If end of file is reached.</span><a href="#l1062"></a>
<span id="l1063">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1063"></a>
<span id="l1064">     */</span><a href="#l1064"></a>
<span id="l1065">    public int readInt()  throws IOException {</span><a href="#l1065"></a>
<span id="l1066">        return bin.readInt();</span><a href="#l1066"></a>
<span id="l1067">    }</span><a href="#l1067"></a>
<span id="l1068"></span><a href="#l1068"></a>
<span id="l1069">    /**</span><a href="#l1069"></a>
<span id="l1070">     * Reads a 64 bit long.</span><a href="#l1070"></a>
<span id="l1071">     *</span><a href="#l1071"></a>
<span id="l1072">     * @return  the read 64 bit long.</span><a href="#l1072"></a>
<span id="l1073">     * @throws  EOFException If end of file is reached.</span><a href="#l1073"></a>
<span id="l1074">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1074"></a>
<span id="l1075">     */</span><a href="#l1075"></a>
<span id="l1076">    public long readLong()  throws IOException {</span><a href="#l1076"></a>
<span id="l1077">        return bin.readLong();</span><a href="#l1077"></a>
<span id="l1078">    }</span><a href="#l1078"></a>
<span id="l1079"></span><a href="#l1079"></a>
<span id="l1080">    /**</span><a href="#l1080"></a>
<span id="l1081">     * Reads a 32 bit float.</span><a href="#l1081"></a>
<span id="l1082">     *</span><a href="#l1082"></a>
<span id="l1083">     * @return  the 32 bit float read.</span><a href="#l1083"></a>
<span id="l1084">     * @throws  EOFException If end of file is reached.</span><a href="#l1084"></a>
<span id="l1085">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1085"></a>
<span id="l1086">     */</span><a href="#l1086"></a>
<span id="l1087">    public float readFloat() throws IOException {</span><a href="#l1087"></a>
<span id="l1088">        return bin.readFloat();</span><a href="#l1088"></a>
<span id="l1089">    }</span><a href="#l1089"></a>
<span id="l1090"></span><a href="#l1090"></a>
<span id="l1091">    /**</span><a href="#l1091"></a>
<span id="l1092">     * Reads a 64 bit double.</span><a href="#l1092"></a>
<span id="l1093">     *</span><a href="#l1093"></a>
<span id="l1094">     * @return  the 64 bit double read.</span><a href="#l1094"></a>
<span id="l1095">     * @throws  EOFException If end of file is reached.</span><a href="#l1095"></a>
<span id="l1096">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1096"></a>
<span id="l1097">     */</span><a href="#l1097"></a>
<span id="l1098">    public double readDouble() throws IOException {</span><a href="#l1098"></a>
<span id="l1099">        return bin.readDouble();</span><a href="#l1099"></a>
<span id="l1100">    }</span><a href="#l1100"></a>
<span id="l1101"></span><a href="#l1101"></a>
<span id="l1102">    /**</span><a href="#l1102"></a>
<span id="l1103">     * Reads bytes, blocking until all bytes are read.</span><a href="#l1103"></a>
<span id="l1104">     *</span><a href="#l1104"></a>
<span id="l1105">     * @param   buf the buffer into which the data is read</span><a href="#l1105"></a>
<span id="l1106">     * @throws  EOFException If end of file is reached.</span><a href="#l1106"></a>
<span id="l1107">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1107"></a>
<span id="l1108">     */</span><a href="#l1108"></a>
<span id="l1109">    public void readFully(byte[] buf) throws IOException {</span><a href="#l1109"></a>
<span id="l1110">        bin.readFully(buf, 0, buf.length, false);</span><a href="#l1110"></a>
<span id="l1111">    }</span><a href="#l1111"></a>
<span id="l1112"></span><a href="#l1112"></a>
<span id="l1113">    /**</span><a href="#l1113"></a>
<span id="l1114">     * Reads bytes, blocking until all bytes are read.</span><a href="#l1114"></a>
<span id="l1115">     *</span><a href="#l1115"></a>
<span id="l1116">     * @param   buf the buffer into which the data is read</span><a href="#l1116"></a>
<span id="l1117">     * @param   off the start offset of the data</span><a href="#l1117"></a>
<span id="l1118">     * @param   len the maximum number of bytes to read</span><a href="#l1118"></a>
<span id="l1119">     * @throws  EOFException If end of file is reached.</span><a href="#l1119"></a>
<span id="l1120">     * @throws  IOException If other I/O error has occurred.</span><a href="#l1120"></a>
<span id="l1121">     */</span><a href="#l1121"></a>
<span id="l1122">    public void readFully(byte[] buf, int off, int len) throws IOException {</span><a href="#l1122"></a>
<span id="l1123">        int endoff = off + len;</span><a href="#l1123"></a>
<span id="l1124">        if (off &lt; 0 || len &lt; 0 || endoff &gt; buf.length || endoff &lt; 0) {</span><a href="#l1124"></a>
<span id="l1125">            throw new IndexOutOfBoundsException();</span><a href="#l1125"></a>
<span id="l1126">        }</span><a href="#l1126"></a>
<span id="l1127">        bin.readFully(buf, off, len, false);</span><a href="#l1127"></a>
<span id="l1128">    }</span><a href="#l1128"></a>
<span id="l1129"></span><a href="#l1129"></a>
<span id="l1130">    /**</span><a href="#l1130"></a>
<span id="l1131">     * Skips bytes.</span><a href="#l1131"></a>
<span id="l1132">     *</span><a href="#l1132"></a>
<span id="l1133">     * @param   len the number of bytes to be skipped</span><a href="#l1133"></a>
<span id="l1134">     * @return  the actual number of bytes skipped.</span><a href="#l1134"></a>
<span id="l1135">     * @throws  IOException If an I/O error has occurred.</span><a href="#l1135"></a>
<span id="l1136">     */</span><a href="#l1136"></a>
<span id="l1137">    public int skipBytes(int len) throws IOException {</span><a href="#l1137"></a>
<span id="l1138">        return bin.skipBytes(len);</span><a href="#l1138"></a>
<span id="l1139">    }</span><a href="#l1139"></a>
<span id="l1140"></span><a href="#l1140"></a>
<span id="l1141">    /**</span><a href="#l1141"></a>
<span id="l1142">     * Reads in a line that has been terminated by a \n, \r, \r\n or EOF.</span><a href="#l1142"></a>
<span id="l1143">     *</span><a href="#l1143"></a>
<span id="l1144">     * @return  a String copy of the line.</span><a href="#l1144"></a>
<span id="l1145">     * @throws  IOException if there are I/O errors while reading from the</span><a href="#l1145"></a>
<span id="l1146">     *          underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1146"></a>
<span id="l1147">     * @deprecated This method does not properly convert bytes to characters.</span><a href="#l1147"></a>
<span id="l1148">     *          see DataInputStream for the details and alternatives.</span><a href="#l1148"></a>
<span id="l1149">     */</span><a href="#l1149"></a>
<span id="l1150">    @Deprecated</span><a href="#l1150"></a>
<span id="l1151">    public String readLine() throws IOException {</span><a href="#l1151"></a>
<span id="l1152">        return bin.readLine();</span><a href="#l1152"></a>
<span id="l1153">    }</span><a href="#l1153"></a>
<span id="l1154"></span><a href="#l1154"></a>
<span id="l1155">    /**</span><a href="#l1155"></a>
<span id="l1156">     * Reads a String in</span><a href="#l1156"></a>
<span id="l1157">     * &lt;a href=&quot;DataInput.html#modified-utf-8&quot;&gt;modified UTF-8&lt;/a&gt;</span><a href="#l1157"></a>
<span id="l1158">     * format.</span><a href="#l1158"></a>
<span id="l1159">     *</span><a href="#l1159"></a>
<span id="l1160">     * @return  the String.</span><a href="#l1160"></a>
<span id="l1161">     * @throws  IOException if there are I/O errors while reading from the</span><a href="#l1161"></a>
<span id="l1162">     *          underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1162"></a>
<span id="l1163">     * @throws  UTFDataFormatException if read bytes do not represent a valid</span><a href="#l1163"></a>
<span id="l1164">     *          modified UTF-8 encoding of a string</span><a href="#l1164"></a>
<span id="l1165">     */</span><a href="#l1165"></a>
<span id="l1166">    public String readUTF() throws IOException {</span><a href="#l1166"></a>
<span id="l1167">        return bin.readUTF();</span><a href="#l1167"></a>
<span id="l1168">    }</span><a href="#l1168"></a>
<span id="l1169"></span><a href="#l1169"></a>
<span id="l1170">    /**</span><a href="#l1170"></a>
<span id="l1171">     * Returns the serialization filter for this stream.</span><a href="#l1171"></a>
<span id="l1172">     * The serialization filter is the most recent filter set in</span><a href="#l1172"></a>
<span id="l1173">     * {@link #setInternalObjectInputFilter setInternalObjectInputFilter} or</span><a href="#l1173"></a>
<span id="l1174">     * the initial process-wide filter from</span><a href="#l1174"></a>
<span id="l1175">     * {@link ObjectInputFilter.Config#getSerialFilter() ObjectInputFilter.Config.getSerialFilter}.</span><a href="#l1175"></a>
<span id="l1176">     *</span><a href="#l1176"></a>
<span id="l1177">     * @return the serialization filter for the stream; may be null</span><a href="#l1177"></a>
<span id="l1178">     */</span><a href="#l1178"></a>
<span id="l1179">    private final ObjectInputFilter getInternalObjectInputFilter() {</span><a href="#l1179"></a>
<span id="l1180">        return serialFilter;</span><a href="#l1180"></a>
<span id="l1181">    }</span><a href="#l1181"></a>
<span id="l1182"></span><a href="#l1182"></a>
<span id="l1183">    /**</span><a href="#l1183"></a>
<span id="l1184">     * Set the serialization filter for the stream.</span><a href="#l1184"></a>
<span id="l1185">     * The filter's {@link ObjectInputFilter#checkInput checkInput} method is called</span><a href="#l1185"></a>
<span id="l1186">     * for each class and reference in the stream.</span><a href="#l1186"></a>
<span id="l1187">     * The filter can check any or all of the class, the array length, the number</span><a href="#l1187"></a>
<span id="l1188">     * of references, the depth of the graph, and the size of the input stream.</span><a href="#l1188"></a>
<span id="l1189">     * &lt;p&gt;</span><a href="#l1189"></a>
<span id="l1190">     * If the filter returns {@link ObjectInputFilter.Status#REJECTED Status.REJECTED},</span><a href="#l1190"></a>
<span id="l1191">     * {@code null} or throws a {@link RuntimeException},</span><a href="#l1191"></a>
<span id="l1192">     * the active {@code readObject} or {@code readUnshared}</span><a href="#l1192"></a>
<span id="l1193">     * throws {@link InvalidClassException}, otherwise deserialization</span><a href="#l1193"></a>
<span id="l1194">     * continues uninterrupted.</span><a href="#l1194"></a>
<span id="l1195">     * &lt;p&gt;</span><a href="#l1195"></a>
<span id="l1196">     * The serialization filter is initialized to the value of</span><a href="#l1196"></a>
<span id="l1197">     * {@link ObjectInputFilter.Config#getSerialFilter() ObjectInputFilter.Config.getSerialFilter}</span><a href="#l1197"></a>
<span id="l1198">     * when the {@code  ObjectInputStream} is constructed and can be set</span><a href="#l1198"></a>
<span id="l1199">     * to a custom filter only once.</span><a href="#l1199"></a>
<span id="l1200">     *</span><a href="#l1200"></a>
<span id="l1201">     * @implSpec</span><a href="#l1201"></a>
<span id="l1202">     * The filter, when not {@code null}, is invoked during {@link #readObject readObject}</span><a href="#l1202"></a>
<span id="l1203">     * and {@link #readUnshared readUnshared} for each object</span><a href="#l1203"></a>
<span id="l1204">     * (regular or class) in the stream including the following:</span><a href="#l1204"></a>
<span id="l1205">     * &lt;ul&gt;</span><a href="#l1205"></a>
<span id="l1206">     *     &lt;li&gt;each object reference previously deserialized from the stream</span><a href="#l1206"></a>
<span id="l1207">     *     (class is {@code null}, arrayLength is -1),</span><a href="#l1207"></a>
<span id="l1208">     *     &lt;li&gt;each regular class (class is not {@code null}, arrayLength is -1),</span><a href="#l1208"></a>
<span id="l1209">     *     &lt;li&gt;each interface of a dynamic proxy and the dynamic proxy class itself</span><a href="#l1209"></a>
<span id="l1210">     *     (class is not {@code null}, arrayLength is -1),</span><a href="#l1210"></a>
<span id="l1211">     *     &lt;li&gt;each array is filtered using the array type and length of the array</span><a href="#l1211"></a>
<span id="l1212">     *     (class is the array type, arrayLength is the requested length),</span><a href="#l1212"></a>
<span id="l1213">     *     &lt;li&gt;each object replaced by its class' {@code readResolve} method</span><a href="#l1213"></a>
<span id="l1214">     *         is filtered using the replacement object's class, if not {@code null},</span><a href="#l1214"></a>
<span id="l1215">     *         and if it is an array, the arrayLength, otherwise -1,</span><a href="#l1215"></a>
<span id="l1216">     *     &lt;li&gt;and each object replaced by {@link #resolveObject resolveObject}</span><a href="#l1216"></a>
<span id="l1217">     *         is filtered using the replacement object's class, if not {@code null},</span><a href="#l1217"></a>
<span id="l1218">     *         and if it is an array, the arrayLength, otherwise -1.</span><a href="#l1218"></a>
<span id="l1219">     * &lt;/ul&gt;</span><a href="#l1219"></a>
<span id="l1220">     *</span><a href="#l1220"></a>
<span id="l1221">     * When the {@link ObjectInputFilter#checkInput checkInput} method is invoked</span><a href="#l1221"></a>
<span id="l1222">     * it is given access to the current class, the array length,</span><a href="#l1222"></a>
<span id="l1223">     * the current number of references already read from the stream,</span><a href="#l1223"></a>
<span id="l1224">     * the depth of nested calls to {@link #readObject readObject} or</span><a href="#l1224"></a>
<span id="l1225">     * {@link #readUnshared readUnshared},</span><a href="#l1225"></a>
<span id="l1226">     * and the implementation dependent number of bytes consumed from the input stream.</span><a href="#l1226"></a>
<span id="l1227">     * &lt;p&gt;</span><a href="#l1227"></a>
<span id="l1228">     * Each call to {@link #readObject readObject} or</span><a href="#l1228"></a>
<span id="l1229">     * {@link #readUnshared readUnshared} increases the depth by 1</span><a href="#l1229"></a>
<span id="l1230">     * before reading an object and decreases by 1 before returning</span><a href="#l1230"></a>
<span id="l1231">     * normally or exceptionally.</span><a href="#l1231"></a>
<span id="l1232">     * The depth starts at {@code 1} and increases for each nested object and</span><a href="#l1232"></a>
<span id="l1233">     * decrements when each nested call returns.</span><a href="#l1233"></a>
<span id="l1234">     * The count of references in the stream starts at {@code 1} and</span><a href="#l1234"></a>
<span id="l1235">     * is increased before reading an object.</span><a href="#l1235"></a>
<span id="l1236">     *</span><a href="#l1236"></a>
<span id="l1237">     * @param filter the filter, may be null</span><a href="#l1237"></a>
<span id="l1238">     * @throws SecurityException if there is security manager and the</span><a href="#l1238"></a>
<span id="l1239">     *       {@code SerializablePermission(&quot;serialFilter&quot;)} is not granted</span><a href="#l1239"></a>
<span id="l1240">     * @throws IllegalStateException if the {@linkplain #getInternalObjectInputFilter() current filter}</span><a href="#l1240"></a>
<span id="l1241">     *       is not {@code null} and is not the process-wide filter</span><a href="#l1241"></a>
<span id="l1242">     */</span><a href="#l1242"></a>
<span id="l1243">    private final void setInternalObjectInputFilter(ObjectInputFilter filter) {</span><a href="#l1243"></a>
<span id="l1244">        SecurityManager sm = System.getSecurityManager();</span><a href="#l1244"></a>
<span id="l1245">        if (sm != null) {</span><a href="#l1245"></a>
<span id="l1246">            sm.checkPermission(new SerializablePermission(&quot;serialFilter&quot;));</span><a href="#l1246"></a>
<span id="l1247">        }</span><a href="#l1247"></a>
<span id="l1248">        // Allow replacement of the process-wide filter if not already set</span><a href="#l1248"></a>
<span id="l1249">        if (serialFilter != null &amp;&amp;</span><a href="#l1249"></a>
<span id="l1250">                serialFilter != ObjectInputFilter.Config.getSerialFilter()) {</span><a href="#l1250"></a>
<span id="l1251">            throw new IllegalStateException(&quot;filter can not be set more than once&quot;);</span><a href="#l1251"></a>
<span id="l1252">        }</span><a href="#l1252"></a>
<span id="l1253">        this.serialFilter = filter;</span><a href="#l1253"></a>
<span id="l1254">    }</span><a href="#l1254"></a>
<span id="l1255"></span><a href="#l1255"></a>
<span id="l1256">    /**</span><a href="#l1256"></a>
<span id="l1257">     * Invoke the serialization filter if non-null.</span><a href="#l1257"></a>
<span id="l1258">     * If the filter rejects or an exception is thrown, throws InvalidClassException.</span><a href="#l1258"></a>
<span id="l1259">     *</span><a href="#l1259"></a>
<span id="l1260">     * @param clazz the class; may be null</span><a href="#l1260"></a>
<span id="l1261">     * @param arrayLength the array length requested; use {@code -1} if not creating an array</span><a href="#l1261"></a>
<span id="l1262">     * @throws InvalidClassException if it rejected by the filter or</span><a href="#l1262"></a>
<span id="l1263">     *        a {@link RuntimeException} is thrown</span><a href="#l1263"></a>
<span id="l1264">     */</span><a href="#l1264"></a>
<span id="l1265">    private void filterCheck(Class&lt;?&gt; clazz, int arrayLength)</span><a href="#l1265"></a>
<span id="l1266">            throws InvalidClassException {</span><a href="#l1266"></a>
<span id="l1267">        if (serialFilter != null) {</span><a href="#l1267"></a>
<span id="l1268">            RuntimeException ex = null;</span><a href="#l1268"></a>
<span id="l1269">            ObjectInputFilter.Status status;</span><a href="#l1269"></a>
<span id="l1270">            // Info about the stream is not available if overridden by subclass, return 0</span><a href="#l1270"></a>
<span id="l1271">            long bytesRead = (bin == null) ? 0 : bin.getBytesRead();</span><a href="#l1271"></a>
<span id="l1272">            try {</span><a href="#l1272"></a>
<span id="l1273">                status = serialFilter.checkInput(new FilterValues(clazz, arrayLength,</span><a href="#l1273"></a>
<span id="l1274">                        totalObjectRefs, depth, bytesRead));</span><a href="#l1274"></a>
<span id="l1275">            } catch (RuntimeException e) {</span><a href="#l1275"></a>
<span id="l1276">                // Preventive interception of an exception to log</span><a href="#l1276"></a>
<span id="l1277">                status = ObjectInputFilter.Status.REJECTED;</span><a href="#l1277"></a>
<span id="l1278">                ex = e;</span><a href="#l1278"></a>
<span id="l1279">            }</span><a href="#l1279"></a>
<span id="l1280">            if (status == null  ||</span><a href="#l1280"></a>
<span id="l1281">                    status == ObjectInputFilter.Status.REJECTED) {</span><a href="#l1281"></a>
<span id="l1282">                // Debug logging of filter checks that fail</span><a href="#l1282"></a>
<span id="l1283">                if (Logging.infoLogger != null) {</span><a href="#l1283"></a>
<span id="l1284">                    Logging.infoLogger.info(</span><a href="#l1284"></a>
<span id="l1285">                            &quot;ObjectInputFilter {0}: {1}, array length: {2}, nRefs: {3}, depth: {4}, bytes: {5}, ex: {6}&quot;,</span><a href="#l1285"></a>
<span id="l1286">                            status, clazz, arrayLength, totalObjectRefs, depth, bytesRead,</span><a href="#l1286"></a>
<span id="l1287">                            Objects.toString(ex, &quot;n/a&quot;));</span><a href="#l1287"></a>
<span id="l1288">                }</span><a href="#l1288"></a>
<span id="l1289">                InvalidClassException ice = new InvalidClassException(&quot;filter status: &quot; + status);</span><a href="#l1289"></a>
<span id="l1290">                ice.initCause(ex);</span><a href="#l1290"></a>
<span id="l1291">                throw ice;</span><a href="#l1291"></a>
<span id="l1292">            } else {</span><a href="#l1292"></a>
<span id="l1293">                // Trace logging for those that succeed</span><a href="#l1293"></a>
<span id="l1294">                if (Logging.traceLogger != null) {</span><a href="#l1294"></a>
<span id="l1295">                    Logging.traceLogger.finer(</span><a href="#l1295"></a>
<span id="l1296">                            &quot;ObjectInputFilter {0}: {1}, array length: {2}, nRefs: {3}, depth: {4}, bytes: {5}, ex: {6}&quot;,</span><a href="#l1296"></a>
<span id="l1297">                            status, clazz, arrayLength, totalObjectRefs, depth, bytesRead,</span><a href="#l1297"></a>
<span id="l1298">                            Objects.toString(ex, &quot;n/a&quot;));</span><a href="#l1298"></a>
<span id="l1299">                }</span><a href="#l1299"></a>
<span id="l1300">            }</span><a href="#l1300"></a>
<span id="l1301">        }</span><a href="#l1301"></a>
<span id="l1302">    }</span><a href="#l1302"></a>
<span id="l1303"></span><a href="#l1303"></a>
<span id="l1304">    /**</span><a href="#l1304"></a>
<span id="l1305">     * Checks the given array type and length to ensure that creation of such</span><a href="#l1305"></a>
<span id="l1306">     * an array is permitted by this ObjectInputStream. The arrayType argument</span><a href="#l1306"></a>
<span id="l1307">     * must represent an actual array type.</span><a href="#l1307"></a>
<span id="l1308">     *</span><a href="#l1308"></a>
<span id="l1309">     * This private method is called via SharedSecrets.</span><a href="#l1309"></a>
<span id="l1310">     *</span><a href="#l1310"></a>
<span id="l1311">     * @param arrayType the array type</span><a href="#l1311"></a>
<span id="l1312">     * @param arrayLength the array length</span><a href="#l1312"></a>
<span id="l1313">     * @throws NullPointerException if arrayType is null</span><a href="#l1313"></a>
<span id="l1314">     * @throws IllegalArgumentException if arrayType isn't actually an array type</span><a href="#l1314"></a>
<span id="l1315">     * @throws NegativeArraySizeException if arrayLength is negative</span><a href="#l1315"></a>
<span id="l1316">     * @throws InvalidClassException if the filter rejects creation</span><a href="#l1316"></a>
<span id="l1317">     */</span><a href="#l1317"></a>
<span id="l1318">    private void checkArray(Class&lt;?&gt; arrayType, int arrayLength) throws InvalidClassException {</span><a href="#l1318"></a>
<span id="l1319">        Objects.requireNonNull(arrayType);</span><a href="#l1319"></a>
<span id="l1320">        if (! arrayType.isArray()) {</span><a href="#l1320"></a>
<span id="l1321">            throw new IllegalArgumentException(&quot;not an array type&quot;);</span><a href="#l1321"></a>
<span id="l1322">        }</span><a href="#l1322"></a>
<span id="l1323"></span><a href="#l1323"></a>
<span id="l1324">        if (arrayLength &lt; 0) {</span><a href="#l1324"></a>
<span id="l1325">            throw new NegativeArraySizeException();</span><a href="#l1325"></a>
<span id="l1326">        }</span><a href="#l1326"></a>
<span id="l1327"></span><a href="#l1327"></a>
<span id="l1328">        filterCheck(arrayType, arrayLength);</span><a href="#l1328"></a>
<span id="l1329">    }</span><a href="#l1329"></a>
<span id="l1330"></span><a href="#l1330"></a>
<span id="l1331">    /**</span><a href="#l1331"></a>
<span id="l1332">     * Provide access to the persistent fields read from the input stream.</span><a href="#l1332"></a>
<span id="l1333">     */</span><a href="#l1333"></a>
<span id="l1334">    public static abstract class GetField {</span><a href="#l1334"></a>
<span id="l1335"></span><a href="#l1335"></a>
<span id="l1336">        /**</span><a href="#l1336"></a>
<span id="l1337">         * Get the ObjectStreamClass that describes the fields in the stream.</span><a href="#l1337"></a>
<span id="l1338">         *</span><a href="#l1338"></a>
<span id="l1339">         * @return  the descriptor class that describes the serializable fields</span><a href="#l1339"></a>
<span id="l1340">         */</span><a href="#l1340"></a>
<span id="l1341">        public abstract ObjectStreamClass getObjectStreamClass();</span><a href="#l1341"></a>
<span id="l1342"></span><a href="#l1342"></a>
<span id="l1343">        /**</span><a href="#l1343"></a>
<span id="l1344">         * Return true if the named field is defaulted and has no value in this</span><a href="#l1344"></a>
<span id="l1345">         * stream.</span><a href="#l1345"></a>
<span id="l1346">         *</span><a href="#l1346"></a>
<span id="l1347">         * @param  name the name of the field</span><a href="#l1347"></a>
<span id="l1348">         * @return true, if and only if the named field is defaulted</span><a href="#l1348"></a>
<span id="l1349">         * @throws IOException if there are I/O errors while reading from</span><a href="#l1349"></a>
<span id="l1350">         *         the underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1350"></a>
<span id="l1351">         * @throws IllegalArgumentException if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1351"></a>
<span id="l1352">         *         correspond to a serializable field</span><a href="#l1352"></a>
<span id="l1353">         */</span><a href="#l1353"></a>
<span id="l1354">        public abstract boolean defaulted(String name) throws IOException;</span><a href="#l1354"></a>
<span id="l1355"></span><a href="#l1355"></a>
<span id="l1356">        /**</span><a href="#l1356"></a>
<span id="l1357">         * Get the value of the named boolean field from the persistent field.</span><a href="#l1357"></a>
<span id="l1358">         *</span><a href="#l1358"></a>
<span id="l1359">         * @param  name the name of the field</span><a href="#l1359"></a>
<span id="l1360">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1360"></a>
<span id="l1361">         *         have a value</span><a href="#l1361"></a>
<span id="l1362">         * @return the value of the named &lt;code&gt;boolean&lt;/code&gt; field</span><a href="#l1362"></a>
<span id="l1363">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1363"></a>
<span id="l1364">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1364"></a>
<span id="l1365">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1365"></a>
<span id="l1366">         *         not serializable or if the field type is incorrect</span><a href="#l1366"></a>
<span id="l1367">         */</span><a href="#l1367"></a>
<span id="l1368">        public abstract boolean get(String name, boolean val)</span><a href="#l1368"></a>
<span id="l1369">            throws IOException;</span><a href="#l1369"></a>
<span id="l1370"></span><a href="#l1370"></a>
<span id="l1371">        /**</span><a href="#l1371"></a>
<span id="l1372">         * Get the value of the named byte field from the persistent field.</span><a href="#l1372"></a>
<span id="l1373">         *</span><a href="#l1373"></a>
<span id="l1374">         * @param  name the name of the field</span><a href="#l1374"></a>
<span id="l1375">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1375"></a>
<span id="l1376">         *         have a value</span><a href="#l1376"></a>
<span id="l1377">         * @return the value of the named &lt;code&gt;byte&lt;/code&gt; field</span><a href="#l1377"></a>
<span id="l1378">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1378"></a>
<span id="l1379">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1379"></a>
<span id="l1380">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1380"></a>
<span id="l1381">         *         not serializable or if the field type is incorrect</span><a href="#l1381"></a>
<span id="l1382">         */</span><a href="#l1382"></a>
<span id="l1383">        public abstract byte get(String name, byte val) throws IOException;</span><a href="#l1383"></a>
<span id="l1384"></span><a href="#l1384"></a>
<span id="l1385">        /**</span><a href="#l1385"></a>
<span id="l1386">         * Get the value of the named char field from the persistent field.</span><a href="#l1386"></a>
<span id="l1387">         *</span><a href="#l1387"></a>
<span id="l1388">         * @param  name the name of the field</span><a href="#l1388"></a>
<span id="l1389">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1389"></a>
<span id="l1390">         *         have a value</span><a href="#l1390"></a>
<span id="l1391">         * @return the value of the named &lt;code&gt;char&lt;/code&gt; field</span><a href="#l1391"></a>
<span id="l1392">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1392"></a>
<span id="l1393">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1393"></a>
<span id="l1394">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1394"></a>
<span id="l1395">         *         not serializable or if the field type is incorrect</span><a href="#l1395"></a>
<span id="l1396">         */</span><a href="#l1396"></a>
<span id="l1397">        public abstract char get(String name, char val) throws IOException;</span><a href="#l1397"></a>
<span id="l1398"></span><a href="#l1398"></a>
<span id="l1399">        /**</span><a href="#l1399"></a>
<span id="l1400">         * Get the value of the named short field from the persistent field.</span><a href="#l1400"></a>
<span id="l1401">         *</span><a href="#l1401"></a>
<span id="l1402">         * @param  name the name of the field</span><a href="#l1402"></a>
<span id="l1403">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1403"></a>
<span id="l1404">         *         have a value</span><a href="#l1404"></a>
<span id="l1405">         * @return the value of the named &lt;code&gt;short&lt;/code&gt; field</span><a href="#l1405"></a>
<span id="l1406">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1406"></a>
<span id="l1407">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1407"></a>
<span id="l1408">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1408"></a>
<span id="l1409">         *         not serializable or if the field type is incorrect</span><a href="#l1409"></a>
<span id="l1410">         */</span><a href="#l1410"></a>
<span id="l1411">        public abstract short get(String name, short val) throws IOException;</span><a href="#l1411"></a>
<span id="l1412"></span><a href="#l1412"></a>
<span id="l1413">        /**</span><a href="#l1413"></a>
<span id="l1414">         * Get the value of the named int field from the persistent field.</span><a href="#l1414"></a>
<span id="l1415">         *</span><a href="#l1415"></a>
<span id="l1416">         * @param  name the name of the field</span><a href="#l1416"></a>
<span id="l1417">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1417"></a>
<span id="l1418">         *         have a value</span><a href="#l1418"></a>
<span id="l1419">         * @return the value of the named &lt;code&gt;int&lt;/code&gt; field</span><a href="#l1419"></a>
<span id="l1420">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1420"></a>
<span id="l1421">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1421"></a>
<span id="l1422">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1422"></a>
<span id="l1423">         *         not serializable or if the field type is incorrect</span><a href="#l1423"></a>
<span id="l1424">         */</span><a href="#l1424"></a>
<span id="l1425">        public abstract int get(String name, int val) throws IOException;</span><a href="#l1425"></a>
<span id="l1426"></span><a href="#l1426"></a>
<span id="l1427">        /**</span><a href="#l1427"></a>
<span id="l1428">         * Get the value of the named long field from the persistent field.</span><a href="#l1428"></a>
<span id="l1429">         *</span><a href="#l1429"></a>
<span id="l1430">         * @param  name the name of the field</span><a href="#l1430"></a>
<span id="l1431">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1431"></a>
<span id="l1432">         *         have a value</span><a href="#l1432"></a>
<span id="l1433">         * @return the value of the named &lt;code&gt;long&lt;/code&gt; field</span><a href="#l1433"></a>
<span id="l1434">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1434"></a>
<span id="l1435">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1435"></a>
<span id="l1436">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1436"></a>
<span id="l1437">         *         not serializable or if the field type is incorrect</span><a href="#l1437"></a>
<span id="l1438">         */</span><a href="#l1438"></a>
<span id="l1439">        public abstract long get(String name, long val) throws IOException;</span><a href="#l1439"></a>
<span id="l1440"></span><a href="#l1440"></a>
<span id="l1441">        /**</span><a href="#l1441"></a>
<span id="l1442">         * Get the value of the named float field from the persistent field.</span><a href="#l1442"></a>
<span id="l1443">         *</span><a href="#l1443"></a>
<span id="l1444">         * @param  name the name of the field</span><a href="#l1444"></a>
<span id="l1445">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1445"></a>
<span id="l1446">         *         have a value</span><a href="#l1446"></a>
<span id="l1447">         * @return the value of the named &lt;code&gt;float&lt;/code&gt; field</span><a href="#l1447"></a>
<span id="l1448">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1448"></a>
<span id="l1449">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1449"></a>
<span id="l1450">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1450"></a>
<span id="l1451">         *         not serializable or if the field type is incorrect</span><a href="#l1451"></a>
<span id="l1452">         */</span><a href="#l1452"></a>
<span id="l1453">        public abstract float get(String name, float val) throws IOException;</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">        /**</span><a href="#l1455"></a>
<span id="l1456">         * Get the value of the named double field from the persistent field.</span><a href="#l1456"></a>
<span id="l1457">         *</span><a href="#l1457"></a>
<span id="l1458">         * @param  name the name of the field</span><a href="#l1458"></a>
<span id="l1459">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1459"></a>
<span id="l1460">         *         have a value</span><a href="#l1460"></a>
<span id="l1461">         * @return the value of the named &lt;code&gt;double&lt;/code&gt; field</span><a href="#l1461"></a>
<span id="l1462">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1462"></a>
<span id="l1463">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1463"></a>
<span id="l1464">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1464"></a>
<span id="l1465">         *         not serializable or if the field type is incorrect</span><a href="#l1465"></a>
<span id="l1466">         */</span><a href="#l1466"></a>
<span id="l1467">        public abstract double get(String name, double val) throws IOException;</span><a href="#l1467"></a>
<span id="l1468"></span><a href="#l1468"></a>
<span id="l1469">        /**</span><a href="#l1469"></a>
<span id="l1470">         * Get the value of the named Object field from the persistent field.</span><a href="#l1470"></a>
<span id="l1471">         *</span><a href="#l1471"></a>
<span id="l1472">         * @param  name the name of the field</span><a href="#l1472"></a>
<span id="l1473">         * @param  val the default value to use if &lt;code&gt;name&lt;/code&gt; does not</span><a href="#l1473"></a>
<span id="l1474">         *         have a value</span><a href="#l1474"></a>
<span id="l1475">         * @return the value of the named &lt;code&gt;Object&lt;/code&gt; field</span><a href="#l1475"></a>
<span id="l1476">         * @throws IOException if there are I/O errors while reading from the</span><a href="#l1476"></a>
<span id="l1477">         *         underlying &lt;code&gt;InputStream&lt;/code&gt;</span><a href="#l1477"></a>
<span id="l1478">         * @throws IllegalArgumentException if type of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l1478"></a>
<span id="l1479">         *         not serializable or if the field type is incorrect</span><a href="#l1479"></a>
<span id="l1480">         */</span><a href="#l1480"></a>
<span id="l1481">        public abstract Object get(String name, Object val) throws IOException;</span><a href="#l1481"></a>
<span id="l1482">    }</span><a href="#l1482"></a>
<span id="l1483"></span><a href="#l1483"></a>
<span id="l1484">    /**</span><a href="#l1484"></a>
<span id="l1485">     * Verifies that this (possibly subclass) instance can be constructed</span><a href="#l1485"></a>
<span id="l1486">     * without violating security constraints: the subclass must not override</span><a href="#l1486"></a>
<span id="l1487">     * security-sensitive non-final methods, or else the</span><a href="#l1487"></a>
<span id="l1488">     * &quot;enableSubclassImplementation&quot; SerializablePermission is checked.</span><a href="#l1488"></a>
<span id="l1489">     */</span><a href="#l1489"></a>
<span id="l1490">    private void verifySubclass() {</span><a href="#l1490"></a>
<span id="l1491">        Class&lt;?&gt; cl = getClass();</span><a href="#l1491"></a>
<span id="l1492">        if (cl == ObjectInputStream.class) {</span><a href="#l1492"></a>
<span id="l1493">            return;</span><a href="#l1493"></a>
<span id="l1494">        }</span><a href="#l1494"></a>
<span id="l1495">        SecurityManager sm = System.getSecurityManager();</span><a href="#l1495"></a>
<span id="l1496">        if (sm == null) {</span><a href="#l1496"></a>
<span id="l1497">            return;</span><a href="#l1497"></a>
<span id="l1498">        }</span><a href="#l1498"></a>
<span id="l1499">        processQueue(Caches.subclassAuditsQueue, Caches.subclassAudits);</span><a href="#l1499"></a>
<span id="l1500">        WeakClassKey key = new WeakClassKey(cl, Caches.subclassAuditsQueue);</span><a href="#l1500"></a>
<span id="l1501">        Boolean result = Caches.subclassAudits.get(key);</span><a href="#l1501"></a>
<span id="l1502">        if (result == null) {</span><a href="#l1502"></a>
<span id="l1503">            result = Boolean.valueOf(auditSubclass(cl));</span><a href="#l1503"></a>
<span id="l1504">            Caches.subclassAudits.putIfAbsent(key, result);</span><a href="#l1504"></a>
<span id="l1505">        }</span><a href="#l1505"></a>
<span id="l1506">        if (result.booleanValue()) {</span><a href="#l1506"></a>
<span id="l1507">            return;</span><a href="#l1507"></a>
<span id="l1508">        }</span><a href="#l1508"></a>
<span id="l1509">        sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);</span><a href="#l1509"></a>
<span id="l1510">    }</span><a href="#l1510"></a>
<span id="l1511"></span><a href="#l1511"></a>
<span id="l1512">    /**</span><a href="#l1512"></a>
<span id="l1513">     * Performs reflective checks on given subclass to verify that it doesn't</span><a href="#l1513"></a>
<span id="l1514">     * override security-sensitive non-final methods.  Returns true if subclass</span><a href="#l1514"></a>
<span id="l1515">     * is &quot;safe&quot;, false otherwise.</span><a href="#l1515"></a>
<span id="l1516">     */</span><a href="#l1516"></a>
<span id="l1517">    private static boolean auditSubclass(final Class&lt;?&gt; subcl) {</span><a href="#l1517"></a>
<span id="l1518">        Boolean result = AccessController.doPrivileged(</span><a href="#l1518"></a>
<span id="l1519">            new PrivilegedAction&lt;Boolean&gt;() {</span><a href="#l1519"></a>
<span id="l1520">                public Boolean run() {</span><a href="#l1520"></a>
<span id="l1521">                    for (Class&lt;?&gt; cl = subcl;</span><a href="#l1521"></a>
<span id="l1522">                         cl != ObjectInputStream.class;</span><a href="#l1522"></a>
<span id="l1523">                         cl = cl.getSuperclass())</span><a href="#l1523"></a>
<span id="l1524">                    {</span><a href="#l1524"></a>
<span id="l1525">                        try {</span><a href="#l1525"></a>
<span id="l1526">                            cl.getDeclaredMethod(</span><a href="#l1526"></a>
<span id="l1527">                                &quot;readUnshared&quot;, (Class[]) null);</span><a href="#l1527"></a>
<span id="l1528">                            return Boolean.FALSE;</span><a href="#l1528"></a>
<span id="l1529">                        } catch (NoSuchMethodException ex) {</span><a href="#l1529"></a>
<span id="l1530">                        }</span><a href="#l1530"></a>
<span id="l1531">                        try {</span><a href="#l1531"></a>
<span id="l1532">                            cl.getDeclaredMethod(&quot;readFields&quot;, (Class[]) null);</span><a href="#l1532"></a>
<span id="l1533">                            return Boolean.FALSE;</span><a href="#l1533"></a>
<span id="l1534">                        } catch (NoSuchMethodException ex) {</span><a href="#l1534"></a>
<span id="l1535">                        }</span><a href="#l1535"></a>
<span id="l1536">                    }</span><a href="#l1536"></a>
<span id="l1537">                    return Boolean.TRUE;</span><a href="#l1537"></a>
<span id="l1538">                }</span><a href="#l1538"></a>
<span id="l1539">            }</span><a href="#l1539"></a>
<span id="l1540">        );</span><a href="#l1540"></a>
<span id="l1541">        return result.booleanValue();</span><a href="#l1541"></a>
<span id="l1542">    }</span><a href="#l1542"></a>
<span id="l1543"></span><a href="#l1543"></a>
<span id="l1544">    /**</span><a href="#l1544"></a>
<span id="l1545">     * Clears internal data structures.</span><a href="#l1545"></a>
<span id="l1546">     */</span><a href="#l1546"></a>
<span id="l1547">    private void clear() {</span><a href="#l1547"></a>
<span id="l1548">        handles.clear();</span><a href="#l1548"></a>
<span id="l1549">        vlist.clear();</span><a href="#l1549"></a>
<span id="l1550">    }</span><a href="#l1550"></a>
<span id="l1551"></span><a href="#l1551"></a>
<span id="l1552">    /**</span><a href="#l1552"></a>
<span id="l1553">     * Underlying readObject implementation.</span><a href="#l1553"></a>
<span id="l1554">     * @param type a type expected to be deserialized; non-null</span><a href="#l1554"></a>
<span id="l1555">     * @param unshared true if the object can not be a reference to a shared object, otherwise false</span><a href="#l1555"></a>
<span id="l1556">     */</span><a href="#l1556"></a>
<span id="l1557">    private Object readObject0(Class&lt;?&gt; type, boolean unshared) throws IOException {</span><a href="#l1557"></a>
<span id="l1558">        boolean oldMode = bin.getBlockDataMode();</span><a href="#l1558"></a>
<span id="l1559">        if (oldMode) {</span><a href="#l1559"></a>
<span id="l1560">            int remain = bin.currentBlockRemaining();</span><a href="#l1560"></a>
<span id="l1561">            if (remain &gt; 0) {</span><a href="#l1561"></a>
<span id="l1562">                throw new OptionalDataException(remain);</span><a href="#l1562"></a>
<span id="l1563">            } else if (defaultDataEnd) {</span><a href="#l1563"></a>
<span id="l1564">                /*</span><a href="#l1564"></a>
<span id="l1565">                 * Fix for 4360508: stream is currently at the end of a field</span><a href="#l1565"></a>
<span id="l1566">                 * value block written via default serialization; since there</span><a href="#l1566"></a>
<span id="l1567">                 * is no terminating TC_ENDBLOCKDATA tag, simulate</span><a href="#l1567"></a>
<span id="l1568">                 * end-of-custom-data behavior explicitly.</span><a href="#l1568"></a>
<span id="l1569">                 */</span><a href="#l1569"></a>
<span id="l1570">                throw new OptionalDataException(true);</span><a href="#l1570"></a>
<span id="l1571">            }</span><a href="#l1571"></a>
<span id="l1572">            bin.setBlockDataMode(false);</span><a href="#l1572"></a>
<span id="l1573">        }</span><a href="#l1573"></a>
<span id="l1574"></span><a href="#l1574"></a>
<span id="l1575">        byte tc;</span><a href="#l1575"></a>
<span id="l1576">        while ((tc = bin.peekByte()) == TC_RESET) {</span><a href="#l1576"></a>
<span id="l1577">            bin.readByte();</span><a href="#l1577"></a>
<span id="l1578">            handleReset();</span><a href="#l1578"></a>
<span id="l1579">        }</span><a href="#l1579"></a>
<span id="l1580"></span><a href="#l1580"></a>
<span id="l1581">        depth++;</span><a href="#l1581"></a>
<span id="l1582">        totalObjectRefs++;</span><a href="#l1582"></a>
<span id="l1583">        try {</span><a href="#l1583"></a>
<span id="l1584">            switch (tc) {</span><a href="#l1584"></a>
<span id="l1585">                case TC_NULL:</span><a href="#l1585"></a>
<span id="l1586">                    return readNull();</span><a href="#l1586"></a>
<span id="l1587"></span><a href="#l1587"></a>
<span id="l1588">                case TC_REFERENCE:</span><a href="#l1588"></a>
<span id="l1589">                    // check the type of the existing object</span><a href="#l1589"></a>
<span id="l1590">                    return type.cast(readHandle(unshared));</span><a href="#l1590"></a>
<span id="l1591"></span><a href="#l1591"></a>
<span id="l1592">                case TC_CLASS:</span><a href="#l1592"></a>
<span id="l1593">                    if (type == String.class) {</span><a href="#l1593"></a>
<span id="l1594">                        throw new ClassCastException(&quot;Cannot cast a class to java.lang.String&quot;);</span><a href="#l1594"></a>
<span id="l1595">                    }</span><a href="#l1595"></a>
<span id="l1596">                    return readClass(unshared);</span><a href="#l1596"></a>
<span id="l1597"></span><a href="#l1597"></a>
<span id="l1598">                case TC_CLASSDESC:</span><a href="#l1598"></a>
<span id="l1599">                case TC_PROXYCLASSDESC:</span><a href="#l1599"></a>
<span id="l1600">                    if (type == String.class) {</span><a href="#l1600"></a>
<span id="l1601">                        throw new ClassCastException(&quot;Cannot cast a class to java.lang.String&quot;);</span><a href="#l1601"></a>
<span id="l1602">                    }</span><a href="#l1602"></a>
<span id="l1603">                    return readClassDesc(unshared);</span><a href="#l1603"></a>
<span id="l1604"></span><a href="#l1604"></a>
<span id="l1605">                case TC_STRING:</span><a href="#l1605"></a>
<span id="l1606">                case TC_LONGSTRING:</span><a href="#l1606"></a>
<span id="l1607">                    return checkResolve(readString(unshared));</span><a href="#l1607"></a>
<span id="l1608"></span><a href="#l1608"></a>
<span id="l1609">                case TC_ARRAY:</span><a href="#l1609"></a>
<span id="l1610">                    if (type == String.class) {</span><a href="#l1610"></a>
<span id="l1611">                        throw new ClassCastException(&quot;Cannot cast an array to java.lang.String&quot;);</span><a href="#l1611"></a>
<span id="l1612">                    }</span><a href="#l1612"></a>
<span id="l1613">                    return checkResolve(readArray(unshared));</span><a href="#l1613"></a>
<span id="l1614"></span><a href="#l1614"></a>
<span id="l1615">                case TC_ENUM:</span><a href="#l1615"></a>
<span id="l1616">                    if (type == String.class) {</span><a href="#l1616"></a>
<span id="l1617">                        throw new ClassCastException(&quot;Cannot cast an enum to java.lang.String&quot;);</span><a href="#l1617"></a>
<span id="l1618">                    }</span><a href="#l1618"></a>
<span id="l1619">                    return checkResolve(readEnum(unshared));</span><a href="#l1619"></a>
<span id="l1620"></span><a href="#l1620"></a>
<span id="l1621">                case TC_OBJECT:</span><a href="#l1621"></a>
<span id="l1622">                    if (type == String.class) {</span><a href="#l1622"></a>
<span id="l1623">                        throw new ClassCastException(&quot;Cannot cast an object to java.lang.String&quot;);</span><a href="#l1623"></a>
<span id="l1624">                    }</span><a href="#l1624"></a>
<span id="l1625">                    return checkResolve(readOrdinaryObject(unshared));</span><a href="#l1625"></a>
<span id="l1626"></span><a href="#l1626"></a>
<span id="l1627">                case TC_EXCEPTION:</span><a href="#l1627"></a>
<span id="l1628">                    if (type == String.class) {</span><a href="#l1628"></a>
<span id="l1629">                        throw new ClassCastException(&quot;Cannot cast an exception to java.lang.String&quot;);</span><a href="#l1629"></a>
<span id="l1630">                    }</span><a href="#l1630"></a>
<span id="l1631">                    IOException ex = readFatalException();</span><a href="#l1631"></a>
<span id="l1632">                    throw new WriteAbortedException(&quot;writing aborted&quot;, ex);</span><a href="#l1632"></a>
<span id="l1633"></span><a href="#l1633"></a>
<span id="l1634">                case TC_BLOCKDATA:</span><a href="#l1634"></a>
<span id="l1635">                case TC_BLOCKDATALONG:</span><a href="#l1635"></a>
<span id="l1636">                    if (oldMode) {</span><a href="#l1636"></a>
<span id="l1637">                        bin.setBlockDataMode(true);</span><a href="#l1637"></a>
<span id="l1638">                        bin.peek();             // force header read</span><a href="#l1638"></a>
<span id="l1639">                        throw new OptionalDataException(</span><a href="#l1639"></a>
<span id="l1640">                            bin.currentBlockRemaining());</span><a href="#l1640"></a>
<span id="l1641">                    } else {</span><a href="#l1641"></a>
<span id="l1642">                        throw new StreamCorruptedException(</span><a href="#l1642"></a>
<span id="l1643">                            &quot;unexpected block data&quot;);</span><a href="#l1643"></a>
<span id="l1644">                    }</span><a href="#l1644"></a>
<span id="l1645"></span><a href="#l1645"></a>
<span id="l1646">                case TC_ENDBLOCKDATA:</span><a href="#l1646"></a>
<span id="l1647">                    if (oldMode) {</span><a href="#l1647"></a>
<span id="l1648">                        throw new OptionalDataException(true);</span><a href="#l1648"></a>
<span id="l1649">                    } else {</span><a href="#l1649"></a>
<span id="l1650">                        throw new StreamCorruptedException(</span><a href="#l1650"></a>
<span id="l1651">                            &quot;unexpected end of block data&quot;);</span><a href="#l1651"></a>
<span id="l1652">                    }</span><a href="#l1652"></a>
<span id="l1653"></span><a href="#l1653"></a>
<span id="l1654">                default:</span><a href="#l1654"></a>
<span id="l1655">                    throw new StreamCorruptedException(</span><a href="#l1655"></a>
<span id="l1656">                        String.format(&quot;invalid type code: %02X&quot;, tc));</span><a href="#l1656"></a>
<span id="l1657">            }</span><a href="#l1657"></a>
<span id="l1658">        } finally {</span><a href="#l1658"></a>
<span id="l1659">            depth--;</span><a href="#l1659"></a>
<span id="l1660">            bin.setBlockDataMode(oldMode);</span><a href="#l1660"></a>
<span id="l1661">        }</span><a href="#l1661"></a>
<span id="l1662">    }</span><a href="#l1662"></a>
<span id="l1663"></span><a href="#l1663"></a>
<span id="l1664">    /**</span><a href="#l1664"></a>
<span id="l1665">     * If resolveObject has been enabled and given object does not have an</span><a href="#l1665"></a>
<span id="l1666">     * exception associated with it, calls resolveObject to determine</span><a href="#l1666"></a>
<span id="l1667">     * replacement for object, and updates handle table accordingly.  Returns</span><a href="#l1667"></a>
<span id="l1668">     * replacement object, or echoes provided object if no replacement</span><a href="#l1668"></a>
<span id="l1669">     * occurred.  Expects that passHandle is set to given object's handle prior</span><a href="#l1669"></a>
<span id="l1670">     * to calling this method.</span><a href="#l1670"></a>
<span id="l1671">     */</span><a href="#l1671"></a>
<span id="l1672">    private Object checkResolve(Object obj) throws IOException {</span><a href="#l1672"></a>
<span id="l1673">        if (!enableResolve || handles.lookupException(passHandle) != null) {</span><a href="#l1673"></a>
<span id="l1674">            return obj;</span><a href="#l1674"></a>
<span id="l1675">        }</span><a href="#l1675"></a>
<span id="l1676">        Object rep = resolveObject(obj);</span><a href="#l1676"></a>
<span id="l1677">        if (rep != obj) {</span><a href="#l1677"></a>
<span id="l1678">            // The type of the original object has been filtered but resolveObject</span><a href="#l1678"></a>
<span id="l1679">            // may have replaced it;  filter the replacement's type</span><a href="#l1679"></a>
<span id="l1680">            if (rep != null) {</span><a href="#l1680"></a>
<span id="l1681">                if (rep.getClass().isArray()) {</span><a href="#l1681"></a>
<span id="l1682">                    filterCheck(rep.getClass(), Array.getLength(rep));</span><a href="#l1682"></a>
<span id="l1683">                } else {</span><a href="#l1683"></a>
<span id="l1684">                    filterCheck(rep.getClass(), -1);</span><a href="#l1684"></a>
<span id="l1685">                }</span><a href="#l1685"></a>
<span id="l1686">            }</span><a href="#l1686"></a>
<span id="l1687">            handles.setObject(passHandle, rep);</span><a href="#l1687"></a>
<span id="l1688">        }</span><a href="#l1688"></a>
<span id="l1689">        return rep;</span><a href="#l1689"></a>
<span id="l1690">    }</span><a href="#l1690"></a>
<span id="l1691"></span><a href="#l1691"></a>
<span id="l1692">    /**</span><a href="#l1692"></a>
<span id="l1693">     * Reads string without allowing it to be replaced in stream.  Called from</span><a href="#l1693"></a>
<span id="l1694">     * within ObjectStreamClass.read().</span><a href="#l1694"></a>
<span id="l1695">     */</span><a href="#l1695"></a>
<span id="l1696">    String readTypeString() throws IOException {</span><a href="#l1696"></a>
<span id="l1697">        int oldHandle = passHandle;</span><a href="#l1697"></a>
<span id="l1698">        try {</span><a href="#l1698"></a>
<span id="l1699">            byte tc = bin.peekByte();</span><a href="#l1699"></a>
<span id="l1700">            switch (tc) {</span><a href="#l1700"></a>
<span id="l1701">                case TC_NULL:</span><a href="#l1701"></a>
<span id="l1702">                    return (String) readNull();</span><a href="#l1702"></a>
<span id="l1703"></span><a href="#l1703"></a>
<span id="l1704">                case TC_REFERENCE:</span><a href="#l1704"></a>
<span id="l1705">                    return (String) readHandle(false);</span><a href="#l1705"></a>
<span id="l1706"></span><a href="#l1706"></a>
<span id="l1707">                case TC_STRING:</span><a href="#l1707"></a>
<span id="l1708">                case TC_LONGSTRING:</span><a href="#l1708"></a>
<span id="l1709">                    return readString(false);</span><a href="#l1709"></a>
<span id="l1710"></span><a href="#l1710"></a>
<span id="l1711">                default:</span><a href="#l1711"></a>
<span id="l1712">                    throw new StreamCorruptedException(</span><a href="#l1712"></a>
<span id="l1713">                        String.format(&quot;invalid type code: %02X&quot;, tc));</span><a href="#l1713"></a>
<span id="l1714">            }</span><a href="#l1714"></a>
<span id="l1715">        } finally {</span><a href="#l1715"></a>
<span id="l1716">            passHandle = oldHandle;</span><a href="#l1716"></a>
<span id="l1717">        }</span><a href="#l1717"></a>
<span id="l1718">    }</span><a href="#l1718"></a>
<span id="l1719"></span><a href="#l1719"></a>
<span id="l1720">    /**</span><a href="#l1720"></a>
<span id="l1721">     * Reads in null code, sets passHandle to NULL_HANDLE and returns null.</span><a href="#l1721"></a>
<span id="l1722">     */</span><a href="#l1722"></a>
<span id="l1723">    private Object readNull() throws IOException {</span><a href="#l1723"></a>
<span id="l1724">        if (bin.readByte() != TC_NULL) {</span><a href="#l1724"></a>
<span id="l1725">            throw new InternalError();</span><a href="#l1725"></a>
<span id="l1726">        }</span><a href="#l1726"></a>
<span id="l1727">        passHandle = NULL_HANDLE;</span><a href="#l1727"></a>
<span id="l1728">        return null;</span><a href="#l1728"></a>
<span id="l1729">    }</span><a href="#l1729"></a>
<span id="l1730"></span><a href="#l1730"></a>
<span id="l1731">    /**</span><a href="#l1731"></a>
<span id="l1732">     * Reads in object handle, sets passHandle to the read handle, and returns</span><a href="#l1732"></a>
<span id="l1733">     * object associated with the handle.</span><a href="#l1733"></a>
<span id="l1734">     */</span><a href="#l1734"></a>
<span id="l1735">    private Object readHandle(boolean unshared) throws IOException {</span><a href="#l1735"></a>
<span id="l1736">        if (bin.readByte() != TC_REFERENCE) {</span><a href="#l1736"></a>
<span id="l1737">            throw new InternalError();</span><a href="#l1737"></a>
<span id="l1738">        }</span><a href="#l1738"></a>
<span id="l1739">        passHandle = bin.readInt() - baseWireHandle;</span><a href="#l1739"></a>
<span id="l1740">        if (passHandle &lt; 0 || passHandle &gt;= handles.size()) {</span><a href="#l1740"></a>
<span id="l1741">            throw new StreamCorruptedException(</span><a href="#l1741"></a>
<span id="l1742">                String.format(&quot;invalid handle value: %08X&quot;, passHandle +</span><a href="#l1742"></a>
<span id="l1743">                baseWireHandle));</span><a href="#l1743"></a>
<span id="l1744">        }</span><a href="#l1744"></a>
<span id="l1745">        if (unshared) {</span><a href="#l1745"></a>
<span id="l1746">            // REMIND: what type of exception to throw here?</span><a href="#l1746"></a>
<span id="l1747">            throw new InvalidObjectException(</span><a href="#l1747"></a>
<span id="l1748">                &quot;cannot read back reference as unshared&quot;);</span><a href="#l1748"></a>
<span id="l1749">        }</span><a href="#l1749"></a>
<span id="l1750"></span><a href="#l1750"></a>
<span id="l1751">        Object obj = handles.lookupObject(passHandle);</span><a href="#l1751"></a>
<span id="l1752">        if (obj == unsharedMarker) {</span><a href="#l1752"></a>
<span id="l1753">            // REMIND: what type of exception to throw here?</span><a href="#l1753"></a>
<span id="l1754">            throw new InvalidObjectException(</span><a href="#l1754"></a>
<span id="l1755">                &quot;cannot read back reference to unshared object&quot;);</span><a href="#l1755"></a>
<span id="l1756">        }</span><a href="#l1756"></a>
<span id="l1757">        filterCheck(null, -1);       // just a check for number of references, depth, no class</span><a href="#l1757"></a>
<span id="l1758">        return obj;</span><a href="#l1758"></a>
<span id="l1759">    }</span><a href="#l1759"></a>
<span id="l1760"></span><a href="#l1760"></a>
<span id="l1761">    /**</span><a href="#l1761"></a>
<span id="l1762">     * Reads in and returns class object.  Sets passHandle to class object's</span><a href="#l1762"></a>
<span id="l1763">     * assigned handle.  Returns null if class is unresolvable (in which case a</span><a href="#l1763"></a>
<span id="l1764">     * ClassNotFoundException will be associated with the class' handle in the</span><a href="#l1764"></a>
<span id="l1765">     * handle table).</span><a href="#l1765"></a>
<span id="l1766">     */</span><a href="#l1766"></a>
<span id="l1767">    private Class&lt;?&gt; readClass(boolean unshared) throws IOException {</span><a href="#l1767"></a>
<span id="l1768">        if (bin.readByte() != TC_CLASS) {</span><a href="#l1768"></a>
<span id="l1769">            throw new InternalError();</span><a href="#l1769"></a>
<span id="l1770">        }</span><a href="#l1770"></a>
<span id="l1771">        ObjectStreamClass desc = readClassDesc(false);</span><a href="#l1771"></a>
<span id="l1772">        Class&lt;?&gt; cl = desc.forClass();</span><a href="#l1772"></a>
<span id="l1773">        passHandle = handles.assign(unshared ? unsharedMarker : cl);</span><a href="#l1773"></a>
<span id="l1774"></span><a href="#l1774"></a>
<span id="l1775">        ClassNotFoundException resolveEx = desc.getResolveException();</span><a href="#l1775"></a>
<span id="l1776">        if (resolveEx != null) {</span><a href="#l1776"></a>
<span id="l1777">            handles.markException(passHandle, resolveEx);</span><a href="#l1777"></a>
<span id="l1778">        }</span><a href="#l1778"></a>
<span id="l1779"></span><a href="#l1779"></a>
<span id="l1780">        handles.finish(passHandle);</span><a href="#l1780"></a>
<span id="l1781">        return cl;</span><a href="#l1781"></a>
<span id="l1782">    }</span><a href="#l1782"></a>
<span id="l1783"></span><a href="#l1783"></a>
<span id="l1784">    /**</span><a href="#l1784"></a>
<span id="l1785">     * Reads in and returns (possibly null) class descriptor.  Sets passHandle</span><a href="#l1785"></a>
<span id="l1786">     * to class descriptor's assigned handle.  If class descriptor cannot be</span><a href="#l1786"></a>
<span id="l1787">     * resolved to a class in the local VM, a ClassNotFoundException is</span><a href="#l1787"></a>
<span id="l1788">     * associated with the class descriptor's handle.</span><a href="#l1788"></a>
<span id="l1789">     */</span><a href="#l1789"></a>
<span id="l1790">    private ObjectStreamClass readClassDesc(boolean unshared)</span><a href="#l1790"></a>
<span id="l1791">        throws IOException</span><a href="#l1791"></a>
<span id="l1792">    {</span><a href="#l1792"></a>
<span id="l1793">        byte tc = bin.peekByte();</span><a href="#l1793"></a>
<span id="l1794">        ObjectStreamClass descriptor;</span><a href="#l1794"></a>
<span id="l1795">        switch (tc) {</span><a href="#l1795"></a>
<span id="l1796">            case TC_NULL:</span><a href="#l1796"></a>
<span id="l1797">                descriptor = (ObjectStreamClass) readNull();</span><a href="#l1797"></a>
<span id="l1798">                break;</span><a href="#l1798"></a>
<span id="l1799">            case TC_REFERENCE:</span><a href="#l1799"></a>
<span id="l1800">                descriptor = (ObjectStreamClass) readHandle(unshared);</span><a href="#l1800"></a>
<span id="l1801">                // Should only reference initialized class descriptors</span><a href="#l1801"></a>
<span id="l1802">                descriptor.checkInitialized();</span><a href="#l1802"></a>
<span id="l1803">                break;</span><a href="#l1803"></a>
<span id="l1804">            case TC_PROXYCLASSDESC:</span><a href="#l1804"></a>
<span id="l1805">                descriptor = readProxyDesc(unshared);</span><a href="#l1805"></a>
<span id="l1806">                break;</span><a href="#l1806"></a>
<span id="l1807">            case TC_CLASSDESC:</span><a href="#l1807"></a>
<span id="l1808">                descriptor = readNonProxyDesc(unshared);</span><a href="#l1808"></a>
<span id="l1809">                break;</span><a href="#l1809"></a>
<span id="l1810">            default:</span><a href="#l1810"></a>
<span id="l1811">                throw new StreamCorruptedException(</span><a href="#l1811"></a>
<span id="l1812">                    String.format(&quot;invalid type code: %02X&quot;, tc));</span><a href="#l1812"></a>
<span id="l1813">        }</span><a href="#l1813"></a>
<span id="l1814">        if (descriptor != null) {</span><a href="#l1814"></a>
<span id="l1815">            validateDescriptor(descriptor);</span><a href="#l1815"></a>
<span id="l1816">        }</span><a href="#l1816"></a>
<span id="l1817">        return descriptor;</span><a href="#l1817"></a>
<span id="l1818">    }</span><a href="#l1818"></a>
<span id="l1819"></span><a href="#l1819"></a>
<span id="l1820">    private boolean isCustomSubclass() {</span><a href="#l1820"></a>
<span id="l1821">        // Return true if this class is a custom subclass of ObjectInputStream</span><a href="#l1821"></a>
<span id="l1822">        return getClass().getClassLoader()</span><a href="#l1822"></a>
<span id="l1823">                    != ObjectInputStream.class.getClassLoader();</span><a href="#l1823"></a>
<span id="l1824">    }</span><a href="#l1824"></a>
<span id="l1825"></span><a href="#l1825"></a>
<span id="l1826">    /**</span><a href="#l1826"></a>
<span id="l1827">     * Reads in and returns class descriptor for a dynamic proxy class.  Sets</span><a href="#l1827"></a>
<span id="l1828">     * passHandle to proxy class descriptor's assigned handle.  If proxy class</span><a href="#l1828"></a>
<span id="l1829">     * descriptor cannot be resolved to a class in the local VM, a</span><a href="#l1829"></a>
<span id="l1830">     * ClassNotFoundException is associated with the descriptor's handle.</span><a href="#l1830"></a>
<span id="l1831">     */</span><a href="#l1831"></a>
<span id="l1832">    private ObjectStreamClass readProxyDesc(boolean unshared)</span><a href="#l1832"></a>
<span id="l1833">        throws IOException</span><a href="#l1833"></a>
<span id="l1834">    {</span><a href="#l1834"></a>
<span id="l1835">        if (bin.readByte() != TC_PROXYCLASSDESC) {</span><a href="#l1835"></a>
<span id="l1836">            throw new InternalError();</span><a href="#l1836"></a>
<span id="l1837">        }</span><a href="#l1837"></a>
<span id="l1838"></span><a href="#l1838"></a>
<span id="l1839">        ObjectStreamClass desc = new ObjectStreamClass();</span><a href="#l1839"></a>
<span id="l1840">        int descHandle = handles.assign(unshared ? unsharedMarker : desc);</span><a href="#l1840"></a>
<span id="l1841">        passHandle = NULL_HANDLE;</span><a href="#l1841"></a>
<span id="l1842"></span><a href="#l1842"></a>
<span id="l1843">        int numIfaces = bin.readInt();</span><a href="#l1843"></a>
<span id="l1844">        if (numIfaces &gt; 65535) {</span><a href="#l1844"></a>
<span id="l1845">            throw new InvalidObjectException(&quot;interface limit exceeded: &quot;</span><a href="#l1845"></a>
<span id="l1846">                    + numIfaces);</span><a href="#l1846"></a>
<span id="l1847">        }</span><a href="#l1847"></a>
<span id="l1848">        String[] ifaces = new String[numIfaces];</span><a href="#l1848"></a>
<span id="l1849">        for (int i = 0; i &lt; numIfaces; i++) {</span><a href="#l1849"></a>
<span id="l1850">            ifaces[i] = bin.readUTF();</span><a href="#l1850"></a>
<span id="l1851">        }</span><a href="#l1851"></a>
<span id="l1852"></span><a href="#l1852"></a>
<span id="l1853">        Class&lt;?&gt; cl = null;</span><a href="#l1853"></a>
<span id="l1854">        ClassNotFoundException resolveEx = null;</span><a href="#l1854"></a>
<span id="l1855">        bin.setBlockDataMode(true);</span><a href="#l1855"></a>
<span id="l1856">        try {</span><a href="#l1856"></a>
<span id="l1857">            if ((cl = resolveProxyClass(ifaces)) == null) {</span><a href="#l1857"></a>
<span id="l1858">                resolveEx = new ClassNotFoundException(&quot;null class&quot;);</span><a href="#l1858"></a>
<span id="l1859">            } else if (!Proxy.isProxyClass(cl)) {</span><a href="#l1859"></a>
<span id="l1860">                throw new InvalidClassException(&quot;Not a proxy&quot;);</span><a href="#l1860"></a>
<span id="l1861">            } else {</span><a href="#l1861"></a>
<span id="l1862">                // ReflectUtil.checkProxyPackageAccess makes a test</span><a href="#l1862"></a>
<span id="l1863">                // equivalent to isCustomSubclass so there's no need</span><a href="#l1863"></a>
<span id="l1864">                // to condition this call to isCustomSubclass == true here.</span><a href="#l1864"></a>
<span id="l1865">                ReflectUtil.checkProxyPackageAccess(</span><a href="#l1865"></a>
<span id="l1866">                        getClass().getClassLoader(),</span><a href="#l1866"></a>
<span id="l1867">                        cl.getInterfaces());</span><a href="#l1867"></a>
<span id="l1868">                // Filter the interfaces</span><a href="#l1868"></a>
<span id="l1869">                for (Class&lt;?&gt; clazz : cl.getInterfaces()) {</span><a href="#l1869"></a>
<span id="l1870">                    filterCheck(clazz, -1);</span><a href="#l1870"></a>
<span id="l1871">                }</span><a href="#l1871"></a>
<span id="l1872">            }</span><a href="#l1872"></a>
<span id="l1873">        } catch (ClassNotFoundException ex) {</span><a href="#l1873"></a>
<span id="l1874">            resolveEx = ex;</span><a href="#l1874"></a>
<span id="l1875">        }</span><a href="#l1875"></a>
<span id="l1876"></span><a href="#l1876"></a>
<span id="l1877">        // Call filterCheck on the class before reading anything else</span><a href="#l1877"></a>
<span id="l1878">        filterCheck(cl, -1);</span><a href="#l1878"></a>
<span id="l1879"></span><a href="#l1879"></a>
<span id="l1880">        skipCustomData();</span><a href="#l1880"></a>
<span id="l1881"></span><a href="#l1881"></a>
<span id="l1882">        try {</span><a href="#l1882"></a>
<span id="l1883">            totalObjectRefs++;</span><a href="#l1883"></a>
<span id="l1884">            depth++;</span><a href="#l1884"></a>
<span id="l1885">            desc.initProxy(cl, resolveEx, readClassDesc(false));</span><a href="#l1885"></a>
<span id="l1886">        } finally {</span><a href="#l1886"></a>
<span id="l1887">            depth--;</span><a href="#l1887"></a>
<span id="l1888">        }</span><a href="#l1888"></a>
<span id="l1889"></span><a href="#l1889"></a>
<span id="l1890">        handles.finish(descHandle);</span><a href="#l1890"></a>
<span id="l1891">        passHandle = descHandle;</span><a href="#l1891"></a>
<span id="l1892">        return desc;</span><a href="#l1892"></a>
<span id="l1893">    }</span><a href="#l1893"></a>
<span id="l1894"></span><a href="#l1894"></a>
<span id="l1895">    /**</span><a href="#l1895"></a>
<span id="l1896">     * Reads in and returns class descriptor for a class that is not a dynamic</span><a href="#l1896"></a>
<span id="l1897">     * proxy class.  Sets passHandle to class descriptor's assigned handle.  If</span><a href="#l1897"></a>
<span id="l1898">     * class descriptor cannot be resolved to a class in the local VM, a</span><a href="#l1898"></a>
<span id="l1899">     * ClassNotFoundException is associated with the descriptor's handle.</span><a href="#l1899"></a>
<span id="l1900">     */</span><a href="#l1900"></a>
<span id="l1901">    private ObjectStreamClass readNonProxyDesc(boolean unshared)</span><a href="#l1901"></a>
<span id="l1902">        throws IOException</span><a href="#l1902"></a>
<span id="l1903">    {</span><a href="#l1903"></a>
<span id="l1904">        if (bin.readByte() != TC_CLASSDESC) {</span><a href="#l1904"></a>
<span id="l1905">            throw new InternalError();</span><a href="#l1905"></a>
<span id="l1906">        }</span><a href="#l1906"></a>
<span id="l1907"></span><a href="#l1907"></a>
<span id="l1908">        ObjectStreamClass desc = new ObjectStreamClass();</span><a href="#l1908"></a>
<span id="l1909">        int descHandle = handles.assign(unshared ? unsharedMarker : desc);</span><a href="#l1909"></a>
<span id="l1910">        passHandle = NULL_HANDLE;</span><a href="#l1910"></a>
<span id="l1911"></span><a href="#l1911"></a>
<span id="l1912">        ObjectStreamClass readDesc = null;</span><a href="#l1912"></a>
<span id="l1913">        try {</span><a href="#l1913"></a>
<span id="l1914">            readDesc = readClassDescriptor();</span><a href="#l1914"></a>
<span id="l1915">        } catch (ClassNotFoundException ex) {</span><a href="#l1915"></a>
<span id="l1916">            throw (IOException) new InvalidClassException(</span><a href="#l1916"></a>
<span id="l1917">                &quot;failed to read class descriptor&quot;).initCause(ex);</span><a href="#l1917"></a>
<span id="l1918">        }</span><a href="#l1918"></a>
<span id="l1919"></span><a href="#l1919"></a>
<span id="l1920">        Class&lt;?&gt; cl = null;</span><a href="#l1920"></a>
<span id="l1921">        ClassNotFoundException resolveEx = null;</span><a href="#l1921"></a>
<span id="l1922">        bin.setBlockDataMode(true);</span><a href="#l1922"></a>
<span id="l1923">        final boolean checksRequired = isCustomSubclass();</span><a href="#l1923"></a>
<span id="l1924">        try {</span><a href="#l1924"></a>
<span id="l1925">            if ((cl = resolveClass(readDesc)) == null) {</span><a href="#l1925"></a>
<span id="l1926">                resolveEx = new ClassNotFoundException(&quot;null class&quot;);</span><a href="#l1926"></a>
<span id="l1927">            } else if (checksRequired) {</span><a href="#l1927"></a>
<span id="l1928">                ReflectUtil.checkPackageAccess(cl);</span><a href="#l1928"></a>
<span id="l1929">            }</span><a href="#l1929"></a>
<span id="l1930">        } catch (ClassNotFoundException ex) {</span><a href="#l1930"></a>
<span id="l1931">            resolveEx = ex;</span><a href="#l1931"></a>
<span id="l1932">        }</span><a href="#l1932"></a>
<span id="l1933"></span><a href="#l1933"></a>
<span id="l1934">        // Call filterCheck on the class before reading anything else</span><a href="#l1934"></a>
<span id="l1935">        filterCheck(cl, -1);</span><a href="#l1935"></a>
<span id="l1936"></span><a href="#l1936"></a>
<span id="l1937">        skipCustomData();</span><a href="#l1937"></a>
<span id="l1938"></span><a href="#l1938"></a>
<span id="l1939">        try {</span><a href="#l1939"></a>
<span id="l1940">            totalObjectRefs++;</span><a href="#l1940"></a>
<span id="l1941">            depth++;</span><a href="#l1941"></a>
<span id="l1942">            desc.initNonProxy(readDesc, cl, resolveEx, readClassDesc(false));</span><a href="#l1942"></a>
<span id="l1943">        } finally {</span><a href="#l1943"></a>
<span id="l1944">            depth--;</span><a href="#l1944"></a>
<span id="l1945">        }</span><a href="#l1945"></a>
<span id="l1946"></span><a href="#l1946"></a>
<span id="l1947">        handles.finish(descHandle);</span><a href="#l1947"></a>
<span id="l1948">        passHandle = descHandle;</span><a href="#l1948"></a>
<span id="l1949"></span><a href="#l1949"></a>
<span id="l1950">        return desc;</span><a href="#l1950"></a>
<span id="l1951">    }</span><a href="#l1951"></a>
<span id="l1952"></span><a href="#l1952"></a>
<span id="l1953">    /**</span><a href="#l1953"></a>
<span id="l1954">     * Reads in and returns new string.  Sets passHandle to new string's</span><a href="#l1954"></a>
<span id="l1955">     * assigned handle.</span><a href="#l1955"></a>
<span id="l1956">     */</span><a href="#l1956"></a>
<span id="l1957">    private String readString(boolean unshared) throws IOException {</span><a href="#l1957"></a>
<span id="l1958">        String str;</span><a href="#l1958"></a>
<span id="l1959">        byte tc = bin.readByte();</span><a href="#l1959"></a>
<span id="l1960">        switch (tc) {</span><a href="#l1960"></a>
<span id="l1961">            case TC_STRING:</span><a href="#l1961"></a>
<span id="l1962">                str = bin.readUTF();</span><a href="#l1962"></a>
<span id="l1963">                break;</span><a href="#l1963"></a>
<span id="l1964"></span><a href="#l1964"></a>
<span id="l1965">            case TC_LONGSTRING:</span><a href="#l1965"></a>
<span id="l1966">                str = bin.readLongUTF();</span><a href="#l1966"></a>
<span id="l1967">                break;</span><a href="#l1967"></a>
<span id="l1968"></span><a href="#l1968"></a>
<span id="l1969">            default:</span><a href="#l1969"></a>
<span id="l1970">                throw new StreamCorruptedException(</span><a href="#l1970"></a>
<span id="l1971">                    String.format(&quot;invalid type code: %02X&quot;, tc));</span><a href="#l1971"></a>
<span id="l1972">        }</span><a href="#l1972"></a>
<span id="l1973">        passHandle = handles.assign(unshared ? unsharedMarker : str);</span><a href="#l1973"></a>
<span id="l1974">        handles.finish(passHandle);</span><a href="#l1974"></a>
<span id="l1975">        return str;</span><a href="#l1975"></a>
<span id="l1976">    }</span><a href="#l1976"></a>
<span id="l1977"></span><a href="#l1977"></a>
<span id="l1978">    /**</span><a href="#l1978"></a>
<span id="l1979">     * Reads in and returns array object, or null if array class is</span><a href="#l1979"></a>
<span id="l1980">     * unresolvable.  Sets passHandle to array's assigned handle.</span><a href="#l1980"></a>
<span id="l1981">     */</span><a href="#l1981"></a>
<span id="l1982">    private Object readArray(boolean unshared) throws IOException {</span><a href="#l1982"></a>
<span id="l1983">        if (bin.readByte() != TC_ARRAY) {</span><a href="#l1983"></a>
<span id="l1984">            throw new InternalError();</span><a href="#l1984"></a>
<span id="l1985">        }</span><a href="#l1985"></a>
<span id="l1986"></span><a href="#l1986"></a>
<span id="l1987">        ObjectStreamClass desc = readClassDesc(false);</span><a href="#l1987"></a>
<span id="l1988">        int len = bin.readInt();</span><a href="#l1988"></a>
<span id="l1989"></span><a href="#l1989"></a>
<span id="l1990">        filterCheck(desc.forClass(), len);</span><a href="#l1990"></a>
<span id="l1991"></span><a href="#l1991"></a>
<span id="l1992">        Object array = null;</span><a href="#l1992"></a>
<span id="l1993">        Class&lt;?&gt; cl, ccl = null;</span><a href="#l1993"></a>
<span id="l1994">        if ((cl = desc.forClass()) != null) {</span><a href="#l1994"></a>
<span id="l1995">            ccl = cl.getComponentType();</span><a href="#l1995"></a>
<span id="l1996">            array = Array.newInstance(ccl, len);</span><a href="#l1996"></a>
<span id="l1997">        }</span><a href="#l1997"></a>
<span id="l1998"></span><a href="#l1998"></a>
<span id="l1999">        int arrayHandle = handles.assign(unshared ? unsharedMarker : array);</span><a href="#l1999"></a>
<span id="l2000">        ClassNotFoundException resolveEx = desc.getResolveException();</span><a href="#l2000"></a>
<span id="l2001">        if (resolveEx != null) {</span><a href="#l2001"></a>
<span id="l2002">            handles.markException(arrayHandle, resolveEx);</span><a href="#l2002"></a>
<span id="l2003">        }</span><a href="#l2003"></a>
<span id="l2004"></span><a href="#l2004"></a>
<span id="l2005">        if (ccl == null) {</span><a href="#l2005"></a>
<span id="l2006">            for (int i = 0; i &lt; len; i++) {</span><a href="#l2006"></a>
<span id="l2007">                readObject0(Object.class, false);</span><a href="#l2007"></a>
<span id="l2008">            }</span><a href="#l2008"></a>
<span id="l2009">        } else if (ccl.isPrimitive()) {</span><a href="#l2009"></a>
<span id="l2010">            if (ccl == Integer.TYPE) {</span><a href="#l2010"></a>
<span id="l2011">                bin.readInts((int[]) array, 0, len);</span><a href="#l2011"></a>
<span id="l2012">            } else if (ccl == Byte.TYPE) {</span><a href="#l2012"></a>
<span id="l2013">                bin.readFully((byte[]) array, 0, len, true);</span><a href="#l2013"></a>
<span id="l2014">            } else if (ccl == Long.TYPE) {</span><a href="#l2014"></a>
<span id="l2015">                bin.readLongs((long[]) array, 0, len);</span><a href="#l2015"></a>
<span id="l2016">            } else if (ccl == Float.TYPE) {</span><a href="#l2016"></a>
<span id="l2017">                bin.readFloats((float[]) array, 0, len);</span><a href="#l2017"></a>
<span id="l2018">            } else if (ccl == Double.TYPE) {</span><a href="#l2018"></a>
<span id="l2019">                bin.readDoubles((double[]) array, 0, len);</span><a href="#l2019"></a>
<span id="l2020">            } else if (ccl == Short.TYPE) {</span><a href="#l2020"></a>
<span id="l2021">                bin.readShorts((short[]) array, 0, len);</span><a href="#l2021"></a>
<span id="l2022">            } else if (ccl == Character.TYPE) {</span><a href="#l2022"></a>
<span id="l2023">                bin.readChars((char[]) array, 0, len);</span><a href="#l2023"></a>
<span id="l2024">            } else if (ccl == Boolean.TYPE) {</span><a href="#l2024"></a>
<span id="l2025">                bin.readBooleans((boolean[]) array, 0, len);</span><a href="#l2025"></a>
<span id="l2026">            } else {</span><a href="#l2026"></a>
<span id="l2027">                throw new InternalError();</span><a href="#l2027"></a>
<span id="l2028">            }</span><a href="#l2028"></a>
<span id="l2029">        } else {</span><a href="#l2029"></a>
<span id="l2030">            Object[] oa = (Object[]) array;</span><a href="#l2030"></a>
<span id="l2031">            for (int i = 0; i &lt; len; i++) {</span><a href="#l2031"></a>
<span id="l2032">                oa[i] = readObject0(Object.class, false);</span><a href="#l2032"></a>
<span id="l2033">                handles.markDependency(arrayHandle, passHandle);</span><a href="#l2033"></a>
<span id="l2034">            }</span><a href="#l2034"></a>
<span id="l2035">        }</span><a href="#l2035"></a>
<span id="l2036"></span><a href="#l2036"></a>
<span id="l2037">        handles.finish(arrayHandle);</span><a href="#l2037"></a>
<span id="l2038">        passHandle = arrayHandle;</span><a href="#l2038"></a>
<span id="l2039">        return array;</span><a href="#l2039"></a>
<span id="l2040">    }</span><a href="#l2040"></a>
<span id="l2041"></span><a href="#l2041"></a>
<span id="l2042">    /**</span><a href="#l2042"></a>
<span id="l2043">     * Reads in and returns enum constant, or null if enum type is</span><a href="#l2043"></a>
<span id="l2044">     * unresolvable.  Sets passHandle to enum constant's assigned handle.</span><a href="#l2044"></a>
<span id="l2045">     */</span><a href="#l2045"></a>
<span id="l2046">    private Enum&lt;?&gt; readEnum(boolean unshared) throws IOException {</span><a href="#l2046"></a>
<span id="l2047">        if (bin.readByte() != TC_ENUM) {</span><a href="#l2047"></a>
<span id="l2048">            throw new InternalError();</span><a href="#l2048"></a>
<span id="l2049">        }</span><a href="#l2049"></a>
<span id="l2050"></span><a href="#l2050"></a>
<span id="l2051">        ObjectStreamClass desc = readClassDesc(false);</span><a href="#l2051"></a>
<span id="l2052">        if (!desc.isEnum()) {</span><a href="#l2052"></a>
<span id="l2053">            throw new InvalidClassException(&quot;non-enum class: &quot; + desc);</span><a href="#l2053"></a>
<span id="l2054">        }</span><a href="#l2054"></a>
<span id="l2055"></span><a href="#l2055"></a>
<span id="l2056">        int enumHandle = handles.assign(unshared ? unsharedMarker : null);</span><a href="#l2056"></a>
<span id="l2057">        ClassNotFoundException resolveEx = desc.getResolveException();</span><a href="#l2057"></a>
<span id="l2058">        if (resolveEx != null) {</span><a href="#l2058"></a>
<span id="l2059">            handles.markException(enumHandle, resolveEx);</span><a href="#l2059"></a>
<span id="l2060">        }</span><a href="#l2060"></a>
<span id="l2061"></span><a href="#l2061"></a>
<span id="l2062">        String name = readString(false);</span><a href="#l2062"></a>
<span id="l2063">        Enum&lt;?&gt; result = null;</span><a href="#l2063"></a>
<span id="l2064">        Class&lt;?&gt; cl = desc.forClass();</span><a href="#l2064"></a>
<span id="l2065">        if (cl != null) {</span><a href="#l2065"></a>
<span id="l2066">            try {</span><a href="#l2066"></a>
<span id="l2067">                @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l2067"></a>
<span id="l2068">                Enum&lt;?&gt; en = Enum.valueOf((Class)cl, name);</span><a href="#l2068"></a>
<span id="l2069">                result = en;</span><a href="#l2069"></a>
<span id="l2070">            } catch (IllegalArgumentException ex) {</span><a href="#l2070"></a>
<span id="l2071">                throw (IOException) new InvalidObjectException(</span><a href="#l2071"></a>
<span id="l2072">                    &quot;enum constant &quot; + name + &quot; does not exist in &quot; +</span><a href="#l2072"></a>
<span id="l2073">                    cl).initCause(ex);</span><a href="#l2073"></a>
<span id="l2074">            }</span><a href="#l2074"></a>
<span id="l2075">            if (!unshared) {</span><a href="#l2075"></a>
<span id="l2076">                handles.setObject(enumHandle, result);</span><a href="#l2076"></a>
<span id="l2077">            }</span><a href="#l2077"></a>
<span id="l2078">        }</span><a href="#l2078"></a>
<span id="l2079"></span><a href="#l2079"></a>
<span id="l2080">        handles.finish(enumHandle);</span><a href="#l2080"></a>
<span id="l2081">        passHandle = enumHandle;</span><a href="#l2081"></a>
<span id="l2082">        return result;</span><a href="#l2082"></a>
<span id="l2083">    }</span><a href="#l2083"></a>
<span id="l2084"></span><a href="#l2084"></a>
<span id="l2085">    /**</span><a href="#l2085"></a>
<span id="l2086">     * Reads and returns &quot;ordinary&quot; (i.e., not a String, Class,</span><a href="#l2086"></a>
<span id="l2087">     * ObjectStreamClass, array, or enum constant) object, or null if object's</span><a href="#l2087"></a>
<span id="l2088">     * class is unresolvable (in which case a ClassNotFoundException will be</span><a href="#l2088"></a>
<span id="l2089">     * associated with object's handle).  Sets passHandle to object's assigned</span><a href="#l2089"></a>
<span id="l2090">     * handle.</span><a href="#l2090"></a>
<span id="l2091">     */</span><a href="#l2091"></a>
<span id="l2092">    private Object readOrdinaryObject(boolean unshared)</span><a href="#l2092"></a>
<span id="l2093">        throws IOException</span><a href="#l2093"></a>
<span id="l2094">    {</span><a href="#l2094"></a>
<span id="l2095">        if (bin.readByte() != TC_OBJECT) {</span><a href="#l2095"></a>
<span id="l2096">            throw new InternalError();</span><a href="#l2096"></a>
<span id="l2097">        }</span><a href="#l2097"></a>
<span id="l2098"></span><a href="#l2098"></a>
<span id="l2099">        ObjectStreamClass desc = readClassDesc(false);</span><a href="#l2099"></a>
<span id="l2100">        desc.checkDeserialize();</span><a href="#l2100"></a>
<span id="l2101"></span><a href="#l2101"></a>
<span id="l2102">        Class&lt;?&gt; cl = desc.forClass();</span><a href="#l2102"></a>
<span id="l2103">        if (cl == String.class || cl == Class.class</span><a href="#l2103"></a>
<span id="l2104">                || cl == ObjectStreamClass.class) {</span><a href="#l2104"></a>
<span id="l2105">            throw new InvalidClassException(&quot;invalid class descriptor&quot;);</span><a href="#l2105"></a>
<span id="l2106">        }</span><a href="#l2106"></a>
<span id="l2107"></span><a href="#l2107"></a>
<span id="l2108">        Object obj;</span><a href="#l2108"></a>
<span id="l2109">        try {</span><a href="#l2109"></a>
<span id="l2110">            obj = desc.isInstantiable() ? desc.newInstance() : null;</span><a href="#l2110"></a>
<span id="l2111">        } catch (Exception ex) {</span><a href="#l2111"></a>
<span id="l2112">            throw (IOException) new InvalidClassException(</span><a href="#l2112"></a>
<span id="l2113">                desc.forClass().getName(),</span><a href="#l2113"></a>
<span id="l2114">                &quot;unable to create instance&quot;).initCause(ex);</span><a href="#l2114"></a>
<span id="l2115">        }</span><a href="#l2115"></a>
<span id="l2116"></span><a href="#l2116"></a>
<span id="l2117">        passHandle = handles.assign(unshared ? unsharedMarker : obj);</span><a href="#l2117"></a>
<span id="l2118">        ClassNotFoundException resolveEx = desc.getResolveException();</span><a href="#l2118"></a>
<span id="l2119">        if (resolveEx != null) {</span><a href="#l2119"></a>
<span id="l2120">            handles.markException(passHandle, resolveEx);</span><a href="#l2120"></a>
<span id="l2121">        }</span><a href="#l2121"></a>
<span id="l2122"></span><a href="#l2122"></a>
<span id="l2123">        if (desc.isExternalizable()) {</span><a href="#l2123"></a>
<span id="l2124">            readExternalData((Externalizable) obj, desc);</span><a href="#l2124"></a>
<span id="l2125">        } else {</span><a href="#l2125"></a>
<span id="l2126">            readSerialData(obj, desc);</span><a href="#l2126"></a>
<span id="l2127">        }</span><a href="#l2127"></a>
<span id="l2128"></span><a href="#l2128"></a>
<span id="l2129">        handles.finish(passHandle);</span><a href="#l2129"></a>
<span id="l2130"></span><a href="#l2130"></a>
<span id="l2131">        if (obj != null &amp;&amp;</span><a href="#l2131"></a>
<span id="l2132">            handles.lookupException(passHandle) == null &amp;&amp;</span><a href="#l2132"></a>
<span id="l2133">            desc.hasReadResolveMethod())</span><a href="#l2133"></a>
<span id="l2134">        {</span><a href="#l2134"></a>
<span id="l2135">            Object rep = desc.invokeReadResolve(obj);</span><a href="#l2135"></a>
<span id="l2136">            if (unshared &amp;&amp; rep.getClass().isArray()) {</span><a href="#l2136"></a>
<span id="l2137">                rep = cloneArray(rep);</span><a href="#l2137"></a>
<span id="l2138">            }</span><a href="#l2138"></a>
<span id="l2139">            if (rep != obj) {</span><a href="#l2139"></a>
<span id="l2140">                // Filter the replacement object</span><a href="#l2140"></a>
<span id="l2141">                if (rep != null) {</span><a href="#l2141"></a>
<span id="l2142">                    if (rep.getClass().isArray()) {</span><a href="#l2142"></a>
<span id="l2143">                        filterCheck(rep.getClass(), Array.getLength(rep));</span><a href="#l2143"></a>
<span id="l2144">                    } else {</span><a href="#l2144"></a>
<span id="l2145">                        filterCheck(rep.getClass(), -1);</span><a href="#l2145"></a>
<span id="l2146">                    }</span><a href="#l2146"></a>
<span id="l2147">                }</span><a href="#l2147"></a>
<span id="l2148">                handles.setObject(passHandle, obj = rep);</span><a href="#l2148"></a>
<span id="l2149">            }</span><a href="#l2149"></a>
<span id="l2150">        }</span><a href="#l2150"></a>
<span id="l2151"></span><a href="#l2151"></a>
<span id="l2152">        return obj;</span><a href="#l2152"></a>
<span id="l2153">    }</span><a href="#l2153"></a>
<span id="l2154"></span><a href="#l2154"></a>
<span id="l2155">    /**</span><a href="#l2155"></a>
<span id="l2156">     * If obj is non-null, reads externalizable data by invoking readExternal()</span><a href="#l2156"></a>
<span id="l2157">     * method of obj; otherwise, attempts to skip over externalizable data.</span><a href="#l2157"></a>
<span id="l2158">     * Expects that passHandle is set to obj's handle before this method is</span><a href="#l2158"></a>
<span id="l2159">     * called.</span><a href="#l2159"></a>
<span id="l2160">     */</span><a href="#l2160"></a>
<span id="l2161">    private void readExternalData(Externalizable obj, ObjectStreamClass desc)</span><a href="#l2161"></a>
<span id="l2162">        throws IOException</span><a href="#l2162"></a>
<span id="l2163">    {</span><a href="#l2163"></a>
<span id="l2164">        SerialCallbackContext oldContext = curContext;</span><a href="#l2164"></a>
<span id="l2165">        if (oldContext != null)</span><a href="#l2165"></a>
<span id="l2166">            oldContext.check();</span><a href="#l2166"></a>
<span id="l2167">        curContext = null;</span><a href="#l2167"></a>
<span id="l2168">        try {</span><a href="#l2168"></a>
<span id="l2169">            boolean blocked = desc.hasBlockExternalData();</span><a href="#l2169"></a>
<span id="l2170">            if (blocked) {</span><a href="#l2170"></a>
<span id="l2171">                bin.setBlockDataMode(true);</span><a href="#l2171"></a>
<span id="l2172">            }</span><a href="#l2172"></a>
<span id="l2173">            if (obj != null) {</span><a href="#l2173"></a>
<span id="l2174">                try {</span><a href="#l2174"></a>
<span id="l2175">                    obj.readExternal(this);</span><a href="#l2175"></a>
<span id="l2176">                } catch (ClassNotFoundException ex) {</span><a href="#l2176"></a>
<span id="l2177">                    /*</span><a href="#l2177"></a>
<span id="l2178">                     * In most cases, the handle table has already propagated</span><a href="#l2178"></a>
<span id="l2179">                     * a CNFException to passHandle at this point; this mark</span><a href="#l2179"></a>
<span id="l2180">                     * call is included to address cases where the readExternal</span><a href="#l2180"></a>
<span id="l2181">                     * method has cons'ed and thrown a new CNFException of its</span><a href="#l2181"></a>
<span id="l2182">                     * own.</span><a href="#l2182"></a>
<span id="l2183">                     */</span><a href="#l2183"></a>
<span id="l2184">                     handles.markException(passHandle, ex);</span><a href="#l2184"></a>
<span id="l2185">                }</span><a href="#l2185"></a>
<span id="l2186">            }</span><a href="#l2186"></a>
<span id="l2187">            if (blocked) {</span><a href="#l2187"></a>
<span id="l2188">                skipCustomData();</span><a href="#l2188"></a>
<span id="l2189">            }</span><a href="#l2189"></a>
<span id="l2190">        } finally {</span><a href="#l2190"></a>
<span id="l2191">            if (oldContext != null)</span><a href="#l2191"></a>
<span id="l2192">                oldContext.check();</span><a href="#l2192"></a>
<span id="l2193">            curContext = oldContext;</span><a href="#l2193"></a>
<span id="l2194">        }</span><a href="#l2194"></a>
<span id="l2195">        /*</span><a href="#l2195"></a>
<span id="l2196">         * At this point, if the externalizable data was not written in</span><a href="#l2196"></a>
<span id="l2197">         * block-data form and either the externalizable class doesn't exist</span><a href="#l2197"></a>
<span id="l2198">         * locally (i.e., obj == null) or readExternal() just threw a</span><a href="#l2198"></a>
<span id="l2199">         * CNFException, then the stream is probably in an inconsistent state,</span><a href="#l2199"></a>
<span id="l2200">         * since some (or all) of the externalizable data may not have been</span><a href="#l2200"></a>
<span id="l2201">         * consumed.  Since there's no &quot;correct&quot; action to take in this case,</span><a href="#l2201"></a>
<span id="l2202">         * we mimic the behavior of past serialization implementations and</span><a href="#l2202"></a>
<span id="l2203">         * blindly hope that the stream is in sync; if it isn't and additional</span><a href="#l2203"></a>
<span id="l2204">         * externalizable data remains in the stream, a subsequent read will</span><a href="#l2204"></a>
<span id="l2205">         * most likely throw a StreamCorruptedException.</span><a href="#l2205"></a>
<span id="l2206">         */</span><a href="#l2206"></a>
<span id="l2207">    }</span><a href="#l2207"></a>
<span id="l2208"></span><a href="#l2208"></a>
<span id="l2209">    /**</span><a href="#l2209"></a>
<span id="l2210">     * Reads (or attempts to skip, if obj is null or is tagged with a</span><a href="#l2210"></a>
<span id="l2211">     * ClassNotFoundException) instance data for each serializable class of</span><a href="#l2211"></a>
<span id="l2212">     * object in stream, from superclass to subclass.  Expects that passHandle</span><a href="#l2212"></a>
<span id="l2213">     * is set to obj's handle before this method is called.</span><a href="#l2213"></a>
<span id="l2214">     */</span><a href="#l2214"></a>
<span id="l2215">    private void readSerialData(Object obj, ObjectStreamClass desc)</span><a href="#l2215"></a>
<span id="l2216">        throws IOException</span><a href="#l2216"></a>
<span id="l2217">    {</span><a href="#l2217"></a>
<span id="l2218">        ObjectStreamClass.ClassDataSlot[] slots = desc.getClassDataLayout();</span><a href="#l2218"></a>
<span id="l2219">        for (int i = 0; i &lt; slots.length; i++) {</span><a href="#l2219"></a>
<span id="l2220">            ObjectStreamClass slotDesc = slots[i].desc;</span><a href="#l2220"></a>
<span id="l2221"></span><a href="#l2221"></a>
<span id="l2222">            if (slots[i].hasData) {</span><a href="#l2222"></a>
<span id="l2223">                if (obj == null || handles.lookupException(passHandle) != null) {</span><a href="#l2223"></a>
<span id="l2224">                    defaultReadFields(null, slotDesc); // skip field values</span><a href="#l2224"></a>
<span id="l2225">                } else if (slotDesc.hasReadObjectMethod()) {</span><a href="#l2225"></a>
<span id="l2226">                    ThreadDeath t = null;</span><a href="#l2226"></a>
<span id="l2227">                    boolean reset = false;</span><a href="#l2227"></a>
<span id="l2228">                    SerialCallbackContext oldContext = curContext;</span><a href="#l2228"></a>
<span id="l2229">                    if (oldContext != null)</span><a href="#l2229"></a>
<span id="l2230">                        oldContext.check();</span><a href="#l2230"></a>
<span id="l2231">                    try {</span><a href="#l2231"></a>
<span id="l2232">                        curContext = new SerialCallbackContext(obj, slotDesc);</span><a href="#l2232"></a>
<span id="l2233"></span><a href="#l2233"></a>
<span id="l2234">                        bin.setBlockDataMode(true);</span><a href="#l2234"></a>
<span id="l2235">                        slotDesc.invokeReadObject(obj, this);</span><a href="#l2235"></a>
<span id="l2236">                    } catch (ClassNotFoundException ex) {</span><a href="#l2236"></a>
<span id="l2237">                        /*</span><a href="#l2237"></a>
<span id="l2238">                         * In most cases, the handle table has already</span><a href="#l2238"></a>
<span id="l2239">                         * propagated a CNFException to passHandle at this</span><a href="#l2239"></a>
<span id="l2240">                         * point; this mark call is included to address cases</span><a href="#l2240"></a>
<span id="l2241">                         * where the custom readObject method has cons'ed and</span><a href="#l2241"></a>
<span id="l2242">                         * thrown a new CNFException of its own.</span><a href="#l2242"></a>
<span id="l2243">                         */</span><a href="#l2243"></a>
<span id="l2244">                        handles.markException(passHandle, ex);</span><a href="#l2244"></a>
<span id="l2245">                    } finally {</span><a href="#l2245"></a>
<span id="l2246">                        do {</span><a href="#l2246"></a>
<span id="l2247">                            try {</span><a href="#l2247"></a>
<span id="l2248">                                curContext.setUsed();</span><a href="#l2248"></a>
<span id="l2249">                                if (oldContext!= null)</span><a href="#l2249"></a>
<span id="l2250">                                    oldContext.check();</span><a href="#l2250"></a>
<span id="l2251">                                curContext = oldContext;</span><a href="#l2251"></a>
<span id="l2252">                                reset = true;</span><a href="#l2252"></a>
<span id="l2253">                            } catch (ThreadDeath x) {</span><a href="#l2253"></a>
<span id="l2254">                                t = x;  // defer until reset is true</span><a href="#l2254"></a>
<span id="l2255">                            }</span><a href="#l2255"></a>
<span id="l2256">                        } while (!reset);</span><a href="#l2256"></a>
<span id="l2257">                        if (t != null)</span><a href="#l2257"></a>
<span id="l2258">                            throw t;</span><a href="#l2258"></a>
<span id="l2259">                    }</span><a href="#l2259"></a>
<span id="l2260"></span><a href="#l2260"></a>
<span id="l2261">                    /*</span><a href="#l2261"></a>
<span id="l2262">                     * defaultDataEnd may have been set indirectly by custom</span><a href="#l2262"></a>
<span id="l2263">                     * readObject() method when calling defaultReadObject() or</span><a href="#l2263"></a>
<span id="l2264">                     * readFields(); clear it to restore normal read behavior.</span><a href="#l2264"></a>
<span id="l2265">                     */</span><a href="#l2265"></a>
<span id="l2266">                    defaultDataEnd = false;</span><a href="#l2266"></a>
<span id="l2267">                } else {</span><a href="#l2267"></a>
<span id="l2268">                    defaultReadFields(obj, slotDesc);</span><a href="#l2268"></a>
<span id="l2269">                    }</span><a href="#l2269"></a>
<span id="l2270"></span><a href="#l2270"></a>
<span id="l2271">                if (slotDesc.hasWriteObjectData()) {</span><a href="#l2271"></a>
<span id="l2272">                    skipCustomData();</span><a href="#l2272"></a>
<span id="l2273">                } else {</span><a href="#l2273"></a>
<span id="l2274">                    bin.setBlockDataMode(false);</span><a href="#l2274"></a>
<span id="l2275">                }</span><a href="#l2275"></a>
<span id="l2276">            } else {</span><a href="#l2276"></a>
<span id="l2277">                if (obj != null &amp;&amp;</span><a href="#l2277"></a>
<span id="l2278">                    slotDesc.hasReadObjectNoDataMethod() &amp;&amp;</span><a href="#l2278"></a>
<span id="l2279">                    handles.lookupException(passHandle) == null)</span><a href="#l2279"></a>
<span id="l2280">                {</span><a href="#l2280"></a>
<span id="l2281">                    slotDesc.invokeReadObjectNoData(obj);</span><a href="#l2281"></a>
<span id="l2282">                }</span><a href="#l2282"></a>
<span id="l2283">            }</span><a href="#l2283"></a>
<span id="l2284">        }</span><a href="#l2284"></a>
<span id="l2285">            }</span><a href="#l2285"></a>
<span id="l2286"></span><a href="#l2286"></a>
<span id="l2287">    /**</span><a href="#l2287"></a>
<span id="l2288">     * Skips over all block data and objects until TC_ENDBLOCKDATA is</span><a href="#l2288"></a>
<span id="l2289">     * encountered.</span><a href="#l2289"></a>
<span id="l2290">     */</span><a href="#l2290"></a>
<span id="l2291">    private void skipCustomData() throws IOException {</span><a href="#l2291"></a>
<span id="l2292">        int oldHandle = passHandle;</span><a href="#l2292"></a>
<span id="l2293">        for (;;) {</span><a href="#l2293"></a>
<span id="l2294">            if (bin.getBlockDataMode()) {</span><a href="#l2294"></a>
<span id="l2295">                bin.skipBlockData();</span><a href="#l2295"></a>
<span id="l2296">                bin.setBlockDataMode(false);</span><a href="#l2296"></a>
<span id="l2297">            }</span><a href="#l2297"></a>
<span id="l2298">            switch (bin.peekByte()) {</span><a href="#l2298"></a>
<span id="l2299">                case TC_BLOCKDATA:</span><a href="#l2299"></a>
<span id="l2300">                case TC_BLOCKDATALONG:</span><a href="#l2300"></a>
<span id="l2301">                    bin.setBlockDataMode(true);</span><a href="#l2301"></a>
<span id="l2302">                    break;</span><a href="#l2302"></a>
<span id="l2303"></span><a href="#l2303"></a>
<span id="l2304">                case TC_ENDBLOCKDATA:</span><a href="#l2304"></a>
<span id="l2305">                    bin.readByte();</span><a href="#l2305"></a>
<span id="l2306">                    passHandle = oldHandle;</span><a href="#l2306"></a>
<span id="l2307">                    return;</span><a href="#l2307"></a>
<span id="l2308"></span><a href="#l2308"></a>
<span id="l2309">                default:</span><a href="#l2309"></a>
<span id="l2310">                    readObject0(Object.class, false);</span><a href="#l2310"></a>
<span id="l2311">                    break;</span><a href="#l2311"></a>
<span id="l2312">            }</span><a href="#l2312"></a>
<span id="l2313">        }</span><a href="#l2313"></a>
<span id="l2314">    }</span><a href="#l2314"></a>
<span id="l2315"></span><a href="#l2315"></a>
<span id="l2316">    /**</span><a href="#l2316"></a>
<span id="l2317">     * Reads in values of serializable fields declared by given class</span><a href="#l2317"></a>
<span id="l2318">     * descriptor.  If obj is non-null, sets field values in obj.  Expects that</span><a href="#l2318"></a>
<span id="l2319">     * passHandle is set to obj's handle before this method is called.</span><a href="#l2319"></a>
<span id="l2320">     */</span><a href="#l2320"></a>
<span id="l2321">    private void defaultReadFields(Object obj, ObjectStreamClass desc)</span><a href="#l2321"></a>
<span id="l2322">        throws IOException</span><a href="#l2322"></a>
<span id="l2323">    {</span><a href="#l2323"></a>
<span id="l2324">        Class&lt;?&gt; cl = desc.forClass();</span><a href="#l2324"></a>
<span id="l2325">        if (cl != null &amp;&amp; obj != null &amp;&amp; !cl.isInstance(obj)) {</span><a href="#l2325"></a>
<span id="l2326">            throw new ClassCastException();</span><a href="#l2326"></a>
<span id="l2327">        }</span><a href="#l2327"></a>
<span id="l2328"></span><a href="#l2328"></a>
<span id="l2329">        int primDataSize = desc.getPrimDataSize();</span><a href="#l2329"></a>
<span id="l2330">        if (primVals == null || primVals.length &lt; primDataSize) {</span><a href="#l2330"></a>
<span id="l2331">            primVals = new byte[primDataSize];</span><a href="#l2331"></a>
<span id="l2332">        }</span><a href="#l2332"></a>
<span id="l2333">            bin.readFully(primVals, 0, primDataSize, false);</span><a href="#l2333"></a>
<span id="l2334">        if (obj != null) {</span><a href="#l2334"></a>
<span id="l2335">            desc.setPrimFieldValues(obj, primVals);</span><a href="#l2335"></a>
<span id="l2336">        }</span><a href="#l2336"></a>
<span id="l2337"></span><a href="#l2337"></a>
<span id="l2338">        int objHandle = passHandle;</span><a href="#l2338"></a>
<span id="l2339">        ObjectStreamField[] fields = desc.getFields(false);</span><a href="#l2339"></a>
<span id="l2340">        Object[] objVals = new Object[desc.getNumObjFields()];</span><a href="#l2340"></a>
<span id="l2341">        int numPrimFields = fields.length - objVals.length;</span><a href="#l2341"></a>
<span id="l2342">        for (int i = 0; i &lt; objVals.length; i++) {</span><a href="#l2342"></a>
<span id="l2343">            ObjectStreamField f = fields[numPrimFields + i];</span><a href="#l2343"></a>
<span id="l2344">            objVals[i] = readObject0(Object.class, f.isUnshared());</span><a href="#l2344"></a>
<span id="l2345">            if (f.getField() != null) {</span><a href="#l2345"></a>
<span id="l2346">                handles.markDependency(objHandle, passHandle);</span><a href="#l2346"></a>
<span id="l2347">            }</span><a href="#l2347"></a>
<span id="l2348">        }</span><a href="#l2348"></a>
<span id="l2349">        if (obj != null) {</span><a href="#l2349"></a>
<span id="l2350">            desc.setObjFieldValues(obj, objVals);</span><a href="#l2350"></a>
<span id="l2351">        }</span><a href="#l2351"></a>
<span id="l2352">        passHandle = objHandle;</span><a href="#l2352"></a>
<span id="l2353">    }</span><a href="#l2353"></a>
<span id="l2354"></span><a href="#l2354"></a>
<span id="l2355">    /**</span><a href="#l2355"></a>
<span id="l2356">     * Reads in and returns IOException that caused serialization to abort.</span><a href="#l2356"></a>
<span id="l2357">     * All stream state is discarded prior to reading in fatal exception.  Sets</span><a href="#l2357"></a>
<span id="l2358">     * passHandle to fatal exception's handle.</span><a href="#l2358"></a>
<span id="l2359">     */</span><a href="#l2359"></a>
<span id="l2360">    private IOException readFatalException() throws IOException {</span><a href="#l2360"></a>
<span id="l2361">        if (bin.readByte() != TC_EXCEPTION) {</span><a href="#l2361"></a>
<span id="l2362">            throw new InternalError();</span><a href="#l2362"></a>
<span id="l2363">        }</span><a href="#l2363"></a>
<span id="l2364">        clear();</span><a href="#l2364"></a>
<span id="l2365">        return (IOException) readObject0(Object.class, false);</span><a href="#l2365"></a>
<span id="l2366">    }</span><a href="#l2366"></a>
<span id="l2367"></span><a href="#l2367"></a>
<span id="l2368">    /**</span><a href="#l2368"></a>
<span id="l2369">     * If recursion depth is 0, clears internal data structures; otherwise,</span><a href="#l2369"></a>
<span id="l2370">     * throws a StreamCorruptedException.  This method is called when a</span><a href="#l2370"></a>
<span id="l2371">     * TC_RESET typecode is encountered.</span><a href="#l2371"></a>
<span id="l2372">     */</span><a href="#l2372"></a>
<span id="l2373">    private void handleReset() throws StreamCorruptedException {</span><a href="#l2373"></a>
<span id="l2374">        if (depth &gt; 0) {</span><a href="#l2374"></a>
<span id="l2375">            throw new StreamCorruptedException(</span><a href="#l2375"></a>
<span id="l2376">                &quot;unexpected reset; recursion depth: &quot; + depth);</span><a href="#l2376"></a>
<span id="l2377">        }</span><a href="#l2377"></a>
<span id="l2378">        clear();</span><a href="#l2378"></a>
<span id="l2379">    }</span><a href="#l2379"></a>
<span id="l2380"></span><a href="#l2380"></a>
<span id="l2381">    /**</span><a href="#l2381"></a>
<span id="l2382">     * Converts specified span of bytes into float values.</span><a href="#l2382"></a>
<span id="l2383">     */</span><a href="#l2383"></a>
<span id="l2384">    // REMIND: remove once hotspot inlines Float.intBitsToFloat</span><a href="#l2384"></a>
<span id="l2385">    private static native void bytesToFloats(byte[] src, int srcpos,</span><a href="#l2385"></a>
<span id="l2386">                                             float[] dst, int dstpos,</span><a href="#l2386"></a>
<span id="l2387">                                             int nfloats);</span><a href="#l2387"></a>
<span id="l2388"></span><a href="#l2388"></a>
<span id="l2389">    /**</span><a href="#l2389"></a>
<span id="l2390">     * Converts specified span of bytes into double values.</span><a href="#l2390"></a>
<span id="l2391">     */</span><a href="#l2391"></a>
<span id="l2392">    // REMIND: remove once hotspot inlines Double.longBitsToDouble</span><a href="#l2392"></a>
<span id="l2393">    private static native void bytesToDoubles(byte[] src, int srcpos,</span><a href="#l2393"></a>
<span id="l2394">                                              double[] dst, int dstpos,</span><a href="#l2394"></a>
<span id="l2395">                                              int ndoubles);</span><a href="#l2395"></a>
<span id="l2396"></span><a href="#l2396"></a>
<span id="l2397">    /**</span><a href="#l2397"></a>
<span id="l2398">     * Returns first non-privileged class loader on the stack (excluding</span><a href="#l2398"></a>
<span id="l2399">     * reflection generated frames) or the extension class loader if only</span><a href="#l2399"></a>
<span id="l2400">     * class loaded by the boot class loader and extension class loader are</span><a href="#l2400"></a>
<span id="l2401">     * found on the stack. This method is also called via reflection by the</span><a href="#l2401"></a>
<span id="l2402">     * following RMI-IIOP class:</span><a href="#l2402"></a>
<span id="l2403">     *</span><a href="#l2403"></a>
<span id="l2404">     *     com.sun.corba.se.internal.util.JDKClassLoader</span><a href="#l2404"></a>
<span id="l2405">     *</span><a href="#l2405"></a>
<span id="l2406">     * This method should not be removed or its signature changed without</span><a href="#l2406"></a>
<span id="l2407">     * corresponding modifications to the above class.</span><a href="#l2407"></a>
<span id="l2408">     */</span><a href="#l2408"></a>
<span id="l2409">    private static ClassLoader latestUserDefinedLoader() {</span><a href="#l2409"></a>
<span id="l2410">        return sun.misc.VM.latestUserDefinedLoader();</span><a href="#l2410"></a>
<span id="l2411">    }</span><a href="#l2411"></a>
<span id="l2412"></span><a href="#l2412"></a>
<span id="l2413">    /**</span><a href="#l2413"></a>
<span id="l2414">     * Default GetField implementation.</span><a href="#l2414"></a>
<span id="l2415">     */</span><a href="#l2415"></a>
<span id="l2416">    private class GetFieldImpl extends GetField {</span><a href="#l2416"></a>
<span id="l2417"></span><a href="#l2417"></a>
<span id="l2418">        /** class descriptor describing serializable fields */</span><a href="#l2418"></a>
<span id="l2419">        private final ObjectStreamClass desc;</span><a href="#l2419"></a>
<span id="l2420">        /** primitive field values */</span><a href="#l2420"></a>
<span id="l2421">        private final byte[] primVals;</span><a href="#l2421"></a>
<span id="l2422">        /** object field values */</span><a href="#l2422"></a>
<span id="l2423">        private final Object[] objVals;</span><a href="#l2423"></a>
<span id="l2424">        /** object field value handles */</span><a href="#l2424"></a>
<span id="l2425">        private final int[] objHandles;</span><a href="#l2425"></a>
<span id="l2426"></span><a href="#l2426"></a>
<span id="l2427">        /**</span><a href="#l2427"></a>
<span id="l2428">         * Creates GetFieldImpl object for reading fields defined in given</span><a href="#l2428"></a>
<span id="l2429">         * class descriptor.</span><a href="#l2429"></a>
<span id="l2430">         */</span><a href="#l2430"></a>
<span id="l2431">        GetFieldImpl(ObjectStreamClass desc) {</span><a href="#l2431"></a>
<span id="l2432">            this.desc = desc;</span><a href="#l2432"></a>
<span id="l2433">            primVals = new byte[desc.getPrimDataSize()];</span><a href="#l2433"></a>
<span id="l2434">            objVals = new Object[desc.getNumObjFields()];</span><a href="#l2434"></a>
<span id="l2435">            objHandles = new int[objVals.length];</span><a href="#l2435"></a>
<span id="l2436">        }</span><a href="#l2436"></a>
<span id="l2437"></span><a href="#l2437"></a>
<span id="l2438">        public ObjectStreamClass getObjectStreamClass() {</span><a href="#l2438"></a>
<span id="l2439">            return desc;</span><a href="#l2439"></a>
<span id="l2440">        }</span><a href="#l2440"></a>
<span id="l2441"></span><a href="#l2441"></a>
<span id="l2442">        public boolean defaulted(String name) throws IOException {</span><a href="#l2442"></a>
<span id="l2443">            return (getFieldOffset(name, null) &lt; 0);</span><a href="#l2443"></a>
<span id="l2444">        }</span><a href="#l2444"></a>
<span id="l2445"></span><a href="#l2445"></a>
<span id="l2446">        public boolean get(String name, boolean val) throws IOException {</span><a href="#l2446"></a>
<span id="l2447">            int off = getFieldOffset(name, Boolean.TYPE);</span><a href="#l2447"></a>
<span id="l2448">            return (off &gt;= 0) ? Bits.getBoolean(primVals, off) : val;</span><a href="#l2448"></a>
<span id="l2449">        }</span><a href="#l2449"></a>
<span id="l2450"></span><a href="#l2450"></a>
<span id="l2451">        public byte get(String name, byte val) throws IOException {</span><a href="#l2451"></a>
<span id="l2452">            int off = getFieldOffset(name, Byte.TYPE);</span><a href="#l2452"></a>
<span id="l2453">            return (off &gt;= 0) ? primVals[off] : val;</span><a href="#l2453"></a>
<span id="l2454">        }</span><a href="#l2454"></a>
<span id="l2455"></span><a href="#l2455"></a>
<span id="l2456">        public char get(String name, char val) throws IOException {</span><a href="#l2456"></a>
<span id="l2457">            int off = getFieldOffset(name, Character.TYPE);</span><a href="#l2457"></a>
<span id="l2458">            return (off &gt;= 0) ? Bits.getChar(primVals, off) : val;</span><a href="#l2458"></a>
<span id="l2459">        }</span><a href="#l2459"></a>
<span id="l2460"></span><a href="#l2460"></a>
<span id="l2461">        public short get(String name, short val) throws IOException {</span><a href="#l2461"></a>
<span id="l2462">            int off = getFieldOffset(name, Short.TYPE);</span><a href="#l2462"></a>
<span id="l2463">            return (off &gt;= 0) ? Bits.getShort(primVals, off) : val;</span><a href="#l2463"></a>
<span id="l2464">        }</span><a href="#l2464"></a>
<span id="l2465"></span><a href="#l2465"></a>
<span id="l2466">        public int get(String name, int val) throws IOException {</span><a href="#l2466"></a>
<span id="l2467">            int off = getFieldOffset(name, Integer.TYPE);</span><a href="#l2467"></a>
<span id="l2468">            return (off &gt;= 0) ? Bits.getInt(primVals, off) : val;</span><a href="#l2468"></a>
<span id="l2469">        }</span><a href="#l2469"></a>
<span id="l2470"></span><a href="#l2470"></a>
<span id="l2471">        public float get(String name, float val) throws IOException {</span><a href="#l2471"></a>
<span id="l2472">            int off = getFieldOffset(name, Float.TYPE);</span><a href="#l2472"></a>
<span id="l2473">            return (off &gt;= 0) ? Bits.getFloat(primVals, off) : val;</span><a href="#l2473"></a>
<span id="l2474">        }</span><a href="#l2474"></a>
<span id="l2475"></span><a href="#l2475"></a>
<span id="l2476">        public long get(String name, long val) throws IOException {</span><a href="#l2476"></a>
<span id="l2477">            int off = getFieldOffset(name, Long.TYPE);</span><a href="#l2477"></a>
<span id="l2478">            return (off &gt;= 0) ? Bits.getLong(primVals, off) : val;</span><a href="#l2478"></a>
<span id="l2479">        }</span><a href="#l2479"></a>
<span id="l2480"></span><a href="#l2480"></a>
<span id="l2481">        public double get(String name, double val) throws IOException {</span><a href="#l2481"></a>
<span id="l2482">            int off = getFieldOffset(name, Double.TYPE);</span><a href="#l2482"></a>
<span id="l2483">            return (off &gt;= 0) ? Bits.getDouble(primVals, off) : val;</span><a href="#l2483"></a>
<span id="l2484">        }</span><a href="#l2484"></a>
<span id="l2485"></span><a href="#l2485"></a>
<span id="l2486">        public Object get(String name, Object val) throws IOException {</span><a href="#l2486"></a>
<span id="l2487">            int off = getFieldOffset(name, Object.class);</span><a href="#l2487"></a>
<span id="l2488">            if (off &gt;= 0) {</span><a href="#l2488"></a>
<span id="l2489">                int objHandle = objHandles[off];</span><a href="#l2489"></a>
<span id="l2490">                handles.markDependency(passHandle, objHandle);</span><a href="#l2490"></a>
<span id="l2491">                return (handles.lookupException(objHandle) == null) ?</span><a href="#l2491"></a>
<span id="l2492">                    objVals[off] : null;</span><a href="#l2492"></a>
<span id="l2493">            } else {</span><a href="#l2493"></a>
<span id="l2494">                return val;</span><a href="#l2494"></a>
<span id="l2495">            }</span><a href="#l2495"></a>
<span id="l2496">        }</span><a href="#l2496"></a>
<span id="l2497"></span><a href="#l2497"></a>
<span id="l2498">        /**</span><a href="#l2498"></a>
<span id="l2499">         * Reads primitive and object field values from stream.</span><a href="#l2499"></a>
<span id="l2500">         */</span><a href="#l2500"></a>
<span id="l2501">        void readFields() throws IOException {</span><a href="#l2501"></a>
<span id="l2502">            bin.readFully(primVals, 0, primVals.length, false);</span><a href="#l2502"></a>
<span id="l2503"></span><a href="#l2503"></a>
<span id="l2504">            int oldHandle = passHandle;</span><a href="#l2504"></a>
<span id="l2505">            ObjectStreamField[] fields = desc.getFields(false);</span><a href="#l2505"></a>
<span id="l2506">            int numPrimFields = fields.length - objVals.length;</span><a href="#l2506"></a>
<span id="l2507">            for (int i = 0; i &lt; objVals.length; i++) {</span><a href="#l2507"></a>
<span id="l2508">                objVals[i] =</span><a href="#l2508"></a>
<span id="l2509">                    readObject0(Object.class, fields[numPrimFields + i].isUnshared());</span><a href="#l2509"></a>
<span id="l2510">                objHandles[i] = passHandle;</span><a href="#l2510"></a>
<span id="l2511">            }</span><a href="#l2511"></a>
<span id="l2512">            passHandle = oldHandle;</span><a href="#l2512"></a>
<span id="l2513">        }</span><a href="#l2513"></a>
<span id="l2514"></span><a href="#l2514"></a>
<span id="l2515">        /**</span><a href="#l2515"></a>
<span id="l2516">         * Returns offset of field with given name and type.  A specified type</span><a href="#l2516"></a>
<span id="l2517">         * of null matches all types, Object.class matches all non-primitive</span><a href="#l2517"></a>
<span id="l2518">         * types, and any other non-null type matches assignable types only.</span><a href="#l2518"></a>
<span id="l2519">         * If no matching field is found in the (incoming) class</span><a href="#l2519"></a>
<span id="l2520">         * descriptor but a matching field is present in the associated local</span><a href="#l2520"></a>
<span id="l2521">         * class descriptor, returns -1.  Throws IllegalArgumentException if</span><a href="#l2521"></a>
<span id="l2522">         * neither incoming nor local class descriptor contains a match.</span><a href="#l2522"></a>
<span id="l2523">         */</span><a href="#l2523"></a>
<span id="l2524">        private int getFieldOffset(String name, Class&lt;?&gt; type) {</span><a href="#l2524"></a>
<span id="l2525">            ObjectStreamField field = desc.getField(name, type);</span><a href="#l2525"></a>
<span id="l2526">            if (field != null) {</span><a href="#l2526"></a>
<span id="l2527">                return field.getOffset();</span><a href="#l2527"></a>
<span id="l2528">            } else if (desc.getLocalDesc().getField(name, type) != null) {</span><a href="#l2528"></a>
<span id="l2529">                return -1;</span><a href="#l2529"></a>
<span id="l2530">            } else {</span><a href="#l2530"></a>
<span id="l2531">                throw new IllegalArgumentException(&quot;no such field &quot; + name +</span><a href="#l2531"></a>
<span id="l2532">                                                   &quot; with type &quot; + type);</span><a href="#l2532"></a>
<span id="l2533">            }</span><a href="#l2533"></a>
<span id="l2534">        }</span><a href="#l2534"></a>
<span id="l2535">    }</span><a href="#l2535"></a>
<span id="l2536"></span><a href="#l2536"></a>
<span id="l2537">    /**</span><a href="#l2537"></a>
<span id="l2538">     * Prioritized list of callbacks to be performed once object graph has been</span><a href="#l2538"></a>
<span id="l2539">     * completely deserialized.</span><a href="#l2539"></a>
<span id="l2540">     */</span><a href="#l2540"></a>
<span id="l2541">    private static class ValidationList {</span><a href="#l2541"></a>
<span id="l2542"></span><a href="#l2542"></a>
<span id="l2543">        private static class Callback {</span><a href="#l2543"></a>
<span id="l2544">            final ObjectInputValidation obj;</span><a href="#l2544"></a>
<span id="l2545">            final int priority;</span><a href="#l2545"></a>
<span id="l2546">            Callback next;</span><a href="#l2546"></a>
<span id="l2547">            final AccessControlContext acc;</span><a href="#l2547"></a>
<span id="l2548"></span><a href="#l2548"></a>
<span id="l2549">            Callback(ObjectInputValidation obj, int priority, Callback next,</span><a href="#l2549"></a>
<span id="l2550">                AccessControlContext acc)</span><a href="#l2550"></a>
<span id="l2551">            {</span><a href="#l2551"></a>
<span id="l2552">                this.obj = obj;</span><a href="#l2552"></a>
<span id="l2553">                this.priority = priority;</span><a href="#l2553"></a>
<span id="l2554">                this.next = next;</span><a href="#l2554"></a>
<span id="l2555">                this.acc = acc;</span><a href="#l2555"></a>
<span id="l2556">            }</span><a href="#l2556"></a>
<span id="l2557">        }</span><a href="#l2557"></a>
<span id="l2558"></span><a href="#l2558"></a>
<span id="l2559">        /** linked list of callbacks */</span><a href="#l2559"></a>
<span id="l2560">        private Callback list;</span><a href="#l2560"></a>
<span id="l2561"></span><a href="#l2561"></a>
<span id="l2562">        /**</span><a href="#l2562"></a>
<span id="l2563">         * Creates new (empty) ValidationList.</span><a href="#l2563"></a>
<span id="l2564">         */</span><a href="#l2564"></a>
<span id="l2565">        ValidationList() {</span><a href="#l2565"></a>
<span id="l2566">        }</span><a href="#l2566"></a>
<span id="l2567"></span><a href="#l2567"></a>
<span id="l2568">        /**</span><a href="#l2568"></a>
<span id="l2569">         * Registers callback.  Throws InvalidObjectException if callback</span><a href="#l2569"></a>
<span id="l2570">         * object is null.</span><a href="#l2570"></a>
<span id="l2571">         */</span><a href="#l2571"></a>
<span id="l2572">        void register(ObjectInputValidation obj, int priority)</span><a href="#l2572"></a>
<span id="l2573">            throws InvalidObjectException</span><a href="#l2573"></a>
<span id="l2574">        {</span><a href="#l2574"></a>
<span id="l2575">            if (obj == null) {</span><a href="#l2575"></a>
<span id="l2576">                throw new InvalidObjectException(&quot;null callback&quot;);</span><a href="#l2576"></a>
<span id="l2577">            }</span><a href="#l2577"></a>
<span id="l2578"></span><a href="#l2578"></a>
<span id="l2579">            Callback prev = null, cur = list;</span><a href="#l2579"></a>
<span id="l2580">            while (cur != null &amp;&amp; priority &lt; cur.priority) {</span><a href="#l2580"></a>
<span id="l2581">                prev = cur;</span><a href="#l2581"></a>
<span id="l2582">                cur = cur.next;</span><a href="#l2582"></a>
<span id="l2583">            }</span><a href="#l2583"></a>
<span id="l2584">            AccessControlContext acc = AccessController.getContext();</span><a href="#l2584"></a>
<span id="l2585">            if (prev != null) {</span><a href="#l2585"></a>
<span id="l2586">                prev.next = new Callback(obj, priority, cur, acc);</span><a href="#l2586"></a>
<span id="l2587">            } else {</span><a href="#l2587"></a>
<span id="l2588">                list = new Callback(obj, priority, list, acc);</span><a href="#l2588"></a>
<span id="l2589">            }</span><a href="#l2589"></a>
<span id="l2590">        }</span><a href="#l2590"></a>
<span id="l2591"></span><a href="#l2591"></a>
<span id="l2592">        /**</span><a href="#l2592"></a>
<span id="l2593">         * Invokes all registered callbacks and clears the callback list.</span><a href="#l2593"></a>
<span id="l2594">         * Callbacks with higher priorities are called first; those with equal</span><a href="#l2594"></a>
<span id="l2595">         * priorities may be called in any order.  If any of the callbacks</span><a href="#l2595"></a>
<span id="l2596">         * throws an InvalidObjectException, the callback process is terminated</span><a href="#l2596"></a>
<span id="l2597">         * and the exception propagated upwards.</span><a href="#l2597"></a>
<span id="l2598">         */</span><a href="#l2598"></a>
<span id="l2599">        void doCallbacks() throws InvalidObjectException {</span><a href="#l2599"></a>
<span id="l2600">            try {</span><a href="#l2600"></a>
<span id="l2601">                while (list != null) {</span><a href="#l2601"></a>
<span id="l2602">                    AccessController.doPrivileged(</span><a href="#l2602"></a>
<span id="l2603">                        new PrivilegedExceptionAction&lt;Void&gt;()</span><a href="#l2603"></a>
<span id="l2604">                    {</span><a href="#l2604"></a>
<span id="l2605">                        public Void run() throws InvalidObjectException {</span><a href="#l2605"></a>
<span id="l2606">                            list.obj.validateObject();</span><a href="#l2606"></a>
<span id="l2607">                            return null;</span><a href="#l2607"></a>
<span id="l2608">                        }</span><a href="#l2608"></a>
<span id="l2609">                    }, list.acc);</span><a href="#l2609"></a>
<span id="l2610">                    list = list.next;</span><a href="#l2610"></a>
<span id="l2611">                }</span><a href="#l2611"></a>
<span id="l2612">            } catch (PrivilegedActionException ex) {</span><a href="#l2612"></a>
<span id="l2613">                list = null;</span><a href="#l2613"></a>
<span id="l2614">                throw (InvalidObjectException) ex.getException();</span><a href="#l2614"></a>
<span id="l2615">            }</span><a href="#l2615"></a>
<span id="l2616">        }</span><a href="#l2616"></a>
<span id="l2617"></span><a href="#l2617"></a>
<span id="l2618">        /**</span><a href="#l2618"></a>
<span id="l2619">         * Resets the callback list to its initial (empty) state.</span><a href="#l2619"></a>
<span id="l2620">         */</span><a href="#l2620"></a>
<span id="l2621">        public void clear() {</span><a href="#l2621"></a>
<span id="l2622">            list = null;</span><a href="#l2622"></a>
<span id="l2623">        }</span><a href="#l2623"></a>
<span id="l2624">    }</span><a href="#l2624"></a>
<span id="l2625"></span><a href="#l2625"></a>
<span id="l2626">    /**</span><a href="#l2626"></a>
<span id="l2627">     * Hold a snapshot of values to be passed to an ObjectInputFilter.</span><a href="#l2627"></a>
<span id="l2628">     */</span><a href="#l2628"></a>
<span id="l2629">    static class FilterValues implements ObjectInputFilter.FilterInfo {</span><a href="#l2629"></a>
<span id="l2630">        final Class&lt;?&gt; clazz;</span><a href="#l2630"></a>
<span id="l2631">        final long arrayLength;</span><a href="#l2631"></a>
<span id="l2632">        final long totalObjectRefs;</span><a href="#l2632"></a>
<span id="l2633">        final long depth;</span><a href="#l2633"></a>
<span id="l2634">        final long streamBytes;</span><a href="#l2634"></a>
<span id="l2635"></span><a href="#l2635"></a>
<span id="l2636">        public FilterValues(Class&lt;?&gt; clazz, long arrayLength, long totalObjectRefs,</span><a href="#l2636"></a>
<span id="l2637">                            long depth, long streamBytes) {</span><a href="#l2637"></a>
<span id="l2638">            this.clazz = clazz;</span><a href="#l2638"></a>
<span id="l2639">            this.arrayLength = arrayLength;</span><a href="#l2639"></a>
<span id="l2640">            this.totalObjectRefs = totalObjectRefs;</span><a href="#l2640"></a>
<span id="l2641">            this.depth = depth;</span><a href="#l2641"></a>
<span id="l2642">            this.streamBytes = streamBytes;</span><a href="#l2642"></a>
<span id="l2643">        }</span><a href="#l2643"></a>
<span id="l2644"></span><a href="#l2644"></a>
<span id="l2645">        @Override</span><a href="#l2645"></a>
<span id="l2646">        public Class&lt;?&gt; serialClass() {</span><a href="#l2646"></a>
<span id="l2647">            return clazz;</span><a href="#l2647"></a>
<span id="l2648">        }</span><a href="#l2648"></a>
<span id="l2649"></span><a href="#l2649"></a>
<span id="l2650">        @Override</span><a href="#l2650"></a>
<span id="l2651">        public long arrayLength() {</span><a href="#l2651"></a>
<span id="l2652">            return arrayLength;</span><a href="#l2652"></a>
<span id="l2653">        }</span><a href="#l2653"></a>
<span id="l2654"></span><a href="#l2654"></a>
<span id="l2655">        @Override</span><a href="#l2655"></a>
<span id="l2656">        public long references() {</span><a href="#l2656"></a>
<span id="l2657">            return totalObjectRefs;</span><a href="#l2657"></a>
<span id="l2658">        }</span><a href="#l2658"></a>
<span id="l2659"></span><a href="#l2659"></a>
<span id="l2660">        @Override</span><a href="#l2660"></a>
<span id="l2661">        public long depth() {</span><a href="#l2661"></a>
<span id="l2662">            return depth;</span><a href="#l2662"></a>
<span id="l2663">        }</span><a href="#l2663"></a>
<span id="l2664"></span><a href="#l2664"></a>
<span id="l2665">        @Override</span><a href="#l2665"></a>
<span id="l2666">        public long streamBytes() {</span><a href="#l2666"></a>
<span id="l2667">            return streamBytes;</span><a href="#l2667"></a>
<span id="l2668">        }</span><a href="#l2668"></a>
<span id="l2669">    }</span><a href="#l2669"></a>
<span id="l2670"></span><a href="#l2670"></a>
<span id="l2671">    /**</span><a href="#l2671"></a>
<span id="l2672">     * Input stream supporting single-byte peek operations.</span><a href="#l2672"></a>
<span id="l2673">     */</span><a href="#l2673"></a>
<span id="l2674">    private static class PeekInputStream extends InputStream {</span><a href="#l2674"></a>
<span id="l2675"></span><a href="#l2675"></a>
<span id="l2676">        /** underlying stream */</span><a href="#l2676"></a>
<span id="l2677">        private final InputStream in;</span><a href="#l2677"></a>
<span id="l2678">        /** peeked byte */</span><a href="#l2678"></a>
<span id="l2679">        private int peekb = -1;</span><a href="#l2679"></a>
<span id="l2680">        /** total bytes read from the stream */</span><a href="#l2680"></a>
<span id="l2681">        private long totalBytesRead = 0;</span><a href="#l2681"></a>
<span id="l2682"></span><a href="#l2682"></a>
<span id="l2683">        /**</span><a href="#l2683"></a>
<span id="l2684">         * Creates new PeekInputStream on top of given underlying stream.</span><a href="#l2684"></a>
<span id="l2685">         */</span><a href="#l2685"></a>
<span id="l2686">        PeekInputStream(InputStream in) {</span><a href="#l2686"></a>
<span id="l2687">            this.in = in;</span><a href="#l2687"></a>
<span id="l2688">        }</span><a href="#l2688"></a>
<span id="l2689"></span><a href="#l2689"></a>
<span id="l2690">        /**</span><a href="#l2690"></a>
<span id="l2691">         * Peeks at next byte value in stream.  Similar to read(), except</span><a href="#l2691"></a>
<span id="l2692">         * that it does not consume the read value.</span><a href="#l2692"></a>
<span id="l2693">         */</span><a href="#l2693"></a>
<span id="l2694">        int peek() throws IOException {</span><a href="#l2694"></a>
<span id="l2695">            if (peekb &gt;= 0) {</span><a href="#l2695"></a>
<span id="l2696">                return peekb;</span><a href="#l2696"></a>
<span id="l2697">            }</span><a href="#l2697"></a>
<span id="l2698">            peekb = in.read();</span><a href="#l2698"></a>
<span id="l2699">            totalBytesRead += peekb &gt;= 0 ? 1 : 0;</span><a href="#l2699"></a>
<span id="l2700">            return peekb;</span><a href="#l2700"></a>
<span id="l2701">        }</span><a href="#l2701"></a>
<span id="l2702"></span><a href="#l2702"></a>
<span id="l2703">        public int read() throws IOException {</span><a href="#l2703"></a>
<span id="l2704">            if (peekb &gt;= 0) {</span><a href="#l2704"></a>
<span id="l2705">                int v = peekb;</span><a href="#l2705"></a>
<span id="l2706">                peekb = -1;</span><a href="#l2706"></a>
<span id="l2707">                return v;</span><a href="#l2707"></a>
<span id="l2708">            } else {</span><a href="#l2708"></a>
<span id="l2709">                int nbytes = in.read();</span><a href="#l2709"></a>
<span id="l2710">                totalBytesRead += nbytes &gt;= 0 ? 1 : 0;</span><a href="#l2710"></a>
<span id="l2711">                return nbytes;</span><a href="#l2711"></a>
<span id="l2712">            }</span><a href="#l2712"></a>
<span id="l2713">        }</span><a href="#l2713"></a>
<span id="l2714"></span><a href="#l2714"></a>
<span id="l2715">        public int read(byte[] b, int off, int len) throws IOException {</span><a href="#l2715"></a>
<span id="l2716">            int nbytes;</span><a href="#l2716"></a>
<span id="l2717">            if (len == 0) {</span><a href="#l2717"></a>
<span id="l2718">                return 0;</span><a href="#l2718"></a>
<span id="l2719">            } else if (peekb &lt; 0) {</span><a href="#l2719"></a>
<span id="l2720">                nbytes = in.read(b, off, len);</span><a href="#l2720"></a>
<span id="l2721">                totalBytesRead += nbytes &gt;= 0 ? nbytes : 0;</span><a href="#l2721"></a>
<span id="l2722">                return nbytes;</span><a href="#l2722"></a>
<span id="l2723">            } else {</span><a href="#l2723"></a>
<span id="l2724">                b[off++] = (byte) peekb;</span><a href="#l2724"></a>
<span id="l2725">                len--;</span><a href="#l2725"></a>
<span id="l2726">                peekb = -1;</span><a href="#l2726"></a>
<span id="l2727">                nbytes = in.read(b, off, len);</span><a href="#l2727"></a>
<span id="l2728">                totalBytesRead += nbytes &gt;= 0 ? nbytes : 0;</span><a href="#l2728"></a>
<span id="l2729">                return (nbytes &gt;= 0) ? (nbytes + 1) : 1;</span><a href="#l2729"></a>
<span id="l2730">            }</span><a href="#l2730"></a>
<span id="l2731">        }</span><a href="#l2731"></a>
<span id="l2732"></span><a href="#l2732"></a>
<span id="l2733">        void readFully(byte[] b, int off, int len) throws IOException {</span><a href="#l2733"></a>
<span id="l2734">            int n = 0;</span><a href="#l2734"></a>
<span id="l2735">            while (n &lt; len) {</span><a href="#l2735"></a>
<span id="l2736">                int count = read(b, off + n, len - n);</span><a href="#l2736"></a>
<span id="l2737">                if (count &lt; 0) {</span><a href="#l2737"></a>
<span id="l2738">                    throw new EOFException();</span><a href="#l2738"></a>
<span id="l2739">                }</span><a href="#l2739"></a>
<span id="l2740">                n += count;</span><a href="#l2740"></a>
<span id="l2741">            }</span><a href="#l2741"></a>
<span id="l2742">        }</span><a href="#l2742"></a>
<span id="l2743"></span><a href="#l2743"></a>
<span id="l2744">        public long skip(long n) throws IOException {</span><a href="#l2744"></a>
<span id="l2745">            if (n &lt;= 0) {</span><a href="#l2745"></a>
<span id="l2746">                return 0;</span><a href="#l2746"></a>
<span id="l2747">            }</span><a href="#l2747"></a>
<span id="l2748">            int skipped = 0;</span><a href="#l2748"></a>
<span id="l2749">            if (peekb &gt;= 0) {</span><a href="#l2749"></a>
<span id="l2750">                peekb = -1;</span><a href="#l2750"></a>
<span id="l2751">                skipped++;</span><a href="#l2751"></a>
<span id="l2752">                n--;</span><a href="#l2752"></a>
<span id="l2753">            }</span><a href="#l2753"></a>
<span id="l2754">            n = skipped + in.skip(n);</span><a href="#l2754"></a>
<span id="l2755">            totalBytesRead += n;</span><a href="#l2755"></a>
<span id="l2756">            return n;</span><a href="#l2756"></a>
<span id="l2757">        }</span><a href="#l2757"></a>
<span id="l2758"></span><a href="#l2758"></a>
<span id="l2759">        public int available() throws IOException {</span><a href="#l2759"></a>
<span id="l2760">            return in.available() + ((peekb &gt;= 0) ? 1 : 0);</span><a href="#l2760"></a>
<span id="l2761">        }</span><a href="#l2761"></a>
<span id="l2762"></span><a href="#l2762"></a>
<span id="l2763">        public void close() throws IOException {</span><a href="#l2763"></a>
<span id="l2764">            in.close();</span><a href="#l2764"></a>
<span id="l2765">        }</span><a href="#l2765"></a>
<span id="l2766"></span><a href="#l2766"></a>
<span id="l2767">        public long getBytesRead() {</span><a href="#l2767"></a>
<span id="l2768">            return totalBytesRead;</span><a href="#l2768"></a>
<span id="l2769">        }</span><a href="#l2769"></a>
<span id="l2770">    }</span><a href="#l2770"></a>
<span id="l2771"></span><a href="#l2771"></a>
<span id="l2772">    /**</span><a href="#l2772"></a>
<span id="l2773">     * Input stream with two modes: in default mode, inputs data written in the</span><a href="#l2773"></a>
<span id="l2774">     * same format as DataOutputStream; in &quot;block data&quot; mode, inputs data</span><a href="#l2774"></a>
<span id="l2775">     * bracketed by block data markers (see object serialization specification</span><a href="#l2775"></a>
<span id="l2776">     * for details).  Buffering depends on block data mode: when in default</span><a href="#l2776"></a>
<span id="l2777">     * mode, no data is buffered in advance; when in block data mode, all data</span><a href="#l2777"></a>
<span id="l2778">     * for the current data block is read in at once (and buffered).</span><a href="#l2778"></a>
<span id="l2779">     */</span><a href="#l2779"></a>
<span id="l2780">    private class BlockDataInputStream</span><a href="#l2780"></a>
<span id="l2781">        extends InputStream implements DataInput</span><a href="#l2781"></a>
<span id="l2782">    {</span><a href="#l2782"></a>
<span id="l2783">        /** maximum data block length */</span><a href="#l2783"></a>
<span id="l2784">        private static final int MAX_BLOCK_SIZE = 1024;</span><a href="#l2784"></a>
<span id="l2785">        /** maximum data block header length */</span><a href="#l2785"></a>
<span id="l2786">        private static final int MAX_HEADER_SIZE = 5;</span><a href="#l2786"></a>
<span id="l2787">        /** (tunable) length of char buffer (for reading strings) */</span><a href="#l2787"></a>
<span id="l2788">        private static final int CHAR_BUF_SIZE = 256;</span><a href="#l2788"></a>
<span id="l2789">        /** readBlockHeader() return value indicating header read may block */</span><a href="#l2789"></a>
<span id="l2790">        private static final int HEADER_BLOCKED = -2;</span><a href="#l2790"></a>
<span id="l2791"></span><a href="#l2791"></a>
<span id="l2792">        /** buffer for reading general/block data */</span><a href="#l2792"></a>
<span id="l2793">        private final byte[] buf = new byte[MAX_BLOCK_SIZE];</span><a href="#l2793"></a>
<span id="l2794">        /** buffer for reading block data headers */</span><a href="#l2794"></a>
<span id="l2795">        private final byte[] hbuf = new byte[MAX_HEADER_SIZE];</span><a href="#l2795"></a>
<span id="l2796">        /** char buffer for fast string reads */</span><a href="#l2796"></a>
<span id="l2797">        private final char[] cbuf = new char[CHAR_BUF_SIZE];</span><a href="#l2797"></a>
<span id="l2798"></span><a href="#l2798"></a>
<span id="l2799">        /** block data mode */</span><a href="#l2799"></a>
<span id="l2800">        private boolean blkmode = false;</span><a href="#l2800"></a>
<span id="l2801"></span><a href="#l2801"></a>
<span id="l2802">        // block data state fields; values meaningful only when blkmode true</span><a href="#l2802"></a>
<span id="l2803">        /** current offset into buf */</span><a href="#l2803"></a>
<span id="l2804">        private int pos = 0;</span><a href="#l2804"></a>
<span id="l2805">        /** end offset of valid data in buf, or -1 if no more block data */</span><a href="#l2805"></a>
<span id="l2806">        private int end = -1;</span><a href="#l2806"></a>
<span id="l2807">        /** number of bytes in current block yet to be read from stream */</span><a href="#l2807"></a>
<span id="l2808">        private int unread = 0;</span><a href="#l2808"></a>
<span id="l2809"></span><a href="#l2809"></a>
<span id="l2810">        /** underlying stream (wrapped in peekable filter stream) */</span><a href="#l2810"></a>
<span id="l2811">        private final PeekInputStream in;</span><a href="#l2811"></a>
<span id="l2812">        /** loopback stream (for data reads that span data blocks) */</span><a href="#l2812"></a>
<span id="l2813">        private final DataInputStream din;</span><a href="#l2813"></a>
<span id="l2814"></span><a href="#l2814"></a>
<span id="l2815">        /**</span><a href="#l2815"></a>
<span id="l2816">         * Creates new BlockDataInputStream on top of given underlying stream.</span><a href="#l2816"></a>
<span id="l2817">         * Block data mode is turned off by default.</span><a href="#l2817"></a>
<span id="l2818">         */</span><a href="#l2818"></a>
<span id="l2819">        BlockDataInputStream(InputStream in) {</span><a href="#l2819"></a>
<span id="l2820">            this.in = new PeekInputStream(in);</span><a href="#l2820"></a>
<span id="l2821">            din = new DataInputStream(this);</span><a href="#l2821"></a>
<span id="l2822">        }</span><a href="#l2822"></a>
<span id="l2823"></span><a href="#l2823"></a>
<span id="l2824">        /**</span><a href="#l2824"></a>
<span id="l2825">         * Sets block data mode to the given mode (true == on, false == off)</span><a href="#l2825"></a>
<span id="l2826">         * and returns the previous mode value.  If the new mode is the same as</span><a href="#l2826"></a>
<span id="l2827">         * the old mode, no action is taken.  Throws IllegalStateException if</span><a href="#l2827"></a>
<span id="l2828">         * block data mode is being switched from on to off while unconsumed</span><a href="#l2828"></a>
<span id="l2829">         * block data is still present in the stream.</span><a href="#l2829"></a>
<span id="l2830">         */</span><a href="#l2830"></a>
<span id="l2831">        boolean setBlockDataMode(boolean newmode) throws IOException {</span><a href="#l2831"></a>
<span id="l2832">            if (blkmode == newmode) {</span><a href="#l2832"></a>
<span id="l2833">                return blkmode;</span><a href="#l2833"></a>
<span id="l2834">            }</span><a href="#l2834"></a>
<span id="l2835">            if (newmode) {</span><a href="#l2835"></a>
<span id="l2836">                pos = 0;</span><a href="#l2836"></a>
<span id="l2837">                end = 0;</span><a href="#l2837"></a>
<span id="l2838">                unread = 0;</span><a href="#l2838"></a>
<span id="l2839">            } else if (pos &lt; end) {</span><a href="#l2839"></a>
<span id="l2840">                throw new IllegalStateException(&quot;unread block data&quot;);</span><a href="#l2840"></a>
<span id="l2841">            }</span><a href="#l2841"></a>
<span id="l2842">            blkmode = newmode;</span><a href="#l2842"></a>
<span id="l2843">            return !blkmode;</span><a href="#l2843"></a>
<span id="l2844">        }</span><a href="#l2844"></a>
<span id="l2845"></span><a href="#l2845"></a>
<span id="l2846">        /**</span><a href="#l2846"></a>
<span id="l2847">         * Returns true if the stream is currently in block data mode, false</span><a href="#l2847"></a>
<span id="l2848">         * otherwise.</span><a href="#l2848"></a>
<span id="l2849">         */</span><a href="#l2849"></a>
<span id="l2850">        boolean getBlockDataMode() {</span><a href="#l2850"></a>
<span id="l2851">            return blkmode;</span><a href="#l2851"></a>
<span id="l2852">        }</span><a href="#l2852"></a>
<span id="l2853"></span><a href="#l2853"></a>
<span id="l2854">        /**</span><a href="#l2854"></a>
<span id="l2855">         * If in block data mode, skips to the end of the current group of data</span><a href="#l2855"></a>
<span id="l2856">         * blocks (but does not unset block data mode).  If not in block data</span><a href="#l2856"></a>
<span id="l2857">         * mode, throws an IllegalStateException.</span><a href="#l2857"></a>
<span id="l2858">         */</span><a href="#l2858"></a>
<span id="l2859">        void skipBlockData() throws IOException {</span><a href="#l2859"></a>
<span id="l2860">            if (!blkmode) {</span><a href="#l2860"></a>
<span id="l2861">                throw new IllegalStateException(&quot;not in block data mode&quot;);</span><a href="#l2861"></a>
<span id="l2862">            }</span><a href="#l2862"></a>
<span id="l2863">            while (end &gt;= 0) {</span><a href="#l2863"></a>
<span id="l2864">                refill();</span><a href="#l2864"></a>
<span id="l2865">            }</span><a href="#l2865"></a>
<span id="l2866">        }</span><a href="#l2866"></a>
<span id="l2867"></span><a href="#l2867"></a>
<span id="l2868">        /**</span><a href="#l2868"></a>
<span id="l2869">         * Attempts to read in the next block data header (if any).  If</span><a href="#l2869"></a>
<span id="l2870">         * canBlock is false and a full header cannot be read without possibly</span><a href="#l2870"></a>
<span id="l2871">         * blocking, returns HEADER_BLOCKED, else if the next element in the</span><a href="#l2871"></a>
<span id="l2872">         * stream is a block data header, returns the block data length</span><a href="#l2872"></a>
<span id="l2873">         * specified by the header, else returns -1.</span><a href="#l2873"></a>
<span id="l2874">         */</span><a href="#l2874"></a>
<span id="l2875">        private int readBlockHeader(boolean canBlock) throws IOException {</span><a href="#l2875"></a>
<span id="l2876">            if (defaultDataEnd) {</span><a href="#l2876"></a>
<span id="l2877">                /*</span><a href="#l2877"></a>
<span id="l2878">                 * Fix for 4360508: stream is currently at the end of a field</span><a href="#l2878"></a>
<span id="l2879">                 * value block written via default serialization; since there</span><a href="#l2879"></a>
<span id="l2880">                 * is no terminating TC_ENDBLOCKDATA tag, simulate</span><a href="#l2880"></a>
<span id="l2881">                 * end-of-custom-data behavior explicitly.</span><a href="#l2881"></a>
<span id="l2882">                 */</span><a href="#l2882"></a>
<span id="l2883">                return -1;</span><a href="#l2883"></a>
<span id="l2884">            }</span><a href="#l2884"></a>
<span id="l2885">            try {</span><a href="#l2885"></a>
<span id="l2886">                for (;;) {</span><a href="#l2886"></a>
<span id="l2887">                    int avail = canBlock ? Integer.MAX_VALUE : in.available();</span><a href="#l2887"></a>
<span id="l2888">                    if (avail == 0) {</span><a href="#l2888"></a>
<span id="l2889">                        return HEADER_BLOCKED;</span><a href="#l2889"></a>
<span id="l2890">                    }</span><a href="#l2890"></a>
<span id="l2891"></span><a href="#l2891"></a>
<span id="l2892">                    int tc = in.peek();</span><a href="#l2892"></a>
<span id="l2893">                    switch (tc) {</span><a href="#l2893"></a>
<span id="l2894">                        case TC_BLOCKDATA:</span><a href="#l2894"></a>
<span id="l2895">                            if (avail &lt; 2) {</span><a href="#l2895"></a>
<span id="l2896">                                return HEADER_BLOCKED;</span><a href="#l2896"></a>
<span id="l2897">                            }</span><a href="#l2897"></a>
<span id="l2898">                            in.readFully(hbuf, 0, 2);</span><a href="#l2898"></a>
<span id="l2899">                            return hbuf[1] &amp; 0xFF;</span><a href="#l2899"></a>
<span id="l2900"></span><a href="#l2900"></a>
<span id="l2901">                        case TC_BLOCKDATALONG:</span><a href="#l2901"></a>
<span id="l2902">                            if (avail &lt; 5) {</span><a href="#l2902"></a>
<span id="l2903">                                return HEADER_BLOCKED;</span><a href="#l2903"></a>
<span id="l2904">                            }</span><a href="#l2904"></a>
<span id="l2905">                            in.readFully(hbuf, 0, 5);</span><a href="#l2905"></a>
<span id="l2906">                            int len = Bits.getInt(hbuf, 1);</span><a href="#l2906"></a>
<span id="l2907">                            if (len &lt; 0) {</span><a href="#l2907"></a>
<span id="l2908">                                throw new StreamCorruptedException(</span><a href="#l2908"></a>
<span id="l2909">                                    &quot;illegal block data header length: &quot; +</span><a href="#l2909"></a>
<span id="l2910">                                    len);</span><a href="#l2910"></a>
<span id="l2911">                            }</span><a href="#l2911"></a>
<span id="l2912">                            return len;</span><a href="#l2912"></a>
<span id="l2913"></span><a href="#l2913"></a>
<span id="l2914">                        /*</span><a href="#l2914"></a>
<span id="l2915">                         * TC_RESETs may occur in between data blocks.</span><a href="#l2915"></a>
<span id="l2916">                         * Unfortunately, this case must be parsed at a lower</span><a href="#l2916"></a>
<span id="l2917">                         * level than other typecodes, since primitive data</span><a href="#l2917"></a>
<span id="l2918">                         * reads may span data blocks separated by a TC_RESET.</span><a href="#l2918"></a>
<span id="l2919">                         */</span><a href="#l2919"></a>
<span id="l2920">                        case TC_RESET:</span><a href="#l2920"></a>
<span id="l2921">                            in.read();</span><a href="#l2921"></a>
<span id="l2922">                            handleReset();</span><a href="#l2922"></a>
<span id="l2923">                            break;</span><a href="#l2923"></a>
<span id="l2924"></span><a href="#l2924"></a>
<span id="l2925">                        default:</span><a href="#l2925"></a>
<span id="l2926">                            if (tc &gt;= 0 &amp;&amp; (tc &lt; TC_BASE || tc &gt; TC_MAX)) {</span><a href="#l2926"></a>
<span id="l2927">                                throw new StreamCorruptedException(</span><a href="#l2927"></a>
<span id="l2928">                                    String.format(&quot;invalid type code: %02X&quot;,</span><a href="#l2928"></a>
<span id="l2929">                                    tc));</span><a href="#l2929"></a>
<span id="l2930">                            }</span><a href="#l2930"></a>
<span id="l2931">                            return -1;</span><a href="#l2931"></a>
<span id="l2932">                    }</span><a href="#l2932"></a>
<span id="l2933">                }</span><a href="#l2933"></a>
<span id="l2934">            } catch (EOFException ex) {</span><a href="#l2934"></a>
<span id="l2935">                throw new StreamCorruptedException(</span><a href="#l2935"></a>
<span id="l2936">                    &quot;unexpected EOF while reading block data header&quot;);</span><a href="#l2936"></a>
<span id="l2937">            }</span><a href="#l2937"></a>
<span id="l2938">        }</span><a href="#l2938"></a>
<span id="l2939"></span><a href="#l2939"></a>
<span id="l2940">        /**</span><a href="#l2940"></a>
<span id="l2941">         * Refills internal buffer buf with block data.  Any data in buf at the</span><a href="#l2941"></a>
<span id="l2942">         * time of the call is considered consumed.  Sets the pos, end, and</span><a href="#l2942"></a>
<span id="l2943">         * unread fields to reflect the new amount of available block data; if</span><a href="#l2943"></a>
<span id="l2944">         * the next element in the stream is not a data block, sets pos and</span><a href="#l2944"></a>
<span id="l2945">         * unread to 0 and end to -1.</span><a href="#l2945"></a>
<span id="l2946">         */</span><a href="#l2946"></a>
<span id="l2947">        private void refill() throws IOException {</span><a href="#l2947"></a>
<span id="l2948">            try {</span><a href="#l2948"></a>
<span id="l2949">                do {</span><a href="#l2949"></a>
<span id="l2950">                    pos = 0;</span><a href="#l2950"></a>
<span id="l2951">                    if (unread &gt; 0) {</span><a href="#l2951"></a>
<span id="l2952">                        int n =</span><a href="#l2952"></a>
<span id="l2953">                            in.read(buf, 0, Math.min(unread, MAX_BLOCK_SIZE));</span><a href="#l2953"></a>
<span id="l2954">                        if (n &gt;= 0) {</span><a href="#l2954"></a>
<span id="l2955">                            end = n;</span><a href="#l2955"></a>
<span id="l2956">                            unread -= n;</span><a href="#l2956"></a>
<span id="l2957">                        } else {</span><a href="#l2957"></a>
<span id="l2958">                            throw new StreamCorruptedException(</span><a href="#l2958"></a>
<span id="l2959">                                &quot;unexpected EOF in middle of data block&quot;);</span><a href="#l2959"></a>
<span id="l2960">                        }</span><a href="#l2960"></a>
<span id="l2961">                    } else {</span><a href="#l2961"></a>
<span id="l2962">                        int n = readBlockHeader(true);</span><a href="#l2962"></a>
<span id="l2963">                        if (n &gt;= 0) {</span><a href="#l2963"></a>
<span id="l2964">                            end = 0;</span><a href="#l2964"></a>
<span id="l2965">                            unread = n;</span><a href="#l2965"></a>
<span id="l2966">                        } else {</span><a href="#l2966"></a>
<span id="l2967">                            end = -1;</span><a href="#l2967"></a>
<span id="l2968">                            unread = 0;</span><a href="#l2968"></a>
<span id="l2969">                        }</span><a href="#l2969"></a>
<span id="l2970">                    }</span><a href="#l2970"></a>
<span id="l2971">                } while (pos == end);</span><a href="#l2971"></a>
<span id="l2972">            } catch (IOException ex) {</span><a href="#l2972"></a>
<span id="l2973">                pos = 0;</span><a href="#l2973"></a>
<span id="l2974">                end = -1;</span><a href="#l2974"></a>
<span id="l2975">                unread = 0;</span><a href="#l2975"></a>
<span id="l2976">                throw ex;</span><a href="#l2976"></a>
<span id="l2977">            }</span><a href="#l2977"></a>
<span id="l2978">        }</span><a href="#l2978"></a>
<span id="l2979"></span><a href="#l2979"></a>
<span id="l2980">        /**</span><a href="#l2980"></a>
<span id="l2981">         * If in block data mode, returns the number of unconsumed bytes</span><a href="#l2981"></a>
<span id="l2982">         * remaining in the current data block.  If not in block data mode,</span><a href="#l2982"></a>
<span id="l2983">         * throws an IllegalStateException.</span><a href="#l2983"></a>
<span id="l2984">         */</span><a href="#l2984"></a>
<span id="l2985">        int currentBlockRemaining() {</span><a href="#l2985"></a>
<span id="l2986">            if (blkmode) {</span><a href="#l2986"></a>
<span id="l2987">                return (end &gt;= 0) ? (end - pos) + unread : 0;</span><a href="#l2987"></a>
<span id="l2988">            } else {</span><a href="#l2988"></a>
<span id="l2989">                throw new IllegalStateException();</span><a href="#l2989"></a>
<span id="l2990">            }</span><a href="#l2990"></a>
<span id="l2991">        }</span><a href="#l2991"></a>
<span id="l2992"></span><a href="#l2992"></a>
<span id="l2993">        /**</span><a href="#l2993"></a>
<span id="l2994">         * Peeks at (but does not consume) and returns the next byte value in</span><a href="#l2994"></a>
<span id="l2995">         * the stream, or -1 if the end of the stream/block data (if in block</span><a href="#l2995"></a>
<span id="l2996">         * data mode) has been reached.</span><a href="#l2996"></a>
<span id="l2997">         */</span><a href="#l2997"></a>
<span id="l2998">        int peek() throws IOException {</span><a href="#l2998"></a>
<span id="l2999">            if (blkmode) {</span><a href="#l2999"></a>
<span id="l3000">                if (pos == end) {</span><a href="#l3000"></a>
<span id="l3001">                    refill();</span><a href="#l3001"></a>
<span id="l3002">                }</span><a href="#l3002"></a>
<span id="l3003">                return (end &gt;= 0) ? (buf[pos] &amp; 0xFF) : -1;</span><a href="#l3003"></a>
<span id="l3004">            } else {</span><a href="#l3004"></a>
<span id="l3005">                return in.peek();</span><a href="#l3005"></a>
<span id="l3006">            }</span><a href="#l3006"></a>
<span id="l3007">        }</span><a href="#l3007"></a>
<span id="l3008"></span><a href="#l3008"></a>
<span id="l3009">        /**</span><a href="#l3009"></a>
<span id="l3010">         * Peeks at (but does not consume) and returns the next byte value in</span><a href="#l3010"></a>
<span id="l3011">         * the stream, or throws EOFException if end of stream/block data has</span><a href="#l3011"></a>
<span id="l3012">         * been reached.</span><a href="#l3012"></a>
<span id="l3013">         */</span><a href="#l3013"></a>
<span id="l3014">        byte peekByte() throws IOException {</span><a href="#l3014"></a>
<span id="l3015">            int val = peek();</span><a href="#l3015"></a>
<span id="l3016">            if (val &lt; 0) {</span><a href="#l3016"></a>
<span id="l3017">                throw new EOFException();</span><a href="#l3017"></a>
<span id="l3018">            }</span><a href="#l3018"></a>
<span id="l3019">            return (byte) val;</span><a href="#l3019"></a>
<span id="l3020">        }</span><a href="#l3020"></a>
<span id="l3021"></span><a href="#l3021"></a>
<span id="l3022"></span><a href="#l3022"></a>
<span id="l3023">        /* ----------------- generic input stream methods ------------------ */</span><a href="#l3023"></a>
<span id="l3024">        /*</span><a href="#l3024"></a>
<span id="l3025">         * The following methods are equivalent to their counterparts in</span><a href="#l3025"></a>
<span id="l3026">         * InputStream, except that they interpret data block boundaries and</span><a href="#l3026"></a>
<span id="l3027">         * read the requested data from within data blocks when in block data</span><a href="#l3027"></a>
<span id="l3028">         * mode.</span><a href="#l3028"></a>
<span id="l3029">         */</span><a href="#l3029"></a>
<span id="l3030"></span><a href="#l3030"></a>
<span id="l3031">        public int read() throws IOException {</span><a href="#l3031"></a>
<span id="l3032">            if (blkmode) {</span><a href="#l3032"></a>
<span id="l3033">                if (pos == end) {</span><a href="#l3033"></a>
<span id="l3034">                    refill();</span><a href="#l3034"></a>
<span id="l3035">                }</span><a href="#l3035"></a>
<span id="l3036">                return (end &gt;= 0) ? (buf[pos++] &amp; 0xFF) : -1;</span><a href="#l3036"></a>
<span id="l3037">            } else {</span><a href="#l3037"></a>
<span id="l3038">                return in.read();</span><a href="#l3038"></a>
<span id="l3039">            }</span><a href="#l3039"></a>
<span id="l3040">        }</span><a href="#l3040"></a>
<span id="l3041"></span><a href="#l3041"></a>
<span id="l3042">        public int read(byte[] b, int off, int len) throws IOException {</span><a href="#l3042"></a>
<span id="l3043">            return read(b, off, len, false);</span><a href="#l3043"></a>
<span id="l3044">        }</span><a href="#l3044"></a>
<span id="l3045"></span><a href="#l3045"></a>
<span id="l3046">        public long skip(long len) throws IOException {</span><a href="#l3046"></a>
<span id="l3047">            long remain = len;</span><a href="#l3047"></a>
<span id="l3048">            while (remain &gt; 0) {</span><a href="#l3048"></a>
<span id="l3049">                if (blkmode) {</span><a href="#l3049"></a>
<span id="l3050">                    if (pos == end) {</span><a href="#l3050"></a>
<span id="l3051">                        refill();</span><a href="#l3051"></a>
<span id="l3052">                    }</span><a href="#l3052"></a>
<span id="l3053">                    if (end &lt; 0) {</span><a href="#l3053"></a>
<span id="l3054">                        break;</span><a href="#l3054"></a>
<span id="l3055">                    }</span><a href="#l3055"></a>
<span id="l3056">                    int nread = (int) Math.min(remain, end - pos);</span><a href="#l3056"></a>
<span id="l3057">                    remain -= nread;</span><a href="#l3057"></a>
<span id="l3058">                    pos += nread;</span><a href="#l3058"></a>
<span id="l3059">                } else {</span><a href="#l3059"></a>
<span id="l3060">                    int nread = (int) Math.min(remain, MAX_BLOCK_SIZE);</span><a href="#l3060"></a>
<span id="l3061">                    if ((nread = in.read(buf, 0, nread)) &lt; 0) {</span><a href="#l3061"></a>
<span id="l3062">                        break;</span><a href="#l3062"></a>
<span id="l3063">                    }</span><a href="#l3063"></a>
<span id="l3064">                    remain -= nread;</span><a href="#l3064"></a>
<span id="l3065">                }</span><a href="#l3065"></a>
<span id="l3066">            }</span><a href="#l3066"></a>
<span id="l3067">            return len - remain;</span><a href="#l3067"></a>
<span id="l3068">        }</span><a href="#l3068"></a>
<span id="l3069"></span><a href="#l3069"></a>
<span id="l3070">        public int available() throws IOException {</span><a href="#l3070"></a>
<span id="l3071">            if (blkmode) {</span><a href="#l3071"></a>
<span id="l3072">                if ((pos == end) &amp;&amp; (unread == 0)) {</span><a href="#l3072"></a>
<span id="l3073">                    int n;</span><a href="#l3073"></a>
<span id="l3074">                    while ((n = readBlockHeader(false)) == 0) ;</span><a href="#l3074"></a>
<span id="l3075">                    switch (n) {</span><a href="#l3075"></a>
<span id="l3076">                        case HEADER_BLOCKED:</span><a href="#l3076"></a>
<span id="l3077">                            break;</span><a href="#l3077"></a>
<span id="l3078"></span><a href="#l3078"></a>
<span id="l3079">                        case -1:</span><a href="#l3079"></a>
<span id="l3080">                            pos = 0;</span><a href="#l3080"></a>
<span id="l3081">                            end = -1;</span><a href="#l3081"></a>
<span id="l3082">                            break;</span><a href="#l3082"></a>
<span id="l3083"></span><a href="#l3083"></a>
<span id="l3084">                        default:</span><a href="#l3084"></a>
<span id="l3085">                            pos = 0;</span><a href="#l3085"></a>
<span id="l3086">                            end = 0;</span><a href="#l3086"></a>
<span id="l3087">                            unread = n;</span><a href="#l3087"></a>
<span id="l3088">                            break;</span><a href="#l3088"></a>
<span id="l3089">                    }</span><a href="#l3089"></a>
<span id="l3090">                }</span><a href="#l3090"></a>
<span id="l3091">                // avoid unnecessary call to in.available() if possible</span><a href="#l3091"></a>
<span id="l3092">                int unreadAvail = (unread &gt; 0) ?</span><a href="#l3092"></a>
<span id="l3093">                    Math.min(in.available(), unread) : 0;</span><a href="#l3093"></a>
<span id="l3094">                return (end &gt;= 0) ? (end - pos) + unreadAvail : 0;</span><a href="#l3094"></a>
<span id="l3095">            } else {</span><a href="#l3095"></a>
<span id="l3096">                return in.available();</span><a href="#l3096"></a>
<span id="l3097">            }</span><a href="#l3097"></a>
<span id="l3098">        }</span><a href="#l3098"></a>
<span id="l3099"></span><a href="#l3099"></a>
<span id="l3100">        public void close() throws IOException {</span><a href="#l3100"></a>
<span id="l3101">            if (blkmode) {</span><a href="#l3101"></a>
<span id="l3102">                pos = 0;</span><a href="#l3102"></a>
<span id="l3103">                end = -1;</span><a href="#l3103"></a>
<span id="l3104">                unread = 0;</span><a href="#l3104"></a>
<span id="l3105">            }</span><a href="#l3105"></a>
<span id="l3106">            in.close();</span><a href="#l3106"></a>
<span id="l3107">        }</span><a href="#l3107"></a>
<span id="l3108"></span><a href="#l3108"></a>
<span id="l3109">        /**</span><a href="#l3109"></a>
<span id="l3110">         * Attempts to read len bytes into byte array b at offset off.  Returns</span><a href="#l3110"></a>
<span id="l3111">         * the number of bytes read, or -1 if the end of stream/block data has</span><a href="#l3111"></a>
<span id="l3112">         * been reached.  If copy is true, reads values into an intermediate</span><a href="#l3112"></a>
<span id="l3113">         * buffer before copying them to b (to avoid exposing a reference to</span><a href="#l3113"></a>
<span id="l3114">         * b).</span><a href="#l3114"></a>
<span id="l3115">         */</span><a href="#l3115"></a>
<span id="l3116">        int read(byte[] b, int off, int len, boolean copy) throws IOException {</span><a href="#l3116"></a>
<span id="l3117">            if (len == 0) {</span><a href="#l3117"></a>
<span id="l3118">                return 0;</span><a href="#l3118"></a>
<span id="l3119">            } else if (blkmode) {</span><a href="#l3119"></a>
<span id="l3120">                if (pos == end) {</span><a href="#l3120"></a>
<span id="l3121">                    refill();</span><a href="#l3121"></a>
<span id="l3122">                }</span><a href="#l3122"></a>
<span id="l3123">                if (end &lt; 0) {</span><a href="#l3123"></a>
<span id="l3124">                    return -1;</span><a href="#l3124"></a>
<span id="l3125">                }</span><a href="#l3125"></a>
<span id="l3126">                int nread = Math.min(len, end - pos);</span><a href="#l3126"></a>
<span id="l3127">                System.arraycopy(buf, pos, b, off, nread);</span><a href="#l3127"></a>
<span id="l3128">                pos += nread;</span><a href="#l3128"></a>
<span id="l3129">                return nread;</span><a href="#l3129"></a>
<span id="l3130">            } else if (copy) {</span><a href="#l3130"></a>
<span id="l3131">                int nread = in.read(buf, 0, Math.min(len, MAX_BLOCK_SIZE));</span><a href="#l3131"></a>
<span id="l3132">                if (nread &gt; 0) {</span><a href="#l3132"></a>
<span id="l3133">                    System.arraycopy(buf, 0, b, off, nread);</span><a href="#l3133"></a>
<span id="l3134">                }</span><a href="#l3134"></a>
<span id="l3135">                return nread;</span><a href="#l3135"></a>
<span id="l3136">            } else {</span><a href="#l3136"></a>
<span id="l3137">                return in.read(b, off, len);</span><a href="#l3137"></a>
<span id="l3138">            }</span><a href="#l3138"></a>
<span id="l3139">        }</span><a href="#l3139"></a>
<span id="l3140"></span><a href="#l3140"></a>
<span id="l3141">        /* ----------------- primitive data input methods ------------------ */</span><a href="#l3141"></a>
<span id="l3142">        /*</span><a href="#l3142"></a>
<span id="l3143">         * The following methods are equivalent to their counterparts in</span><a href="#l3143"></a>
<span id="l3144">         * DataInputStream, except that they interpret data block boundaries</span><a href="#l3144"></a>
<span id="l3145">         * and read the requested data from within data blocks when in block</span><a href="#l3145"></a>
<span id="l3146">         * data mode.</span><a href="#l3146"></a>
<span id="l3147">         */</span><a href="#l3147"></a>
<span id="l3148"></span><a href="#l3148"></a>
<span id="l3149">        public void readFully(byte[] b) throws IOException {</span><a href="#l3149"></a>
<span id="l3150">            readFully(b, 0, b.length, false);</span><a href="#l3150"></a>
<span id="l3151">        }</span><a href="#l3151"></a>
<span id="l3152"></span><a href="#l3152"></a>
<span id="l3153">        public void readFully(byte[] b, int off, int len) throws IOException {</span><a href="#l3153"></a>
<span id="l3154">            readFully(b, off, len, false);</span><a href="#l3154"></a>
<span id="l3155">        }</span><a href="#l3155"></a>
<span id="l3156"></span><a href="#l3156"></a>
<span id="l3157">        public void readFully(byte[] b, int off, int len, boolean copy)</span><a href="#l3157"></a>
<span id="l3158">            throws IOException</span><a href="#l3158"></a>
<span id="l3159">        {</span><a href="#l3159"></a>
<span id="l3160">            while (len &gt; 0) {</span><a href="#l3160"></a>
<span id="l3161">                int n = read(b, off, len, copy);</span><a href="#l3161"></a>
<span id="l3162">                if (n &lt; 0) {</span><a href="#l3162"></a>
<span id="l3163">                    throw new EOFException();</span><a href="#l3163"></a>
<span id="l3164">                }</span><a href="#l3164"></a>
<span id="l3165">                off += n;</span><a href="#l3165"></a>
<span id="l3166">                len -= n;</span><a href="#l3166"></a>
<span id="l3167">            }</span><a href="#l3167"></a>
<span id="l3168">        }</span><a href="#l3168"></a>
<span id="l3169"></span><a href="#l3169"></a>
<span id="l3170">        public int skipBytes(int n) throws IOException {</span><a href="#l3170"></a>
<span id="l3171">            return din.skipBytes(n);</span><a href="#l3171"></a>
<span id="l3172">        }</span><a href="#l3172"></a>
<span id="l3173"></span><a href="#l3173"></a>
<span id="l3174">        public boolean readBoolean() throws IOException {</span><a href="#l3174"></a>
<span id="l3175">            int v = read();</span><a href="#l3175"></a>
<span id="l3176">            if (v &lt; 0) {</span><a href="#l3176"></a>
<span id="l3177">                throw new EOFException();</span><a href="#l3177"></a>
<span id="l3178">            }</span><a href="#l3178"></a>
<span id="l3179">            return (v != 0);</span><a href="#l3179"></a>
<span id="l3180">        }</span><a href="#l3180"></a>
<span id="l3181"></span><a href="#l3181"></a>
<span id="l3182">        public byte readByte() throws IOException {</span><a href="#l3182"></a>
<span id="l3183">            int v = read();</span><a href="#l3183"></a>
<span id="l3184">            if (v &lt; 0) {</span><a href="#l3184"></a>
<span id="l3185">                throw new EOFException();</span><a href="#l3185"></a>
<span id="l3186">            }</span><a href="#l3186"></a>
<span id="l3187">            return (byte) v;</span><a href="#l3187"></a>
<span id="l3188">        }</span><a href="#l3188"></a>
<span id="l3189"></span><a href="#l3189"></a>
<span id="l3190">        public int readUnsignedByte() throws IOException {</span><a href="#l3190"></a>
<span id="l3191">            int v = read();</span><a href="#l3191"></a>
<span id="l3192">            if (v &lt; 0) {</span><a href="#l3192"></a>
<span id="l3193">                throw new EOFException();</span><a href="#l3193"></a>
<span id="l3194">            }</span><a href="#l3194"></a>
<span id="l3195">            return v;</span><a href="#l3195"></a>
<span id="l3196">        }</span><a href="#l3196"></a>
<span id="l3197"></span><a href="#l3197"></a>
<span id="l3198">        public char readChar() throws IOException {</span><a href="#l3198"></a>
<span id="l3199">            if (!blkmode) {</span><a href="#l3199"></a>
<span id="l3200">                pos = 0;</span><a href="#l3200"></a>
<span id="l3201">                in.readFully(buf, 0, 2);</span><a href="#l3201"></a>
<span id="l3202">            } else if (end - pos &lt; 2) {</span><a href="#l3202"></a>
<span id="l3203">                return din.readChar();</span><a href="#l3203"></a>
<span id="l3204">            }</span><a href="#l3204"></a>
<span id="l3205">            char v = Bits.getChar(buf, pos);</span><a href="#l3205"></a>
<span id="l3206">            pos += 2;</span><a href="#l3206"></a>
<span id="l3207">            return v;</span><a href="#l3207"></a>
<span id="l3208">        }</span><a href="#l3208"></a>
<span id="l3209"></span><a href="#l3209"></a>
<span id="l3210">        public short readShort() throws IOException {</span><a href="#l3210"></a>
<span id="l3211">            if (!blkmode) {</span><a href="#l3211"></a>
<span id="l3212">                pos = 0;</span><a href="#l3212"></a>
<span id="l3213">                in.readFully(buf, 0, 2);</span><a href="#l3213"></a>
<span id="l3214">            } else if (end - pos &lt; 2) {</span><a href="#l3214"></a>
<span id="l3215">                return din.readShort();</span><a href="#l3215"></a>
<span id="l3216">            }</span><a href="#l3216"></a>
<span id="l3217">            short v = Bits.getShort(buf, pos);</span><a href="#l3217"></a>
<span id="l3218">            pos += 2;</span><a href="#l3218"></a>
<span id="l3219">            return v;</span><a href="#l3219"></a>
<span id="l3220">        }</span><a href="#l3220"></a>
<span id="l3221"></span><a href="#l3221"></a>
<span id="l3222">        public int readUnsignedShort() throws IOException {</span><a href="#l3222"></a>
<span id="l3223">            if (!blkmode) {</span><a href="#l3223"></a>
<span id="l3224">                pos = 0;</span><a href="#l3224"></a>
<span id="l3225">                in.readFully(buf, 0, 2);</span><a href="#l3225"></a>
<span id="l3226">            } else if (end - pos &lt; 2) {</span><a href="#l3226"></a>
<span id="l3227">                return din.readUnsignedShort();</span><a href="#l3227"></a>
<span id="l3228">            }</span><a href="#l3228"></a>
<span id="l3229">            int v = Bits.getShort(buf, pos) &amp; 0xFFFF;</span><a href="#l3229"></a>
<span id="l3230">            pos += 2;</span><a href="#l3230"></a>
<span id="l3231">            return v;</span><a href="#l3231"></a>
<span id="l3232">        }</span><a href="#l3232"></a>
<span id="l3233"></span><a href="#l3233"></a>
<span id="l3234">        public int readInt() throws IOException {</span><a href="#l3234"></a>
<span id="l3235">            if (!blkmode) {</span><a href="#l3235"></a>
<span id="l3236">                pos = 0;</span><a href="#l3236"></a>
<span id="l3237">                in.readFully(buf, 0, 4);</span><a href="#l3237"></a>
<span id="l3238">            } else if (end - pos &lt; 4) {</span><a href="#l3238"></a>
<span id="l3239">                return din.readInt();</span><a href="#l3239"></a>
<span id="l3240">            }</span><a href="#l3240"></a>
<span id="l3241">            int v = Bits.getInt(buf, pos);</span><a href="#l3241"></a>
<span id="l3242">            pos += 4;</span><a href="#l3242"></a>
<span id="l3243">            return v;</span><a href="#l3243"></a>
<span id="l3244">        }</span><a href="#l3244"></a>
<span id="l3245"></span><a href="#l3245"></a>
<span id="l3246">        public float readFloat() throws IOException {</span><a href="#l3246"></a>
<span id="l3247">            if (!blkmode) {</span><a href="#l3247"></a>
<span id="l3248">                pos = 0;</span><a href="#l3248"></a>
<span id="l3249">                in.readFully(buf, 0, 4);</span><a href="#l3249"></a>
<span id="l3250">            } else if (end - pos &lt; 4) {</span><a href="#l3250"></a>
<span id="l3251">                return din.readFloat();</span><a href="#l3251"></a>
<span id="l3252">            }</span><a href="#l3252"></a>
<span id="l3253">            float v = Bits.getFloat(buf, pos);</span><a href="#l3253"></a>
<span id="l3254">            pos += 4;</span><a href="#l3254"></a>
<span id="l3255">            return v;</span><a href="#l3255"></a>
<span id="l3256">        }</span><a href="#l3256"></a>
<span id="l3257"></span><a href="#l3257"></a>
<span id="l3258">        public long readLong() throws IOException {</span><a href="#l3258"></a>
<span id="l3259">            if (!blkmode) {</span><a href="#l3259"></a>
<span id="l3260">                pos = 0;</span><a href="#l3260"></a>
<span id="l3261">                in.readFully(buf, 0, 8);</span><a href="#l3261"></a>
<span id="l3262">            } else if (end - pos &lt; 8) {</span><a href="#l3262"></a>
<span id="l3263">                return din.readLong();</span><a href="#l3263"></a>
<span id="l3264">            }</span><a href="#l3264"></a>
<span id="l3265">            long v = Bits.getLong(buf, pos);</span><a href="#l3265"></a>
<span id="l3266">            pos += 8;</span><a href="#l3266"></a>
<span id="l3267">            return v;</span><a href="#l3267"></a>
<span id="l3268">        }</span><a href="#l3268"></a>
<span id="l3269"></span><a href="#l3269"></a>
<span id="l3270">        public double readDouble() throws IOException {</span><a href="#l3270"></a>
<span id="l3271">            if (!blkmode) {</span><a href="#l3271"></a>
<span id="l3272">                pos = 0;</span><a href="#l3272"></a>
<span id="l3273">                in.readFully(buf, 0, 8);</span><a href="#l3273"></a>
<span id="l3274">            } else if (end - pos &lt; 8) {</span><a href="#l3274"></a>
<span id="l3275">                return din.readDouble();</span><a href="#l3275"></a>
<span id="l3276">            }</span><a href="#l3276"></a>
<span id="l3277">            double v = Bits.getDouble(buf, pos);</span><a href="#l3277"></a>
<span id="l3278">            pos += 8;</span><a href="#l3278"></a>
<span id="l3279">            return v;</span><a href="#l3279"></a>
<span id="l3280">        }</span><a href="#l3280"></a>
<span id="l3281"></span><a href="#l3281"></a>
<span id="l3282">        public String readUTF() throws IOException {</span><a href="#l3282"></a>
<span id="l3283">            return readUTFBody(readUnsignedShort());</span><a href="#l3283"></a>
<span id="l3284">        }</span><a href="#l3284"></a>
<span id="l3285"></span><a href="#l3285"></a>
<span id="l3286">        @SuppressWarnings(&quot;deprecation&quot;)</span><a href="#l3286"></a>
<span id="l3287">        public String readLine() throws IOException {</span><a href="#l3287"></a>
<span id="l3288">            return din.readLine();      // deprecated, not worth optimizing</span><a href="#l3288"></a>
<span id="l3289">        }</span><a href="#l3289"></a>
<span id="l3290"></span><a href="#l3290"></a>
<span id="l3291">        /* -------------- primitive data array input methods --------------- */</span><a href="#l3291"></a>
<span id="l3292">        /*</span><a href="#l3292"></a>
<span id="l3293">         * The following methods read in spans of primitive data values.</span><a href="#l3293"></a>
<span id="l3294">         * Though equivalent to calling the corresponding primitive read</span><a href="#l3294"></a>
<span id="l3295">         * methods repeatedly, these methods are optimized for reading groups</span><a href="#l3295"></a>
<span id="l3296">         * of primitive data values more efficiently.</span><a href="#l3296"></a>
<span id="l3297">         */</span><a href="#l3297"></a>
<span id="l3298"></span><a href="#l3298"></a>
<span id="l3299">        void readBooleans(boolean[] v, int off, int len) throws IOException {</span><a href="#l3299"></a>
<span id="l3300">            int stop, endoff = off + len;</span><a href="#l3300"></a>
<span id="l3301">            while (off &lt; endoff) {</span><a href="#l3301"></a>
<span id="l3302">                if (!blkmode) {</span><a href="#l3302"></a>
<span id="l3303">                    int span = Math.min(endoff - off, MAX_BLOCK_SIZE);</span><a href="#l3303"></a>
<span id="l3304">                    in.readFully(buf, 0, span);</span><a href="#l3304"></a>
<span id="l3305">                    stop = off + span;</span><a href="#l3305"></a>
<span id="l3306">                    pos = 0;</span><a href="#l3306"></a>
<span id="l3307">                } else if (end - pos &lt; 1) {</span><a href="#l3307"></a>
<span id="l3308">                    v[off++] = din.readBoolean();</span><a href="#l3308"></a>
<span id="l3309">                    continue;</span><a href="#l3309"></a>
<span id="l3310">                } else {</span><a href="#l3310"></a>
<span id="l3311">                    stop = Math.min(endoff, off + end - pos);</span><a href="#l3311"></a>
<span id="l3312">                }</span><a href="#l3312"></a>
<span id="l3313"></span><a href="#l3313"></a>
<span id="l3314">                while (off &lt; stop) {</span><a href="#l3314"></a>
<span id="l3315">                    v[off++] = Bits.getBoolean(buf, pos++);</span><a href="#l3315"></a>
<span id="l3316">                }</span><a href="#l3316"></a>
<span id="l3317">            }</span><a href="#l3317"></a>
<span id="l3318">        }</span><a href="#l3318"></a>
<span id="l3319"></span><a href="#l3319"></a>
<span id="l3320">        void readChars(char[] v, int off, int len) throws IOException {</span><a href="#l3320"></a>
<span id="l3321">            int stop, endoff = off + len;</span><a href="#l3321"></a>
<span id="l3322">            while (off &lt; endoff) {</span><a href="#l3322"></a>
<span id="l3323">                if (!blkmode) {</span><a href="#l3323"></a>
<span id="l3324">                    int span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 1);</span><a href="#l3324"></a>
<span id="l3325">                    in.readFully(buf, 0, span &lt;&lt; 1);</span><a href="#l3325"></a>
<span id="l3326">                    stop = off + span;</span><a href="#l3326"></a>
<span id="l3327">                    pos = 0;</span><a href="#l3327"></a>
<span id="l3328">                } else if (end - pos &lt; 2) {</span><a href="#l3328"></a>
<span id="l3329">                    v[off++] = din.readChar();</span><a href="#l3329"></a>
<span id="l3330">                    continue;</span><a href="#l3330"></a>
<span id="l3331">                } else {</span><a href="#l3331"></a>
<span id="l3332">                    stop = Math.min(endoff, off + ((end - pos) &gt;&gt; 1));</span><a href="#l3332"></a>
<span id="l3333">                }</span><a href="#l3333"></a>
<span id="l3334"></span><a href="#l3334"></a>
<span id="l3335">                while (off &lt; stop) {</span><a href="#l3335"></a>
<span id="l3336">                    v[off++] = Bits.getChar(buf, pos);</span><a href="#l3336"></a>
<span id="l3337">                    pos += 2;</span><a href="#l3337"></a>
<span id="l3338">                }</span><a href="#l3338"></a>
<span id="l3339">            }</span><a href="#l3339"></a>
<span id="l3340">        }</span><a href="#l3340"></a>
<span id="l3341"></span><a href="#l3341"></a>
<span id="l3342">        void readShorts(short[] v, int off, int len) throws IOException {</span><a href="#l3342"></a>
<span id="l3343">            int stop, endoff = off + len;</span><a href="#l3343"></a>
<span id="l3344">            while (off &lt; endoff) {</span><a href="#l3344"></a>
<span id="l3345">                if (!blkmode) {</span><a href="#l3345"></a>
<span id="l3346">                    int span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 1);</span><a href="#l3346"></a>
<span id="l3347">                    in.readFully(buf, 0, span &lt;&lt; 1);</span><a href="#l3347"></a>
<span id="l3348">                    stop = off + span;</span><a href="#l3348"></a>
<span id="l3349">                    pos = 0;</span><a href="#l3349"></a>
<span id="l3350">                } else if (end - pos &lt; 2) {</span><a href="#l3350"></a>
<span id="l3351">                    v[off++] = din.readShort();</span><a href="#l3351"></a>
<span id="l3352">                    continue;</span><a href="#l3352"></a>
<span id="l3353">                } else {</span><a href="#l3353"></a>
<span id="l3354">                    stop = Math.min(endoff, off + ((end - pos) &gt;&gt; 1));</span><a href="#l3354"></a>
<span id="l3355">                }</span><a href="#l3355"></a>
<span id="l3356"></span><a href="#l3356"></a>
<span id="l3357">                while (off &lt; stop) {</span><a href="#l3357"></a>
<span id="l3358">                    v[off++] = Bits.getShort(buf, pos);</span><a href="#l3358"></a>
<span id="l3359">                    pos += 2;</span><a href="#l3359"></a>
<span id="l3360">                }</span><a href="#l3360"></a>
<span id="l3361">            }</span><a href="#l3361"></a>
<span id="l3362">        }</span><a href="#l3362"></a>
<span id="l3363"></span><a href="#l3363"></a>
<span id="l3364">        void readInts(int[] v, int off, int len) throws IOException {</span><a href="#l3364"></a>
<span id="l3365">            int stop, endoff = off + len;</span><a href="#l3365"></a>
<span id="l3366">            while (off &lt; endoff) {</span><a href="#l3366"></a>
<span id="l3367">                if (!blkmode) {</span><a href="#l3367"></a>
<span id="l3368">                    int span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 2);</span><a href="#l3368"></a>
<span id="l3369">                    in.readFully(buf, 0, span &lt;&lt; 2);</span><a href="#l3369"></a>
<span id="l3370">                    stop = off + span;</span><a href="#l3370"></a>
<span id="l3371">                    pos = 0;</span><a href="#l3371"></a>
<span id="l3372">                } else if (end - pos &lt; 4) {</span><a href="#l3372"></a>
<span id="l3373">                    v[off++] = din.readInt();</span><a href="#l3373"></a>
<span id="l3374">                    continue;</span><a href="#l3374"></a>
<span id="l3375">                } else {</span><a href="#l3375"></a>
<span id="l3376">                    stop = Math.min(endoff, off + ((end - pos) &gt;&gt; 2));</span><a href="#l3376"></a>
<span id="l3377">                }</span><a href="#l3377"></a>
<span id="l3378"></span><a href="#l3378"></a>
<span id="l3379">                while (off &lt; stop) {</span><a href="#l3379"></a>
<span id="l3380">                    v[off++] = Bits.getInt(buf, pos);</span><a href="#l3380"></a>
<span id="l3381">                    pos += 4;</span><a href="#l3381"></a>
<span id="l3382">                }</span><a href="#l3382"></a>
<span id="l3383">            }</span><a href="#l3383"></a>
<span id="l3384">        }</span><a href="#l3384"></a>
<span id="l3385"></span><a href="#l3385"></a>
<span id="l3386">        void readFloats(float[] v, int off, int len) throws IOException {</span><a href="#l3386"></a>
<span id="l3387">            int span, endoff = off + len;</span><a href="#l3387"></a>
<span id="l3388">            while (off &lt; endoff) {</span><a href="#l3388"></a>
<span id="l3389">                if (!blkmode) {</span><a href="#l3389"></a>
<span id="l3390">                    span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 2);</span><a href="#l3390"></a>
<span id="l3391">                    in.readFully(buf, 0, span &lt;&lt; 2);</span><a href="#l3391"></a>
<span id="l3392">                    pos = 0;</span><a href="#l3392"></a>
<span id="l3393">                } else if (end - pos &lt; 4) {</span><a href="#l3393"></a>
<span id="l3394">                    v[off++] = din.readFloat();</span><a href="#l3394"></a>
<span id="l3395">                    continue;</span><a href="#l3395"></a>
<span id="l3396">                } else {</span><a href="#l3396"></a>
<span id="l3397">                    span = Math.min(endoff - off, ((end - pos) &gt;&gt; 2));</span><a href="#l3397"></a>
<span id="l3398">                }</span><a href="#l3398"></a>
<span id="l3399"></span><a href="#l3399"></a>
<span id="l3400">                bytesToFloats(buf, pos, v, off, span);</span><a href="#l3400"></a>
<span id="l3401">                off += span;</span><a href="#l3401"></a>
<span id="l3402">                pos += span &lt;&lt; 2;</span><a href="#l3402"></a>
<span id="l3403">            }</span><a href="#l3403"></a>
<span id="l3404">        }</span><a href="#l3404"></a>
<span id="l3405"></span><a href="#l3405"></a>
<span id="l3406">        void readLongs(long[] v, int off, int len) throws IOException {</span><a href="#l3406"></a>
<span id="l3407">            int stop, endoff = off + len;</span><a href="#l3407"></a>
<span id="l3408">            while (off &lt; endoff) {</span><a href="#l3408"></a>
<span id="l3409">                if (!blkmode) {</span><a href="#l3409"></a>
<span id="l3410">                    int span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 3);</span><a href="#l3410"></a>
<span id="l3411">                    in.readFully(buf, 0, span &lt;&lt; 3);</span><a href="#l3411"></a>
<span id="l3412">                    stop = off + span;</span><a href="#l3412"></a>
<span id="l3413">                    pos = 0;</span><a href="#l3413"></a>
<span id="l3414">                } else if (end - pos &lt; 8) {</span><a href="#l3414"></a>
<span id="l3415">                    v[off++] = din.readLong();</span><a href="#l3415"></a>
<span id="l3416">                    continue;</span><a href="#l3416"></a>
<span id="l3417">                } else {</span><a href="#l3417"></a>
<span id="l3418">                    stop = Math.min(endoff, off + ((end - pos) &gt;&gt; 3));</span><a href="#l3418"></a>
<span id="l3419">                }</span><a href="#l3419"></a>
<span id="l3420"></span><a href="#l3420"></a>
<span id="l3421">                while (off &lt; stop) {</span><a href="#l3421"></a>
<span id="l3422">                    v[off++] = Bits.getLong(buf, pos);</span><a href="#l3422"></a>
<span id="l3423">                    pos += 8;</span><a href="#l3423"></a>
<span id="l3424">                }</span><a href="#l3424"></a>
<span id="l3425">            }</span><a href="#l3425"></a>
<span id="l3426">        }</span><a href="#l3426"></a>
<span id="l3427"></span><a href="#l3427"></a>
<span id="l3428">        void readDoubles(double[] v, int off, int len) throws IOException {</span><a href="#l3428"></a>
<span id="l3429">            int span, endoff = off + len;</span><a href="#l3429"></a>
<span id="l3430">            while (off &lt; endoff) {</span><a href="#l3430"></a>
<span id="l3431">                if (!blkmode) {</span><a href="#l3431"></a>
<span id="l3432">                    span = Math.min(endoff - off, MAX_BLOCK_SIZE &gt;&gt; 3);</span><a href="#l3432"></a>
<span id="l3433">                    in.readFully(buf, 0, span &lt;&lt; 3);</span><a href="#l3433"></a>
<span id="l3434">                    pos = 0;</span><a href="#l3434"></a>
<span id="l3435">                } else if (end - pos &lt; 8) {</span><a href="#l3435"></a>
<span id="l3436">                    v[off++] = din.readDouble();</span><a href="#l3436"></a>
<span id="l3437">                    continue;</span><a href="#l3437"></a>
<span id="l3438">                } else {</span><a href="#l3438"></a>
<span id="l3439">                    span = Math.min(endoff - off, ((end - pos) &gt;&gt; 3));</span><a href="#l3439"></a>
<span id="l3440">                }</span><a href="#l3440"></a>
<span id="l3441"></span><a href="#l3441"></a>
<span id="l3442">                bytesToDoubles(buf, pos, v, off, span);</span><a href="#l3442"></a>
<span id="l3443">                off += span;</span><a href="#l3443"></a>
<span id="l3444">                pos += span &lt;&lt; 3;</span><a href="#l3444"></a>
<span id="l3445">            }</span><a href="#l3445"></a>
<span id="l3446">        }</span><a href="#l3446"></a>
<span id="l3447"></span><a href="#l3447"></a>
<span id="l3448">        /**</span><a href="#l3448"></a>
<span id="l3449">         * Reads in string written in &quot;long&quot; UTF format.  &quot;Long&quot; UTF format is</span><a href="#l3449"></a>
<span id="l3450">         * identical to standard UTF, except that it uses an 8 byte header</span><a href="#l3450"></a>
<span id="l3451">         * (instead of the standard 2 bytes) to convey the UTF encoding length.</span><a href="#l3451"></a>
<span id="l3452">         */</span><a href="#l3452"></a>
<span id="l3453">        String readLongUTF() throws IOException {</span><a href="#l3453"></a>
<span id="l3454">            return readUTFBody(readLong());</span><a href="#l3454"></a>
<span id="l3455">        }</span><a href="#l3455"></a>
<span id="l3456"></span><a href="#l3456"></a>
<span id="l3457">        /**</span><a href="#l3457"></a>
<span id="l3458">         * Reads in the &quot;body&quot; (i.e., the UTF representation minus the 2-byte</span><a href="#l3458"></a>
<span id="l3459">         * or 8-byte length header) of a UTF encoding, which occupies the next</span><a href="#l3459"></a>
<span id="l3460">         * utflen bytes.</span><a href="#l3460"></a>
<span id="l3461">         */</span><a href="#l3461"></a>
<span id="l3462">        private String readUTFBody(long utflen) throws IOException {</span><a href="#l3462"></a>
<span id="l3463">            StringBuilder sbuf;</span><a href="#l3463"></a>
<span id="l3464">            if (utflen &gt; 0 &amp;&amp; utflen &lt; Integer.MAX_VALUE) {</span><a href="#l3464"></a>
<span id="l3465">                // a reasonable initial capacity based on the UTF length</span><a href="#l3465"></a>
<span id="l3466">                int initialCapacity = Math.min((int)utflen, 0xFFFF);</span><a href="#l3466"></a>
<span id="l3467">                sbuf = new StringBuilder(initialCapacity);</span><a href="#l3467"></a>
<span id="l3468">            } else {</span><a href="#l3468"></a>
<span id="l3469">                sbuf = new StringBuilder();</span><a href="#l3469"></a>
<span id="l3470">            }</span><a href="#l3470"></a>
<span id="l3471"></span><a href="#l3471"></a>
<span id="l3472">            if (!blkmode) {</span><a href="#l3472"></a>
<span id="l3473">                end = pos = 0;</span><a href="#l3473"></a>
<span id="l3474">            }</span><a href="#l3474"></a>
<span id="l3475"></span><a href="#l3475"></a>
<span id="l3476">            while (utflen &gt; 0) {</span><a href="#l3476"></a>
<span id="l3477">                int avail = end - pos;</span><a href="#l3477"></a>
<span id="l3478">                if (avail &gt;= 3 || (long) avail == utflen) {</span><a href="#l3478"></a>
<span id="l3479">                    utflen -= readUTFSpan(sbuf, utflen);</span><a href="#l3479"></a>
<span id="l3480">                } else {</span><a href="#l3480"></a>
<span id="l3481">                    if (blkmode) {</span><a href="#l3481"></a>
<span id="l3482">                        // near block boundary, read one byte at a time</span><a href="#l3482"></a>
<span id="l3483">                        utflen -= readUTFChar(sbuf, utflen);</span><a href="#l3483"></a>
<span id="l3484">                    } else {</span><a href="#l3484"></a>
<span id="l3485">                        // shift and refill buffer manually</span><a href="#l3485"></a>
<span id="l3486">                        if (avail &gt; 0) {</span><a href="#l3486"></a>
<span id="l3487">                            System.arraycopy(buf, pos, buf, 0, avail);</span><a href="#l3487"></a>
<span id="l3488">                        }</span><a href="#l3488"></a>
<span id="l3489">                        pos = 0;</span><a href="#l3489"></a>
<span id="l3490">                        end = (int) Math.min(MAX_BLOCK_SIZE, utflen);</span><a href="#l3490"></a>
<span id="l3491">                        in.readFully(buf, avail, end - avail);</span><a href="#l3491"></a>
<span id="l3492">                    }</span><a href="#l3492"></a>
<span id="l3493">                }</span><a href="#l3493"></a>
<span id="l3494">            }</span><a href="#l3494"></a>
<span id="l3495"></span><a href="#l3495"></a>
<span id="l3496">            return sbuf.toString();</span><a href="#l3496"></a>
<span id="l3497">        }</span><a href="#l3497"></a>
<span id="l3498"></span><a href="#l3498"></a>
<span id="l3499">        /**</span><a href="#l3499"></a>
<span id="l3500">         * Reads span of UTF-encoded characters out of internal buffer</span><a href="#l3500"></a>
<span id="l3501">         * (starting at offset pos and ending at or before offset end),</span><a href="#l3501"></a>
<span id="l3502">         * consuming no more than utflen bytes.  Appends read characters to</span><a href="#l3502"></a>
<span id="l3503">         * sbuf.  Returns the number of bytes consumed.</span><a href="#l3503"></a>
<span id="l3504">         */</span><a href="#l3504"></a>
<span id="l3505">        private long readUTFSpan(StringBuilder sbuf, long utflen)</span><a href="#l3505"></a>
<span id="l3506">            throws IOException</span><a href="#l3506"></a>
<span id="l3507">        {</span><a href="#l3507"></a>
<span id="l3508">            int cpos = 0;</span><a href="#l3508"></a>
<span id="l3509">            int start = pos;</span><a href="#l3509"></a>
<span id="l3510">            int avail = Math.min(end - pos, CHAR_BUF_SIZE);</span><a href="#l3510"></a>
<span id="l3511">            // stop short of last char unless all of utf bytes in buffer</span><a href="#l3511"></a>
<span id="l3512">            int stop = pos + ((utflen &gt; avail) ? avail - 2 : (int) utflen);</span><a href="#l3512"></a>
<span id="l3513">            boolean outOfBounds = false;</span><a href="#l3513"></a>
<span id="l3514"></span><a href="#l3514"></a>
<span id="l3515">            try {</span><a href="#l3515"></a>
<span id="l3516">                while (pos &lt; stop) {</span><a href="#l3516"></a>
<span id="l3517">                    int b1, b2, b3;</span><a href="#l3517"></a>
<span id="l3518">                    b1 = buf[pos++] &amp; 0xFF;</span><a href="#l3518"></a>
<span id="l3519">                    switch (b1 &gt;&gt; 4) {</span><a href="#l3519"></a>
<span id="l3520">                        case 0:</span><a href="#l3520"></a>
<span id="l3521">                        case 1:</span><a href="#l3521"></a>
<span id="l3522">                        case 2:</span><a href="#l3522"></a>
<span id="l3523">                        case 3:</span><a href="#l3523"></a>
<span id="l3524">                        case 4:</span><a href="#l3524"></a>
<span id="l3525">                        case 5:</span><a href="#l3525"></a>
<span id="l3526">                        case 6:</span><a href="#l3526"></a>
<span id="l3527">                        case 7:   // 1 byte format: 0xxxxxxx</span><a href="#l3527"></a>
<span id="l3528">                            cbuf[cpos++] = (char) b1;</span><a href="#l3528"></a>
<span id="l3529">                            break;</span><a href="#l3529"></a>
<span id="l3530"></span><a href="#l3530"></a>
<span id="l3531">                        case 12:</span><a href="#l3531"></a>
<span id="l3532">                        case 13:  // 2 byte format: 110xxxxx 10xxxxxx</span><a href="#l3532"></a>
<span id="l3533">                            b2 = buf[pos++];</span><a href="#l3533"></a>
<span id="l3534">                            if ((b2 &amp; 0xC0) != 0x80) {</span><a href="#l3534"></a>
<span id="l3535">                                throw new UTFDataFormatException();</span><a href="#l3535"></a>
<span id="l3536">                            }</span><a href="#l3536"></a>
<span id="l3537">                            cbuf[cpos++] = (char) (((b1 &amp; 0x1F) &lt;&lt; 6) |</span><a href="#l3537"></a>
<span id="l3538">                                                   ((b2 &amp; 0x3F) &lt;&lt; 0));</span><a href="#l3538"></a>
<span id="l3539">                            break;</span><a href="#l3539"></a>
<span id="l3540"></span><a href="#l3540"></a>
<span id="l3541">                        case 14:  // 3 byte format: 1110xxxx 10xxxxxx 10xxxxxx</span><a href="#l3541"></a>
<span id="l3542">                            b3 = buf[pos + 1];</span><a href="#l3542"></a>
<span id="l3543">                            b2 = buf[pos + 0];</span><a href="#l3543"></a>
<span id="l3544">                            pos += 2;</span><a href="#l3544"></a>
<span id="l3545">                            if ((b2 &amp; 0xC0) != 0x80 || (b3 &amp; 0xC0) != 0x80) {</span><a href="#l3545"></a>
<span id="l3546">                                throw new UTFDataFormatException();</span><a href="#l3546"></a>
<span id="l3547">                            }</span><a href="#l3547"></a>
<span id="l3548">                            cbuf[cpos++] = (char) (((b1 &amp; 0x0F) &lt;&lt; 12) |</span><a href="#l3548"></a>
<span id="l3549">                                                   ((b2 &amp; 0x3F) &lt;&lt; 6) |</span><a href="#l3549"></a>
<span id="l3550">                                                   ((b3 &amp; 0x3F) &lt;&lt; 0));</span><a href="#l3550"></a>
<span id="l3551">                            break;</span><a href="#l3551"></a>
<span id="l3552"></span><a href="#l3552"></a>
<span id="l3553">                        default:  // 10xx xxxx, 1111 xxxx</span><a href="#l3553"></a>
<span id="l3554">                            throw new UTFDataFormatException();</span><a href="#l3554"></a>
<span id="l3555">                    }</span><a href="#l3555"></a>
<span id="l3556">                }</span><a href="#l3556"></a>
<span id="l3557">            } catch (ArrayIndexOutOfBoundsException ex) {</span><a href="#l3557"></a>
<span id="l3558">                outOfBounds = true;</span><a href="#l3558"></a>
<span id="l3559">            } finally {</span><a href="#l3559"></a>
<span id="l3560">                if (outOfBounds || (pos - start) &gt; utflen) {</span><a href="#l3560"></a>
<span id="l3561">                    /*</span><a href="#l3561"></a>
<span id="l3562">                     * Fix for 4450867: if a malformed utf char causes the</span><a href="#l3562"></a>
<span id="l3563">                     * conversion loop to scan past the expected end of the utf</span><a href="#l3563"></a>
<span id="l3564">                     * string, only consume the expected number of utf bytes.</span><a href="#l3564"></a>
<span id="l3565">                     */</span><a href="#l3565"></a>
<span id="l3566">                    pos = start + (int) utflen;</span><a href="#l3566"></a>
<span id="l3567">                    throw new UTFDataFormatException();</span><a href="#l3567"></a>
<span id="l3568">                }</span><a href="#l3568"></a>
<span id="l3569">            }</span><a href="#l3569"></a>
<span id="l3570"></span><a href="#l3570"></a>
<span id="l3571">            sbuf.append(cbuf, 0, cpos);</span><a href="#l3571"></a>
<span id="l3572">            return pos - start;</span><a href="#l3572"></a>
<span id="l3573">        }</span><a href="#l3573"></a>
<span id="l3574"></span><a href="#l3574"></a>
<span id="l3575">        /**</span><a href="#l3575"></a>
<span id="l3576">         * Reads in single UTF-encoded character one byte at a time, appends</span><a href="#l3576"></a>
<span id="l3577">         * the character to sbuf, and returns the number of bytes consumed.</span><a href="#l3577"></a>
<span id="l3578">         * This method is used when reading in UTF strings written in block</span><a href="#l3578"></a>
<span id="l3579">         * data mode to handle UTF-encoded characters which (potentially)</span><a href="#l3579"></a>
<span id="l3580">         * straddle block-data boundaries.</span><a href="#l3580"></a>
<span id="l3581">         */</span><a href="#l3581"></a>
<span id="l3582">        private int readUTFChar(StringBuilder sbuf, long utflen)</span><a href="#l3582"></a>
<span id="l3583">            throws IOException</span><a href="#l3583"></a>
<span id="l3584">        {</span><a href="#l3584"></a>
<span id="l3585">            int b1, b2, b3;</span><a href="#l3585"></a>
<span id="l3586">            b1 = readByte() &amp; 0xFF;</span><a href="#l3586"></a>
<span id="l3587">            switch (b1 &gt;&gt; 4) {</span><a href="#l3587"></a>
<span id="l3588">                case 0:</span><a href="#l3588"></a>
<span id="l3589">                case 1:</span><a href="#l3589"></a>
<span id="l3590">                case 2:</span><a href="#l3590"></a>
<span id="l3591">                case 3:</span><a href="#l3591"></a>
<span id="l3592">                case 4:</span><a href="#l3592"></a>
<span id="l3593">                case 5:</span><a href="#l3593"></a>
<span id="l3594">                case 6:</span><a href="#l3594"></a>
<span id="l3595">                case 7:     // 1 byte format: 0xxxxxxx</span><a href="#l3595"></a>
<span id="l3596">                    sbuf.append((char) b1);</span><a href="#l3596"></a>
<span id="l3597">                    return 1;</span><a href="#l3597"></a>
<span id="l3598"></span><a href="#l3598"></a>
<span id="l3599">                case 12:</span><a href="#l3599"></a>
<span id="l3600">                case 13:    // 2 byte format: 110xxxxx 10xxxxxx</span><a href="#l3600"></a>
<span id="l3601">                    if (utflen &lt; 2) {</span><a href="#l3601"></a>
<span id="l3602">                        throw new UTFDataFormatException();</span><a href="#l3602"></a>
<span id="l3603">                    }</span><a href="#l3603"></a>
<span id="l3604">                    b2 = readByte();</span><a href="#l3604"></a>
<span id="l3605">                    if ((b2 &amp; 0xC0) != 0x80) {</span><a href="#l3605"></a>
<span id="l3606">                        throw new UTFDataFormatException();</span><a href="#l3606"></a>
<span id="l3607">                    }</span><a href="#l3607"></a>
<span id="l3608">                    sbuf.append((char) (((b1 &amp; 0x1F) &lt;&lt; 6) |</span><a href="#l3608"></a>
<span id="l3609">                                        ((b2 &amp; 0x3F) &lt;&lt; 0)));</span><a href="#l3609"></a>
<span id="l3610">                    return 2;</span><a href="#l3610"></a>
<span id="l3611"></span><a href="#l3611"></a>
<span id="l3612">                case 14:    // 3 byte format: 1110xxxx 10xxxxxx 10xxxxxx</span><a href="#l3612"></a>
<span id="l3613">                    if (utflen &lt; 3) {</span><a href="#l3613"></a>
<span id="l3614">                        if (utflen == 2) {</span><a href="#l3614"></a>
<span id="l3615">                            readByte();         // consume remaining byte</span><a href="#l3615"></a>
<span id="l3616">                        }</span><a href="#l3616"></a>
<span id="l3617">                        throw new UTFDataFormatException();</span><a href="#l3617"></a>
<span id="l3618">                    }</span><a href="#l3618"></a>
<span id="l3619">                    b2 = readByte();</span><a href="#l3619"></a>
<span id="l3620">                    b3 = readByte();</span><a href="#l3620"></a>
<span id="l3621">                    if ((b2 &amp; 0xC0) != 0x80 || (b3 &amp; 0xC0) != 0x80) {</span><a href="#l3621"></a>
<span id="l3622">                        throw new UTFDataFormatException();</span><a href="#l3622"></a>
<span id="l3623">                    }</span><a href="#l3623"></a>
<span id="l3624">                    sbuf.append((char) (((b1 &amp; 0x0F) &lt;&lt; 12) |</span><a href="#l3624"></a>
<span id="l3625">                                        ((b2 &amp; 0x3F) &lt;&lt; 6) |</span><a href="#l3625"></a>
<span id="l3626">                                        ((b3 &amp; 0x3F) &lt;&lt; 0)));</span><a href="#l3626"></a>
<span id="l3627">                    return 3;</span><a href="#l3627"></a>
<span id="l3628"></span><a href="#l3628"></a>
<span id="l3629">                default:   // 10xx xxxx, 1111 xxxx</span><a href="#l3629"></a>
<span id="l3630">                    throw new UTFDataFormatException();</span><a href="#l3630"></a>
<span id="l3631">            }</span><a href="#l3631"></a>
<span id="l3632">        }</span><a href="#l3632"></a>
<span id="l3633"></span><a href="#l3633"></a>
<span id="l3634">        /**</span><a href="#l3634"></a>
<span id="l3635">         * Returns the number of bytes read from the input stream.</span><a href="#l3635"></a>
<span id="l3636">         * @return the number of bytes read from the input stream</span><a href="#l3636"></a>
<span id="l3637">         */</span><a href="#l3637"></a>
<span id="l3638">        long getBytesRead() {</span><a href="#l3638"></a>
<span id="l3639">            return in.getBytesRead();</span><a href="#l3639"></a>
<span id="l3640">        }</span><a href="#l3640"></a>
<span id="l3641">    }</span><a href="#l3641"></a>
<span id="l3642"></span><a href="#l3642"></a>
<span id="l3643">    /**</span><a href="#l3643"></a>
<span id="l3644">     * Unsynchronized table which tracks wire handle to object mappings, as</span><a href="#l3644"></a>
<span id="l3645">     * well as ClassNotFoundExceptions associated with deserialized objects.</span><a href="#l3645"></a>
<span id="l3646">     * This class implements an exception-propagation algorithm for</span><a href="#l3646"></a>
<span id="l3647">     * determining which objects should have ClassNotFoundExceptions associated</span><a href="#l3647"></a>
<span id="l3648">     * with them, taking into account cycles and discontinuities (e.g., skipped</span><a href="#l3648"></a>
<span id="l3649">     * fields) in the object graph.</span><a href="#l3649"></a>
<span id="l3650">     *</span><a href="#l3650"></a>
<span id="l3651">     * &lt;p&gt;General use of the table is as follows: during deserialization, a</span><a href="#l3651"></a>
<span id="l3652">     * given object is first assigned a handle by calling the assign method.</span><a href="#l3652"></a>
<span id="l3653">     * This method leaves the assigned handle in an &quot;open&quot; state, wherein</span><a href="#l3653"></a>
<span id="l3654">     * dependencies on the exception status of other handles can be registered</span><a href="#l3654"></a>
<span id="l3655">     * by calling the markDependency method, or an exception can be directly</span><a href="#l3655"></a>
<span id="l3656">     * associated with the handle by calling markException.  When a handle is</span><a href="#l3656"></a>
<span id="l3657">     * tagged with an exception, the HandleTable assumes responsibility for</span><a href="#l3657"></a>
<span id="l3658">     * propagating the exception to any other objects which depend</span><a href="#l3658"></a>
<span id="l3659">     * (transitively) on the exception-tagged object.</span><a href="#l3659"></a>
<span id="l3660">     *</span><a href="#l3660"></a>
<span id="l3661">     * &lt;p&gt;Once all exception information/dependencies for the handle have been</span><a href="#l3661"></a>
<span id="l3662">     * registered, the handle should be &quot;closed&quot; by calling the finish method</span><a href="#l3662"></a>
<span id="l3663">     * on it.  The act of finishing a handle allows the exception propagation</span><a href="#l3663"></a>
<span id="l3664">     * algorithm to aggressively prune dependency links, lessening the</span><a href="#l3664"></a>
<span id="l3665">     * performance/memory impact of exception tracking.</span><a href="#l3665"></a>
<span id="l3666">     *</span><a href="#l3666"></a>
<span id="l3667">     * &lt;p&gt;Note that the exception propagation algorithm used depends on handles</span><a href="#l3667"></a>
<span id="l3668">     * being assigned/finished in LIFO order; however, for simplicity as well</span><a href="#l3668"></a>
<span id="l3669">     * as memory conservation, it does not enforce this constraint.</span><a href="#l3669"></a>
<span id="l3670">     */</span><a href="#l3670"></a>
<span id="l3671">    // REMIND: add full description of exception propagation algorithm?</span><a href="#l3671"></a>
<span id="l3672">    private static class HandleTable {</span><a href="#l3672"></a>
<span id="l3673"></span><a href="#l3673"></a>
<span id="l3674">        /* status codes indicating whether object has associated exception */</span><a href="#l3674"></a>
<span id="l3675">        private static final byte STATUS_OK = 1;</span><a href="#l3675"></a>
<span id="l3676">        private static final byte STATUS_UNKNOWN = 2;</span><a href="#l3676"></a>
<span id="l3677">        private static final byte STATUS_EXCEPTION = 3;</span><a href="#l3677"></a>
<span id="l3678"></span><a href="#l3678"></a>
<span id="l3679">        /** array mapping handle -&gt; object status */</span><a href="#l3679"></a>
<span id="l3680">        byte[] status;</span><a href="#l3680"></a>
<span id="l3681">        /** array mapping handle -&gt; object/exception (depending on status) */</span><a href="#l3681"></a>
<span id="l3682">        Object[] entries;</span><a href="#l3682"></a>
<span id="l3683">        /** array mapping handle -&gt; list of dependent handles (if any) */</span><a href="#l3683"></a>
<span id="l3684">        HandleList[] deps;</span><a href="#l3684"></a>
<span id="l3685">        /** lowest unresolved dependency */</span><a href="#l3685"></a>
<span id="l3686">        int lowDep = -1;</span><a href="#l3686"></a>
<span id="l3687">        /** number of handles in table */</span><a href="#l3687"></a>
<span id="l3688">        int size = 0;</span><a href="#l3688"></a>
<span id="l3689"></span><a href="#l3689"></a>
<span id="l3690">        /**</span><a href="#l3690"></a>
<span id="l3691">         * Creates handle table with the given initial capacity.</span><a href="#l3691"></a>
<span id="l3692">         */</span><a href="#l3692"></a>
<span id="l3693">        HandleTable(int initialCapacity) {</span><a href="#l3693"></a>
<span id="l3694">            status = new byte[initialCapacity];</span><a href="#l3694"></a>
<span id="l3695">            entries = new Object[initialCapacity];</span><a href="#l3695"></a>
<span id="l3696">            deps = new HandleList[initialCapacity];</span><a href="#l3696"></a>
<span id="l3697">        }</span><a href="#l3697"></a>
<span id="l3698"></span><a href="#l3698"></a>
<span id="l3699">        /**</span><a href="#l3699"></a>
<span id="l3700">         * Assigns next available handle to given object, and returns assigned</span><a href="#l3700"></a>
<span id="l3701">         * handle.  Once object has been completely deserialized (and all</span><a href="#l3701"></a>
<span id="l3702">         * dependencies on other objects identified), the handle should be</span><a href="#l3702"></a>
<span id="l3703">         * &quot;closed&quot; by passing it to finish().</span><a href="#l3703"></a>
<span id="l3704">         */</span><a href="#l3704"></a>
<span id="l3705">        int assign(Object obj) {</span><a href="#l3705"></a>
<span id="l3706">            if (size &gt;= entries.length) {</span><a href="#l3706"></a>
<span id="l3707">                grow();</span><a href="#l3707"></a>
<span id="l3708">            }</span><a href="#l3708"></a>
<span id="l3709">            status[size] = STATUS_UNKNOWN;</span><a href="#l3709"></a>
<span id="l3710">            entries[size] = obj;</span><a href="#l3710"></a>
<span id="l3711">            return size++;</span><a href="#l3711"></a>
<span id="l3712">        }</span><a href="#l3712"></a>
<span id="l3713"></span><a href="#l3713"></a>
<span id="l3714">        /**</span><a href="#l3714"></a>
<span id="l3715">         * Registers a dependency (in exception status) of one handle on</span><a href="#l3715"></a>
<span id="l3716">         * another.  The dependent handle must be &quot;open&quot; (i.e., assigned, but</span><a href="#l3716"></a>
<span id="l3717">         * not finished yet).  No action is taken if either dependent or target</span><a href="#l3717"></a>
<span id="l3718">         * handle is NULL_HANDLE.</span><a href="#l3718"></a>
<span id="l3719">         */</span><a href="#l3719"></a>
<span id="l3720">        void markDependency(int dependent, int target) {</span><a href="#l3720"></a>
<span id="l3721">            if (dependent == NULL_HANDLE || target == NULL_HANDLE) {</span><a href="#l3721"></a>
<span id="l3722">                return;</span><a href="#l3722"></a>
<span id="l3723">            }</span><a href="#l3723"></a>
<span id="l3724">            switch (status[dependent]) {</span><a href="#l3724"></a>
<span id="l3725"></span><a href="#l3725"></a>
<span id="l3726">                case STATUS_UNKNOWN:</span><a href="#l3726"></a>
<span id="l3727">                    switch (status[target]) {</span><a href="#l3727"></a>
<span id="l3728">                        case STATUS_OK:</span><a href="#l3728"></a>
<span id="l3729">                            // ignore dependencies on objs with no exception</span><a href="#l3729"></a>
<span id="l3730">                            break;</span><a href="#l3730"></a>
<span id="l3731"></span><a href="#l3731"></a>
<span id="l3732">                        case STATUS_EXCEPTION:</span><a href="#l3732"></a>
<span id="l3733">                            // eagerly propagate exception</span><a href="#l3733"></a>
<span id="l3734">                            markException(dependent,</span><a href="#l3734"></a>
<span id="l3735">                                (ClassNotFoundException) entries[target]);</span><a href="#l3735"></a>
<span id="l3736">                            break;</span><a href="#l3736"></a>
<span id="l3737"></span><a href="#l3737"></a>
<span id="l3738">                        case STATUS_UNKNOWN:</span><a href="#l3738"></a>
<span id="l3739">                            // add to dependency list of target</span><a href="#l3739"></a>
<span id="l3740">                            if (deps[target] == null) {</span><a href="#l3740"></a>
<span id="l3741">                                deps[target] = new HandleList();</span><a href="#l3741"></a>
<span id="l3742">                            }</span><a href="#l3742"></a>
<span id="l3743">                            deps[target].add(dependent);</span><a href="#l3743"></a>
<span id="l3744"></span><a href="#l3744"></a>
<span id="l3745">                            // remember lowest unresolved target seen</span><a href="#l3745"></a>
<span id="l3746">                            if (lowDep &lt; 0 || lowDep &gt; target) {</span><a href="#l3746"></a>
<span id="l3747">                                lowDep = target;</span><a href="#l3747"></a>
<span id="l3748">                            }</span><a href="#l3748"></a>
<span id="l3749">                            break;</span><a href="#l3749"></a>
<span id="l3750"></span><a href="#l3750"></a>
<span id="l3751">                        default:</span><a href="#l3751"></a>
<span id="l3752">                            throw new InternalError();</span><a href="#l3752"></a>
<span id="l3753">                    }</span><a href="#l3753"></a>
<span id="l3754">                    break;</span><a href="#l3754"></a>
<span id="l3755"></span><a href="#l3755"></a>
<span id="l3756">                case STATUS_EXCEPTION:</span><a href="#l3756"></a>
<span id="l3757">                    break;</span><a href="#l3757"></a>
<span id="l3758"></span><a href="#l3758"></a>
<span id="l3759">                default:</span><a href="#l3759"></a>
<span id="l3760">                    throw new InternalError();</span><a href="#l3760"></a>
<span id="l3761">            }</span><a href="#l3761"></a>
<span id="l3762">        }</span><a href="#l3762"></a>
<span id="l3763"></span><a href="#l3763"></a>
<span id="l3764">        /**</span><a href="#l3764"></a>
<span id="l3765">         * Associates a ClassNotFoundException (if one not already associated)</span><a href="#l3765"></a>
<span id="l3766">         * with the currently active handle and propagates it to other</span><a href="#l3766"></a>
<span id="l3767">         * referencing objects as appropriate.  The specified handle must be</span><a href="#l3767"></a>
<span id="l3768">         * &quot;open&quot; (i.e., assigned, but not finished yet).</span><a href="#l3768"></a>
<span id="l3769">         */</span><a href="#l3769"></a>
<span id="l3770">        void markException(int handle, ClassNotFoundException ex) {</span><a href="#l3770"></a>
<span id="l3771">            switch (status[handle]) {</span><a href="#l3771"></a>
<span id="l3772">                case STATUS_UNKNOWN:</span><a href="#l3772"></a>
<span id="l3773">                    status[handle] = STATUS_EXCEPTION;</span><a href="#l3773"></a>
<span id="l3774">                    entries[handle] = ex;</span><a href="#l3774"></a>
<span id="l3775"></span><a href="#l3775"></a>
<span id="l3776">                    // propagate exception to dependents</span><a href="#l3776"></a>
<span id="l3777">                    HandleList dlist = deps[handle];</span><a href="#l3777"></a>
<span id="l3778">                    if (dlist != null) {</span><a href="#l3778"></a>
<span id="l3779">                        int ndeps = dlist.size();</span><a href="#l3779"></a>
<span id="l3780">                        for (int i = 0; i &lt; ndeps; i++) {</span><a href="#l3780"></a>
<span id="l3781">                            markException(dlist.get(i), ex);</span><a href="#l3781"></a>
<span id="l3782">                        }</span><a href="#l3782"></a>
<span id="l3783">                        deps[handle] = null;</span><a href="#l3783"></a>
<span id="l3784">                    }</span><a href="#l3784"></a>
<span id="l3785">                    break;</span><a href="#l3785"></a>
<span id="l3786"></span><a href="#l3786"></a>
<span id="l3787">                case STATUS_EXCEPTION:</span><a href="#l3787"></a>
<span id="l3788">                    break;</span><a href="#l3788"></a>
<span id="l3789"></span><a href="#l3789"></a>
<span id="l3790">                default:</span><a href="#l3790"></a>
<span id="l3791">                    throw new InternalError();</span><a href="#l3791"></a>
<span id="l3792">            }</span><a href="#l3792"></a>
<span id="l3793">        }</span><a href="#l3793"></a>
<span id="l3794"></span><a href="#l3794"></a>
<span id="l3795">        /**</span><a href="#l3795"></a>
<span id="l3796">         * Marks given handle as finished, meaning that no new dependencies</span><a href="#l3796"></a>
<span id="l3797">         * will be marked for handle.  Calls to the assign and finish methods</span><a href="#l3797"></a>
<span id="l3798">         * must occur in LIFO order.</span><a href="#l3798"></a>
<span id="l3799">         */</span><a href="#l3799"></a>
<span id="l3800">        void finish(int handle) {</span><a href="#l3800"></a>
<span id="l3801">            int end;</span><a href="#l3801"></a>
<span id="l3802">            if (lowDep &lt; 0) {</span><a href="#l3802"></a>
<span id="l3803">                // no pending unknowns, only resolve current handle</span><a href="#l3803"></a>
<span id="l3804">                end = handle + 1;</span><a href="#l3804"></a>
<span id="l3805">            } else if (lowDep &gt;= handle) {</span><a href="#l3805"></a>
<span id="l3806">                // pending unknowns now clearable, resolve all upward handles</span><a href="#l3806"></a>
<span id="l3807">                end = size;</span><a href="#l3807"></a>
<span id="l3808">                lowDep = -1;</span><a href="#l3808"></a>
<span id="l3809">            } else {</span><a href="#l3809"></a>
<span id="l3810">                // unresolved backrefs present, can't resolve anything yet</span><a href="#l3810"></a>
<span id="l3811">                return;</span><a href="#l3811"></a>
<span id="l3812">            }</span><a href="#l3812"></a>
<span id="l3813"></span><a href="#l3813"></a>
<span id="l3814">            // change STATUS_UNKNOWN -&gt; STATUS_OK in selected span of handles</span><a href="#l3814"></a>
<span id="l3815">            for (int i = handle; i &lt; end; i++) {</span><a href="#l3815"></a>
<span id="l3816">                switch (status[i]) {</span><a href="#l3816"></a>
<span id="l3817">                    case STATUS_UNKNOWN:</span><a href="#l3817"></a>
<span id="l3818">                        status[i] = STATUS_OK;</span><a href="#l3818"></a>
<span id="l3819">                        deps[i] = null;</span><a href="#l3819"></a>
<span id="l3820">                        break;</span><a href="#l3820"></a>
<span id="l3821"></span><a href="#l3821"></a>
<span id="l3822">                    case STATUS_OK:</span><a href="#l3822"></a>
<span id="l3823">                    case STATUS_EXCEPTION:</span><a href="#l3823"></a>
<span id="l3824">                        break;</span><a href="#l3824"></a>
<span id="l3825"></span><a href="#l3825"></a>
<span id="l3826">                    default:</span><a href="#l3826"></a>
<span id="l3827">                        throw new InternalError();</span><a href="#l3827"></a>
<span id="l3828">                }</span><a href="#l3828"></a>
<span id="l3829">            }</span><a href="#l3829"></a>
<span id="l3830">        }</span><a href="#l3830"></a>
<span id="l3831"></span><a href="#l3831"></a>
<span id="l3832">        /**</span><a href="#l3832"></a>
<span id="l3833">         * Assigns a new object to the given handle.  The object previously</span><a href="#l3833"></a>
<span id="l3834">         * associated with the handle is forgotten.  This method has no effect</span><a href="#l3834"></a>
<span id="l3835">         * if the given handle already has an exception associated with it.</span><a href="#l3835"></a>
<span id="l3836">         * This method may be called at any time after the handle is assigned.</span><a href="#l3836"></a>
<span id="l3837">         */</span><a href="#l3837"></a>
<span id="l3838">        void setObject(int handle, Object obj) {</span><a href="#l3838"></a>
<span id="l3839">            switch (status[handle]) {</span><a href="#l3839"></a>
<span id="l3840">                case STATUS_UNKNOWN:</span><a href="#l3840"></a>
<span id="l3841">                case STATUS_OK:</span><a href="#l3841"></a>
<span id="l3842">                    entries[handle] = obj;</span><a href="#l3842"></a>
<span id="l3843">                    break;</span><a href="#l3843"></a>
<span id="l3844"></span><a href="#l3844"></a>
<span id="l3845">                case STATUS_EXCEPTION:</span><a href="#l3845"></a>
<span id="l3846">                    break;</span><a href="#l3846"></a>
<span id="l3847"></span><a href="#l3847"></a>
<span id="l3848">                default:</span><a href="#l3848"></a>
<span id="l3849">                    throw new InternalError();</span><a href="#l3849"></a>
<span id="l3850">            }</span><a href="#l3850"></a>
<span id="l3851">        }</span><a href="#l3851"></a>
<span id="l3852"></span><a href="#l3852"></a>
<span id="l3853">        /**</span><a href="#l3853"></a>
<span id="l3854">         * Looks up and returns object associated with the given handle.</span><a href="#l3854"></a>
<span id="l3855">         * Returns null if the given handle is NULL_HANDLE, or if it has an</span><a href="#l3855"></a>
<span id="l3856">         * associated ClassNotFoundException.</span><a href="#l3856"></a>
<span id="l3857">         */</span><a href="#l3857"></a>
<span id="l3858">        Object lookupObject(int handle) {</span><a href="#l3858"></a>
<span id="l3859">            return (handle != NULL_HANDLE &amp;&amp;</span><a href="#l3859"></a>
<span id="l3860">                    status[handle] != STATUS_EXCEPTION) ?</span><a href="#l3860"></a>
<span id="l3861">                entries[handle] : null;</span><a href="#l3861"></a>
<span id="l3862">        }</span><a href="#l3862"></a>
<span id="l3863"></span><a href="#l3863"></a>
<span id="l3864">        /**</span><a href="#l3864"></a>
<span id="l3865">         * Looks up and returns ClassNotFoundException associated with the</span><a href="#l3865"></a>
<span id="l3866">         * given handle.  Returns null if the given handle is NULL_HANDLE, or</span><a href="#l3866"></a>
<span id="l3867">         * if there is no ClassNotFoundException associated with the handle.</span><a href="#l3867"></a>
<span id="l3868">         */</span><a href="#l3868"></a>
<span id="l3869">        ClassNotFoundException lookupException(int handle) {</span><a href="#l3869"></a>
<span id="l3870">            return (handle != NULL_HANDLE &amp;&amp;</span><a href="#l3870"></a>
<span id="l3871">                    status[handle] == STATUS_EXCEPTION) ?</span><a href="#l3871"></a>
<span id="l3872">                (ClassNotFoundException) entries[handle] : null;</span><a href="#l3872"></a>
<span id="l3873">        }</span><a href="#l3873"></a>
<span id="l3874"></span><a href="#l3874"></a>
<span id="l3875">        /**</span><a href="#l3875"></a>
<span id="l3876">         * Resets table to its initial state.</span><a href="#l3876"></a>
<span id="l3877">         */</span><a href="#l3877"></a>
<span id="l3878">        void clear() {</span><a href="#l3878"></a>
<span id="l3879">            Arrays.fill(status, 0, size, (byte) 0);</span><a href="#l3879"></a>
<span id="l3880">            Arrays.fill(entries, 0, size, null);</span><a href="#l3880"></a>
<span id="l3881">            Arrays.fill(deps, 0, size, null);</span><a href="#l3881"></a>
<span id="l3882">            lowDep = -1;</span><a href="#l3882"></a>
<span id="l3883">            size = 0;</span><a href="#l3883"></a>
<span id="l3884">        }</span><a href="#l3884"></a>
<span id="l3885"></span><a href="#l3885"></a>
<span id="l3886">        /**</span><a href="#l3886"></a>
<span id="l3887">         * Returns number of handles registered in table.</span><a href="#l3887"></a>
<span id="l3888">         */</span><a href="#l3888"></a>
<span id="l3889">        int size() {</span><a href="#l3889"></a>
<span id="l3890">            return size;</span><a href="#l3890"></a>
<span id="l3891">        }</span><a href="#l3891"></a>
<span id="l3892"></span><a href="#l3892"></a>
<span id="l3893">        /**</span><a href="#l3893"></a>
<span id="l3894">         * Expands capacity of internal arrays.</span><a href="#l3894"></a>
<span id="l3895">         */</span><a href="#l3895"></a>
<span id="l3896">        private void grow() {</span><a href="#l3896"></a>
<span id="l3897">            int newCapacity = (entries.length &lt;&lt; 1) + 1;</span><a href="#l3897"></a>
<span id="l3898"></span><a href="#l3898"></a>
<span id="l3899">            byte[] newStatus = new byte[newCapacity];</span><a href="#l3899"></a>
<span id="l3900">            Object[] newEntries = new Object[newCapacity];</span><a href="#l3900"></a>
<span id="l3901">            HandleList[] newDeps = new HandleList[newCapacity];</span><a href="#l3901"></a>
<span id="l3902"></span><a href="#l3902"></a>
<span id="l3903">            System.arraycopy(status, 0, newStatus, 0, size);</span><a href="#l3903"></a>
<span id="l3904">            System.arraycopy(entries, 0, newEntries, 0, size);</span><a href="#l3904"></a>
<span id="l3905">            System.arraycopy(deps, 0, newDeps, 0, size);</span><a href="#l3905"></a>
<span id="l3906"></span><a href="#l3906"></a>
<span id="l3907">            status = newStatus;</span><a href="#l3907"></a>
<span id="l3908">            entries = newEntries;</span><a href="#l3908"></a>
<span id="l3909">            deps = newDeps;</span><a href="#l3909"></a>
<span id="l3910">        }</span><a href="#l3910"></a>
<span id="l3911"></span><a href="#l3911"></a>
<span id="l3912">        /**</span><a href="#l3912"></a>
<span id="l3913">         * Simple growable list of (integer) handles.</span><a href="#l3913"></a>
<span id="l3914">         */</span><a href="#l3914"></a>
<span id="l3915">        private static class HandleList {</span><a href="#l3915"></a>
<span id="l3916">            private int[] list = new int[4];</span><a href="#l3916"></a>
<span id="l3917">            private int size = 0;</span><a href="#l3917"></a>
<span id="l3918"></span><a href="#l3918"></a>
<span id="l3919">            public HandleList() {</span><a href="#l3919"></a>
<span id="l3920">            }</span><a href="#l3920"></a>
<span id="l3921"></span><a href="#l3921"></a>
<span id="l3922">            public void add(int handle) {</span><a href="#l3922"></a>
<span id="l3923">                if (size &gt;= list.length) {</span><a href="#l3923"></a>
<span id="l3924">                    int[] newList = new int[list.length &lt;&lt; 1];</span><a href="#l3924"></a>
<span id="l3925">                    System.arraycopy(list, 0, newList, 0, list.length);</span><a href="#l3925"></a>
<span id="l3926">                    list = newList;</span><a href="#l3926"></a>
<span id="l3927">                }</span><a href="#l3927"></a>
<span id="l3928">                list[size++] = handle;</span><a href="#l3928"></a>
<span id="l3929">            }</span><a href="#l3929"></a>
<span id="l3930"></span><a href="#l3930"></a>
<span id="l3931">            public int get(int index) {</span><a href="#l3931"></a>
<span id="l3932">                if (index &gt;= size) {</span><a href="#l3932"></a>
<span id="l3933">                    throw new ArrayIndexOutOfBoundsException();</span><a href="#l3933"></a>
<span id="l3934">                }</span><a href="#l3934"></a>
<span id="l3935">                return list[index];</span><a href="#l3935"></a>
<span id="l3936">            }</span><a href="#l3936"></a>
<span id="l3937"></span><a href="#l3937"></a>
<span id="l3938">            public int size() {</span><a href="#l3938"></a>
<span id="l3939">                return size;</span><a href="#l3939"></a>
<span id="l3940">            }</span><a href="#l3940"></a>
<span id="l3941">        }</span><a href="#l3941"></a>
<span id="l3942">    }</span><a href="#l3942"></a>
<span id="l3943"></span><a href="#l3943"></a>
<span id="l3944">    /**</span><a href="#l3944"></a>
<span id="l3945">     * Method for cloning arrays in case of using unsharing reading</span><a href="#l3945"></a>
<span id="l3946">     */</span><a href="#l3946"></a>
<span id="l3947">    private static Object cloneArray(Object array) {</span><a href="#l3947"></a>
<span id="l3948">        if (array instanceof Object[]) {</span><a href="#l3948"></a>
<span id="l3949">            return ((Object[]) array).clone();</span><a href="#l3949"></a>
<span id="l3950">        } else if (array instanceof boolean[]) {</span><a href="#l3950"></a>
<span id="l3951">            return ((boolean[]) array).clone();</span><a href="#l3951"></a>
<span id="l3952">        } else if (array instanceof byte[]) {</span><a href="#l3952"></a>
<span id="l3953">            return ((byte[]) array).clone();</span><a href="#l3953"></a>
<span id="l3954">        } else if (array instanceof char[]) {</span><a href="#l3954"></a>
<span id="l3955">            return ((char[]) array).clone();</span><a href="#l3955"></a>
<span id="l3956">        } else if (array instanceof double[]) {</span><a href="#l3956"></a>
<span id="l3957">            return ((double[]) array).clone();</span><a href="#l3957"></a>
<span id="l3958">        } else if (array instanceof float[]) {</span><a href="#l3958"></a>
<span id="l3959">            return ((float[]) array).clone();</span><a href="#l3959"></a>
<span id="l3960">        } else if (array instanceof int[]) {</span><a href="#l3960"></a>
<span id="l3961">            return ((int[]) array).clone();</span><a href="#l3961"></a>
<span id="l3962">        } else if (array instanceof long[]) {</span><a href="#l3962"></a>
<span id="l3963">            return ((long[]) array).clone();</span><a href="#l3963"></a>
<span id="l3964">        } else if (array instanceof short[]) {</span><a href="#l3964"></a>
<span id="l3965">            return ((short[]) array).clone();</span><a href="#l3965"></a>
<span id="l3966">        } else {</span><a href="#l3966"></a>
<span id="l3967">            throw new AssertionError();</span><a href="#l3967"></a>
<span id="l3968">        }</span><a href="#l3968"></a>
<span id="l3969">    }</span><a href="#l3969"></a>
<span id="l3970"></span><a href="#l3970"></a>
<span id="l3971">    private void validateDescriptor(ObjectStreamClass descriptor) {</span><a href="#l3971"></a>
<span id="l3972">        ObjectStreamClassValidator validating = validator;</span><a href="#l3972"></a>
<span id="l3973">        if (validating != null) {</span><a href="#l3973"></a>
<span id="l3974">            validating.validateDescriptor(descriptor);</span><a href="#l3974"></a>
<span id="l3975">        }</span><a href="#l3975"></a>
<span id="l3976">    }</span><a href="#l3976"></a>
<span id="l3977"></span><a href="#l3977"></a>
<span id="l3978">    // controlled access to ObjectStreamClassValidator</span><a href="#l3978"></a>
<span id="l3979">    private volatile ObjectStreamClassValidator validator;</span><a href="#l3979"></a>
<span id="l3980"></span><a href="#l3980"></a>
<span id="l3981">    private static void setValidator(ObjectInputStream ois, ObjectStreamClassValidator validator) {</span><a href="#l3981"></a>
<span id="l3982">        ois.validator = validator;</span><a href="#l3982"></a>
<span id="l3983">    }</span><a href="#l3983"></a>
<span id="l3984">    static {</span><a href="#l3984"></a>
<span id="l3985">        SharedSecrets.setJavaObjectInputStreamAccess(ObjectInputStream::setValidator);</span><a href="#l3985"></a>
<span id="l3986">        SharedSecrets.setJavaObjectInputStreamReadString(ObjectInputStream::readString);</span><a href="#l3986"></a>
<span id="l3987">    }</span><a href="#l3987"></a>
<span id="l3988">}</span><a href="#l3988"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

