<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: db82be4e049c src/share/classes/java/io/ObjectStreamClass.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/io/ObjectStreamClass.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/db82be4e049c/src/share/classes/java/io/ObjectStreamClass.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/db82be4e049c/src/share/classes/java/io/ObjectStreamClass.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/db82be4e049c/src/share/classes/java/io/ObjectStreamClass.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/db82be4e049c/src/share/classes/java/io/ObjectStreamClass.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/db82be4e049c/src/share/classes/java/io/ObjectStreamClass.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/io/ObjectStreamClass.java @ 13884:db82be4e049c</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3de016c65a06/src/share/classes/java/io/ObjectStreamClass.java">3de016c65a06</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/a75922cb4096/src/share/classes/java/io/ObjectStreamClass.java">a75922cb4096</a> </td>
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
<span id="l28">import java.lang.ref.Reference;</span><a href="#l28"></a>
<span id="l29">import java.lang.ref.ReferenceQueue;</span><a href="#l29"></a>
<span id="l30">import java.lang.ref.SoftReference;</span><a href="#l30"></a>
<span id="l31">import java.lang.ref.WeakReference;</span><a href="#l31"></a>
<span id="l32">import java.lang.reflect.Constructor;</span><a href="#l32"></a>
<span id="l33">import java.lang.reflect.Field;</span><a href="#l33"></a>
<span id="l34">import java.lang.reflect.InvocationTargetException;</span><a href="#l34"></a>
<span id="l35">import java.lang.reflect.UndeclaredThrowableException;</span><a href="#l35"></a>
<span id="l36">import java.lang.reflect.Member;</span><a href="#l36"></a>
<span id="l37">import java.lang.reflect.Method;</span><a href="#l37"></a>
<span id="l38">import java.lang.reflect.Modifier;</span><a href="#l38"></a>
<span id="l39">import java.lang.reflect.Proxy;</span><a href="#l39"></a>
<span id="l40">import java.security.AccessControlContext;</span><a href="#l40"></a>
<span id="l41">import java.security.AccessController;</span><a href="#l41"></a>
<span id="l42">import java.security.MessageDigest;</span><a href="#l42"></a>
<span id="l43">import java.security.NoSuchAlgorithmException;</span><a href="#l43"></a>
<span id="l44">import java.security.PermissionCollection;</span><a href="#l44"></a>
<span id="l45">import java.security.Permissions;</span><a href="#l45"></a>
<span id="l46">import java.security.PrivilegedAction;</span><a href="#l46"></a>
<span id="l47">import java.security.ProtectionDomain;</span><a href="#l47"></a>
<span id="l48">import java.util.ArrayList;</span><a href="#l48"></a>
<span id="l49">import java.util.Arrays;</span><a href="#l49"></a>
<span id="l50">import java.util.Collections;</span><a href="#l50"></a>
<span id="l51">import java.util.Comparator;</span><a href="#l51"></a>
<span id="l52">import java.util.HashSet;</span><a href="#l52"></a>
<span id="l53">import java.util.Set;</span><a href="#l53"></a>
<span id="l54">import java.util.concurrent.ConcurrentHashMap;</span><a href="#l54"></a>
<span id="l55">import java.util.concurrent.ConcurrentMap;</span><a href="#l55"></a>
<span id="l56">import sun.misc.JavaSecurityAccess;</span><a href="#l56"></a>
<span id="l57">import sun.misc.SharedSecrets;</span><a href="#l57"></a>
<span id="l58">import sun.misc.Unsafe;</span><a href="#l58"></a>
<span id="l59">import sun.reflect.CallerSensitive;</span><a href="#l59"></a>
<span id="l60">import sun.reflect.Reflection;</span><a href="#l60"></a>
<span id="l61">import sun.reflect.ReflectionFactory;</span><a href="#l61"></a>
<span id="l62">import sun.reflect.misc.ReflectUtil;</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">/**</span><a href="#l64"></a>
<span id="l65"> * Serialization's descriptor for classes.  It contains the name and</span><a href="#l65"></a>
<span id="l66"> * serialVersionUID of the class.  The ObjectStreamClass for a specific class</span><a href="#l66"></a>
<span id="l67"> * loaded in this Java VM can be found/created using the lookup method.</span><a href="#l67"></a>
<span id="l68"> *</span><a href="#l68"></a>
<span id="l69"> * &lt;p&gt;The algorithm to compute the SerialVersionUID is described in</span><a href="#l69"></a>
<span id="l70"> * &lt;a href=&quot;../../../platform/serialization/spec/class.html#4100&quot;&gt;Object</span><a href="#l70"></a>
<span id="l71"> * Serialization Specification, Section 4.6, Stream Unique Identifiers&lt;/a&gt;.</span><a href="#l71"></a>
<span id="l72"> *</span><a href="#l72"></a>
<span id="l73"> * @author      Mike Warres</span><a href="#l73"></a>
<span id="l74"> * @author      Roger Riggs</span><a href="#l74"></a>
<span id="l75"> * @see ObjectStreamField</span><a href="#l75"></a>
<span id="l76"> * @see &lt;a href=&quot;../../../platform/serialization/spec/class.html&quot;&gt;Object Serialization Specification, Section 4, Class Descriptors&lt;/a&gt;</span><a href="#l76"></a>
<span id="l77"> * @since   JDK1.1</span><a href="#l77"></a>
<span id="l78"> */</span><a href="#l78"></a>
<span id="l79">public class ObjectStreamClass implements Serializable {</span><a href="#l79"></a>
<span id="l80"></span><a href="#l80"></a>
<span id="l81">    /** serialPersistentFields value indicating no serializable fields */</span><a href="#l81"></a>
<span id="l82">    public static final ObjectStreamField[] NO_FIELDS =</span><a href="#l82"></a>
<span id="l83">        new ObjectStreamField[0];</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    private static final long serialVersionUID = -6120832682080437368L;</span><a href="#l85"></a>
<span id="l86">    private static final ObjectStreamField[] serialPersistentFields =</span><a href="#l86"></a>
<span id="l87">        NO_FIELDS;</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /** true if deserialization constructor checking is disabled */</span><a href="#l89"></a>
<span id="l90">    private static boolean disableSerialConstructorChecks =</span><a href="#l90"></a>
<span id="l91">        AccessController.doPrivileged(</span><a href="#l91"></a>
<span id="l92">            new PrivilegedAction&lt;Boolean&gt;() {</span><a href="#l92"></a>
<span id="l93">                public Boolean run() {</span><a href="#l93"></a>
<span id="l94">                    String prop = &quot;jdk.disableSerialConstructorChecks&quot;;</span><a href="#l94"></a>
<span id="l95">                    return &quot;true&quot;.equals(System.getProperty(prop))</span><a href="#l95"></a>
<span id="l96">                            ? Boolean.TRUE : Boolean.FALSE;</span><a href="#l96"></a>
<span id="l97">                }</span><a href="#l97"></a>
<span id="l98">            }</span><a href="#l98"></a>
<span id="l99">        ).booleanValue();</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    /** reflection factory for obtaining serialization constructors */</span><a href="#l101"></a>
<span id="l102">    private static final ReflectionFactory reflFactory =</span><a href="#l102"></a>
<span id="l103">        AccessController.doPrivileged(</span><a href="#l103"></a>
<span id="l104">            new ReflectionFactory.GetReflectionFactoryAction());</span><a href="#l104"></a>
<span id="l105"></span><a href="#l105"></a>
<span id="l106">    private static class Caches {</span><a href="#l106"></a>
<span id="l107">        /** cache mapping local classes -&gt; descriptors */</span><a href="#l107"></a>
<span id="l108">        static final ConcurrentMap&lt;WeakClassKey,Reference&lt;?&gt;&gt; localDescs =</span><a href="#l108"></a>
<span id="l109">            new ConcurrentHashMap&lt;&gt;();</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">        /** cache mapping field group/local desc pairs -&gt; field reflectors */</span><a href="#l111"></a>
<span id="l112">        static final ConcurrentMap&lt;FieldReflectorKey,Reference&lt;?&gt;&gt; reflectors =</span><a href="#l112"></a>
<span id="l113">            new ConcurrentHashMap&lt;&gt;();</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">        /** queue for WeakReferences to local classes */</span><a href="#l115"></a>
<span id="l116">        private static final ReferenceQueue&lt;Class&lt;?&gt;&gt; localDescsQueue =</span><a href="#l116"></a>
<span id="l117">            new ReferenceQueue&lt;&gt;();</span><a href="#l117"></a>
<span id="l118">        /** queue for WeakReferences to field reflectors keys */</span><a href="#l118"></a>
<span id="l119">        private static final ReferenceQueue&lt;Class&lt;?&gt;&gt; reflectorsQueue =</span><a href="#l119"></a>
<span id="l120">            new ReferenceQueue&lt;&gt;();</span><a href="#l120"></a>
<span id="l121">    }</span><a href="#l121"></a>
<span id="l122"></span><a href="#l122"></a>
<span id="l123">    /** class associated with this descriptor (if any) */</span><a href="#l123"></a>
<span id="l124">    private Class&lt;?&gt; cl;</span><a href="#l124"></a>
<span id="l125">    /** name of class represented by this descriptor */</span><a href="#l125"></a>
<span id="l126">    private String name;</span><a href="#l126"></a>
<span id="l127">    /** serialVersionUID of represented class (null if not computed yet) */</span><a href="#l127"></a>
<span id="l128">    private volatile Long suid;</span><a href="#l128"></a>
<span id="l129"></span><a href="#l129"></a>
<span id="l130">    /** true if represents dynamic proxy class */</span><a href="#l130"></a>
<span id="l131">    private boolean isProxy;</span><a href="#l131"></a>
<span id="l132">    /** true if represents enum type */</span><a href="#l132"></a>
<span id="l133">    private boolean isEnum;</span><a href="#l133"></a>
<span id="l134">    /** true if represented class implements Serializable */</span><a href="#l134"></a>
<span id="l135">    private boolean serializable;</span><a href="#l135"></a>
<span id="l136">    /** true if represented class implements Externalizable */</span><a href="#l136"></a>
<span id="l137">    private boolean externalizable;</span><a href="#l137"></a>
<span id="l138">    /** true if desc has data written by class-defined writeObject method */</span><a href="#l138"></a>
<span id="l139">    private boolean hasWriteObjectData;</span><a href="#l139"></a>
<span id="l140">    /**</span><a href="#l140"></a>
<span id="l141">     * true if desc has externalizable data written in block data format; this</span><a href="#l141"></a>
<span id="l142">     * must be true by default to accommodate ObjectInputStream subclasses which</span><a href="#l142"></a>
<span id="l143">     * override readClassDescriptor() to return class descriptors obtained from</span><a href="#l143"></a>
<span id="l144">     * ObjectStreamClass.lookup() (see 4461737)</span><a href="#l144"></a>
<span id="l145">     */</span><a href="#l145"></a>
<span id="l146">    private boolean hasBlockExternalData = true;</span><a href="#l146"></a>
<span id="l147"></span><a href="#l147"></a>
<span id="l148">    /**</span><a href="#l148"></a>
<span id="l149">     * Contains information about InvalidClassException instances to be thrown</span><a href="#l149"></a>
<span id="l150">     * when attempting operations on an invalid class. Note that instances of</span><a href="#l150"></a>
<span id="l151">     * this class are immutable and are potentially shared among</span><a href="#l151"></a>
<span id="l152">     * ObjectStreamClass instances.</span><a href="#l152"></a>
<span id="l153">     */</span><a href="#l153"></a>
<span id="l154">    private static class ExceptionInfo {</span><a href="#l154"></a>
<span id="l155">        private final String className;</span><a href="#l155"></a>
<span id="l156">        private final String message;</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">        ExceptionInfo(String cn, String msg) {</span><a href="#l158"></a>
<span id="l159">            className = cn;</span><a href="#l159"></a>
<span id="l160">            message = msg;</span><a href="#l160"></a>
<span id="l161">        }</span><a href="#l161"></a>
<span id="l162"></span><a href="#l162"></a>
<span id="l163">        /**</span><a href="#l163"></a>
<span id="l164">         * Returns (does not throw) an InvalidClassException instance created</span><a href="#l164"></a>
<span id="l165">         * from the information in this object, suitable for being thrown by</span><a href="#l165"></a>
<span id="l166">         * the caller.</span><a href="#l166"></a>
<span id="l167">         */</span><a href="#l167"></a>
<span id="l168">        InvalidClassException newInvalidClassException() {</span><a href="#l168"></a>
<span id="l169">            return new InvalidClassException(className, message);</span><a href="#l169"></a>
<span id="l170">        }</span><a href="#l170"></a>
<span id="l171">    }</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">    /** exception (if any) thrown while attempting to resolve class */</span><a href="#l173"></a>
<span id="l174">    private ClassNotFoundException resolveEx;</span><a href="#l174"></a>
<span id="l175">    /** exception (if any) to throw if non-enum deserialization attempted */</span><a href="#l175"></a>
<span id="l176">    private ExceptionInfo deserializeEx;</span><a href="#l176"></a>
<span id="l177">    /** exception (if any) to throw if non-enum serialization attempted */</span><a href="#l177"></a>
<span id="l178">    private ExceptionInfo serializeEx;</span><a href="#l178"></a>
<span id="l179">    /** exception (if any) to throw if default serialization attempted */</span><a href="#l179"></a>
<span id="l180">    private ExceptionInfo defaultSerializeEx;</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    /** serializable fields */</span><a href="#l182"></a>
<span id="l183">    private ObjectStreamField[] fields;</span><a href="#l183"></a>
<span id="l184">    /** aggregate marshalled size of primitive fields */</span><a href="#l184"></a>
<span id="l185">    private int primDataSize;</span><a href="#l185"></a>
<span id="l186">    /** number of non-primitive fields */</span><a href="#l186"></a>
<span id="l187">    private int numObjFields;</span><a href="#l187"></a>
<span id="l188">    /** reflector for setting/getting serializable field values */</span><a href="#l188"></a>
<span id="l189">    private FieldReflector fieldRefl;</span><a href="#l189"></a>
<span id="l190">    /** data layout of serialized objects described by this class desc */</span><a href="#l190"></a>
<span id="l191">    private volatile ClassDataSlot[] dataLayout;</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    /** serialization-appropriate constructor, or null if none */</span><a href="#l193"></a>
<span id="l194">    private Constructor&lt;?&gt; cons;</span><a href="#l194"></a>
<span id="l195">    /** protection domains that need to be checked when calling the constructor */</span><a href="#l195"></a>
<span id="l196">    private ProtectionDomain[] domains;</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    /** class-defined writeObject method, or null if none */</span><a href="#l198"></a>
<span id="l199">    private Method writeObjectMethod;</span><a href="#l199"></a>
<span id="l200">    /** class-defined readObject method, or null if none */</span><a href="#l200"></a>
<span id="l201">    private Method readObjectMethod;</span><a href="#l201"></a>
<span id="l202">    /** class-defined readObjectNoData method, or null if none */</span><a href="#l202"></a>
<span id="l203">    private Method readObjectNoDataMethod;</span><a href="#l203"></a>
<span id="l204">    /** class-defined writeReplace method, or null if none */</span><a href="#l204"></a>
<span id="l205">    private Method writeReplaceMethod;</span><a href="#l205"></a>
<span id="l206">    /** class-defined readResolve method, or null if none */</span><a href="#l206"></a>
<span id="l207">    private Method readResolveMethod;</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">    /** local class descriptor for represented class (may point to self) */</span><a href="#l209"></a>
<span id="l210">    private ObjectStreamClass localDesc;</span><a href="#l210"></a>
<span id="l211">    /** superclass descriptor appearing in stream */</span><a href="#l211"></a>
<span id="l212">    private ObjectStreamClass superDesc;</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">    /** true if, and only if, the object has been correctly initialized */</span><a href="#l214"></a>
<span id="l215">    private boolean initialized;</span><a href="#l215"></a>
<span id="l216"></span><a href="#l216"></a>
<span id="l217">    /**</span><a href="#l217"></a>
<span id="l218">     * Initializes native code.</span><a href="#l218"></a>
<span id="l219">     */</span><a href="#l219"></a>
<span id="l220">    private static native void initNative();</span><a href="#l220"></a>
<span id="l221">    static {</span><a href="#l221"></a>
<span id="l222">        initNative();</span><a href="#l222"></a>
<span id="l223">    }</span><a href="#l223"></a>
<span id="l224"></span><a href="#l224"></a>
<span id="l225">    /**</span><a href="#l225"></a>
<span id="l226">     * Find the descriptor for a class that can be serialized.  Creates an</span><a href="#l226"></a>
<span id="l227">     * ObjectStreamClass instance if one does not exist yet for class. Null is</span><a href="#l227"></a>
<span id="l228">     * returned if the specified class does not implement java.io.Serializable</span><a href="#l228"></a>
<span id="l229">     * or java.io.Externalizable.</span><a href="#l229"></a>
<span id="l230">     *</span><a href="#l230"></a>
<span id="l231">     * @param   cl class for which to get the descriptor</span><a href="#l231"></a>
<span id="l232">     * @return  the class descriptor for the specified class</span><a href="#l232"></a>
<span id="l233">     */</span><a href="#l233"></a>
<span id="l234">    public static ObjectStreamClass lookup(Class&lt;?&gt; cl) {</span><a href="#l234"></a>
<span id="l235">        return lookup(cl, false);</span><a href="#l235"></a>
<span id="l236">    }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">    /**</span><a href="#l238"></a>
<span id="l239">     * Returns the descriptor for any class, regardless of whether it</span><a href="#l239"></a>
<span id="l240">     * implements {@link Serializable}.</span><a href="#l240"></a>
<span id="l241">     *</span><a href="#l241"></a>
<span id="l242">     * @param        cl class for which to get the descriptor</span><a href="#l242"></a>
<span id="l243">     * @return       the class descriptor for the specified class</span><a href="#l243"></a>
<span id="l244">     * @since 1.6</span><a href="#l244"></a>
<span id="l245">     */</span><a href="#l245"></a>
<span id="l246">    public static ObjectStreamClass lookupAny(Class&lt;?&gt; cl) {</span><a href="#l246"></a>
<span id="l247">        return lookup(cl, true);</span><a href="#l247"></a>
<span id="l248">    }</span><a href="#l248"></a>
<span id="l249"></span><a href="#l249"></a>
<span id="l250">    /**</span><a href="#l250"></a>
<span id="l251">     * Returns the name of the class described by this descriptor.</span><a href="#l251"></a>
<span id="l252">     * This method returns the name of the class in the format that</span><a href="#l252"></a>
<span id="l253">     * is used by the {@link Class#getName} method.</span><a href="#l253"></a>
<span id="l254">     *</span><a href="#l254"></a>
<span id="l255">     * @return a string representing the name of the class</span><a href="#l255"></a>
<span id="l256">     */</span><a href="#l256"></a>
<span id="l257">    public String getName() {</span><a href="#l257"></a>
<span id="l258">        return name;</span><a href="#l258"></a>
<span id="l259">    }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    /**</span><a href="#l261"></a>
<span id="l262">     * Return the serialVersionUID for this class.  The serialVersionUID</span><a href="#l262"></a>
<span id="l263">     * defines a set of classes all with the same name that have evolved from a</span><a href="#l263"></a>
<span id="l264">     * common root class and agree to be serialized and deserialized using a</span><a href="#l264"></a>
<span id="l265">     * common format.  NonSerializable classes have a serialVersionUID of 0L.</span><a href="#l265"></a>
<span id="l266">     *</span><a href="#l266"></a>
<span id="l267">     * @return  the SUID of the class described by this descriptor</span><a href="#l267"></a>
<span id="l268">     */</span><a href="#l268"></a>
<span id="l269">    public long getSerialVersionUID() {</span><a href="#l269"></a>
<span id="l270">        // REMIND: synchronize instead of relying on volatile?</span><a href="#l270"></a>
<span id="l271">        if (suid == null) {</span><a href="#l271"></a>
<span id="l272">            suid = AccessController.doPrivileged(</span><a href="#l272"></a>
<span id="l273">                new PrivilegedAction&lt;Long&gt;() {</span><a href="#l273"></a>
<span id="l274">                    public Long run() {</span><a href="#l274"></a>
<span id="l275">                        return computeDefaultSUID(cl);</span><a href="#l275"></a>
<span id="l276">                    }</span><a href="#l276"></a>
<span id="l277">                }</span><a href="#l277"></a>
<span id="l278">            );</span><a href="#l278"></a>
<span id="l279">        }</span><a href="#l279"></a>
<span id="l280">        return suid.longValue();</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    /**</span><a href="#l283"></a>
<span id="l284">     * Return the class in the local VM that this version is mapped to.  Null</span><a href="#l284"></a>
<span id="l285">     * is returned if there is no corresponding local class.</span><a href="#l285"></a>
<span id="l286">     *</span><a href="#l286"></a>
<span id="l287">     * @return  the &lt;code&gt;Class&lt;/code&gt; instance that this descriptor represents</span><a href="#l287"></a>
<span id="l288">     */</span><a href="#l288"></a>
<span id="l289">    @CallerSensitive</span><a href="#l289"></a>
<span id="l290">    public Class&lt;?&gt; forClass() {</span><a href="#l290"></a>
<span id="l291">        if (cl == null) {</span><a href="#l291"></a>
<span id="l292">            return null;</span><a href="#l292"></a>
<span id="l293">        }</span><a href="#l293"></a>
<span id="l294">        requireInitialized();</span><a href="#l294"></a>
<span id="l295">        if (System.getSecurityManager() != null) {</span><a href="#l295"></a>
<span id="l296">            Class&lt;?&gt; caller = Reflection.getCallerClass();</span><a href="#l296"></a>
<span id="l297">            if (ReflectUtil.needsPackageAccessCheck(caller.getClassLoader(), cl.getClassLoader())) {</span><a href="#l297"></a>
<span id="l298">                ReflectUtil.checkPackageAccess(cl);</span><a href="#l298"></a>
<span id="l299">            }</span><a href="#l299"></a>
<span id="l300">        }</span><a href="#l300"></a>
<span id="l301">        return cl;</span><a href="#l301"></a>
<span id="l302">    }</span><a href="#l302"></a>
<span id="l303"></span><a href="#l303"></a>
<span id="l304">    /**</span><a href="#l304"></a>
<span id="l305">     * Return an array of the fields of this serializable class.</span><a href="#l305"></a>
<span id="l306">     *</span><a href="#l306"></a>
<span id="l307">     * @return  an array containing an element for each persistent field of</span><a href="#l307"></a>
<span id="l308">     *          this class. Returns an array of length zero if there are no</span><a href="#l308"></a>
<span id="l309">     *          fields.</span><a href="#l309"></a>
<span id="l310">     * @since 1.2</span><a href="#l310"></a>
<span id="l311">     */</span><a href="#l311"></a>
<span id="l312">    public ObjectStreamField[] getFields() {</span><a href="#l312"></a>
<span id="l313">        return getFields(true);</span><a href="#l313"></a>
<span id="l314">    }</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    /**</span><a href="#l316"></a>
<span id="l317">     * Get the field of this class by name.</span><a href="#l317"></a>
<span id="l318">     *</span><a href="#l318"></a>
<span id="l319">     * @param   name the name of the data field to look for</span><a href="#l319"></a>
<span id="l320">     * @return  The ObjectStreamField object of the named field or null if</span><a href="#l320"></a>
<span id="l321">     *          there is no such named field.</span><a href="#l321"></a>
<span id="l322">     */</span><a href="#l322"></a>
<span id="l323">    public ObjectStreamField getField(String name) {</span><a href="#l323"></a>
<span id="l324">        return getField(name, null);</span><a href="#l324"></a>
<span id="l325">    }</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">    /**</span><a href="#l327"></a>
<span id="l328">     * Return a string describing this ObjectStreamClass.</span><a href="#l328"></a>
<span id="l329">     */</span><a href="#l329"></a>
<span id="l330">    public String toString() {</span><a href="#l330"></a>
<span id="l331">        return name + &quot;: static final long serialVersionUID = &quot; +</span><a href="#l331"></a>
<span id="l332">            getSerialVersionUID() + &quot;L;&quot;;</span><a href="#l332"></a>
<span id="l333">    }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">    /**</span><a href="#l335"></a>
<span id="l336">     * Looks up and returns class descriptor for given class, or null if class</span><a href="#l336"></a>
<span id="l337">     * is non-serializable and &quot;all&quot; is set to false.</span><a href="#l337"></a>
<span id="l338">     *</span><a href="#l338"></a>
<span id="l339">     * @param   cl class to look up</span><a href="#l339"></a>
<span id="l340">     * @param   all if true, return descriptors for all classes; if false, only</span><a href="#l340"></a>
<span id="l341">     *          return descriptors for serializable classes</span><a href="#l341"></a>
<span id="l342">     */</span><a href="#l342"></a>
<span id="l343">    static ObjectStreamClass lookup(Class&lt;?&gt; cl, boolean all) {</span><a href="#l343"></a>
<span id="l344">        if (!(all || Serializable.class.isAssignableFrom(cl))) {</span><a href="#l344"></a>
<span id="l345">            return null;</span><a href="#l345"></a>
<span id="l346">        }</span><a href="#l346"></a>
<span id="l347">        processQueue(Caches.localDescsQueue, Caches.localDescs);</span><a href="#l347"></a>
<span id="l348">        WeakClassKey key = new WeakClassKey(cl, Caches.localDescsQueue);</span><a href="#l348"></a>
<span id="l349">        Reference&lt;?&gt; ref = Caches.localDescs.get(key);</span><a href="#l349"></a>
<span id="l350">        Object entry = null;</span><a href="#l350"></a>
<span id="l351">        if (ref != null) {</span><a href="#l351"></a>
<span id="l352">            entry = ref.get();</span><a href="#l352"></a>
<span id="l353">        }</span><a href="#l353"></a>
<span id="l354">        EntryFuture future = null;</span><a href="#l354"></a>
<span id="l355">        if (entry == null) {</span><a href="#l355"></a>
<span id="l356">            EntryFuture newEntry = new EntryFuture();</span><a href="#l356"></a>
<span id="l357">            Reference&lt;?&gt; newRef = new SoftReference&lt;&gt;(newEntry);</span><a href="#l357"></a>
<span id="l358">            do {</span><a href="#l358"></a>
<span id="l359">                if (ref != null) {</span><a href="#l359"></a>
<span id="l360">                    Caches.localDescs.remove(key, ref);</span><a href="#l360"></a>
<span id="l361">                }</span><a href="#l361"></a>
<span id="l362">                ref = Caches.localDescs.putIfAbsent(key, newRef);</span><a href="#l362"></a>
<span id="l363">                if (ref != null) {</span><a href="#l363"></a>
<span id="l364">                    entry = ref.get();</span><a href="#l364"></a>
<span id="l365">                }</span><a href="#l365"></a>
<span id="l366">            } while (ref != null &amp;&amp; entry == null);</span><a href="#l366"></a>
<span id="l367">            if (entry == null) {</span><a href="#l367"></a>
<span id="l368">                future = newEntry;</span><a href="#l368"></a>
<span id="l369">            }</span><a href="#l369"></a>
<span id="l370">        }</span><a href="#l370"></a>
<span id="l371"></span><a href="#l371"></a>
<span id="l372">        if (entry instanceof ObjectStreamClass) {  // check common case first</span><a href="#l372"></a>
<span id="l373">            return (ObjectStreamClass) entry;</span><a href="#l373"></a>
<span id="l374">        }</span><a href="#l374"></a>
<span id="l375">        if (entry instanceof EntryFuture) {</span><a href="#l375"></a>
<span id="l376">            future = (EntryFuture) entry;</span><a href="#l376"></a>
<span id="l377">            if (future.getOwner() == Thread.currentThread()) {</span><a href="#l377"></a>
<span id="l378">                /*</span><a href="#l378"></a>
<span id="l379">                 * Handle nested call situation described by 4803747: waiting</span><a href="#l379"></a>
<span id="l380">                 * for future value to be set by a lookup() call further up the</span><a href="#l380"></a>
<span id="l381">                 * stack will result in deadlock, so calculate and set the</span><a href="#l381"></a>
<span id="l382">                 * future value here instead.</span><a href="#l382"></a>
<span id="l383">                 */</span><a href="#l383"></a>
<span id="l384">                entry = null;</span><a href="#l384"></a>
<span id="l385">            } else {</span><a href="#l385"></a>
<span id="l386">                entry = future.get();</span><a href="#l386"></a>
<span id="l387">            }</span><a href="#l387"></a>
<span id="l388">        }</span><a href="#l388"></a>
<span id="l389">        if (entry == null) {</span><a href="#l389"></a>
<span id="l390">            try {</span><a href="#l390"></a>
<span id="l391">                entry = new ObjectStreamClass(cl);</span><a href="#l391"></a>
<span id="l392">            } catch (Throwable th) {</span><a href="#l392"></a>
<span id="l393">                entry = th;</span><a href="#l393"></a>
<span id="l394">            }</span><a href="#l394"></a>
<span id="l395">            if (future.set(entry)) {</span><a href="#l395"></a>
<span id="l396">                Caches.localDescs.put(key, new SoftReference&lt;Object&gt;(entry));</span><a href="#l396"></a>
<span id="l397">            } else {</span><a href="#l397"></a>
<span id="l398">                // nested lookup call already set future</span><a href="#l398"></a>
<span id="l399">                entry = future.get();</span><a href="#l399"></a>
<span id="l400">            }</span><a href="#l400"></a>
<span id="l401">        }</span><a href="#l401"></a>
<span id="l402"></span><a href="#l402"></a>
<span id="l403">        if (entry instanceof ObjectStreamClass) {</span><a href="#l403"></a>
<span id="l404">            return (ObjectStreamClass) entry;</span><a href="#l404"></a>
<span id="l405">        } else if (entry instanceof RuntimeException) {</span><a href="#l405"></a>
<span id="l406">            throw (RuntimeException) entry;</span><a href="#l406"></a>
<span id="l407">        } else if (entry instanceof Error) {</span><a href="#l407"></a>
<span id="l408">            throw (Error) entry;</span><a href="#l408"></a>
<span id="l409">        } else {</span><a href="#l409"></a>
<span id="l410">            throw new InternalError(&quot;unexpected entry: &quot; + entry);</span><a href="#l410"></a>
<span id="l411">        }</span><a href="#l411"></a>
<span id="l412">    }</span><a href="#l412"></a>
<span id="l413"></span><a href="#l413"></a>
<span id="l414">    /**</span><a href="#l414"></a>
<span id="l415">     * Placeholder used in class descriptor and field reflector lookup tables</span><a href="#l415"></a>
<span id="l416">     * for an entry in the process of being initialized.  (Internal) callers</span><a href="#l416"></a>
<span id="l417">     * which receive an EntryFuture belonging to another thread as the result</span><a href="#l417"></a>
<span id="l418">     * of a lookup should call the get() method of the EntryFuture; this will</span><a href="#l418"></a>
<span id="l419">     * return the actual entry once it is ready for use and has been set().  To</span><a href="#l419"></a>
<span id="l420">     * conserve objects, EntryFutures synchronize on themselves.</span><a href="#l420"></a>
<span id="l421">     */</span><a href="#l421"></a>
<span id="l422">    private static class EntryFuture {</span><a href="#l422"></a>
<span id="l423"></span><a href="#l423"></a>
<span id="l424">        private static final Object unset = new Object();</span><a href="#l424"></a>
<span id="l425">        private final Thread owner = Thread.currentThread();</span><a href="#l425"></a>
<span id="l426">        private Object entry = unset;</span><a href="#l426"></a>
<span id="l427"></span><a href="#l427"></a>
<span id="l428">        /**</span><a href="#l428"></a>
<span id="l429">         * Attempts to set the value contained by this EntryFuture.  If the</span><a href="#l429"></a>
<span id="l430">         * EntryFuture's value has not been set already, then the value is</span><a href="#l430"></a>
<span id="l431">         * saved, any callers blocked in the get() method are notified, and</span><a href="#l431"></a>
<span id="l432">         * true is returned.  If the value has already been set, then no saving</span><a href="#l432"></a>
<span id="l433">         * or notification occurs, and false is returned.</span><a href="#l433"></a>
<span id="l434">         */</span><a href="#l434"></a>
<span id="l435">        synchronized boolean set(Object entry) {</span><a href="#l435"></a>
<span id="l436">            if (this.entry != unset) {</span><a href="#l436"></a>
<span id="l437">                return false;</span><a href="#l437"></a>
<span id="l438">            }</span><a href="#l438"></a>
<span id="l439">            this.entry = entry;</span><a href="#l439"></a>
<span id="l440">            notifyAll();</span><a href="#l440"></a>
<span id="l441">            return true;</span><a href="#l441"></a>
<span id="l442">        }</span><a href="#l442"></a>
<span id="l443"></span><a href="#l443"></a>
<span id="l444">        /**</span><a href="#l444"></a>
<span id="l445">         * Returns the value contained by this EntryFuture, blocking if</span><a href="#l445"></a>
<span id="l446">         * necessary until a value is set.</span><a href="#l446"></a>
<span id="l447">         */</span><a href="#l447"></a>
<span id="l448">        synchronized Object get() {</span><a href="#l448"></a>
<span id="l449">            boolean interrupted = false;</span><a href="#l449"></a>
<span id="l450">            while (entry == unset) {</span><a href="#l450"></a>
<span id="l451">                try {</span><a href="#l451"></a>
<span id="l452">                    wait();</span><a href="#l452"></a>
<span id="l453">                } catch (InterruptedException ex) {</span><a href="#l453"></a>
<span id="l454">                    interrupted = true;</span><a href="#l454"></a>
<span id="l455">                }</span><a href="#l455"></a>
<span id="l456">            }</span><a href="#l456"></a>
<span id="l457">            if (interrupted) {</span><a href="#l457"></a>
<span id="l458">                AccessController.doPrivileged(</span><a href="#l458"></a>
<span id="l459">                    new PrivilegedAction&lt;Void&gt;() {</span><a href="#l459"></a>
<span id="l460">                        public Void run() {</span><a href="#l460"></a>
<span id="l461">                            Thread.currentThread().interrupt();</span><a href="#l461"></a>
<span id="l462">                            return null;</span><a href="#l462"></a>
<span id="l463">                        }</span><a href="#l463"></a>
<span id="l464">                    }</span><a href="#l464"></a>
<span id="l465">                );</span><a href="#l465"></a>
<span id="l466">            }</span><a href="#l466"></a>
<span id="l467">            return entry;</span><a href="#l467"></a>
<span id="l468">        }</span><a href="#l468"></a>
<span id="l469"></span><a href="#l469"></a>
<span id="l470">        /**</span><a href="#l470"></a>
<span id="l471">         * Returns the thread that created this EntryFuture.</span><a href="#l471"></a>
<span id="l472">         */</span><a href="#l472"></a>
<span id="l473">        Thread getOwner() {</span><a href="#l473"></a>
<span id="l474">            return owner;</span><a href="#l474"></a>
<span id="l475">        }</span><a href="#l475"></a>
<span id="l476">    }</span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">    /**</span><a href="#l478"></a>
<span id="l479">     * Creates local class descriptor representing given class.</span><a href="#l479"></a>
<span id="l480">     */</span><a href="#l480"></a>
<span id="l481">    private ObjectStreamClass(final Class&lt;?&gt; cl) {</span><a href="#l481"></a>
<span id="l482">        this.cl = cl;</span><a href="#l482"></a>
<span id="l483">        name = cl.getName();</span><a href="#l483"></a>
<span id="l484">        isProxy = Proxy.isProxyClass(cl);</span><a href="#l484"></a>
<span id="l485">        isEnum = Enum.class.isAssignableFrom(cl);</span><a href="#l485"></a>
<span id="l486">        serializable = Serializable.class.isAssignableFrom(cl);</span><a href="#l486"></a>
<span id="l487">        externalizable = Externalizable.class.isAssignableFrom(cl);</span><a href="#l487"></a>
<span id="l488"></span><a href="#l488"></a>
<span id="l489">        Class&lt;?&gt; superCl = cl.getSuperclass();</span><a href="#l489"></a>
<span id="l490">        superDesc = (superCl != null) ? lookup(superCl, false) : null;</span><a href="#l490"></a>
<span id="l491">        localDesc = this;</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">        if (serializable) {</span><a href="#l493"></a>
<span id="l494">            AccessController.doPrivileged(new PrivilegedAction&lt;Void&gt;() {</span><a href="#l494"></a>
<span id="l495">                public Void run() {</span><a href="#l495"></a>
<span id="l496">                    if (isEnum) {</span><a href="#l496"></a>
<span id="l497">                        suid = Long.valueOf(0);</span><a href="#l497"></a>
<span id="l498">                        fields = NO_FIELDS;</span><a href="#l498"></a>
<span id="l499">                        return null;</span><a href="#l499"></a>
<span id="l500">                    }</span><a href="#l500"></a>
<span id="l501">                    if (cl.isArray()) {</span><a href="#l501"></a>
<span id="l502">                        fields = NO_FIELDS;</span><a href="#l502"></a>
<span id="l503">                        return null;</span><a href="#l503"></a>
<span id="l504">                    }</span><a href="#l504"></a>
<span id="l505"></span><a href="#l505"></a>
<span id="l506">                    suid = getDeclaredSUID(cl);</span><a href="#l506"></a>
<span id="l507">                    try {</span><a href="#l507"></a>
<span id="l508">                        fields = getSerialFields(cl);</span><a href="#l508"></a>
<span id="l509">                        computeFieldOffsets();</span><a href="#l509"></a>
<span id="l510">                    } catch (InvalidClassException e) {</span><a href="#l510"></a>
<span id="l511">                        serializeEx = deserializeEx =</span><a href="#l511"></a>
<span id="l512">                            new ExceptionInfo(e.classname, e.getMessage());</span><a href="#l512"></a>
<span id="l513">                        fields = NO_FIELDS;</span><a href="#l513"></a>
<span id="l514">                    }</span><a href="#l514"></a>
<span id="l515"></span><a href="#l515"></a>
<span id="l516">                    if (externalizable) {</span><a href="#l516"></a>
<span id="l517">                        cons = getExternalizableConstructor(cl);</span><a href="#l517"></a>
<span id="l518">                    } else {</span><a href="#l518"></a>
<span id="l519">                        cons = getSerializableConstructor(cl);</span><a href="#l519"></a>
<span id="l520">                        writeObjectMethod = getPrivateMethod(cl, &quot;writeObject&quot;,</span><a href="#l520"></a>
<span id="l521">                            new Class&lt;?&gt;[] { ObjectOutputStream.class },</span><a href="#l521"></a>
<span id="l522">                            Void.TYPE);</span><a href="#l522"></a>
<span id="l523">                        readObjectMethod = getPrivateMethod(cl, &quot;readObject&quot;,</span><a href="#l523"></a>
<span id="l524">                            new Class&lt;?&gt;[] { ObjectInputStream.class },</span><a href="#l524"></a>
<span id="l525">                            Void.TYPE);</span><a href="#l525"></a>
<span id="l526">                        readObjectNoDataMethod = getPrivateMethod(</span><a href="#l526"></a>
<span id="l527">                            cl, &quot;readObjectNoData&quot;, null, Void.TYPE);</span><a href="#l527"></a>
<span id="l528">                        hasWriteObjectData = (writeObjectMethod != null);</span><a href="#l528"></a>
<span id="l529">                    }</span><a href="#l529"></a>
<span id="l530">                    domains = getProtectionDomains(cons, cl);</span><a href="#l530"></a>
<span id="l531">                    writeReplaceMethod = getInheritableMethod(</span><a href="#l531"></a>
<span id="l532">                        cl, &quot;writeReplace&quot;, null, Object.class);</span><a href="#l532"></a>
<span id="l533">                    readResolveMethod = getInheritableMethod(</span><a href="#l533"></a>
<span id="l534">                        cl, &quot;readResolve&quot;, null, Object.class);</span><a href="#l534"></a>
<span id="l535">                    return null;</span><a href="#l535"></a>
<span id="l536">                }</span><a href="#l536"></a>
<span id="l537">            });</span><a href="#l537"></a>
<span id="l538">        } else {</span><a href="#l538"></a>
<span id="l539">            suid = Long.valueOf(0);</span><a href="#l539"></a>
<span id="l540">            fields = NO_FIELDS;</span><a href="#l540"></a>
<span id="l541">        }</span><a href="#l541"></a>
<span id="l542"></span><a href="#l542"></a>
<span id="l543">        try {</span><a href="#l543"></a>
<span id="l544">            fieldRefl = getReflector(fields, this);</span><a href="#l544"></a>
<span id="l545">        } catch (InvalidClassException ex) {</span><a href="#l545"></a>
<span id="l546">            // field mismatches impossible when matching local fields vs. self</span><a href="#l546"></a>
<span id="l547">            throw new InternalError(ex);</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">        if (deserializeEx == null) {</span><a href="#l550"></a>
<span id="l551">            if (isEnum) {</span><a href="#l551"></a>
<span id="l552">                deserializeEx = new ExceptionInfo(name, &quot;enum type&quot;);</span><a href="#l552"></a>
<span id="l553">            } else if (cons == null) {</span><a href="#l553"></a>
<span id="l554">                deserializeEx = new ExceptionInfo(name, &quot;no valid constructor&quot;);</span><a href="#l554"></a>
<span id="l555">            }</span><a href="#l555"></a>
<span id="l556">        }</span><a href="#l556"></a>
<span id="l557">        for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l557"></a>
<span id="l558">            if (fields[i].getField() == null) {</span><a href="#l558"></a>
<span id="l559">                defaultSerializeEx = new ExceptionInfo(</span><a href="#l559"></a>
<span id="l560">                    name, &quot;unmatched serializable field(s) declared&quot;);</span><a href="#l560"></a>
<span id="l561">            }</span><a href="#l561"></a>
<span id="l562">        }</span><a href="#l562"></a>
<span id="l563">        initialized = true;</span><a href="#l563"></a>
<span id="l564">    }</span><a href="#l564"></a>
<span id="l565"></span><a href="#l565"></a>
<span id="l566">    /**</span><a href="#l566"></a>
<span id="l567">     * Creates blank class descriptor which should be initialized via a</span><a href="#l567"></a>
<span id="l568">     * subsequent call to initProxy(), initNonProxy() or readNonProxy().</span><a href="#l568"></a>
<span id="l569">     */</span><a href="#l569"></a>
<span id="l570">    ObjectStreamClass() {</span><a href="#l570"></a>
<span id="l571">    }</span><a href="#l571"></a>
<span id="l572"></span><a href="#l572"></a>
<span id="l573">    /**</span><a href="#l573"></a>
<span id="l574">     * Creates a PermissionDomain that grants no permission.</span><a href="#l574"></a>
<span id="l575">     */</span><a href="#l575"></a>
<span id="l576">    private ProtectionDomain noPermissionsDomain() {</span><a href="#l576"></a>
<span id="l577">        PermissionCollection perms = new Permissions();</span><a href="#l577"></a>
<span id="l578">        perms.setReadOnly();</span><a href="#l578"></a>
<span id="l579">        return new ProtectionDomain(null, perms);</span><a href="#l579"></a>
<span id="l580">    }</span><a href="#l580"></a>
<span id="l581"></span><a href="#l581"></a>
<span id="l582">    /**</span><a href="#l582"></a>
<span id="l583">     * Aggregate the ProtectionDomains of all the classes that separate</span><a href="#l583"></a>
<span id="l584">     * a concrete class {@code cl} from its ancestor's class declaring</span><a href="#l584"></a>
<span id="l585">     * a constructor {@code cons}.</span><a href="#l585"></a>
<span id="l586">     *</span><a href="#l586"></a>
<span id="l587">     * If {@code cl} is defined by the boot loader, or the constructor</span><a href="#l587"></a>
<span id="l588">     * {@code cons} is declared by {@code cl}, or if there is no security</span><a href="#l588"></a>
<span id="l589">     * manager, then this method does nothing and {@code null} is returned.</span><a href="#l589"></a>
<span id="l590">     *</span><a href="#l590"></a>
<span id="l591">     * @param cons A constructor declared by {@code cl} or one of its</span><a href="#l591"></a>
<span id="l592">     *             ancestors.</span><a href="#l592"></a>
<span id="l593">     * @param cl A concrete class, which is either the class declaring</span><a href="#l593"></a>
<span id="l594">     *           the constructor {@code cons}, or a serializable subclass</span><a href="#l594"></a>
<span id="l595">     *           of that class.</span><a href="#l595"></a>
<span id="l596">     * @return An array of ProtectionDomain representing the set of</span><a href="#l596"></a>
<span id="l597">     *         ProtectionDomain that separate the concrete class {@code cl}</span><a href="#l597"></a>
<span id="l598">     *         from its ancestor's declaring {@code cons}, or {@code null}.</span><a href="#l598"></a>
<span id="l599">     */</span><a href="#l599"></a>
<span id="l600">    private ProtectionDomain[] getProtectionDomains(Constructor&lt;?&gt; cons,</span><a href="#l600"></a>
<span id="l601">                                                    Class&lt;?&gt; cl) {</span><a href="#l601"></a>
<span id="l602">        ProtectionDomain[] domains = null;</span><a href="#l602"></a>
<span id="l603">        if (cons != null &amp;&amp; cl.getClassLoader() != null</span><a href="#l603"></a>
<span id="l604">                &amp;&amp; System.getSecurityManager() != null) {</span><a href="#l604"></a>
<span id="l605">            Class&lt;?&gt; cls = cl;</span><a href="#l605"></a>
<span id="l606">            Class&lt;?&gt; fnscl = cons.getDeclaringClass();</span><a href="#l606"></a>
<span id="l607">            Set&lt;ProtectionDomain&gt; pds = null;</span><a href="#l607"></a>
<span id="l608">            while (cls != fnscl) {</span><a href="#l608"></a>
<span id="l609">                ProtectionDomain pd = cls.getProtectionDomain();</span><a href="#l609"></a>
<span id="l610">                if (pd != null) {</span><a href="#l610"></a>
<span id="l611">                    if (pds == null) pds = new HashSet&lt;&gt;();</span><a href="#l611"></a>
<span id="l612">                    pds.add(pd);</span><a href="#l612"></a>
<span id="l613">                }</span><a href="#l613"></a>
<span id="l614">                cls = cls.getSuperclass();</span><a href="#l614"></a>
<span id="l615">                if (cls == null) {</span><a href="#l615"></a>
<span id="l616">                    // that's not supposed to happen</span><a href="#l616"></a>
<span id="l617">                    // make a ProtectionDomain with no permission.</span><a href="#l617"></a>
<span id="l618">                    // should we throw instead?</span><a href="#l618"></a>
<span id="l619">                    if (pds == null) pds = new HashSet&lt;&gt;();</span><a href="#l619"></a>
<span id="l620">                    else pds.clear();</span><a href="#l620"></a>
<span id="l621">                    pds.add(noPermissionsDomain());</span><a href="#l621"></a>
<span id="l622">                    break;</span><a href="#l622"></a>
<span id="l623">                }</span><a href="#l623"></a>
<span id="l624">            }</span><a href="#l624"></a>
<span id="l625">            if (pds != null) {</span><a href="#l625"></a>
<span id="l626">                domains = pds.toArray(new ProtectionDomain[0]);</span><a href="#l626"></a>
<span id="l627">            }</span><a href="#l627"></a>
<span id="l628">        }</span><a href="#l628"></a>
<span id="l629">        return domains;</span><a href="#l629"></a>
<span id="l630">    }</span><a href="#l630"></a>
<span id="l631"></span><a href="#l631"></a>
<span id="l632">    /**</span><a href="#l632"></a>
<span id="l633">     * Initializes class descriptor representing a proxy class.</span><a href="#l633"></a>
<span id="l634">     */</span><a href="#l634"></a>
<span id="l635">    void initProxy(Class&lt;?&gt; cl,</span><a href="#l635"></a>
<span id="l636">                   ClassNotFoundException resolveEx,</span><a href="#l636"></a>
<span id="l637">                   ObjectStreamClass superDesc)</span><a href="#l637"></a>
<span id="l638">        throws InvalidClassException</span><a href="#l638"></a>
<span id="l639">    {</span><a href="#l639"></a>
<span id="l640">        ObjectStreamClass osc = null;</span><a href="#l640"></a>
<span id="l641">        if (cl != null) {</span><a href="#l641"></a>
<span id="l642">            osc = lookup(cl, true);</span><a href="#l642"></a>
<span id="l643">            if (!osc.isProxy) {</span><a href="#l643"></a>
<span id="l644">                throw new InvalidClassException(</span><a href="#l644"></a>
<span id="l645">                    &quot;cannot bind proxy descriptor to a non-proxy class&quot;);</span><a href="#l645"></a>
<span id="l646">            }</span><a href="#l646"></a>
<span id="l647">        }</span><a href="#l647"></a>
<span id="l648">        this.cl = cl;</span><a href="#l648"></a>
<span id="l649">        this.resolveEx = resolveEx;</span><a href="#l649"></a>
<span id="l650">        this.superDesc = superDesc;</span><a href="#l650"></a>
<span id="l651">        isProxy = true;</span><a href="#l651"></a>
<span id="l652">        serializable = true;</span><a href="#l652"></a>
<span id="l653">        suid = Long.valueOf(0);</span><a href="#l653"></a>
<span id="l654">        fields = NO_FIELDS;</span><a href="#l654"></a>
<span id="l655">        if (osc != null) {</span><a href="#l655"></a>
<span id="l656">            localDesc = osc;</span><a href="#l656"></a>
<span id="l657">            name = localDesc.name;</span><a href="#l657"></a>
<span id="l658">            externalizable = localDesc.externalizable;</span><a href="#l658"></a>
<span id="l659">            writeReplaceMethod = localDesc.writeReplaceMethod;</span><a href="#l659"></a>
<span id="l660">            readResolveMethod = localDesc.readResolveMethod;</span><a href="#l660"></a>
<span id="l661">            deserializeEx = localDesc.deserializeEx;</span><a href="#l661"></a>
<span id="l662">            domains = localDesc.domains;</span><a href="#l662"></a>
<span id="l663">            cons = localDesc.cons;</span><a href="#l663"></a>
<span id="l664">        }</span><a href="#l664"></a>
<span id="l665">        fieldRefl = getReflector(fields, localDesc);</span><a href="#l665"></a>
<span id="l666">        initialized = true;</span><a href="#l666"></a>
<span id="l667">    }</span><a href="#l667"></a>
<span id="l668"></span><a href="#l668"></a>
<span id="l669">    /**</span><a href="#l669"></a>
<span id="l670">     * Initializes class descriptor representing a non-proxy class.</span><a href="#l670"></a>
<span id="l671">     */</span><a href="#l671"></a>
<span id="l672">    void initNonProxy(ObjectStreamClass model,</span><a href="#l672"></a>
<span id="l673">                      Class&lt;?&gt; cl,</span><a href="#l673"></a>
<span id="l674">                      ClassNotFoundException resolveEx,</span><a href="#l674"></a>
<span id="l675">                      ObjectStreamClass superDesc)</span><a href="#l675"></a>
<span id="l676">        throws InvalidClassException</span><a href="#l676"></a>
<span id="l677">    {</span><a href="#l677"></a>
<span id="l678">        long suid = Long.valueOf(model.getSerialVersionUID());</span><a href="#l678"></a>
<span id="l679">        ObjectStreamClass osc = null;</span><a href="#l679"></a>
<span id="l680">        if (cl != null) {</span><a href="#l680"></a>
<span id="l681">            osc = lookup(cl, true);</span><a href="#l681"></a>
<span id="l682">            if (osc.isProxy) {</span><a href="#l682"></a>
<span id="l683">                throw new InvalidClassException(</span><a href="#l683"></a>
<span id="l684">                        &quot;cannot bind non-proxy descriptor to a proxy class&quot;);</span><a href="#l684"></a>
<span id="l685">            }</span><a href="#l685"></a>
<span id="l686">            if (model.isEnum != osc.isEnum) {</span><a href="#l686"></a>
<span id="l687">                throw new InvalidClassException(model.isEnum ?</span><a href="#l687"></a>
<span id="l688">                        &quot;cannot bind enum descriptor to a non-enum class&quot; :</span><a href="#l688"></a>
<span id="l689">                        &quot;cannot bind non-enum descriptor to an enum class&quot;);</span><a href="#l689"></a>
<span id="l690">            }</span><a href="#l690"></a>
<span id="l691"></span><a href="#l691"></a>
<span id="l692">            if (model.serializable == osc.serializable &amp;&amp;</span><a href="#l692"></a>
<span id="l693">                    !cl.isArray() &amp;&amp;</span><a href="#l693"></a>
<span id="l694">                    suid != osc.getSerialVersionUID()) {</span><a href="#l694"></a>
<span id="l695">                throw new InvalidClassException(osc.name,</span><a href="#l695"></a>
<span id="l696">                        &quot;local class incompatible: &quot; +</span><a href="#l696"></a>
<span id="l697">                                &quot;stream classdesc serialVersionUID = &quot; + suid +</span><a href="#l697"></a>
<span id="l698">                                &quot;, local class serialVersionUID = &quot; +</span><a href="#l698"></a>
<span id="l699">                                osc.getSerialVersionUID());</span><a href="#l699"></a>
<span id="l700">            }</span><a href="#l700"></a>
<span id="l701"></span><a href="#l701"></a>
<span id="l702">            if (!classNamesEqual(model.name, osc.name)) {</span><a href="#l702"></a>
<span id="l703">                throw new InvalidClassException(osc.name,</span><a href="#l703"></a>
<span id="l704">                        &quot;local class name incompatible with stream class &quot; +</span><a href="#l704"></a>
<span id="l705">                                &quot;name \&quot;&quot; + model.name + &quot;\&quot;&quot;);</span><a href="#l705"></a>
<span id="l706">            }</span><a href="#l706"></a>
<span id="l707"></span><a href="#l707"></a>
<span id="l708">            if (!model.isEnum) {</span><a href="#l708"></a>
<span id="l709">                if ((model.serializable == osc.serializable) &amp;&amp;</span><a href="#l709"></a>
<span id="l710">                        (model.externalizable != osc.externalizable)) {</span><a href="#l710"></a>
<span id="l711">                    throw new InvalidClassException(osc.name,</span><a href="#l711"></a>
<span id="l712">                            &quot;Serializable incompatible with Externalizable&quot;);</span><a href="#l712"></a>
<span id="l713">                }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">                if ((model.serializable != osc.serializable) ||</span><a href="#l715"></a>
<span id="l716">                        (model.externalizable != osc.externalizable) ||</span><a href="#l716"></a>
<span id="l717">                        !(model.serializable || model.externalizable)) {</span><a href="#l717"></a>
<span id="l718">                    deserializeEx = new ExceptionInfo(</span><a href="#l718"></a>
<span id="l719">                            osc.name, &quot;class invalid for deserialization&quot;);</span><a href="#l719"></a>
<span id="l720">                }</span><a href="#l720"></a>
<span id="l721">            }</span><a href="#l721"></a>
<span id="l722">        }</span><a href="#l722"></a>
<span id="l723"></span><a href="#l723"></a>
<span id="l724">        this.cl = cl;</span><a href="#l724"></a>
<span id="l725">        this.resolveEx = resolveEx;</span><a href="#l725"></a>
<span id="l726">        this.superDesc = superDesc;</span><a href="#l726"></a>
<span id="l727">        name = model.name;</span><a href="#l727"></a>
<span id="l728">        this.suid = suid;</span><a href="#l728"></a>
<span id="l729">        isProxy = false;</span><a href="#l729"></a>
<span id="l730">        isEnum = model.isEnum;</span><a href="#l730"></a>
<span id="l731">        serializable = model.serializable;</span><a href="#l731"></a>
<span id="l732">        externalizable = model.externalizable;</span><a href="#l732"></a>
<span id="l733">        hasBlockExternalData = model.hasBlockExternalData;</span><a href="#l733"></a>
<span id="l734">        hasWriteObjectData = model.hasWriteObjectData;</span><a href="#l734"></a>
<span id="l735">        fields = model.fields;</span><a href="#l735"></a>
<span id="l736">        primDataSize = model.primDataSize;</span><a href="#l736"></a>
<span id="l737">        numObjFields = model.numObjFields;</span><a href="#l737"></a>
<span id="l738"></span><a href="#l738"></a>
<span id="l739">        if (osc != null) {</span><a href="#l739"></a>
<span id="l740">            localDesc = osc;</span><a href="#l740"></a>
<span id="l741">            writeObjectMethod = localDesc.writeObjectMethod;</span><a href="#l741"></a>
<span id="l742">            readObjectMethod = localDesc.readObjectMethod;</span><a href="#l742"></a>
<span id="l743">            readObjectNoDataMethod = localDesc.readObjectNoDataMethod;</span><a href="#l743"></a>
<span id="l744">            writeReplaceMethod = localDesc.writeReplaceMethod;</span><a href="#l744"></a>
<span id="l745">            readResolveMethod = localDesc.readResolveMethod;</span><a href="#l745"></a>
<span id="l746">            if (deserializeEx == null) {</span><a href="#l746"></a>
<span id="l747">                deserializeEx = localDesc.deserializeEx;</span><a href="#l747"></a>
<span id="l748">            }</span><a href="#l748"></a>
<span id="l749">            domains = localDesc.domains;</span><a href="#l749"></a>
<span id="l750">            cons = localDesc.cons;</span><a href="#l750"></a>
<span id="l751">        }</span><a href="#l751"></a>
<span id="l752"></span><a href="#l752"></a>
<span id="l753">        fieldRefl = getReflector(fields, localDesc);</span><a href="#l753"></a>
<span id="l754">        // reassign to matched fields so as to reflect local unshared settings</span><a href="#l754"></a>
<span id="l755">        fields = fieldRefl.getFields();</span><a href="#l755"></a>
<span id="l756">        initialized = true;</span><a href="#l756"></a>
<span id="l757">    }</span><a href="#l757"></a>
<span id="l758"></span><a href="#l758"></a>
<span id="l759">    /**</span><a href="#l759"></a>
<span id="l760">     * Reads non-proxy class descriptor information from given input stream.</span><a href="#l760"></a>
<span id="l761">     * The resulting class descriptor is not fully functional; it can only be</span><a href="#l761"></a>
<span id="l762">     * used as input to the ObjectInputStream.resolveClass() and</span><a href="#l762"></a>
<span id="l763">     * ObjectStreamClass.initNonProxy() methods.</span><a href="#l763"></a>
<span id="l764">     */</span><a href="#l764"></a>
<span id="l765">    void readNonProxy(ObjectInputStream in)</span><a href="#l765"></a>
<span id="l766">        throws IOException, ClassNotFoundException</span><a href="#l766"></a>
<span id="l767">    {</span><a href="#l767"></a>
<span id="l768">        name = in.readUTF();</span><a href="#l768"></a>
<span id="l769">        suid = Long.valueOf(in.readLong());</span><a href="#l769"></a>
<span id="l770">        isProxy = false;</span><a href="#l770"></a>
<span id="l771"></span><a href="#l771"></a>
<span id="l772">        byte flags = in.readByte();</span><a href="#l772"></a>
<span id="l773">        hasWriteObjectData =</span><a href="#l773"></a>
<span id="l774">            ((flags &amp; ObjectStreamConstants.SC_WRITE_METHOD) != 0);</span><a href="#l774"></a>
<span id="l775">        hasBlockExternalData =</span><a href="#l775"></a>
<span id="l776">            ((flags &amp; ObjectStreamConstants.SC_BLOCK_DATA) != 0);</span><a href="#l776"></a>
<span id="l777">        externalizable =</span><a href="#l777"></a>
<span id="l778">            ((flags &amp; ObjectStreamConstants.SC_EXTERNALIZABLE) != 0);</span><a href="#l778"></a>
<span id="l779">        boolean sflag =</span><a href="#l779"></a>
<span id="l780">            ((flags &amp; ObjectStreamConstants.SC_SERIALIZABLE) != 0);</span><a href="#l780"></a>
<span id="l781">        if (externalizable &amp;&amp; sflag) {</span><a href="#l781"></a>
<span id="l782">            throw new InvalidClassException(</span><a href="#l782"></a>
<span id="l783">                name, &quot;serializable and externalizable flags conflict&quot;);</span><a href="#l783"></a>
<span id="l784">        }</span><a href="#l784"></a>
<span id="l785">        serializable = externalizable || sflag;</span><a href="#l785"></a>
<span id="l786">        isEnum = ((flags &amp; ObjectStreamConstants.SC_ENUM) != 0);</span><a href="#l786"></a>
<span id="l787">        if (isEnum &amp;&amp; suid.longValue() != 0L) {</span><a href="#l787"></a>
<span id="l788">            throw new InvalidClassException(name,</span><a href="#l788"></a>
<span id="l789">                &quot;enum descriptor has non-zero serialVersionUID: &quot; + suid);</span><a href="#l789"></a>
<span id="l790">        }</span><a href="#l790"></a>
<span id="l791"></span><a href="#l791"></a>
<span id="l792">        int numFields = in.readShort();</span><a href="#l792"></a>
<span id="l793">        if (isEnum &amp;&amp; numFields != 0) {</span><a href="#l793"></a>
<span id="l794">            throw new InvalidClassException(name,</span><a href="#l794"></a>
<span id="l795">                &quot;enum descriptor has non-zero field count: &quot; + numFields);</span><a href="#l795"></a>
<span id="l796">        }</span><a href="#l796"></a>
<span id="l797">        fields = (numFields &gt; 0) ?</span><a href="#l797"></a>
<span id="l798">            new ObjectStreamField[numFields] : NO_FIELDS;</span><a href="#l798"></a>
<span id="l799">        for (int i = 0; i &lt; numFields; i++) {</span><a href="#l799"></a>
<span id="l800">            char tcode = (char) in.readByte();</span><a href="#l800"></a>
<span id="l801">            String fname = in.readUTF();</span><a href="#l801"></a>
<span id="l802">            String signature = ((tcode == 'L') || (tcode == '[')) ?</span><a href="#l802"></a>
<span id="l803">                in.readTypeString() : new String(new char[] { tcode });</span><a href="#l803"></a>
<span id="l804">            try {</span><a href="#l804"></a>
<span id="l805">                fields[i] = new ObjectStreamField(fname, signature, false);</span><a href="#l805"></a>
<span id="l806">            } catch (RuntimeException e) {</span><a href="#l806"></a>
<span id="l807">                throw (IOException) new InvalidClassException(name,</span><a href="#l807"></a>
<span id="l808">                    &quot;invalid descriptor for field &quot; + fname).initCause(e);</span><a href="#l808"></a>
<span id="l809">            }</span><a href="#l809"></a>
<span id="l810">        }</span><a href="#l810"></a>
<span id="l811">        computeFieldOffsets();</span><a href="#l811"></a>
<span id="l812">    }</span><a href="#l812"></a>
<span id="l813"></span><a href="#l813"></a>
<span id="l814">    /**</span><a href="#l814"></a>
<span id="l815">     * Writes non-proxy class descriptor information to given output stream.</span><a href="#l815"></a>
<span id="l816">     */</span><a href="#l816"></a>
<span id="l817">    void writeNonProxy(ObjectOutputStream out) throws IOException {</span><a href="#l817"></a>
<span id="l818">        out.writeUTF(name);</span><a href="#l818"></a>
<span id="l819">        out.writeLong(getSerialVersionUID());</span><a href="#l819"></a>
<span id="l820"></span><a href="#l820"></a>
<span id="l821">        byte flags = 0;</span><a href="#l821"></a>
<span id="l822">        if (externalizable) {</span><a href="#l822"></a>
<span id="l823">            flags |= ObjectStreamConstants.SC_EXTERNALIZABLE;</span><a href="#l823"></a>
<span id="l824">            int protocol = out.getProtocolVersion();</span><a href="#l824"></a>
<span id="l825">            if (protocol != ObjectStreamConstants.PROTOCOL_VERSION_1) {</span><a href="#l825"></a>
<span id="l826">                flags |= ObjectStreamConstants.SC_BLOCK_DATA;</span><a href="#l826"></a>
<span id="l827">            }</span><a href="#l827"></a>
<span id="l828">        } else if (serializable) {</span><a href="#l828"></a>
<span id="l829">            flags |= ObjectStreamConstants.SC_SERIALIZABLE;</span><a href="#l829"></a>
<span id="l830">        }</span><a href="#l830"></a>
<span id="l831">        if (hasWriteObjectData) {</span><a href="#l831"></a>
<span id="l832">            flags |= ObjectStreamConstants.SC_WRITE_METHOD;</span><a href="#l832"></a>
<span id="l833">        }</span><a href="#l833"></a>
<span id="l834">        if (isEnum) {</span><a href="#l834"></a>
<span id="l835">            flags |= ObjectStreamConstants.SC_ENUM;</span><a href="#l835"></a>
<span id="l836">        }</span><a href="#l836"></a>
<span id="l837">        out.writeByte(flags);</span><a href="#l837"></a>
<span id="l838"></span><a href="#l838"></a>
<span id="l839">        out.writeShort(fields.length);</span><a href="#l839"></a>
<span id="l840">        for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l840"></a>
<span id="l841">            ObjectStreamField f = fields[i];</span><a href="#l841"></a>
<span id="l842">            out.writeByte(f.getTypeCode());</span><a href="#l842"></a>
<span id="l843">            out.writeUTF(f.getName());</span><a href="#l843"></a>
<span id="l844">            if (!f.isPrimitive()) {</span><a href="#l844"></a>
<span id="l845">                out.writeTypeString(f.getTypeString());</span><a href="#l845"></a>
<span id="l846">            }</span><a href="#l846"></a>
<span id="l847">        }</span><a href="#l847"></a>
<span id="l848">    }</span><a href="#l848"></a>
<span id="l849"></span><a href="#l849"></a>
<span id="l850">    /**</span><a href="#l850"></a>
<span id="l851">     * Returns ClassNotFoundException (if any) thrown while attempting to</span><a href="#l851"></a>
<span id="l852">     * resolve local class corresponding to this class descriptor.</span><a href="#l852"></a>
<span id="l853">     */</span><a href="#l853"></a>
<span id="l854">    ClassNotFoundException getResolveException() {</span><a href="#l854"></a>
<span id="l855">        return resolveEx;</span><a href="#l855"></a>
<span id="l856">    }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">    /**</span><a href="#l858"></a>
<span id="l859">     * Throws InternalError if not initialized.</span><a href="#l859"></a>
<span id="l860">     */</span><a href="#l860"></a>
<span id="l861">    private final void requireInitialized() {</span><a href="#l861"></a>
<span id="l862">        if (!initialized)</span><a href="#l862"></a>
<span id="l863">            throw new InternalError(&quot;Unexpected call when not initialized&quot;);</span><a href="#l863"></a>
<span id="l864">    }</span><a href="#l864"></a>
<span id="l865"></span><a href="#l865"></a>
<span id="l866">    /**</span><a href="#l866"></a>
<span id="l867">     * Throws InvalidClassException if not initialized.</span><a href="#l867"></a>
<span id="l868">     * To be called in cases where an uninitialized class descriptor indicates</span><a href="#l868"></a>
<span id="l869">     * a problem in the serialization stream.</span><a href="#l869"></a>
<span id="l870">     */</span><a href="#l870"></a>
<span id="l871">    final void checkInitialized() throws InvalidClassException {</span><a href="#l871"></a>
<span id="l872">        if (!initialized) {</span><a href="#l872"></a>
<span id="l873">            throw new InvalidClassException(&quot;Class descriptor should be initialized&quot;);</span><a href="#l873"></a>
<span id="l874">        }</span><a href="#l874"></a>
<span id="l875">    }</span><a href="#l875"></a>
<span id="l876"></span><a href="#l876"></a>
<span id="l877">    /**</span><a href="#l877"></a>
<span id="l878">     * Throws an InvalidClassException if object instances referencing this</span><a href="#l878"></a>
<span id="l879">     * class descriptor should not be allowed to deserialize.  This method does</span><a href="#l879"></a>
<span id="l880">     * not apply to deserialization of enum constants.</span><a href="#l880"></a>
<span id="l881">     */</span><a href="#l881"></a>
<span id="l882">    void checkDeserialize() throws InvalidClassException {</span><a href="#l882"></a>
<span id="l883">        requireInitialized();</span><a href="#l883"></a>
<span id="l884">        if (deserializeEx != null) {</span><a href="#l884"></a>
<span id="l885">            throw deserializeEx.newInvalidClassException();</span><a href="#l885"></a>
<span id="l886">        }</span><a href="#l886"></a>
<span id="l887">    }</span><a href="#l887"></a>
<span id="l888"></span><a href="#l888"></a>
<span id="l889">    /**</span><a href="#l889"></a>
<span id="l890">     * Throws an InvalidClassException if objects whose class is represented by</span><a href="#l890"></a>
<span id="l891">     * this descriptor should not be allowed to serialize.  This method does</span><a href="#l891"></a>
<span id="l892">     * not apply to serialization of enum constants.</span><a href="#l892"></a>
<span id="l893">     */</span><a href="#l893"></a>
<span id="l894">    void checkSerialize() throws InvalidClassException {</span><a href="#l894"></a>
<span id="l895">        requireInitialized();</span><a href="#l895"></a>
<span id="l896">        if (serializeEx != null) {</span><a href="#l896"></a>
<span id="l897">            throw serializeEx.newInvalidClassException();</span><a href="#l897"></a>
<span id="l898">        }</span><a href="#l898"></a>
<span id="l899">    }</span><a href="#l899"></a>
<span id="l900"></span><a href="#l900"></a>
<span id="l901">    /**</span><a href="#l901"></a>
<span id="l902">     * Throws an InvalidClassException if objects whose class is represented by</span><a href="#l902"></a>
<span id="l903">     * this descriptor should not be permitted to use default serialization</span><a href="#l903"></a>
<span id="l904">     * (e.g., if the class declares serializable fields that do not correspond</span><a href="#l904"></a>
<span id="l905">     * to actual fields, and hence must use the GetField API).  This method</span><a href="#l905"></a>
<span id="l906">     * does not apply to deserialization of enum constants.</span><a href="#l906"></a>
<span id="l907">     */</span><a href="#l907"></a>
<span id="l908">    void checkDefaultSerialize() throws InvalidClassException {</span><a href="#l908"></a>
<span id="l909">        requireInitialized();</span><a href="#l909"></a>
<span id="l910">        if (defaultSerializeEx != null) {</span><a href="#l910"></a>
<span id="l911">            throw defaultSerializeEx.newInvalidClassException();</span><a href="#l911"></a>
<span id="l912">        }</span><a href="#l912"></a>
<span id="l913">    }</span><a href="#l913"></a>
<span id="l914"></span><a href="#l914"></a>
<span id="l915">    /**</span><a href="#l915"></a>
<span id="l916">     * Returns superclass descriptor.  Note that on the receiving side, the</span><a href="#l916"></a>
<span id="l917">     * superclass descriptor may be bound to a class that is not a superclass</span><a href="#l917"></a>
<span id="l918">     * of the subclass descriptor's bound class.</span><a href="#l918"></a>
<span id="l919">     */</span><a href="#l919"></a>
<span id="l920">    ObjectStreamClass getSuperDesc() {</span><a href="#l920"></a>
<span id="l921">        requireInitialized();</span><a href="#l921"></a>
<span id="l922">        return superDesc;</span><a href="#l922"></a>
<span id="l923">    }</span><a href="#l923"></a>
<span id="l924"></span><a href="#l924"></a>
<span id="l925">    /**</span><a href="#l925"></a>
<span id="l926">     * Returns the &quot;local&quot; class descriptor for the class associated with this</span><a href="#l926"></a>
<span id="l927">     * class descriptor (i.e., the result of</span><a href="#l927"></a>
<span id="l928">     * ObjectStreamClass.lookup(this.forClass())) or null if there is no class</span><a href="#l928"></a>
<span id="l929">     * associated with this descriptor.</span><a href="#l929"></a>
<span id="l930">     */</span><a href="#l930"></a>
<span id="l931">    ObjectStreamClass getLocalDesc() {</span><a href="#l931"></a>
<span id="l932">        requireInitialized();</span><a href="#l932"></a>
<span id="l933">        return localDesc;</span><a href="#l933"></a>
<span id="l934">    }</span><a href="#l934"></a>
<span id="l935"></span><a href="#l935"></a>
<span id="l936">    /**</span><a href="#l936"></a>
<span id="l937">     * Returns arrays of ObjectStreamFields representing the serializable</span><a href="#l937"></a>
<span id="l938">     * fields of the represented class.  If copy is true, a clone of this class</span><a href="#l938"></a>
<span id="l939">     * descriptor's field array is returned, otherwise the array itself is</span><a href="#l939"></a>
<span id="l940">     * returned.</span><a href="#l940"></a>
<span id="l941">     */</span><a href="#l941"></a>
<span id="l942">    ObjectStreamField[] getFields(boolean copy) {</span><a href="#l942"></a>
<span id="l943">        return copy ? fields.clone() : fields;</span><a href="#l943"></a>
<span id="l944">    }</span><a href="#l944"></a>
<span id="l945"></span><a href="#l945"></a>
<span id="l946">    /**</span><a href="#l946"></a>
<span id="l947">     * Looks up a serializable field of the represented class by name and type.</span><a href="#l947"></a>
<span id="l948">     * A specified type of null matches all types, Object.class matches all</span><a href="#l948"></a>
<span id="l949">     * non-primitive types, and any other non-null type matches assignable</span><a href="#l949"></a>
<span id="l950">     * types only.  Returns matching field, or null if no match found.</span><a href="#l950"></a>
<span id="l951">     */</span><a href="#l951"></a>
<span id="l952">    ObjectStreamField getField(String name, Class&lt;?&gt; type) {</span><a href="#l952"></a>
<span id="l953">        for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l953"></a>
<span id="l954">            ObjectStreamField f = fields[i];</span><a href="#l954"></a>
<span id="l955">            if (f.getName().equals(name)) {</span><a href="#l955"></a>
<span id="l956">                if (type == null ||</span><a href="#l956"></a>
<span id="l957">                    (type == Object.class &amp;&amp; !f.isPrimitive()))</span><a href="#l957"></a>
<span id="l958">                {</span><a href="#l958"></a>
<span id="l959">                    return f;</span><a href="#l959"></a>
<span id="l960">                }</span><a href="#l960"></a>
<span id="l961">                Class&lt;?&gt; ftype = f.getType();</span><a href="#l961"></a>
<span id="l962">                if (ftype != null &amp;&amp; type.isAssignableFrom(ftype)) {</span><a href="#l962"></a>
<span id="l963">                    return f;</span><a href="#l963"></a>
<span id="l964">                }</span><a href="#l964"></a>
<span id="l965">            }</span><a href="#l965"></a>
<span id="l966">        }</span><a href="#l966"></a>
<span id="l967">        return null;</span><a href="#l967"></a>
<span id="l968">    }</span><a href="#l968"></a>
<span id="l969"></span><a href="#l969"></a>
<span id="l970">    /**</span><a href="#l970"></a>
<span id="l971">     * Returns true if class descriptor represents a dynamic proxy class, false</span><a href="#l971"></a>
<span id="l972">     * otherwise.</span><a href="#l972"></a>
<span id="l973">     */</span><a href="#l973"></a>
<span id="l974">    boolean isProxy() {</span><a href="#l974"></a>
<span id="l975">        requireInitialized();</span><a href="#l975"></a>
<span id="l976">        return isProxy;</span><a href="#l976"></a>
<span id="l977">    }</span><a href="#l977"></a>
<span id="l978"></span><a href="#l978"></a>
<span id="l979">    /**</span><a href="#l979"></a>
<span id="l980">     * Returns true if class descriptor represents an enum type, false</span><a href="#l980"></a>
<span id="l981">     * otherwise.</span><a href="#l981"></a>
<span id="l982">     */</span><a href="#l982"></a>
<span id="l983">    boolean isEnum() {</span><a href="#l983"></a>
<span id="l984">        requireInitialized();</span><a href="#l984"></a>
<span id="l985">        return isEnum;</span><a href="#l985"></a>
<span id="l986">    }</span><a href="#l986"></a>
<span id="l987"></span><a href="#l987"></a>
<span id="l988">    /**</span><a href="#l988"></a>
<span id="l989">     * Returns true if represented class implements Externalizable, false</span><a href="#l989"></a>
<span id="l990">     * otherwise.</span><a href="#l990"></a>
<span id="l991">     */</span><a href="#l991"></a>
<span id="l992">    boolean isExternalizable() {</span><a href="#l992"></a>
<span id="l993">        requireInitialized();</span><a href="#l993"></a>
<span id="l994">        return externalizable;</span><a href="#l994"></a>
<span id="l995">    }</span><a href="#l995"></a>
<span id="l996"></span><a href="#l996"></a>
<span id="l997">    /**</span><a href="#l997"></a>
<span id="l998">     * Returns true if represented class implements Serializable, false</span><a href="#l998"></a>
<span id="l999">     * otherwise.</span><a href="#l999"></a>
<span id="l1000">     */</span><a href="#l1000"></a>
<span id="l1001">    boolean isSerializable() {</span><a href="#l1001"></a>
<span id="l1002">        requireInitialized();</span><a href="#l1002"></a>
<span id="l1003">        return serializable;</span><a href="#l1003"></a>
<span id="l1004">    }</span><a href="#l1004"></a>
<span id="l1005"></span><a href="#l1005"></a>
<span id="l1006">    /**</span><a href="#l1006"></a>
<span id="l1007">     * Returns true if class descriptor represents externalizable class that</span><a href="#l1007"></a>
<span id="l1008">     * has written its data in 1.2 (block data) format, false otherwise.</span><a href="#l1008"></a>
<span id="l1009">     */</span><a href="#l1009"></a>
<span id="l1010">    boolean hasBlockExternalData() {</span><a href="#l1010"></a>
<span id="l1011">        requireInitialized();</span><a href="#l1011"></a>
<span id="l1012">        return hasBlockExternalData;</span><a href="#l1012"></a>
<span id="l1013">    }</span><a href="#l1013"></a>
<span id="l1014"></span><a href="#l1014"></a>
<span id="l1015">    /**</span><a href="#l1015"></a>
<span id="l1016">     * Returns true if class descriptor represents serializable (but not</span><a href="#l1016"></a>
<span id="l1017">     * externalizable) class which has written its data via a custom</span><a href="#l1017"></a>
<span id="l1018">     * writeObject() method, false otherwise.</span><a href="#l1018"></a>
<span id="l1019">     */</span><a href="#l1019"></a>
<span id="l1020">    boolean hasWriteObjectData() {</span><a href="#l1020"></a>
<span id="l1021">        requireInitialized();</span><a href="#l1021"></a>
<span id="l1022">        return hasWriteObjectData;</span><a href="#l1022"></a>
<span id="l1023">    }</span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">    /**</span><a href="#l1025"></a>
<span id="l1026">     * Returns true if represented class is serializable/externalizable and can</span><a href="#l1026"></a>
<span id="l1027">     * be instantiated by the serialization runtime--i.e., if it is</span><a href="#l1027"></a>
<span id="l1028">     * externalizable and defines a public no-arg constructor, or if it is</span><a href="#l1028"></a>
<span id="l1029">     * non-externalizable and its first non-serializable superclass defines an</span><a href="#l1029"></a>
<span id="l1030">     * accessible no-arg constructor.  Otherwise, returns false.</span><a href="#l1030"></a>
<span id="l1031">     */</span><a href="#l1031"></a>
<span id="l1032">    boolean isInstantiable() {</span><a href="#l1032"></a>
<span id="l1033">        requireInitialized();</span><a href="#l1033"></a>
<span id="l1034">        return (cons != null);</span><a href="#l1034"></a>
<span id="l1035">    }</span><a href="#l1035"></a>
<span id="l1036"></span><a href="#l1036"></a>
<span id="l1037">    /**</span><a href="#l1037"></a>
<span id="l1038">     * Returns true if represented class is serializable (but not</span><a href="#l1038"></a>
<span id="l1039">     * externalizable) and defines a conformant writeObject method.  Otherwise,</span><a href="#l1039"></a>
<span id="l1040">     * returns false.</span><a href="#l1040"></a>
<span id="l1041">     */</span><a href="#l1041"></a>
<span id="l1042">    boolean hasWriteObjectMethod() {</span><a href="#l1042"></a>
<span id="l1043">        requireInitialized();</span><a href="#l1043"></a>
<span id="l1044">        return (writeObjectMethod != null);</span><a href="#l1044"></a>
<span id="l1045">    }</span><a href="#l1045"></a>
<span id="l1046"></span><a href="#l1046"></a>
<span id="l1047">    /**</span><a href="#l1047"></a>
<span id="l1048">     * Returns true if represented class is serializable (but not</span><a href="#l1048"></a>
<span id="l1049">     * externalizable) and defines a conformant readObject method.  Otherwise,</span><a href="#l1049"></a>
<span id="l1050">     * returns false.</span><a href="#l1050"></a>
<span id="l1051">     */</span><a href="#l1051"></a>
<span id="l1052">    boolean hasReadObjectMethod() {</span><a href="#l1052"></a>
<span id="l1053">        requireInitialized();</span><a href="#l1053"></a>
<span id="l1054">        return (readObjectMethod != null);</span><a href="#l1054"></a>
<span id="l1055">    }</span><a href="#l1055"></a>
<span id="l1056"></span><a href="#l1056"></a>
<span id="l1057">    /**</span><a href="#l1057"></a>
<span id="l1058">     * Returns true if represented class is serializable (but not</span><a href="#l1058"></a>
<span id="l1059">     * externalizable) and defines a conformant readObjectNoData method.</span><a href="#l1059"></a>
<span id="l1060">     * Otherwise, returns false.</span><a href="#l1060"></a>
<span id="l1061">     */</span><a href="#l1061"></a>
<span id="l1062">    boolean hasReadObjectNoDataMethod() {</span><a href="#l1062"></a>
<span id="l1063">        requireInitialized();</span><a href="#l1063"></a>
<span id="l1064">        return (readObjectNoDataMethod != null);</span><a href="#l1064"></a>
<span id="l1065">    }</span><a href="#l1065"></a>
<span id="l1066"></span><a href="#l1066"></a>
<span id="l1067">    /**</span><a href="#l1067"></a>
<span id="l1068">     * Returns true if represented class is serializable or externalizable and</span><a href="#l1068"></a>
<span id="l1069">     * defines a conformant writeReplace method.  Otherwise, returns false.</span><a href="#l1069"></a>
<span id="l1070">     */</span><a href="#l1070"></a>
<span id="l1071">    boolean hasWriteReplaceMethod() {</span><a href="#l1071"></a>
<span id="l1072">        requireInitialized();</span><a href="#l1072"></a>
<span id="l1073">        return (writeReplaceMethod != null);</span><a href="#l1073"></a>
<span id="l1074">    }</span><a href="#l1074"></a>
<span id="l1075"></span><a href="#l1075"></a>
<span id="l1076">    /**</span><a href="#l1076"></a>
<span id="l1077">     * Returns true if represented class is serializable or externalizable and</span><a href="#l1077"></a>
<span id="l1078">     * defines a conformant readResolve method.  Otherwise, returns false.</span><a href="#l1078"></a>
<span id="l1079">     */</span><a href="#l1079"></a>
<span id="l1080">    boolean hasReadResolveMethod() {</span><a href="#l1080"></a>
<span id="l1081">        requireInitialized();</span><a href="#l1081"></a>
<span id="l1082">        return (readResolveMethod != null);</span><a href="#l1082"></a>
<span id="l1083">    }</span><a href="#l1083"></a>
<span id="l1084"></span><a href="#l1084"></a>
<span id="l1085">    /**</span><a href="#l1085"></a>
<span id="l1086">     * Creates a new instance of the represented class.  If the class is</span><a href="#l1086"></a>
<span id="l1087">     * externalizable, invokes its public no-arg constructor; otherwise, if the</span><a href="#l1087"></a>
<span id="l1088">     * class is serializable, invokes the no-arg constructor of the first</span><a href="#l1088"></a>
<span id="l1089">     * non-serializable superclass.  Throws UnsupportedOperationException if</span><a href="#l1089"></a>
<span id="l1090">     * this class descriptor is not associated with a class, if the associated</span><a href="#l1090"></a>
<span id="l1091">     * class is non-serializable or if the appropriate no-arg constructor is</span><a href="#l1091"></a>
<span id="l1092">     * inaccessible/unavailable.</span><a href="#l1092"></a>
<span id="l1093">     */</span><a href="#l1093"></a>
<span id="l1094">    Object newInstance()</span><a href="#l1094"></a>
<span id="l1095">        throws InstantiationException, InvocationTargetException,</span><a href="#l1095"></a>
<span id="l1096">               UnsupportedOperationException</span><a href="#l1096"></a>
<span id="l1097">    {</span><a href="#l1097"></a>
<span id="l1098">        requireInitialized();</span><a href="#l1098"></a>
<span id="l1099">        if (cons != null) {</span><a href="#l1099"></a>
<span id="l1100">            try {</span><a href="#l1100"></a>
<span id="l1101">                if (domains == null || domains.length == 0) {</span><a href="#l1101"></a>
<span id="l1102">                    return cons.newInstance();</span><a href="#l1102"></a>
<span id="l1103">                } else {</span><a href="#l1103"></a>
<span id="l1104">                    JavaSecurityAccess jsa = SharedSecrets.getJavaSecurityAccess();</span><a href="#l1104"></a>
<span id="l1105">                    PrivilegedAction&lt;?&gt; pea = () -&gt; {</span><a href="#l1105"></a>
<span id="l1106">                        try {</span><a href="#l1106"></a>
<span id="l1107">                            return cons.newInstance();</span><a href="#l1107"></a>
<span id="l1108">                        } catch (InstantiationException</span><a href="#l1108"></a>
<span id="l1109">                                 | InvocationTargetException</span><a href="#l1109"></a>
<span id="l1110">                                 | IllegalAccessException x) {</span><a href="#l1110"></a>
<span id="l1111">                            throw new UndeclaredThrowableException(x);</span><a href="#l1111"></a>
<span id="l1112">                        }</span><a href="#l1112"></a>
<span id="l1113">                    }; // Can't use PrivilegedExceptionAction with jsa</span><a href="#l1113"></a>
<span id="l1114">                    try {</span><a href="#l1114"></a>
<span id="l1115">                        return jsa.doIntersectionPrivilege(pea,</span><a href="#l1115"></a>
<span id="l1116">                                   AccessController.getContext(),</span><a href="#l1116"></a>
<span id="l1117">                                   new AccessControlContext(domains));</span><a href="#l1117"></a>
<span id="l1118">                    } catch (UndeclaredThrowableException x) {</span><a href="#l1118"></a>
<span id="l1119">                        Throwable cause = x.getCause();</span><a href="#l1119"></a>
<span id="l1120">                        if (cause instanceof InstantiationException)</span><a href="#l1120"></a>
<span id="l1121">                            throw (InstantiationException) cause;</span><a href="#l1121"></a>
<span id="l1122">                        if (cause instanceof InvocationTargetException)</span><a href="#l1122"></a>
<span id="l1123">                            throw (InvocationTargetException) cause;</span><a href="#l1123"></a>
<span id="l1124">                        if (cause instanceof IllegalAccessException)</span><a href="#l1124"></a>
<span id="l1125">                            throw (IllegalAccessException) cause;</span><a href="#l1125"></a>
<span id="l1126">                        // not supposed to happen</span><a href="#l1126"></a>
<span id="l1127">                        throw x;</span><a href="#l1127"></a>
<span id="l1128">                    }</span><a href="#l1128"></a>
<span id="l1129">                }</span><a href="#l1129"></a>
<span id="l1130">            } catch (IllegalAccessException ex) {</span><a href="#l1130"></a>
<span id="l1131">                // should not occur, as access checks have been suppressed</span><a href="#l1131"></a>
<span id="l1132">                throw new InternalError(ex);</span><a href="#l1132"></a>
<span id="l1133">            }</span><a href="#l1133"></a>
<span id="l1134">        } else {</span><a href="#l1134"></a>
<span id="l1135">            throw new UnsupportedOperationException();</span><a href="#l1135"></a>
<span id="l1136">        }</span><a href="#l1136"></a>
<span id="l1137">    }</span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139">    /**</span><a href="#l1139"></a>
<span id="l1140">     * Invokes the writeObject method of the represented serializable class.</span><a href="#l1140"></a>
<span id="l1141">     * Throws UnsupportedOperationException if this class descriptor is not</span><a href="#l1141"></a>
<span id="l1142">     * associated with a class, or if the class is externalizable,</span><a href="#l1142"></a>
<span id="l1143">     * non-serializable or does not define writeObject.</span><a href="#l1143"></a>
<span id="l1144">     */</span><a href="#l1144"></a>
<span id="l1145">    void invokeWriteObject(Object obj, ObjectOutputStream out)</span><a href="#l1145"></a>
<span id="l1146">        throws IOException, UnsupportedOperationException</span><a href="#l1146"></a>
<span id="l1147">    {</span><a href="#l1147"></a>
<span id="l1148">        requireInitialized();</span><a href="#l1148"></a>
<span id="l1149">        if (writeObjectMethod != null) {</span><a href="#l1149"></a>
<span id="l1150">            try {</span><a href="#l1150"></a>
<span id="l1151">                writeObjectMethod.invoke(obj, new Object[]{ out });</span><a href="#l1151"></a>
<span id="l1152">            } catch (InvocationTargetException ex) {</span><a href="#l1152"></a>
<span id="l1153">                Throwable th = ex.getTargetException();</span><a href="#l1153"></a>
<span id="l1154">                if (th instanceof IOException) {</span><a href="#l1154"></a>
<span id="l1155">                    throw (IOException) th;</span><a href="#l1155"></a>
<span id="l1156">                } else {</span><a href="#l1156"></a>
<span id="l1157">                    throwMiscException(th);</span><a href="#l1157"></a>
<span id="l1158">                }</span><a href="#l1158"></a>
<span id="l1159">            } catch (IllegalAccessException ex) {</span><a href="#l1159"></a>
<span id="l1160">                // should not occur, as access checks have been suppressed</span><a href="#l1160"></a>
<span id="l1161">                throw new InternalError(ex);</span><a href="#l1161"></a>
<span id="l1162">            }</span><a href="#l1162"></a>
<span id="l1163">        } else {</span><a href="#l1163"></a>
<span id="l1164">            throw new UnsupportedOperationException();</span><a href="#l1164"></a>
<span id="l1165">        }</span><a href="#l1165"></a>
<span id="l1166">    }</span><a href="#l1166"></a>
<span id="l1167"></span><a href="#l1167"></a>
<span id="l1168">    /**</span><a href="#l1168"></a>
<span id="l1169">     * Invokes the readObject method of the represented serializable class.</span><a href="#l1169"></a>
<span id="l1170">     * Throws UnsupportedOperationException if this class descriptor is not</span><a href="#l1170"></a>
<span id="l1171">     * associated with a class, or if the class is externalizable,</span><a href="#l1171"></a>
<span id="l1172">     * non-serializable or does not define readObject.</span><a href="#l1172"></a>
<span id="l1173">     */</span><a href="#l1173"></a>
<span id="l1174">    void invokeReadObject(Object obj, ObjectInputStream in)</span><a href="#l1174"></a>
<span id="l1175">        throws ClassNotFoundException, IOException,</span><a href="#l1175"></a>
<span id="l1176">               UnsupportedOperationException</span><a href="#l1176"></a>
<span id="l1177">    {</span><a href="#l1177"></a>
<span id="l1178">        requireInitialized();</span><a href="#l1178"></a>
<span id="l1179">        if (readObjectMethod != null) {</span><a href="#l1179"></a>
<span id="l1180">            try {</span><a href="#l1180"></a>
<span id="l1181">                readObjectMethod.invoke(obj, new Object[]{ in });</span><a href="#l1181"></a>
<span id="l1182">            } catch (InvocationTargetException ex) {</span><a href="#l1182"></a>
<span id="l1183">                Throwable th = ex.getTargetException();</span><a href="#l1183"></a>
<span id="l1184">                if (th instanceof ClassNotFoundException) {</span><a href="#l1184"></a>
<span id="l1185">                    throw (ClassNotFoundException) th;</span><a href="#l1185"></a>
<span id="l1186">                } else if (th instanceof IOException) {</span><a href="#l1186"></a>
<span id="l1187">                    throw (IOException) th;</span><a href="#l1187"></a>
<span id="l1188">                } else {</span><a href="#l1188"></a>
<span id="l1189">                    throwMiscException(th);</span><a href="#l1189"></a>
<span id="l1190">                }</span><a href="#l1190"></a>
<span id="l1191">            } catch (IllegalAccessException ex) {</span><a href="#l1191"></a>
<span id="l1192">                // should not occur, as access checks have been suppressed</span><a href="#l1192"></a>
<span id="l1193">                throw new InternalError(ex);</span><a href="#l1193"></a>
<span id="l1194">            }</span><a href="#l1194"></a>
<span id="l1195">        } else {</span><a href="#l1195"></a>
<span id="l1196">            throw new UnsupportedOperationException();</span><a href="#l1196"></a>
<span id="l1197">        }</span><a href="#l1197"></a>
<span id="l1198">    }</span><a href="#l1198"></a>
<span id="l1199"></span><a href="#l1199"></a>
<span id="l1200">    /**</span><a href="#l1200"></a>
<span id="l1201">     * Invokes the readObjectNoData method of the represented serializable</span><a href="#l1201"></a>
<span id="l1202">     * class.  Throws UnsupportedOperationException if this class descriptor is</span><a href="#l1202"></a>
<span id="l1203">     * not associated with a class, or if the class is externalizable,</span><a href="#l1203"></a>
<span id="l1204">     * non-serializable or does not define readObjectNoData.</span><a href="#l1204"></a>
<span id="l1205">     */</span><a href="#l1205"></a>
<span id="l1206">    void invokeReadObjectNoData(Object obj)</span><a href="#l1206"></a>
<span id="l1207">        throws IOException, UnsupportedOperationException</span><a href="#l1207"></a>
<span id="l1208">    {</span><a href="#l1208"></a>
<span id="l1209">        requireInitialized();</span><a href="#l1209"></a>
<span id="l1210">        if (readObjectNoDataMethod != null) {</span><a href="#l1210"></a>
<span id="l1211">            try {</span><a href="#l1211"></a>
<span id="l1212">                readObjectNoDataMethod.invoke(obj, (Object[]) null);</span><a href="#l1212"></a>
<span id="l1213">            } catch (InvocationTargetException ex) {</span><a href="#l1213"></a>
<span id="l1214">                Throwable th = ex.getTargetException();</span><a href="#l1214"></a>
<span id="l1215">                if (th instanceof ObjectStreamException) {</span><a href="#l1215"></a>
<span id="l1216">                    throw (ObjectStreamException) th;</span><a href="#l1216"></a>
<span id="l1217">                } else {</span><a href="#l1217"></a>
<span id="l1218">                    throwMiscException(th);</span><a href="#l1218"></a>
<span id="l1219">                }</span><a href="#l1219"></a>
<span id="l1220">            } catch (IllegalAccessException ex) {</span><a href="#l1220"></a>
<span id="l1221">                // should not occur, as access checks have been suppressed</span><a href="#l1221"></a>
<span id="l1222">                throw new InternalError(ex);</span><a href="#l1222"></a>
<span id="l1223">            }</span><a href="#l1223"></a>
<span id="l1224">        } else {</span><a href="#l1224"></a>
<span id="l1225">            throw new UnsupportedOperationException();</span><a href="#l1225"></a>
<span id="l1226">        }</span><a href="#l1226"></a>
<span id="l1227">    }</span><a href="#l1227"></a>
<span id="l1228"></span><a href="#l1228"></a>
<span id="l1229">    /**</span><a href="#l1229"></a>
<span id="l1230">     * Invokes the writeReplace method of the represented serializable class and</span><a href="#l1230"></a>
<span id="l1231">     * returns the result.  Throws UnsupportedOperationException if this class</span><a href="#l1231"></a>
<span id="l1232">     * descriptor is not associated with a class, or if the class is</span><a href="#l1232"></a>
<span id="l1233">     * non-serializable or does not define writeReplace.</span><a href="#l1233"></a>
<span id="l1234">     */</span><a href="#l1234"></a>
<span id="l1235">    Object invokeWriteReplace(Object obj)</span><a href="#l1235"></a>
<span id="l1236">        throws IOException, UnsupportedOperationException</span><a href="#l1236"></a>
<span id="l1237">    {</span><a href="#l1237"></a>
<span id="l1238">        requireInitialized();</span><a href="#l1238"></a>
<span id="l1239">        if (writeReplaceMethod != null) {</span><a href="#l1239"></a>
<span id="l1240">            try {</span><a href="#l1240"></a>
<span id="l1241">                return writeReplaceMethod.invoke(obj, (Object[]) null);</span><a href="#l1241"></a>
<span id="l1242">            } catch (InvocationTargetException ex) {</span><a href="#l1242"></a>
<span id="l1243">                Throwable th = ex.getTargetException();</span><a href="#l1243"></a>
<span id="l1244">                if (th instanceof ObjectStreamException) {</span><a href="#l1244"></a>
<span id="l1245">                    throw (ObjectStreamException) th;</span><a href="#l1245"></a>
<span id="l1246">                } else {</span><a href="#l1246"></a>
<span id="l1247">                    throwMiscException(th);</span><a href="#l1247"></a>
<span id="l1248">                    throw new InternalError(th);  // never reached</span><a href="#l1248"></a>
<span id="l1249">                }</span><a href="#l1249"></a>
<span id="l1250">            } catch (IllegalAccessException ex) {</span><a href="#l1250"></a>
<span id="l1251">                // should not occur, as access checks have been suppressed</span><a href="#l1251"></a>
<span id="l1252">                throw new InternalError(ex);</span><a href="#l1252"></a>
<span id="l1253">            }</span><a href="#l1253"></a>
<span id="l1254">        } else {</span><a href="#l1254"></a>
<span id="l1255">            throw new UnsupportedOperationException();</span><a href="#l1255"></a>
<span id="l1256">        }</span><a href="#l1256"></a>
<span id="l1257">    }</span><a href="#l1257"></a>
<span id="l1258"></span><a href="#l1258"></a>
<span id="l1259">    /**</span><a href="#l1259"></a>
<span id="l1260">     * Invokes the readResolve method of the represented serializable class and</span><a href="#l1260"></a>
<span id="l1261">     * returns the result.  Throws UnsupportedOperationException if this class</span><a href="#l1261"></a>
<span id="l1262">     * descriptor is not associated with a class, or if the class is</span><a href="#l1262"></a>
<span id="l1263">     * non-serializable or does not define readResolve.</span><a href="#l1263"></a>
<span id="l1264">     */</span><a href="#l1264"></a>
<span id="l1265">    Object invokeReadResolve(Object obj)</span><a href="#l1265"></a>
<span id="l1266">        throws IOException, UnsupportedOperationException</span><a href="#l1266"></a>
<span id="l1267">    {</span><a href="#l1267"></a>
<span id="l1268">        requireInitialized();</span><a href="#l1268"></a>
<span id="l1269">        if (readResolveMethod != null) {</span><a href="#l1269"></a>
<span id="l1270">            try {</span><a href="#l1270"></a>
<span id="l1271">                return readResolveMethod.invoke(obj, (Object[]) null);</span><a href="#l1271"></a>
<span id="l1272">            } catch (InvocationTargetException ex) {</span><a href="#l1272"></a>
<span id="l1273">                Throwable th = ex.getTargetException();</span><a href="#l1273"></a>
<span id="l1274">                if (th instanceof ObjectStreamException) {</span><a href="#l1274"></a>
<span id="l1275">                    throw (ObjectStreamException) th;</span><a href="#l1275"></a>
<span id="l1276">                } else {</span><a href="#l1276"></a>
<span id="l1277">                    throwMiscException(th);</span><a href="#l1277"></a>
<span id="l1278">                    throw new InternalError(th);  // never reached</span><a href="#l1278"></a>
<span id="l1279">                }</span><a href="#l1279"></a>
<span id="l1280">            } catch (IllegalAccessException ex) {</span><a href="#l1280"></a>
<span id="l1281">                // should not occur, as access checks have been suppressed</span><a href="#l1281"></a>
<span id="l1282">                throw new InternalError(ex);</span><a href="#l1282"></a>
<span id="l1283">            }</span><a href="#l1283"></a>
<span id="l1284">        } else {</span><a href="#l1284"></a>
<span id="l1285">            throw new UnsupportedOperationException();</span><a href="#l1285"></a>
<span id="l1286">        }</span><a href="#l1286"></a>
<span id="l1287">    }</span><a href="#l1287"></a>
<span id="l1288"></span><a href="#l1288"></a>
<span id="l1289">    /**</span><a href="#l1289"></a>
<span id="l1290">     * Class representing the portion of an object's serialized form allotted</span><a href="#l1290"></a>
<span id="l1291">     * to data described by a given class descriptor.  If &quot;hasData&quot; is false,</span><a href="#l1291"></a>
<span id="l1292">     * the object's serialized form does not contain data associated with the</span><a href="#l1292"></a>
<span id="l1293">     * class descriptor.</span><a href="#l1293"></a>
<span id="l1294">     */</span><a href="#l1294"></a>
<span id="l1295">    static class ClassDataSlot {</span><a href="#l1295"></a>
<span id="l1296"></span><a href="#l1296"></a>
<span id="l1297">        /** class descriptor &quot;occupying&quot; this slot */</span><a href="#l1297"></a>
<span id="l1298">        final ObjectStreamClass desc;</span><a href="#l1298"></a>
<span id="l1299">        /** true if serialized form includes data for this slot's descriptor */</span><a href="#l1299"></a>
<span id="l1300">        final boolean hasData;</span><a href="#l1300"></a>
<span id="l1301"></span><a href="#l1301"></a>
<span id="l1302">        ClassDataSlot(ObjectStreamClass desc, boolean hasData) {</span><a href="#l1302"></a>
<span id="l1303">            this.desc = desc;</span><a href="#l1303"></a>
<span id="l1304">            this.hasData = hasData;</span><a href="#l1304"></a>
<span id="l1305">        }</span><a href="#l1305"></a>
<span id="l1306">    }</span><a href="#l1306"></a>
<span id="l1307"></span><a href="#l1307"></a>
<span id="l1308">    /**</span><a href="#l1308"></a>
<span id="l1309">     * Returns array of ClassDataSlot instances representing the data layout</span><a href="#l1309"></a>
<span id="l1310">     * (including superclass data) for serialized objects described by this</span><a href="#l1310"></a>
<span id="l1311">     * class descriptor.  ClassDataSlots are ordered by inheritance with those</span><a href="#l1311"></a>
<span id="l1312">     * containing &quot;higher&quot; superclasses appearing first.  The final</span><a href="#l1312"></a>
<span id="l1313">     * ClassDataSlot contains a reference to this descriptor.</span><a href="#l1313"></a>
<span id="l1314">     */</span><a href="#l1314"></a>
<span id="l1315">    ClassDataSlot[] getClassDataLayout() throws InvalidClassException {</span><a href="#l1315"></a>
<span id="l1316">        // REMIND: synchronize instead of relying on volatile?</span><a href="#l1316"></a>
<span id="l1317">        if (dataLayout == null) {</span><a href="#l1317"></a>
<span id="l1318">            dataLayout = getClassDataLayout0();</span><a href="#l1318"></a>
<span id="l1319">        }</span><a href="#l1319"></a>
<span id="l1320">        return dataLayout;</span><a href="#l1320"></a>
<span id="l1321">    }</span><a href="#l1321"></a>
<span id="l1322"></span><a href="#l1322"></a>
<span id="l1323">    private ClassDataSlot[] getClassDataLayout0()</span><a href="#l1323"></a>
<span id="l1324">        throws InvalidClassException</span><a href="#l1324"></a>
<span id="l1325">    {</span><a href="#l1325"></a>
<span id="l1326">        ArrayList&lt;ClassDataSlot&gt; slots = new ArrayList&lt;&gt;();</span><a href="#l1326"></a>
<span id="l1327">        Class&lt;?&gt; start = cl, end = cl;</span><a href="#l1327"></a>
<span id="l1328"></span><a href="#l1328"></a>
<span id="l1329">        // locate closest non-serializable superclass</span><a href="#l1329"></a>
<span id="l1330">        while (end != null &amp;&amp; Serializable.class.isAssignableFrom(end)) {</span><a href="#l1330"></a>
<span id="l1331">            end = end.getSuperclass();</span><a href="#l1331"></a>
<span id="l1332">        }</span><a href="#l1332"></a>
<span id="l1333"></span><a href="#l1333"></a>
<span id="l1334">        HashSet&lt;String&gt; oscNames = new HashSet&lt;&gt;(3);</span><a href="#l1334"></a>
<span id="l1335"></span><a href="#l1335"></a>
<span id="l1336">        for (ObjectStreamClass d = this; d != null; d = d.superDesc) {</span><a href="#l1336"></a>
<span id="l1337">            if (oscNames.contains(d.name)) {</span><a href="#l1337"></a>
<span id="l1338">                throw new InvalidClassException(&quot;Circular reference.&quot;);</span><a href="#l1338"></a>
<span id="l1339">            } else {</span><a href="#l1339"></a>
<span id="l1340">                oscNames.add(d.name);</span><a href="#l1340"></a>
<span id="l1341">            }</span><a href="#l1341"></a>
<span id="l1342"></span><a href="#l1342"></a>
<span id="l1343">            // search up inheritance hierarchy for class with matching name</span><a href="#l1343"></a>
<span id="l1344">            String searchName = (d.cl != null) ? d.cl.getName() : d.name;</span><a href="#l1344"></a>
<span id="l1345">            Class&lt;?&gt; match = null;</span><a href="#l1345"></a>
<span id="l1346">            for (Class&lt;?&gt; c = start; c != end; c = c.getSuperclass()) {</span><a href="#l1346"></a>
<span id="l1347">                if (searchName.equals(c.getName())) {</span><a href="#l1347"></a>
<span id="l1348">                    match = c;</span><a href="#l1348"></a>
<span id="l1349">                    break;</span><a href="#l1349"></a>
<span id="l1350">                }</span><a href="#l1350"></a>
<span id="l1351">            }</span><a href="#l1351"></a>
<span id="l1352"></span><a href="#l1352"></a>
<span id="l1353">            // add &quot;no data&quot; slot for each unmatched class below match</span><a href="#l1353"></a>
<span id="l1354">            if (match != null) {</span><a href="#l1354"></a>
<span id="l1355">                for (Class&lt;?&gt; c = start; c != match; c = c.getSuperclass()) {</span><a href="#l1355"></a>
<span id="l1356">                    slots.add(new ClassDataSlot(</span><a href="#l1356"></a>
<span id="l1357">                        ObjectStreamClass.lookup(c, true), false));</span><a href="#l1357"></a>
<span id="l1358">                }</span><a href="#l1358"></a>
<span id="l1359">                start = match.getSuperclass();</span><a href="#l1359"></a>
<span id="l1360">            }</span><a href="#l1360"></a>
<span id="l1361"></span><a href="#l1361"></a>
<span id="l1362">            // record descriptor/class pairing</span><a href="#l1362"></a>
<span id="l1363">            slots.add(new ClassDataSlot(d.getVariantFor(match), true));</span><a href="#l1363"></a>
<span id="l1364">        }</span><a href="#l1364"></a>
<span id="l1365"></span><a href="#l1365"></a>
<span id="l1366">        // add &quot;no data&quot; slot for any leftover unmatched classes</span><a href="#l1366"></a>
<span id="l1367">        for (Class&lt;?&gt; c = start; c != end; c = c.getSuperclass()) {</span><a href="#l1367"></a>
<span id="l1368">            slots.add(new ClassDataSlot(</span><a href="#l1368"></a>
<span id="l1369">                ObjectStreamClass.lookup(c, true), false));</span><a href="#l1369"></a>
<span id="l1370">        }</span><a href="#l1370"></a>
<span id="l1371"></span><a href="#l1371"></a>
<span id="l1372">        // order slots from superclass -&gt; subclass</span><a href="#l1372"></a>
<span id="l1373">        Collections.reverse(slots);</span><a href="#l1373"></a>
<span id="l1374">        return slots.toArray(new ClassDataSlot[slots.size()]);</span><a href="#l1374"></a>
<span id="l1375">    }</span><a href="#l1375"></a>
<span id="l1376"></span><a href="#l1376"></a>
<span id="l1377">    /**</span><a href="#l1377"></a>
<span id="l1378">     * Returns aggregate size (in bytes) of marshalled primitive field values</span><a href="#l1378"></a>
<span id="l1379">     * for represented class.</span><a href="#l1379"></a>
<span id="l1380">     */</span><a href="#l1380"></a>
<span id="l1381">    int getPrimDataSize() {</span><a href="#l1381"></a>
<span id="l1382">        return primDataSize;</span><a href="#l1382"></a>
<span id="l1383">    }</span><a href="#l1383"></a>
<span id="l1384"></span><a href="#l1384"></a>
<span id="l1385">    /**</span><a href="#l1385"></a>
<span id="l1386">     * Returns number of non-primitive serializable fields of represented</span><a href="#l1386"></a>
<span id="l1387">     * class.</span><a href="#l1387"></a>
<span id="l1388">     */</span><a href="#l1388"></a>
<span id="l1389">    int getNumObjFields() {</span><a href="#l1389"></a>
<span id="l1390">        return numObjFields;</span><a href="#l1390"></a>
<span id="l1391">    }</span><a href="#l1391"></a>
<span id="l1392"></span><a href="#l1392"></a>
<span id="l1393">    /**</span><a href="#l1393"></a>
<span id="l1394">     * Fetches the serializable primitive field values of object obj and</span><a href="#l1394"></a>
<span id="l1395">     * marshals them into byte array buf starting at offset 0.  It is the</span><a href="#l1395"></a>
<span id="l1396">     * responsibility of the caller to ensure that obj is of the proper type if</span><a href="#l1396"></a>
<span id="l1397">     * non-null.</span><a href="#l1397"></a>
<span id="l1398">     */</span><a href="#l1398"></a>
<span id="l1399">    void getPrimFieldValues(Object obj, byte[] buf) {</span><a href="#l1399"></a>
<span id="l1400">        fieldRefl.getPrimFieldValues(obj, buf);</span><a href="#l1400"></a>
<span id="l1401">    }</span><a href="#l1401"></a>
<span id="l1402"></span><a href="#l1402"></a>
<span id="l1403">    /**</span><a href="#l1403"></a>
<span id="l1404">     * Sets the serializable primitive fields of object obj using values</span><a href="#l1404"></a>
<span id="l1405">     * unmarshalled from byte array buf starting at offset 0.  It is the</span><a href="#l1405"></a>
<span id="l1406">     * responsibility of the caller to ensure that obj is of the proper type if</span><a href="#l1406"></a>
<span id="l1407">     * non-null.</span><a href="#l1407"></a>
<span id="l1408">     */</span><a href="#l1408"></a>
<span id="l1409">    void setPrimFieldValues(Object obj, byte[] buf) {</span><a href="#l1409"></a>
<span id="l1410">        fieldRefl.setPrimFieldValues(obj, buf);</span><a href="#l1410"></a>
<span id="l1411">    }</span><a href="#l1411"></a>
<span id="l1412"></span><a href="#l1412"></a>
<span id="l1413">    /**</span><a href="#l1413"></a>
<span id="l1414">     * Fetches the serializable object field values of object obj and stores</span><a href="#l1414"></a>
<span id="l1415">     * them in array vals starting at offset 0.  It is the responsibility of</span><a href="#l1415"></a>
<span id="l1416">     * the caller to ensure that obj is of the proper type if non-null.</span><a href="#l1416"></a>
<span id="l1417">     */</span><a href="#l1417"></a>
<span id="l1418">    void getObjFieldValues(Object obj, Object[] vals) {</span><a href="#l1418"></a>
<span id="l1419">        fieldRefl.getObjFieldValues(obj, vals);</span><a href="#l1419"></a>
<span id="l1420">    }</span><a href="#l1420"></a>
<span id="l1421"></span><a href="#l1421"></a>
<span id="l1422">    /**</span><a href="#l1422"></a>
<span id="l1423">     * Sets the serializable object fields of object obj using values from</span><a href="#l1423"></a>
<span id="l1424">     * array vals starting at offset 0.  It is the responsibility of the caller</span><a href="#l1424"></a>
<span id="l1425">     * to ensure that obj is of the proper type if non-null.</span><a href="#l1425"></a>
<span id="l1426">     */</span><a href="#l1426"></a>
<span id="l1427">    void setObjFieldValues(Object obj, Object[] vals) {</span><a href="#l1427"></a>
<span id="l1428">        fieldRefl.setObjFieldValues(obj, vals);</span><a href="#l1428"></a>
<span id="l1429">    }</span><a href="#l1429"></a>
<span id="l1430"></span><a href="#l1430"></a>
<span id="l1431">    /**</span><a href="#l1431"></a>
<span id="l1432">     * Calculates and sets serializable field offsets, as well as primitive</span><a href="#l1432"></a>
<span id="l1433">     * data size and object field count totals.  Throws InvalidClassException</span><a href="#l1433"></a>
<span id="l1434">     * if fields are illegally ordered.</span><a href="#l1434"></a>
<span id="l1435">     */</span><a href="#l1435"></a>
<span id="l1436">    private void computeFieldOffsets() throws InvalidClassException {</span><a href="#l1436"></a>
<span id="l1437">        primDataSize = 0;</span><a href="#l1437"></a>
<span id="l1438">        numObjFields = 0;</span><a href="#l1438"></a>
<span id="l1439">        int firstObjIndex = -1;</span><a href="#l1439"></a>
<span id="l1440"></span><a href="#l1440"></a>
<span id="l1441">        for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l1441"></a>
<span id="l1442">            ObjectStreamField f = fields[i];</span><a href="#l1442"></a>
<span id="l1443">            switch (f.getTypeCode()) {</span><a href="#l1443"></a>
<span id="l1444">                case 'Z':</span><a href="#l1444"></a>
<span id="l1445">                case 'B':</span><a href="#l1445"></a>
<span id="l1446">                    f.setOffset(primDataSize++);</span><a href="#l1446"></a>
<span id="l1447">                    break;</span><a href="#l1447"></a>
<span id="l1448"></span><a href="#l1448"></a>
<span id="l1449">                case 'C':</span><a href="#l1449"></a>
<span id="l1450">                case 'S':</span><a href="#l1450"></a>
<span id="l1451">                    f.setOffset(primDataSize);</span><a href="#l1451"></a>
<span id="l1452">                    primDataSize += 2;</span><a href="#l1452"></a>
<span id="l1453">                    break;</span><a href="#l1453"></a>
<span id="l1454"></span><a href="#l1454"></a>
<span id="l1455">                case 'I':</span><a href="#l1455"></a>
<span id="l1456">                case 'F':</span><a href="#l1456"></a>
<span id="l1457">                    f.setOffset(primDataSize);</span><a href="#l1457"></a>
<span id="l1458">                    primDataSize += 4;</span><a href="#l1458"></a>
<span id="l1459">                    break;</span><a href="#l1459"></a>
<span id="l1460"></span><a href="#l1460"></a>
<span id="l1461">                case 'J':</span><a href="#l1461"></a>
<span id="l1462">                case 'D':</span><a href="#l1462"></a>
<span id="l1463">                    f.setOffset(primDataSize);</span><a href="#l1463"></a>
<span id="l1464">                    primDataSize += 8;</span><a href="#l1464"></a>
<span id="l1465">                    break;</span><a href="#l1465"></a>
<span id="l1466"></span><a href="#l1466"></a>
<span id="l1467">                case '[':</span><a href="#l1467"></a>
<span id="l1468">                case 'L':</span><a href="#l1468"></a>
<span id="l1469">                    f.setOffset(numObjFields++);</span><a href="#l1469"></a>
<span id="l1470">                    if (firstObjIndex == -1) {</span><a href="#l1470"></a>
<span id="l1471">                        firstObjIndex = i;</span><a href="#l1471"></a>
<span id="l1472">                    }</span><a href="#l1472"></a>
<span id="l1473">                    break;</span><a href="#l1473"></a>
<span id="l1474"></span><a href="#l1474"></a>
<span id="l1475">                default:</span><a href="#l1475"></a>
<span id="l1476">                    throw new InternalError();</span><a href="#l1476"></a>
<span id="l1477">            }</span><a href="#l1477"></a>
<span id="l1478">        }</span><a href="#l1478"></a>
<span id="l1479">        if (firstObjIndex != -1 &amp;&amp;</span><a href="#l1479"></a>
<span id="l1480">            firstObjIndex + numObjFields != fields.length)</span><a href="#l1480"></a>
<span id="l1481">        {</span><a href="#l1481"></a>
<span id="l1482">            throw new InvalidClassException(name, &quot;illegal field order&quot;);</span><a href="#l1482"></a>
<span id="l1483">        }</span><a href="#l1483"></a>
<span id="l1484">    }</span><a href="#l1484"></a>
<span id="l1485"></span><a href="#l1485"></a>
<span id="l1486">    /**</span><a href="#l1486"></a>
<span id="l1487">     * If given class is the same as the class associated with this class</span><a href="#l1487"></a>
<span id="l1488">     * descriptor, returns reference to this class descriptor.  Otherwise,</span><a href="#l1488"></a>
<span id="l1489">     * returns variant of this class descriptor bound to given class.</span><a href="#l1489"></a>
<span id="l1490">     */</span><a href="#l1490"></a>
<span id="l1491">    private ObjectStreamClass getVariantFor(Class&lt;?&gt; cl)</span><a href="#l1491"></a>
<span id="l1492">        throws InvalidClassException</span><a href="#l1492"></a>
<span id="l1493">    {</span><a href="#l1493"></a>
<span id="l1494">        if (this.cl == cl) {</span><a href="#l1494"></a>
<span id="l1495">            return this;</span><a href="#l1495"></a>
<span id="l1496">        }</span><a href="#l1496"></a>
<span id="l1497">        ObjectStreamClass desc = new ObjectStreamClass();</span><a href="#l1497"></a>
<span id="l1498">        if (isProxy) {</span><a href="#l1498"></a>
<span id="l1499">            desc.initProxy(cl, null, superDesc);</span><a href="#l1499"></a>
<span id="l1500">        } else {</span><a href="#l1500"></a>
<span id="l1501">            desc.initNonProxy(this, cl, null, superDesc);</span><a href="#l1501"></a>
<span id="l1502">        }</span><a href="#l1502"></a>
<span id="l1503">        return desc;</span><a href="#l1503"></a>
<span id="l1504">    }</span><a href="#l1504"></a>
<span id="l1505"></span><a href="#l1505"></a>
<span id="l1506">    /**</span><a href="#l1506"></a>
<span id="l1507">     * Returns public no-arg constructor of given class, or null if none found.</span><a href="#l1507"></a>
<span id="l1508">     * Access checks are disabled on the returned constructor (if any), since</span><a href="#l1508"></a>
<span id="l1509">     * the defining class may still be non-public.</span><a href="#l1509"></a>
<span id="l1510">     */</span><a href="#l1510"></a>
<span id="l1511">    private static Constructor&lt;?&gt; getExternalizableConstructor(Class&lt;?&gt; cl) {</span><a href="#l1511"></a>
<span id="l1512">        try {</span><a href="#l1512"></a>
<span id="l1513">            Constructor&lt;?&gt; cons = cl.getDeclaredConstructor((Class&lt;?&gt;[]) null);</span><a href="#l1513"></a>
<span id="l1514">            cons.setAccessible(true);</span><a href="#l1514"></a>
<span id="l1515">            return ((cons.getModifiers() &amp; Modifier.PUBLIC) != 0) ?</span><a href="#l1515"></a>
<span id="l1516">                cons : null;</span><a href="#l1516"></a>
<span id="l1517">        } catch (NoSuchMethodException ex) {</span><a href="#l1517"></a>
<span id="l1518">            return null;</span><a href="#l1518"></a>
<span id="l1519">        }</span><a href="#l1519"></a>
<span id="l1520">    }</span><a href="#l1520"></a>
<span id="l1521"></span><a href="#l1521"></a>
<span id="l1522">    /**</span><a href="#l1522"></a>
<span id="l1523">     * Given a class, determines whether its superclass has</span><a href="#l1523"></a>
<span id="l1524">     * any constructors that are accessible from the class.</span><a href="#l1524"></a>
<span id="l1525">     * This is a special purpose method intended to do access</span><a href="#l1525"></a>
<span id="l1526">     * checking for a serializable class and its superclasses</span><a href="#l1526"></a>
<span id="l1527">     * up to, but not including, the first non-serializable</span><a href="#l1527"></a>
<span id="l1528">     * superclass. This also implies that the superclass is</span><a href="#l1528"></a>
<span id="l1529">     * always non-null, because a serializable class must be a</span><a href="#l1529"></a>
<span id="l1530">     * class (not an interface) and Object is not serializable.</span><a href="#l1530"></a>
<span id="l1531">     *</span><a href="#l1531"></a>
<span id="l1532">     * @param cl the class from which access is checked</span><a href="#l1532"></a>
<span id="l1533">     * @return whether the superclass has a constructor accessible from cl</span><a href="#l1533"></a>
<span id="l1534">     */</span><a href="#l1534"></a>
<span id="l1535">    private static boolean superHasAccessibleConstructor(Class&lt;?&gt; cl) {</span><a href="#l1535"></a>
<span id="l1536">        Class&lt;?&gt; superCl = cl.getSuperclass();</span><a href="#l1536"></a>
<span id="l1537">        assert Serializable.class.isAssignableFrom(cl);</span><a href="#l1537"></a>
<span id="l1538">        assert superCl != null;</span><a href="#l1538"></a>
<span id="l1539">        if (packageEquals(cl, superCl)) {</span><a href="#l1539"></a>
<span id="l1540">            // accessible if any non-private constructor is found</span><a href="#l1540"></a>
<span id="l1541">            for (Constructor&lt;?&gt; ctor : superCl.getDeclaredConstructors()) {</span><a href="#l1541"></a>
<span id="l1542">                if ((ctor.getModifiers() &amp; Modifier.PRIVATE) == 0) {</span><a href="#l1542"></a>
<span id="l1543">                    return true;</span><a href="#l1543"></a>
<span id="l1544">                }</span><a href="#l1544"></a>
<span id="l1545">            }</span><a href="#l1545"></a>
<span id="l1546">            return false;</span><a href="#l1546"></a>
<span id="l1547">        } else {</span><a href="#l1547"></a>
<span id="l1548">            // sanity check to ensure the parent is protected or public</span><a href="#l1548"></a>
<span id="l1549">            if ((superCl.getModifiers() &amp; (Modifier.PROTECTED | Modifier.PUBLIC)) == 0) {</span><a href="#l1549"></a>
<span id="l1550">                return false;</span><a href="#l1550"></a>
<span id="l1551">            }</span><a href="#l1551"></a>
<span id="l1552">            // accessible if any constructor is protected or public</span><a href="#l1552"></a>
<span id="l1553">            for (Constructor&lt;?&gt; ctor : superCl.getDeclaredConstructors()) {</span><a href="#l1553"></a>
<span id="l1554">                if ((ctor.getModifiers() &amp; (Modifier.PROTECTED | Modifier.PUBLIC)) != 0) {</span><a href="#l1554"></a>
<span id="l1555">                    return true;</span><a href="#l1555"></a>
<span id="l1556">                }</span><a href="#l1556"></a>
<span id="l1557">            }</span><a href="#l1557"></a>
<span id="l1558">            return false;</span><a href="#l1558"></a>
<span id="l1559">        }</span><a href="#l1559"></a>
<span id="l1560">    }</span><a href="#l1560"></a>
<span id="l1561"></span><a href="#l1561"></a>
<span id="l1562">    /**</span><a href="#l1562"></a>
<span id="l1563">     * Returns subclass-accessible no-arg constructor of first non-serializable</span><a href="#l1563"></a>
<span id="l1564">     * superclass, or null if none found.  Access checks are disabled on the</span><a href="#l1564"></a>
<span id="l1565">     * returned constructor (if any).</span><a href="#l1565"></a>
<span id="l1566">     */</span><a href="#l1566"></a>
<span id="l1567">    private static Constructor&lt;?&gt; getSerializableConstructor(Class&lt;?&gt; cl) {</span><a href="#l1567"></a>
<span id="l1568">        Class&lt;?&gt; initCl = cl;</span><a href="#l1568"></a>
<span id="l1569">        while (Serializable.class.isAssignableFrom(initCl)) {</span><a href="#l1569"></a>
<span id="l1570">            Class&lt;?&gt; prev = initCl;</span><a href="#l1570"></a>
<span id="l1571">            if ((initCl = initCl.getSuperclass()) == null ||</span><a href="#l1571"></a>
<span id="l1572">                (!disableSerialConstructorChecks &amp;&amp; !superHasAccessibleConstructor(prev))) {</span><a href="#l1572"></a>
<span id="l1573">                return null;</span><a href="#l1573"></a>
<span id="l1574">            }</span><a href="#l1574"></a>
<span id="l1575">        }</span><a href="#l1575"></a>
<span id="l1576">        try {</span><a href="#l1576"></a>
<span id="l1577">            Constructor&lt;?&gt; cons = initCl.getDeclaredConstructor((Class&lt;?&gt;[]) null);</span><a href="#l1577"></a>
<span id="l1578">            int mods = cons.getModifiers();</span><a href="#l1578"></a>
<span id="l1579">            if ((mods &amp; Modifier.PRIVATE) != 0 ||</span><a href="#l1579"></a>
<span id="l1580">                ((mods &amp; (Modifier.PUBLIC | Modifier.PROTECTED)) == 0 &amp;&amp;</span><a href="#l1580"></a>
<span id="l1581">                 !packageEquals(cl, initCl)))</span><a href="#l1581"></a>
<span id="l1582">            {</span><a href="#l1582"></a>
<span id="l1583">                return null;</span><a href="#l1583"></a>
<span id="l1584">            }</span><a href="#l1584"></a>
<span id="l1585">            cons = reflFactory.newConstructorForSerialization(cl, cons);</span><a href="#l1585"></a>
<span id="l1586">            cons.setAccessible(true);</span><a href="#l1586"></a>
<span id="l1587">            return cons;</span><a href="#l1587"></a>
<span id="l1588">        } catch (NoSuchMethodException ex) {</span><a href="#l1588"></a>
<span id="l1589">            return null;</span><a href="#l1589"></a>
<span id="l1590">        }</span><a href="#l1590"></a>
<span id="l1591">    }</span><a href="#l1591"></a>
<span id="l1592"></span><a href="#l1592"></a>
<span id="l1593">    /**</span><a href="#l1593"></a>
<span id="l1594">     * Returns non-static, non-abstract method with given signature provided it</span><a href="#l1594"></a>
<span id="l1595">     * is defined by or accessible (via inheritance) by the given class, or</span><a href="#l1595"></a>
<span id="l1596">     * null if no match found.  Access checks are disabled on the returned</span><a href="#l1596"></a>
<span id="l1597">     * method (if any).</span><a href="#l1597"></a>
<span id="l1598">     */</span><a href="#l1598"></a>
<span id="l1599">    private static Method getInheritableMethod(Class&lt;?&gt; cl, String name,</span><a href="#l1599"></a>
<span id="l1600">                                               Class&lt;?&gt;[] argTypes,</span><a href="#l1600"></a>
<span id="l1601">                                               Class&lt;?&gt; returnType)</span><a href="#l1601"></a>
<span id="l1602">    {</span><a href="#l1602"></a>
<span id="l1603">        Method meth = null;</span><a href="#l1603"></a>
<span id="l1604">        Class&lt;?&gt; defCl = cl;</span><a href="#l1604"></a>
<span id="l1605">        while (defCl != null) {</span><a href="#l1605"></a>
<span id="l1606">            try {</span><a href="#l1606"></a>
<span id="l1607">                meth = defCl.getDeclaredMethod(name, argTypes);</span><a href="#l1607"></a>
<span id="l1608">                break;</span><a href="#l1608"></a>
<span id="l1609">            } catch (NoSuchMethodException ex) {</span><a href="#l1609"></a>
<span id="l1610">                defCl = defCl.getSuperclass();</span><a href="#l1610"></a>
<span id="l1611">            }</span><a href="#l1611"></a>
<span id="l1612">        }</span><a href="#l1612"></a>
<span id="l1613"></span><a href="#l1613"></a>
<span id="l1614">        if ((meth == null) || (meth.getReturnType() != returnType)) {</span><a href="#l1614"></a>
<span id="l1615">            return null;</span><a href="#l1615"></a>
<span id="l1616">        }</span><a href="#l1616"></a>
<span id="l1617">        meth.setAccessible(true);</span><a href="#l1617"></a>
<span id="l1618">        int mods = meth.getModifiers();</span><a href="#l1618"></a>
<span id="l1619">        if ((mods &amp; (Modifier.STATIC | Modifier.ABSTRACT)) != 0) {</span><a href="#l1619"></a>
<span id="l1620">            return null;</span><a href="#l1620"></a>
<span id="l1621">        } else if ((mods &amp; (Modifier.PUBLIC | Modifier.PROTECTED)) != 0) {</span><a href="#l1621"></a>
<span id="l1622">            return meth;</span><a href="#l1622"></a>
<span id="l1623">        } else if ((mods &amp; Modifier.PRIVATE) != 0) {</span><a href="#l1623"></a>
<span id="l1624">            return (cl == defCl) ? meth : null;</span><a href="#l1624"></a>
<span id="l1625">        } else {</span><a href="#l1625"></a>
<span id="l1626">            return packageEquals(cl, defCl) ? meth : null;</span><a href="#l1626"></a>
<span id="l1627">        }</span><a href="#l1627"></a>
<span id="l1628">    }</span><a href="#l1628"></a>
<span id="l1629"></span><a href="#l1629"></a>
<span id="l1630">    /**</span><a href="#l1630"></a>
<span id="l1631">     * Returns non-static private method with given signature defined by given</span><a href="#l1631"></a>
<span id="l1632">     * class, or null if none found.  Access checks are disabled on the</span><a href="#l1632"></a>
<span id="l1633">     * returned method (if any).</span><a href="#l1633"></a>
<span id="l1634">     */</span><a href="#l1634"></a>
<span id="l1635">    private static Method getPrivateMethod(Class&lt;?&gt; cl, String name,</span><a href="#l1635"></a>
<span id="l1636">                                           Class&lt;?&gt;[] argTypes,</span><a href="#l1636"></a>
<span id="l1637">                                           Class&lt;?&gt; returnType)</span><a href="#l1637"></a>
<span id="l1638">    {</span><a href="#l1638"></a>
<span id="l1639">        try {</span><a href="#l1639"></a>
<span id="l1640">            Method meth = cl.getDeclaredMethod(name, argTypes);</span><a href="#l1640"></a>
<span id="l1641">            meth.setAccessible(true);</span><a href="#l1641"></a>
<span id="l1642">            int mods = meth.getModifiers();</span><a href="#l1642"></a>
<span id="l1643">            return ((meth.getReturnType() == returnType) &amp;&amp;</span><a href="#l1643"></a>
<span id="l1644">                    ((mods &amp; Modifier.STATIC) == 0) &amp;&amp;</span><a href="#l1644"></a>
<span id="l1645">                    ((mods &amp; Modifier.PRIVATE) != 0)) ? meth : null;</span><a href="#l1645"></a>
<span id="l1646">        } catch (NoSuchMethodException ex) {</span><a href="#l1646"></a>
<span id="l1647">            return null;</span><a href="#l1647"></a>
<span id="l1648">        }</span><a href="#l1648"></a>
<span id="l1649">    }</span><a href="#l1649"></a>
<span id="l1650"></span><a href="#l1650"></a>
<span id="l1651">    /**</span><a href="#l1651"></a>
<span id="l1652">     * Returns true if classes are defined in the same runtime package, false</span><a href="#l1652"></a>
<span id="l1653">     * otherwise.</span><a href="#l1653"></a>
<span id="l1654">     */</span><a href="#l1654"></a>
<span id="l1655">    private static boolean packageEquals(Class&lt;?&gt; cl1, Class&lt;?&gt; cl2) {</span><a href="#l1655"></a>
<span id="l1656">        return (cl1.getClassLoader() == cl2.getClassLoader() &amp;&amp;</span><a href="#l1656"></a>
<span id="l1657">                getPackageName(cl1).equals(getPackageName(cl2)));</span><a href="#l1657"></a>
<span id="l1658">    }</span><a href="#l1658"></a>
<span id="l1659"></span><a href="#l1659"></a>
<span id="l1660">    /**</span><a href="#l1660"></a>
<span id="l1661">     * Returns package name of given class.</span><a href="#l1661"></a>
<span id="l1662">     */</span><a href="#l1662"></a>
<span id="l1663">    private static String getPackageName(Class&lt;?&gt; cl) {</span><a href="#l1663"></a>
<span id="l1664">        String s = cl.getName();</span><a href="#l1664"></a>
<span id="l1665">        int i = s.lastIndexOf('[');</span><a href="#l1665"></a>
<span id="l1666">        if (i &gt;= 0) {</span><a href="#l1666"></a>
<span id="l1667">            s = s.substring(i + 2);</span><a href="#l1667"></a>
<span id="l1668">        }</span><a href="#l1668"></a>
<span id="l1669">        i = s.lastIndexOf('.');</span><a href="#l1669"></a>
<span id="l1670">        return (i &gt;= 0) ? s.substring(0, i) : &quot;&quot;;</span><a href="#l1670"></a>
<span id="l1671">    }</span><a href="#l1671"></a>
<span id="l1672"></span><a href="#l1672"></a>
<span id="l1673">    /**</span><a href="#l1673"></a>
<span id="l1674">     * Compares class names for equality, ignoring package names.  Returns true</span><a href="#l1674"></a>
<span id="l1675">     * if class names equal, false otherwise.</span><a href="#l1675"></a>
<span id="l1676">     */</span><a href="#l1676"></a>
<span id="l1677">    private static boolean classNamesEqual(String name1, String name2) {</span><a href="#l1677"></a>
<span id="l1678">        name1 = name1.substring(name1.lastIndexOf('.') + 1);</span><a href="#l1678"></a>
<span id="l1679">        name2 = name2.substring(name2.lastIndexOf('.') + 1);</span><a href="#l1679"></a>
<span id="l1680">        return name1.equals(name2);</span><a href="#l1680"></a>
<span id="l1681">    }</span><a href="#l1681"></a>
<span id="l1682"></span><a href="#l1682"></a>
<span id="l1683">    /**</span><a href="#l1683"></a>
<span id="l1684">     * Returns JVM type signature for given class.</span><a href="#l1684"></a>
<span id="l1685">     */</span><a href="#l1685"></a>
<span id="l1686">    private static String getClassSignature(Class&lt;?&gt; cl) {</span><a href="#l1686"></a>
<span id="l1687">        StringBuilder sbuf = new StringBuilder();</span><a href="#l1687"></a>
<span id="l1688">        while (cl.isArray()) {</span><a href="#l1688"></a>
<span id="l1689">            sbuf.append('[');</span><a href="#l1689"></a>
<span id="l1690">            cl = cl.getComponentType();</span><a href="#l1690"></a>
<span id="l1691">        }</span><a href="#l1691"></a>
<span id="l1692">        if (cl.isPrimitive()) {</span><a href="#l1692"></a>
<span id="l1693">            if (cl == Integer.TYPE) {</span><a href="#l1693"></a>
<span id="l1694">                sbuf.append('I');</span><a href="#l1694"></a>
<span id="l1695">            } else if (cl == Byte.TYPE) {</span><a href="#l1695"></a>
<span id="l1696">                sbuf.append('B');</span><a href="#l1696"></a>
<span id="l1697">            } else if (cl == Long.TYPE) {</span><a href="#l1697"></a>
<span id="l1698">                sbuf.append('J');</span><a href="#l1698"></a>
<span id="l1699">            } else if (cl == Float.TYPE) {</span><a href="#l1699"></a>
<span id="l1700">                sbuf.append('F');</span><a href="#l1700"></a>
<span id="l1701">            } else if (cl == Double.TYPE) {</span><a href="#l1701"></a>
<span id="l1702">                sbuf.append('D');</span><a href="#l1702"></a>
<span id="l1703">            } else if (cl == Short.TYPE) {</span><a href="#l1703"></a>
<span id="l1704">                sbuf.append('S');</span><a href="#l1704"></a>
<span id="l1705">            } else if (cl == Character.TYPE) {</span><a href="#l1705"></a>
<span id="l1706">                sbuf.append('C');</span><a href="#l1706"></a>
<span id="l1707">            } else if (cl == Boolean.TYPE) {</span><a href="#l1707"></a>
<span id="l1708">                sbuf.append('Z');</span><a href="#l1708"></a>
<span id="l1709">            } else if (cl == Void.TYPE) {</span><a href="#l1709"></a>
<span id="l1710">                sbuf.append('V');</span><a href="#l1710"></a>
<span id="l1711">            } else {</span><a href="#l1711"></a>
<span id="l1712">                throw new InternalError();</span><a href="#l1712"></a>
<span id="l1713">            }</span><a href="#l1713"></a>
<span id="l1714">        } else {</span><a href="#l1714"></a>
<span id="l1715">            sbuf.append('L' + cl.getName().replace('.', '/') + ';');</span><a href="#l1715"></a>
<span id="l1716">        }</span><a href="#l1716"></a>
<span id="l1717">        return sbuf.toString();</span><a href="#l1717"></a>
<span id="l1718">    }</span><a href="#l1718"></a>
<span id="l1719"></span><a href="#l1719"></a>
<span id="l1720">    /**</span><a href="#l1720"></a>
<span id="l1721">     * Returns JVM type signature for given list of parameters and return type.</span><a href="#l1721"></a>
<span id="l1722">     */</span><a href="#l1722"></a>
<span id="l1723">    private static String getMethodSignature(Class&lt;?&gt;[] paramTypes,</span><a href="#l1723"></a>
<span id="l1724">                                             Class&lt;?&gt; retType)</span><a href="#l1724"></a>
<span id="l1725">    {</span><a href="#l1725"></a>
<span id="l1726">        StringBuilder sbuf = new StringBuilder();</span><a href="#l1726"></a>
<span id="l1727">        sbuf.append('(');</span><a href="#l1727"></a>
<span id="l1728">        for (int i = 0; i &lt; paramTypes.length; i++) {</span><a href="#l1728"></a>
<span id="l1729">            sbuf.append(getClassSignature(paramTypes[i]));</span><a href="#l1729"></a>
<span id="l1730">        }</span><a href="#l1730"></a>
<span id="l1731">        sbuf.append(')');</span><a href="#l1731"></a>
<span id="l1732">        sbuf.append(getClassSignature(retType));</span><a href="#l1732"></a>
<span id="l1733">        return sbuf.toString();</span><a href="#l1733"></a>
<span id="l1734">    }</span><a href="#l1734"></a>
<span id="l1735"></span><a href="#l1735"></a>
<span id="l1736">    /**</span><a href="#l1736"></a>
<span id="l1737">     * Convenience method for throwing an exception that is either a</span><a href="#l1737"></a>
<span id="l1738">     * RuntimeException, Error, or of some unexpected type (in which case it is</span><a href="#l1738"></a>
<span id="l1739">     * wrapped inside an IOException).</span><a href="#l1739"></a>
<span id="l1740">     */</span><a href="#l1740"></a>
<span id="l1741">    private static void throwMiscException(Throwable th) throws IOException {</span><a href="#l1741"></a>
<span id="l1742">        if (th instanceof RuntimeException) {</span><a href="#l1742"></a>
<span id="l1743">            throw (RuntimeException) th;</span><a href="#l1743"></a>
<span id="l1744">        } else if (th instanceof Error) {</span><a href="#l1744"></a>
<span id="l1745">            throw (Error) th;</span><a href="#l1745"></a>
<span id="l1746">        } else {</span><a href="#l1746"></a>
<span id="l1747">            IOException ex = new IOException(&quot;unexpected exception type&quot;);</span><a href="#l1747"></a>
<span id="l1748">            ex.initCause(th);</span><a href="#l1748"></a>
<span id="l1749">            throw ex;</span><a href="#l1749"></a>
<span id="l1750">        }</span><a href="#l1750"></a>
<span id="l1751">    }</span><a href="#l1751"></a>
<span id="l1752"></span><a href="#l1752"></a>
<span id="l1753">    /**</span><a href="#l1753"></a>
<span id="l1754">     * Returns ObjectStreamField array describing the serializable fields of</span><a href="#l1754"></a>
<span id="l1755">     * the given class.  Serializable fields backed by an actual field of the</span><a href="#l1755"></a>
<span id="l1756">     * class are represented by ObjectStreamFields with corresponding non-null</span><a href="#l1756"></a>
<span id="l1757">     * Field objects.  Throws InvalidClassException if the (explicitly</span><a href="#l1757"></a>
<span id="l1758">     * declared) serializable fields are invalid.</span><a href="#l1758"></a>
<span id="l1759">     */</span><a href="#l1759"></a>
<span id="l1760">    private static ObjectStreamField[] getSerialFields(Class&lt;?&gt; cl)</span><a href="#l1760"></a>
<span id="l1761">        throws InvalidClassException</span><a href="#l1761"></a>
<span id="l1762">    {</span><a href="#l1762"></a>
<span id="l1763">        ObjectStreamField[] fields;</span><a href="#l1763"></a>
<span id="l1764">        if (Serializable.class.isAssignableFrom(cl) &amp;&amp;</span><a href="#l1764"></a>
<span id="l1765">            !Externalizable.class.isAssignableFrom(cl) &amp;&amp;</span><a href="#l1765"></a>
<span id="l1766">            !Proxy.isProxyClass(cl) &amp;&amp;</span><a href="#l1766"></a>
<span id="l1767">            !cl.isInterface())</span><a href="#l1767"></a>
<span id="l1768">        {</span><a href="#l1768"></a>
<span id="l1769">            if ((fields = getDeclaredSerialFields(cl)) == null) {</span><a href="#l1769"></a>
<span id="l1770">                fields = getDefaultSerialFields(cl);</span><a href="#l1770"></a>
<span id="l1771">            }</span><a href="#l1771"></a>
<span id="l1772">            Arrays.sort(fields);</span><a href="#l1772"></a>
<span id="l1773">        } else {</span><a href="#l1773"></a>
<span id="l1774">            fields = NO_FIELDS;</span><a href="#l1774"></a>
<span id="l1775">        }</span><a href="#l1775"></a>
<span id="l1776">        return fields;</span><a href="#l1776"></a>
<span id="l1777">    }</span><a href="#l1777"></a>
<span id="l1778"></span><a href="#l1778"></a>
<span id="l1779">    /**</span><a href="#l1779"></a>
<span id="l1780">     * Returns serializable fields of given class as defined explicitly by a</span><a href="#l1780"></a>
<span id="l1781">     * &quot;serialPersistentFields&quot; field, or null if no appropriate</span><a href="#l1781"></a>
<span id="l1782">     * &quot;serialPersistentFields&quot; field is defined.  Serializable fields backed</span><a href="#l1782"></a>
<span id="l1783">     * by an actual field of the class are represented by ObjectStreamFields</span><a href="#l1783"></a>
<span id="l1784">     * with corresponding non-null Field objects.  For compatibility with past</span><a href="#l1784"></a>
<span id="l1785">     * releases, a &quot;serialPersistentFields&quot; field with a null value is</span><a href="#l1785"></a>
<span id="l1786">     * considered equivalent to not declaring &quot;serialPersistentFields&quot;.  Throws</span><a href="#l1786"></a>
<span id="l1787">     * InvalidClassException if the declared serializable fields are</span><a href="#l1787"></a>
<span id="l1788">     * invalid--e.g., if multiple fields share the same name.</span><a href="#l1788"></a>
<span id="l1789">     */</span><a href="#l1789"></a>
<span id="l1790">    private static ObjectStreamField[] getDeclaredSerialFields(Class&lt;?&gt; cl)</span><a href="#l1790"></a>
<span id="l1791">        throws InvalidClassException</span><a href="#l1791"></a>
<span id="l1792">    {</span><a href="#l1792"></a>
<span id="l1793">        ObjectStreamField[] serialPersistentFields = null;</span><a href="#l1793"></a>
<span id="l1794">        try {</span><a href="#l1794"></a>
<span id="l1795">            Field f = cl.getDeclaredField(&quot;serialPersistentFields&quot;);</span><a href="#l1795"></a>
<span id="l1796">            int mask = Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL;</span><a href="#l1796"></a>
<span id="l1797">            if ((f.getModifiers() &amp; mask) == mask) {</span><a href="#l1797"></a>
<span id="l1798">                f.setAccessible(true);</span><a href="#l1798"></a>
<span id="l1799">                serialPersistentFields = (ObjectStreamField[]) f.get(null);</span><a href="#l1799"></a>
<span id="l1800">            }</span><a href="#l1800"></a>
<span id="l1801">        } catch (Exception ex) {</span><a href="#l1801"></a>
<span id="l1802">        }</span><a href="#l1802"></a>
<span id="l1803">        if (serialPersistentFields == null) {</span><a href="#l1803"></a>
<span id="l1804">            return null;</span><a href="#l1804"></a>
<span id="l1805">        } else if (serialPersistentFields.length == 0) {</span><a href="#l1805"></a>
<span id="l1806">            return NO_FIELDS;</span><a href="#l1806"></a>
<span id="l1807">        }</span><a href="#l1807"></a>
<span id="l1808"></span><a href="#l1808"></a>
<span id="l1809">        ObjectStreamField[] boundFields =</span><a href="#l1809"></a>
<span id="l1810">            new ObjectStreamField[serialPersistentFields.length];</span><a href="#l1810"></a>
<span id="l1811">        Set&lt;String&gt; fieldNames = new HashSet&lt;&gt;(serialPersistentFields.length);</span><a href="#l1811"></a>
<span id="l1812"></span><a href="#l1812"></a>
<span id="l1813">        for (int i = 0; i &lt; serialPersistentFields.length; i++) {</span><a href="#l1813"></a>
<span id="l1814">            ObjectStreamField spf = serialPersistentFields[i];</span><a href="#l1814"></a>
<span id="l1815"></span><a href="#l1815"></a>
<span id="l1816">            String fname = spf.getName();</span><a href="#l1816"></a>
<span id="l1817">            if (fieldNames.contains(fname)) {</span><a href="#l1817"></a>
<span id="l1818">                throw new InvalidClassException(</span><a href="#l1818"></a>
<span id="l1819">                    &quot;multiple serializable fields named &quot; + fname);</span><a href="#l1819"></a>
<span id="l1820">            }</span><a href="#l1820"></a>
<span id="l1821">            fieldNames.add(fname);</span><a href="#l1821"></a>
<span id="l1822"></span><a href="#l1822"></a>
<span id="l1823">            try {</span><a href="#l1823"></a>
<span id="l1824">                Field f = cl.getDeclaredField(fname);</span><a href="#l1824"></a>
<span id="l1825">                if ((f.getType() == spf.getType()) &amp;&amp;</span><a href="#l1825"></a>
<span id="l1826">                    ((f.getModifiers() &amp; Modifier.STATIC) == 0))</span><a href="#l1826"></a>
<span id="l1827">                {</span><a href="#l1827"></a>
<span id="l1828">                    boundFields[i] =</span><a href="#l1828"></a>
<span id="l1829">                        new ObjectStreamField(f, spf.isUnshared(), true);</span><a href="#l1829"></a>
<span id="l1830">                }</span><a href="#l1830"></a>
<span id="l1831">            } catch (NoSuchFieldException ex) {</span><a href="#l1831"></a>
<span id="l1832">            }</span><a href="#l1832"></a>
<span id="l1833">            if (boundFields[i] == null) {</span><a href="#l1833"></a>
<span id="l1834">                boundFields[i] = new ObjectStreamField(</span><a href="#l1834"></a>
<span id="l1835">                    fname, spf.getType(), spf.isUnshared());</span><a href="#l1835"></a>
<span id="l1836">            }</span><a href="#l1836"></a>
<span id="l1837">        }</span><a href="#l1837"></a>
<span id="l1838">        return boundFields;</span><a href="#l1838"></a>
<span id="l1839">    }</span><a href="#l1839"></a>
<span id="l1840"></span><a href="#l1840"></a>
<span id="l1841">    /**</span><a href="#l1841"></a>
<span id="l1842">     * Returns array of ObjectStreamFields corresponding to all non-static</span><a href="#l1842"></a>
<span id="l1843">     * non-transient fields declared by given class.  Each ObjectStreamField</span><a href="#l1843"></a>
<span id="l1844">     * contains a Field object for the field it represents.  If no default</span><a href="#l1844"></a>
<span id="l1845">     * serializable fields exist, NO_FIELDS is returned.</span><a href="#l1845"></a>
<span id="l1846">     */</span><a href="#l1846"></a>
<span id="l1847">    private static ObjectStreamField[] getDefaultSerialFields(Class&lt;?&gt; cl) {</span><a href="#l1847"></a>
<span id="l1848">        Field[] clFields = cl.getDeclaredFields();</span><a href="#l1848"></a>
<span id="l1849">        ArrayList&lt;ObjectStreamField&gt; list = new ArrayList&lt;&gt;();</span><a href="#l1849"></a>
<span id="l1850">        int mask = Modifier.STATIC | Modifier.TRANSIENT;</span><a href="#l1850"></a>
<span id="l1851"></span><a href="#l1851"></a>
<span id="l1852">        for (int i = 0; i &lt; clFields.length; i++) {</span><a href="#l1852"></a>
<span id="l1853">            if ((clFields[i].getModifiers() &amp; mask) == 0) {</span><a href="#l1853"></a>
<span id="l1854">                list.add(new ObjectStreamField(clFields[i], false, true));</span><a href="#l1854"></a>
<span id="l1855">            }</span><a href="#l1855"></a>
<span id="l1856">        }</span><a href="#l1856"></a>
<span id="l1857">        int size = list.size();</span><a href="#l1857"></a>
<span id="l1858">        return (size == 0) ? NO_FIELDS :</span><a href="#l1858"></a>
<span id="l1859">            list.toArray(new ObjectStreamField[size]);</span><a href="#l1859"></a>
<span id="l1860">    }</span><a href="#l1860"></a>
<span id="l1861"></span><a href="#l1861"></a>
<span id="l1862">    /**</span><a href="#l1862"></a>
<span id="l1863">     * Returns explicit serial version UID value declared by given class, or</span><a href="#l1863"></a>
<span id="l1864">     * null if none.</span><a href="#l1864"></a>
<span id="l1865">     */</span><a href="#l1865"></a>
<span id="l1866">    private static Long getDeclaredSUID(Class&lt;?&gt; cl) {</span><a href="#l1866"></a>
<span id="l1867">        try {</span><a href="#l1867"></a>
<span id="l1868">            Field f = cl.getDeclaredField(&quot;serialVersionUID&quot;);</span><a href="#l1868"></a>
<span id="l1869">            int mask = Modifier.STATIC | Modifier.FINAL;</span><a href="#l1869"></a>
<span id="l1870">            if ((f.getModifiers() &amp; mask) == mask) {</span><a href="#l1870"></a>
<span id="l1871">                f.setAccessible(true);</span><a href="#l1871"></a>
<span id="l1872">                return Long.valueOf(f.getLong(null));</span><a href="#l1872"></a>
<span id="l1873">            }</span><a href="#l1873"></a>
<span id="l1874">        } catch (Exception ex) {</span><a href="#l1874"></a>
<span id="l1875">        }</span><a href="#l1875"></a>
<span id="l1876">        return null;</span><a href="#l1876"></a>
<span id="l1877">    }</span><a href="#l1877"></a>
<span id="l1878"></span><a href="#l1878"></a>
<span id="l1879">    /**</span><a href="#l1879"></a>
<span id="l1880">     * Computes the default serial version UID value for the given class.</span><a href="#l1880"></a>
<span id="l1881">     */</span><a href="#l1881"></a>
<span id="l1882">    private static long computeDefaultSUID(Class&lt;?&gt; cl) {</span><a href="#l1882"></a>
<span id="l1883">        if (!Serializable.class.isAssignableFrom(cl) || Proxy.isProxyClass(cl))</span><a href="#l1883"></a>
<span id="l1884">        {</span><a href="#l1884"></a>
<span id="l1885">            return 0L;</span><a href="#l1885"></a>
<span id="l1886">        }</span><a href="#l1886"></a>
<span id="l1887"></span><a href="#l1887"></a>
<span id="l1888">        try {</span><a href="#l1888"></a>
<span id="l1889">            ByteArrayOutputStream bout = new ByteArrayOutputStream();</span><a href="#l1889"></a>
<span id="l1890">            DataOutputStream dout = new DataOutputStream(bout);</span><a href="#l1890"></a>
<span id="l1891"></span><a href="#l1891"></a>
<span id="l1892">            dout.writeUTF(cl.getName());</span><a href="#l1892"></a>
<span id="l1893"></span><a href="#l1893"></a>
<span id="l1894">            int classMods = cl.getModifiers() &amp;</span><a href="#l1894"></a>
<span id="l1895">                (Modifier.PUBLIC | Modifier.FINAL |</span><a href="#l1895"></a>
<span id="l1896">                 Modifier.INTERFACE | Modifier.ABSTRACT);</span><a href="#l1896"></a>
<span id="l1897"></span><a href="#l1897"></a>
<span id="l1898">            /*</span><a href="#l1898"></a>
<span id="l1899">             * compensate for javac bug in which ABSTRACT bit was set for an</span><a href="#l1899"></a>
<span id="l1900">             * interface only if the interface declared methods</span><a href="#l1900"></a>
<span id="l1901">             */</span><a href="#l1901"></a>
<span id="l1902">            Method[] methods = cl.getDeclaredMethods();</span><a href="#l1902"></a>
<span id="l1903">            if ((classMods &amp; Modifier.INTERFACE) != 0) {</span><a href="#l1903"></a>
<span id="l1904">                classMods = (methods.length &gt; 0) ?</span><a href="#l1904"></a>
<span id="l1905">                    (classMods | Modifier.ABSTRACT) :</span><a href="#l1905"></a>
<span id="l1906">                    (classMods &amp; ~Modifier.ABSTRACT);</span><a href="#l1906"></a>
<span id="l1907">            }</span><a href="#l1907"></a>
<span id="l1908">            dout.writeInt(classMods);</span><a href="#l1908"></a>
<span id="l1909"></span><a href="#l1909"></a>
<span id="l1910">            if (!cl.isArray()) {</span><a href="#l1910"></a>
<span id="l1911">                /*</span><a href="#l1911"></a>
<span id="l1912">                 * compensate for change in 1.2FCS in which</span><a href="#l1912"></a>
<span id="l1913">                 * Class.getInterfaces() was modified to return Cloneable and</span><a href="#l1913"></a>
<span id="l1914">                 * Serializable for array classes.</span><a href="#l1914"></a>
<span id="l1915">                 */</span><a href="#l1915"></a>
<span id="l1916">                Class&lt;?&gt;[] interfaces = cl.getInterfaces();</span><a href="#l1916"></a>
<span id="l1917">                String[] ifaceNames = new String[interfaces.length];</span><a href="#l1917"></a>
<span id="l1918">                for (int i = 0; i &lt; interfaces.length; i++) {</span><a href="#l1918"></a>
<span id="l1919">                    ifaceNames[i] = interfaces[i].getName();</span><a href="#l1919"></a>
<span id="l1920">                }</span><a href="#l1920"></a>
<span id="l1921">                Arrays.sort(ifaceNames);</span><a href="#l1921"></a>
<span id="l1922">                for (int i = 0; i &lt; ifaceNames.length; i++) {</span><a href="#l1922"></a>
<span id="l1923">                    dout.writeUTF(ifaceNames[i]);</span><a href="#l1923"></a>
<span id="l1924">                }</span><a href="#l1924"></a>
<span id="l1925">            }</span><a href="#l1925"></a>
<span id="l1926"></span><a href="#l1926"></a>
<span id="l1927">            Field[] fields = cl.getDeclaredFields();</span><a href="#l1927"></a>
<span id="l1928">            MemberSignature[] fieldSigs = new MemberSignature[fields.length];</span><a href="#l1928"></a>
<span id="l1929">            for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l1929"></a>
<span id="l1930">                fieldSigs[i] = new MemberSignature(fields[i]);</span><a href="#l1930"></a>
<span id="l1931">            }</span><a href="#l1931"></a>
<span id="l1932">            Arrays.sort(fieldSigs, new Comparator&lt;MemberSignature&gt;() {</span><a href="#l1932"></a>
<span id="l1933">                public int compare(MemberSignature ms1, MemberSignature ms2) {</span><a href="#l1933"></a>
<span id="l1934">                    return ms1.name.compareTo(ms2.name);</span><a href="#l1934"></a>
<span id="l1935">                }</span><a href="#l1935"></a>
<span id="l1936">            });</span><a href="#l1936"></a>
<span id="l1937">            for (int i = 0; i &lt; fieldSigs.length; i++) {</span><a href="#l1937"></a>
<span id="l1938">                MemberSignature sig = fieldSigs[i];</span><a href="#l1938"></a>
<span id="l1939">                int mods = sig.member.getModifiers() &amp;</span><a href="#l1939"></a>
<span id="l1940">                    (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED |</span><a href="#l1940"></a>
<span id="l1941">                     Modifier.STATIC | Modifier.FINAL | Modifier.VOLATILE |</span><a href="#l1941"></a>
<span id="l1942">                     Modifier.TRANSIENT);</span><a href="#l1942"></a>
<span id="l1943">                if (((mods &amp; Modifier.PRIVATE) == 0) ||</span><a href="#l1943"></a>
<span id="l1944">                    ((mods &amp; (Modifier.STATIC | Modifier.TRANSIENT)) == 0))</span><a href="#l1944"></a>
<span id="l1945">                {</span><a href="#l1945"></a>
<span id="l1946">                    dout.writeUTF(sig.name);</span><a href="#l1946"></a>
<span id="l1947">                    dout.writeInt(mods);</span><a href="#l1947"></a>
<span id="l1948">                    dout.writeUTF(sig.signature);</span><a href="#l1948"></a>
<span id="l1949">                }</span><a href="#l1949"></a>
<span id="l1950">            }</span><a href="#l1950"></a>
<span id="l1951"></span><a href="#l1951"></a>
<span id="l1952">            if (hasStaticInitializer(cl)) {</span><a href="#l1952"></a>
<span id="l1953">                dout.writeUTF(&quot;&lt;clinit&gt;&quot;);</span><a href="#l1953"></a>
<span id="l1954">                dout.writeInt(Modifier.STATIC);</span><a href="#l1954"></a>
<span id="l1955">                dout.writeUTF(&quot;()V&quot;);</span><a href="#l1955"></a>
<span id="l1956">            }</span><a href="#l1956"></a>
<span id="l1957"></span><a href="#l1957"></a>
<span id="l1958">            Constructor&lt;?&gt;[] cons = cl.getDeclaredConstructors();</span><a href="#l1958"></a>
<span id="l1959">            MemberSignature[] consSigs = new MemberSignature[cons.length];</span><a href="#l1959"></a>
<span id="l1960">            for (int i = 0; i &lt; cons.length; i++) {</span><a href="#l1960"></a>
<span id="l1961">                consSigs[i] = new MemberSignature(cons[i]);</span><a href="#l1961"></a>
<span id="l1962">            }</span><a href="#l1962"></a>
<span id="l1963">            Arrays.sort(consSigs, new Comparator&lt;MemberSignature&gt;() {</span><a href="#l1963"></a>
<span id="l1964">                public int compare(MemberSignature ms1, MemberSignature ms2) {</span><a href="#l1964"></a>
<span id="l1965">                    return ms1.signature.compareTo(ms2.signature);</span><a href="#l1965"></a>
<span id="l1966">                }</span><a href="#l1966"></a>
<span id="l1967">            });</span><a href="#l1967"></a>
<span id="l1968">            for (int i = 0; i &lt; consSigs.length; i++) {</span><a href="#l1968"></a>
<span id="l1969">                MemberSignature sig = consSigs[i];</span><a href="#l1969"></a>
<span id="l1970">                int mods = sig.member.getModifiers() &amp;</span><a href="#l1970"></a>
<span id="l1971">                    (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED |</span><a href="#l1971"></a>
<span id="l1972">                     Modifier.STATIC | Modifier.FINAL |</span><a href="#l1972"></a>
<span id="l1973">                     Modifier.SYNCHRONIZED | Modifier.NATIVE |</span><a href="#l1973"></a>
<span id="l1974">                     Modifier.ABSTRACT | Modifier.STRICT);</span><a href="#l1974"></a>
<span id="l1975">                if ((mods &amp; Modifier.PRIVATE) == 0) {</span><a href="#l1975"></a>
<span id="l1976">                    dout.writeUTF(&quot;&lt;init&gt;&quot;);</span><a href="#l1976"></a>
<span id="l1977">                    dout.writeInt(mods);</span><a href="#l1977"></a>
<span id="l1978">                    dout.writeUTF(sig.signature.replace('/', '.'));</span><a href="#l1978"></a>
<span id="l1979">                }</span><a href="#l1979"></a>
<span id="l1980">            }</span><a href="#l1980"></a>
<span id="l1981"></span><a href="#l1981"></a>
<span id="l1982">            MemberSignature[] methSigs = new MemberSignature[methods.length];</span><a href="#l1982"></a>
<span id="l1983">            for (int i = 0; i &lt; methods.length; i++) {</span><a href="#l1983"></a>
<span id="l1984">                methSigs[i] = new MemberSignature(methods[i]);</span><a href="#l1984"></a>
<span id="l1985">            }</span><a href="#l1985"></a>
<span id="l1986">            Arrays.sort(methSigs, new Comparator&lt;MemberSignature&gt;() {</span><a href="#l1986"></a>
<span id="l1987">                public int compare(MemberSignature ms1, MemberSignature ms2) {</span><a href="#l1987"></a>
<span id="l1988">                    int comp = ms1.name.compareTo(ms2.name);</span><a href="#l1988"></a>
<span id="l1989">                    if (comp == 0) {</span><a href="#l1989"></a>
<span id="l1990">                        comp = ms1.signature.compareTo(ms2.signature);</span><a href="#l1990"></a>
<span id="l1991">                    }</span><a href="#l1991"></a>
<span id="l1992">                    return comp;</span><a href="#l1992"></a>
<span id="l1993">                }</span><a href="#l1993"></a>
<span id="l1994">            });</span><a href="#l1994"></a>
<span id="l1995">            for (int i = 0; i &lt; methSigs.length; i++) {</span><a href="#l1995"></a>
<span id="l1996">                MemberSignature sig = methSigs[i];</span><a href="#l1996"></a>
<span id="l1997">                int mods = sig.member.getModifiers() &amp;</span><a href="#l1997"></a>
<span id="l1998">                    (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED |</span><a href="#l1998"></a>
<span id="l1999">                     Modifier.STATIC | Modifier.FINAL |</span><a href="#l1999"></a>
<span id="l2000">                     Modifier.SYNCHRONIZED | Modifier.NATIVE |</span><a href="#l2000"></a>
<span id="l2001">                     Modifier.ABSTRACT | Modifier.STRICT);</span><a href="#l2001"></a>
<span id="l2002">                if ((mods &amp; Modifier.PRIVATE) == 0) {</span><a href="#l2002"></a>
<span id="l2003">                    dout.writeUTF(sig.name);</span><a href="#l2003"></a>
<span id="l2004">                    dout.writeInt(mods);</span><a href="#l2004"></a>
<span id="l2005">                    dout.writeUTF(sig.signature.replace('/', '.'));</span><a href="#l2005"></a>
<span id="l2006">                }</span><a href="#l2006"></a>
<span id="l2007">            }</span><a href="#l2007"></a>
<span id="l2008"></span><a href="#l2008"></a>
<span id="l2009">            dout.flush();</span><a href="#l2009"></a>
<span id="l2010"></span><a href="#l2010"></a>
<span id="l2011">            MessageDigest md = MessageDigest.getInstance(&quot;SHA&quot;);</span><a href="#l2011"></a>
<span id="l2012">            byte[] hashBytes = md.digest(bout.toByteArray());</span><a href="#l2012"></a>
<span id="l2013">            long hash = 0;</span><a href="#l2013"></a>
<span id="l2014">            for (int i = Math.min(hashBytes.length, 8) - 1; i &gt;= 0; i--) {</span><a href="#l2014"></a>
<span id="l2015">                hash = (hash &lt;&lt; 8) | (hashBytes[i] &amp; 0xFF);</span><a href="#l2015"></a>
<span id="l2016">            }</span><a href="#l2016"></a>
<span id="l2017">            return hash;</span><a href="#l2017"></a>
<span id="l2018">        } catch (IOException ex) {</span><a href="#l2018"></a>
<span id="l2019">            throw new InternalError(ex);</span><a href="#l2019"></a>
<span id="l2020">        } catch (NoSuchAlgorithmException ex) {</span><a href="#l2020"></a>
<span id="l2021">            throw new SecurityException(ex.getMessage());</span><a href="#l2021"></a>
<span id="l2022">        }</span><a href="#l2022"></a>
<span id="l2023">    }</span><a href="#l2023"></a>
<span id="l2024"></span><a href="#l2024"></a>
<span id="l2025">    /**</span><a href="#l2025"></a>
<span id="l2026">     * Returns true if the given class defines a static initializer method,</span><a href="#l2026"></a>
<span id="l2027">     * false otherwise.</span><a href="#l2027"></a>
<span id="l2028">     */</span><a href="#l2028"></a>
<span id="l2029">    private native static boolean hasStaticInitializer(Class&lt;?&gt; cl);</span><a href="#l2029"></a>
<span id="l2030"></span><a href="#l2030"></a>
<span id="l2031">    /**</span><a href="#l2031"></a>
<span id="l2032">     * Class for computing and caching field/constructor/method signatures</span><a href="#l2032"></a>
<span id="l2033">     * during serialVersionUID calculation.</span><a href="#l2033"></a>
<span id="l2034">     */</span><a href="#l2034"></a>
<span id="l2035">    private static class MemberSignature {</span><a href="#l2035"></a>
<span id="l2036"></span><a href="#l2036"></a>
<span id="l2037">        public final Member member;</span><a href="#l2037"></a>
<span id="l2038">        public final String name;</span><a href="#l2038"></a>
<span id="l2039">        public final String signature;</span><a href="#l2039"></a>
<span id="l2040"></span><a href="#l2040"></a>
<span id="l2041">        public MemberSignature(Field field) {</span><a href="#l2041"></a>
<span id="l2042">            member = field;</span><a href="#l2042"></a>
<span id="l2043">            name = field.getName();</span><a href="#l2043"></a>
<span id="l2044">            signature = getClassSignature(field.getType());</span><a href="#l2044"></a>
<span id="l2045">        }</span><a href="#l2045"></a>
<span id="l2046"></span><a href="#l2046"></a>
<span id="l2047">        public MemberSignature(Constructor&lt;?&gt; cons) {</span><a href="#l2047"></a>
<span id="l2048">            member = cons;</span><a href="#l2048"></a>
<span id="l2049">            name = cons.getName();</span><a href="#l2049"></a>
<span id="l2050">            signature = getMethodSignature(</span><a href="#l2050"></a>
<span id="l2051">                cons.getParameterTypes(), Void.TYPE);</span><a href="#l2051"></a>
<span id="l2052">        }</span><a href="#l2052"></a>
<span id="l2053"></span><a href="#l2053"></a>
<span id="l2054">        public MemberSignature(Method meth) {</span><a href="#l2054"></a>
<span id="l2055">            member = meth;</span><a href="#l2055"></a>
<span id="l2056">            name = meth.getName();</span><a href="#l2056"></a>
<span id="l2057">            signature = getMethodSignature(</span><a href="#l2057"></a>
<span id="l2058">                meth.getParameterTypes(), meth.getReturnType());</span><a href="#l2058"></a>
<span id="l2059">        }</span><a href="#l2059"></a>
<span id="l2060">    }</span><a href="#l2060"></a>
<span id="l2061"></span><a href="#l2061"></a>
<span id="l2062">    /**</span><a href="#l2062"></a>
<span id="l2063">     * Class for setting and retrieving serializable field values in batch.</span><a href="#l2063"></a>
<span id="l2064">     */</span><a href="#l2064"></a>
<span id="l2065">    // REMIND: dynamically generate these?</span><a href="#l2065"></a>
<span id="l2066">    private static class FieldReflector {</span><a href="#l2066"></a>
<span id="l2067"></span><a href="#l2067"></a>
<span id="l2068">        /** handle for performing unsafe operations */</span><a href="#l2068"></a>
<span id="l2069">        private static final Unsafe unsafe = Unsafe.getUnsafe();</span><a href="#l2069"></a>
<span id="l2070"></span><a href="#l2070"></a>
<span id="l2071">        /** fields to operate on */</span><a href="#l2071"></a>
<span id="l2072">        private final ObjectStreamField[] fields;</span><a href="#l2072"></a>
<span id="l2073">        /** number of primitive fields */</span><a href="#l2073"></a>
<span id="l2074">        private final int numPrimFields;</span><a href="#l2074"></a>
<span id="l2075">        /** unsafe field keys for reading fields - may contain dupes */</span><a href="#l2075"></a>
<span id="l2076">        private final long[] readKeys;</span><a href="#l2076"></a>
<span id="l2077">        /** unsafe fields keys for writing fields - no dupes */</span><a href="#l2077"></a>
<span id="l2078">        private final long[] writeKeys;</span><a href="#l2078"></a>
<span id="l2079">        /** field data offsets */</span><a href="#l2079"></a>
<span id="l2080">        private final int[] offsets;</span><a href="#l2080"></a>
<span id="l2081">        /** field type codes */</span><a href="#l2081"></a>
<span id="l2082">        private final char[] typeCodes;</span><a href="#l2082"></a>
<span id="l2083">        /** field types */</span><a href="#l2083"></a>
<span id="l2084">        private final Class&lt;?&gt;[] types;</span><a href="#l2084"></a>
<span id="l2085"></span><a href="#l2085"></a>
<span id="l2086">        /**</span><a href="#l2086"></a>
<span id="l2087">         * Constructs FieldReflector capable of setting/getting values from the</span><a href="#l2087"></a>
<span id="l2088">         * subset of fields whose ObjectStreamFields contain non-null</span><a href="#l2088"></a>
<span id="l2089">         * reflective Field objects.  ObjectStreamFields with null Fields are</span><a href="#l2089"></a>
<span id="l2090">         * treated as filler, for which get operations return default values</span><a href="#l2090"></a>
<span id="l2091">         * and set operations discard given values.</span><a href="#l2091"></a>
<span id="l2092">         */</span><a href="#l2092"></a>
<span id="l2093">        FieldReflector(ObjectStreamField[] fields) {</span><a href="#l2093"></a>
<span id="l2094">            this.fields = fields;</span><a href="#l2094"></a>
<span id="l2095">            int nfields = fields.length;</span><a href="#l2095"></a>
<span id="l2096">            readKeys = new long[nfields];</span><a href="#l2096"></a>
<span id="l2097">            writeKeys = new long[nfields];</span><a href="#l2097"></a>
<span id="l2098">            offsets = new int[nfields];</span><a href="#l2098"></a>
<span id="l2099">            typeCodes = new char[nfields];</span><a href="#l2099"></a>
<span id="l2100">            ArrayList&lt;Class&lt;?&gt;&gt; typeList = new ArrayList&lt;&gt;();</span><a href="#l2100"></a>
<span id="l2101">            Set&lt;Long&gt; usedKeys = new HashSet&lt;&gt;();</span><a href="#l2101"></a>
<span id="l2102"></span><a href="#l2102"></a>
<span id="l2103"></span><a href="#l2103"></a>
<span id="l2104">            for (int i = 0; i &lt; nfields; i++) {</span><a href="#l2104"></a>
<span id="l2105">                ObjectStreamField f = fields[i];</span><a href="#l2105"></a>
<span id="l2106">                Field rf = f.getField();</span><a href="#l2106"></a>
<span id="l2107">                long key = (rf != null) ?</span><a href="#l2107"></a>
<span id="l2108">                    unsafe.objectFieldOffset(rf) : Unsafe.INVALID_FIELD_OFFSET;</span><a href="#l2108"></a>
<span id="l2109">                readKeys[i] = key;</span><a href="#l2109"></a>
<span id="l2110">                writeKeys[i] = usedKeys.add(key) ?</span><a href="#l2110"></a>
<span id="l2111">                    key : Unsafe.INVALID_FIELD_OFFSET;</span><a href="#l2111"></a>
<span id="l2112">                offsets[i] = f.getOffset();</span><a href="#l2112"></a>
<span id="l2113">                typeCodes[i] = f.getTypeCode();</span><a href="#l2113"></a>
<span id="l2114">                if (!f.isPrimitive()) {</span><a href="#l2114"></a>
<span id="l2115">                    typeList.add((rf != null) ? rf.getType() : null);</span><a href="#l2115"></a>
<span id="l2116">                }</span><a href="#l2116"></a>
<span id="l2117">            }</span><a href="#l2117"></a>
<span id="l2118"></span><a href="#l2118"></a>
<span id="l2119">            types = typeList.toArray(new Class&lt;?&gt;[typeList.size()]);</span><a href="#l2119"></a>
<span id="l2120">            numPrimFields = nfields - types.length;</span><a href="#l2120"></a>
<span id="l2121">        }</span><a href="#l2121"></a>
<span id="l2122"></span><a href="#l2122"></a>
<span id="l2123">        /**</span><a href="#l2123"></a>
<span id="l2124">         * Returns list of ObjectStreamFields representing fields operated on</span><a href="#l2124"></a>
<span id="l2125">         * by this reflector.  The shared/unshared values and Field objects</span><a href="#l2125"></a>
<span id="l2126">         * contained by ObjectStreamFields in the list reflect their bindings</span><a href="#l2126"></a>
<span id="l2127">         * to locally defined serializable fields.</span><a href="#l2127"></a>
<span id="l2128">         */</span><a href="#l2128"></a>
<span id="l2129">        ObjectStreamField[] getFields() {</span><a href="#l2129"></a>
<span id="l2130">            return fields;</span><a href="#l2130"></a>
<span id="l2131">        }</span><a href="#l2131"></a>
<span id="l2132"></span><a href="#l2132"></a>
<span id="l2133">        /**</span><a href="#l2133"></a>
<span id="l2134">         * Fetches the serializable primitive field values of object obj and</span><a href="#l2134"></a>
<span id="l2135">         * marshals them into byte array buf starting at offset 0.  The caller</span><a href="#l2135"></a>
<span id="l2136">         * is responsible for ensuring that obj is of the proper type.</span><a href="#l2136"></a>
<span id="l2137">         */</span><a href="#l2137"></a>
<span id="l2138">        void getPrimFieldValues(Object obj, byte[] buf) {</span><a href="#l2138"></a>
<span id="l2139">            if (obj == null) {</span><a href="#l2139"></a>
<span id="l2140">                throw new NullPointerException();</span><a href="#l2140"></a>
<span id="l2141">            }</span><a href="#l2141"></a>
<span id="l2142">            /* assuming checkDefaultSerialize() has been called on the class</span><a href="#l2142"></a>
<span id="l2143">             * descriptor this FieldReflector was obtained from, no field keys</span><a href="#l2143"></a>
<span id="l2144">             * in array should be equal to Unsafe.INVALID_FIELD_OFFSET.</span><a href="#l2144"></a>
<span id="l2145">             */</span><a href="#l2145"></a>
<span id="l2146">            for (int i = 0; i &lt; numPrimFields; i++) {</span><a href="#l2146"></a>
<span id="l2147">                long key = readKeys[i];</span><a href="#l2147"></a>
<span id="l2148">                int off = offsets[i];</span><a href="#l2148"></a>
<span id="l2149">                switch (typeCodes[i]) {</span><a href="#l2149"></a>
<span id="l2150">                    case 'Z':</span><a href="#l2150"></a>
<span id="l2151">                        Bits.putBoolean(buf, off, unsafe.getBoolean(obj, key));</span><a href="#l2151"></a>
<span id="l2152">                        break;</span><a href="#l2152"></a>
<span id="l2153"></span><a href="#l2153"></a>
<span id="l2154">                    case 'B':</span><a href="#l2154"></a>
<span id="l2155">                        buf[off] = unsafe.getByte(obj, key);</span><a href="#l2155"></a>
<span id="l2156">                        break;</span><a href="#l2156"></a>
<span id="l2157"></span><a href="#l2157"></a>
<span id="l2158">                    case 'C':</span><a href="#l2158"></a>
<span id="l2159">                        Bits.putChar(buf, off, unsafe.getChar(obj, key));</span><a href="#l2159"></a>
<span id="l2160">                        break;</span><a href="#l2160"></a>
<span id="l2161"></span><a href="#l2161"></a>
<span id="l2162">                    case 'S':</span><a href="#l2162"></a>
<span id="l2163">                        Bits.putShort(buf, off, unsafe.getShort(obj, key));</span><a href="#l2163"></a>
<span id="l2164">                        break;</span><a href="#l2164"></a>
<span id="l2165"></span><a href="#l2165"></a>
<span id="l2166">                    case 'I':</span><a href="#l2166"></a>
<span id="l2167">                        Bits.putInt(buf, off, unsafe.getInt(obj, key));</span><a href="#l2167"></a>
<span id="l2168">                        break;</span><a href="#l2168"></a>
<span id="l2169"></span><a href="#l2169"></a>
<span id="l2170">                    case 'F':</span><a href="#l2170"></a>
<span id="l2171">                        Bits.putFloat(buf, off, unsafe.getFloat(obj, key));</span><a href="#l2171"></a>
<span id="l2172">                        break;</span><a href="#l2172"></a>
<span id="l2173"></span><a href="#l2173"></a>
<span id="l2174">                    case 'J':</span><a href="#l2174"></a>
<span id="l2175">                        Bits.putLong(buf, off, unsafe.getLong(obj, key));</span><a href="#l2175"></a>
<span id="l2176">                        break;</span><a href="#l2176"></a>
<span id="l2177"></span><a href="#l2177"></a>
<span id="l2178">                    case 'D':</span><a href="#l2178"></a>
<span id="l2179">                        Bits.putDouble(buf, off, unsafe.getDouble(obj, key));</span><a href="#l2179"></a>
<span id="l2180">                        break;</span><a href="#l2180"></a>
<span id="l2181"></span><a href="#l2181"></a>
<span id="l2182">                    default:</span><a href="#l2182"></a>
<span id="l2183">                        throw new InternalError();</span><a href="#l2183"></a>
<span id="l2184">                }</span><a href="#l2184"></a>
<span id="l2185">            }</span><a href="#l2185"></a>
<span id="l2186">        }</span><a href="#l2186"></a>
<span id="l2187"></span><a href="#l2187"></a>
<span id="l2188">        /**</span><a href="#l2188"></a>
<span id="l2189">         * Sets the serializable primitive fields of object obj using values</span><a href="#l2189"></a>
<span id="l2190">         * unmarshalled from byte array buf starting at offset 0.  The caller</span><a href="#l2190"></a>
<span id="l2191">         * is responsible for ensuring that obj is of the proper type.</span><a href="#l2191"></a>
<span id="l2192">         */</span><a href="#l2192"></a>
<span id="l2193">        void setPrimFieldValues(Object obj, byte[] buf) {</span><a href="#l2193"></a>
<span id="l2194">            if (obj == null) {</span><a href="#l2194"></a>
<span id="l2195">                throw new NullPointerException();</span><a href="#l2195"></a>
<span id="l2196">            }</span><a href="#l2196"></a>
<span id="l2197">            for (int i = 0; i &lt; numPrimFields; i++) {</span><a href="#l2197"></a>
<span id="l2198">                long key = writeKeys[i];</span><a href="#l2198"></a>
<span id="l2199">                if (key == Unsafe.INVALID_FIELD_OFFSET) {</span><a href="#l2199"></a>
<span id="l2200">                    continue;           // discard value</span><a href="#l2200"></a>
<span id="l2201">                }</span><a href="#l2201"></a>
<span id="l2202">                int off = offsets[i];</span><a href="#l2202"></a>
<span id="l2203">                switch (typeCodes[i]) {</span><a href="#l2203"></a>
<span id="l2204">                    case 'Z':</span><a href="#l2204"></a>
<span id="l2205">                        unsafe.putBoolean(obj, key, Bits.getBoolean(buf, off));</span><a href="#l2205"></a>
<span id="l2206">                        break;</span><a href="#l2206"></a>
<span id="l2207"></span><a href="#l2207"></a>
<span id="l2208">                    case 'B':</span><a href="#l2208"></a>
<span id="l2209">                        unsafe.putByte(obj, key, buf[off]);</span><a href="#l2209"></a>
<span id="l2210">                        break;</span><a href="#l2210"></a>
<span id="l2211"></span><a href="#l2211"></a>
<span id="l2212">                    case 'C':</span><a href="#l2212"></a>
<span id="l2213">                        unsafe.putChar(obj, key, Bits.getChar(buf, off));</span><a href="#l2213"></a>
<span id="l2214">                        break;</span><a href="#l2214"></a>
<span id="l2215"></span><a href="#l2215"></a>
<span id="l2216">                    case 'S':</span><a href="#l2216"></a>
<span id="l2217">                        unsafe.putShort(obj, key, Bits.getShort(buf, off));</span><a href="#l2217"></a>
<span id="l2218">                        break;</span><a href="#l2218"></a>
<span id="l2219"></span><a href="#l2219"></a>
<span id="l2220">                    case 'I':</span><a href="#l2220"></a>
<span id="l2221">                        unsafe.putInt(obj, key, Bits.getInt(buf, off));</span><a href="#l2221"></a>
<span id="l2222">                        break;</span><a href="#l2222"></a>
<span id="l2223"></span><a href="#l2223"></a>
<span id="l2224">                    case 'F':</span><a href="#l2224"></a>
<span id="l2225">                        unsafe.putFloat(obj, key, Bits.getFloat(buf, off));</span><a href="#l2225"></a>
<span id="l2226">                        break;</span><a href="#l2226"></a>
<span id="l2227"></span><a href="#l2227"></a>
<span id="l2228">                    case 'J':</span><a href="#l2228"></a>
<span id="l2229">                        unsafe.putLong(obj, key, Bits.getLong(buf, off));</span><a href="#l2229"></a>
<span id="l2230">                        break;</span><a href="#l2230"></a>
<span id="l2231"></span><a href="#l2231"></a>
<span id="l2232">                    case 'D':</span><a href="#l2232"></a>
<span id="l2233">                        unsafe.putDouble(obj, key, Bits.getDouble(buf, off));</span><a href="#l2233"></a>
<span id="l2234">                        break;</span><a href="#l2234"></a>
<span id="l2235"></span><a href="#l2235"></a>
<span id="l2236">                    default:</span><a href="#l2236"></a>
<span id="l2237">                        throw new InternalError();</span><a href="#l2237"></a>
<span id="l2238">                }</span><a href="#l2238"></a>
<span id="l2239">            }</span><a href="#l2239"></a>
<span id="l2240">        }</span><a href="#l2240"></a>
<span id="l2241"></span><a href="#l2241"></a>
<span id="l2242">        /**</span><a href="#l2242"></a>
<span id="l2243">         * Fetches the serializable object field values of object obj and</span><a href="#l2243"></a>
<span id="l2244">         * stores them in array vals starting at offset 0.  The caller is</span><a href="#l2244"></a>
<span id="l2245">         * responsible for ensuring that obj is of the proper type.</span><a href="#l2245"></a>
<span id="l2246">         */</span><a href="#l2246"></a>
<span id="l2247">        void getObjFieldValues(Object obj, Object[] vals) {</span><a href="#l2247"></a>
<span id="l2248">            if (obj == null) {</span><a href="#l2248"></a>
<span id="l2249">                throw new NullPointerException();</span><a href="#l2249"></a>
<span id="l2250">            }</span><a href="#l2250"></a>
<span id="l2251">            /* assuming checkDefaultSerialize() has been called on the class</span><a href="#l2251"></a>
<span id="l2252">             * descriptor this FieldReflector was obtained from, no field keys</span><a href="#l2252"></a>
<span id="l2253">             * in array should be equal to Unsafe.INVALID_FIELD_OFFSET.</span><a href="#l2253"></a>
<span id="l2254">             */</span><a href="#l2254"></a>
<span id="l2255">            for (int i = numPrimFields; i &lt; fields.length; i++) {</span><a href="#l2255"></a>
<span id="l2256">                switch (typeCodes[i]) {</span><a href="#l2256"></a>
<span id="l2257">                    case 'L':</span><a href="#l2257"></a>
<span id="l2258">                    case '[':</span><a href="#l2258"></a>
<span id="l2259">                        vals[offsets[i]] = unsafe.getObject(obj, readKeys[i]);</span><a href="#l2259"></a>
<span id="l2260">                        break;</span><a href="#l2260"></a>
<span id="l2261"></span><a href="#l2261"></a>
<span id="l2262">                    default:</span><a href="#l2262"></a>
<span id="l2263">                        throw new InternalError();</span><a href="#l2263"></a>
<span id="l2264">                }</span><a href="#l2264"></a>
<span id="l2265">            }</span><a href="#l2265"></a>
<span id="l2266">        }</span><a href="#l2266"></a>
<span id="l2267"></span><a href="#l2267"></a>
<span id="l2268">        /**</span><a href="#l2268"></a>
<span id="l2269">         * Sets the serializable object fields of object obj using values from</span><a href="#l2269"></a>
<span id="l2270">         * array vals starting at offset 0.  The caller is responsible for</span><a href="#l2270"></a>
<span id="l2271">         * ensuring that obj is of the proper type; however, attempts to set a</span><a href="#l2271"></a>
<span id="l2272">         * field with a value of the wrong type will trigger an appropriate</span><a href="#l2272"></a>
<span id="l2273">         * ClassCastException.</span><a href="#l2273"></a>
<span id="l2274">         */</span><a href="#l2274"></a>
<span id="l2275">        void setObjFieldValues(Object obj, Object[] vals) {</span><a href="#l2275"></a>
<span id="l2276">            if (obj == null) {</span><a href="#l2276"></a>
<span id="l2277">                throw new NullPointerException();</span><a href="#l2277"></a>
<span id="l2278">            }</span><a href="#l2278"></a>
<span id="l2279">            for (int i = numPrimFields; i &lt; fields.length; i++) {</span><a href="#l2279"></a>
<span id="l2280">                long key = writeKeys[i];</span><a href="#l2280"></a>
<span id="l2281">                if (key == Unsafe.INVALID_FIELD_OFFSET) {</span><a href="#l2281"></a>
<span id="l2282">                    continue;           // discard value</span><a href="#l2282"></a>
<span id="l2283">                }</span><a href="#l2283"></a>
<span id="l2284">                switch (typeCodes[i]) {</span><a href="#l2284"></a>
<span id="l2285">                    case 'L':</span><a href="#l2285"></a>
<span id="l2286">                    case '[':</span><a href="#l2286"></a>
<span id="l2287">                        Object val = vals[offsets[i]];</span><a href="#l2287"></a>
<span id="l2288">                        if (val != null &amp;&amp;</span><a href="#l2288"></a>
<span id="l2289">                            !types[i - numPrimFields].isInstance(val))</span><a href="#l2289"></a>
<span id="l2290">                        {</span><a href="#l2290"></a>
<span id="l2291">                            Field f = fields[i].getField();</span><a href="#l2291"></a>
<span id="l2292">                            throw new ClassCastException(</span><a href="#l2292"></a>
<span id="l2293">                                &quot;cannot assign instance of &quot; +</span><a href="#l2293"></a>
<span id="l2294">                                val.getClass().getName() + &quot; to field &quot; +</span><a href="#l2294"></a>
<span id="l2295">                                f.getDeclaringClass().getName() + &quot;.&quot; +</span><a href="#l2295"></a>
<span id="l2296">                                f.getName() + &quot; of type &quot; +</span><a href="#l2296"></a>
<span id="l2297">                                f.getType().getName() + &quot; in instance of &quot; +</span><a href="#l2297"></a>
<span id="l2298">                                obj.getClass().getName());</span><a href="#l2298"></a>
<span id="l2299">                        }</span><a href="#l2299"></a>
<span id="l2300">                        unsafe.putObject(obj, key, val);</span><a href="#l2300"></a>
<span id="l2301">                        break;</span><a href="#l2301"></a>
<span id="l2302"></span><a href="#l2302"></a>
<span id="l2303">                    default:</span><a href="#l2303"></a>
<span id="l2304">                        throw new InternalError();</span><a href="#l2304"></a>
<span id="l2305">                }</span><a href="#l2305"></a>
<span id="l2306">            }</span><a href="#l2306"></a>
<span id="l2307">        }</span><a href="#l2307"></a>
<span id="l2308">    }</span><a href="#l2308"></a>
<span id="l2309"></span><a href="#l2309"></a>
<span id="l2310">    /**</span><a href="#l2310"></a>
<span id="l2311">     * Matches given set of serializable fields with serializable fields</span><a href="#l2311"></a>
<span id="l2312">     * described by the given local class descriptor, and returns a</span><a href="#l2312"></a>
<span id="l2313">     * FieldReflector instance capable of setting/getting values from the</span><a href="#l2313"></a>
<span id="l2314">     * subset of fields that match (non-matching fields are treated as filler,</span><a href="#l2314"></a>
<span id="l2315">     * for which get operations return default values and set operations</span><a href="#l2315"></a>
<span id="l2316">     * discard given values).  Throws InvalidClassException if unresolvable</span><a href="#l2316"></a>
<span id="l2317">     * type conflicts exist between the two sets of fields.</span><a href="#l2317"></a>
<span id="l2318">     */</span><a href="#l2318"></a>
<span id="l2319">    private static FieldReflector getReflector(ObjectStreamField[] fields,</span><a href="#l2319"></a>
<span id="l2320">                                               ObjectStreamClass localDesc)</span><a href="#l2320"></a>
<span id="l2321">        throws InvalidClassException</span><a href="#l2321"></a>
<span id="l2322">    {</span><a href="#l2322"></a>
<span id="l2323">        // class irrelevant if no fields</span><a href="#l2323"></a>
<span id="l2324">        Class&lt;?&gt; cl = (localDesc != null &amp;&amp; fields.length &gt; 0) ?</span><a href="#l2324"></a>
<span id="l2325">            localDesc.cl : null;</span><a href="#l2325"></a>
<span id="l2326">        processQueue(Caches.reflectorsQueue, Caches.reflectors);</span><a href="#l2326"></a>
<span id="l2327">        FieldReflectorKey key = new FieldReflectorKey(cl, fields,</span><a href="#l2327"></a>
<span id="l2328">                                                      Caches.reflectorsQueue);</span><a href="#l2328"></a>
<span id="l2329">        Reference&lt;?&gt; ref = Caches.reflectors.get(key);</span><a href="#l2329"></a>
<span id="l2330">        Object entry = null;</span><a href="#l2330"></a>
<span id="l2331">        if (ref != null) {</span><a href="#l2331"></a>
<span id="l2332">            entry = ref.get();</span><a href="#l2332"></a>
<span id="l2333">        }</span><a href="#l2333"></a>
<span id="l2334">        EntryFuture future = null;</span><a href="#l2334"></a>
<span id="l2335">        if (entry == null) {</span><a href="#l2335"></a>
<span id="l2336">            EntryFuture newEntry = new EntryFuture();</span><a href="#l2336"></a>
<span id="l2337">            Reference&lt;?&gt; newRef = new SoftReference&lt;&gt;(newEntry);</span><a href="#l2337"></a>
<span id="l2338">            do {</span><a href="#l2338"></a>
<span id="l2339">                if (ref != null) {</span><a href="#l2339"></a>
<span id="l2340">                    Caches.reflectors.remove(key, ref);</span><a href="#l2340"></a>
<span id="l2341">                }</span><a href="#l2341"></a>
<span id="l2342">                ref = Caches.reflectors.putIfAbsent(key, newRef);</span><a href="#l2342"></a>
<span id="l2343">                if (ref != null) {</span><a href="#l2343"></a>
<span id="l2344">                    entry = ref.get();</span><a href="#l2344"></a>
<span id="l2345">                }</span><a href="#l2345"></a>
<span id="l2346">            } while (ref != null &amp;&amp; entry == null);</span><a href="#l2346"></a>
<span id="l2347">            if (entry == null) {</span><a href="#l2347"></a>
<span id="l2348">                future = newEntry;</span><a href="#l2348"></a>
<span id="l2349">            }</span><a href="#l2349"></a>
<span id="l2350">        }</span><a href="#l2350"></a>
<span id="l2351"></span><a href="#l2351"></a>
<span id="l2352">        if (entry instanceof FieldReflector) {  // check common case first</span><a href="#l2352"></a>
<span id="l2353">            return (FieldReflector) entry;</span><a href="#l2353"></a>
<span id="l2354">        } else if (entry instanceof EntryFuture) {</span><a href="#l2354"></a>
<span id="l2355">            entry = ((EntryFuture) entry).get();</span><a href="#l2355"></a>
<span id="l2356">        } else if (entry == null) {</span><a href="#l2356"></a>
<span id="l2357">            try {</span><a href="#l2357"></a>
<span id="l2358">                entry = new FieldReflector(matchFields(fields, localDesc));</span><a href="#l2358"></a>
<span id="l2359">            } catch (Throwable th) {</span><a href="#l2359"></a>
<span id="l2360">                entry = th;</span><a href="#l2360"></a>
<span id="l2361">            }</span><a href="#l2361"></a>
<span id="l2362">            future.set(entry);</span><a href="#l2362"></a>
<span id="l2363">            Caches.reflectors.put(key, new SoftReference&lt;Object&gt;(entry));</span><a href="#l2363"></a>
<span id="l2364">        }</span><a href="#l2364"></a>
<span id="l2365"></span><a href="#l2365"></a>
<span id="l2366">        if (entry instanceof FieldReflector) {</span><a href="#l2366"></a>
<span id="l2367">            return (FieldReflector) entry;</span><a href="#l2367"></a>
<span id="l2368">        } else if (entry instanceof InvalidClassException) {</span><a href="#l2368"></a>
<span id="l2369">            throw (InvalidClassException) entry;</span><a href="#l2369"></a>
<span id="l2370">        } else if (entry instanceof RuntimeException) {</span><a href="#l2370"></a>
<span id="l2371">            throw (RuntimeException) entry;</span><a href="#l2371"></a>
<span id="l2372">        } else if (entry instanceof Error) {</span><a href="#l2372"></a>
<span id="l2373">            throw (Error) entry;</span><a href="#l2373"></a>
<span id="l2374">        } else {</span><a href="#l2374"></a>
<span id="l2375">            throw new InternalError(&quot;unexpected entry: &quot; + entry);</span><a href="#l2375"></a>
<span id="l2376">        }</span><a href="#l2376"></a>
<span id="l2377">    }</span><a href="#l2377"></a>
<span id="l2378"></span><a href="#l2378"></a>
<span id="l2379">    /**</span><a href="#l2379"></a>
<span id="l2380">     * FieldReflector cache lookup key.  Keys are considered equal if they</span><a href="#l2380"></a>
<span id="l2381">     * refer to the same class and equivalent field formats.</span><a href="#l2381"></a>
<span id="l2382">     */</span><a href="#l2382"></a>
<span id="l2383">    private static class FieldReflectorKey extends WeakReference&lt;Class&lt;?&gt;&gt; {</span><a href="#l2383"></a>
<span id="l2384"></span><a href="#l2384"></a>
<span id="l2385">        private final String[] sigs;</span><a href="#l2385"></a>
<span id="l2386">        private final int hash;</span><a href="#l2386"></a>
<span id="l2387">        private final boolean nullClass;</span><a href="#l2387"></a>
<span id="l2388"></span><a href="#l2388"></a>
<span id="l2389">        FieldReflectorKey(Class&lt;?&gt; cl, ObjectStreamField[] fields,</span><a href="#l2389"></a>
<span id="l2390">                          ReferenceQueue&lt;Class&lt;?&gt;&gt; queue)</span><a href="#l2390"></a>
<span id="l2391">        {</span><a href="#l2391"></a>
<span id="l2392">            super(cl, queue);</span><a href="#l2392"></a>
<span id="l2393">            nullClass = (cl == null);</span><a href="#l2393"></a>
<span id="l2394">            sigs = new String[2 * fields.length];</span><a href="#l2394"></a>
<span id="l2395">            for (int i = 0, j = 0; i &lt; fields.length; i++) {</span><a href="#l2395"></a>
<span id="l2396">                ObjectStreamField f = fields[i];</span><a href="#l2396"></a>
<span id="l2397">                sigs[j++] = f.getName();</span><a href="#l2397"></a>
<span id="l2398">                sigs[j++] = f.getSignature();</span><a href="#l2398"></a>
<span id="l2399">            }</span><a href="#l2399"></a>
<span id="l2400">            hash = System.identityHashCode(cl) + Arrays.hashCode(sigs);</span><a href="#l2400"></a>
<span id="l2401">        }</span><a href="#l2401"></a>
<span id="l2402"></span><a href="#l2402"></a>
<span id="l2403">        public int hashCode() {</span><a href="#l2403"></a>
<span id="l2404">            return hash;</span><a href="#l2404"></a>
<span id="l2405">        }</span><a href="#l2405"></a>
<span id="l2406"></span><a href="#l2406"></a>
<span id="l2407">        public boolean equals(Object obj) {</span><a href="#l2407"></a>
<span id="l2408">            if (obj == this) {</span><a href="#l2408"></a>
<span id="l2409">                return true;</span><a href="#l2409"></a>
<span id="l2410">            }</span><a href="#l2410"></a>
<span id="l2411"></span><a href="#l2411"></a>
<span id="l2412">            if (obj instanceof FieldReflectorKey) {</span><a href="#l2412"></a>
<span id="l2413">                FieldReflectorKey other = (FieldReflectorKey) obj;</span><a href="#l2413"></a>
<span id="l2414">                Class&lt;?&gt; referent;</span><a href="#l2414"></a>
<span id="l2415">                return (nullClass ? other.nullClass</span><a href="#l2415"></a>
<span id="l2416">                                  : ((referent = get()) != null) &amp;&amp;</span><a href="#l2416"></a>
<span id="l2417">                                    (referent == other.get())) &amp;&amp;</span><a href="#l2417"></a>
<span id="l2418">                        Arrays.equals(sigs, other.sigs);</span><a href="#l2418"></a>
<span id="l2419">            } else {</span><a href="#l2419"></a>
<span id="l2420">                return false;</span><a href="#l2420"></a>
<span id="l2421">            }</span><a href="#l2421"></a>
<span id="l2422">        }</span><a href="#l2422"></a>
<span id="l2423">    }</span><a href="#l2423"></a>
<span id="l2424"></span><a href="#l2424"></a>
<span id="l2425">    /**</span><a href="#l2425"></a>
<span id="l2426">     * Matches given set of serializable fields with serializable fields</span><a href="#l2426"></a>
<span id="l2427">     * obtained from the given local class descriptor (which contain bindings</span><a href="#l2427"></a>
<span id="l2428">     * to reflective Field objects).  Returns list of ObjectStreamFields in</span><a href="#l2428"></a>
<span id="l2429">     * which each ObjectStreamField whose signature matches that of a local</span><a href="#l2429"></a>
<span id="l2430">     * field contains a Field object for that field; unmatched</span><a href="#l2430"></a>
<span id="l2431">     * ObjectStreamFields contain null Field objects.  Shared/unshared settings</span><a href="#l2431"></a>
<span id="l2432">     * of the returned ObjectStreamFields also reflect those of matched local</span><a href="#l2432"></a>
<span id="l2433">     * ObjectStreamFields.  Throws InvalidClassException if unresolvable type</span><a href="#l2433"></a>
<span id="l2434">     * conflicts exist between the two sets of fields.</span><a href="#l2434"></a>
<span id="l2435">     */</span><a href="#l2435"></a>
<span id="l2436">    private static ObjectStreamField[] matchFields(ObjectStreamField[] fields,</span><a href="#l2436"></a>
<span id="l2437">                                                   ObjectStreamClass localDesc)</span><a href="#l2437"></a>
<span id="l2438">        throws InvalidClassException</span><a href="#l2438"></a>
<span id="l2439">    {</span><a href="#l2439"></a>
<span id="l2440">        ObjectStreamField[] localFields = (localDesc != null) ?</span><a href="#l2440"></a>
<span id="l2441">            localDesc.fields : NO_FIELDS;</span><a href="#l2441"></a>
<span id="l2442"></span><a href="#l2442"></a>
<span id="l2443">        /*</span><a href="#l2443"></a>
<span id="l2444">         * Even if fields == localFields, we cannot simply return localFields</span><a href="#l2444"></a>
<span id="l2445">         * here.  In previous implementations of serialization,</span><a href="#l2445"></a>
<span id="l2446">         * ObjectStreamField.getType() returned Object.class if the</span><a href="#l2446"></a>
<span id="l2447">         * ObjectStreamField represented a non-primitive field and belonged to</span><a href="#l2447"></a>
<span id="l2448">         * a non-local class descriptor.  To preserve this (questionable)</span><a href="#l2448"></a>
<span id="l2449">         * behavior, the ObjectStreamField instances returned by matchFields</span><a href="#l2449"></a>
<span id="l2450">         * cannot report non-primitive types other than Object.class; hence</span><a href="#l2450"></a>
<span id="l2451">         * localFields cannot be returned directly.</span><a href="#l2451"></a>
<span id="l2452">         */</span><a href="#l2452"></a>
<span id="l2453"></span><a href="#l2453"></a>
<span id="l2454">        ObjectStreamField[] matches = new ObjectStreamField[fields.length];</span><a href="#l2454"></a>
<span id="l2455">        for (int i = 0; i &lt; fields.length; i++) {</span><a href="#l2455"></a>
<span id="l2456">            ObjectStreamField f = fields[i], m = null;</span><a href="#l2456"></a>
<span id="l2457">            for (int j = 0; j &lt; localFields.length; j++) {</span><a href="#l2457"></a>
<span id="l2458">                ObjectStreamField lf = localFields[j];</span><a href="#l2458"></a>
<span id="l2459">                if (f.getName().equals(lf.getName())) {</span><a href="#l2459"></a>
<span id="l2460">                    if ((f.isPrimitive() || lf.isPrimitive()) &amp;&amp;</span><a href="#l2460"></a>
<span id="l2461">                        f.getTypeCode() != lf.getTypeCode())</span><a href="#l2461"></a>
<span id="l2462">                    {</span><a href="#l2462"></a>
<span id="l2463">                        throw new InvalidClassException(localDesc.name,</span><a href="#l2463"></a>
<span id="l2464">                            &quot;incompatible types for field &quot; + f.getName());</span><a href="#l2464"></a>
<span id="l2465">                    }</span><a href="#l2465"></a>
<span id="l2466">                    if (lf.getField() != null) {</span><a href="#l2466"></a>
<span id="l2467">                        m = new ObjectStreamField(</span><a href="#l2467"></a>
<span id="l2468">                            lf.getField(), lf.isUnshared(), false);</span><a href="#l2468"></a>
<span id="l2469">                    } else {</span><a href="#l2469"></a>
<span id="l2470">                        m = new ObjectStreamField(</span><a href="#l2470"></a>
<span id="l2471">                            lf.getName(), lf.getSignature(), lf.isUnshared());</span><a href="#l2471"></a>
<span id="l2472">                    }</span><a href="#l2472"></a>
<span id="l2473">                }</span><a href="#l2473"></a>
<span id="l2474">            }</span><a href="#l2474"></a>
<span id="l2475">            if (m == null) {</span><a href="#l2475"></a>
<span id="l2476">                m = new ObjectStreamField(</span><a href="#l2476"></a>
<span id="l2477">                    f.getName(), f.getSignature(), false);</span><a href="#l2477"></a>
<span id="l2478">            }</span><a href="#l2478"></a>
<span id="l2479">            m.setOffset(f.getOffset());</span><a href="#l2479"></a>
<span id="l2480">            matches[i] = m;</span><a href="#l2480"></a>
<span id="l2481">        }</span><a href="#l2481"></a>
<span id="l2482">        return matches;</span><a href="#l2482"></a>
<span id="l2483">    }</span><a href="#l2483"></a>
<span id="l2484"></span><a href="#l2484"></a>
<span id="l2485">    /**</span><a href="#l2485"></a>
<span id="l2486">     * Removes from the specified map any keys that have been enqueued</span><a href="#l2486"></a>
<span id="l2487">     * on the specified reference queue.</span><a href="#l2487"></a>
<span id="l2488">     */</span><a href="#l2488"></a>
<span id="l2489">    static void processQueue(ReferenceQueue&lt;Class&lt;?&gt;&gt; queue,</span><a href="#l2489"></a>
<span id="l2490">                             ConcurrentMap&lt;? extends</span><a href="#l2490"></a>
<span id="l2491">                             WeakReference&lt;Class&lt;?&gt;&gt;, ?&gt; map)</span><a href="#l2491"></a>
<span id="l2492">    {</span><a href="#l2492"></a>
<span id="l2493">        Reference&lt;? extends Class&lt;?&gt;&gt; ref;</span><a href="#l2493"></a>
<span id="l2494">        while((ref = queue.poll()) != null) {</span><a href="#l2494"></a>
<span id="l2495">            map.remove(ref);</span><a href="#l2495"></a>
<span id="l2496">        }</span><a href="#l2496"></a>
<span id="l2497">    }</span><a href="#l2497"></a>
<span id="l2498"></span><a href="#l2498"></a>
<span id="l2499">    /**</span><a href="#l2499"></a>
<span id="l2500">     *  Weak key for Class objects.</span><a href="#l2500"></a>
<span id="l2501">     *</span><a href="#l2501"></a>
<span id="l2502">     **/</span><a href="#l2502"></a>
<span id="l2503">    static class WeakClassKey extends WeakReference&lt;Class&lt;?&gt;&gt; {</span><a href="#l2503"></a>
<span id="l2504">        /**</span><a href="#l2504"></a>
<span id="l2505">         * saved value of the referent's identity hash code, to maintain</span><a href="#l2505"></a>
<span id="l2506">         * a consistent hash code after the referent has been cleared</span><a href="#l2506"></a>
<span id="l2507">         */</span><a href="#l2507"></a>
<span id="l2508">        private final int hash;</span><a href="#l2508"></a>
<span id="l2509"></span><a href="#l2509"></a>
<span id="l2510">        /**</span><a href="#l2510"></a>
<span id="l2511">         * Create a new WeakClassKey to the given object, registered</span><a href="#l2511"></a>
<span id="l2512">         * with a queue.</span><a href="#l2512"></a>
<span id="l2513">         */</span><a href="#l2513"></a>
<span id="l2514">        WeakClassKey(Class&lt;?&gt; cl, ReferenceQueue&lt;Class&lt;?&gt;&gt; refQueue) {</span><a href="#l2514"></a>
<span id="l2515">            super(cl, refQueue);</span><a href="#l2515"></a>
<span id="l2516">            hash = System.identityHashCode(cl);</span><a href="#l2516"></a>
<span id="l2517">        }</span><a href="#l2517"></a>
<span id="l2518"></span><a href="#l2518"></a>
<span id="l2519">        /**</span><a href="#l2519"></a>
<span id="l2520">         * Returns the identity hash code of the original referent.</span><a href="#l2520"></a>
<span id="l2521">         */</span><a href="#l2521"></a>
<span id="l2522">        public int hashCode() {</span><a href="#l2522"></a>
<span id="l2523">            return hash;</span><a href="#l2523"></a>
<span id="l2524">        }</span><a href="#l2524"></a>
<span id="l2525"></span><a href="#l2525"></a>
<span id="l2526">        /**</span><a href="#l2526"></a>
<span id="l2527">         * Returns true if the given object is this identical</span><a href="#l2527"></a>
<span id="l2528">         * WeakClassKey instance, or, if this object's referent has not</span><a href="#l2528"></a>
<span id="l2529">         * been cleared, if the given object is another WeakClassKey</span><a href="#l2529"></a>
<span id="l2530">         * instance with the identical non-null referent as this one.</span><a href="#l2530"></a>
<span id="l2531">         */</span><a href="#l2531"></a>
<span id="l2532">        public boolean equals(Object obj) {</span><a href="#l2532"></a>
<span id="l2533">            if (obj == this) {</span><a href="#l2533"></a>
<span id="l2534">                return true;</span><a href="#l2534"></a>
<span id="l2535">            }</span><a href="#l2535"></a>
<span id="l2536"></span><a href="#l2536"></a>
<span id="l2537">            if (obj instanceof WeakClassKey) {</span><a href="#l2537"></a>
<span id="l2538">                Object referent = get();</span><a href="#l2538"></a>
<span id="l2539">                return (referent != null) &amp;&amp;</span><a href="#l2539"></a>
<span id="l2540">                       (referent == ((WeakClassKey) obj).get());</span><a href="#l2540"></a>
<span id="l2541">            } else {</span><a href="#l2541"></a>
<span id="l2542">                return false;</span><a href="#l2542"></a>
<span id="l2543">            }</span><a href="#l2543"></a>
<span id="l2544">        }</span><a href="#l2544"></a>
<span id="l2545">    }</span><a href="#l2545"></a>
<span id="l2546">}</span><a href="#l2546"></a></pre>
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

