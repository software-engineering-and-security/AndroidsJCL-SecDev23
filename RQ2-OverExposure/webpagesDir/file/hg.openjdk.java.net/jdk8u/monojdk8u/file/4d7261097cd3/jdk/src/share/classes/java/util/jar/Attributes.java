<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/monojdk8u/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/monojdk8u/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/monojdk8u/static/mercurial.js"></script>

<title>jdk8u/monojdk8u: 4d7261097cd3 jdk/src/share/classes/java/util/jar/Attributes.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/monojdk8u/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/monojdk8u/shortlog/4d7261097cd3">log</a></li>
<li><a href="/jdk8u/monojdk8u/graph/4d7261097cd3">graph</a></li>
<li><a href="/jdk8u/monojdk8u/tags">tags</a></li>
<li><a href="/jdk8u/monojdk8u/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/rev/4d7261097cd3">changeset</a></li>
<li><a href="/jdk8u/monojdk8u/file/4d7261097cd3/jdk/src/share/classes/java/util/jar/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/monojdk8u/file/tip/jdk/src/share/classes/java/util/jar/Attributes.java">latest</a></li>
<li><a href="/jdk8u/monojdk8u/diff/4d7261097cd3/jdk/src/share/classes/java/util/jar/Attributes.java">diff</a></li>
<li><a href="/jdk8u/monojdk8u/comparison/4d7261097cd3/jdk/src/share/classes/java/util/jar/Attributes.java">comparison</a></li>
<li><a href="/jdk8u/monojdk8u/annotate/4d7261097cd3/jdk/src/share/classes/java/util/jar/Attributes.java">annotate</a></li>
<li><a href="/jdk8u/monojdk8u/log/4d7261097cd3/jdk/src/share/classes/java/util/jar/Attributes.java">file log</a></li>
<li><a href="/jdk8u/monojdk8u/raw-file/4d7261097cd3/jdk/src/share/classes/java/util/jar/Attributes.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/monojdk8u/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/monojdk8u">monojdk8u</a> </h2>
<h3>view jdk/src/share/classes/java/util/jar/Attributes.java @ 48819:4d7261097cd3</h3>

