<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk6/jdk6/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk6/jdk6/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk6/jdk6/jdk/static/mercurial.js"></script>

<title>jdk6/jdk6/jdk: 9b95de10b74e src/share/classes/java/lang/ClassLoader.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk6/jdk6/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk6/jdk6/jdk/shortlog/9b95de10b74e">log</a></li>
<li><a href="/jdk6/jdk6/jdk/graph/9b95de10b74e">graph</a></li>
<li><a href="/jdk6/jdk6/jdk/tags">tags</a></li>
<li><a href="/jdk6/jdk6/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk6/jdk6/jdk/rev/9b95de10b74e">changeset</a></li>
<li><a href="/jdk6/jdk6/jdk/file/9b95de10b74e/src/share/classes/java/lang/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk6/jdk6/jdk/file/tip/src/share/classes/java/lang/ClassLoader.java">latest</a></li>
<li><a href="/jdk6/jdk6/jdk/diff/9b95de10b74e/src/share/classes/java/lang/ClassLoader.java">diff</a></li>
<li><a href="/jdk6/jdk6/jdk/comparison/9b95de10b74e/src/share/classes/java/lang/ClassLoader.java">comparison</a></li>
<li><a href="/jdk6/jdk6/jdk/annotate/9b95de10b74e/src/share/classes/java/lang/ClassLoader.java">annotate</a></li>
<li><a href="/jdk6/jdk6/jdk/log/9b95de10b74e/src/share/classes/java/lang/ClassLoader.java">file log</a></li>
<li><a href="/jdk6/jdk6/jdk/raw-file/9b95de10b74e/src/share/classes/java/lang/ClassLoader.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk6/jdk6/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk6">jdk6</a> / <a href="/jdk6/jdk6">jdk6</a> / <a href="/jdk6/jdk6/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/lang/ClassLoader.java @ 220:9b95de10b74e</h3>