<form class="search" action="/jdk8u/monojdk8u/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/monojdk8u/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8272026: Verify Jar Verification
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Fri, 26 Nov 2021 14:57:43 +0300</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/monojdk8u/file/f9133d69dd4d/jdk/src/share/classes/java/util/jar/Attributes.java">f9133d69dd4d</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1997, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.util.jar;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.ByteArrayOutputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.DataInputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.DataOutputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.IOException;</span><a href="#l31"></a>
<span id="l32">import java.util.HashMap;</span><a href="#l32"></a>
<span id="l33">import java.util.Map;</span><a href="#l33"></a>
<span id="l34">import java.util.Set;</span><a href="#l34"></a>
<span id="l35">import java.util.Collection;</span><a href="#l35"></a>
<span id="l36">import java.util.AbstractSet;</span><a href="#l36"></a>
<span id="l37">import java.util.Iterator;</span><a href="#l37"></a>
<span id="l38">import sun.util.logging.PlatformLogger;</span><a href="#l38"></a>
<span id="l39">import java.util.Comparator;</span><a href="#l39"></a>
<span id="l40">import sun.misc.ASCIICaseInsensitiveComparator;</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">/**</span><a href="#l42"></a>
<span id="l43"> * The Attributes class maps Manifest attribute names to associated string</span><a href="#l43"></a>
<span id="l44"> * values. Valid attribute names are case-insensitive, are restricted to</span><a href="#l44"></a>
<span id="l45"> * the ASCII characters in the set [0-9a-zA-Z_-], and cannot exceed 70</span><a href="#l45"></a>
<span id="l46"> * characters in length. Attribute values can contain any characters and</span><a href="#l46"></a>
<span id="l47"> * will be UTF8-encoded when written to the output stream.  See the</span><a href="#l47"></a>
<span id="l48"> * &lt;a href=&quot;../../../../technotes/guides/jar/jar.html&quot;&gt;JAR File Specification&lt;/a&gt;</span><a href="#l48"></a>
<span id="l49"> * for more information about valid attribute names and values.</span><a href="#l49"></a>
<span id="l50"> *</span><a href="#l50"></a>
<span id="l51"> * @author  David Connelly</span><a href="#l51"></a>
<span id="l52"> * @see     Manifest</span><a href="#l52"></a>
<span id="l53"> * @since   1.2</span><a href="#l53"></a>
<span id="l54"> */</span><a href="#l54"></a>
<span id="l55">public class Attributes implements Map&lt;Object,Object&gt;, Cloneable {</span><a href="#l55"></a>
<span id="l56">    /**</span><a href="#l56"></a>
<span id="l57">     * The attribute name-value mappings.</span><a href="#l57"></a>
<span id="l58">     */</span><a href="#l58"></a>
<span id="l59">    protected Map&lt;Object,Object&gt; map;</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    /**</span><a href="#l61"></a>
<span id="l62">     * Constructs a new, empty Attributes object with default size.</span><a href="#l62"></a>
<span id="l63">     */</span><a href="#l63"></a>
<span id="l64">    public Attributes() {</span><a href="#l64"></a>
<span id="l65">        this(11);</span><a href="#l65"></a>
<span id="l66">    }</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    /**</span><a href="#l68"></a>
<span id="l69">     * Constructs a new, empty Attributes object with the specified</span><a href="#l69"></a>
<span id="l70">     * initial size.</span><a href="#l70"></a>
<span id="l71">     *</span><a href="#l71"></a>
<span id="l72">     * @param size the initial number of attributes</span><a href="#l72"></a>
<span id="l73">     */</span><a href="#l73"></a>
<span id="l74">    public Attributes(int size) {</span><a href="#l74"></a>
<span id="l75">        map = new HashMap&lt;&gt;(size);</span><a href="#l75"></a>
<span id="l76">    }</span><a href="#l76"></a>
<span id="l77"></span><a href="#l77"></a>
<span id="l78">    /**</span><a href="#l78"></a>
<span id="l79">     * Constructs a new Attributes object with the same attribute name-value</span><a href="#l79"></a>
<span id="l80">     * mappings as in the specified Attributes.</span><a href="#l80"></a>
<span id="l81">     *</span><a href="#l81"></a>
<span id="l82">     * @param attr the specified Attributes</span><a href="#l82"></a>
<span id="l83">     */</span><a href="#l83"></a>
<span id="l84">    public Attributes(Attributes attr) {</span><a href="#l84"></a>
<span id="l85">        map = new HashMap&lt;&gt;(attr);</span><a href="#l85"></a>
<span id="l86">    }</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /**</span><a href="#l89"></a>
<span id="l90">     * Returns the value of the specified attribute name, or null if the</span><a href="#l90"></a>
<span id="l91">     * attribute name was not found.</span><a href="#l91"></a>
<span id="l92">     *</span><a href="#l92"></a>
<span id="l93">     * @param name the attribute name</span><a href="#l93"></a>
<span id="l94">     * @return the value of the specified attribute name, or null if</span><a href="#l94"></a>
<span id="l95">     *         not found.</span><a href="#l95"></a>
<span id="l96">     */</span><a href="#l96"></a>
<span id="l97">    public Object get(Object name) {</span><a href="#l97"></a>
<span id="l98">        return map.get(name);</span><a href="#l98"></a>
<span id="l99">    }</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    /**</span><a href="#l101"></a>
<span id="l102">     * Returns the value of the specified attribute name, specified as</span><a href="#l102"></a>
<span id="l103">     * a string, or null if the attribute was not found. The attribute</span><a href="#l103"></a>
<span id="l104">     * name is case-insensitive.</span><a href="#l104"></a>
<span id="l105">     * &lt;p&gt;</span><a href="#l105"></a>
<span id="l106">     * This method is defined as:</span><a href="#l106"></a>
<span id="l107">     * &lt;pre&gt;</span><a href="#l107"></a>
<span id="l108">     *      return (String)get(new Attributes.Name((String)name));</span><a href="#l108"></a>
<span id="l109">     * &lt;/pre&gt;</span><a href="#l109"></a>
<span id="l110">     *</span><a href="#l110"></a>
<span id="l111">     * @param name the attribute name as a string</span><a href="#l111"></a>
<span id="l112">     * @return the String value of the specified attribute name, or null if</span><a href="#l112"></a>
<span id="l113">     *         not found.</span><a href="#l113"></a>
<span id="l114">     * @throws IllegalArgumentException if the attribute name is invalid</span><a href="#l114"></a>
<span id="l115">     */</span><a href="#l115"></a>
<span id="l116">    public String getValue(String name) {</span><a href="#l116"></a>
<span id="l117">        return (String)get(new Attributes.Name(name));</span><a href="#l117"></a>
<span id="l118">    }</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">    /**</span><a href="#l120"></a>
<span id="l121">     * Returns the value of the specified Attributes.Name, or null if the</span><a href="#l121"></a>
<span id="l122">     * attribute was not found.</span><a href="#l122"></a>
<span id="l123">     * &lt;p&gt;</span><a href="#l123"></a>
<span id="l124">     * This method is defined as:</span><a href="#l124"></a>
<span id="l125">     * &lt;pre&gt;</span><a href="#l125"></a>
<span id="l126">     *     return (String)get(name);</span><a href="#l126"></a>
<span id="l127">     * &lt;/pre&gt;</span><a href="#l127"></a>
<span id="l128">     *</span><a href="#l128"></a>
<span id="l129">     * @param name the Attributes.Name object</span><a href="#l129"></a>
<span id="l130">     * @return the String value of the specified Attribute.Name, or null if</span><a href="#l130"></a>
<span id="l131">     *         not found.</span><a href="#l131"></a>
<span id="l132">     */</span><a href="#l132"></a>
<span id="l133">    public String getValue(Name name) {</span><a href="#l133"></a>
<span id="l134">        return (String)get(name);</span><a href="#l134"></a>
<span id="l135">    }</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">    /**</span><a href="#l137"></a>
<span id="l138">     * Associates the specified value with the specified attribute name</span><a href="#l138"></a>
<span id="l139">     * (key) in this Map. If the Map previously contained a mapping for</span><a href="#l139"></a>
<span id="l140">     * the attribute name, the old value is replaced.</span><a href="#l140"></a>
<span id="l141">     *</span><a href="#l141"></a>
<span id="l142">     * @param name the attribute name</span><a href="#l142"></a>
<span id="l143">     * @param value the attribute value</span><a href="#l143"></a>
<span id="l144">     * @return the previous value of the attribute, or null if none</span><a href="#l144"></a>
<span id="l145">     * @exception ClassCastException if the name is not a Attributes.Name</span><a href="#l145"></a>
<span id="l146">     *            or the value is not a String</span><a href="#l146"></a>
<span id="l147">     */</span><a href="#l147"></a>
<span id="l148">    public Object put(Object name, Object value) {</span><a href="#l148"></a>
<span id="l149">        return map.put((Attributes.Name)name, (String)value);</span><a href="#l149"></a>
<span id="l150">    }</span><a href="#l150"></a>
<span id="l151"></span><a href="#l151"></a>
<span id="l152">    /**</span><a href="#l152"></a>
<span id="l153">     * Associates the specified value with the specified attribute name,</span><a href="#l153"></a>
<span id="l154">     * specified as a String. The attributes name is case-insensitive.</span><a href="#l154"></a>
<span id="l155">     * If the Map previously contained a mapping for the attribute name,</span><a href="#l155"></a>
<span id="l156">     * the old value is replaced.</span><a href="#l156"></a>
<span id="l157">     * &lt;p&gt;</span><a href="#l157"></a>
<span id="l158">     * This method is defined as:</span><a href="#l158"></a>
<span id="l159">     * &lt;pre&gt;</span><a href="#l159"></a>
<span id="l160">     *      return (String)put(new Attributes.Name(name), value);</span><a href="#l160"></a>
<span id="l161">     * &lt;/pre&gt;</span><a href="#l161"></a>
<span id="l162">     *</span><a href="#l162"></a>
<span id="l163">     * @param name the attribute name as a string</span><a href="#l163"></a>
<span id="l164">     * @param value the attribute value</span><a href="#l164"></a>
<span id="l165">     * @return the previous value of the attribute, or null if none</span><a href="#l165"></a>
<span id="l166">     * @exception IllegalArgumentException if the attribute name is invalid</span><a href="#l166"></a>
<span id="l167">     */</span><a href="#l167"></a>
<span id="l168">    public String putValue(String name, String value) {</span><a href="#l168"></a>
<span id="l169">        return (String)put(new Name(name), value);</span><a href="#l169"></a>
<span id="l170">    }</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    /**</span><a href="#l172"></a>
<span id="l173">     * Removes the attribute with the specified name (key) from this Map.</span><a href="#l173"></a>
<span id="l174">     * Returns the previous attribute value, or null if none.</span><a href="#l174"></a>
<span id="l175">     *</span><a href="#l175"></a>
<span id="l176">     * @param name attribute name</span><a href="#l176"></a>
<span id="l177">     * @return the previous value of the attribute, or null if none</span><a href="#l177"></a>
<span id="l178">     */</span><a href="#l178"></a>
<span id="l179">    public Object remove(Object name) {</span><a href="#l179"></a>
<span id="l180">        return map.remove(name);</span><a href="#l180"></a>
<span id="l181">    }</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">    /**</span><a href="#l183"></a>
<span id="l184">     * Returns true if this Map maps one or more attribute names (keys)</span><a href="#l184"></a>
<span id="l185">     * to the specified value.</span><a href="#l185"></a>
<span id="l186">     *</span><a href="#l186"></a>
<span id="l187">     * @param value the attribute value</span><a href="#l187"></a>
<span id="l188">     * @return true if this Map maps one or more attribute names to</span><a href="#l188"></a>
<span id="l189">     *         the specified value</span><a href="#l189"></a>
<span id="l190">     */</span><a href="#l190"></a>
<span id="l191">    public boolean containsValue(Object value) {</span><a href="#l191"></a>
<span id="l192">        return map.containsValue(value);</span><a href="#l192"></a>
<span id="l193">    }</span><a href="#l193"></a>
<span id="l194"></span><a href="#l194"></a>
<span id="l195">    /**</span><a href="#l195"></a>
<span id="l196">     * Returns true if this Map contains the specified attribute name (key).</span><a href="#l196"></a>
<span id="l197">     *</span><a href="#l197"></a>
<span id="l198">     * @param name the attribute name</span><a href="#l198"></a>
<span id="l199">     * @return true if this Map contains the specified attribute name</span><a href="#l199"></a>
<span id="l200">     */</span><a href="#l200"></a>
<span id="l201">    public boolean containsKey(Object name) {</span><a href="#l201"></a>
<span id="l202">        return map.containsKey(name);</span><a href="#l202"></a>
<span id="l203">    }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    /**</span><a href="#l205"></a>
<span id="l206">     * Copies all of the attribute name-value mappings from the specified</span><a href="#l206"></a>
<span id="l207">     * Attributes to this Map. Duplicate mappings will be replaced.</span><a href="#l207"></a>
<span id="l208">     *</span><a href="#l208"></a>
<span id="l209">     * @param attr the Attributes to be stored in this map</span><a href="#l209"></a>
<span id="l210">     * @exception ClassCastException if attr is not an Attributes</span><a href="#l210"></a>
<span id="l211">     */</span><a href="#l211"></a>
<span id="l212">    public void putAll(Map&lt;?,?&gt; attr) {</span><a href="#l212"></a>
<span id="l213">        // ## javac bug?</span><a href="#l213"></a>
<span id="l214">        if (!Attributes.class.isInstance(attr))</span><a href="#l214"></a>
<span id="l215">            throw new ClassCastException();</span><a href="#l215"></a>
<span id="l216">        for (Map.Entry&lt;?,?&gt; me : (attr).entrySet())</span><a href="#l216"></a>
<span id="l217">            put(me.getKey(), me.getValue());</span><a href="#l217"></a>
<span id="l218">    }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">    /**</span><a href="#l220"></a>
<span id="l221">     * Removes all attributes from this Map.</span><a href="#l221"></a>
<span id="l222">     */</span><a href="#l222"></a>
<span id="l223">    public void clear() {</span><a href="#l223"></a>
<span id="l224">        map.clear();</span><a href="#l224"></a>
<span id="l225">    }</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    /**</span><a href="#l227"></a>
<span id="l228">     * Returns the number of attributes in this Map.</span><a href="#l228"></a>
<span id="l229">     */</span><a href="#l229"></a>
<span id="l230">    public int size() {</span><a href="#l230"></a>
<span id="l231">        return map.size();</span><a href="#l231"></a>
<span id="l232">    }</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">    /**</span><a href="#l234"></a>
<span id="l235">     * Returns true if this Map contains no attributes.</span><a href="#l235"></a>
<span id="l236">     */</span><a href="#l236"></a>
<span id="l237">    public boolean isEmpty() {</span><a href="#l237"></a>
<span id="l238">        return map.isEmpty();</span><a href="#l238"></a>
<span id="l239">    }</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">    /**</span><a href="#l241"></a>
<span id="l242">     * Returns a Set view of the attribute names (keys) contained in this Map.</span><a href="#l242"></a>
<span id="l243">     */</span><a href="#l243"></a>
<span id="l244">    public Set&lt;Object&gt; keySet() {</span><a href="#l244"></a>
<span id="l245">        return map.keySet();</span><a href="#l245"></a>
<span id="l246">    }</span><a href="#l246"></a>
<span id="l247"></span><a href="#l247"></a>
<span id="l248">    /**</span><a href="#l248"></a>
<span id="l249">     * Returns a Collection view of the attribute values contained in this Map.</span><a href="#l249"></a>
<span id="l250">     */</span><a href="#l250"></a>
<span id="l251">    public Collection&lt;Object&gt; values() {</span><a href="#l251"></a>
<span id="l252">        return map.values();</span><a href="#l252"></a>
<span id="l253">    }</span><a href="#l253"></a>
<span id="l254"></span><a href="#l254"></a>
<span id="l255">    /**</span><a href="#l255"></a>
<span id="l256">     * Returns a Collection view of the attribute name-value mappings</span><a href="#l256"></a>
<span id="l257">     * contained in this Map.</span><a href="#l257"></a>
<span id="l258">     */</span><a href="#l258"></a>
<span id="l259">    public Set&lt;Map.Entry&lt;Object,Object&gt;&gt; entrySet() {</span><a href="#l259"></a>
<span id="l260">        return map.entrySet();</span><a href="#l260"></a>
<span id="l261">    }</span><a href="#l261"></a>
<span id="l262"></span><a href="#l262"></a>
<span id="l263">    /**</span><a href="#l263"></a>
<span id="l264">     * Compares the specified Attributes object with this Map for equality.</span><a href="#l264"></a>
<span id="l265">     * Returns true if the given object is also an instance of Attributes</span><a href="#l265"></a>
<span id="l266">     * and the two Attributes objects represent the same mappings.</span><a href="#l266"></a>
<span id="l267">     *</span><a href="#l267"></a>
<span id="l268">     * @param o the Object to be compared</span><a href="#l268"></a>
<span id="l269">     * @return true if the specified Object is equal to this Map</span><a href="#l269"></a>
<span id="l270">     */</span><a href="#l270"></a>
<span id="l271">    public boolean equals(Object o) {</span><a href="#l271"></a>
<span id="l272">        return map.equals(o);</span><a href="#l272"></a>
<span id="l273">    }</span><a href="#l273"></a>
<span id="l274"></span><a href="#l274"></a>
<span id="l275">    /**</span><a href="#l275"></a>
<span id="l276">     * Returns the hash code value for this Map.</span><a href="#l276"></a>
<span id="l277">     */</span><a href="#l277"></a>
<span id="l278">    public int hashCode() {</span><a href="#l278"></a>
<span id="l279">        return map.hashCode();</span><a href="#l279"></a>
<span id="l280">    }</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">    /**</span><a href="#l282"></a>
<span id="l283">     * Returns a copy of the Attributes, implemented as follows:</span><a href="#l283"></a>
<span id="l284">     * &lt;pre&gt;</span><a href="#l284"></a>
<span id="l285">     *     public Object clone() { return new Attributes(this); }</span><a href="#l285"></a>
<span id="l286">     * &lt;/pre&gt;</span><a href="#l286"></a>
<span id="l287">     * Since the attribute names and values are themselves immutable,</span><a href="#l287"></a>
<span id="l288">     * the Attributes returned can be safely modified without affecting</span><a href="#l288"></a>
<span id="l289">     * the original.</span><a href="#l289"></a>
<span id="l290">     */</span><a href="#l290"></a>
<span id="l291">    public Object clone() {</span><a href="#l291"></a>
<span id="l292">        return new Attributes(this);</span><a href="#l292"></a>
<span id="l293">    }</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">    /*</span><a href="#l295"></a>
<span id="l296">     * Writes the current attributes to the specified data output stream.</span><a href="#l296"></a>
<span id="l297">     * XXX Need to handle UTF8 values and break up lines longer than 72 bytes</span><a href="#l297"></a>
<span id="l298">     */</span><a href="#l298"></a>
<span id="l299">     void write(DataOutputStream os) throws IOException {</span><a href="#l299"></a>
<span id="l300">        Iterator&lt;Map.Entry&lt;Object, Object&gt;&gt; it = entrySet().iterator();</span><a href="#l300"></a>
<span id="l301">        while (it.hasNext()) {</span><a href="#l301"></a>
<span id="l302">            Map.Entry&lt;Object, Object&gt; e = it.next();</span><a href="#l302"></a>
<span id="l303">            StringBuffer buffer = new StringBuffer(</span><a href="#l303"></a>
<span id="l304">                                        ((Name)e.getKey()).toString());</span><a href="#l304"></a>
<span id="l305">            buffer.append(&quot;: &quot;);</span><a href="#l305"></a>
<span id="l306"></span><a href="#l306"></a>
<span id="l307">            String value = (String)e.getValue();</span><a href="#l307"></a>
<span id="l308">            if (value != null) {</span><a href="#l308"></a>
<span id="l309">                byte[] vb = value.getBytes(&quot;UTF8&quot;);</span><a href="#l309"></a>
<span id="l310">                value = new String(vb, 0, 0, vb.length);</span><a href="#l310"></a>
<span id="l311">            }</span><a href="#l311"></a>
<span id="l312">            buffer.append(value);</span><a href="#l312"></a>
<span id="l313"></span><a href="#l313"></a>
<span id="l314">            buffer.append(&quot;\r\n&quot;);</span><a href="#l314"></a>
<span id="l315">            Manifest.make72Safe(buffer);</span><a href="#l315"></a>
<span id="l316">            os.writeBytes(buffer.toString());</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">        os.writeBytes(&quot;\r\n&quot;);</span><a href="#l318"></a>
<span id="l319">    }</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    /*</span><a href="#l321"></a>
<span id="l322">     * Writes the current attributes to the specified data output stream,</span><a href="#l322"></a>
<span id="l323">     * make sure to write out the MANIFEST_VERSION or SIGNATURE_VERSION</span><a href="#l323"></a>
<span id="l324">     * attributes first.</span><a href="#l324"></a>
<span id="l325">     *</span><a href="#l325"></a>
<span id="l326">     * XXX Need to handle UTF8 values and break up lines longer than 72 bytes</span><a href="#l326"></a>
<span id="l327">     */</span><a href="#l327"></a>
<span id="l328">    void writeMain(DataOutputStream out) throws IOException</span><a href="#l328"></a>
<span id="l329">    {</span><a href="#l329"></a>
<span id="l330">        // write out the *-Version header first, if it exists</span><a href="#l330"></a>
<span id="l331">        String vername = Name.MANIFEST_VERSION.toString();</span><a href="#l331"></a>
<span id="l332">        String version = getValue(vername);</span><a href="#l332"></a>
<span id="l333">        if (version == null) {</span><a href="#l333"></a>
<span id="l334">            vername = Name.SIGNATURE_VERSION.toString();</span><a href="#l334"></a>
<span id="l335">            version = getValue(vername);</span><a href="#l335"></a>
<span id="l336">        }</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">        if (version != null) {</span><a href="#l338"></a>
<span id="l339">            out.writeBytes(vername+&quot;: &quot;+version+&quot;\r\n&quot;);</span><a href="#l339"></a>
<span id="l340">        }</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">        // write out all attributes except for the version</span><a href="#l342"></a>
<span id="l343">        // we wrote out earlier</span><a href="#l343"></a>
<span id="l344">        Iterator&lt;Map.Entry&lt;Object, Object&gt;&gt; it = entrySet().iterator();</span><a href="#l344"></a>
<span id="l345">        while (it.hasNext()) {</span><a href="#l345"></a>
<span id="l346">            Map.Entry&lt;Object, Object&gt; e = it.next();</span><a href="#l346"></a>
<span id="l347">            String name = ((Name)e.getKey()).toString();</span><a href="#l347"></a>
<span id="l348">            if ((version != null) &amp;&amp; ! (name.equalsIgnoreCase(vername))) {</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">                StringBuffer buffer = new StringBuffer(name);</span><a href="#l350"></a>
<span id="l351">                buffer.append(&quot;: &quot;);</span><a href="#l351"></a>
<span id="l352"></span><a href="#l352"></a>
<span id="l353">                String value = (String)e.getValue();</span><a href="#l353"></a>
<span id="l354">                if (value != null) {</span><a href="#l354"></a>
<span id="l355">                    byte[] vb = value.getBytes(&quot;UTF8&quot;);</span><a href="#l355"></a>
<span id="l356">                    value = new String(vb, 0, 0, vb.length);</span><a href="#l356"></a>
<span id="l357">                }</span><a href="#l357"></a>
<span id="l358">                buffer.append(value);</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">                buffer.append(&quot;\r\n&quot;);</span><a href="#l360"></a>
<span id="l361">                Manifest.make72Safe(buffer);</span><a href="#l361"></a>
<span id="l362">                out.writeBytes(buffer.toString());</span><a href="#l362"></a>
<span id="l363">            }</span><a href="#l363"></a>
<span id="l364">        }</span><a href="#l364"></a>
<span id="l365">        out.writeBytes(&quot;\r\n&quot;);</span><a href="#l365"></a>
<span id="l366">    }</span><a href="#l366"></a>
<span id="l367"></span><a href="#l367"></a>
<span id="l368">    /*</span><a href="#l368"></a>
<span id="l369">     * Reads attributes from the specified input stream.</span><a href="#l369"></a>
<span id="l370">     * XXX Need to handle UTF8 values.</span><a href="#l370"></a>
<span id="l371">     */</span><a href="#l371"></a>
<span id="l372">    void read(Manifest.FastInputStream is, byte[] lbuf) throws IOException {</span><a href="#l372"></a>
<span id="l373">        String name = null, value = null;</span><a href="#l373"></a>
<span id="l374">        ByteArrayOutputStream fullLine = new ByteArrayOutputStream();</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">        int len;</span><a href="#l376"></a>
<span id="l377">        while ((len = is.readLine(lbuf)) != -1) {</span><a href="#l377"></a>
<span id="l378">            boolean lineContinued = false;</span><a href="#l378"></a>
<span id="l379">            if (lbuf[--len] != '\n') {</span><a href="#l379"></a>
<span id="l380">                throw new IOException(&quot;line too long&quot;);</span><a href="#l380"></a>
<span id="l381">            }</span><a href="#l381"></a>
<span id="l382">            if (len &gt; 0 &amp;&amp; lbuf[len-1] == '\r') {</span><a href="#l382"></a>
<span id="l383">                --len;</span><a href="#l383"></a>
<span id="l384">            }</span><a href="#l384"></a>
<span id="l385">            if (len == 0) {</span><a href="#l385"></a>
<span id="l386">                break;</span><a href="#l386"></a>
<span id="l387">            }</span><a href="#l387"></a>
<span id="l388">            int i = 0;</span><a href="#l388"></a>
<span id="l389">            if (lbuf[0] == ' ') {</span><a href="#l389"></a>
<span id="l390">                // continuation of previous line</span><a href="#l390"></a>
<span id="l391">                if (name == null) {</span><a href="#l391"></a>
<span id="l392">                    throw new IOException(&quot;misplaced continuation line&quot;);</span><a href="#l392"></a>
<span id="l393">                }</span><a href="#l393"></a>
<span id="l394">                lineContinued = true;</span><a href="#l394"></a>
<span id="l395">                fullLine.write(lbuf, 1, len - 1);</span><a href="#l395"></a>
<span id="l396">                if (is.peek() == ' ') {</span><a href="#l396"></a>
<span id="l397">                    continue;</span><a href="#l397"></a>
<span id="l398">                }</span><a href="#l398"></a>
<span id="l399">                value = fullLine.toString(&quot;UTF8&quot;);</span><a href="#l399"></a>
<span id="l400">                fullLine.reset();</span><a href="#l400"></a>
<span id="l401">            } else {</span><a href="#l401"></a>
<span id="l402">                while (lbuf[i++] != ':') {</span><a href="#l402"></a>
<span id="l403">                    if (i &gt;= len) {</span><a href="#l403"></a>
<span id="l404">                        throw new IOException(&quot;invalid header field&quot;);</span><a href="#l404"></a>
<span id="l405">                    }</span><a href="#l405"></a>
<span id="l406">                }</span><a href="#l406"></a>
<span id="l407">                if (lbuf[i++] != ' ') {</span><a href="#l407"></a>
<span id="l408">                    throw new IOException(&quot;invalid header field&quot;);</span><a href="#l408"></a>
<span id="l409">                }</span><a href="#l409"></a>
<span id="l410">                name = new String(lbuf, 0, 0, i - 2);</span><a href="#l410"></a>
<span id="l411">                if (is.peek() == ' ') {</span><a href="#l411"></a>
<span id="l412">                    fullLine.reset();</span><a href="#l412"></a>
<span id="l413">                    fullLine.write(lbuf, i, len - i);</span><a href="#l413"></a>
<span id="l414">                    continue;</span><a href="#l414"></a>
<span id="l415">                }</span><a href="#l415"></a>
<span id="l416">                value = new String(lbuf, i, len - i, &quot;UTF8&quot;);</span><a href="#l416"></a>
<span id="l417">            }</span><a href="#l417"></a>
<span id="l418">            try {</span><a href="#l418"></a>
<span id="l419">                if ((putValue(name, value) != null) &amp;&amp; (!lineContinued)) {</span><a href="#l419"></a>
<span id="l420">                    PlatformLogger.getLogger(&quot;java.util.jar&quot;).warning(</span><a href="#l420"></a>
<span id="l421">                                     &quot;Duplicate name in Manifest: &quot; + name</span><a href="#l421"></a>
<span id="l422">                                     + &quot;.\n&quot;</span><a href="#l422"></a>
<span id="l423">                                     + &quot;Ensure that the manifest does not &quot;</span><a href="#l423"></a>
<span id="l424">                                     + &quot;have duplicate entries, and\n&quot;</span><a href="#l424"></a>
<span id="l425">                                     + &quot;that blank lines separate &quot;</span><a href="#l425"></a>
<span id="l426">                                     + &quot;individual sections in both your\n&quot;</span><a href="#l426"></a>
<span id="l427">                                     + &quot;manifest and in the META-INF/MANIFEST.MF &quot;</span><a href="#l427"></a>
<span id="l428">                                     + &quot;entry in the jar file.&quot;);</span><a href="#l428"></a>
<span id="l429">                }</span><a href="#l429"></a>
<span id="l430">            } catch (IllegalArgumentException e) {</span><a href="#l430"></a>
<span id="l431">                throw new IOException(&quot;invalid header field name: &quot; + name);</span><a href="#l431"></a>
<span id="l432">            }</span><a href="#l432"></a>
<span id="l433">        }</span><a href="#l433"></a>
<span id="l434">    }</span><a href="#l434"></a>
<span id="l435"></span><a href="#l435"></a>
<span id="l436">    /**</span><a href="#l436"></a>
<span id="l437">     * The Attributes.Name class represents an attribute name stored in</span><a href="#l437"></a>
<span id="l438">     * this Map. Valid attribute names are case-insensitive, are restricted</span><a href="#l438"></a>
<span id="l439">     * to the ASCII characters in the set [0-9a-zA-Z_-], and cannot exceed</span><a href="#l439"></a>
<span id="l440">     * 70 characters in length. Attribute values can contain any characters</span><a href="#l440"></a>
<span id="l441">     * and will be UTF8-encoded when written to the output stream.  See the</span><a href="#l441"></a>
<span id="l442">     * &lt;a href=&quot;../../../../technotes/guides/jar/jar.html&quot;&gt;JAR File Specification&lt;/a&gt;</span><a href="#l442"></a>
<span id="l443">     * for more information about valid attribute names and values.</span><a href="#l443"></a>
<span id="l444">     */</span><a href="#l444"></a>
<span id="l445">    public static class Name {</span><a href="#l445"></a>
<span id="l446">        private String name;</span><a href="#l446"></a>
<span id="l447">        private int hashCode = -1;</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">        /**</span><a href="#l449"></a>
<span id="l450">         * Constructs a new attribute name using the given string name.</span><a href="#l450"></a>
<span id="l451">         *</span><a href="#l451"></a>
<span id="l452">         * @param name the attribute string name</span><a href="#l452"></a>
<span id="l453">         * @exception IllegalArgumentException if the attribute name was</span><a href="#l453"></a>
<span id="l454">         *            invalid</span><a href="#l454"></a>
<span id="l455">         * @exception NullPointerException if the attribute name was null</span><a href="#l455"></a>
<span id="l456">         */</span><a href="#l456"></a>
<span id="l457">        public Name(String name) {</span><a href="#l457"></a>
<span id="l458">            if (name == null) {</span><a href="#l458"></a>
<span id="l459">                throw new NullPointerException(&quot;name&quot;);</span><a href="#l459"></a>
<span id="l460">            }</span><a href="#l460"></a>
<span id="l461">            if (!isValid(name)) {</span><a href="#l461"></a>
<span id="l462">                throw new IllegalArgumentException(name);</span><a href="#l462"></a>
<span id="l463">            }</span><a href="#l463"></a>
<span id="l464">            this.name = name.intern();</span><a href="#l464"></a>
<span id="l465">        }</span><a href="#l465"></a>
<span id="l466"></span><a href="#l466"></a>
<span id="l467">        private static boolean isValid(String name) {</span><a href="#l467"></a>
<span id="l468">            int len = name.length();</span><a href="#l468"></a>
<span id="l469">            if (len &gt; 70 || len == 0) {</span><a href="#l469"></a>
<span id="l470">                return false;</span><a href="#l470"></a>
<span id="l471">            }</span><a href="#l471"></a>
<span id="l472">            for (int i = 0; i &lt; len; i++) {</span><a href="#l472"></a>
<span id="l473">                if (!isValid(name.charAt(i))) {</span><a href="#l473"></a>
<span id="l474">                    return false;</span><a href="#l474"></a>
<span id="l475">                }</span><a href="#l475"></a>
<span id="l476">            }</span><a href="#l476"></a>
<span id="l477">            return true;</span><a href="#l477"></a>
<span id="l478">        }</span><a href="#l478"></a>
<span id="l479"></span><a href="#l479"></a>
<span id="l480">        private static boolean isValid(char c) {</span><a href="#l480"></a>
<span id="l481">            return isAlpha(c) || isDigit(c) || c == '_' || c == '-';</span><a href="#l481"></a>
<span id="l482">        }</span><a href="#l482"></a>
<span id="l483"></span><a href="#l483"></a>
<span id="l484">        private static boolean isAlpha(char c) {</span><a href="#l484"></a>
<span id="l485">            return (c &gt;= 'a' &amp;&amp; c &lt;= 'z') || (c &gt;= 'A' &amp;&amp; c &lt;= 'Z');</span><a href="#l485"></a>
<span id="l486">        }</span><a href="#l486"></a>
<span id="l487"></span><a href="#l487"></a>
<span id="l488">        private static boolean isDigit(char c) {</span><a href="#l488"></a>
<span id="l489">            return c &gt;= '0' &amp;&amp; c &lt;= '9';</span><a href="#l489"></a>
<span id="l490">        }</span><a href="#l490"></a>
<span id="l491"></span><a href="#l491"></a>
<span id="l492">        /**</span><a href="#l492"></a>
<span id="l493">         * Compares this attribute name to another for equality.</span><a href="#l493"></a>
<span id="l494">         * @param o the object to compare</span><a href="#l494"></a>
<span id="l495">         * @return true if this attribute name is equal to the</span><a href="#l495"></a>
<span id="l496">         *         specified attribute object</span><a href="#l496"></a>
<span id="l497">         */</span><a href="#l497"></a>
<span id="l498">        public boolean equals(Object o) {</span><a href="#l498"></a>
<span id="l499">            if (o instanceof Name) {</span><a href="#l499"></a>
<span id="l500">                Comparator&lt;String&gt; c = ASCIICaseInsensitiveComparator.CASE_INSENSITIVE_ORDER;</span><a href="#l500"></a>
<span id="l501">                return c.compare(name, ((Name)o).name) == 0;</span><a href="#l501"></a>
<span id="l502">            } else {</span><a href="#l502"></a>
<span id="l503">                return false;</span><a href="#l503"></a>
<span id="l504">            }</span><a href="#l504"></a>
<span id="l505">        }</span><a href="#l505"></a>
<span id="l506"></span><a href="#l506"></a>
<span id="l507">        /**</span><a href="#l507"></a>
<span id="l508">         * Computes the hash value for this attribute name.</span><a href="#l508"></a>
<span id="l509">         */</span><a href="#l509"></a>
<span id="l510">        public int hashCode() {</span><a href="#l510"></a>
<span id="l511">            if (hashCode == -1) {</span><a href="#l511"></a>
<span id="l512">                hashCode = ASCIICaseInsensitiveComparator.lowerCaseHashCode(name);</span><a href="#l512"></a>
<span id="l513">            }</span><a href="#l513"></a>
<span id="l514">            return hashCode;</span><a href="#l514"></a>
<span id="l515">        }</span><a href="#l515"></a>
<span id="l516"></span><a href="#l516"></a>
<span id="l517">        /**</span><a href="#l517"></a>
<span id="l518">         * Returns the attribute name as a String.</span><a href="#l518"></a>
<span id="l519">         */</span><a href="#l519"></a>
<span id="l520">        public String toString() {</span><a href="#l520"></a>
<span id="l521">            return name;</span><a href="#l521"></a>
<span id="l522">        }</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">        /**</span><a href="#l524"></a>
<span id="l525">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Manifest-Version&lt;/code&gt;</span><a href="#l525"></a>
<span id="l526">         * manifest attribute. This attribute indicates the version number</span><a href="#l526"></a>
<span id="l527">         * of the manifest standard to which a JAR file's manifest conforms.</span><a href="#l527"></a>
<span id="l528">         * @see &lt;a href=&quot;../../../../technotes/guides/jar/jar.html#JAR_Manifest&quot;&gt;</span><a href="#l528"></a>
<span id="l529">         *      Manifest and Signature Specification&lt;/a&gt;</span><a href="#l529"></a>
<span id="l530">         */</span><a href="#l530"></a>
<span id="l531">        public static final Name MANIFEST_VERSION = new Name(&quot;Manifest-Version&quot;);</span><a href="#l531"></a>
<span id="l532"></span><a href="#l532"></a>
<span id="l533">        /**</span><a href="#l533"></a>
<span id="l534">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Signature-Version&lt;/code&gt;</span><a href="#l534"></a>
<span id="l535">         * manifest attribute used when signing JAR files.</span><a href="#l535"></a>
<span id="l536">         * @see &lt;a href=&quot;../../../../technotes/guides/jar/jar.html#JAR_Manifest&quot;&gt;</span><a href="#l536"></a>
<span id="l537">         *      Manifest and Signature Specification&lt;/a&gt;</span><a href="#l537"></a>
<span id="l538">         */</span><a href="#l538"></a>
<span id="l539">        public static final Name SIGNATURE_VERSION = new Name(&quot;Signature-Version&quot;);</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">        /**</span><a href="#l541"></a>
<span id="l542">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Content-Type&lt;/code&gt;</span><a href="#l542"></a>
<span id="l543">         * manifest attribute.</span><a href="#l543"></a>
<span id="l544">         */</span><a href="#l544"></a>
<span id="l545">        public static final Name CONTENT_TYPE = new Name(&quot;Content-Type&quot;);</span><a href="#l545"></a>
<span id="l546"></span><a href="#l546"></a>
<span id="l547">        /**</span><a href="#l547"></a>
<span id="l548">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Class-Path&lt;/code&gt;</span><a href="#l548"></a>
<span id="l549">         * manifest attribute. Bundled extensions can use this attribute</span><a href="#l549"></a>
<span id="l550">         * to find other JAR files containing needed classes.</span><a href="#l550"></a>
<span id="l551">         * @see &lt;a href=&quot;../../../../technotes/guides/jar/jar.html#classpath&quot;&gt;</span><a href="#l551"></a>
<span id="l552">         *      JAR file specification&lt;/a&gt;</span><a href="#l552"></a>
<span id="l553">         */</span><a href="#l553"></a>
<span id="l554">        public static final Name CLASS_PATH = new Name(&quot;Class-Path&quot;);</span><a href="#l554"></a>
<span id="l555"></span><a href="#l555"></a>
<span id="l556">        /**</span><a href="#l556"></a>
<span id="l557">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Main-Class&lt;/code&gt; manifest</span><a href="#l557"></a>
<span id="l558">         * attribute used for launching applications packaged in JAR files.</span><a href="#l558"></a>
<span id="l559">         * The &lt;code&gt;Main-Class&lt;/code&gt; attribute is used in conjunction</span><a href="#l559"></a>
<span id="l560">         * with the &lt;code&gt;-jar&lt;/code&gt; command-line option of the</span><a href="#l560"></a>
<span id="l561">         * &lt;tt&gt;java&lt;/tt&gt; application launcher.</span><a href="#l561"></a>
<span id="l562">         */</span><a href="#l562"></a>
<span id="l563">        public static final Name MAIN_CLASS = new Name(&quot;Main-Class&quot;);</span><a href="#l563"></a>
<span id="l564"></span><a href="#l564"></a>
<span id="l565">        /**</span><a href="#l565"></a>
<span id="l566">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Sealed&lt;/code&gt; manifest attribute</span><a href="#l566"></a>
<span id="l567">         * used for sealing.</span><a href="#l567"></a>
<span id="l568">         * @see &lt;a href=&quot;../../../../technotes/guides/jar/jar.html#sealing&quot;&gt;</span><a href="#l568"></a>
<span id="l569">         *      Package Sealing&lt;/a&gt;</span><a href="#l569"></a>
<span id="l570">         */</span><a href="#l570"></a>
<span id="l571">        public static final Name SEALED = new Name(&quot;Sealed&quot;);</span><a href="#l571"></a>
<span id="l572"></span><a href="#l572"></a>
<span id="l573">       /**</span><a href="#l573"></a>
<span id="l574">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Extension-List&lt;/code&gt; manifest attribute</span><a href="#l574"></a>
<span id="l575">         * used for declaring dependencies on installed extensions.</span><a href="#l575"></a>
<span id="l576">         * @see &lt;a href=&quot;../../../../technotes/guides/extensions/spec.html#dependency&quot;&gt;</span><a href="#l576"></a>
<span id="l577">         *      Installed extension dependency&lt;/a&gt;</span><a href="#l577"></a>
<span id="l578">         */</span><a href="#l578"></a>
<span id="l579">        public static final Name EXTENSION_LIST = new Name(&quot;Extension-List&quot;);</span><a href="#l579"></a>
<span id="l580"></span><a href="#l580"></a>
<span id="l581">        /**</span><a href="#l581"></a>
<span id="l582">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Extension-Name&lt;/code&gt; manifest attribute</span><a href="#l582"></a>
<span id="l583">         * used for declaring dependencies on installed extensions.</span><a href="#l583"></a>
<span id="l584">         * @see &lt;a href=&quot;../../../../technotes/guides/extensions/spec.html#dependency&quot;&gt;</span><a href="#l584"></a>
<span id="l585">         *      Installed extension dependency&lt;/a&gt;</span><a href="#l585"></a>
<span id="l586">         */</span><a href="#l586"></a>
<span id="l587">        public static final Name EXTENSION_NAME = new Name(&quot;Extension-Name&quot;);</span><a href="#l587"></a>
<span id="l588"></span><a href="#l588"></a>
<span id="l589">        /**</span><a href="#l589"></a>
<span id="l590">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Extension-Name&lt;/code&gt; manifest attribute</span><a href="#l590"></a>
<span id="l591">         * used for declaring dependencies on installed extensions.</span><a href="#l591"></a>
<span id="l592">         * @deprecated Extension mechanism will be removed in a future release.</span><a href="#l592"></a>
<span id="l593">         *             Use class path instead.</span><a href="#l593"></a>
<span id="l594">         * @see &lt;a href=&quot;../../../../technotes/guides/extensions/spec.html#dependency&quot;&gt;</span><a href="#l594"></a>
<span id="l595">         *      Installed extension dependency&lt;/a&gt;</span><a href="#l595"></a>
<span id="l596">         */</span><a href="#l596"></a>
<span id="l597">        @Deprecated</span><a href="#l597"></a>
<span id="l598">        public static final Name EXTENSION_INSTALLATION = new Name(&quot;Extension-Installation&quot;);</span><a href="#l598"></a>
<span id="l599"></span><a href="#l599"></a>
<span id="l600">        /**</span><a href="#l600"></a>
<span id="l601">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Implementation-Title&lt;/code&gt;</span><a href="#l601"></a>
<span id="l602">         * manifest attribute used for package versioning.</span><a href="#l602"></a>
<span id="l603">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l603"></a>
<span id="l604">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l604"></a>
<span id="l605">         */</span><a href="#l605"></a>
<span id="l606">        public static final Name IMPLEMENTATION_TITLE = new Name(&quot;Implementation-Title&quot;);</span><a href="#l606"></a>
<span id="l607"></span><a href="#l607"></a>
<span id="l608">        /**</span><a href="#l608"></a>
<span id="l609">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Implementation-Version&lt;/code&gt;</span><a href="#l609"></a>
<span id="l610">         * manifest attribute used for package versioning.</span><a href="#l610"></a>
<span id="l611">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l611"></a>
<span id="l612">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l612"></a>
<span id="l613">         */</span><a href="#l613"></a>
<span id="l614">        public static final Name IMPLEMENTATION_VERSION = new Name(&quot;Implementation-Version&quot;);</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">        /**</span><a href="#l616"></a>
<span id="l617">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Implementation-Vendor&lt;/code&gt;</span><a href="#l617"></a>
<span id="l618">         * manifest attribute used for package versioning.</span><a href="#l618"></a>
<span id="l619">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l619"></a>
<span id="l620">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l620"></a>
<span id="l621">         */</span><a href="#l621"></a>
<span id="l622">        public static final Name IMPLEMENTATION_VENDOR = new Name(&quot;Implementation-Vendor&quot;);</span><a href="#l622"></a>
<span id="l623"></span><a href="#l623"></a>
<span id="l624">        /**</span><a href="#l624"></a>
<span id="l625">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Implementation-Vendor-Id&lt;/code&gt;</span><a href="#l625"></a>
<span id="l626">         * manifest attribute used for package versioning.</span><a href="#l626"></a>
<span id="l627">         * @deprecated Extension mechanism will be removed in a future release.</span><a href="#l627"></a>
<span id="l628">         *             Use class path instead.</span><a href="#l628"></a>
<span id="l629">         * @see &lt;a href=&quot;../../../../technotes/guides/extensions/versioning.html#applet&quot;&gt;</span><a href="#l629"></a>
<span id="l630">         *      Optional Package Versioning&lt;/a&gt;</span><a href="#l630"></a>
<span id="l631">         */</span><a href="#l631"></a>
<span id="l632">        @Deprecated</span><a href="#l632"></a>
<span id="l633">        public static final Name IMPLEMENTATION_VENDOR_ID = new Name(&quot;Implementation-Vendor-Id&quot;);</span><a href="#l633"></a>
<span id="l634"></span><a href="#l634"></a>
<span id="l635">       /**</span><a href="#l635"></a>
<span id="l636">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Implementation-URL&lt;/code&gt;</span><a href="#l636"></a>
<span id="l637">         * manifest attribute used for package versioning.</span><a href="#l637"></a>
<span id="l638">         * @deprecated Extension mechanism will be removed in a future release.</span><a href="#l638"></a>
<span id="l639">         *             Use class path instead.</span><a href="#l639"></a>
<span id="l640">         * @see &lt;a href=&quot;../../../../technotes/guides/extensions/versioning.html#applet&quot;&gt;</span><a href="#l640"></a>
<span id="l641">         *      Optional Package Versioning&lt;/a&gt;</span><a href="#l641"></a>
<span id="l642">         */</span><a href="#l642"></a>
<span id="l643">        @Deprecated</span><a href="#l643"></a>
<span id="l644">        public static final Name IMPLEMENTATION_URL = new Name(&quot;Implementation-URL&quot;);</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">        /**</span><a href="#l646"></a>
<span id="l647">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Specification-Title&lt;/code&gt;</span><a href="#l647"></a>
<span id="l648">         * manifest attribute used for package versioning.</span><a href="#l648"></a>
<span id="l649">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l649"></a>
<span id="l650">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l650"></a>
<span id="l651">         */</span><a href="#l651"></a>
<span id="l652">        public static final Name SPECIFICATION_TITLE = new Name(&quot;Specification-Title&quot;);</span><a href="#l652"></a>
<span id="l653"></span><a href="#l653"></a>
<span id="l654">        /**</span><a href="#l654"></a>
<span id="l655">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Specification-Version&lt;/code&gt;</span><a href="#l655"></a>
<span id="l656">         * manifest attribute used for package versioning.</span><a href="#l656"></a>
<span id="l657">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l657"></a>
<span id="l658">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l658"></a>
<span id="l659">         */</span><a href="#l659"></a>
<span id="l660">        public static final Name SPECIFICATION_VERSION = new Name(&quot;Specification-Version&quot;);</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">        /**</span><a href="#l662"></a>
<span id="l663">         * &lt;code&gt;Name&lt;/code&gt; object for &lt;code&gt;Specification-Vendor&lt;/code&gt;</span><a href="#l663"></a>
<span id="l664">         * manifest attribute used for package versioning.</span><a href="#l664"></a>
<span id="l665">         * @see &lt;a href=&quot;../../../../technotes/guides/versioning/spec/versioning2.html#wp90779&quot;&gt;</span><a href="#l665"></a>
<span id="l666">         *      Java Product Versioning Specification&lt;/a&gt;</span><a href="#l666"></a>
<span id="l667">         */</span><a href="#l667"></a>
<span id="l668">        public static final Name SPECIFICATION_VENDOR = new Name(&quot;Specification-Vendor&quot;);</span><a href="#l668"></a>
<span id="l669">    }</span><a href="#l669"></a>
<span id="l670">}</span><a href="#l670"></a></pre>
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