<form class="search" action="/jdk6/jdk6/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk6/jdk6/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">6636650: (cl) Resurrected ClassLoaders can still have children
Summary: Prevent classloader from resurrection
Reviewed-by: hawtin</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#118;&#97;&#108;&#101;&#114;&#105;&#101;&#112;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 24 Aug 2009 17:09:10 -0700</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk6/jdk6/jdk/file/2d585507a41b/src/share/classes/java/lang/ClassLoader.java">2d585507a41b</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk6/jdk6/jdk/file/ffa98eed5766/src/share/classes/java/lang/ClassLoader.java">ffa98eed5766</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright 1994-2009 Sun Microsystems, Inc.  All Rights Reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Sun designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Sun in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
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
<span id="l21"> * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,</span><a href="#l21"></a>
<span id="l22"> * CA 95054 USA or visit www.sun.com if you need additional information or</span><a href="#l22"></a>
<span id="l23"> * have any questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25">package java.lang;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.io.InputStream;</span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.File;</span><a href="#l29"></a>
<span id="l30">import java.lang.reflect.Constructor;</span><a href="#l30"></a>
<span id="l31">import java.lang.reflect.InvocationTargetException;</span><a href="#l31"></a>
<span id="l32">import java.net.MalformedURLException;</span><a href="#l32"></a>
<span id="l33">import java.net.URL;</span><a href="#l33"></a>
<span id="l34">import java.security.AccessController;</span><a href="#l34"></a>
<span id="l35">import java.security.AccessControlContext;</span><a href="#l35"></a>
<span id="l36">import java.security.CodeSource;</span><a href="#l36"></a>
<span id="l37">import java.security.Policy;</span><a href="#l37"></a>
<span id="l38">import java.security.PrivilegedAction;</span><a href="#l38"></a>
<span id="l39">import java.security.PrivilegedActionException;</span><a href="#l39"></a>
<span id="l40">import java.security.PrivilegedExceptionAction;</span><a href="#l40"></a>
<span id="l41">import java.security.ProtectionDomain;</span><a href="#l41"></a>
<span id="l42">import java.util.Enumeration;</span><a href="#l42"></a>
<span id="l43">import java.util.Hashtable;</span><a href="#l43"></a>
<span id="l44">import java.util.HashMap;</span><a href="#l44"></a>
<span id="l45">import java.util.HashSet;</span><a href="#l45"></a>
<span id="l46">import java.util.Set;</span><a href="#l46"></a>
<span id="l47">import java.util.Stack;</span><a href="#l47"></a>
<span id="l48">import java.util.Map;</span><a href="#l48"></a>
<span id="l49">import java.util.Vector;</span><a href="#l49"></a>
<span id="l50">import sun.misc.ClassFileTransformer;</span><a href="#l50"></a>
<span id="l51">import sun.misc.CompoundEnumeration;</span><a href="#l51"></a>
<span id="l52">import sun.misc.Resource;</span><a href="#l52"></a>
<span id="l53">import sun.misc.URLClassPath;</span><a href="#l53"></a>
<span id="l54">import sun.misc.VM;</span><a href="#l54"></a>
<span id="l55">import sun.reflect.Reflection;</span><a href="#l55"></a>
<span id="l56">import sun.security.util.SecurityConstants;</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">/**</span><a href="#l58"></a>
<span id="l59"> * A class loader is an object that is responsible for loading classes. The</span><a href="#l59"></a>
<span id="l60"> * class &lt;tt&gt;ClassLoader&lt;/tt&gt; is an abstract class.  Given the &lt;a</span><a href="#l60"></a>
<span id="l61"> * href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of a class, a class loader should attempt to</span><a href="#l61"></a>
<span id="l62"> * locate or generate data that constitutes a definition for the class.  A</span><a href="#l62"></a>
<span id="l63"> * typical strategy is to transform the name into a file name and then read a</span><a href="#l63"></a>
<span id="l64"> * &quot;class file&quot; of that name from a file system.</span><a href="#l64"></a>
<span id="l65"> *</span><a href="#l65"></a>
<span id="l66"> * &lt;p&gt; Every {@link Class &lt;tt&gt;Class&lt;/tt&gt;} object contains a {@link</span><a href="#l66"></a>
<span id="l67"> * Class#getClassLoader() reference} to the &lt;tt&gt;ClassLoader&lt;/tt&gt; that defined</span><a href="#l67"></a>
<span id="l68"> * it.</span><a href="#l68"></a>
<span id="l69"> *</span><a href="#l69"></a>
<span id="l70"> * &lt;p&gt; &lt;tt&gt;Class&lt;/tt&gt; objects for array classes are not created by class</span><a href="#l70"></a>
<span id="l71"> * loaders, but are created automatically as required by the Java runtime.</span><a href="#l71"></a>
<span id="l72"> * The class loader for an array class, as returned by {@link</span><a href="#l72"></a>
<span id="l73"> * Class#getClassLoader()} is the same as the class loader for its element</span><a href="#l73"></a>
<span id="l74"> * type; if the element type is a primitive type, then the array class has no</span><a href="#l74"></a>
<span id="l75"> * class loader.</span><a href="#l75"></a>
<span id="l76"> *</span><a href="#l76"></a>
<span id="l77"> * &lt;p&gt; Applications implement subclasses of &lt;tt&gt;ClassLoader&lt;/tt&gt; in order to</span><a href="#l77"></a>
<span id="l78"> * extend the manner in which the Java virtual machine dynamically loads</span><a href="#l78"></a>
<span id="l79"> * classes.</span><a href="#l79"></a>
<span id="l80"> *</span><a href="#l80"></a>
<span id="l81"> * &lt;p&gt; Class loaders may typically be used by security managers to indicate</span><a href="#l81"></a>
<span id="l82"> * security domains.</span><a href="#l82"></a>
<span id="l83"> *</span><a href="#l83"></a>
<span id="l84"> * &lt;p&gt; The &lt;tt&gt;ClassLoader&lt;/tt&gt; class uses a delegation model to search for</span><a href="#l84"></a>
<span id="l85"> * classes and resources.  Each instance of &lt;tt&gt;ClassLoader&lt;/tt&gt; has an</span><a href="#l85"></a>
<span id="l86"> * associated parent class loader.  When requested to find a class or</span><a href="#l86"></a>
<span id="l87"> * resource, a &lt;tt&gt;ClassLoader&lt;/tt&gt; instance will delegate the search for the</span><a href="#l87"></a>
<span id="l88"> * class or resource to its parent class loader before attempting to find the</span><a href="#l88"></a>
<span id="l89"> * class or resource itself.  The virtual machine's built-in class loader,</span><a href="#l89"></a>
<span id="l90"> * called the &quot;bootstrap class loader&quot;, does not itself have a parent but may</span><a href="#l90"></a>
<span id="l91"> * serve as the parent of a &lt;tt&gt;ClassLoader&lt;/tt&gt; instance.</span><a href="#l91"></a>
<span id="l92"> *</span><a href="#l92"></a>
<span id="l93"> * &lt;p&gt; Normally, the Java virtual machine loads classes from the local file</span><a href="#l93"></a>
<span id="l94"> * system in a platform-dependent manner.  For example, on UNIX systems, the</span><a href="#l94"></a>
<span id="l95"> * virtual machine loads classes from the directory defined by the</span><a href="#l95"></a>
<span id="l96"> * &lt;tt&gt;CLASSPATH&lt;/tt&gt; environment variable.</span><a href="#l96"></a>
<span id="l97"> *</span><a href="#l97"></a>
<span id="l98"> * &lt;p&gt; However, some classes may not originate from a file; they may originate</span><a href="#l98"></a>
<span id="l99"> * from other sources, such as the network, or they could be constructed by an</span><a href="#l99"></a>
<span id="l100"> * application.  The method {@link #defineClass(String, byte[], int, int)</span><a href="#l100"></a>
<span id="l101"> * &lt;tt&gt;defineClass&lt;/tt&gt;} converts an array of bytes into an instance of class</span><a href="#l101"></a>
<span id="l102"> * &lt;tt&gt;Class&lt;/tt&gt;. Instances of this newly defined class can be created using</span><a href="#l102"></a>
<span id="l103"> * {@link Class#newInstance &lt;tt&gt;Class.newInstance&lt;/tt&gt;}.</span><a href="#l103"></a>
<span id="l104"> *</span><a href="#l104"></a>
<span id="l105"> * &lt;p&gt; The methods and constructors of objects created by a class loader may</span><a href="#l105"></a>
<span id="l106"> * reference other classes.  To determine the class(es) referred to, the Java</span><a href="#l106"></a>
<span id="l107"> * virtual machine invokes the {@link #loadClass &lt;tt&gt;loadClass&lt;/tt&gt;} method of</span><a href="#l107"></a>
<span id="l108"> * the class loader that originally created the class.</span><a href="#l108"></a>
<span id="l109"> *</span><a href="#l109"></a>
<span id="l110"> * &lt;p&gt; For example, an application could create a network class loader to</span><a href="#l110"></a>
<span id="l111"> * download class files from a server.  Sample code might look like:</span><a href="#l111"></a>
<span id="l112"> *</span><a href="#l112"></a>
<span id="l113"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l113"></a>
<span id="l114"> *   ClassLoader loader&amp;nbsp;= new NetworkClassLoader(host,&amp;nbsp;port);</span><a href="#l114"></a>
<span id="l115"> *   Object main&amp;nbsp;= loader.loadClass(&quot;Main&quot;, true).newInstance();</span><a href="#l115"></a>
<span id="l116"> *       &amp;nbsp;.&amp;nbsp;.&amp;nbsp;.</span><a href="#l116"></a>
<span id="l117"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l117"></a>
<span id="l118"> *</span><a href="#l118"></a>
<span id="l119"> * &lt;p&gt; The network class loader subclass must define the methods {@link</span><a href="#l119"></a>
<span id="l120"> * #findClass &lt;tt&gt;findClass&lt;/tt&gt;} and &lt;tt&gt;loadClassData&lt;/tt&gt; to load a class</span><a href="#l120"></a>
<span id="l121"> * from the network.  Once it has downloaded the bytes that make up the class,</span><a href="#l121"></a>
<span id="l122"> * it should use the method {@link #defineClass &lt;tt&gt;defineClass&lt;/tt&gt;} to</span><a href="#l122"></a>
<span id="l123"> * create a class instance.  A sample implementation is:</span><a href="#l123"></a>
<span id="l124"> *</span><a href="#l124"></a>
<span id="l125"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l125"></a>
<span id="l126"> *     class NetworkClassLoader extends ClassLoader {</span><a href="#l126"></a>
<span id="l127"> *         String host;</span><a href="#l127"></a>
<span id="l128"> *         int port;</span><a href="#l128"></a>
<span id="l129"> *</span><a href="#l129"></a>
<span id="l130"> *         public Class findClass(String name) {</span><a href="#l130"></a>
<span id="l131"> *             byte[] b = loadClassData(name);</span><a href="#l131"></a>
<span id="l132"> *             return defineClass(name, b, 0, b.length);</span><a href="#l132"></a>
<span id="l133"> *         }</span><a href="#l133"></a>
<span id="l134"> *</span><a href="#l134"></a>
<span id="l135"> *         private byte[] loadClassData(String name) {</span><a href="#l135"></a>
<span id="l136"> *             // load the class data from the connection</span><a href="#l136"></a>
<span id="l137"> *             &amp;nbsp;.&amp;nbsp;.&amp;nbsp;.</span><a href="#l137"></a>
<span id="l138"> *         }</span><a href="#l138"></a>
<span id="l139"> *     }</span><a href="#l139"></a>
<span id="l140"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l140"></a>
<span id="l141"> *</span><a href="#l141"></a>
<span id="l142"> * &lt;h4&gt; &lt;a name=&quot;name&quot;&gt;Binary names&lt;/a&gt; &lt;/h4&gt;</span><a href="#l142"></a>
<span id="l143"> *</span><a href="#l143"></a>
<span id="l144"> * &lt;p&gt; Any class name provided as a {@link String} parameter to methods in</span><a href="#l144"></a>
<span id="l145"> * &lt;tt&gt;ClassLoader&lt;/tt&gt; must be a binary name as defined by the &lt;a</span><a href="#l145"></a>
<span id="l146"> * href=&quot;http://java.sun.com/docs/books/jls/&quot;&gt;Java Language Specification&lt;/a&gt;.</span><a href="#l146"></a>
<span id="l147"> *</span><a href="#l147"></a>
<span id="l148"> * &lt;p&gt; Examples of valid class names include:</span><a href="#l148"></a>
<span id="l149"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l149"></a>
<span id="l150"> *   &quot;java.lang.String&quot;</span><a href="#l150"></a>
<span id="l151"> *   &quot;javax.swing.JSpinner$DefaultEditor&quot;</span><a href="#l151"></a>
<span id="l152"> *   &quot;java.security.KeyStore$Builder$FileBuilder$1&quot;</span><a href="#l152"></a>
<span id="l153"> *   &quot;java.net.URLClassLoader$3$1&quot;</span><a href="#l153"></a>
<span id="l154"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l154"></a>
<span id="l155"> *</span><a href="#l155"></a>
<span id="l156"> * @see      #resolveClass(Class)</span><a href="#l156"></a>
<span id="l157"> * @since 1.0</span><a href="#l157"></a>
<span id="l158"> */</span><a href="#l158"></a>
<span id="l159">public abstract class ClassLoader {</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">    private static native void registerNatives();</span><a href="#l161"></a>
<span id="l162">    static {</span><a href="#l162"></a>
<span id="l163">        registerNatives();</span><a href="#l163"></a>
<span id="l164">    }</span><a href="#l164"></a>
<span id="l165"></span><a href="#l165"></a>
<span id="l166">    // The parent class loader for delegation</span><a href="#l166"></a>
<span id="l167">    private ClassLoader parent;</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">    // Hashtable that maps packages to certs</span><a href="#l169"></a>
<span id="l170">    private Hashtable package2certs = new Hashtable(11);</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    // Shared among all packages with unsigned classes</span><a href="#l172"></a>
<span id="l173">    java.security.cert.Certificate[] nocerts;</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    // The classes loaded by this class loader.  The only purpose of this table</span><a href="#l175"></a>
<span id="l176">    // is to keep the classes from being GC'ed until the loader is GC'ed.</span><a href="#l176"></a>
<span id="l177">    private Vector classes = new Vector();</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    // The initiating protection domains for all classes loaded by this loader</span><a href="#l179"></a>
<span id="l180">    private Set domains = new HashSet();</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    // Invoked by the VM to record every loaded class with this loader.</span><a href="#l182"></a>
<span id="l183">    void addClass(Class c) {</span><a href="#l183"></a>
<span id="l184">        classes.addElement(c);</span><a href="#l184"></a>
<span id="l185">    }</span><a href="#l185"></a>
<span id="l186"></span><a href="#l186"></a>
<span id="l187">    // The packages defined in this class loader.  Each package name is mapped</span><a href="#l187"></a>
<span id="l188">    // to its corresponding Package object.</span><a href="#l188"></a>
<span id="l189">    private HashMap packages = new HashMap();</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    private static Void checkCreateClassLoader() {</span><a href="#l191"></a>
<span id="l192">	SecurityManager security = System.getSecurityManager();</span><a href="#l192"></a>
<span id="l193">	if (security != null) {</span><a href="#l193"></a>
<span id="l194">            security.checkCreateClassLoader();</span><a href="#l194"></a>
<span id="l195">	}</span><a href="#l195"></a>
<span id="l196">        return null;</span><a href="#l196"></a>
<span id="l197">    }</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">    private ClassLoader(Void unused, ClassLoader parent) {</span><a href="#l199"></a>
<span id="l200">        this.parent = parent;</span><a href="#l200"></a>
<span id="l201">    }</span><a href="#l201"></a>
<span id="l202"></span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">    /**</span><a href="#l204"></a>
<span id="l205">     * Creates a new class loader using the specified parent class loader for</span><a href="#l205"></a>
<span id="l206">     * delegation.</span><a href="#l206"></a>
<span id="l207">     *</span><a href="#l207"></a>
<span id="l208">     * &lt;p&gt; If there is a security manager, its {@link</span><a href="#l208"></a>
<span id="l209">     * SecurityManager#checkCreateClassLoader()</span><a href="#l209"></a>
<span id="l210">     * &lt;tt&gt;checkCreateClassLoader&lt;/tt&gt;} method is invoked.  This may result in</span><a href="#l210"></a>
<span id="l211">     * a security exception.  &lt;/p&gt;</span><a href="#l211"></a>
<span id="l212">     *</span><a href="#l212"></a>
<span id="l213">     * @param  parent</span><a href="#l213"></a>
<span id="l214">     *         The parent class loader</span><a href="#l214"></a>
<span id="l215">     *</span><a href="#l215"></a>
<span id="l216">     * @throws  SecurityException</span><a href="#l216"></a>
<span id="l217">     *          If a security manager exists and its</span><a href="#l217"></a>
<span id="l218">     *          &lt;tt&gt;checkCreateClassLoader&lt;/tt&gt; method doesn't allow creation</span><a href="#l218"></a>
<span id="l219">     *          of a new class loader.</span><a href="#l219"></a>
<span id="l220">     *</span><a href="#l220"></a>
<span id="l221">     * @since  1.2</span><a href="#l221"></a>
<span id="l222">     */</span><a href="#l222"></a>
<span id="l223">    protected ClassLoader(ClassLoader parent) {</span><a href="#l223"></a>
<span id="l224">	this(checkCreateClassLoader(), parent);</span><a href="#l224"></a>
<span id="l225">    }</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    /**</span><a href="#l227"></a>
<span id="l228">     * Creates a new class loader using the &lt;tt&gt;ClassLoader&lt;/tt&gt; returned by</span><a href="#l228"></a>
<span id="l229">     * the method {@link #getSystemClassLoader()</span><a href="#l229"></a>
<span id="l230">     * &lt;tt&gt;getSystemClassLoader()&lt;/tt&gt;} as the parent class loader.</span><a href="#l230"></a>
<span id="l231">     *</span><a href="#l231"></a>
<span id="l232">     * &lt;p&gt; If there is a security manager, its {@link</span><a href="#l232"></a>
<span id="l233">     * SecurityManager#checkCreateClassLoader()</span><a href="#l233"></a>
<span id="l234">     * &lt;tt&gt;checkCreateClassLoader&lt;/tt&gt;} method is invoked.  This may result in</span><a href="#l234"></a>
<span id="l235">     * a security exception.  &lt;/p&gt;</span><a href="#l235"></a>
<span id="l236">     *</span><a href="#l236"></a>
<span id="l237">     * @throws  SecurityException</span><a href="#l237"></a>
<span id="l238">     *          If a security manager exists and its</span><a href="#l238"></a>
<span id="l239">     *          &lt;tt&gt;checkCreateClassLoader&lt;/tt&gt; method doesn't allow creation</span><a href="#l239"></a>
<span id="l240">     *          of a new class loader.</span><a href="#l240"></a>
<span id="l241">     */</span><a href="#l241"></a>
<span id="l242">    protected ClassLoader() {</span><a href="#l242"></a>
<span id="l243">	this(checkCreateClassLoader(), getSystemClassLoader());</span><a href="#l243"></a>
<span id="l244">    }</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    // -- Class --</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    /**</span><a href="#l249"></a>
<span id="l250">     * Loads the class with the specified &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt;.</span><a href="#l250"></a>
<span id="l251">     * This method searches for classes in the same manner as the {@link</span><a href="#l251"></a>
<span id="l252">     * #loadClass(String, boolean)} method.  It is invoked by the Java virtual</span><a href="#l252"></a>
<span id="l253">     * machine to resolve class references.  Invoking this method is equivalent</span><a href="#l253"></a>
<span id="l254">     * to invoking {@link #loadClass(String, boolean) &lt;tt&gt;loadClass(name,</span><a href="#l254"></a>
<span id="l255">     * false)&lt;/tt&gt;}.  &lt;/p&gt;</span><a href="#l255"></a>
<span id="l256">     *</span><a href="#l256"></a>
<span id="l257">     * @param  name</span><a href="#l257"></a>
<span id="l258">     *         The &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l258"></a>
<span id="l259">     *</span><a href="#l259"></a>
<span id="l260">     * @return  The resulting &lt;tt&gt;Class&lt;/tt&gt; object</span><a href="#l260"></a>
<span id="l261">     *</span><a href="#l261"></a>
<span id="l262">     * @throws  ClassNotFoundException</span><a href="#l262"></a>
<span id="l263">     *          If the class was not found</span><a href="#l263"></a>
<span id="l264">     */</span><a href="#l264"></a>
<span id="l265">    public Class&lt;?&gt; loadClass(String name) throws ClassNotFoundException {</span><a href="#l265"></a>
<span id="l266">        return loadClass(name, false);</span><a href="#l266"></a>
<span id="l267">    }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">    /**</span><a href="#l269"></a>
<span id="l270">     * Loads the class with the specified &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt;.  The</span><a href="#l270"></a>
<span id="l271">     * default implementation of this method searches for classes in the</span><a href="#l271"></a>
<span id="l272">     * following order:</span><a href="#l272"></a>
<span id="l273">     *</span><a href="#l273"></a>
<span id="l274">     * &lt;p&gt;&lt;ol&gt;</span><a href="#l274"></a>
<span id="l275">     *</span><a href="#l275"></a>
<span id="l276">     *   &lt;li&gt;&lt;p&gt; Invoke {@link #findLoadedClass(String)} to check if the class</span><a href="#l276"></a>
<span id="l277">     *   has already been loaded.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l277"></a>
<span id="l278">     *</span><a href="#l278"></a>
<span id="l279">     *   &lt;li&gt;&lt;p&gt; Invoke the {@link #loadClass(String) &lt;tt&gt;loadClass&lt;/tt&gt;} method</span><a href="#l279"></a>
<span id="l280">     *   on the parent class loader.  If the parent is &lt;tt&gt;null&lt;/tt&gt; the class</span><a href="#l280"></a>
<span id="l281">     *   loader built-in to the virtual machine is used, instead.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l281"></a>
<span id="l282">     *</span><a href="#l282"></a>
<span id="l283">     *   &lt;li&gt;&lt;p&gt; Invoke the {@link #findClass(String)} method to find the</span><a href="#l283"></a>
<span id="l284">     *   class.  &lt;/p&gt;&lt;/li&gt;</span><a href="#l284"></a>
<span id="l285">     *</span><a href="#l285"></a>
<span id="l286">     * &lt;/ol&gt;</span><a href="#l286"></a>
<span id="l287">     *</span><a href="#l287"></a>
<span id="l288">     * &lt;p&gt; If the class was found using the above steps, and the</span><a href="#l288"></a>
<span id="l289">     * &lt;tt&gt;resolve&lt;/tt&gt; flag is true, this method will then invoke the {@link</span><a href="#l289"></a>
<span id="l290">     * #resolveClass(Class)} method on the resulting &lt;tt&gt;Class&lt;/tt&gt; object.</span><a href="#l290"></a>
<span id="l291">     *</span><a href="#l291"></a>
<span id="l292">     * &lt;p&gt; Subclasses of &lt;tt&gt;ClassLoader&lt;/tt&gt; are encouraged to override {@link</span><a href="#l292"></a>
<span id="l293">     * #findClass(String)}, rather than this method.  &lt;/p&gt;</span><a href="#l293"></a>
<span id="l294">     *</span><a href="#l294"></a>
<span id="l295">     * @param  name</span><a href="#l295"></a>
<span id="l296">     *         The &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l296"></a>
<span id="l297">     *</span><a href="#l297"></a>
<span id="l298">     * @param  resolve</span><a href="#l298"></a>
<span id="l299">     *         If &lt;tt&gt;true&lt;/tt&gt; then resolve the class</span><a href="#l299"></a>
<span id="l300">     *</span><a href="#l300"></a>
<span id="l301">     * @return  The resulting &lt;tt&gt;Class&lt;/tt&gt; object</span><a href="#l301"></a>
<span id="l302">     *</span><a href="#l302"></a>
<span id="l303">     * @throws  ClassNotFoundException</span><a href="#l303"></a>
<span id="l304">     *          If the class could not be found</span><a href="#l304"></a>
<span id="l305">     */</span><a href="#l305"></a>
<span id="l306">    protected synchronized Class&lt;?&gt; loadClass(String name, boolean resolve)</span><a href="#l306"></a>
<span id="l307">        throws ClassNotFoundException</span><a href="#l307"></a>
<span id="l308">    {</span><a href="#l308"></a>
<span id="l309">        // First, check if the class has already been loaded</span><a href="#l309"></a>
<span id="l310">        Class c = findLoadedClass(name);</span><a href="#l310"></a>
<span id="l311">        if (c == null) {</span><a href="#l311"></a>
<span id="l312">            try {</span><a href="#l312"></a>
<span id="l313">                if (parent != null) {</span><a href="#l313"></a>
<span id="l314">                    c = parent.loadClass(name, false);</span><a href="#l314"></a>
<span id="l315">                } else {</span><a href="#l315"></a>
<span id="l316">                    c = findBootstrapClass0(name);</span><a href="#l316"></a>
<span id="l317">                }</span><a href="#l317"></a>
<span id="l318">            } catch (ClassNotFoundException e) {</span><a href="#l318"></a>
<span id="l319">                // If still not found, then invoke findClass in order</span><a href="#l319"></a>
<span id="l320">                // to find the class.</span><a href="#l320"></a>
<span id="l321">                c = findClass(name);</span><a href="#l321"></a>
<span id="l322">            }</span><a href="#l322"></a>
<span id="l323">        }</span><a href="#l323"></a>
<span id="l324">        if (resolve) {</span><a href="#l324"></a>
<span id="l325">            resolveClass(c);</span><a href="#l325"></a>
<span id="l326">        }</span><a href="#l326"></a>
<span id="l327">        return c;</span><a href="#l327"></a>
<span id="l328">    }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">    // This method is invoked by the virtual machine to load a class.</span><a href="#l330"></a>
<span id="l331">    private synchronized Class loadClassInternal(String name)</span><a href="#l331"></a>
<span id="l332">        throws ClassNotFoundException</span><a href="#l332"></a>
<span id="l333">    {</span><a href="#l333"></a>
<span id="l334">        return loadClass(name);</span><a href="#l334"></a>
<span id="l335">    }</span><a href="#l335"></a>
<span id="l336"></span><a href="#l336"></a>
<span id="l337">    private void checkPackageAccess(Class cls, ProtectionDomain pd) {</span><a href="#l337"></a>
<span id="l338">        final SecurityManager sm = System.getSecurityManager();</span><a href="#l338"></a>
<span id="l339">        if (sm != null) {</span><a href="#l339"></a>
<span id="l340">            final String name = cls.getName();</span><a href="#l340"></a>
<span id="l341">            final int i = name.lastIndexOf('.');</span><a href="#l341"></a>
<span id="l342">            if (i != -1) {</span><a href="#l342"></a>
<span id="l343">                AccessController.doPrivileged(new PrivilegedAction() {</span><a href="#l343"></a>
<span id="l344">                    public Object run() {</span><a href="#l344"></a>
<span id="l345">                        sm.checkPackageAccess(name.substring(0, i));</span><a href="#l345"></a>
<span id="l346">                        return null;</span><a href="#l346"></a>
<span id="l347">                    }</span><a href="#l347"></a>
<span id="l348">                }, new AccessControlContext(new ProtectionDomain[] {pd}));</span><a href="#l348"></a>
<span id="l349">            }</span><a href="#l349"></a>
<span id="l350">        }</span><a href="#l350"></a>
<span id="l351">        domains.add(pd);</span><a href="#l351"></a>
<span id="l352">    }</span><a href="#l352"></a>
<span id="l353"></span><a href="#l353"></a>
<span id="l354">    /**</span><a href="#l354"></a>
<span id="l355">     * Finds the class with the specified &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt;.</span><a href="#l355"></a>
<span id="l356">     * This method should be overridden by class loader implementations that</span><a href="#l356"></a>
<span id="l357">     * follow the delegation model for loading classes, and will be invoked by</span><a href="#l357"></a>
<span id="l358">     * the {@link #loadClass &lt;tt&gt;loadClass&lt;/tt&gt;} method after checking the</span><a href="#l358"></a>
<span id="l359">     * parent class loader for the requested class.  The default implementation</span><a href="#l359"></a>
<span id="l360">     * throws a &lt;tt&gt;ClassNotFoundException&lt;/tt&gt;.  &lt;/p&gt;</span><a href="#l360"></a>
<span id="l361">     *</span><a href="#l361"></a>
<span id="l362">     * @param  name</span><a href="#l362"></a>
<span id="l363">     *         The &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l363"></a>
<span id="l364">     *</span><a href="#l364"></a>
<span id="l365">     * @return  The resulting &lt;tt&gt;Class&lt;/tt&gt; object</span><a href="#l365"></a>
<span id="l366">     *</span><a href="#l366"></a>
<span id="l367">     * @throws  ClassNotFoundException</span><a href="#l367"></a>
<span id="l368">     *          If the class could not be found</span><a href="#l368"></a>
<span id="l369">     *</span><a href="#l369"></a>
<span id="l370">     * @since  1.2</span><a href="#l370"></a>
<span id="l371">     */</span><a href="#l371"></a>
<span id="l372">    protected Class&lt;?&gt; findClass(String name) throws ClassNotFoundException {</span><a href="#l372"></a>
<span id="l373">        throw new ClassNotFoundException(name);</span><a href="#l373"></a>
<span id="l374">    }</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">    /**</span><a href="#l376"></a>
<span id="l377">     * Converts an array of bytes into an instance of class &lt;tt&gt;Class&lt;/tt&gt;.</span><a href="#l377"></a>
<span id="l378">     * Before the &lt;tt&gt;Class&lt;/tt&gt; can be used it must be resolved.  This method</span><a href="#l378"></a>
<span id="l379">     * is deprecated in favor of the version that takes a &lt;a</span><a href="#l379"></a>
<span id="l380">     * href=&quot;#name&quot;&gt;binary name&lt;/a&gt; as its first argument, and is more secure.</span><a href="#l380"></a>
<span id="l381">     *</span><a href="#l381"></a>
<span id="l382">     * @param  b</span><a href="#l382"></a>
<span id="l383">     *         The bytes that make up the class data.  The bytes in positions</span><a href="#l383"></a>
<span id="l384">     *         &lt;tt&gt;off&lt;/tt&gt; through &lt;tt&gt;off+len-1&lt;/tt&gt; should have the format</span><a href="#l384"></a>
<span id="l385">     *         of a valid class file as defined by the &lt;a</span><a href="#l385"></a>
<span id="l386">     *         href=&quot;http://java.sun.com/docs/books/vmspec/&quot;&gt;Java Virtual</span><a href="#l386"></a>
<span id="l387">     *         Machine Specification&lt;/a&gt;.</span><a href="#l387"></a>
<span id="l388">     *</span><a href="#l388"></a>
<span id="l389">     * @param  off</span><a href="#l389"></a>
<span id="l390">     *         The start offset in &lt;tt&gt;b&lt;/tt&gt; of the class data</span><a href="#l390"></a>
<span id="l391">     *</span><a href="#l391"></a>
<span id="l392">     * @param  len</span><a href="#l392"></a>
<span id="l393">     *         The length of the class data</span><a href="#l393"></a>
<span id="l394">     *</span><a href="#l394"></a>
<span id="l395">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object that was created from the specified</span><a href="#l395"></a>
<span id="l396">     *          class data</span><a href="#l396"></a>
<span id="l397">     *</span><a href="#l397"></a>
<span id="l398">     * @throws  ClassFormatError</span><a href="#l398"></a>
<span id="l399">     *          If the data did not contain a valid class</span><a href="#l399"></a>
<span id="l400">     *</span><a href="#l400"></a>
<span id="l401">     * @throws  IndexOutOfBoundsException</span><a href="#l401"></a>
<span id="l402">     *          If either &lt;tt&gt;off&lt;/tt&gt; or &lt;tt&gt;len&lt;/tt&gt; is negative, or if</span><a href="#l402"></a>
<span id="l403">     *          &lt;tt&gt;off+len&lt;/tt&gt; is greater than &lt;tt&gt;b.length&lt;/tt&gt;.</span><a href="#l403"></a>
<span id="l404">     *</span><a href="#l404"></a>
<span id="l405">     * @see  #loadClass(String, boolean)</span><a href="#l405"></a>
<span id="l406">     * @see  #resolveClass(Class)</span><a href="#l406"></a>
<span id="l407">     *</span><a href="#l407"></a>
<span id="l408">     * @deprecated  Replaced by {@link #defineClass(String, byte[], int, int)</span><a href="#l408"></a>
<span id="l409">     * defineClass(String, byte[], int, int)}</span><a href="#l409"></a>
<span id="l410">     */</span><a href="#l410"></a>
<span id="l411">    @Deprecated</span><a href="#l411"></a>
<span id="l412">    protected final Class&lt;?&gt; defineClass(byte[] b, int off, int len)</span><a href="#l412"></a>
<span id="l413">        throws ClassFormatError</span><a href="#l413"></a>
<span id="l414">    {</span><a href="#l414"></a>
<span id="l415">        return defineClass(null, b, off, len, null);</span><a href="#l415"></a>
<span id="l416">    }</span><a href="#l416"></a>
<span id="l417"></span><a href="#l417"></a>
<span id="l418">    /**</span><a href="#l418"></a>
<span id="l419">     * Converts an array of bytes into an instance of class &lt;tt&gt;Class&lt;/tt&gt;.</span><a href="#l419"></a>
<span id="l420">     * Before the &lt;tt&gt;Class&lt;/tt&gt; can be used it must be resolved.</span><a href="#l420"></a>
<span id="l421">     *</span><a href="#l421"></a>
<span id="l422">     * &lt;p&gt; This method assigns a default {@link java.security.ProtectionDomain</span><a href="#l422"></a>
<span id="l423">     * &lt;tt&gt;ProtectionDomain&lt;/tt&gt;} to the newly defined class.  The</span><a href="#l423"></a>
<span id="l424">     * &lt;tt&gt;ProtectionDomain&lt;/tt&gt; is effectively granted the same set of</span><a href="#l424"></a>
<span id="l425">     * permissions returned when {@link</span><a href="#l425"></a>
<span id="l426">     * java.security.Policy#getPermissions(java.security.CodeSource)</span><a href="#l426"></a>
<span id="l427">     * &lt;tt&gt;Policy.getPolicy().getPermissions(new CodeSource(null, null))&lt;/tt&gt;}</span><a href="#l427"></a>
<span id="l428">     * is invoked.  The default domain is created on the first invocation of</span><a href="#l428"></a>
<span id="l429">     * {@link #defineClass(String, byte[], int, int) &lt;tt&gt;defineClass&lt;/tt&gt;},</span><a href="#l429"></a>
<span id="l430">     * and re-used on subsequent invocations.</span><a href="#l430"></a>
<span id="l431">     *</span><a href="#l431"></a>
<span id="l432">     * &lt;p&gt; To assign a specific &lt;tt&gt;ProtectionDomain&lt;/tt&gt; to the class, use</span><a href="#l432"></a>
<span id="l433">     * the {@link #defineClass(String, byte[], int, int,</span><a href="#l433"></a>
<span id="l434">     * java.security.ProtectionDomain) &lt;tt&gt;defineClass&lt;/tt&gt;} method that takes a</span><a href="#l434"></a>
<span id="l435">     * &lt;tt&gt;ProtectionDomain&lt;/tt&gt; as one of its arguments.  &lt;/p&gt;</span><a href="#l435"></a>
<span id="l436">     *</span><a href="#l436"></a>
<span id="l437">     * @param  name</span><a href="#l437"></a>
<span id="l438">     *         The expected &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class, or</span><a href="#l438"></a>
<span id="l439">     *         &lt;tt&gt;null&lt;/tt&gt; if not known</span><a href="#l439"></a>
<span id="l440">     *</span><a href="#l440"></a>
<span id="l441">     * @param  b</span><a href="#l441"></a>
<span id="l442">     *         The bytes that make up the class data.  The bytes in positions</span><a href="#l442"></a>
<span id="l443">     *         &lt;tt&gt;off&lt;/tt&gt; through &lt;tt&gt;off+len-1&lt;/tt&gt; should have the format</span><a href="#l443"></a>
<span id="l444">     *         of a valid class file as defined by the &lt;a</span><a href="#l444"></a>
<span id="l445">     *         href=&quot;http://java.sun.com/docs/books/vmspec/&quot;&gt;Java Virtual</span><a href="#l445"></a>
<span id="l446">     *         Machine Specification&lt;/a&gt;.</span><a href="#l446"></a>
<span id="l447">     *</span><a href="#l447"></a>
<span id="l448">     * @param  off</span><a href="#l448"></a>
<span id="l449">     *         The start offset in &lt;tt&gt;b&lt;/tt&gt; of the class data</span><a href="#l449"></a>
<span id="l450">     *</span><a href="#l450"></a>
<span id="l451">     * @param  len</span><a href="#l451"></a>
<span id="l452">     *         The length of the class data</span><a href="#l452"></a>
<span id="l453">     *</span><a href="#l453"></a>
<span id="l454">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object that was created from the specified</span><a href="#l454"></a>
<span id="l455">     *          class data.</span><a href="#l455"></a>
<span id="l456">     *</span><a href="#l456"></a>
<span id="l457">     * @throws  ClassFormatError</span><a href="#l457"></a>
<span id="l458">     *          If the data did not contain a valid class</span><a href="#l458"></a>
<span id="l459">     *</span><a href="#l459"></a>
<span id="l460">     * @throws  IndexOutOfBoundsException</span><a href="#l460"></a>
<span id="l461">     *          If either &lt;tt&gt;off&lt;/tt&gt; or &lt;tt&gt;len&lt;/tt&gt; is negative, or if</span><a href="#l461"></a>
<span id="l462">     *          &lt;tt&gt;off+len&lt;/tt&gt; is greater than &lt;tt&gt;b.length&lt;/tt&gt;.</span><a href="#l462"></a>
<span id="l463">     *</span><a href="#l463"></a>
<span id="l464">     * @throws  SecurityException</span><a href="#l464"></a>
<span id="l465">     *          If an attempt is made to add this class to a package that</span><a href="#l465"></a>
<span id="l466">     *          contains classes that were signed by a different set of</span><a href="#l466"></a>
<span id="l467">     *          certificates than this class (which is unsigned), or if</span><a href="#l467"></a>
<span id="l468">     *          &lt;tt&gt;name&lt;/tt&gt; begins with &quot;&lt;tt&gt;java.&lt;/tt&gt;&quot;.</span><a href="#l468"></a>
<span id="l469">     *</span><a href="#l469"></a>
<span id="l470">     * @see  #loadClass(String, boolean)</span><a href="#l470"></a>
<span id="l471">     * @see  #resolveClass(Class)</span><a href="#l471"></a>
<span id="l472">     * @see  java.security.CodeSource</span><a href="#l472"></a>
<span id="l473">     * @see  java.security.SecureClassLoader</span><a href="#l473"></a>
<span id="l474">     *</span><a href="#l474"></a>
<span id="l475">     * @since  1.1</span><a href="#l475"></a>
<span id="l476">     */</span><a href="#l476"></a>
<span id="l477">    protected final Class&lt;?&gt; defineClass(String name, byte[] b, int off, int len)</span><a href="#l477"></a>
<span id="l478">        throws ClassFormatError</span><a href="#l478"></a>
<span id="l479">    {</span><a href="#l479"></a>
<span id="l480">        return defineClass(name, b, off, len, null);</span><a href="#l480"></a>
<span id="l481">    }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">    /* Determine protection domain, and check that:</span><a href="#l483"></a>
<span id="l484">        - not define java.* class,</span><a href="#l484"></a>
<span id="l485">        - signer of this class matches signers for the rest of the classes in package.</span><a href="#l485"></a>
<span id="l486">    */</span><a href="#l486"></a>
<span id="l487">    private ProtectionDomain preDefineClass(String name,</span><a href="#l487"></a>
<span id="l488">                                            ProtectionDomain protectionDomain)</span><a href="#l488"></a>
<span id="l489">    {</span><a href="#l489"></a>
<span id="l490">        if (!checkName(name))</span><a href="#l490"></a>
<span id="l491">            throw new NoClassDefFoundError(&quot;IllegalName: &quot; + name);</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">        if ((name != null) &amp;&amp; name.startsWith(&quot;java.&quot;)) {</span><a href="#l493"></a>
<span id="l494">            throw new SecurityException(&quot;Prohibited package name: &quot; +</span><a href="#l494"></a>
<span id="l495">                                        name.substring(0, name.lastIndexOf('.')));</span><a href="#l495"></a>
<span id="l496">        }</span><a href="#l496"></a>
<span id="l497">        if (protectionDomain == null) {</span><a href="#l497"></a>
<span id="l498">            protectionDomain = getDefaultDomain();</span><a href="#l498"></a>
<span id="l499">        }</span><a href="#l499"></a>
<span id="l500"></span><a href="#l500"></a>
<span id="l501">        if (name != null)</span><a href="#l501"></a>
<span id="l502">            checkCerts(name, protectionDomain.getCodeSource());</span><a href="#l502"></a>
<span id="l503"></span><a href="#l503"></a>
<span id="l504">        return protectionDomain;</span><a href="#l504"></a>
<span id="l505">    }</span><a href="#l505"></a>
<span id="l506"></span><a href="#l506"></a>
<span id="l507">    private String defineClassSourceLocation(ProtectionDomain protectionDomain)</span><a href="#l507"></a>
<span id="l508">    {</span><a href="#l508"></a>
<span id="l509">        CodeSource cs = protectionDomain.getCodeSource();</span><a href="#l509"></a>
<span id="l510">        String source = null;</span><a href="#l510"></a>
<span id="l511">        if (cs != null &amp;&amp; cs.getLocation() != null) {</span><a href="#l511"></a>
<span id="l512">            source = cs.getLocation().toString();</span><a href="#l512"></a>
<span id="l513">        }</span><a href="#l513"></a>
<span id="l514">        return source;</span><a href="#l514"></a>
<span id="l515">    }</span><a href="#l515"></a>
<span id="l516"></span><a href="#l516"></a>
<span id="l517">    private Class defineTransformedClass(String name, byte[] b, int off, int len,</span><a href="#l517"></a>
<span id="l518">                                         ProtectionDomain protectionDomain,</span><a href="#l518"></a>
<span id="l519">                                         ClassFormatError cfe, String source)</span><a href="#l519"></a>
<span id="l520">      throws ClassFormatError</span><a href="#l520"></a>
<span id="l521">    {</span><a href="#l521"></a>
<span id="l522">        // Class format error - try to transform the bytecode and</span><a href="#l522"></a>
<span id="l523">        // define the class again</span><a href="#l523"></a>
<span id="l524">        //</span><a href="#l524"></a>
<span id="l525">        Object[] transformers = ClassFileTransformer.getTransformers();</span><a href="#l525"></a>
<span id="l526">        Class c = null;</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">        for (int i = 0; transformers != null &amp;&amp; i &lt; transformers.length; i++) {</span><a href="#l528"></a>
<span id="l529">            try {</span><a href="#l529"></a>
<span id="l530">              // Transform byte code using transformer</span><a href="#l530"></a>
<span id="l531">              byte[] tb = ((ClassFileTransformer) transformers[i]).transform(b, off, len);</span><a href="#l531"></a>
<span id="l532">              c = defineClass1(name, tb, 0, tb.length, protectionDomain, source);</span><a href="#l532"></a>
<span id="l533">              break;</span><a href="#l533"></a>
<span id="l534">            } catch (ClassFormatError cfe2)     {</span><a href="#l534"></a>
<span id="l535">              // If ClassFormatError occurs, try next transformer</span><a href="#l535"></a>
<span id="l536">            }</span><a href="#l536"></a>
<span id="l537">        }</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">        // Rethrow original ClassFormatError if unable to transform</span><a href="#l539"></a>
<span id="l540">        // bytecode to well-formed</span><a href="#l540"></a>
<span id="l541">        //</span><a href="#l541"></a>
<span id="l542">        if (c == null)</span><a href="#l542"></a>
<span id="l543">            throw cfe;</span><a href="#l543"></a>
<span id="l544"></span><a href="#l544"></a>
<span id="l545">        return c;</span><a href="#l545"></a>
<span id="l546">    }</span><a href="#l546"></a>
<span id="l547"></span><a href="#l547"></a>
<span id="l548">    private void postDefineClass(Class c, ProtectionDomain protectionDomain)</span><a href="#l548"></a>
<span id="l549">    {</span><a href="#l549"></a>
<span id="l550">        if (protectionDomain.getCodeSource() != null) {</span><a href="#l550"></a>
<span id="l551">            java.security.cert.Certificate certs[] =</span><a href="#l551"></a>
<span id="l552">                protectionDomain.getCodeSource().getCertificates();</span><a href="#l552"></a>
<span id="l553">            if (certs != null)</span><a href="#l553"></a>
<span id="l554">                setSigners(c, certs);</span><a href="#l554"></a>
<span id="l555">        }</span><a href="#l555"></a>
<span id="l556">    }</span><a href="#l556"></a>
<span id="l557"></span><a href="#l557"></a>
<span id="l558">    /**</span><a href="#l558"></a>
<span id="l559">     * Converts an array of bytes into an instance of class &lt;tt&gt;Class&lt;/tt&gt;,</span><a href="#l559"></a>
<span id="l560">     * with an optional &lt;tt&gt;ProtectionDomain&lt;/tt&gt;.  If the domain is</span><a href="#l560"></a>
<span id="l561">     * &lt;tt&gt;null&lt;/tt&gt;, then a default domain will be assigned to the class as</span><a href="#l561"></a>
<span id="l562">     * specified in the documentation for {@link #defineClass(String, byte[],</span><a href="#l562"></a>
<span id="l563">     * int, int)}.  Before the class can be used it must be resolved.</span><a href="#l563"></a>
<span id="l564">     *</span><a href="#l564"></a>
<span id="l565">     * &lt;p&gt; The first class defined in a package determines the exact set of</span><a href="#l565"></a>
<span id="l566">     * certificates that all subsequent classes defined in that package must</span><a href="#l566"></a>
<span id="l567">     * contain.  The set of certificates for a class is obtained from the</span><a href="#l567"></a>
<span id="l568">     * {@link java.security.CodeSource &lt;tt&gt;CodeSource&lt;/tt&gt;} within the</span><a href="#l568"></a>
<span id="l569">     * &lt;tt&gt;ProtectionDomain&lt;/tt&gt; of the class.  Any classes added to that</span><a href="#l569"></a>
<span id="l570">     * package must contain the same set of certificates or a</span><a href="#l570"></a>
<span id="l571">     * &lt;tt&gt;SecurityException&lt;/tt&gt; will be thrown.  Note that if</span><a href="#l571"></a>
<span id="l572">     * &lt;tt&gt;name&lt;/tt&gt; is &lt;tt&gt;null&lt;/tt&gt;, this check is not performed.</span><a href="#l572"></a>
<span id="l573">     * You should always pass in the &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the</span><a href="#l573"></a>
<span id="l574">     * class you are defining as well as the bytes.  This ensures that the</span><a href="#l574"></a>
<span id="l575">     * class you are defining is indeed the class you think it is.</span><a href="#l575"></a>
<span id="l576">     *</span><a href="#l576"></a>
<span id="l577">     * &lt;p&gt; The specified &lt;tt&gt;name&lt;/tt&gt; cannot begin with &quot;&lt;tt&gt;java.&lt;/tt&gt;&quot;, since</span><a href="#l577"></a>
<span id="l578">     * all classes in the &quot;&lt;tt&gt;java.*&lt;/tt&gt; packages can only be defined by the</span><a href="#l578"></a>
<span id="l579">     * bootstrap class loader.  If &lt;tt&gt;name&lt;/tt&gt; is not &lt;tt&gt;null&lt;/tt&gt;, it</span><a href="#l579"></a>
<span id="l580">     * must be equal to the &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l580"></a>
<span id="l581">     * specified by the byte array &quot;&lt;tt&gt;b&lt;/tt&gt;&quot;, otherwise a {@link</span><a href="#l581"></a>
<span id="l582">     * &lt;tt&gt;NoClassDefFoundError&lt;/tt&gt;} will be thrown.  &lt;/p&gt;</span><a href="#l582"></a>
<span id="l583">     *</span><a href="#l583"></a>
<span id="l584">     * @param  name</span><a href="#l584"></a>
<span id="l585">     *         The expected &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class, or</span><a href="#l585"></a>
<span id="l586">     *         &lt;tt&gt;null&lt;/tt&gt; if not known</span><a href="#l586"></a>
<span id="l587">     *</span><a href="#l587"></a>
<span id="l588">     * @param  b</span><a href="#l588"></a>
<span id="l589">     *         The bytes that make up the class data. The bytes in positions</span><a href="#l589"></a>
<span id="l590">     *         &lt;tt&gt;off&lt;/tt&gt; through &lt;tt&gt;off+len-1&lt;/tt&gt; should have the format</span><a href="#l590"></a>
<span id="l591">     *         of a valid class file as defined by the &lt;a</span><a href="#l591"></a>
<span id="l592">     *         href=&quot;http://java.sun.com/docs/books/vmspec/&quot;&gt;Java Virtual</span><a href="#l592"></a>
<span id="l593">     *         Machine Specification&lt;/a&gt;.</span><a href="#l593"></a>
<span id="l594">     *</span><a href="#l594"></a>
<span id="l595">     * @param  off</span><a href="#l595"></a>
<span id="l596">     *         The start offset in &lt;tt&gt;b&lt;/tt&gt; of the class data</span><a href="#l596"></a>
<span id="l597">     *</span><a href="#l597"></a>
<span id="l598">     * @param  len</span><a href="#l598"></a>
<span id="l599">     *         The length of the class data</span><a href="#l599"></a>
<span id="l600">     *</span><a href="#l600"></a>
<span id="l601">     * @param  protectionDomain</span><a href="#l601"></a>
<span id="l602">     *         The ProtectionDomain of the class</span><a href="#l602"></a>
<span id="l603">     *</span><a href="#l603"></a>
<span id="l604">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object created from the data,</span><a href="#l604"></a>
<span id="l605">     *          and optional &lt;tt&gt;ProtectionDomain&lt;/tt&gt;.</span><a href="#l605"></a>
<span id="l606">     *</span><a href="#l606"></a>
<span id="l607">     * @throws  ClassFormatError</span><a href="#l607"></a>
<span id="l608">     *          If the data did not contain a valid class</span><a href="#l608"></a>
<span id="l609">     *</span><a href="#l609"></a>
<span id="l610">     * @throws  NoClassDefFoundError</span><a href="#l610"></a>
<span id="l611">     *          If &lt;tt&gt;name&lt;/tt&gt; is not equal to the &lt;a href=&quot;#name&quot;&gt;binary</span><a href="#l611"></a>
<span id="l612">     *          name&lt;/a&gt; of the class specified by &lt;tt&gt;b&lt;/tt&gt;</span><a href="#l612"></a>
<span id="l613">     *</span><a href="#l613"></a>
<span id="l614">     * @throws  IndexOutOfBoundsException</span><a href="#l614"></a>
<span id="l615">     *          If either &lt;tt&gt;off&lt;/tt&gt; or &lt;tt&gt;len&lt;/tt&gt; is negative, or if</span><a href="#l615"></a>
<span id="l616">     *          &lt;tt&gt;off+len&lt;/tt&gt; is greater than &lt;tt&gt;b.length&lt;/tt&gt;.</span><a href="#l616"></a>
<span id="l617">     *</span><a href="#l617"></a>
<span id="l618">     * @throws  SecurityException</span><a href="#l618"></a>
<span id="l619">     *          If an attempt is made to add this class to a package that</span><a href="#l619"></a>
<span id="l620">     *          contains classes that were signed by a different set of</span><a href="#l620"></a>
<span id="l621">     *          certificates than this class, or if &lt;tt&gt;name&lt;/tt&gt; begins with</span><a href="#l621"></a>
<span id="l622">     *          &quot;&lt;tt&gt;java.&lt;/tt&gt;&quot;.</span><a href="#l622"></a>
<span id="l623">     */</span><a href="#l623"></a>
<span id="l624">    protected final Class&lt;?&gt; defineClass(String name, byte[] b, int off, int len,</span><a href="#l624"></a>
<span id="l625">                                         ProtectionDomain protectionDomain)</span><a href="#l625"></a>
<span id="l626">        throws ClassFormatError</span><a href="#l626"></a>
<span id="l627">    {</span><a href="#l627"></a>
<span id="l628">        protectionDomain = preDefineClass(name, protectionDomain);</span><a href="#l628"></a>
<span id="l629"></span><a href="#l629"></a>
<span id="l630">        Class c = null;</span><a href="#l630"></a>
<span id="l631">        String source = defineClassSourceLocation(protectionDomain);</span><a href="#l631"></a>
<span id="l632"></span><a href="#l632"></a>
<span id="l633">        try {</span><a href="#l633"></a>
<span id="l634">            c = defineClass1(name, b, off, len, protectionDomain, source);</span><a href="#l634"></a>
<span id="l635">        } catch (ClassFormatError cfe) {</span><a href="#l635"></a>
<span id="l636">            c = defineTransformedClass(name, b, off, len, protectionDomain, cfe, source);</span><a href="#l636"></a>
<span id="l637">        }</span><a href="#l637"></a>
<span id="l638"></span><a href="#l638"></a>
<span id="l639">        postDefineClass(c, protectionDomain);</span><a href="#l639"></a>
<span id="l640">        return c;</span><a href="#l640"></a>
<span id="l641">    }</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">    /**</span><a href="#l643"></a>
<span id="l644">     * Converts a {@link java.nio.ByteBuffer &lt;tt&gt;ByteBuffer&lt;/tt&gt;}</span><a href="#l644"></a>
<span id="l645">     * into an instance of class &lt;tt&gt;Class&lt;/tt&gt;,</span><a href="#l645"></a>
<span id="l646">     * with an optional &lt;tt&gt;ProtectionDomain&lt;/tt&gt;.  If the domain is</span><a href="#l646"></a>
<span id="l647">     * &lt;tt&gt;null&lt;/tt&gt;, then a default domain will be assigned to the class as</span><a href="#l647"></a>
<span id="l648">     * specified in the documentation for {@link #defineClass(String, byte[],</span><a href="#l648"></a>
<span id="l649">     * int, int)}.  Before the class can be used it must be resolved.</span><a href="#l649"></a>
<span id="l650">     *</span><a href="#l650"></a>
<span id="l651">     * &lt;p&gt;The rules about the first class defined in a package determining the set of</span><a href="#l651"></a>
<span id="l652">     * certificates for the package, and the restrictions on class names are identical</span><a href="#l652"></a>
<span id="l653">     * to those specified in the documentation for {@link #defineClass(String, byte[],</span><a href="#l653"></a>
<span id="l654">     * int, int, ProtectionDomain)}.</span><a href="#l654"></a>
<span id="l655">     *</span><a href="#l655"></a>
<span id="l656">     * &lt;p&gt; An invocation of this method of the form</span><a href="#l656"></a>
<span id="l657">     * &lt;i&gt;cl&lt;/i&gt;&lt;tt&gt;.defineClass(&lt;/tt&gt;&lt;i&gt;name&lt;/i&gt;&lt;tt&gt;,&lt;/tt&gt;</span><a href="#l657"></a>
<span id="l658">     * &lt;i&gt;bBuffer&lt;/i&gt;&lt;tt&gt;,&lt;/tt&gt; &lt;i&gt;pd&lt;/i&gt;&lt;tt&gt;)&lt;/tt&gt; yields exactly the same</span><a href="#l658"></a>
<span id="l659">     * result as the statements</span><a href="#l659"></a>
<span id="l660">     *</span><a href="#l660"></a>
<span id="l661">     * &lt;blockquote&gt;&lt;tt&gt;</span><a href="#l661"></a>
<span id="l662">     * ...&lt;br&gt;</span><a href="#l662"></a>
<span id="l663">     * byte[] temp = new byte[&lt;/tt&gt;&lt;i&gt;bBuffer&lt;/i&gt;&lt;tt&gt;.{@link java.nio.ByteBuffer#remaining</span><a href="#l663"></a>
<span id="l664">     * remaining}()];&lt;br&gt;</span><a href="#l664"></a>
<span id="l665">     *     &lt;/tt&gt;&lt;i&gt;bBuffer&lt;/i&gt;&lt;tt&gt;.{@link java.nio.ByteBuffer#get(byte[])</span><a href="#l665"></a>
<span id="l666">     * get}(temp);&lt;br&gt;</span><a href="#l666"></a>
<span id="l667">     *     return {@link #defineClass(String, byte[], int, int, ProtectionDomain)</span><a href="#l667"></a>
<span id="l668">     * &lt;/tt&gt;&lt;i&gt;cl&lt;/i&gt;&lt;tt&gt;.defineClass}(&lt;/tt&gt;&lt;i&gt;name&lt;/i&gt;&lt;tt&gt;, temp, 0, temp.length, &lt;/tt&gt;&lt;i&gt;pd&lt;/i&gt;&lt;tt&gt;);&lt;br&gt;</span><a href="#l668"></a>
<span id="l669">     * &lt;/tt&gt;&lt;/blockquote&gt;</span><a href="#l669"></a>
<span id="l670">     *</span><a href="#l670"></a>
<span id="l671">     * @param  name</span><a href="#l671"></a>
<span id="l672">     *         The expected &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a. of the class, or</span><a href="#l672"></a>
<span id="l673">     *         &lt;tt&gt;null&lt;/tt&gt; if not known</span><a href="#l673"></a>
<span id="l674">     *</span><a href="#l674"></a>
<span id="l675">     * @param  b</span><a href="#l675"></a>
<span id="l676">     *         The bytes that make up the class data. The bytes from positions</span><a href="#l676"></a>
<span id="l677">     *         &lt;tt&gt;b.position()&lt;/tt&gt; through &lt;tt&gt;b.position() + b.limit() -1 &lt;/tt&gt;</span><a href="#l677"></a>
<span id="l678">     *         should have the format of a valid class file as defined by the &lt;a</span><a href="#l678"></a>
<span id="l679">     *         href=&quot;http://java.sun.com/docs/books/vmspec/&quot;&gt;Java Virtual</span><a href="#l679"></a>
<span id="l680">     *         Machine Specification&lt;/a&gt;.</span><a href="#l680"></a>
<span id="l681">     *</span><a href="#l681"></a>
<span id="l682">     * @param  protectionDomain</span><a href="#l682"></a>
<span id="l683">     *         The ProtectionDomain of the class, or &lt;tt&gt;null&lt;/tt&gt;.</span><a href="#l683"></a>
<span id="l684">     *</span><a href="#l684"></a>
<span id="l685">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object created from the data,</span><a href="#l685"></a>
<span id="l686">     *          and optional &lt;tt&gt;ProtectionDomain&lt;/tt&gt;.</span><a href="#l686"></a>
<span id="l687">     *</span><a href="#l687"></a>
<span id="l688">     * @throws  ClassFormatError</span><a href="#l688"></a>
<span id="l689">     *          If the data did not contain a valid class.</span><a href="#l689"></a>
<span id="l690">     *</span><a href="#l690"></a>
<span id="l691">     * @throws  NoClassDefFoundError</span><a href="#l691"></a>
<span id="l692">     *          If &lt;tt&gt;name&lt;/tt&gt; is not equal to the &lt;a href=&quot;#name&quot;&gt;binary</span><a href="#l692"></a>
<span id="l693">     *          name&lt;/a&gt; of the class specified by &lt;tt&gt;b&lt;/tt&gt;</span><a href="#l693"></a>
<span id="l694">     *</span><a href="#l694"></a>
<span id="l695">     * @throws  SecurityException</span><a href="#l695"></a>
<span id="l696">     *          If an attempt is made to add this class to a package that</span><a href="#l696"></a>
<span id="l697">     *          contains classes that were signed by a different set of</span><a href="#l697"></a>
<span id="l698">     *          certificates than this class, or if &lt;tt&gt;name&lt;/tt&gt; begins with</span><a href="#l698"></a>
<span id="l699">     *          &quot;&lt;tt&gt;java.&lt;/tt&gt;&quot;.</span><a href="#l699"></a>
<span id="l700">     *</span><a href="#l700"></a>
<span id="l701">     * @see      #defineClass(String, byte[], int, int, ProtectionDomain)</span><a href="#l701"></a>
<span id="l702">     *</span><a href="#l702"></a>
<span id="l703">     * @since  1.5</span><a href="#l703"></a>
<span id="l704">     */</span><a href="#l704"></a>
<span id="l705">    protected final Class&lt;?&gt; defineClass(String name, java.nio.ByteBuffer b,</span><a href="#l705"></a>
<span id="l706">                                         ProtectionDomain protectionDomain)</span><a href="#l706"></a>
<span id="l707">        throws ClassFormatError</span><a href="#l707"></a>
<span id="l708">    {</span><a href="#l708"></a>
<span id="l709">        int len = b.remaining();</span><a href="#l709"></a>
<span id="l710"></span><a href="#l710"></a>
<span id="l711">        // Use byte[] if not a direct ByteBufer:</span><a href="#l711"></a>
<span id="l712">        if (!b.isDirect()) {</span><a href="#l712"></a>
<span id="l713">            if (b.hasArray()) {</span><a href="#l713"></a>
<span id="l714">                return defineClass(name, b.array(),</span><a href="#l714"></a>
<span id="l715">                                   b.position() + b.arrayOffset(), len,</span><a href="#l715"></a>
<span id="l716">                                   protectionDomain);</span><a href="#l716"></a>
<span id="l717">            } else {</span><a href="#l717"></a>
<span id="l718">                // no array, or read-only array</span><a href="#l718"></a>
<span id="l719">                byte[] tb = new byte[len];</span><a href="#l719"></a>
<span id="l720">                b.get(tb);  // get bytes out of byte buffer.</span><a href="#l720"></a>
<span id="l721">                return defineClass(name, tb, 0, len, protectionDomain);</span><a href="#l721"></a>
<span id="l722">            }</span><a href="#l722"></a>
<span id="l723">        }</span><a href="#l723"></a>
<span id="l724"></span><a href="#l724"></a>
<span id="l725">        protectionDomain = preDefineClass(name, protectionDomain);</span><a href="#l725"></a>
<span id="l726"></span><a href="#l726"></a>
<span id="l727">        Class c = null;</span><a href="#l727"></a>
<span id="l728">        String source = defineClassSourceLocation(protectionDomain);</span><a href="#l728"></a>
<span id="l729"></span><a href="#l729"></a>
<span id="l730">        try {</span><a href="#l730"></a>
<span id="l731">            c = defineClass2(name, b, b.position(), len, protectionDomain, source);</span><a href="#l731"></a>
<span id="l732">        } catch (ClassFormatError cfe) {</span><a href="#l732"></a>
<span id="l733">            byte[] tb = new byte[len];</span><a href="#l733"></a>
<span id="l734">            b.get(tb);  // get bytes out of byte buffer.</span><a href="#l734"></a>
<span id="l735">            c = defineTransformedClass(name, tb, 0, len, protectionDomain, cfe, source);</span><a href="#l735"></a>
<span id="l736">        }</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">        postDefineClass(c, protectionDomain);</span><a href="#l738"></a>
<span id="l739">        return c;</span><a href="#l739"></a>
<span id="l740">    }</span><a href="#l740"></a>
<span id="l741"></span><a href="#l741"></a>
<span id="l742">    private native Class defineClass0(String name, byte[] b, int off, int len,</span><a href="#l742"></a>
<span id="l743">                                      ProtectionDomain pd);</span><a href="#l743"></a>
<span id="l744"></span><a href="#l744"></a>
<span id="l745">    private native Class defineClass1(String name, byte[] b, int off, int len,</span><a href="#l745"></a>
<span id="l746">                                      ProtectionDomain pd, String source);</span><a href="#l746"></a>
<span id="l747"></span><a href="#l747"></a>
<span id="l748">    private native Class defineClass2(String name, java.nio.ByteBuffer b,</span><a href="#l748"></a>
<span id="l749">                                      int off, int len, ProtectionDomain pd,</span><a href="#l749"></a>
<span id="l750">                                      String source);</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">    // true if the name is null or has the potential to be a valid binary name</span><a href="#l752"></a>
<span id="l753">    private boolean checkName(String name) {</span><a href="#l753"></a>
<span id="l754">        if ((name == null) || (name.length() == 0))</span><a href="#l754"></a>
<span id="l755">            return true;</span><a href="#l755"></a>
<span id="l756">        if ((name.indexOf('/') != -1)</span><a href="#l756"></a>
<span id="l757">            || (!VM.allowArraySyntax() &amp;&amp; (name.charAt(0) == '[')))</span><a href="#l757"></a>
<span id="l758">            return false;</span><a href="#l758"></a>
<span id="l759">        return true;</span><a href="#l759"></a>
<span id="l760">    }</span><a href="#l760"></a>
<span id="l761"></span><a href="#l761"></a>
<span id="l762">    private synchronized void checkCerts(String name, CodeSource cs) {</span><a href="#l762"></a>
<span id="l763">        int i = name.lastIndexOf('.');</span><a href="#l763"></a>
<span id="l764">        String pname = (i == -1) ? &quot;&quot; : name.substring(0, i);</span><a href="#l764"></a>
<span id="l765">        java.security.cert.Certificate[] pcerts =</span><a href="#l765"></a>
<span id="l766">            (java.security.cert.Certificate[]) package2certs.get(pname);</span><a href="#l766"></a>
<span id="l767">        if (pcerts == null) {</span><a href="#l767"></a>
<span id="l768">            // first class in this package gets to define which</span><a href="#l768"></a>
<span id="l769">            // certificates must be the same for all other classes</span><a href="#l769"></a>
<span id="l770">            // in this package</span><a href="#l770"></a>
<span id="l771">            if (cs != null) {</span><a href="#l771"></a>
<span id="l772">                pcerts = cs.getCertificates();</span><a href="#l772"></a>
<span id="l773">            }</span><a href="#l773"></a>
<span id="l774">            if (pcerts == null) {</span><a href="#l774"></a>
<span id="l775">                if (nocerts == null)</span><a href="#l775"></a>
<span id="l776">                    nocerts = new java.security.cert.Certificate[0];</span><a href="#l776"></a>
<span id="l777">                pcerts = nocerts;</span><a href="#l777"></a>
<span id="l778">            }</span><a href="#l778"></a>
<span id="l779">            package2certs.put(pname, pcerts);</span><a href="#l779"></a>
<span id="l780">        } else {</span><a href="#l780"></a>
<span id="l781">            java.security.cert.Certificate[] certs = null;</span><a href="#l781"></a>
<span id="l782">            if (cs != null) {</span><a href="#l782"></a>
<span id="l783">                certs = cs.getCertificates();</span><a href="#l783"></a>
<span id="l784">            }</span><a href="#l784"></a>
<span id="l785"></span><a href="#l785"></a>
<span id="l786">            if (!compareCerts(pcerts, certs)) {</span><a href="#l786"></a>
<span id="l787">                throw new SecurityException(&quot;class \&quot;&quot;+ name +</span><a href="#l787"></a>
<span id="l788">                                            &quot;\&quot;'s signer information does not match signer information of other classes in the same package&quot;);</span><a href="#l788"></a>
<span id="l789">            }</span><a href="#l789"></a>
<span id="l790">        }</span><a href="#l790"></a>
<span id="l791">    }</span><a href="#l791"></a>
<span id="l792"></span><a href="#l792"></a>
<span id="l793">    /**</span><a href="#l793"></a>
<span id="l794">     * check to make sure the certs for the new class (certs) are the same as</span><a href="#l794"></a>
<span id="l795">     * the certs for the first class inserted in the package (pcerts)</span><a href="#l795"></a>
<span id="l796">     */</span><a href="#l796"></a>
<span id="l797">    private boolean compareCerts(java.security.cert.Certificate[] pcerts,</span><a href="#l797"></a>
<span id="l798">                                 java.security.cert.Certificate[] certs)</span><a href="#l798"></a>
<span id="l799">    {</span><a href="#l799"></a>
<span id="l800">        // certs can be null, indicating no certs.</span><a href="#l800"></a>
<span id="l801">        if ((certs == null) || (certs.length == 0)) {</span><a href="#l801"></a>
<span id="l802">            return pcerts.length == 0;</span><a href="#l802"></a>
<span id="l803">        }</span><a href="#l803"></a>
<span id="l804"></span><a href="#l804"></a>
<span id="l805">        // the length must be the same at this point</span><a href="#l805"></a>
<span id="l806">        if (certs.length != pcerts.length)</span><a href="#l806"></a>
<span id="l807">            return false;</span><a href="#l807"></a>
<span id="l808"></span><a href="#l808"></a>
<span id="l809">        // go through and make sure all the certs in one array</span><a href="#l809"></a>
<span id="l810">        // are in the other and vice-versa.</span><a href="#l810"></a>
<span id="l811">        boolean match;</span><a href="#l811"></a>
<span id="l812">        for (int i = 0; i &lt; certs.length; i++) {</span><a href="#l812"></a>
<span id="l813">            match = false;</span><a href="#l813"></a>
<span id="l814">            for (int j = 0; j &lt; pcerts.length; j++) {</span><a href="#l814"></a>
<span id="l815">                if (certs[i].equals(pcerts[j])) {</span><a href="#l815"></a>
<span id="l816">                    match = true;</span><a href="#l816"></a>
<span id="l817">                    break;</span><a href="#l817"></a>
<span id="l818">                }</span><a href="#l818"></a>
<span id="l819">            }</span><a href="#l819"></a>
<span id="l820">            if (!match) return false;</span><a href="#l820"></a>
<span id="l821">        }</span><a href="#l821"></a>
<span id="l822"></span><a href="#l822"></a>
<span id="l823">        // now do the same for pcerts</span><a href="#l823"></a>
<span id="l824">        for (int i = 0; i &lt; pcerts.length; i++) {</span><a href="#l824"></a>
<span id="l825">            match = false;</span><a href="#l825"></a>
<span id="l826">            for (int j = 0; j &lt; certs.length; j++) {</span><a href="#l826"></a>
<span id="l827">                if (pcerts[i].equals(certs[j])) {</span><a href="#l827"></a>
<span id="l828">                    match = true;</span><a href="#l828"></a>
<span id="l829">                    break;</span><a href="#l829"></a>
<span id="l830">                }</span><a href="#l830"></a>
<span id="l831">            }</span><a href="#l831"></a>
<span id="l832">            if (!match) return false;</span><a href="#l832"></a>
<span id="l833">        }</span><a href="#l833"></a>
<span id="l834"></span><a href="#l834"></a>
<span id="l835">        return true;</span><a href="#l835"></a>
<span id="l836">    }</span><a href="#l836"></a>
<span id="l837"></span><a href="#l837"></a>
<span id="l838">    /**</span><a href="#l838"></a>
<span id="l839">     * Links the specified class.  This (misleadingly named) method may be</span><a href="#l839"></a>
<span id="l840">     * used by a class loader to link a class.  If the class &lt;tt&gt;c&lt;/tt&gt; has</span><a href="#l840"></a>
<span id="l841">     * already been linked, then this method simply returns. Otherwise, the</span><a href="#l841"></a>
<span id="l842">     * class is linked as described in the &quot;Execution&quot; chapter of the &lt;a</span><a href="#l842"></a>
<span id="l843">     * href=&quot;http://java.sun.com/docs/books/jls/&quot;&gt;Java Language</span><a href="#l843"></a>
<span id="l844">     * Specification&lt;/a&gt;.</span><a href="#l844"></a>
<span id="l845">     * &lt;/p&gt;</span><a href="#l845"></a>
<span id="l846">     *</span><a href="#l846"></a>
<span id="l847">     * @param  c</span><a href="#l847"></a>
<span id="l848">     *         The class to link</span><a href="#l848"></a>
<span id="l849">     *</span><a href="#l849"></a>
<span id="l850">     * @throws  NullPointerException</span><a href="#l850"></a>
<span id="l851">     *          If &lt;tt&gt;c&lt;/tt&gt; is &lt;tt&gt;null&lt;/tt&gt;.</span><a href="#l851"></a>
<span id="l852">     *</span><a href="#l852"></a>
<span id="l853">     * @see  #defineClass(String, byte[], int, int)</span><a href="#l853"></a>
<span id="l854">     */</span><a href="#l854"></a>
<span id="l855">    protected final void resolveClass(Class&lt;?&gt; c) {</span><a href="#l855"></a>
<span id="l856">        resolveClass0(c);</span><a href="#l856"></a>
<span id="l857">    }</span><a href="#l857"></a>
<span id="l858"></span><a href="#l858"></a>
<span id="l859">    private native void resolveClass0(Class c);</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">    /**</span><a href="#l861"></a>
<span id="l862">     * Finds a class with the specified &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt;,</span><a href="#l862"></a>
<span id="l863">     * loading it if necessary.</span><a href="#l863"></a>
<span id="l864">     *</span><a href="#l864"></a>
<span id="l865">     * &lt;p&gt; This method loads the class through the system class loader (see</span><a href="#l865"></a>
<span id="l866">     * {@link #getSystemClassLoader()}).  The &lt;tt&gt;Class&lt;/tt&gt; object returned</span><a href="#l866"></a>
<span id="l867">     * might have more than one &lt;tt&gt;ClassLoader&lt;/tt&gt; associated with it.</span><a href="#l867"></a>
<span id="l868">     * Subclasses of &lt;tt&gt;ClassLoader&lt;/tt&gt; need not usually invoke this method,</span><a href="#l868"></a>
<span id="l869">     * because most class loaders need to override just {@link</span><a href="#l869"></a>
<span id="l870">     * #findClass(String)}.  &lt;/p&gt;</span><a href="#l870"></a>
<span id="l871">     *</span><a href="#l871"></a>
<span id="l872">     * @param  name</span><a href="#l872"></a>
<span id="l873">     *         The &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l873"></a>
<span id="l874">     *</span><a href="#l874"></a>
<span id="l875">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object for the specified &lt;tt&gt;name&lt;/tt&gt;</span><a href="#l875"></a>
<span id="l876">     *</span><a href="#l876"></a>
<span id="l877">     * @throws  ClassNotFoundException</span><a href="#l877"></a>
<span id="l878">     *          If the class could not be found</span><a href="#l878"></a>
<span id="l879">     *</span><a href="#l879"></a>
<span id="l880">     * @see  #ClassLoader(ClassLoader)</span><a href="#l880"></a>
<span id="l881">     * @see  #getParent()</span><a href="#l881"></a>
<span id="l882">     */</span><a href="#l882"></a>
<span id="l883">    protected final Class&lt;?&gt; findSystemClass(String name)</span><a href="#l883"></a>
<span id="l884">        throws ClassNotFoundException</span><a href="#l884"></a>
<span id="l885">    {</span><a href="#l885"></a>
<span id="l886">        ClassLoader system = getSystemClassLoader();</span><a href="#l886"></a>
<span id="l887">        if (system == null) {</span><a href="#l887"></a>
<span id="l888">            if (!checkName(name))</span><a href="#l888"></a>
<span id="l889">                throw new ClassNotFoundException(name);</span><a href="#l889"></a>
<span id="l890">            return findBootstrapClass(name);</span><a href="#l890"></a>
<span id="l891">        }</span><a href="#l891"></a>
<span id="l892">        return system.loadClass(name);</span><a href="#l892"></a>
<span id="l893">    }</span><a href="#l893"></a>
<span id="l894"></span><a href="#l894"></a>
<span id="l895">    private Class findBootstrapClass0(String name)</span><a href="#l895"></a>
<span id="l896">        throws ClassNotFoundException</span><a href="#l896"></a>
<span id="l897">    {</span><a href="#l897"></a>
<span id="l898">        if (!checkName(name))</span><a href="#l898"></a>
<span id="l899">            throw new ClassNotFoundException(name);</span><a href="#l899"></a>
<span id="l900">        return findBootstrapClass(name);</span><a href="#l900"></a>
<span id="l901">    }</span><a href="#l901"></a>
<span id="l902"></span><a href="#l902"></a>
<span id="l903">    private native Class findBootstrapClass(String name)</span><a href="#l903"></a>
<span id="l904">        throws ClassNotFoundException;</span><a href="#l904"></a>
<span id="l905"></span><a href="#l905"></a>
<span id="l906">    /**</span><a href="#l906"></a>
<span id="l907">     * Returns the class with the given &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; if this</span><a href="#l907"></a>
<span id="l908">     * loader has been recorded by the Java virtual machine as an initiating</span><a href="#l908"></a>
<span id="l909">     * loader of a class with that &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt;.  Otherwise</span><a href="#l909"></a>
<span id="l910">     * &lt;tt&gt;null&lt;/tt&gt; is returned.  &lt;/p&gt;</span><a href="#l910"></a>
<span id="l911">     *</span><a href="#l911"></a>
<span id="l912">     * @param  name</span><a href="#l912"></a>
<span id="l913">     *         The &lt;a href=&quot;#name&quot;&gt;binary name&lt;/a&gt; of the class</span><a href="#l913"></a>
<span id="l914">     *</span><a href="#l914"></a>
<span id="l915">     * @return  The &lt;tt&gt;Class&lt;/tt&gt; object, or &lt;tt&gt;null&lt;/tt&gt; if the class has</span><a href="#l915"></a>
<span id="l916">     *          not been loaded</span><a href="#l916"></a>
<span id="l917">     *</span><a href="#l917"></a>
<span id="l918">     * @since  1.1</span><a href="#l918"></a>
<span id="l919">     */</span><a href="#l919"></a>
<span id="l920">    protected final Class&lt;?&gt; findLoadedClass(String name) {</span><a href="#l920"></a>
<span id="l921">        if (!checkName(name))</span><a href="#l921"></a>
<span id="l922">            return null;</span><a href="#l922"></a>
<span id="l923">        return findLoadedClass0(name);</span><a href="#l923"></a>
<span id="l924">    }</span><a href="#l924"></a>
<span id="l925"></span><a href="#l925"></a>
<span id="l926">    private native final Class findLoadedClass0(String name);</span><a href="#l926"></a>
<span id="l927"></span><a href="#l927"></a>
<span id="l928">    /**</span><a href="#l928"></a>
<span id="l929">     * Sets the signers of a class.  This should be invoked after defining a</span><a href="#l929"></a>
<span id="l930">     * class.  &lt;/p&gt;</span><a href="#l930"></a>
<span id="l931">     *</span><a href="#l931"></a>
<span id="l932">     * @param  c</span><a href="#l932"></a>
<span id="l933">     *         The &lt;tt&gt;Class&lt;/tt&gt; object</span><a href="#l933"></a>
<span id="l934">     *</span><a href="#l934"></a>
<span id="l935">     * @param  signers</span><a href="#l935"></a>
<span id="l936">     *         The signers for the class</span><a href="#l936"></a>
<span id="l937">     *</span><a href="#l937"></a>
<span id="l938">     * @since  1.1</span><a href="#l938"></a>
<span id="l939">     */</span><a href="#l939"></a>
<span id="l940">    protected final void setSigners(Class&lt;?&gt; c, Object[] signers) {</span><a href="#l940"></a>
<span id="l941">        c.setSigners(signers);</span><a href="#l941"></a>
<span id="l942">    }</span><a href="#l942"></a>
<span id="l943"></span><a href="#l943"></a>
<span id="l944"></span><a href="#l944"></a>
<span id="l945">    // -- Resource --</span><a href="#l945"></a>
<span id="l946"></span><a href="#l946"></a>
<span id="l947">    /**</span><a href="#l947"></a>
<span id="l948">     * Finds the resource with the given name.  A resource is some data</span><a href="#l948"></a>
<span id="l949">     * (images, audio, text, etc) that can be accessed by class code in a way</span><a href="#l949"></a>
<span id="l950">     * that is independent of the location of the code.</span><a href="#l950"></a>
<span id="l951">     *</span><a href="#l951"></a>
<span id="l952">     * &lt;p&gt; The name of a resource is a '&lt;tt&gt;/&lt;/tt&gt;'-separated path name that</span><a href="#l952"></a>
<span id="l953">     * identifies the resource.</span><a href="#l953"></a>
<span id="l954">     *</span><a href="#l954"></a>
<span id="l955">     * &lt;p&gt; This method will first search the parent class loader for the</span><a href="#l955"></a>
<span id="l956">     * resource; if the parent is &lt;tt&gt;null&lt;/tt&gt; the path of the class loader</span><a href="#l956"></a>
<span id="l957">     * built-in to the virtual machine is searched.  That failing, this method</span><a href="#l957"></a>
<span id="l958">     * will invoke {@link #findResource(String)} to find the resource.  &lt;/p&gt;</span><a href="#l958"></a>
<span id="l959">     *</span><a href="#l959"></a>
<span id="l960">     * @param  name</span><a href="#l960"></a>
<span id="l961">     *         The resource name</span><a href="#l961"></a>
<span id="l962">     *</span><a href="#l962"></a>
<span id="l963">     * @return  A &lt;tt&gt;URL&lt;/tt&gt; object for reading the resource, or</span><a href="#l963"></a>
<span id="l964">     *          &lt;tt&gt;null&lt;/tt&gt; if the resource could not be found or the invoker</span><a href="#l964"></a>
<span id="l965">     *          doesn't have adequate  privileges to get the resource.</span><a href="#l965"></a>
<span id="l966">     *</span><a href="#l966"></a>
<span id="l967">     * @since  1.1</span><a href="#l967"></a>
<span id="l968">     */</span><a href="#l968"></a>
<span id="l969">    public URL getResource(String name) {</span><a href="#l969"></a>
<span id="l970">        URL url;</span><a href="#l970"></a>
<span id="l971">        if (parent != null) {</span><a href="#l971"></a>
<span id="l972">            url = parent.getResource(name);</span><a href="#l972"></a>
<span id="l973">        } else {</span><a href="#l973"></a>
<span id="l974">            url = getBootstrapResource(name);</span><a href="#l974"></a>
<span id="l975">        }</span><a href="#l975"></a>
<span id="l976">        if (url == null) {</span><a href="#l976"></a>
<span id="l977">            url = findResource(name);</span><a href="#l977"></a>
<span id="l978">        }</span><a href="#l978"></a>
<span id="l979">        return url;</span><a href="#l979"></a>
<span id="l980">    }</span><a href="#l980"></a>
<span id="l981"></span><a href="#l981"></a>
<span id="l982">    /**</span><a href="#l982"></a>
<span id="l983">     * Finds all the resources with the given name. A resource is some data</span><a href="#l983"></a>
<span id="l984">     * (images, audio, text, etc) that can be accessed by class code in a way</span><a href="#l984"></a>
<span id="l985">     * that is independent of the location of the code.</span><a href="#l985"></a>
<span id="l986">     *</span><a href="#l986"></a>
<span id="l987">     * &lt;p&gt;The name of a resource is a &lt;tt&gt;/&lt;/tt&gt;-separated path name that</span><a href="#l987"></a>
<span id="l988">     * identifies the resource.</span><a href="#l988"></a>
<span id="l989">     *</span><a href="#l989"></a>
<span id="l990">     * &lt;p&gt; The search order is described in the documentation for {@link</span><a href="#l990"></a>
<span id="l991">     * #getResource(String)}.  &lt;/p&gt;</span><a href="#l991"></a>
<span id="l992">     *</span><a href="#l992"></a>
<span id="l993">     * @param  name</span><a href="#l993"></a>
<span id="l994">     *         The resource name</span><a href="#l994"></a>
<span id="l995">     *</span><a href="#l995"></a>
<span id="l996">     * @return  An enumeration of {@link java.net.URL &lt;tt&gt;URL&lt;/tt&gt;} objects for</span><a href="#l996"></a>
<span id="l997">     *          the resource.  If no resources could  be found, the enumeration</span><a href="#l997"></a>
<span id="l998">     *          will be empty.  Resources that the class loader doesn't have</span><a href="#l998"></a>
<span id="l999">     *          access to will not be in the enumeration.</span><a href="#l999"></a>
<span id="l1000">     *</span><a href="#l1000"></a>
<span id="l1001">     * @throws  IOException</span><a href="#l1001"></a>
<span id="l1002">     *          If I/O errors occur</span><a href="#l1002"></a>
<span id="l1003">     *</span><a href="#l1003"></a>
<span id="l1004">     * @see  #findResources(String)</span><a href="#l1004"></a>
<span id="l1005">     *</span><a href="#l1005"></a>
<span id="l1006">     * @since  1.2</span><a href="#l1006"></a>
<span id="l1007">     */</span><a href="#l1007"></a>
<span id="l1008">    public Enumeration&lt;URL&gt; getResources(String name) throws IOException {</span><a href="#l1008"></a>
<span id="l1009">        Enumeration[] tmp = new Enumeration[2];</span><a href="#l1009"></a>
<span id="l1010">        if (parent != null) {</span><a href="#l1010"></a>
<span id="l1011">            tmp[0] = parent.getResources(name);</span><a href="#l1011"></a>
<span id="l1012">        } else {</span><a href="#l1012"></a>
<span id="l1013">            tmp[0] = getBootstrapResources(name);</span><a href="#l1013"></a>
<span id="l1014">        }</span><a href="#l1014"></a>
<span id="l1015">        tmp[1] = findResources(name);</span><a href="#l1015"></a>
<span id="l1016"></span><a href="#l1016"></a>
<span id="l1017">        return new CompoundEnumeration(tmp);</span><a href="#l1017"></a>
<span id="l1018">    }</span><a href="#l1018"></a>
<span id="l1019"></span><a href="#l1019"></a>
<span id="l1020">    /**</span><a href="#l1020"></a>
<span id="l1021">     * Finds the resource with the given name. Class loader implementations</span><a href="#l1021"></a>
<span id="l1022">     * should override this method to specify where to find resources.  &lt;/p&gt;</span><a href="#l1022"></a>
<span id="l1023">     *</span><a href="#l1023"></a>
<span id="l1024">     * @param  name</span><a href="#l1024"></a>
<span id="l1025">     *         The resource name</span><a href="#l1025"></a>
<span id="l1026">     *</span><a href="#l1026"></a>
<span id="l1027">     * @return  A &lt;tt&gt;URL&lt;/tt&gt; object for reading the resource, or</span><a href="#l1027"></a>
<span id="l1028">     *          &lt;tt&gt;null&lt;/tt&gt; if the resource could not be found</span><a href="#l1028"></a>
<span id="l1029">     *</span><a href="#l1029"></a>
<span id="l1030">     * @since  1.2</span><a href="#l1030"></a>
<span id="l1031">     */</span><a href="#l1031"></a>
<span id="l1032">    protected URL findResource(String name) {</span><a href="#l1032"></a>
<span id="l1033">        return null;</span><a href="#l1033"></a>
<span id="l1034">    }</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">    /**</span><a href="#l1036"></a>
<span id="l1037">     * Returns an enumeration of {@link java.net.URL &lt;tt&gt;URL&lt;/tt&gt;} objects</span><a href="#l1037"></a>
<span id="l1038">     * representing all the resources with the given name. Class loader</span><a href="#l1038"></a>
<span id="l1039">     * implementations should override this method to specify where to load</span><a href="#l1039"></a>
<span id="l1040">     * resources from.  &lt;/p&gt;</span><a href="#l1040"></a>
<span id="l1041">     *</span><a href="#l1041"></a>
<span id="l1042">     * @param  name</span><a href="#l1042"></a>
<span id="l1043">     *         The resource name</span><a href="#l1043"></a>
<span id="l1044">     *</span><a href="#l1044"></a>
<span id="l1045">     * @return  An enumeration of {@link java.net.URL &lt;tt&gt;URL&lt;/tt&gt;} objects for</span><a href="#l1045"></a>
<span id="l1046">     *          the resources</span><a href="#l1046"></a>
<span id="l1047">     *</span><a href="#l1047"></a>
<span id="l1048">     * @throws  IOException</span><a href="#l1048"></a>
<span id="l1049">     *          If I/O errors occur</span><a href="#l1049"></a>
<span id="l1050">     *</span><a href="#l1050"></a>
<span id="l1051">     * @since  1.2</span><a href="#l1051"></a>
<span id="l1052">     */</span><a href="#l1052"></a>
<span id="l1053">    protected Enumeration&lt;URL&gt; findResources(String name) throws IOException {</span><a href="#l1053"></a>
<span id="l1054">        return new CompoundEnumeration(new Enumeration[0]);</span><a href="#l1054"></a>
<span id="l1055">    }</span><a href="#l1055"></a>
<span id="l1056"></span><a href="#l1056"></a>
<span id="l1057">    /**</span><a href="#l1057"></a>
<span id="l1058">     * Find a resource of the specified name from the search path used to load</span><a href="#l1058"></a>
<span id="l1059">     * classes.  This method locates the resource through the system class</span><a href="#l1059"></a>
<span id="l1060">     * loader (see {@link #getSystemClassLoader()}).  &lt;/p&gt;</span><a href="#l1060"></a>
<span id="l1061">     *</span><a href="#l1061"></a>
<span id="l1062">     * @param  name</span><a href="#l1062"></a>
<span id="l1063">     *         The resource name</span><a href="#l1063"></a>
<span id="l1064">     *</span><a href="#l1064"></a>
<span id="l1065">     * @return  A {@link java.net.URL &lt;tt&gt;URL&lt;/tt&gt;} object for reading the</span><a href="#l1065"></a>
<span id="l1066">     *          resource, or &lt;tt&gt;null&lt;/tt&gt; if the resource could not be found</span><a href="#l1066"></a>
<span id="l1067">     *</span><a href="#l1067"></a>
<span id="l1068">     * @since  1.1</span><a href="#l1068"></a>
<span id="l1069">     */</span><a href="#l1069"></a>
<span id="l1070">    public static URL getSystemResource(String name) {</span><a href="#l1070"></a>
<span id="l1071">        ClassLoader system = getSystemClassLoader();</span><a href="#l1071"></a>
<span id="l1072">        if (system == null) {</span><a href="#l1072"></a>
<span id="l1073">            return getBootstrapResource(name);</span><a href="#l1073"></a>
<span id="l1074">        }</span><a href="#l1074"></a>
<span id="l1075">        return system.getResource(name);</span><a href="#l1075"></a>
<span id="l1076">    }</span><a href="#l1076"></a>
<span id="l1077"></span><a href="#l1077"></a>
<span id="l1078">    /**</span><a href="#l1078"></a>
<span id="l1079">     * Finds all resources of the specified name from the search path used to</span><a href="#l1079"></a>
<span id="l1080">     * load classes.  The resources thus found are returned as an</span><a href="#l1080"></a>
<span id="l1081">     * {@link java.util.Enumeration &lt;tt&gt;Enumeration&lt;/tt&gt;} of {@link</span><a href="#l1081"></a>
<span id="l1082">     * java.net.URL &lt;tt&gt;URL&lt;/tt&gt;} objects.</span><a href="#l1082"></a>
<span id="l1083">     *</span><a href="#l1083"></a>
<span id="l1084">     * &lt;p&gt; The search order is described in the documentation for {@link</span><a href="#l1084"></a>
<span id="l1085">     * #getSystemResource(String)}.  &lt;/p&gt;</span><a href="#l1085"></a>
<span id="l1086">     *</span><a href="#l1086"></a>
<span id="l1087">     * @param  name</span><a href="#l1087"></a>
<span id="l1088">     *         The resource name</span><a href="#l1088"></a>
<span id="l1089">     *</span><a href="#l1089"></a>
<span id="l1090">     * @return  An enumeration of resource {@link java.net.URL &lt;tt&gt;URL&lt;/tt&gt;}</span><a href="#l1090"></a>
<span id="l1091">     *          objects</span><a href="#l1091"></a>
<span id="l1092">     *</span><a href="#l1092"></a>
<span id="l1093">     * @throws  IOException</span><a href="#l1093"></a>
<span id="l1094">     *          If I/O errors occur</span><a href="#l1094"></a>
<span id="l1095"></span><a href="#l1095"></a>
<span id="l1096">     * @since  1.2</span><a href="#l1096"></a>
<span id="l1097">     */</span><a href="#l1097"></a>
<span id="l1098">    public static Enumeration&lt;URL&gt; getSystemResources(String name)</span><a href="#l1098"></a>
<span id="l1099">        throws IOException</span><a href="#l1099"></a>
<span id="l1100">    {</span><a href="#l1100"></a>
<span id="l1101">        ClassLoader system = getSystemClassLoader();</span><a href="#l1101"></a>
<span id="l1102">        if (system == null) {</span><a href="#l1102"></a>
<span id="l1103">            return getBootstrapResources(name);</span><a href="#l1103"></a>
<span id="l1104">        }</span><a href="#l1104"></a>
<span id="l1105">        return system.getResources(name);</span><a href="#l1105"></a>
<span id="l1106">    }</span><a href="#l1106"></a>
<span id="l1107"></span><a href="#l1107"></a>
<span id="l1108">    /**</span><a href="#l1108"></a>
<span id="l1109">     * Find resources from the VM's built-in classloader.</span><a href="#l1109"></a>
<span id="l1110">     */</span><a href="#l1110"></a>
<span id="l1111">    private static URL getBootstrapResource(String name) {</span><a href="#l1111"></a>
<span id="l1112">        URLClassPath ucp = getBootstrapClassPath();</span><a href="#l1112"></a>
<span id="l1113">        Resource res = ucp.getResource(name);</span><a href="#l1113"></a>
<span id="l1114">        return res != null ? res.getURL() : null;</span><a href="#l1114"></a>
<span id="l1115">    }</span><a href="#l1115"></a>
<span id="l1116"></span><a href="#l1116"></a>
<span id="l1117">    /**</span><a href="#l1117"></a>
<span id="l1118">     * Find resources from the VM's built-in classloader.</span><a href="#l1118"></a>
<span id="l1119">     */</span><a href="#l1119"></a>
<span id="l1120">    private static Enumeration getBootstrapResources(String name)</span><a href="#l1120"></a>
<span id="l1121">        throws IOException</span><a href="#l1121"></a>
<span id="l1122">    {</span><a href="#l1122"></a>
<span id="l1123">        final Enumeration e = getBootstrapClassPath().getResources(name);</span><a href="#l1123"></a>
<span id="l1124">        return new Enumeration () {</span><a href="#l1124"></a>
<span id="l1125">            public Object nextElement() {</span><a href="#l1125"></a>
<span id="l1126">                return ((Resource)e.nextElement()).getURL();</span><a href="#l1126"></a>
<span id="l1127">            }</span><a href="#l1127"></a>
<span id="l1128">            public boolean hasMoreElements() {</span><a href="#l1128"></a>
<span id="l1129">                return e.hasMoreElements();</span><a href="#l1129"></a>
<span id="l1130">            }</span><a href="#l1130"></a>
<span id="l1131">        };</span><a href="#l1131"></a>
<span id="l1132">    }</span><a href="#l1132"></a>
<span id="l1133"></span><a href="#l1133"></a>
<span id="l1134">    // Returns the URLClassPath that is used for finding system resources.</span><a href="#l1134"></a>
<span id="l1135">    static URLClassPath getBootstrapClassPath() {</span><a href="#l1135"></a>
<span id="l1136">        if (bootstrapClassPath == null) {</span><a href="#l1136"></a>
<span id="l1137">            bootstrapClassPath = sun.misc.Launcher.getBootstrapClassPath();</span><a href="#l1137"></a>
<span id="l1138">        }</span><a href="#l1138"></a>
<span id="l1139">        return bootstrapClassPath;</span><a href="#l1139"></a>
<span id="l1140">    }</span><a href="#l1140"></a>
<span id="l1141"></span><a href="#l1141"></a>
<span id="l1142">    private static URLClassPath bootstrapClassPath;</span><a href="#l1142"></a>
<span id="l1143"></span><a href="#l1143"></a>
<span id="l1144">    /**</span><a href="#l1144"></a>
<span id="l1145">     * Returns an input stream for reading the specified resource.</span><a href="#l1145"></a>
<span id="l1146">     *</span><a href="#l1146"></a>
<span id="l1147">     * &lt;p&gt; The search order is described in the documentation for {@link</span><a href="#l1147"></a>
<span id="l1148">     * #getResource(String)}.  &lt;/p&gt;</span><a href="#l1148"></a>
<span id="l1149">     *</span><a href="#l1149"></a>
<span id="l1150">     * @param  name</span><a href="#l1150"></a>
<span id="l1151">     *         The resource name</span><a href="#l1151"></a>
<span id="l1152">     *</span><a href="#l1152"></a>
<span id="l1153">     * @return  An input stream for reading the resource, or &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l1153"></a>
<span id="l1154">     *          if the resource could not be found</span><a href="#l1154"></a>
<span id="l1155">     *</span><a href="#l1155"></a>
<span id="l1156">     * @since  1.1</span><a href="#l1156"></a>
<span id="l1157">     */</span><a href="#l1157"></a>
<span id="l1158">    public InputStream getResourceAsStream(String name) {</span><a href="#l1158"></a>
<span id="l1159">        URL url = getResource(name);</span><a href="#l1159"></a>
<span id="l1160">        try {</span><a href="#l1160"></a>
<span id="l1161">            return url != null ? url.openStream() : null;</span><a href="#l1161"></a>
<span id="l1162">        } catch (IOException e) {</span><a href="#l1162"></a>
<span id="l1163">            return null;</span><a href="#l1163"></a>
<span id="l1164">        }</span><a href="#l1164"></a>
<span id="l1165">    }</span><a href="#l1165"></a>
<span id="l1166"></span><a href="#l1166"></a>
<span id="l1167">    /**</span><a href="#l1167"></a>
<span id="l1168">     * Open for reading, a resource of the specified name from the search path</span><a href="#l1168"></a>
<span id="l1169">     * used to load classes.  This method locates the resource through the</span><a href="#l1169"></a>
<span id="l1170">     * system class loader (see {@link #getSystemClassLoader()}).  &lt;/p&gt;</span><a href="#l1170"></a>
<span id="l1171">     *</span><a href="#l1171"></a>
<span id="l1172">     * @param  name</span><a href="#l1172"></a>
<span id="l1173">     *         The resource name</span><a href="#l1173"></a>
<span id="l1174">     *</span><a href="#l1174"></a>
<span id="l1175">     * @return  An input stream for reading the resource, or &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l1175"></a>
<span id="l1176">     *          if the resource could not be found</span><a href="#l1176"></a>
<span id="l1177">     *</span><a href="#l1177"></a>
<span id="l1178">     * @since  1.1</span><a href="#l1178"></a>
<span id="l1179">     */</span><a href="#l1179"></a>
<span id="l1180">    public static InputStream getSystemResourceAsStream(String name) {</span><a href="#l1180"></a>
<span id="l1181">        URL url = getSystemResource(name);</span><a href="#l1181"></a>
<span id="l1182">        try {</span><a href="#l1182"></a>
<span id="l1183">            return url != null ? url.openStream() : null;</span><a href="#l1183"></a>
<span id="l1184">        } catch (IOException e) {</span><a href="#l1184"></a>
<span id="l1185">            return null;</span><a href="#l1185"></a>
<span id="l1186">        }</span><a href="#l1186"></a>
<span id="l1187">    }</span><a href="#l1187"></a>
<span id="l1188"></span><a href="#l1188"></a>
<span id="l1189"></span><a href="#l1189"></a>
<span id="l1190">    // -- Hierarchy --</span><a href="#l1190"></a>
<span id="l1191"></span><a href="#l1191"></a>
<span id="l1192">    /**</span><a href="#l1192"></a>
<span id="l1193">     * Returns the parent class loader for delegation. Some implementations may</span><a href="#l1193"></a>
<span id="l1194">     * use &lt;tt&gt;null&lt;/tt&gt; to represent the bootstrap class loader. This method</span><a href="#l1194"></a>
<span id="l1195">     * will return &lt;tt&gt;null&lt;/tt&gt; in such implementations if this class loader's</span><a href="#l1195"></a>
<span id="l1196">     * parent is the bootstrap class loader.</span><a href="#l1196"></a>
<span id="l1197">     *</span><a href="#l1197"></a>
<span id="l1198">     * &lt;p&gt; If a security manager is present, and the invoker's class loader is</span><a href="#l1198"></a>
<span id="l1199">     * not &lt;tt&gt;null&lt;/tt&gt; and is not an ancestor of this class loader, then this</span><a href="#l1199"></a>
<span id="l1200">     * method invokes the security manager's {@link</span><a href="#l1200"></a>
<span id="l1201">     * SecurityManager#checkPermission(java.security.Permission)</span><a href="#l1201"></a>
<span id="l1202">     * &lt;tt&gt;checkPermission&lt;/tt&gt;} method with a {@link</span><a href="#l1202"></a>
<span id="l1203">     * RuntimePermission#RuntimePermission(String)</span><a href="#l1203"></a>
<span id="l1204">     * &lt;tt&gt;RuntimePermission(&quot;getClassLoader&quot;)&lt;/tt&gt;} permission to verify</span><a href="#l1204"></a>
<span id="l1205">     * access to the parent class loader is permitted.  If not, a</span><a href="#l1205"></a>
<span id="l1206">     * &lt;tt&gt;SecurityException&lt;/tt&gt; will be thrown.  &lt;/p&gt;</span><a href="#l1206"></a>
<span id="l1207">     *</span><a href="#l1207"></a>
<span id="l1208">     * @return  The parent &lt;tt&gt;ClassLoader&lt;/tt&gt;</span><a href="#l1208"></a>
<span id="l1209">     *</span><a href="#l1209"></a>
<span id="l1210">     * @throws  SecurityException</span><a href="#l1210"></a>
<span id="l1211">     *          If a security manager exists and its &lt;tt&gt;checkPermission&lt;/tt&gt;</span><a href="#l1211"></a>
<span id="l1212">     *          method doesn't allow access to this class loader's parent class</span><a href="#l1212"></a>
<span id="l1213">     *          loader.</span><a href="#l1213"></a>
<span id="l1214">     *</span><a href="#l1214"></a>
<span id="l1215">     * @since  1.2</span><a href="#l1215"></a>
<span id="l1216">     */</span><a href="#l1216"></a>
<span id="l1217">    public final ClassLoader getParent() {</span><a href="#l1217"></a>
<span id="l1218">        if (parent == null)</span><a href="#l1218"></a>
<span id="l1219">            return null;</span><a href="#l1219"></a>
<span id="l1220">        SecurityManager sm = System.getSecurityManager();</span><a href="#l1220"></a>
<span id="l1221">        if (sm != null) {</span><a href="#l1221"></a>
<span id="l1222">            ClassLoader ccl = getCallerClassLoader();</span><a href="#l1222"></a>
<span id="l1223">            if (ccl != null &amp;&amp; !isAncestor(ccl)) {</span><a href="#l1223"></a>
<span id="l1224">                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);</span><a href="#l1224"></a>
<span id="l1225">            }</span><a href="#l1225"></a>
<span id="l1226">        }</span><a href="#l1226"></a>
<span id="l1227">        return parent;</span><a href="#l1227"></a>
<span id="l1228">    }</span><a href="#l1228"></a>
<span id="l1229"></span><a href="#l1229"></a>
<span id="l1230">    /**</span><a href="#l1230"></a>
<span id="l1231">     * Returns the system class loader for delegation.  This is the default</span><a href="#l1231"></a>
<span id="l1232">     * delegation parent for new &lt;tt&gt;ClassLoader&lt;/tt&gt; instances, and is</span><a href="#l1232"></a>
<span id="l1233">     * typically the class loader used to start the application.</span><a href="#l1233"></a>
<span id="l1234">     *</span><a href="#l1234"></a>
<span id="l1235">     * &lt;p&gt; This method is first invoked early in the runtime's startup</span><a href="#l1235"></a>
<span id="l1236">     * sequence, at which point it creates the system class loader and sets it</span><a href="#l1236"></a>
<span id="l1237">     * as the context class loader of the invoking &lt;tt&gt;Thread&lt;/tt&gt;.</span><a href="#l1237"></a>
<span id="l1238">     *</span><a href="#l1238"></a>
<span id="l1239">     * &lt;p&gt; The default system class loader is an implementation-dependent</span><a href="#l1239"></a>
<span id="l1240">     * instance of this class.</span><a href="#l1240"></a>
<span id="l1241">     *</span><a href="#l1241"></a>
<span id="l1242">     * &lt;p&gt; If the system property &quot;&lt;tt&gt;java.system.class.loader&lt;/tt&gt;&quot; is defined</span><a href="#l1242"></a>
<span id="l1243">     * when this method is first invoked then the value of that property is</span><a href="#l1243"></a>
<span id="l1244">     * taken to be the name of a class that will be returned as the system</span><a href="#l1244"></a>
<span id="l1245">     * class loader.  The class is loaded using the default system class loader</span><a href="#l1245"></a>
<span id="l1246">     * and must define a public constructor that takes a single parameter of</span><a href="#l1246"></a>
<span id="l1247">     * type &lt;tt&gt;ClassLoader&lt;/tt&gt; which is used as the delegation parent.  An</span><a href="#l1247"></a>
<span id="l1248">     * instance is then created using this constructor with the default system</span><a href="#l1248"></a>
<span id="l1249">     * class loader as the parameter.  The resulting class loader is defined</span><a href="#l1249"></a>
<span id="l1250">     * to be the system class loader.</span><a href="#l1250"></a>
<span id="l1251">     *</span><a href="#l1251"></a>
<span id="l1252">     * &lt;p&gt; If a security manager is present, and the invoker's class loader is</span><a href="#l1252"></a>
<span id="l1253">     * not &lt;tt&gt;null&lt;/tt&gt; and the invoker's class loader is not the same as or</span><a href="#l1253"></a>
<span id="l1254">     * an ancestor of the system class loader, then this method invokes the</span><a href="#l1254"></a>
<span id="l1255">     * security manager's {@link</span><a href="#l1255"></a>
<span id="l1256">     * SecurityManager#checkPermission(java.security.Permission)</span><a href="#l1256"></a>
<span id="l1257">     * &lt;tt&gt;checkPermission&lt;/tt&gt;} method with a {@link</span><a href="#l1257"></a>
<span id="l1258">     * RuntimePermission#RuntimePermission(String)</span><a href="#l1258"></a>
<span id="l1259">     * &lt;tt&gt;RuntimePermission(&quot;getClassLoader&quot;)&lt;/tt&gt;} permission to verify</span><a href="#l1259"></a>
<span id="l1260">     * access to the system class loader.  If not, a</span><a href="#l1260"></a>
<span id="l1261">     * &lt;tt&gt;SecurityException&lt;/tt&gt; will be thrown.  &lt;/p&gt;</span><a href="#l1261"></a>
<span id="l1262">     *</span><a href="#l1262"></a>
<span id="l1263">     * @return  The system &lt;tt&gt;ClassLoader&lt;/tt&gt; for delegation, or</span><a href="#l1263"></a>
<span id="l1264">     *          &lt;tt&gt;null&lt;/tt&gt; if none</span><a href="#l1264"></a>
<span id="l1265">     *</span><a href="#l1265"></a>
<span id="l1266">     * @throws  SecurityException</span><a href="#l1266"></a>
<span id="l1267">     *          If a security manager exists and its &lt;tt&gt;checkPermission&lt;/tt&gt;</span><a href="#l1267"></a>
<span id="l1268">     *          method doesn't allow access to the system class loader.</span><a href="#l1268"></a>
<span id="l1269">     *</span><a href="#l1269"></a>
<span id="l1270">     * @throws  IllegalStateException</span><a href="#l1270"></a>
<span id="l1271">     *          If invoked recursively during the construction of the class</span><a href="#l1271"></a>
<span id="l1272">     *          loader specified by the &quot;&lt;tt&gt;java.system.class.loader&lt;/tt&gt;&quot;</span><a href="#l1272"></a>
<span id="l1273">     *          property.</span><a href="#l1273"></a>
<span id="l1274">     *</span><a href="#l1274"></a>
<span id="l1275">     * @throws  Error</span><a href="#l1275"></a>
<span id="l1276">     *          If the system property &quot;&lt;tt&gt;java.system.class.loader&lt;/tt&gt;&quot;</span><a href="#l1276"></a>
<span id="l1277">     *          is defined but the named class could not be loaded, the</span><a href="#l1277"></a>
<span id="l1278">     *          provider class does not define the required constructor, or an</span><a href="#l1278"></a>
<span id="l1279">     *          exception is thrown by that constructor when it is invoked. The</span><a href="#l1279"></a>
<span id="l1280">     *          underlying cause of the error can be retrieved via the</span><a href="#l1280"></a>
<span id="l1281">     *          {@link Throwable#getCause()} method.</span><a href="#l1281"></a>
<span id="l1282">     *</span><a href="#l1282"></a>
<span id="l1283">     * @revised  1.4</span><a href="#l1283"></a>
<span id="l1284">     */</span><a href="#l1284"></a>
<span id="l1285">    public static ClassLoader getSystemClassLoader() {</span><a href="#l1285"></a>
<span id="l1286">        initSystemClassLoader();</span><a href="#l1286"></a>
<span id="l1287">        if (scl == null) {</span><a href="#l1287"></a>
<span id="l1288">            return null;</span><a href="#l1288"></a>
<span id="l1289">        }</span><a href="#l1289"></a>
<span id="l1290">        SecurityManager sm = System.getSecurityManager();</span><a href="#l1290"></a>
<span id="l1291">        if (sm != null) {</span><a href="#l1291"></a>
<span id="l1292">            ClassLoader ccl = getCallerClassLoader();</span><a href="#l1292"></a>
<span id="l1293">            if (ccl != null &amp;&amp; ccl != scl &amp;&amp; !scl.isAncestor(ccl)) {</span><a href="#l1293"></a>
<span id="l1294">                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);</span><a href="#l1294"></a>
<span id="l1295">            }</span><a href="#l1295"></a>
<span id="l1296">        }</span><a href="#l1296"></a>
<span id="l1297">        return scl;</span><a href="#l1297"></a>
<span id="l1298">    }</span><a href="#l1298"></a>
<span id="l1299"></span><a href="#l1299"></a>
<span id="l1300">    private static synchronized void initSystemClassLoader() {</span><a href="#l1300"></a>
<span id="l1301">        if (!sclSet) {</span><a href="#l1301"></a>
<span id="l1302">            if (scl != null)</span><a href="#l1302"></a>
<span id="l1303">                throw new IllegalStateException(&quot;recursive invocation&quot;);</span><a href="#l1303"></a>
<span id="l1304">            sun.misc.Launcher l = sun.misc.Launcher.getLauncher();</span><a href="#l1304"></a>
<span id="l1305">            if (l != null) {</span><a href="#l1305"></a>
<span id="l1306">                Throwable oops = null;</span><a href="#l1306"></a>
<span id="l1307">                scl = l.getClassLoader();</span><a href="#l1307"></a>
<span id="l1308">                try {</span><a href="#l1308"></a>
<span id="l1309">                    PrivilegedExceptionAction a;</span><a href="#l1309"></a>
<span id="l1310">                    a = new SystemClassLoaderAction(scl);</span><a href="#l1310"></a>
<span id="l1311">                    scl = (ClassLoader) AccessController.doPrivileged(a);</span><a href="#l1311"></a>
<span id="l1312">                } catch (PrivilegedActionException pae) {</span><a href="#l1312"></a>
<span id="l1313">                    oops = pae.getCause();</span><a href="#l1313"></a>
<span id="l1314">                    if (oops instanceof InvocationTargetException) {</span><a href="#l1314"></a>
<span id="l1315">                        oops = oops.getCause();</span><a href="#l1315"></a>
<span id="l1316">                    }</span><a href="#l1316"></a>
<span id="l1317">                }</span><a href="#l1317"></a>
<span id="l1318">                if (oops != null) {</span><a href="#l1318"></a>
<span id="l1319">                    if (oops instanceof Error) {</span><a href="#l1319"></a>
<span id="l1320">                        throw (Error) oops;</span><a href="#l1320"></a>
<span id="l1321">                    } else {</span><a href="#l1321"></a>
<span id="l1322">                        // wrap the exception</span><a href="#l1322"></a>
<span id="l1323">                        throw new Error(oops);</span><a href="#l1323"></a>
<span id="l1324">                    }</span><a href="#l1324"></a>
<span id="l1325">                }</span><a href="#l1325"></a>
<span id="l1326">            }</span><a href="#l1326"></a>
<span id="l1327">            sclSet = true;</span><a href="#l1327"></a>
<span id="l1328">        }</span><a href="#l1328"></a>
<span id="l1329">    }</span><a href="#l1329"></a>
<span id="l1330"></span><a href="#l1330"></a>
<span id="l1331">    // Returns true if the specified class loader can be found in this class</span><a href="#l1331"></a>
<span id="l1332">    // loader's delegation chain.</span><a href="#l1332"></a>
<span id="l1333">    boolean isAncestor(ClassLoader cl) {</span><a href="#l1333"></a>
<span id="l1334">        ClassLoader acl = this;</span><a href="#l1334"></a>
<span id="l1335">        do {</span><a href="#l1335"></a>
<span id="l1336">            acl = acl.parent;</span><a href="#l1336"></a>
<span id="l1337">            if (cl == acl) {</span><a href="#l1337"></a>
<span id="l1338">                return true;</span><a href="#l1338"></a>
<span id="l1339">            }</span><a href="#l1339"></a>
<span id="l1340">        } while (acl != null);</span><a href="#l1340"></a>
<span id="l1341">        return false;</span><a href="#l1341"></a>
<span id="l1342">    }</span><a href="#l1342"></a>
<span id="l1343"></span><a href="#l1343"></a>
<span id="l1344">    // Returns the invoker's class loader, or null if none.</span><a href="#l1344"></a>
<span id="l1345">    // NOTE: This must always be invoked when there is exactly one intervening</span><a href="#l1345"></a>
<span id="l1346">    // frame from the core libraries on the stack between this method's</span><a href="#l1346"></a>
<span id="l1347">    // invocation and the desired invoker.</span><a href="#l1347"></a>
<span id="l1348">    static ClassLoader getCallerClassLoader() {</span><a href="#l1348"></a>
<span id="l1349">        // NOTE use of more generic Reflection.getCallerClass()</span><a href="#l1349"></a>
<span id="l1350">        Class caller = Reflection.getCallerClass(3);</span><a href="#l1350"></a>
<span id="l1351">        // This can be null if the VM is requesting it</span><a href="#l1351"></a>
<span id="l1352">        if (caller == null) {</span><a href="#l1352"></a>
<span id="l1353">            return null;</span><a href="#l1353"></a>
<span id="l1354">        }</span><a href="#l1354"></a>
<span id="l1355">        // Circumvent security check since this is package-private</span><a href="#l1355"></a>
<span id="l1356">        return caller.getClassLoader0();</span><a href="#l1356"></a>
<span id="l1357">    }</span><a href="#l1357"></a>
<span id="l1358"></span><a href="#l1358"></a>
<span id="l1359">    // The class loader for the system</span><a href="#l1359"></a>
<span id="l1360">    private static ClassLoader scl;</span><a href="#l1360"></a>
<span id="l1361"></span><a href="#l1361"></a>
<span id="l1362">    // Set to true once the system class loader has been set</span><a href="#l1362"></a>
<span id="l1363">    private static boolean sclSet;</span><a href="#l1363"></a>
<span id="l1364"></span><a href="#l1364"></a>
<span id="l1365"></span><a href="#l1365"></a>
<span id="l1366">    // -- Package --</span><a href="#l1366"></a>
<span id="l1367"></span><a href="#l1367"></a>
<span id="l1368">    /**</span><a href="#l1368"></a>
<span id="l1369">     * Defines a package by name in this &lt;tt&gt;ClassLoader&lt;/tt&gt;.  This allows</span><a href="#l1369"></a>
<span id="l1370">     * class loaders to define the packages for their classes. Packages must</span><a href="#l1370"></a>
<span id="l1371">     * be created before the class is defined, and package names must be</span><a href="#l1371"></a>
<span id="l1372">     * unique within a class loader and cannot be redefined or changed once</span><a href="#l1372"></a>
<span id="l1373">     * created.  &lt;/p&gt;</span><a href="#l1373"></a>
<span id="l1374">     *</span><a href="#l1374"></a>
<span id="l1375">     * @param  name</span><a href="#l1375"></a>
<span id="l1376">     *         The package name</span><a href="#l1376"></a>
<span id="l1377">     *</span><a href="#l1377"></a>
<span id="l1378">     * @param  specTitle</span><a href="#l1378"></a>
<span id="l1379">     *         The specification title</span><a href="#l1379"></a>
<span id="l1380">     *</span><a href="#l1380"></a>
<span id="l1381">     * @param  specVersion</span><a href="#l1381"></a>
<span id="l1382">     *         The specification version</span><a href="#l1382"></a>
<span id="l1383">     *</span><a href="#l1383"></a>
<span id="l1384">     * @param  specVendor</span><a href="#l1384"></a>
<span id="l1385">     *         The specification vendor</span><a href="#l1385"></a>
<span id="l1386">     *</span><a href="#l1386"></a>
<span id="l1387">     * @param  implTitle</span><a href="#l1387"></a>
<span id="l1388">     *         The implementation title</span><a href="#l1388"></a>
<span id="l1389">     *</span><a href="#l1389"></a>
<span id="l1390">     * @param  implVersion</span><a href="#l1390"></a>
<span id="l1391">     *         The implementation version</span><a href="#l1391"></a>
<span id="l1392">     *</span><a href="#l1392"></a>
<span id="l1393">     * @param  implVendor</span><a href="#l1393"></a>
<span id="l1394">     *         The implementation vendor</span><a href="#l1394"></a>
<span id="l1395">     *</span><a href="#l1395"></a>
<span id="l1396">     * @param  sealBase</span><a href="#l1396"></a>
<span id="l1397">     *         If not &lt;tt&gt;null&lt;/tt&gt;, then this package is sealed with</span><a href="#l1397"></a>
<span id="l1398">     *         respect to the given code source {@link java.net.URL</span><a href="#l1398"></a>
<span id="l1399">     *         &lt;tt&gt;URL&lt;/tt&gt;}  object.  Otherwise, the package is not sealed.</span><a href="#l1399"></a>
<span id="l1400">     *</span><a href="#l1400"></a>
<span id="l1401">     * @return  The newly defined &lt;tt&gt;Package&lt;/tt&gt; object</span><a href="#l1401"></a>
<span id="l1402">     *</span><a href="#l1402"></a>
<span id="l1403">     * @throws  IllegalArgumentException</span><a href="#l1403"></a>
<span id="l1404">     *          If package name duplicates an existing package either in this</span><a href="#l1404"></a>
<span id="l1405">     *          class loader or one of its ancestors</span><a href="#l1405"></a>
<span id="l1406">     *</span><a href="#l1406"></a>
<span id="l1407">     * @since  1.2</span><a href="#l1407"></a>
<span id="l1408">     */</span><a href="#l1408"></a>
<span id="l1409">    protected Package definePackage(String name, String specTitle,</span><a href="#l1409"></a>
<span id="l1410">                                    String specVersion, String specVendor,</span><a href="#l1410"></a>
<span id="l1411">                                    String implTitle, String implVersion,</span><a href="#l1411"></a>
<span id="l1412">                                    String implVendor, URL sealBase)</span><a href="#l1412"></a>
<span id="l1413">        throws IllegalArgumentException</span><a href="#l1413"></a>
<span id="l1414">    {</span><a href="#l1414"></a>
<span id="l1415">        synchronized (packages) {</span><a href="#l1415"></a>
<span id="l1416">            Package pkg = getPackage(name);</span><a href="#l1416"></a>
<span id="l1417">            if (pkg != null) {</span><a href="#l1417"></a>
<span id="l1418">                throw new IllegalArgumentException(name);</span><a href="#l1418"></a>
<span id="l1419">            }</span><a href="#l1419"></a>
<span id="l1420">            pkg = new Package(name, specTitle, specVersion, specVendor,</span><a href="#l1420"></a>
<span id="l1421">                              implTitle, implVersion, implVendor,</span><a href="#l1421"></a>
<span id="l1422">                              sealBase, this);</span><a href="#l1422"></a>
<span id="l1423">            packages.put(name, pkg);</span><a href="#l1423"></a>
<span id="l1424">            return pkg;</span><a href="#l1424"></a>
<span id="l1425">        }</span><a href="#l1425"></a>
<span id="l1426">    }</span><a href="#l1426"></a>
<span id="l1427"></span><a href="#l1427"></a>
<span id="l1428">    /**</span><a href="#l1428"></a>
<span id="l1429">     * Returns a &lt;tt&gt;Package&lt;/tt&gt; that has been defined by this class loader</span><a href="#l1429"></a>
<span id="l1430">     * or any of its ancestors.  &lt;/p&gt;</span><a href="#l1430"></a>
<span id="l1431">     *</span><a href="#l1431"></a>
<span id="l1432">     * @param  name</span><a href="#l1432"></a>
<span id="l1433">     *         The package name</span><a href="#l1433"></a>
<span id="l1434">     *</span><a href="#l1434"></a>
<span id="l1435">     * @return  The &lt;tt&gt;Package&lt;/tt&gt; corresponding to the given name, or</span><a href="#l1435"></a>
<span id="l1436">     *          &lt;tt&gt;null&lt;/tt&gt; if not found</span><a href="#l1436"></a>
<span id="l1437">     *</span><a href="#l1437"></a>
<span id="l1438">     * @since  1.2</span><a href="#l1438"></a>
<span id="l1439">     */</span><a href="#l1439"></a>
<span id="l1440">    protected Package getPackage(String name) {</span><a href="#l1440"></a>
<span id="l1441">        synchronized (packages) {</span><a href="#l1441"></a>
<span id="l1442">            Package pkg = (Package)packages.get(name);</span><a href="#l1442"></a>
<span id="l1443">            if (pkg == null) {</span><a href="#l1443"></a>
<span id="l1444">                if (parent != null) {</span><a href="#l1444"></a>
<span id="l1445">                    pkg = parent.getPackage(name);</span><a href="#l1445"></a>
<span id="l1446">                } else {</span><a href="#l1446"></a>
<span id="l1447">                    pkg = Package.getSystemPackage(name);</span><a href="#l1447"></a>
<span id="l1448">                }</span><a href="#l1448"></a>
<span id="l1449">                if (pkg != null) {</span><a href="#l1449"></a>
<span id="l1450">                    packages.put(name, pkg);</span><a href="#l1450"></a>
<span id="l1451">                }</span><a href="#l1451"></a>
<span id="l1452">            }</span><a href="#l1452"></a>
<span id="l1453">            return pkg;</span><a href="#l1453"></a>
<span id="l1454">        }</span><a href="#l1454"></a>
<span id="l1455">    }</span><a href="#l1455"></a>
<span id="l1456"></span><a href="#l1456"></a>
<span id="l1457">    /**</span><a href="#l1457"></a>
<span id="l1458">     * Returns all of the &lt;tt&gt;Packages&lt;/tt&gt; defined by this class loader and</span><a href="#l1458"></a>
<span id="l1459">     * its ancestors.  &lt;/p&gt;</span><a href="#l1459"></a>
<span id="l1460">     *</span><a href="#l1460"></a>
<span id="l1461">     * @return  The array of &lt;tt&gt;Package&lt;/tt&gt; objects defined by this</span><a href="#l1461"></a>
<span id="l1462">     *          &lt;tt&gt;ClassLoader&lt;/tt&gt;</span><a href="#l1462"></a>
<span id="l1463">     *</span><a href="#l1463"></a>
<span id="l1464">     * @since  1.2</span><a href="#l1464"></a>
<span id="l1465">     */</span><a href="#l1465"></a>
<span id="l1466">    protected Package[] getPackages() {</span><a href="#l1466"></a>
<span id="l1467">        Map map;</span><a href="#l1467"></a>
<span id="l1468">        synchronized (packages) {</span><a href="#l1468"></a>
<span id="l1469">            map = (Map)packages.clone();</span><a href="#l1469"></a>
<span id="l1470">        }</span><a href="#l1470"></a>
<span id="l1471">        Package[] pkgs;</span><a href="#l1471"></a>
<span id="l1472">        if (parent != null) {</span><a href="#l1472"></a>
<span id="l1473">            pkgs = parent.getPackages();</span><a href="#l1473"></a>
<span id="l1474">        } else {</span><a href="#l1474"></a>
<span id="l1475">            pkgs = Package.getSystemPackages();</span><a href="#l1475"></a>
<span id="l1476">        }</span><a href="#l1476"></a>
<span id="l1477">        if (pkgs != null) {</span><a href="#l1477"></a>
<span id="l1478">            for (int i = 0; i &lt; pkgs.length; i++) {</span><a href="#l1478"></a>
<span id="l1479">                String pkgName = pkgs[i].getName();</span><a href="#l1479"></a>
<span id="l1480">                if (map.get(pkgName) == null) {</span><a href="#l1480"></a>
<span id="l1481">                    map.put(pkgName, pkgs[i]);</span><a href="#l1481"></a>
<span id="l1482">                }</span><a href="#l1482"></a>
<span id="l1483">            }</span><a href="#l1483"></a>
<span id="l1484">        }</span><a href="#l1484"></a>
<span id="l1485">        return (Package[])map.values().toArray(new Package[map.size()]);</span><a href="#l1485"></a>
<span id="l1486">    }</span><a href="#l1486"></a>
<span id="l1487"></span><a href="#l1487"></a>
<span id="l1488"></span><a href="#l1488"></a>
<span id="l1489">    // -- Native library access --</span><a href="#l1489"></a>
<span id="l1490"></span><a href="#l1490"></a>
<span id="l1491">    /**</span><a href="#l1491"></a>
<span id="l1492">     * Returns the absolute path name of a native library.  The VM invokes this</span><a href="#l1492"></a>
<span id="l1493">     * method to locate the native libraries that belong to classes loaded with</span><a href="#l1493"></a>
<span id="l1494">     * this class loader. If this method returns &lt;tt&gt;null&lt;/tt&gt;, the VM</span><a href="#l1494"></a>
<span id="l1495">     * searches the library along the path specified as the</span><a href="#l1495"></a>
<span id="l1496">     * &quot;&lt;tt&gt;java.library.path&lt;/tt&gt;&quot; property.  &lt;/p&gt;</span><a href="#l1496"></a>
<span id="l1497">     *</span><a href="#l1497"></a>
<span id="l1498">     * @param  libname</span><a href="#l1498"></a>
<span id="l1499">     *         The library name</span><a href="#l1499"></a>
<span id="l1500">     *</span><a href="#l1500"></a>
<span id="l1501">     * @return  The absolute path of the native library</span><a href="#l1501"></a>
<span id="l1502">     *</span><a href="#l1502"></a>
<span id="l1503">     * @see  System#loadLibrary(String)</span><a href="#l1503"></a>
<span id="l1504">     * @see  System#mapLibraryName(String)</span><a href="#l1504"></a>
<span id="l1505">     *</span><a href="#l1505"></a>
<span id="l1506">     * @since  1.2</span><a href="#l1506"></a>
<span id="l1507">     */</span><a href="#l1507"></a>
<span id="l1508">    protected String findLibrary(String libname) {</span><a href="#l1508"></a>
<span id="l1509">        return null;</span><a href="#l1509"></a>
<span id="l1510">    }</span><a href="#l1510"></a>
<span id="l1511"></span><a href="#l1511"></a>
<span id="l1512">    /**</span><a href="#l1512"></a>
<span id="l1513">     * The inner class NativeLibrary denotes a loaded native library instance.</span><a href="#l1513"></a>
<span id="l1514">     * Every classloader contains a vector of loaded native libraries in the</span><a href="#l1514"></a>
<span id="l1515">     * private field &lt;tt&gt;nativeLibraries&lt;/tt&gt;.  The native libraries loaded</span><a href="#l1515"></a>
<span id="l1516">     * into the system are entered into the &lt;tt&gt;systemNativeLibraries&lt;/tt&gt;</span><a href="#l1516"></a>
<span id="l1517">     * vector.</span><a href="#l1517"></a>
<span id="l1518">     *</span><a href="#l1518"></a>
<span id="l1519">     * &lt;p&gt; Every native library requires a particular version of JNI. This is</span><a href="#l1519"></a>
<span id="l1520">     * denoted by the private &lt;tt&gt;jniVersion&lt;/tt&gt; field.  This field is set by</span><a href="#l1520"></a>
<span id="l1521">     * the VM when it loads the library, and used by the VM to pass the correct</span><a href="#l1521"></a>
<span id="l1522">     * version of JNI to the native methods.  &lt;/p&gt;</span><a href="#l1522"></a>
<span id="l1523">     *</span><a href="#l1523"></a>
<span id="l1524">     * @see      ClassLoader</span><a href="#l1524"></a>
<span id="l1525">     * @since    1.2</span><a href="#l1525"></a>
<span id="l1526">     */</span><a href="#l1526"></a>
<span id="l1527">    static class NativeLibrary {</span><a href="#l1527"></a>
<span id="l1528">        // opaque handle to native library, used in native code.</span><a href="#l1528"></a>
<span id="l1529">        long handle;</span><a href="#l1529"></a>
<span id="l1530">        // the version of JNI environment the native library requires.</span><a href="#l1530"></a>
<span id="l1531">        private int jniVersion;</span><a href="#l1531"></a>
<span id="l1532">        // the class from which the library is loaded, also indicates</span><a href="#l1532"></a>
<span id="l1533">        // the loader this native library belongs.</span><a href="#l1533"></a>
<span id="l1534">        private Class fromClass;</span><a href="#l1534"></a>
<span id="l1535">        // the canonicalized name of the native library.</span><a href="#l1535"></a>
<span id="l1536">        String name;</span><a href="#l1536"></a>
<span id="l1537"></span><a href="#l1537"></a>
<span id="l1538">        native void load(String name);</span><a href="#l1538"></a>
<span id="l1539">        native long find(String name);</span><a href="#l1539"></a>
<span id="l1540">        native void unload();</span><a href="#l1540"></a>
<span id="l1541"></span><a href="#l1541"></a>
<span id="l1542">        public NativeLibrary(Class fromClass, String name) {</span><a href="#l1542"></a>
<span id="l1543">            this.name = name;</span><a href="#l1543"></a>
<span id="l1544">            this.fromClass = fromClass;</span><a href="#l1544"></a>
<span id="l1545">        }</span><a href="#l1545"></a>
<span id="l1546"></span><a href="#l1546"></a>
<span id="l1547">        protected void finalize() {</span><a href="#l1547"></a>
<span id="l1548">            synchronized (loadedLibraryNames) {</span><a href="#l1548"></a>
<span id="l1549">                if (fromClass.getClassLoader() != null &amp;&amp; handle != 0) {</span><a href="#l1549"></a>
<span id="l1550">                    /* remove the native library name */</span><a href="#l1550"></a>
<span id="l1551">                    int size = loadedLibraryNames.size();</span><a href="#l1551"></a>
<span id="l1552">                    for (int i = 0; i &lt; size; i++) {</span><a href="#l1552"></a>
<span id="l1553">                        if (name.equals(loadedLibraryNames.elementAt(i))) {</span><a href="#l1553"></a>
<span id="l1554">                            loadedLibraryNames.removeElementAt(i);</span><a href="#l1554"></a>
<span id="l1555">                            break;</span><a href="#l1555"></a>
<span id="l1556">                        }</span><a href="#l1556"></a>
<span id="l1557">                    }</span><a href="#l1557"></a>
<span id="l1558">                    /* unload the library. */</span><a href="#l1558"></a>
<span id="l1559">                    ClassLoader.nativeLibraryContext.push(this);</span><a href="#l1559"></a>
<span id="l1560">                    try {</span><a href="#l1560"></a>
<span id="l1561">                        unload();</span><a href="#l1561"></a>
<span id="l1562">                    } finally {</span><a href="#l1562"></a>
<span id="l1563">                        ClassLoader.nativeLibraryContext.pop();</span><a href="#l1563"></a>
<span id="l1564">                    }</span><a href="#l1564"></a>
<span id="l1565">                }</span><a href="#l1565"></a>
<span id="l1566">            }</span><a href="#l1566"></a>
<span id="l1567">        }</span><a href="#l1567"></a>
<span id="l1568">        // Invoked in the VM to determine the context class in</span><a href="#l1568"></a>
<span id="l1569">        // JNI_Load/JNI_Unload</span><a href="#l1569"></a>
<span id="l1570">        static Class getFromClass() {</span><a href="#l1570"></a>
<span id="l1571">            return ((NativeLibrary)</span><a href="#l1571"></a>
<span id="l1572">                    (ClassLoader.nativeLibraryContext.peek())).fromClass;</span><a href="#l1572"></a>
<span id="l1573">        }</span><a href="#l1573"></a>
<span id="l1574">    }</span><a href="#l1574"></a>
<span id="l1575"></span><a href="#l1575"></a>
<span id="l1576">    // The &quot;default&quot; domain. Set as the default ProtectionDomain on newly</span><a href="#l1576"></a>
<span id="l1577">    // created classes.</span><a href="#l1577"></a>
<span id="l1578">    private ProtectionDomain defaultDomain = null;</span><a href="#l1578"></a>
<span id="l1579"></span><a href="#l1579"></a>
<span id="l1580">    // Returns (and initializes) the default domain.</span><a href="#l1580"></a>
<span id="l1581">    private synchronized ProtectionDomain getDefaultDomain() {</span><a href="#l1581"></a>
<span id="l1582">        if (defaultDomain == null) {</span><a href="#l1582"></a>
<span id="l1583">            CodeSource cs =</span><a href="#l1583"></a>
<span id="l1584">                new CodeSource(null, (java.security.cert.Certificate[]) null);</span><a href="#l1584"></a>
<span id="l1585">            defaultDomain = new ProtectionDomain(cs, null, this, null);</span><a href="#l1585"></a>
<span id="l1586">        }</span><a href="#l1586"></a>
<span id="l1587">        return defaultDomain;</span><a href="#l1587"></a>
<span id="l1588">    }</span><a href="#l1588"></a>
<span id="l1589"></span><a href="#l1589"></a>
<span id="l1590">    // All native library names we've loaded.</span><a href="#l1590"></a>
<span id="l1591">    private static Vector loadedLibraryNames = new Vector();</span><a href="#l1591"></a>
<span id="l1592">    // Native libraries belonging to system classes.</span><a href="#l1592"></a>
<span id="l1593">    private static Vector systemNativeLibraries = new Vector();</span><a href="#l1593"></a>
<span id="l1594">    // Native libraries associated with the class loader.</span><a href="#l1594"></a>
<span id="l1595">    private Vector nativeLibraries = new Vector();</span><a href="#l1595"></a>
<span id="l1596"></span><a href="#l1596"></a>
<span id="l1597">    // native libraries being loaded/unloaded.</span><a href="#l1597"></a>
<span id="l1598">    private static Stack nativeLibraryContext = new Stack();</span><a href="#l1598"></a>
<span id="l1599"></span><a href="#l1599"></a>
<span id="l1600">    // The paths searched for libraries</span><a href="#l1600"></a>
<span id="l1601">    static private String usr_paths[];</span><a href="#l1601"></a>
<span id="l1602">    static private String sys_paths[];</span><a href="#l1602"></a>
<span id="l1603"></span><a href="#l1603"></a>
<span id="l1604">    private static String[] initializePath(String propname) {</span><a href="#l1604"></a>
<span id="l1605">        String ldpath = System.getProperty(propname, &quot;&quot;);</span><a href="#l1605"></a>
<span id="l1606">        String ps = File.pathSeparator;</span><a href="#l1606"></a>
<span id="l1607">        int ldlen = ldpath.length();</span><a href="#l1607"></a>
<span id="l1608">        int i, j, n;</span><a href="#l1608"></a>
<span id="l1609">        // Count the separators in the path</span><a href="#l1609"></a>
<span id="l1610">        i = ldpath.indexOf(ps);</span><a href="#l1610"></a>
<span id="l1611">        n = 0;</span><a href="#l1611"></a>
<span id="l1612">        while (i &gt;= 0) {</span><a href="#l1612"></a>
<span id="l1613">            n++;</span><a href="#l1613"></a>
<span id="l1614">            i = ldpath.indexOf(ps, i + 1);</span><a href="#l1614"></a>
<span id="l1615">        }</span><a href="#l1615"></a>
<span id="l1616"></span><a href="#l1616"></a>
<span id="l1617">        // allocate the array of paths - n :'s = n + 1 path elements</span><a href="#l1617"></a>
<span id="l1618">        String[] paths = new String[n + 1];</span><a href="#l1618"></a>
<span id="l1619"></span><a href="#l1619"></a>
<span id="l1620">        // Fill the array with paths from the ldpath</span><a href="#l1620"></a>
<span id="l1621">        n = i = 0;</span><a href="#l1621"></a>
<span id="l1622">        j = ldpath.indexOf(ps);</span><a href="#l1622"></a>
<span id="l1623">        while (j &gt;= 0) {</span><a href="#l1623"></a>
<span id="l1624">            if (j - i &gt; 0) {</span><a href="#l1624"></a>
<span id="l1625">                paths[n++] = ldpath.substring(i, j);</span><a href="#l1625"></a>
<span id="l1626">            } else if (j - i == 0) {</span><a href="#l1626"></a>
<span id="l1627">                paths[n++] = &quot;.&quot;;</span><a href="#l1627"></a>
<span id="l1628">            }</span><a href="#l1628"></a>
<span id="l1629">            i = j + 1;</span><a href="#l1629"></a>
<span id="l1630">            j = ldpath.indexOf(ps, i);</span><a href="#l1630"></a>
<span id="l1631">        }</span><a href="#l1631"></a>
<span id="l1632">        paths[n] = ldpath.substring(i, ldlen);</span><a href="#l1632"></a>
<span id="l1633">        return paths;</span><a href="#l1633"></a>
<span id="l1634">    }</span><a href="#l1634"></a>
<span id="l1635"></span><a href="#l1635"></a>
<span id="l1636">    // Invoked in the java.lang.Runtime class to implement load and loadLibrary.</span><a href="#l1636"></a>
<span id="l1637">    static void loadLibrary(Class fromClass, String name,</span><a href="#l1637"></a>
<span id="l1638">                            boolean isAbsolute) {</span><a href="#l1638"></a>
<span id="l1639">        ClassLoader loader =</span><a href="#l1639"></a>
<span id="l1640">            (fromClass == null) ? null : fromClass.getClassLoader();</span><a href="#l1640"></a>
<span id="l1641">        if (sys_paths == null) {</span><a href="#l1641"></a>
<span id="l1642">            usr_paths = initializePath(&quot;java.library.path&quot;);</span><a href="#l1642"></a>
<span id="l1643">            sys_paths = initializePath(&quot;sun.boot.library.path&quot;);</span><a href="#l1643"></a>
<span id="l1644">        }</span><a href="#l1644"></a>
<span id="l1645">        if (isAbsolute) {</span><a href="#l1645"></a>
<span id="l1646">            if (loadLibrary0(fromClass, new File(name))) {</span><a href="#l1646"></a>
<span id="l1647">                return;</span><a href="#l1647"></a>
<span id="l1648">            }</span><a href="#l1648"></a>
<span id="l1649">            throw new UnsatisfiedLinkError(&quot;Can't load library: &quot; + name);</span><a href="#l1649"></a>
<span id="l1650">        }</span><a href="#l1650"></a>
<span id="l1651">        if (loader != null) {</span><a href="#l1651"></a>
<span id="l1652">            String libfilename = loader.findLibrary(name);</span><a href="#l1652"></a>
<span id="l1653">            if (libfilename != null) {</span><a href="#l1653"></a>
<span id="l1654">                File libfile = new File(libfilename);</span><a href="#l1654"></a>
<span id="l1655">                if (!libfile.isAbsolute()) {</span><a href="#l1655"></a>
<span id="l1656">                    throw new UnsatisfiedLinkError(</span><a href="#l1656"></a>
<span id="l1657">    &quot;ClassLoader.findLibrary failed to return an absolute path: &quot; + libfilename);</span><a href="#l1657"></a>
<span id="l1658">                }</span><a href="#l1658"></a>
<span id="l1659">                if (loadLibrary0(fromClass, libfile)) {</span><a href="#l1659"></a>
<span id="l1660">                    return;</span><a href="#l1660"></a>
<span id="l1661">                }</span><a href="#l1661"></a>
<span id="l1662">                throw new UnsatisfiedLinkError(&quot;Can't load &quot; + libfilename);</span><a href="#l1662"></a>
<span id="l1663">            }</span><a href="#l1663"></a>
<span id="l1664">        }</span><a href="#l1664"></a>
<span id="l1665">        for (int i = 0 ; i &lt; sys_paths.length ; i++) {</span><a href="#l1665"></a>
<span id="l1666">            File libfile = new File(sys_paths[i], System.mapLibraryName(name));</span><a href="#l1666"></a>
<span id="l1667">            if (loadLibrary0(fromClass, libfile)) {</span><a href="#l1667"></a>
<span id="l1668">                return;</span><a href="#l1668"></a>
<span id="l1669">            }</span><a href="#l1669"></a>
<span id="l1670">        }</span><a href="#l1670"></a>
<span id="l1671">        if (loader != null) {</span><a href="#l1671"></a>
<span id="l1672">            for (int i = 0 ; i &lt; usr_paths.length ; i++) {</span><a href="#l1672"></a>
<span id="l1673">                File libfile = new File(usr_paths[i],</span><a href="#l1673"></a>
<span id="l1674">                                        System.mapLibraryName(name));</span><a href="#l1674"></a>
<span id="l1675">                if (loadLibrary0(fromClass, libfile)) {</span><a href="#l1675"></a>
<span id="l1676">                    return;</span><a href="#l1676"></a>
<span id="l1677">                }</span><a href="#l1677"></a>
<span id="l1678">            }</span><a href="#l1678"></a>
<span id="l1679">        }</span><a href="#l1679"></a>
<span id="l1680">        // Oops, it failed</span><a href="#l1680"></a>
<span id="l1681">        throw new UnsatisfiedLinkError(&quot;no &quot; + name + &quot; in java.library.path&quot;);</span><a href="#l1681"></a>
<span id="l1682">    }</span><a href="#l1682"></a>
<span id="l1683"></span><a href="#l1683"></a>
<span id="l1684">    private static boolean loadLibrary0(Class fromClass, final File file) {</span><a href="#l1684"></a>
<span id="l1685">        Boolean exists = (Boolean)</span><a href="#l1685"></a>
<span id="l1686">            AccessController.doPrivileged(new PrivilegedAction() {</span><a href="#l1686"></a>
<span id="l1687">                public Object run() {</span><a href="#l1687"></a>
<span id="l1688">                    return new Boolean(file.exists());</span><a href="#l1688"></a>
<span id="l1689">                }</span><a href="#l1689"></a>
<span id="l1690">            });</span><a href="#l1690"></a>
<span id="l1691">        if (!exists.booleanValue()) {</span><a href="#l1691"></a>
<span id="l1692">            return false;</span><a href="#l1692"></a>
<span id="l1693">        }</span><a href="#l1693"></a>
<span id="l1694">        String name;</span><a href="#l1694"></a>
<span id="l1695">        try {</span><a href="#l1695"></a>
<span id="l1696">            name = file.getCanonicalPath();</span><a href="#l1696"></a>
<span id="l1697">        } catch (IOException e) {</span><a href="#l1697"></a>
<span id="l1698">            return false;</span><a href="#l1698"></a>
<span id="l1699">        }</span><a href="#l1699"></a>
<span id="l1700">        ClassLoader loader =</span><a href="#l1700"></a>
<span id="l1701">            (fromClass == null) ? null : fromClass.getClassLoader();</span><a href="#l1701"></a>
<span id="l1702">        Vector libs =</span><a href="#l1702"></a>
<span id="l1703">            loader != null ? loader.nativeLibraries : systemNativeLibraries;</span><a href="#l1703"></a>
<span id="l1704">        synchronized (libs) {</span><a href="#l1704"></a>
<span id="l1705">            int size = libs.size();</span><a href="#l1705"></a>
<span id="l1706">            for (int i = 0; i &lt; size; i++) {</span><a href="#l1706"></a>
<span id="l1707">                NativeLibrary lib = (NativeLibrary)libs.elementAt(i);</span><a href="#l1707"></a>
<span id="l1708">                if (name.equals(lib.name)) {</span><a href="#l1708"></a>
<span id="l1709">                    return true;</span><a href="#l1709"></a>
<span id="l1710">                }</span><a href="#l1710"></a>
<span id="l1711">            }</span><a href="#l1711"></a>
<span id="l1712"></span><a href="#l1712"></a>
<span id="l1713">            synchronized (loadedLibraryNames) {</span><a href="#l1713"></a>
<span id="l1714">                if (loadedLibraryNames.contains(name)) {</span><a href="#l1714"></a>
<span id="l1715">                    throw new UnsatisfiedLinkError</span><a href="#l1715"></a>
<span id="l1716">                        (&quot;Native Library &quot; +</span><a href="#l1716"></a>
<span id="l1717">                         name +</span><a href="#l1717"></a>
<span id="l1718">                         &quot; already loaded in another classloader&quot;);</span><a href="#l1718"></a>
<span id="l1719">                }</span><a href="#l1719"></a>
<span id="l1720">                /* If the library is being loaded (must be by the same thread,</span><a href="#l1720"></a>
<span id="l1721">                 * because Runtime.load and Runtime.loadLibrary are</span><a href="#l1721"></a>
<span id="l1722">                 * synchronous). The reason is can occur is that the JNI_OnLoad</span><a href="#l1722"></a>
<span id="l1723">                 * function can cause another loadLibrary invocation.</span><a href="#l1723"></a>
<span id="l1724">                 *</span><a href="#l1724"></a>
<span id="l1725">                 * Thus we can use a static stack to hold the list of libraries</span><a href="#l1725"></a>
<span id="l1726">                 * we are loading.</span><a href="#l1726"></a>
<span id="l1727">                 *</span><a href="#l1727"></a>
<span id="l1728">                 * If there is a pending load operation for the library, we</span><a href="#l1728"></a>
<span id="l1729">                 * immediately return success; otherwise, we raise</span><a href="#l1729"></a>
<span id="l1730">                 * UnsatisfiedLinkError.</span><a href="#l1730"></a>
<span id="l1731">                 */</span><a href="#l1731"></a>
<span id="l1732">                int n = nativeLibraryContext.size();</span><a href="#l1732"></a>
<span id="l1733">                for (int i = 0; i &lt; n; i++) {</span><a href="#l1733"></a>
<span id="l1734">                    NativeLibrary lib = (NativeLibrary)</span><a href="#l1734"></a>
<span id="l1735">                        nativeLibraryContext.elementAt(i);</span><a href="#l1735"></a>
<span id="l1736">                    if (name.equals(lib.name)) {</span><a href="#l1736"></a>
<span id="l1737">                        if (loader == lib.fromClass.getClassLoader()) {</span><a href="#l1737"></a>
<span id="l1738">                            return true;</span><a href="#l1738"></a>
<span id="l1739">                        } else {</span><a href="#l1739"></a>
<span id="l1740">                            throw new UnsatisfiedLinkError</span><a href="#l1740"></a>
<span id="l1741">                                (&quot;Native Library &quot; +</span><a href="#l1741"></a>
<span id="l1742">                                 name +</span><a href="#l1742"></a>
<span id="l1743">                                 &quot; is being loaded in another classloader&quot;);</span><a href="#l1743"></a>
<span id="l1744">                        }</span><a href="#l1744"></a>
<span id="l1745">                    }</span><a href="#l1745"></a>
<span id="l1746">                }</span><a href="#l1746"></a>
<span id="l1747">                NativeLibrary lib = new NativeLibrary(fromClass, name);</span><a href="#l1747"></a>
<span id="l1748">                nativeLibraryContext.push(lib);</span><a href="#l1748"></a>
<span id="l1749">                try {</span><a href="#l1749"></a>
<span id="l1750">                    lib.load(name);</span><a href="#l1750"></a>
<span id="l1751">                } finally {</span><a href="#l1751"></a>
<span id="l1752">                    nativeLibraryContext.pop();</span><a href="#l1752"></a>
<span id="l1753">                }</span><a href="#l1753"></a>
<span id="l1754">                if (lib.handle != 0) {</span><a href="#l1754"></a>
<span id="l1755">                    loadedLibraryNames.addElement(name);</span><a href="#l1755"></a>
<span id="l1756">                    libs.addElement(lib);</span><a href="#l1756"></a>
<span id="l1757">                    return true;</span><a href="#l1757"></a>
<span id="l1758">                }</span><a href="#l1758"></a>
<span id="l1759">                return false;</span><a href="#l1759"></a>
<span id="l1760">            }</span><a href="#l1760"></a>
<span id="l1761">        }</span><a href="#l1761"></a>
<span id="l1762">    }</span><a href="#l1762"></a>
<span id="l1763"></span><a href="#l1763"></a>
<span id="l1764">    // Invoked in the VM class linking code.</span><a href="#l1764"></a>
<span id="l1765">    static long findNative(ClassLoader loader, String name) {</span><a href="#l1765"></a>
<span id="l1766">        Vector libs =</span><a href="#l1766"></a>
<span id="l1767">            loader != null ? loader.nativeLibraries : systemNativeLibraries;</span><a href="#l1767"></a>
<span id="l1768">        synchronized (libs) {</span><a href="#l1768"></a>
<span id="l1769">            int size = libs.size();</span><a href="#l1769"></a>
<span id="l1770">            for (int i = 0; i &lt; size; i++) {</span><a href="#l1770"></a>
<span id="l1771">                NativeLibrary lib = (NativeLibrary)libs.elementAt(i);</span><a href="#l1771"></a>
<span id="l1772">                long entry = lib.find(name);</span><a href="#l1772"></a>
<span id="l1773">                if (entry != 0)</span><a href="#l1773"></a>
<span id="l1774">                    return entry;</span><a href="#l1774"></a>
<span id="l1775">            }</span><a href="#l1775"></a>
<span id="l1776">        }</span><a href="#l1776"></a>
<span id="l1777">        return 0;</span><a href="#l1777"></a>
<span id="l1778">    }</span><a href="#l1778"></a>
<span id="l1779"></span><a href="#l1779"></a>
<span id="l1780"></span><a href="#l1780"></a>
<span id="l1781">    // -- Assertion management --</span><a href="#l1781"></a>
<span id="l1782"></span><a href="#l1782"></a>
<span id="l1783">    // The default toggle for assertion checking.</span><a href="#l1783"></a>
<span id="l1784">    private boolean defaultAssertionStatus = false;</span><a href="#l1784"></a>
<span id="l1785"></span><a href="#l1785"></a>
<span id="l1786">    // Maps String packageName to Boolean package default assertion status Note</span><a href="#l1786"></a>
<span id="l1787">    // that the default package is placed under a null map key.  If this field</span><a href="#l1787"></a>
<span id="l1788">    // is null then we are delegating assertion status queries to the VM, i.e.,</span><a href="#l1788"></a>
<span id="l1789">    // none of this ClassLoader's assertion status modification methods have</span><a href="#l1789"></a>
<span id="l1790">    // been invoked.</span><a href="#l1790"></a>
<span id="l1791">    private Map packageAssertionStatus = null;</span><a href="#l1791"></a>
<span id="l1792"></span><a href="#l1792"></a>
<span id="l1793">    // Maps String fullyQualifiedClassName to Boolean assertionStatus If this</span><a href="#l1793"></a>
<span id="l1794">    // field is null then we are delegating assertion status queries to the VM,</span><a href="#l1794"></a>
<span id="l1795">    // i.e., none of this ClassLoader's assertion status modification methods</span><a href="#l1795"></a>
<span id="l1796">    // have been invoked.</span><a href="#l1796"></a>
<span id="l1797">    Map classAssertionStatus = null;</span><a href="#l1797"></a>
<span id="l1798"></span><a href="#l1798"></a>
<span id="l1799">    /**</span><a href="#l1799"></a>
<span id="l1800">     * Sets the default assertion status for this class loader.  This setting</span><a href="#l1800"></a>
<span id="l1801">     * determines whether classes loaded by this class loader and initialized</span><a href="#l1801"></a>
<span id="l1802">     * in the future will have assertions enabled or disabled by default.</span><a href="#l1802"></a>
<span id="l1803">     * This setting may be overridden on a per-package or per-class basis by</span><a href="#l1803"></a>
<span id="l1804">     * invoking {@link #setPackageAssertionStatus(String, boolean)} or {@link</span><a href="#l1804"></a>
<span id="l1805">     * #setClassAssertionStatus(String, boolean)}.  &lt;/p&gt;</span><a href="#l1805"></a>
<span id="l1806">     *</span><a href="#l1806"></a>
<span id="l1807">     * @param  enabled</span><a href="#l1807"></a>
<span id="l1808">     *         &lt;tt&gt;true&lt;/tt&gt; if classes loaded by this class loader will</span><a href="#l1808"></a>
<span id="l1809">     *         henceforth have assertions enabled by default, &lt;tt&gt;false&lt;/tt&gt;</span><a href="#l1809"></a>
<span id="l1810">     *         if they will have assertions disabled by default.</span><a href="#l1810"></a>
<span id="l1811">     *</span><a href="#l1811"></a>
<span id="l1812">     * @since  1.4</span><a href="#l1812"></a>
<span id="l1813">     */</span><a href="#l1813"></a>
<span id="l1814">    public synchronized void setDefaultAssertionStatus(boolean enabled) {</span><a href="#l1814"></a>
<span id="l1815">        if (classAssertionStatus == null)</span><a href="#l1815"></a>
<span id="l1816">            initializeJavaAssertionMaps();</span><a href="#l1816"></a>
<span id="l1817"></span><a href="#l1817"></a>
<span id="l1818">        defaultAssertionStatus = enabled;</span><a href="#l1818"></a>
<span id="l1819">    }</span><a href="#l1819"></a>
<span id="l1820"></span><a href="#l1820"></a>
<span id="l1821">    /**</span><a href="#l1821"></a>
<span id="l1822">     * Sets the package default assertion status for the named package.  The</span><a href="#l1822"></a>
<span id="l1823">     * package default assertion status determines the assertion status for</span><a href="#l1823"></a>
<span id="l1824">     * classes initialized in the future that belong to the named package or</span><a href="#l1824"></a>
<span id="l1825">     * any of its &quot;subpackages&quot;.</span><a href="#l1825"></a>
<span id="l1826">     *</span><a href="#l1826"></a>
<span id="l1827">     * &lt;p&gt; A subpackage of a package named p is any package whose name begins</span><a href="#l1827"></a>
<span id="l1828">     * with &quot;&lt;tt&gt;p.&lt;/tt&gt;&quot;.  For example, &lt;tt&gt;javax.swing.text&lt;/tt&gt; is a</span><a href="#l1828"></a>
<span id="l1829">     * subpackage of &lt;tt&gt;javax.swing&lt;/tt&gt;, and both &lt;tt&gt;java.util&lt;/tt&gt; and</span><a href="#l1829"></a>
<span id="l1830">     * &lt;tt&gt;java.lang.reflect&lt;/tt&gt; are subpackages of &lt;tt&gt;java&lt;/tt&gt;.</span><a href="#l1830"></a>
<span id="l1831">     *</span><a href="#l1831"></a>
<span id="l1832">     * &lt;p&gt; In the event that multiple package defaults apply to a given class,</span><a href="#l1832"></a>
<span id="l1833">     * the package default pertaining to the most specific package takes</span><a href="#l1833"></a>
<span id="l1834">     * precedence over the others.  For example, if &lt;tt&gt;javax.lang&lt;/tt&gt; and</span><a href="#l1834"></a>
<span id="l1835">     * &lt;tt&gt;javax.lang.reflect&lt;/tt&gt; both have package defaults associated with</span><a href="#l1835"></a>
<span id="l1836">     * them, the latter package default applies to classes in</span><a href="#l1836"></a>
<span id="l1837">     * &lt;tt&gt;javax.lang.reflect&lt;/tt&gt;.</span><a href="#l1837"></a>
<span id="l1838">     *</span><a href="#l1838"></a>
<span id="l1839">     * &lt;p&gt; Package defaults take precedence over the class loader's default</span><a href="#l1839"></a>
<span id="l1840">     * assertion status, and may be overridden on a per-class basis by invoking</span><a href="#l1840"></a>
<span id="l1841">     * {@link #setClassAssertionStatus(String, boolean)}.  &lt;/p&gt;</span><a href="#l1841"></a>
<span id="l1842">     *</span><a href="#l1842"></a>
<span id="l1843">     * @param  packageName</span><a href="#l1843"></a>
<span id="l1844">     *         The name of the package whose package default assertion status</span><a href="#l1844"></a>
<span id="l1845">     *         is to be set. A &lt;tt&gt;null&lt;/tt&gt; value indicates the unnamed</span><a href="#l1845"></a>
<span id="l1846">     *         package that is &quot;current&quot;</span><a href="#l1846"></a>
<span id="l1847">     *         (&lt;a href=&quot;http://java.sun.com/docs/books/jls/&quot;&gt;Java Language</span><a href="#l1847"></a>
<span id="l1848">     *         Specification&lt;/a&gt;, section 7.4.2).</span><a href="#l1848"></a>
<span id="l1849">     *</span><a href="#l1849"></a>
<span id="l1850">     * @param  enabled</span><a href="#l1850"></a>
<span id="l1851">     *         &lt;tt&gt;true&lt;/tt&gt; if classes loaded by this classloader and</span><a href="#l1851"></a>
<span id="l1852">     *         belonging to the named package or any of its subpackages will</span><a href="#l1852"></a>
<span id="l1853">     *         have assertions enabled by default, &lt;tt&gt;false&lt;/tt&gt; if they will</span><a href="#l1853"></a>
<span id="l1854">     *         have assertions disabled by default.</span><a href="#l1854"></a>
<span id="l1855">     *</span><a href="#l1855"></a>
<span id="l1856">     * @since  1.4</span><a href="#l1856"></a>
<span id="l1857">     */</span><a href="#l1857"></a>
<span id="l1858">    public synchronized void setPackageAssertionStatus(String packageName,</span><a href="#l1858"></a>
<span id="l1859">                                                       boolean enabled)</span><a href="#l1859"></a>
<span id="l1860">    {</span><a href="#l1860"></a>
<span id="l1861">        if (packageAssertionStatus == null)</span><a href="#l1861"></a>
<span id="l1862">            initializeJavaAssertionMaps();</span><a href="#l1862"></a>
<span id="l1863"></span><a href="#l1863"></a>
<span id="l1864">        packageAssertionStatus.put(packageName, Boolean.valueOf(enabled));</span><a href="#l1864"></a>
<span id="l1865">    }</span><a href="#l1865"></a>
<span id="l1866"></span><a href="#l1866"></a>
<span id="l1867">    /**</span><a href="#l1867"></a>
<span id="l1868">     * Sets the desired assertion status for the named top-level class in this</span><a href="#l1868"></a>
<span id="l1869">     * class loader and any nested classes contained therein.  This setting</span><a href="#l1869"></a>
<span id="l1870">     * takes precedence over the class loader's default assertion status, and</span><a href="#l1870"></a>
<span id="l1871">     * over any applicable per-package default.  This method has no effect if</span><a href="#l1871"></a>
<span id="l1872">     * the named class has already been initialized.  (Once a class is</span><a href="#l1872"></a>
<span id="l1873">     * initialized, its assertion status cannot change.)</span><a href="#l1873"></a>
<span id="l1874">     *</span><a href="#l1874"></a>
<span id="l1875">     * &lt;p&gt; If the named class is not a top-level class, this invocation will</span><a href="#l1875"></a>
<span id="l1876">     * have no effect on the actual assertion status of any class. &lt;/p&gt;</span><a href="#l1876"></a>
<span id="l1877">     *</span><a href="#l1877"></a>
<span id="l1878">     * @param  className</span><a href="#l1878"></a>
<span id="l1879">     *         The fully qualified class name of the top-level class whose</span><a href="#l1879"></a>
<span id="l1880">     *         assertion status is to be set.</span><a href="#l1880"></a>
<span id="l1881">     *</span><a href="#l1881"></a>
<span id="l1882">     * @param  enabled</span><a href="#l1882"></a>
<span id="l1883">     *         &lt;tt&gt;true&lt;/tt&gt; if the named class is to have assertions</span><a href="#l1883"></a>
<span id="l1884">     *         enabled when (and if) it is initialized, &lt;tt&gt;false&lt;/tt&gt; if the</span><a href="#l1884"></a>
<span id="l1885">     *         class is to have assertions disabled.</span><a href="#l1885"></a>
<span id="l1886">     *</span><a href="#l1886"></a>
<span id="l1887">     * @since  1.4</span><a href="#l1887"></a>
<span id="l1888">     */</span><a href="#l1888"></a>
<span id="l1889">    public synchronized void setClassAssertionStatus(String className,</span><a href="#l1889"></a>
<span id="l1890">                                                     boolean enabled)</span><a href="#l1890"></a>
<span id="l1891">    {</span><a href="#l1891"></a>
<span id="l1892">        if (classAssertionStatus == null)</span><a href="#l1892"></a>
<span id="l1893">            initializeJavaAssertionMaps();</span><a href="#l1893"></a>
<span id="l1894"></span><a href="#l1894"></a>
<span id="l1895">        classAssertionStatus.put(className, Boolean.valueOf(enabled));</span><a href="#l1895"></a>
<span id="l1896">    }</span><a href="#l1896"></a>
<span id="l1897"></span><a href="#l1897"></a>
<span id="l1898">    /**</span><a href="#l1898"></a>
<span id="l1899">     * Sets the default assertion status for this class loader to</span><a href="#l1899"></a>
<span id="l1900">     * &lt;tt&gt;false&lt;/tt&gt; and discards any package defaults or class assertion</span><a href="#l1900"></a>
<span id="l1901">     * status settings associated with the class loader.  This method is</span><a href="#l1901"></a>
<span id="l1902">     * provided so that class loaders can be made to ignore any command line or</span><a href="#l1902"></a>
<span id="l1903">     * persistent assertion status settings and &quot;start with a clean slate.&quot;</span><a href="#l1903"></a>
<span id="l1904">     * &lt;/p&gt;</span><a href="#l1904"></a>
<span id="l1905">     *</span><a href="#l1905"></a>
<span id="l1906">     * @since  1.4</span><a href="#l1906"></a>
<span id="l1907">     */</span><a href="#l1907"></a>
<span id="l1908">    public synchronized void clearAssertionStatus() {</span><a href="#l1908"></a>
<span id="l1909">        /*</span><a href="#l1909"></a>
<span id="l1910">         * Whether or not &quot;Java assertion maps&quot; are initialized, set</span><a href="#l1910"></a>
<span id="l1911">         * them to empty maps, effectively ignoring any present settings.</span><a href="#l1911"></a>
<span id="l1912">         */</span><a href="#l1912"></a>
<span id="l1913">        classAssertionStatus = new HashMap();</span><a href="#l1913"></a>
<span id="l1914">        packageAssertionStatus = new HashMap();</span><a href="#l1914"></a>
<span id="l1915"></span><a href="#l1915"></a>
<span id="l1916">        defaultAssertionStatus = false;</span><a href="#l1916"></a>
<span id="l1917">    }</span><a href="#l1917"></a>
<span id="l1918"></span><a href="#l1918"></a>
<span id="l1919">    /**</span><a href="#l1919"></a>
<span id="l1920">     * Returns the assertion status that would be assigned to the specified</span><a href="#l1920"></a>
<span id="l1921">     * class if it were to be initialized at the time this method is invoked.</span><a href="#l1921"></a>
<span id="l1922">     * If the named class has had its assertion status set, the most recent</span><a href="#l1922"></a>
<span id="l1923">     * setting will be returned; otherwise, if any package default assertion</span><a href="#l1923"></a>
<span id="l1924">     * status pertains to this class, the most recent setting for the most</span><a href="#l1924"></a>
<span id="l1925">     * specific pertinent package default assertion status is returned;</span><a href="#l1925"></a>
<span id="l1926">     * otherwise, this class loader's default assertion status is returned.</span><a href="#l1926"></a>
<span id="l1927">     * &lt;/p&gt;</span><a href="#l1927"></a>
<span id="l1928">     *</span><a href="#l1928"></a>
<span id="l1929">     * @param  className</span><a href="#l1929"></a>
<span id="l1930">     *         The fully qualified class name of the class whose desired</span><a href="#l1930"></a>
<span id="l1931">     *         assertion status is being queried.</span><a href="#l1931"></a>
<span id="l1932">     *</span><a href="#l1932"></a>
<span id="l1933">     * @return  The desired assertion status of the specified class.</span><a href="#l1933"></a>
<span id="l1934">     *</span><a href="#l1934"></a>
<span id="l1935">     * @see  #setClassAssertionStatus(String, boolean)</span><a href="#l1935"></a>
<span id="l1936">     * @see  #setPackageAssertionStatus(String, boolean)</span><a href="#l1936"></a>
<span id="l1937">     * @see  #setDefaultAssertionStatus(boolean)</span><a href="#l1937"></a>
<span id="l1938">     *</span><a href="#l1938"></a>
<span id="l1939">     * @since  1.4</span><a href="#l1939"></a>
<span id="l1940">     */</span><a href="#l1940"></a>
<span id="l1941">    synchronized boolean desiredAssertionStatus(String className) {</span><a href="#l1941"></a>
<span id="l1942">        Boolean result;</span><a href="#l1942"></a>
<span id="l1943"></span><a href="#l1943"></a>
<span id="l1944">        // assert classAssertionStatus   != null;</span><a href="#l1944"></a>
<span id="l1945">        // assert packageAssertionStatus != null;</span><a href="#l1945"></a>
<span id="l1946"></span><a href="#l1946"></a>
<span id="l1947">        // Check for a class entry</span><a href="#l1947"></a>
<span id="l1948">        result = (Boolean)classAssertionStatus.get(className);</span><a href="#l1948"></a>
<span id="l1949">        if (result != null)</span><a href="#l1949"></a>
<span id="l1950">            return result.booleanValue();</span><a href="#l1950"></a>
<span id="l1951"></span><a href="#l1951"></a>
<span id="l1952">        // Check for most specific package entry</span><a href="#l1952"></a>
<span id="l1953">        int dotIndex = className.lastIndexOf(&quot;.&quot;);</span><a href="#l1953"></a>
<span id="l1954">        if (dotIndex &lt; 0) { // default package</span><a href="#l1954"></a>
<span id="l1955">            result = (Boolean)packageAssertionStatus.get(null);</span><a href="#l1955"></a>
<span id="l1956">            if (result != null)</span><a href="#l1956"></a>
<span id="l1957">                return result.booleanValue();</span><a href="#l1957"></a>
<span id="l1958">        }</span><a href="#l1958"></a>
<span id="l1959">        while(dotIndex &gt; 0) {</span><a href="#l1959"></a>
<span id="l1960">            className = className.substring(0, dotIndex);</span><a href="#l1960"></a>
<span id="l1961">            result = (Boolean)packageAssertionStatus.get(className);</span><a href="#l1961"></a>
<span id="l1962">            if (result != null)</span><a href="#l1962"></a>
<span id="l1963">                return result.booleanValue();</span><a href="#l1963"></a>
<span id="l1964">            dotIndex = className.lastIndexOf(&quot;.&quot;, dotIndex-1);</span><a href="#l1964"></a>
<span id="l1965">        }</span><a href="#l1965"></a>
<span id="l1966"></span><a href="#l1966"></a>
<span id="l1967">        // Return the classloader default</span><a href="#l1967"></a>
<span id="l1968">        return defaultAssertionStatus;</span><a href="#l1968"></a>
<span id="l1969">    }</span><a href="#l1969"></a>
<span id="l1970"></span><a href="#l1970"></a>
<span id="l1971">    // Set up the assertions with information provided by the VM.</span><a href="#l1971"></a>
<span id="l1972">    private void initializeJavaAssertionMaps() {</span><a href="#l1972"></a>
<span id="l1973">        // assert Thread.holdsLock(this);</span><a href="#l1973"></a>
<span id="l1974"></span><a href="#l1974"></a>
<span id="l1975">        classAssertionStatus = new HashMap();</span><a href="#l1975"></a>
<span id="l1976">        packageAssertionStatus = new HashMap();</span><a href="#l1976"></a>
<span id="l1977">        AssertionStatusDirectives directives = retrieveDirectives();</span><a href="#l1977"></a>
<span id="l1978"></span><a href="#l1978"></a>
<span id="l1979">        for(int i = 0; i &lt; directives.classes.length; i++)</span><a href="#l1979"></a>
<span id="l1980">            classAssertionStatus.put(directives.classes[i],</span><a href="#l1980"></a>
<span id="l1981">                              Boolean.valueOf(directives.classEnabled[i]));</span><a href="#l1981"></a>
<span id="l1982"></span><a href="#l1982"></a>
<span id="l1983">        for(int i = 0; i &lt; directives.packages.length; i++)</span><a href="#l1983"></a>
<span id="l1984">            packageAssertionStatus.put(directives.packages[i],</span><a href="#l1984"></a>
<span id="l1985">                              Boolean.valueOf(directives.packageEnabled[i]));</span><a href="#l1985"></a>
<span id="l1986"></span><a href="#l1986"></a>
<span id="l1987">        defaultAssertionStatus = directives.deflt;</span><a href="#l1987"></a>
<span id="l1988">    }</span><a href="#l1988"></a>
<span id="l1989"></span><a href="#l1989"></a>
<span id="l1990">    // Retrieves the assertion directives from the VM.</span><a href="#l1990"></a>
<span id="l1991">    private static native AssertionStatusDirectives retrieveDirectives();</span><a href="#l1991"></a>
<span id="l1992">}</span><a href="#l1992"></a>
<span id="l1993"></span><a href="#l1993"></a>
<span id="l1994"></span><a href="#l1994"></a>
<span id="l1995">class SystemClassLoaderAction implements PrivilegedExceptionAction {</span><a href="#l1995"></a>
<span id="l1996">    private ClassLoader parent;</span><a href="#l1996"></a>
<span id="l1997"></span><a href="#l1997"></a>
<span id="l1998">    SystemClassLoaderAction(ClassLoader parent) {</span><a href="#l1998"></a>
<span id="l1999">        this.parent = parent;</span><a href="#l1999"></a>
<span id="l2000">    }</span><a href="#l2000"></a>
<span id="l2001"></span><a href="#l2001"></a>
<span id="l2002">    public Object run() throws Exception {</span><a href="#l2002"></a>
<span id="l2003">        ClassLoader sys;</span><a href="#l2003"></a>
<span id="l2004">        Constructor ctor;</span><a href="#l2004"></a>
<span id="l2005">        Class c;</span><a href="#l2005"></a>
<span id="l2006">        Class cp[] = { ClassLoader.class };</span><a href="#l2006"></a>
<span id="l2007">        Object params[] = { parent };</span><a href="#l2007"></a>
<span id="l2008"></span><a href="#l2008"></a>
<span id="l2009">        String cls = System.getProperty(&quot;java.system.class.loader&quot;);</span><a href="#l2009"></a>
<span id="l2010">        if (cls == null) {</span><a href="#l2010"></a>
<span id="l2011">            return parent;</span><a href="#l2011"></a>
<span id="l2012">        }</span><a href="#l2012"></a>
<span id="l2013"></span><a href="#l2013"></a>
<span id="l2014">        c = Class.forName(cls, true, parent);</span><a href="#l2014"></a>
<span id="l2015">        ctor = c.getDeclaredConstructor(cp);</span><a href="#l2015"></a>
<span id="l2016">        sys = (ClassLoader) ctor.newInstance(params);</span><a href="#l2016"></a>
<span id="l2017">        Thread.currentThread().setContextClassLoader(sys);</span><a href="#l2017"></a>
<span id="l2018">        return sys;</span><a href="#l2018"></a>
<span id="l2019">    }</span><a href="#l2019"></a>
<span id="l2020">}</span><a href="#l2020"></a></pre>
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

