<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: 41d764523117 src/share/classes/java/lang/invoke/MethodType.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/41d764523117">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/41d764523117">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/41d764523117">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/41d764523117/src/share/classes/java/lang/invoke/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/java/lang/invoke/MethodType.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/41d764523117/src/share/classes/java/lang/invoke/MethodType.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/41d764523117/src/share/classes/java/lang/invoke/MethodType.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/41d764523117/src/share/classes/java/lang/invoke/MethodType.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/41d764523117/src/share/classes/java/lang/invoke/MethodType.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/41d764523117/src/share/classes/java/lang/invoke/MethodType.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/lang/invoke/MethodType.java @ 9005:41d764523117</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8235274: Enhance typing of methods
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 13 Apr 2020 04:58:22 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/4c7e50519694/src/share/classes/java/lang/invoke/MethodType.java">4c7e50519694</a> </td>
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
<span id="l2"> * Copyright (c) 2008, 2012, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.lang.invoke;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import sun.invoke.util.Wrapper;</span><a href="#l28"></a>
<span id="l29">import java.lang.ref.WeakReference;</span><a href="#l29"></a>
<span id="l30">import java.lang.ref.ReferenceQueue;</span><a href="#l30"></a>
<span id="l31">import java.util.Arrays;</span><a href="#l31"></a>
<span id="l32">import java.util.Collections;</span><a href="#l32"></a>
<span id="l33">import java.util.List;</span><a href="#l33"></a>
<span id="l34">import sun.invoke.util.BytecodeDescriptor;</span><a href="#l34"></a>
<span id="l35">import static java.lang.invoke.MethodHandleStatics.*;</span><a href="#l35"></a>
<span id="l36">import sun.invoke.util.VerifyType;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * A method type represents the arguments and return type accepted and</span><a href="#l39"></a>
<span id="l40"> * returned by a method handle, or the arguments and return type passed</span><a href="#l40"></a>
<span id="l41"> * and expected  by a method handle caller.  Method types must be properly</span><a href="#l41"></a>
<span id="l42"> * matched between a method handle and all its callers,</span><a href="#l42"></a>
<span id="l43"> * and the JVM's operations enforce this matching at, specifically</span><a href="#l43"></a>
<span id="l44"> * during calls to {@link MethodHandle#invokeExact MethodHandle.invokeExact}</span><a href="#l44"></a>
<span id="l45"> * and {@link MethodHandle#invoke MethodHandle.invoke}, and during execution</span><a href="#l45"></a>
<span id="l46"> * of {@code invokedynamic} instructions.</span><a href="#l46"></a>
<span id="l47"> * &lt;p&gt;</span><a href="#l47"></a>
<span id="l48"> * The structure is a return type accompanied by any number of parameter types.</span><a href="#l48"></a>
<span id="l49"> * The types (primitive, {@code void}, and reference) are represented by {@link Class} objects.</span><a href="#l49"></a>
<span id="l50"> * (For ease of exposition, we treat {@code void} as if it were a type.</span><a href="#l50"></a>
<span id="l51"> * In fact, it denotes the absence of a return type.)</span><a href="#l51"></a>
<span id="l52"> * &lt;p&gt;</span><a href="#l52"></a>
<span id="l53"> * All instances of {@code MethodType} are immutable.</span><a href="#l53"></a>
<span id="l54"> * Two instances are completely interchangeable if they compare equal.</span><a href="#l54"></a>
<span id="l55"> * Equality depends on pairwise correspondence of the return and parameter types and on nothing else.</span><a href="#l55"></a>
<span id="l56"> * &lt;p&gt;</span><a href="#l56"></a>
<span id="l57"> * This type can be created only by factory methods.</span><a href="#l57"></a>
<span id="l58"> * All factory methods may cache values, though caching is not guaranteed.</span><a href="#l58"></a>
<span id="l59"> * Some factory methods are static, while others are virtual methods which</span><a href="#l59"></a>
<span id="l60"> * modify precursor method types, e.g., by changing a selected parameter.</span><a href="#l60"></a>
<span id="l61"> * &lt;p&gt;</span><a href="#l61"></a>
<span id="l62"> * Factory methods which operate on groups of parameter types</span><a href="#l62"></a>
<span id="l63"> * are systematically presented in two versions, so that both Java arrays and</span><a href="#l63"></a>
<span id="l64"> * Java lists can be used to work with groups of parameter types.</span><a href="#l64"></a>
<span id="l65"> * The query methods {@code parameterArray} and {@code parameterList}</span><a href="#l65"></a>
<span id="l66"> * also provide a choice between arrays and lists.</span><a href="#l66"></a>
<span id="l67"> * &lt;p&gt;</span><a href="#l67"></a>
<span id="l68"> * {@code MethodType} objects are sometimes derived from bytecode instructions</span><a href="#l68"></a>
<span id="l69"> * such as {@code invokedynamic}, specifically from the type descriptor strings associated</span><a href="#l69"></a>
<span id="l70"> * with the instructions in a class file's constant pool.</span><a href="#l70"></a>
<span id="l71"> * &lt;p&gt;</span><a href="#l71"></a>
<span id="l72"> * Like classes and strings, method types can also be represented directly</span><a href="#l72"></a>
<span id="l73"> * in a class file's constant pool as constants.</span><a href="#l73"></a>
<span id="l74"> * A method type may be loaded by an {@code ldc} instruction which refers</span><a href="#l74"></a>
<span id="l75"> * to a suitable {@code CONSTANT_MethodType} constant pool entry.</span><a href="#l75"></a>
<span id="l76"> * The entry refers to a {@code CONSTANT_Utf8} spelling for the descriptor string.</span><a href="#l76"></a>
<span id="l77"> * For more details, see the &lt;a href=&quot;package-summary.html#mtcon&quot;&gt;package summary&lt;/a&gt;.</span><a href="#l77"></a>
<span id="l78"> * &lt;p&gt;</span><a href="#l78"></a>
<span id="l79"> * When the JVM materializes a {@code MethodType} from a descriptor string,</span><a href="#l79"></a>
<span id="l80"> * all classes named in the descriptor must be accessible, and will be loaded.</span><a href="#l80"></a>
<span id="l81"> * (But the classes need not be initialized, as is the case with a {@code CONSTANT_Class}.)</span><a href="#l81"></a>
<span id="l82"> * This loading may occur at any time before the {@code MethodType} object is first derived.</span><a href="#l82"></a>
<span id="l83"> * @author John Rose, JSR 292 EG</span><a href="#l83"></a>
<span id="l84"> */</span><a href="#l84"></a>
<span id="l85">public final</span><a href="#l85"></a>
<span id="l86">class MethodType implements java.io.Serializable {</span><a href="#l86"></a>
<span id="l87">    private static final long serialVersionUID = 292L;  // {rtype, {ptype...}}</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    // The rtype and ptypes fields define the structural identity of the method type:</span><a href="#l89"></a>
<span id="l90">    private final Class&lt;?&gt;   rtype;</span><a href="#l90"></a>
<span id="l91">    private final Class&lt;?&gt;[] ptypes;</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">    // The remaining fields are caches of various sorts:</span><a href="#l93"></a>
<span id="l94">    private MethodTypeForm form; // erased form, plus cached data about primitives</span><a href="#l94"></a>
<span id="l95">    private Object wrapAlt;  // alternative wrapped/unwrapped version and</span><a href="#l95"></a>
<span id="l96">                             // private communication for readObject and readResolve</span><a href="#l96"></a>
<span id="l97">    private Invokers invokers;   // cache of handy higher-order adapters</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    /**</span><a href="#l99"></a>
<span id="l100">     * Check the given parameters for validity and store them into the final fields.</span><a href="#l100"></a>
<span id="l101">     */</span><a href="#l101"></a>
<span id="l102">    private MethodType(Class&lt;?&gt; rtype, Class&lt;?&gt;[] ptypes) {</span><a href="#l102"></a>
<span id="l103">        checkRtype(rtype);</span><a href="#l103"></a>
<span id="l104">        checkPtypes(ptypes);</span><a href="#l104"></a>
<span id="l105">        this.rtype = rtype;</span><a href="#l105"></a>
<span id="l106">        this.ptypes = ptypes;</span><a href="#l106"></a>
<span id="l107">    }</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">    /*trusted*/ MethodTypeForm form() { return form; }</span><a href="#l109"></a>
<span id="l110">    /*trusted*/ Class&lt;?&gt; rtype() { return rtype; }</span><a href="#l110"></a>
<span id="l111">    /*trusted*/ Class&lt;?&gt;[] ptypes() { return ptypes; }</span><a href="#l111"></a>
<span id="l112"></span><a href="#l112"></a>
<span id="l113">    void setForm(MethodTypeForm f) { form = f; }</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    /** This number, mandated by the JVM spec as 255,</span><a href="#l115"></a>
<span id="l116">     *  is the maximum number of &lt;em&gt;slots&lt;/em&gt;</span><a href="#l116"></a>
<span id="l117">     *  that any Java method can receive in its argument list.</span><a href="#l117"></a>
<span id="l118">     *  It limits both JVM signatures and method type objects.</span><a href="#l118"></a>
<span id="l119">     *  The longest possible invocation will look like</span><a href="#l119"></a>
<span id="l120">     *  {@code staticMethod(arg1, arg2, ..., arg255)} or</span><a href="#l120"></a>
<span id="l121">     *  {@code x.virtualMethod(arg1, arg2, ..., arg254)}.</span><a href="#l121"></a>
<span id="l122">     */</span><a href="#l122"></a>
<span id="l123">    /*non-public*/ static final int MAX_JVM_ARITY = 255;  // this is mandated by the JVM spec.</span><a href="#l123"></a>
<span id="l124"></span><a href="#l124"></a>
<span id="l125">    /** This number is the maximum arity of a method handle, 254.</span><a href="#l125"></a>
<span id="l126">     *  It is derived from the absolute JVM-imposed arity by subtracting one,</span><a href="#l126"></a>
<span id="l127">     *  which is the slot occupied by the method handle itself at the</span><a href="#l127"></a>
<span id="l128">     *  beginning of the argument list used to invoke the method handle.</span><a href="#l128"></a>
<span id="l129">     *  The longest possible invocation will look like</span><a href="#l129"></a>
<span id="l130">     *  {@code mh.invoke(arg1, arg2, ..., arg254)}.</span><a href="#l130"></a>
<span id="l131">     */</span><a href="#l131"></a>
<span id="l132">    // Issue:  Should we allow MH.invokeWithArguments to go to the full 255?</span><a href="#l132"></a>
<span id="l133">    /*non-public*/ static final int MAX_MH_ARITY = MAX_JVM_ARITY-1;  // deduct one for mh receiver</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">    /** This number is the maximum arity of a method handle invoker, 253.</span><a href="#l135"></a>
<span id="l136">     *  It is derived from the absolute JVM-imposed arity by subtracting two,</span><a href="#l136"></a>
<span id="l137">     *  which are the slots occupied by invoke method handle, and the the</span><a href="#l137"></a>
<span id="l138">     *  target method handle, which are both at the beginning of the argument</span><a href="#l138"></a>
<span id="l139">     *  list used to invoke the target method handle.</span><a href="#l139"></a>
<span id="l140">     *  The longest possible invocation will look like</span><a href="#l140"></a>
<span id="l141">     *  {@code invokermh.invoke(targetmh, arg1, arg2, ..., arg253)}.</span><a href="#l141"></a>
<span id="l142">     */</span><a href="#l142"></a>
<span id="l143">    /*non-public*/ static final int MAX_MH_INVOKER_ARITY = MAX_MH_ARITY-1;  // deduct one more for invoker</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">    private static void checkRtype(Class&lt;?&gt; rtype) {</span><a href="#l145"></a>
<span id="l146">        rtype.equals(rtype);  // null check</span><a href="#l146"></a>
<span id="l147">    }</span><a href="#l147"></a>
<span id="l148">    private static int checkPtype(Class&lt;?&gt; ptype) {</span><a href="#l148"></a>
<span id="l149">        ptype.getClass();  //NPE</span><a href="#l149"></a>
<span id="l150">        if (ptype == void.class)</span><a href="#l150"></a>
<span id="l151">            throw newIllegalArgumentException(&quot;parameter type cannot be void&quot;);</span><a href="#l151"></a>
<span id="l152">        if (ptype == double.class || ptype == long.class)  return 1;</span><a href="#l152"></a>
<span id="l153">        return 0;</span><a href="#l153"></a>
<span id="l154">    }</span><a href="#l154"></a>
<span id="l155">    /** Return number of extra slots (count of long/double args). */</span><a href="#l155"></a>
<span id="l156">    private static int checkPtypes(Class&lt;?&gt;[] ptypes) {</span><a href="#l156"></a>
<span id="l157">        int slots = 0;</span><a href="#l157"></a>
<span id="l158">        for (Class&lt;?&gt; ptype : ptypes) {</span><a href="#l158"></a>
<span id="l159">            slots += checkPtype(ptype);</span><a href="#l159"></a>
<span id="l160">        }</span><a href="#l160"></a>
<span id="l161">        checkSlotCount(ptypes.length + slots);</span><a href="#l161"></a>
<span id="l162">        return slots;</span><a href="#l162"></a>
<span id="l163">    }</span><a href="#l163"></a>
<span id="l164">    static void checkSlotCount(int count) {</span><a href="#l164"></a>
<span id="l165">        assert((MAX_JVM_ARITY &amp; (MAX_JVM_ARITY+1)) == 0);</span><a href="#l165"></a>
<span id="l166">        // MAX_JVM_ARITY must be power of 2 minus 1 for following code trick to work:</span><a href="#l166"></a>
<span id="l167">        if ((count &amp; MAX_JVM_ARITY) != count)</span><a href="#l167"></a>
<span id="l168">            throw newIllegalArgumentException(&quot;bad parameter count &quot;+count);</span><a href="#l168"></a>
<span id="l169">    }</span><a href="#l169"></a>
<span id="l170">    private static IndexOutOfBoundsException newIndexOutOfBoundsException(Object num) {</span><a href="#l170"></a>
<span id="l171">        if (num instanceof Integer)  num = &quot;bad index: &quot;+num;</span><a href="#l171"></a>
<span id="l172">        return new IndexOutOfBoundsException(num.toString());</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    static final WeakInternSet internTable = new WeakInternSet();</span><a href="#l175"></a>
<span id="l176"></span><a href="#l176"></a>
<span id="l177">    static final Class&lt;?&gt;[] NO_PTYPES = {};</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    /**</span><a href="#l179"></a>
<span id="l180">     * Finds or creates an instance of the given method type.</span><a href="#l180"></a>
<span id="l181">     * @param rtype  the return type</span><a href="#l181"></a>
<span id="l182">     * @param ptypes the parameter types</span><a href="#l182"></a>
<span id="l183">     * @return a method type with the given components</span><a href="#l183"></a>
<span id="l184">     * @throws NullPointerException if {@code rtype} or {@code ptypes} or any element of {@code ptypes} is null</span><a href="#l184"></a>
<span id="l185">     * @throws IllegalArgumentException if any element of {@code ptypes} is {@code void.class}</span><a href="#l185"></a>
<span id="l186">     */</span><a href="#l186"></a>
<span id="l187">    public static</span><a href="#l187"></a>
<span id="l188">    MethodType methodType(Class&lt;?&gt; rtype, Class&lt;?&gt;[] ptypes) {</span><a href="#l188"></a>
<span id="l189">        return makeImpl(rtype, ptypes, false);</span><a href="#l189"></a>
<span id="l190">    }</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">    /**</span><a href="#l192"></a>
<span id="l193">     * Finds or creates a method type with the given components.</span><a href="#l193"></a>
<span id="l194">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l194"></a>
<span id="l195">     * @return a method type with the given components</span><a href="#l195"></a>
<span id="l196">     * @throws NullPointerException if {@code rtype} or {@code ptypes} or any element of {@code ptypes} is null</span><a href="#l196"></a>
<span id="l197">     * @throws IllegalArgumentException if any element of {@code ptypes} is {@code void.class}</span><a href="#l197"></a>
<span id="l198">     */</span><a href="#l198"></a>
<span id="l199">    public static</span><a href="#l199"></a>
<span id="l200">    MethodType methodType(Class&lt;?&gt; rtype, List&lt;Class&lt;?&gt;&gt; ptypes) {</span><a href="#l200"></a>
<span id="l201">        boolean notrust = false;  // random List impl. could return evil ptypes array</span><a href="#l201"></a>
<span id="l202">        return makeImpl(rtype, listToArray(ptypes), notrust);</span><a href="#l202"></a>
<span id="l203">    }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    private static Class&lt;?&gt;[] listToArray(List&lt;Class&lt;?&gt;&gt; ptypes) {</span><a href="#l205"></a>
<span id="l206">        // sanity check the size before the toArray call, since size might be huge</span><a href="#l206"></a>
<span id="l207">        checkSlotCount(ptypes.size());</span><a href="#l207"></a>
<span id="l208">        return ptypes.toArray(NO_PTYPES);</span><a href="#l208"></a>
<span id="l209">    }</span><a href="#l209"></a>
<span id="l210"></span><a href="#l210"></a>
<span id="l211">    /**</span><a href="#l211"></a>
<span id="l212">     * Finds or creates a method type with the given components.</span><a href="#l212"></a>
<span id="l213">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l213"></a>
<span id="l214">     * The leading parameter type is prepended to the remaining array.</span><a href="#l214"></a>
<span id="l215">     * @return a method type with the given components</span><a href="#l215"></a>
<span id="l216">     * @throws NullPointerException if {@code rtype} or {@code ptype0} or {@code ptypes} or any element of {@code ptypes} is null</span><a href="#l216"></a>
<span id="l217">     * @throws IllegalArgumentException if {@code ptype0} or {@code ptypes} or any element of {@code ptypes} is {@code void.class}</span><a href="#l217"></a>
<span id="l218">     */</span><a href="#l218"></a>
<span id="l219">    public static</span><a href="#l219"></a>
<span id="l220">    MethodType methodType(Class&lt;?&gt; rtype, Class&lt;?&gt; ptype0, Class&lt;?&gt;... ptypes) {</span><a href="#l220"></a>
<span id="l221">        Class&lt;?&gt;[] ptypes1 = new Class&lt;?&gt;[1+ptypes.length];</span><a href="#l221"></a>
<span id="l222">        ptypes1[0] = ptype0;</span><a href="#l222"></a>
<span id="l223">        System.arraycopy(ptypes, 0, ptypes1, 1, ptypes.length);</span><a href="#l223"></a>
<span id="l224">        return makeImpl(rtype, ptypes1, true);</span><a href="#l224"></a>
<span id="l225">    }</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    /**</span><a href="#l227"></a>
<span id="l228">     * Finds or creates a method type with the given components.</span><a href="#l228"></a>
<span id="l229">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l229"></a>
<span id="l230">     * The resulting method has no parameter types.</span><a href="#l230"></a>
<span id="l231">     * @return a method type with the given return value</span><a href="#l231"></a>
<span id="l232">     * @throws NullPointerException if {@code rtype} is null</span><a href="#l232"></a>
<span id="l233">     */</span><a href="#l233"></a>
<span id="l234">    public static</span><a href="#l234"></a>
<span id="l235">    MethodType methodType(Class&lt;?&gt; rtype) {</span><a href="#l235"></a>
<span id="l236">        return makeImpl(rtype, NO_PTYPES, true);</span><a href="#l236"></a>
<span id="l237">    }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    /**</span><a href="#l239"></a>
<span id="l240">     * Finds or creates a method type with the given components.</span><a href="#l240"></a>
<span id="l241">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l241"></a>
<span id="l242">     * The resulting method has the single given parameter type.</span><a href="#l242"></a>
<span id="l243">     * @return a method type with the given return value and parameter type</span><a href="#l243"></a>
<span id="l244">     * @throws NullPointerException if {@code rtype} or {@code ptype0} is null</span><a href="#l244"></a>
<span id="l245">     * @throws IllegalArgumentException if {@code ptype0} is {@code void.class}</span><a href="#l245"></a>
<span id="l246">     */</span><a href="#l246"></a>
<span id="l247">    public static</span><a href="#l247"></a>
<span id="l248">    MethodType methodType(Class&lt;?&gt; rtype, Class&lt;?&gt; ptype0) {</span><a href="#l248"></a>
<span id="l249">        return makeImpl(rtype, new Class&lt;?&gt;[]{ ptype0 }, true);</span><a href="#l249"></a>
<span id="l250">    }</span><a href="#l250"></a>
<span id="l251"></span><a href="#l251"></a>
<span id="l252">    /**</span><a href="#l252"></a>
<span id="l253">     * Finds or creates a method type with the given components.</span><a href="#l253"></a>
<span id="l254">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l254"></a>
<span id="l255">     * The resulting method has the same parameter types as {@code ptypes},</span><a href="#l255"></a>
<span id="l256">     * and the specified return type.</span><a href="#l256"></a>
<span id="l257">     * @throws NullPointerException if {@code rtype} or {@code ptypes} is null</span><a href="#l257"></a>
<span id="l258">     */</span><a href="#l258"></a>
<span id="l259">    public static</span><a href="#l259"></a>
<span id="l260">    MethodType methodType(Class&lt;?&gt; rtype, MethodType ptypes) {</span><a href="#l260"></a>
<span id="l261">        return makeImpl(rtype, ptypes.ptypes, true);</span><a href="#l261"></a>
<span id="l262">    }</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">    /**</span><a href="#l264"></a>
<span id="l265">     * Sole factory method to find or create an interned method type.</span><a href="#l265"></a>
<span id="l266">     * @param rtype desired return type</span><a href="#l266"></a>
<span id="l267">     * @param ptypes desired parameter types</span><a href="#l267"></a>
<span id="l268">     * @param trusted whether the ptypes can be used without cloning</span><a href="#l268"></a>
<span id="l269">     * @return the unique method type of the desired structure</span><a href="#l269"></a>
<span id="l270">     */</span><a href="#l270"></a>
<span id="l271">    /*trusted*/ static</span><a href="#l271"></a>
<span id="l272">    MethodType makeImpl(Class&lt;?&gt; rtype, Class&lt;?&gt;[] ptypes, boolean trusted) {</span><a href="#l272"></a>
<span id="l273">        if (ptypes.length == 0) {</span><a href="#l273"></a>
<span id="l274">            ptypes = NO_PTYPES; trusted = true;</span><a href="#l274"></a>
<span id="l275">        }</span><a href="#l275"></a>
<span id="l276">        MethodType mt1 = new MethodType(rtype, ptypes);</span><a href="#l276"></a>
<span id="l277">        MethodType mt0 = internTable.get(mt1);</span><a href="#l277"></a>
<span id="l278">        if (mt0 != null)</span><a href="#l278"></a>
<span id="l279">            return mt0;</span><a href="#l279"></a>
<span id="l280">        if (!trusted)</span><a href="#l280"></a>
<span id="l281">            // defensively copy the array passed in by the user</span><a href="#l281"></a>
<span id="l282">            mt1 = new MethodType(rtype, ptypes.clone());</span><a href="#l282"></a>
<span id="l283">        // promote the object to the Real Thing, and reprobe</span><a href="#l283"></a>
<span id="l284">        MethodTypeForm form = MethodTypeForm.findForm(mt1);</span><a href="#l284"></a>
<span id="l285">        mt1.form = form;</span><a href="#l285"></a>
<span id="l286">        return internTable.add(mt1);</span><a href="#l286"></a>
<span id="l287">    }</span><a href="#l287"></a>
<span id="l288">    private static final MethodType[] objectOnlyTypes = new MethodType[20];</span><a href="#l288"></a>
<span id="l289"></span><a href="#l289"></a>
<span id="l290">    /**</span><a href="#l290"></a>
<span id="l291">     * Finds or creates a method type whose components are {@code Object} with an optional trailing {@code Object[]} array.</span><a href="#l291"></a>
<span id="l292">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l292"></a>
<span id="l293">     * All parameters and the return type will be {@code Object},</span><a href="#l293"></a>
<span id="l294">     * except the final array parameter if any, which will be {@code Object[]}.</span><a href="#l294"></a>
<span id="l295">     * @param objectArgCount number of parameters (excluding the final array parameter if any)</span><a href="#l295"></a>
<span id="l296">     * @param finalArray whether there will be a trailing array parameter, of type {@code Object[]}</span><a href="#l296"></a>
<span id="l297">     * @return a generally applicable method type, for all calls of the given fixed argument count and a collected array of further arguments</span><a href="#l297"></a>
<span id="l298">     * @throws IllegalArgumentException if {@code objectArgCount} is negative or greater than 255 (or 254, if {@code finalArray} is true)</span><a href="#l298"></a>
<span id="l299">     * @see #genericMethodType(int)</span><a href="#l299"></a>
<span id="l300">     */</span><a href="#l300"></a>
<span id="l301">    public static</span><a href="#l301"></a>
<span id="l302">    MethodType genericMethodType(int objectArgCount, boolean finalArray) {</span><a href="#l302"></a>
<span id="l303">        MethodType mt;</span><a href="#l303"></a>
<span id="l304">        checkSlotCount(objectArgCount);</span><a href="#l304"></a>
<span id="l305">        int ivarargs = (!finalArray ? 0 : 1);</span><a href="#l305"></a>
<span id="l306">        int ootIndex = objectArgCount*2 + ivarargs;</span><a href="#l306"></a>
<span id="l307">        if (ootIndex &lt; objectOnlyTypes.length) {</span><a href="#l307"></a>
<span id="l308">            mt = objectOnlyTypes[ootIndex];</span><a href="#l308"></a>
<span id="l309">            if (mt != null)  return mt;</span><a href="#l309"></a>
<span id="l310">        }</span><a href="#l310"></a>
<span id="l311">        Class&lt;?&gt;[] ptypes = new Class&lt;?&gt;[objectArgCount + ivarargs];</span><a href="#l311"></a>
<span id="l312">        Arrays.fill(ptypes, Object.class);</span><a href="#l312"></a>
<span id="l313">        if (ivarargs != 0)  ptypes[objectArgCount] = Object[].class;</span><a href="#l313"></a>
<span id="l314">        mt = makeImpl(Object.class, ptypes, true);</span><a href="#l314"></a>
<span id="l315">        if (ootIndex &lt; objectOnlyTypes.length) {</span><a href="#l315"></a>
<span id="l316">            objectOnlyTypes[ootIndex] = mt;     // cache it here also!</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">        return mt;</span><a href="#l318"></a>
<span id="l319">    }</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    /**</span><a href="#l321"></a>
<span id="l322">     * Finds or creates a method type whose components are all {@code Object}.</span><a href="#l322"></a>
<span id="l323">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l323"></a>
<span id="l324">     * All parameters and the return type will be Object.</span><a href="#l324"></a>
<span id="l325">     * @param objectArgCount number of parameters</span><a href="#l325"></a>
<span id="l326">     * @return a generally applicable method type, for all calls of the given argument count</span><a href="#l326"></a>
<span id="l327">     * @throws IllegalArgumentException if {@code objectArgCount} is negative or greater than 255</span><a href="#l327"></a>
<span id="l328">     * @see #genericMethodType(int, boolean)</span><a href="#l328"></a>
<span id="l329">     */</span><a href="#l329"></a>
<span id="l330">    public static</span><a href="#l330"></a>
<span id="l331">    MethodType genericMethodType(int objectArgCount) {</span><a href="#l331"></a>
<span id="l332">        return genericMethodType(objectArgCount, false);</span><a href="#l332"></a>
<span id="l333">    }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">    /**</span><a href="#l335"></a>
<span id="l336">     * Finds or creates a method type with a single different parameter type.</span><a href="#l336"></a>
<span id="l337">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l337"></a>
<span id="l338">     * @param num    the index (zero-based) of the parameter type to change</span><a href="#l338"></a>
<span id="l339">     * @param nptype a new parameter type to replace the old one with</span><a href="#l339"></a>
<span id="l340">     * @return the same type, except with the selected parameter changed</span><a href="#l340"></a>
<span id="l341">     * @throws IndexOutOfBoundsException if {@code num} is not a valid index into {@code parameterArray()}</span><a href="#l341"></a>
<span id="l342">     * @throws IllegalArgumentException if {@code nptype} is {@code void.class}</span><a href="#l342"></a>
<span id="l343">     * @throws NullPointerException if {@code nptype} is null</span><a href="#l343"></a>
<span id="l344">     */</span><a href="#l344"></a>
<span id="l345">    public MethodType changeParameterType(int num, Class&lt;?&gt; nptype) {</span><a href="#l345"></a>
<span id="l346">        if (parameterType(num) == nptype)  return this;</span><a href="#l346"></a>
<span id="l347">        checkPtype(nptype);</span><a href="#l347"></a>
<span id="l348">        Class&lt;?&gt;[] nptypes = ptypes.clone();</span><a href="#l348"></a>
<span id="l349">        nptypes[num] = nptype;</span><a href="#l349"></a>
<span id="l350">        return makeImpl(rtype, nptypes, true);</span><a href="#l350"></a>
<span id="l351">    }</span><a href="#l351"></a>
<span id="l352"></span><a href="#l352"></a>
<span id="l353">    /**</span><a href="#l353"></a>
<span id="l354">     * Finds or creates a method type with additional parameter types.</span><a href="#l354"></a>
<span id="l355">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l355"></a>
<span id="l356">     * @param num    the position (zero-based) of the inserted parameter type(s)</span><a href="#l356"></a>
<span id="l357">     * @param ptypesToInsert zero or more new parameter types to insert into the parameter list</span><a href="#l357"></a>
<span id="l358">     * @return the same type, except with the selected parameter(s) inserted</span><a href="#l358"></a>
<span id="l359">     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than {@code parameterCount()}</span><a href="#l359"></a>
<span id="l360">     * @throws IllegalArgumentException if any element of {@code ptypesToInsert} is {@code void.class}</span><a href="#l360"></a>
<span id="l361">     *                                  or if the resulting method type would have more than 255 parameter slots</span><a href="#l361"></a>
<span id="l362">     * @throws NullPointerException if {@code ptypesToInsert} or any of its elements is null</span><a href="#l362"></a>
<span id="l363">     */</span><a href="#l363"></a>
<span id="l364">    public MethodType insertParameterTypes(int num, Class&lt;?&gt;... ptypesToInsert) {</span><a href="#l364"></a>
<span id="l365">        int len = ptypes.length;</span><a href="#l365"></a>
<span id="l366">        if (num &lt; 0 || num &gt; len)</span><a href="#l366"></a>
<span id="l367">            throw newIndexOutOfBoundsException(num);</span><a href="#l367"></a>
<span id="l368">        int ins = checkPtypes(ptypesToInsert);</span><a href="#l368"></a>
<span id="l369">        checkSlotCount(parameterSlotCount() + ptypesToInsert.length + ins);</span><a href="#l369"></a>
<span id="l370">        int ilen = ptypesToInsert.length;</span><a href="#l370"></a>
<span id="l371">        if (ilen == 0)  return this;</span><a href="#l371"></a>
<span id="l372">        Class&lt;?&gt;[] nptypes = Arrays.copyOfRange(ptypes, 0, len+ilen);</span><a href="#l372"></a>
<span id="l373">        System.arraycopy(nptypes, num, nptypes, num+ilen, len-num);</span><a href="#l373"></a>
<span id="l374">        System.arraycopy(ptypesToInsert, 0, nptypes, num, ilen);</span><a href="#l374"></a>
<span id="l375">        return makeImpl(rtype, nptypes, true);</span><a href="#l375"></a>
<span id="l376">    }</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">    /**</span><a href="#l378"></a>
<span id="l379">     * Finds or creates a method type with additional parameter types.</span><a href="#l379"></a>
<span id="l380">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l380"></a>
<span id="l381">     * @param ptypesToInsert zero or more new parameter types to insert after the end of the parameter list</span><a href="#l381"></a>
<span id="l382">     * @return the same type, except with the selected parameter(s) appended</span><a href="#l382"></a>
<span id="l383">     * @throws IllegalArgumentException if any element of {@code ptypesToInsert} is {@code void.class}</span><a href="#l383"></a>
<span id="l384">     *                                  or if the resulting method type would have more than 255 parameter slots</span><a href="#l384"></a>
<span id="l385">     * @throws NullPointerException if {@code ptypesToInsert} or any of its elements is null</span><a href="#l385"></a>
<span id="l386">     */</span><a href="#l386"></a>
<span id="l387">    public MethodType appendParameterTypes(Class&lt;?&gt;... ptypesToInsert) {</span><a href="#l387"></a>
<span id="l388">        return insertParameterTypes(parameterCount(), ptypesToInsert);</span><a href="#l388"></a>
<span id="l389">    }</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">    /**</span><a href="#l391"></a>
<span id="l392">     * Finds or creates a method type with additional parameter types.</span><a href="#l392"></a>
<span id="l393">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l393"></a>
<span id="l394">     * @param num    the position (zero-based) of the inserted parameter type(s)</span><a href="#l394"></a>
<span id="l395">     * @param ptypesToInsert zero or more new parameter types to insert into the parameter list</span><a href="#l395"></a>
<span id="l396">     * @return the same type, except with the selected parameter(s) inserted</span><a href="#l396"></a>
<span id="l397">     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than {@code parameterCount()}</span><a href="#l397"></a>
<span id="l398">     * @throws IllegalArgumentException if any element of {@code ptypesToInsert} is {@code void.class}</span><a href="#l398"></a>
<span id="l399">     *                                  or if the resulting method type would have more than 255 parameter slots</span><a href="#l399"></a>
<span id="l400">     * @throws NullPointerException if {@code ptypesToInsert} or any of its elements is null</span><a href="#l400"></a>
<span id="l401">     */</span><a href="#l401"></a>
<span id="l402">    public MethodType insertParameterTypes(int num, List&lt;Class&lt;?&gt;&gt; ptypesToInsert) {</span><a href="#l402"></a>
<span id="l403">        return insertParameterTypes(num, listToArray(ptypesToInsert));</span><a href="#l403"></a>
<span id="l404">    }</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">    /**</span><a href="#l406"></a>
<span id="l407">     * Finds or creates a method type with additional parameter types.</span><a href="#l407"></a>
<span id="l408">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l408"></a>
<span id="l409">     * @param ptypesToInsert zero or more new parameter types to insert after the end of the parameter list</span><a href="#l409"></a>
<span id="l410">     * @return the same type, except with the selected parameter(s) appended</span><a href="#l410"></a>
<span id="l411">     * @throws IllegalArgumentException if any element of {@code ptypesToInsert} is {@code void.class}</span><a href="#l411"></a>
<span id="l412">     *                                  or if the resulting method type would have more than 255 parameter slots</span><a href="#l412"></a>
<span id="l413">     * @throws NullPointerException if {@code ptypesToInsert} or any of its elements is null</span><a href="#l413"></a>
<span id="l414">     */</span><a href="#l414"></a>
<span id="l415">    public MethodType appendParameterTypes(List&lt;Class&lt;?&gt;&gt; ptypesToInsert) {</span><a href="#l415"></a>
<span id="l416">        return insertParameterTypes(parameterCount(), ptypesToInsert);</span><a href="#l416"></a>
<span id="l417">    }</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a>
<span id="l419">     /**</span><a href="#l419"></a>
<span id="l420">     * Finds or creates a method type with modified parameter types.</span><a href="#l420"></a>
<span id="l421">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l421"></a>
<span id="l422">     * @param start  the position (zero-based) of the first replaced parameter type(s)</span><a href="#l422"></a>
<span id="l423">     * @param end    the position (zero-based) after the last replaced parameter type(s)</span><a href="#l423"></a>
<span id="l424">     * @param ptypesToInsert zero or more new parameter types to insert into the parameter list</span><a href="#l424"></a>
<span id="l425">     * @return the same type, except with the selected parameter(s) replaced</span><a href="#l425"></a>
<span id="l426">     * @throws IndexOutOfBoundsException if {@code start} is negative or greater than {@code parameterCount()}</span><a href="#l426"></a>
<span id="l427">     *                                  or if {@code end} is negative or greater than {@code parameterCount()}</span><a href="#l427"></a>
<span id="l428">     *                                  or if {@code start} is greater than {@code end}</span><a href="#l428"></a>
<span id="l429">     * @throws IllegalArgumentException if any element of {@code ptypesToInsert} is {@code void.class}</span><a href="#l429"></a>
<span id="l430">     *                                  or if the resulting method type would have more than 255 parameter slots</span><a href="#l430"></a>
<span id="l431">     * @throws NullPointerException if {@code ptypesToInsert} or any of its elements is null</span><a href="#l431"></a>
<span id="l432">     */</span><a href="#l432"></a>
<span id="l433">    /*non-public*/ MethodType replaceParameterTypes(int start, int end, Class&lt;?&gt;... ptypesToInsert) {</span><a href="#l433"></a>
<span id="l434">        if (start == end)</span><a href="#l434"></a>
<span id="l435">            return insertParameterTypes(start, ptypesToInsert);</span><a href="#l435"></a>
<span id="l436">        int len = ptypes.length;</span><a href="#l436"></a>
<span id="l437">        if (!(0 &lt;= start &amp;&amp; start &lt;= end &amp;&amp; end &lt;= len))</span><a href="#l437"></a>
<span id="l438">            throw newIndexOutOfBoundsException(&quot;start=&quot;+start+&quot; end=&quot;+end);</span><a href="#l438"></a>
<span id="l439">        int ilen = ptypesToInsert.length;</span><a href="#l439"></a>
<span id="l440">        if (ilen == 0)</span><a href="#l440"></a>
<span id="l441">            return dropParameterTypes(start, end);</span><a href="#l441"></a>
<span id="l442">        return dropParameterTypes(start, end).insertParameterTypes(start, ptypesToInsert);</span><a href="#l442"></a>
<span id="l443">    }</span><a href="#l443"></a>
<span id="l444"></span><a href="#l444"></a>
<span id="l445">    /**</span><a href="#l445"></a>
<span id="l446">     * Finds or creates a method type with some parameter types omitted.</span><a href="#l446"></a>
<span id="l447">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l447"></a>
<span id="l448">     * @param start  the index (zero-based) of the first parameter type to remove</span><a href="#l448"></a>
<span id="l449">     * @param end    the index (greater than {@code start}) of the first parameter type after not to remove</span><a href="#l449"></a>
<span id="l450">     * @return the same type, except with the selected parameter(s) removed</span><a href="#l450"></a>
<span id="l451">     * @throws IndexOutOfBoundsException if {@code start} is negative or greater than {@code parameterCount()}</span><a href="#l451"></a>
<span id="l452">     *                                  or if {@code end} is negative or greater than {@code parameterCount()}</span><a href="#l452"></a>
<span id="l453">     *                                  or if {@code start} is greater than {@code end}</span><a href="#l453"></a>
<span id="l454">     */</span><a href="#l454"></a>
<span id="l455">    public MethodType dropParameterTypes(int start, int end) {</span><a href="#l455"></a>
<span id="l456">        int len = ptypes.length;</span><a href="#l456"></a>
<span id="l457">        if (!(0 &lt;= start &amp;&amp; start &lt;= end &amp;&amp; end &lt;= len))</span><a href="#l457"></a>
<span id="l458">            throw newIndexOutOfBoundsException(&quot;start=&quot;+start+&quot; end=&quot;+end);</span><a href="#l458"></a>
<span id="l459">        if (start == end)  return this;</span><a href="#l459"></a>
<span id="l460">        Class&lt;?&gt;[] nptypes;</span><a href="#l460"></a>
<span id="l461">        if (start == 0) {</span><a href="#l461"></a>
<span id="l462">            if (end == len) {</span><a href="#l462"></a>
<span id="l463">                // drop all parameters</span><a href="#l463"></a>
<span id="l464">                nptypes = NO_PTYPES;</span><a href="#l464"></a>
<span id="l465">            } else {</span><a href="#l465"></a>
<span id="l466">                // drop initial parameter(s)</span><a href="#l466"></a>
<span id="l467">                nptypes = Arrays.copyOfRange(ptypes, end, len);</span><a href="#l467"></a>
<span id="l468">            }</span><a href="#l468"></a>
<span id="l469">        } else {</span><a href="#l469"></a>
<span id="l470">            if (end == len) {</span><a href="#l470"></a>
<span id="l471">                // drop trailing parameter(s)</span><a href="#l471"></a>
<span id="l472">                nptypes = Arrays.copyOfRange(ptypes, 0, start);</span><a href="#l472"></a>
<span id="l473">            } else {</span><a href="#l473"></a>
<span id="l474">                int tail = len - end;</span><a href="#l474"></a>
<span id="l475">                nptypes = Arrays.copyOfRange(ptypes, 0, start + tail);</span><a href="#l475"></a>
<span id="l476">                System.arraycopy(ptypes, end, nptypes, start, tail);</span><a href="#l476"></a>
<span id="l477">            }</span><a href="#l477"></a>
<span id="l478">        }</span><a href="#l478"></a>
<span id="l479">        return makeImpl(rtype, nptypes, true);</span><a href="#l479"></a>
<span id="l480">    }</span><a href="#l480"></a>
<span id="l481"></span><a href="#l481"></a>
<span id="l482">    /**</span><a href="#l482"></a>
<span id="l483">     * Finds or creates a method type with a different return type.</span><a href="#l483"></a>
<span id="l484">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l484"></a>
<span id="l485">     * @param nrtype a return parameter type to replace the old one with</span><a href="#l485"></a>
<span id="l486">     * @return the same type, except with the return type change</span><a href="#l486"></a>
<span id="l487">     * @throws NullPointerException if {@code nrtype} is null</span><a href="#l487"></a>
<span id="l488">     */</span><a href="#l488"></a>
<span id="l489">    public MethodType changeReturnType(Class&lt;?&gt; nrtype) {</span><a href="#l489"></a>
<span id="l490">        if (returnType() == nrtype)  return this;</span><a href="#l490"></a>
<span id="l491">        return makeImpl(nrtype, ptypes, true);</span><a href="#l491"></a>
<span id="l492">    }</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494">    /**</span><a href="#l494"></a>
<span id="l495">     * Reports if this type contains a primitive argument or return value.</span><a href="#l495"></a>
<span id="l496">     * The return type {@code void} counts as a primitive.</span><a href="#l496"></a>
<span id="l497">     * @return true if any of the types are primitives</span><a href="#l497"></a>
<span id="l498">     */</span><a href="#l498"></a>
<span id="l499">    public boolean hasPrimitives() {</span><a href="#l499"></a>
<span id="l500">        return form.hasPrimitives();</span><a href="#l500"></a>
<span id="l501">    }</span><a href="#l501"></a>
<span id="l502"></span><a href="#l502"></a>
<span id="l503">    /**</span><a href="#l503"></a>
<span id="l504">     * Reports if this type contains a wrapper argument or return value.</span><a href="#l504"></a>
<span id="l505">     * Wrappers are types which box primitive values, such as {@link Integer}.</span><a href="#l505"></a>
<span id="l506">     * The reference type {@code java.lang.Void} counts as a wrapper,</span><a href="#l506"></a>
<span id="l507">     * if it occurs as a return type.</span><a href="#l507"></a>
<span id="l508">     * @return true if any of the types are wrappers</span><a href="#l508"></a>
<span id="l509">     */</span><a href="#l509"></a>
<span id="l510">    public boolean hasWrappers() {</span><a href="#l510"></a>
<span id="l511">        return unwrap() != this;</span><a href="#l511"></a>
<span id="l512">    }</span><a href="#l512"></a>
<span id="l513"></span><a href="#l513"></a>
<span id="l514">    /**</span><a href="#l514"></a>
<span id="l515">     * Erases all reference types to {@code Object}.</span><a href="#l515"></a>
<span id="l516">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l516"></a>
<span id="l517">     * All primitive types (including {@code void}) will remain unchanged.</span><a href="#l517"></a>
<span id="l518">     * @return a version of the original type with all reference types replaced</span><a href="#l518"></a>
<span id="l519">     */</span><a href="#l519"></a>
<span id="l520">    public MethodType erase() {</span><a href="#l520"></a>
<span id="l521">        return form.erasedType();</span><a href="#l521"></a>
<span id="l522">    }</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">    /**</span><a href="#l524"></a>
<span id="l525">     * Erases all reference types to {@code Object}, and all subword types to {@code int}.</span><a href="#l525"></a>
<span id="l526">     * This is the reduced type polymorphism used by private methods</span><a href="#l526"></a>
<span id="l527">     * such as {@link MethodHandle#invokeBasic invokeBasic}.</span><a href="#l527"></a>
<span id="l528">     * @return a version of the original type with all reference and subword types replaced</span><a href="#l528"></a>
<span id="l529">     */</span><a href="#l529"></a>
<span id="l530">    /*non-public*/ MethodType basicType() {</span><a href="#l530"></a>
<span id="l531">        return form.basicType();</span><a href="#l531"></a>
<span id="l532">    }</span><a href="#l532"></a>
<span id="l533"></span><a href="#l533"></a>
<span id="l534">    /**</span><a href="#l534"></a>
<span id="l535">     * @return a version of the original type with MethodHandle prepended as the first argument</span><a href="#l535"></a>
<span id="l536">     */</span><a href="#l536"></a>
<span id="l537">    /*non-public*/ MethodType invokerType() {</span><a href="#l537"></a>
<span id="l538">        return insertParameterTypes(0, MethodHandle.class);</span><a href="#l538"></a>
<span id="l539">    }</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">    /**</span><a href="#l541"></a>
<span id="l542">     * Converts all types, both reference and primitive, to {@code Object}.</span><a href="#l542"></a>
<span id="l543">     * Convenience method for {@link #genericMethodType(int) genericMethodType}.</span><a href="#l543"></a>
<span id="l544">     * The expression {@code type.wrap().erase()} produces the same value</span><a href="#l544"></a>
<span id="l545">     * as {@code type.generic()}.</span><a href="#l545"></a>
<span id="l546">     * @return a version of the original type with all types replaced</span><a href="#l546"></a>
<span id="l547">     */</span><a href="#l547"></a>
<span id="l548">    public MethodType generic() {</span><a href="#l548"></a>
<span id="l549">        return genericMethodType(parameterCount());</span><a href="#l549"></a>
<span id="l550">    }</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">    /**</span><a href="#l552"></a>
<span id="l553">     * Converts all primitive types to their corresponding wrapper types.</span><a href="#l553"></a>
<span id="l554">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l554"></a>
<span id="l555">     * All reference types (including wrapper types) will remain unchanged.</span><a href="#l555"></a>
<span id="l556">     * A {@code void} return type is changed to the type {@code java.lang.Void}.</span><a href="#l556"></a>
<span id="l557">     * The expression {@code type.wrap().erase()} produces the same value</span><a href="#l557"></a>
<span id="l558">     * as {@code type.generic()}.</span><a href="#l558"></a>
<span id="l559">     * @return a version of the original type with all primitive types replaced</span><a href="#l559"></a>
<span id="l560">     */</span><a href="#l560"></a>
<span id="l561">    public MethodType wrap() {</span><a href="#l561"></a>
<span id="l562">        return hasPrimitives() ? wrapWithPrims(this) : this;</span><a href="#l562"></a>
<span id="l563">    }</span><a href="#l563"></a>
<span id="l564"></span><a href="#l564"></a>
<span id="l565">    /**</span><a href="#l565"></a>
<span id="l566">     * Converts all wrapper types to their corresponding primitive types.</span><a href="#l566"></a>
<span id="l567">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l567"></a>
<span id="l568">     * All primitive types (including {@code void}) will remain unchanged.</span><a href="#l568"></a>
<span id="l569">     * A return type of {@code java.lang.Void} is changed to {@code void}.</span><a href="#l569"></a>
<span id="l570">     * @return a version of the original type with all wrapper types replaced</span><a href="#l570"></a>
<span id="l571">     */</span><a href="#l571"></a>
<span id="l572">    public MethodType unwrap() {</span><a href="#l572"></a>
<span id="l573">        MethodType noprims = !hasPrimitives() ? this : wrapWithPrims(this);</span><a href="#l573"></a>
<span id="l574">        return unwrapWithNoPrims(noprims);</span><a href="#l574"></a>
<span id="l575">    }</span><a href="#l575"></a>
<span id="l576"></span><a href="#l576"></a>
<span id="l577">    private static MethodType wrapWithPrims(MethodType pt) {</span><a href="#l577"></a>
<span id="l578">        assert(pt.hasPrimitives());</span><a href="#l578"></a>
<span id="l579">        MethodType wt = (MethodType)pt.wrapAlt;</span><a href="#l579"></a>
<span id="l580">        if (wt == null) {</span><a href="#l580"></a>
<span id="l581">            // fill in lazily</span><a href="#l581"></a>
<span id="l582">            wt = MethodTypeForm.canonicalize(pt, MethodTypeForm.WRAP, MethodTypeForm.WRAP);</span><a href="#l582"></a>
<span id="l583">            assert(wt != null);</span><a href="#l583"></a>
<span id="l584">            pt.wrapAlt = wt;</span><a href="#l584"></a>
<span id="l585">        }</span><a href="#l585"></a>
<span id="l586">        return wt;</span><a href="#l586"></a>
<span id="l587">    }</span><a href="#l587"></a>
<span id="l588"></span><a href="#l588"></a>
<span id="l589">    private static MethodType unwrapWithNoPrims(MethodType wt) {</span><a href="#l589"></a>
<span id="l590">        assert(!wt.hasPrimitives());</span><a href="#l590"></a>
<span id="l591">        MethodType uwt = (MethodType)wt.wrapAlt;</span><a href="#l591"></a>
<span id="l592">        if (uwt == null) {</span><a href="#l592"></a>
<span id="l593">            // fill in lazily</span><a href="#l593"></a>
<span id="l594">            uwt = MethodTypeForm.canonicalize(wt, MethodTypeForm.UNWRAP, MethodTypeForm.UNWRAP);</span><a href="#l594"></a>
<span id="l595">            if (uwt == null)</span><a href="#l595"></a>
<span id="l596">                uwt = wt;    // type has no wrappers or prims at all</span><a href="#l596"></a>
<span id="l597">            wt.wrapAlt = uwt;</span><a href="#l597"></a>
<span id="l598">        }</span><a href="#l598"></a>
<span id="l599">        return uwt;</span><a href="#l599"></a>
<span id="l600">    }</span><a href="#l600"></a>
<span id="l601"></span><a href="#l601"></a>
<span id="l602">    /**</span><a href="#l602"></a>
<span id="l603">     * Returns the parameter type at the specified index, within this method type.</span><a href="#l603"></a>
<span id="l604">     * @param num the index (zero-based) of the desired parameter type</span><a href="#l604"></a>
<span id="l605">     * @return the selected parameter type</span><a href="#l605"></a>
<span id="l606">     * @throws IndexOutOfBoundsException if {@code num} is not a valid index into {@code parameterArray()}</span><a href="#l606"></a>
<span id="l607">     */</span><a href="#l607"></a>
<span id="l608">    public Class&lt;?&gt; parameterType(int num) {</span><a href="#l608"></a>
<span id="l609">        return ptypes[num];</span><a href="#l609"></a>
<span id="l610">    }</span><a href="#l610"></a>
<span id="l611">    /**</span><a href="#l611"></a>
<span id="l612">     * Returns the number of parameter types in this method type.</span><a href="#l612"></a>
<span id="l613">     * @return the number of parameter types</span><a href="#l613"></a>
<span id="l614">     */</span><a href="#l614"></a>
<span id="l615">    public int parameterCount() {</span><a href="#l615"></a>
<span id="l616">        return ptypes.length;</span><a href="#l616"></a>
<span id="l617">    }</span><a href="#l617"></a>
<span id="l618">    /**</span><a href="#l618"></a>
<span id="l619">     * Returns the return type of this method type.</span><a href="#l619"></a>
<span id="l620">     * @return the return type</span><a href="#l620"></a>
<span id="l621">     */</span><a href="#l621"></a>
<span id="l622">    public Class&lt;?&gt; returnType() {</span><a href="#l622"></a>
<span id="l623">        return rtype;</span><a href="#l623"></a>
<span id="l624">    }</span><a href="#l624"></a>
<span id="l625"></span><a href="#l625"></a>
<span id="l626">    /**</span><a href="#l626"></a>
<span id="l627">     * Presents the parameter types as a list (a convenience method).</span><a href="#l627"></a>
<span id="l628">     * The list will be immutable.</span><a href="#l628"></a>
<span id="l629">     * @return the parameter types (as an immutable list)</span><a href="#l629"></a>
<span id="l630">     */</span><a href="#l630"></a>
<span id="l631">    public List&lt;Class&lt;?&gt;&gt; parameterList() {</span><a href="#l631"></a>
<span id="l632">        return Collections.unmodifiableList(Arrays.asList(ptypes.clone()));</span><a href="#l632"></a>
<span id="l633">    }</span><a href="#l633"></a>
<span id="l634"></span><a href="#l634"></a>
<span id="l635">    /*non-public*/ Class&lt;?&gt; lastParameterType() {</span><a href="#l635"></a>
<span id="l636">        int len = ptypes.length;</span><a href="#l636"></a>
<span id="l637">        return len == 0 ? void.class : ptypes[len-1];</span><a href="#l637"></a>
<span id="l638">    }</span><a href="#l638"></a>
<span id="l639"></span><a href="#l639"></a>
<span id="l640">    /**</span><a href="#l640"></a>
<span id="l641">     * Presents the parameter types as an array (a convenience method).</span><a href="#l641"></a>
<span id="l642">     * Changes to the array will not result in changes to the type.</span><a href="#l642"></a>
<span id="l643">     * @return the parameter types (as a fresh copy if necessary)</span><a href="#l643"></a>
<span id="l644">     */</span><a href="#l644"></a>
<span id="l645">    public Class&lt;?&gt;[] parameterArray() {</span><a href="#l645"></a>
<span id="l646">        return ptypes.clone();</span><a href="#l646"></a>
<span id="l647">    }</span><a href="#l647"></a>
<span id="l648"></span><a href="#l648"></a>
<span id="l649">    /**</span><a href="#l649"></a>
<span id="l650">     * Compares the specified object with this type for equality.</span><a href="#l650"></a>
<span id="l651">     * That is, it returns &lt;tt&gt;true&lt;/tt&gt; if and only if the specified object</span><a href="#l651"></a>
<span id="l652">     * is also a method type with exactly the same parameters and return type.</span><a href="#l652"></a>
<span id="l653">     * @param x object to compare</span><a href="#l653"></a>
<span id="l654">     * @see Object#equals(Object)</span><a href="#l654"></a>
<span id="l655">     */</span><a href="#l655"></a>
<span id="l656">    @Override</span><a href="#l656"></a>
<span id="l657">    public boolean equals(Object x) {</span><a href="#l657"></a>
<span id="l658">        return this == x || x instanceof MethodType &amp;&amp; equals((MethodType)x);</span><a href="#l658"></a>
<span id="l659">    }</span><a href="#l659"></a>
<span id="l660"></span><a href="#l660"></a>
<span id="l661">    private boolean equals(MethodType that) {</span><a href="#l661"></a>
<span id="l662">        return this.rtype == that.rtype</span><a href="#l662"></a>
<span id="l663">            &amp;&amp; Arrays.equals(this.ptypes, that.ptypes);</span><a href="#l663"></a>
<span id="l664">    }</span><a href="#l664"></a>
<span id="l665"></span><a href="#l665"></a>
<span id="l666">    /**</span><a href="#l666"></a>
<span id="l667">     * Returns the hash code value for this method type.</span><a href="#l667"></a>
<span id="l668">     * It is defined to be the same as the hashcode of a List</span><a href="#l668"></a>
<span id="l669">     * whose elements are the return type followed by the</span><a href="#l669"></a>
<span id="l670">     * parameter types.</span><a href="#l670"></a>
<span id="l671">     * @return the hash code value for this method type</span><a href="#l671"></a>
<span id="l672">     * @see Object#hashCode()</span><a href="#l672"></a>
<span id="l673">     * @see #equals(Object)</span><a href="#l673"></a>
<span id="l674">     * @see List#hashCode()</span><a href="#l674"></a>
<span id="l675">     */</span><a href="#l675"></a>
<span id="l676">    @Override</span><a href="#l676"></a>
<span id="l677">    public int hashCode() {</span><a href="#l677"></a>
<span id="l678">      int hashCode = 31 + rtype.hashCode();</span><a href="#l678"></a>
<span id="l679">      for (Class&lt;?&gt; ptype : ptypes)</span><a href="#l679"></a>
<span id="l680">          hashCode = 31*hashCode + ptype.hashCode();</span><a href="#l680"></a>
<span id="l681">      return hashCode;</span><a href="#l681"></a>
<span id="l682">    }</span><a href="#l682"></a>
<span id="l683"></span><a href="#l683"></a>
<span id="l684">    /**</span><a href="#l684"></a>
<span id="l685">     * Returns a string representation of the method type,</span><a href="#l685"></a>
<span id="l686">     * of the form {@code &quot;(PT0,PT1...)RT&quot;}.</span><a href="#l686"></a>
<span id="l687">     * The string representation of a method type is a</span><a href="#l687"></a>
<span id="l688">     * parenthesis enclosed, comma separated list of type names,</span><a href="#l688"></a>
<span id="l689">     * followed immediately by the return type.</span><a href="#l689"></a>
<span id="l690">     * &lt;p&gt;</span><a href="#l690"></a>
<span id="l691">     * Each type is represented by its</span><a href="#l691"></a>
<span id="l692">     * {@link java.lang.Class#getSimpleName simple name}.</span><a href="#l692"></a>
<span id="l693">     */</span><a href="#l693"></a>
<span id="l694">    @Override</span><a href="#l694"></a>
<span id="l695">    public String toString() {</span><a href="#l695"></a>
<span id="l696">        StringBuilder sb = new StringBuilder();</span><a href="#l696"></a>
<span id="l697">        sb.append(&quot;(&quot;);</span><a href="#l697"></a>
<span id="l698">        for (int i = 0; i &lt; ptypes.length; i++) {</span><a href="#l698"></a>
<span id="l699">            if (i &gt; 0)  sb.append(&quot;,&quot;);</span><a href="#l699"></a>
<span id="l700">            sb.append(ptypes[i].getSimpleName());</span><a href="#l700"></a>
<span id="l701">        }</span><a href="#l701"></a>
<span id="l702">        sb.append(&quot;)&quot;);</span><a href="#l702"></a>
<span id="l703">        sb.append(rtype.getSimpleName());</span><a href="#l703"></a>
<span id="l704">        return sb.toString();</span><a href="#l704"></a>
<span id="l705">    }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707"></span><a href="#l707"></a>
<span id="l708">    /*non-public*/</span><a href="#l708"></a>
<span id="l709">    boolean isViewableAs(MethodType newType) {</span><a href="#l709"></a>
<span id="l710">        if (!VerifyType.isNullConversion(returnType(), newType.returnType()))</span><a href="#l710"></a>
<span id="l711">            return false;</span><a href="#l711"></a>
<span id="l712">        int argc = parameterCount();</span><a href="#l712"></a>
<span id="l713">        if (argc != newType.parameterCount())</span><a href="#l713"></a>
<span id="l714">            return false;</span><a href="#l714"></a>
<span id="l715">        for (int i = 0; i &lt; argc; i++) {</span><a href="#l715"></a>
<span id="l716">            if (!VerifyType.isNullConversion(newType.parameterType(i), parameterType(i)))</span><a href="#l716"></a>
<span id="l717">                return false;</span><a href="#l717"></a>
<span id="l718">        }</span><a href="#l718"></a>
<span id="l719">        return true;</span><a href="#l719"></a>
<span id="l720">    }</span><a href="#l720"></a>
<span id="l721">    /*non-public*/</span><a href="#l721"></a>
<span id="l722">    boolean isCastableTo(MethodType newType) {</span><a href="#l722"></a>
<span id="l723">        int argc = parameterCount();</span><a href="#l723"></a>
<span id="l724">        if (argc != newType.parameterCount())</span><a href="#l724"></a>
<span id="l725">            return false;</span><a href="#l725"></a>
<span id="l726">        return true;</span><a href="#l726"></a>
<span id="l727">    }</span><a href="#l727"></a>
<span id="l728">    /*non-public*/</span><a href="#l728"></a>
<span id="l729">    boolean isConvertibleTo(MethodType newType) {</span><a href="#l729"></a>
<span id="l730">        if (!canConvert(returnType(), newType.returnType()))</span><a href="#l730"></a>
<span id="l731">            return false;</span><a href="#l731"></a>
<span id="l732">        int argc = parameterCount();</span><a href="#l732"></a>
<span id="l733">        if (argc != newType.parameterCount())</span><a href="#l733"></a>
<span id="l734">            return false;</span><a href="#l734"></a>
<span id="l735">        for (int i = 0; i &lt; argc; i++) {</span><a href="#l735"></a>
<span id="l736">            if (!canConvert(newType.parameterType(i), parameterType(i)))</span><a href="#l736"></a>
<span id="l737">                return false;</span><a href="#l737"></a>
<span id="l738">        }</span><a href="#l738"></a>
<span id="l739">        return true;</span><a href="#l739"></a>
<span id="l740">    }</span><a href="#l740"></a>
<span id="l741">    /*non-public*/</span><a href="#l741"></a>
<span id="l742">    static boolean canConvert(Class&lt;?&gt; src, Class&lt;?&gt; dst) {</span><a href="#l742"></a>
<span id="l743">        // short-circuit a few cases:</span><a href="#l743"></a>
<span id="l744">        if (src == dst || dst == Object.class)  return true;</span><a href="#l744"></a>
<span id="l745">        // the remainder of this logic is documented in MethodHandle.asType</span><a href="#l745"></a>
<span id="l746">        if (src.isPrimitive()) {</span><a href="#l746"></a>
<span id="l747">            // can force void to an explicit null, a la reflect.Method.invoke</span><a href="#l747"></a>
<span id="l748">            // can also force void to a primitive zero, by analogy</span><a href="#l748"></a>
<span id="l749">            if (src == void.class)  return true;  //or !dst.isPrimitive()?</span><a href="#l749"></a>
<span id="l750">            Wrapper sw = Wrapper.forPrimitiveType(src);</span><a href="#l750"></a>
<span id="l751">            if (dst.isPrimitive()) {</span><a href="#l751"></a>
<span id="l752">                // P-&gt;P must widen</span><a href="#l752"></a>
<span id="l753">                return Wrapper.forPrimitiveType(dst).isConvertibleFrom(sw);</span><a href="#l753"></a>
<span id="l754">            } else {</span><a href="#l754"></a>
<span id="l755">                // P-&gt;R must box and widen</span><a href="#l755"></a>
<span id="l756">                return dst.isAssignableFrom(sw.wrapperType());</span><a href="#l756"></a>
<span id="l757">            }</span><a href="#l757"></a>
<span id="l758">        } else if (dst.isPrimitive()) {</span><a href="#l758"></a>
<span id="l759">            // any value can be dropped</span><a href="#l759"></a>
<span id="l760">            if (dst == void.class)  return true;</span><a href="#l760"></a>
<span id="l761">            Wrapper dw = Wrapper.forPrimitiveType(dst);</span><a href="#l761"></a>
<span id="l762">            // R-&gt;P must be able to unbox (from a dynamically chosen type) and widen</span><a href="#l762"></a>
<span id="l763">            // For example:</span><a href="#l763"></a>
<span id="l764">            //   Byte/Number/Comparable/Object -&gt; dw:Byte -&gt; byte.</span><a href="#l764"></a>
<span id="l765">            //   Character/Comparable/Object -&gt; dw:Character -&gt; char</span><a href="#l765"></a>
<span id="l766">            //   Boolean/Comparable/Object -&gt; dw:Boolean -&gt; boolean</span><a href="#l766"></a>
<span id="l767">            // This means that dw must be cast-compatible with src.</span><a href="#l767"></a>
<span id="l768">            if (src.isAssignableFrom(dw.wrapperType())) {</span><a href="#l768"></a>
<span id="l769">                return true;</span><a href="#l769"></a>
<span id="l770">            }</span><a href="#l770"></a>
<span id="l771">            // The above does not work if the source reference is strongly typed</span><a href="#l771"></a>
<span id="l772">            // to a wrapper whose primitive must be widened.  For example:</span><a href="#l772"></a>
<span id="l773">            //   Byte -&gt; unbox:byte -&gt; short/int/long/float/double</span><a href="#l773"></a>
<span id="l774">            //   Character -&gt; unbox:char -&gt; int/long/float/double</span><a href="#l774"></a>
<span id="l775">            if (Wrapper.isWrapperType(src) &amp;&amp;</span><a href="#l775"></a>
<span id="l776">                dw.isConvertibleFrom(Wrapper.forWrapperType(src))) {</span><a href="#l776"></a>
<span id="l777">                // can unbox from src and then widen to dst</span><a href="#l777"></a>
<span id="l778">                return true;</span><a href="#l778"></a>
<span id="l779">            }</span><a href="#l779"></a>
<span id="l780">            // We have already covered cases which arise due to runtime unboxing</span><a href="#l780"></a>
<span id="l781">            // of a reference type which covers several wrapper types:</span><a href="#l781"></a>
<span id="l782">            //   Object -&gt; cast:Integer -&gt; unbox:int -&gt; long/float/double</span><a href="#l782"></a>
<span id="l783">            //   Serializable -&gt; cast:Byte -&gt; unbox:byte -&gt; byte/short/int/long/float/double</span><a href="#l783"></a>
<span id="l784">            // An marginal case is Number -&gt; dw:Character -&gt; char, which would be OK if there were a</span><a href="#l784"></a>
<span id="l785">            // subclass of Number which wraps a value that can convert to char.</span><a href="#l785"></a>
<span id="l786">            // Since there is none, we don't need an extra check here to cover char or boolean.</span><a href="#l786"></a>
<span id="l787">            return false;</span><a href="#l787"></a>
<span id="l788">        } else {</span><a href="#l788"></a>
<span id="l789">            // R-&gt;R always works, since null is always valid dynamically</span><a href="#l789"></a>
<span id="l790">            return true;</span><a href="#l790"></a>
<span id="l791">        }</span><a href="#l791"></a>
<span id="l792">    }</span><a href="#l792"></a>
<span id="l793"></span><a href="#l793"></a>
<span id="l794">    /// Queries which have to do with the bytecode architecture</span><a href="#l794"></a>
<span id="l795"></span><a href="#l795"></a>
<span id="l796">    /** Reports the number of JVM stack slots required to invoke a method</span><a href="#l796"></a>
<span id="l797">     * of this type.  Note that (for historical reasons) the JVM requires</span><a href="#l797"></a>
<span id="l798">     * a second stack slot to pass long and double arguments.</span><a href="#l798"></a>
<span id="l799">     * So this method returns {@link #parameterCount() parameterCount} plus the</span><a href="#l799"></a>
<span id="l800">     * number of long and double parameters (if any).</span><a href="#l800"></a>
<span id="l801">     * &lt;p&gt;</span><a href="#l801"></a>
<span id="l802">     * This method is included for the benefit of applications that must</span><a href="#l802"></a>
<span id="l803">     * generate bytecodes that process method handles and invokedynamic.</span><a href="#l803"></a>
<span id="l804">     * @return the number of JVM stack slots for this type's parameters</span><a href="#l804"></a>
<span id="l805">     */</span><a href="#l805"></a>
<span id="l806">    /*non-public*/ int parameterSlotCount() {</span><a href="#l806"></a>
<span id="l807">        return form.parameterSlotCount();</span><a href="#l807"></a>
<span id="l808">    }</span><a href="#l808"></a>
<span id="l809"></span><a href="#l809"></a>
<span id="l810">    /*non-public*/ Invokers invokers() {</span><a href="#l810"></a>
<span id="l811">        Invokers inv = invokers;</span><a href="#l811"></a>
<span id="l812">        if (inv != null)  return inv;</span><a href="#l812"></a>
<span id="l813">        invokers = inv = new Invokers(this);</span><a href="#l813"></a>
<span id="l814">        return inv;</span><a href="#l814"></a>
<span id="l815">    }</span><a href="#l815"></a>
<span id="l816"></span><a href="#l816"></a>
<span id="l817">    /** Reports the number of JVM stack slots which carry all parameters including and after</span><a href="#l817"></a>
<span id="l818">     * the given position, which must be in the range of 0 to</span><a href="#l818"></a>
<span id="l819">     * {@code parameterCount} inclusive.  Successive parameters are</span><a href="#l819"></a>
<span id="l820">     * more shallowly stacked, and parameters are indexed in the bytecodes</span><a href="#l820"></a>
<span id="l821">     * according to their trailing edge.  Thus, to obtain the depth</span><a href="#l821"></a>
<span id="l822">     * in the outgoing call stack of parameter {@code N}, obtain</span><a href="#l822"></a>
<span id="l823">     * the {@code parameterSlotDepth} of its trailing edge</span><a href="#l823"></a>
<span id="l824">     * at position {@code N+1}.</span><a href="#l824"></a>
<span id="l825">     * &lt;p&gt;</span><a href="#l825"></a>
<span id="l826">     * Parameters of type {@code long} and {@code double} occupy</span><a href="#l826"></a>
<span id="l827">     * two stack slots (for historical reasons) and all others occupy one.</span><a href="#l827"></a>
<span id="l828">     * Therefore, the number returned is the number of arguments</span><a href="#l828"></a>
<span id="l829">     * &lt;em&gt;including&lt;/em&gt; and &lt;em&gt;after&lt;/em&gt; the given parameter,</span><a href="#l829"></a>
<span id="l830">     * &lt;em&gt;plus&lt;/em&gt; the number of long or double arguments</span><a href="#l830"></a>
<span id="l831">     * at or after after the argument for the given parameter.</span><a href="#l831"></a>
<span id="l832">     * &lt;p&gt;</span><a href="#l832"></a>
<span id="l833">     * This method is included for the benefit of applications that must</span><a href="#l833"></a>
<span id="l834">     * generate bytecodes that process method handles and invokedynamic.</span><a href="#l834"></a>
<span id="l835">     * @param num an index (zero-based, inclusive) within the parameter types</span><a href="#l835"></a>
<span id="l836">     * @return the index of the (shallowest) JVM stack slot transmitting the</span><a href="#l836"></a>
<span id="l837">     *         given parameter</span><a href="#l837"></a>
<span id="l838">     * @throws IllegalArgumentException if {@code num} is negative or greater than {@code parameterCount()}</span><a href="#l838"></a>
<span id="l839">     */</span><a href="#l839"></a>
<span id="l840">    /*non-public*/ int parameterSlotDepth(int num) {</span><a href="#l840"></a>
<span id="l841">        if (num &lt; 0 || num &gt; ptypes.length)</span><a href="#l841"></a>
<span id="l842">            parameterType(num);  // force a range check</span><a href="#l842"></a>
<span id="l843">        return form.parameterToArgSlot(num-1);</span><a href="#l843"></a>
<span id="l844">    }</span><a href="#l844"></a>
<span id="l845"></span><a href="#l845"></a>
<span id="l846">    /** Reports the number of JVM stack slots required to receive a return value</span><a href="#l846"></a>
<span id="l847">     * from a method of this type.</span><a href="#l847"></a>
<span id="l848">     * If the {@link #returnType() return type} is void, it will be zero,</span><a href="#l848"></a>
<span id="l849">     * else if the return type is long or double, it will be two, else one.</span><a href="#l849"></a>
<span id="l850">     * &lt;p&gt;</span><a href="#l850"></a>
<span id="l851">     * This method is included for the benefit of applications that must</span><a href="#l851"></a>
<span id="l852">     * generate bytecodes that process method handles and invokedynamic.</span><a href="#l852"></a>
<span id="l853">     * @return the number of JVM stack slots (0, 1, or 2) for this type's return value</span><a href="#l853"></a>
<span id="l854">     * Will be removed for PFD.</span><a href="#l854"></a>
<span id="l855">     */</span><a href="#l855"></a>
<span id="l856">    /*non-public*/ int returnSlotCount() {</span><a href="#l856"></a>
<span id="l857">        return form.returnSlotCount();</span><a href="#l857"></a>
<span id="l858">    }</span><a href="#l858"></a>
<span id="l859"></span><a href="#l859"></a>
<span id="l860">    /**</span><a href="#l860"></a>
<span id="l861">     * Finds or creates an instance of a method type, given the spelling of its bytecode descriptor.</span><a href="#l861"></a>
<span id="l862">     * Convenience method for {@link #methodType(java.lang.Class, java.lang.Class[]) methodType}.</span><a href="#l862"></a>
<span id="l863">     * Any class or interface name embedded in the descriptor string</span><a href="#l863"></a>
<span id="l864">     * will be resolved by calling {@link ClassLoader#loadClass(java.lang.String)}</span><a href="#l864"></a>
<span id="l865">     * on the given loader (or if it is null, on the system class loader).</span><a href="#l865"></a>
<span id="l866">     * &lt;p&gt;</span><a href="#l866"></a>
<span id="l867">     * Note that it is possible to encounter method types which cannot be</span><a href="#l867"></a>
<span id="l868">     * constructed by this method, because their component types are</span><a href="#l868"></a>
<span id="l869">     * not all reachable from a common class loader.</span><a href="#l869"></a>
<span id="l870">     * &lt;p&gt;</span><a href="#l870"></a>
<span id="l871">     * This method is included for the benefit of applications that must</span><a href="#l871"></a>
<span id="l872">     * generate bytecodes that process method handles and {@code invokedynamic}.</span><a href="#l872"></a>
<span id="l873">     * @param descriptor a bytecode-level type descriptor string &quot;(T...)T&quot;</span><a href="#l873"></a>
<span id="l874">     * @param loader the class loader in which to look up the types</span><a href="#l874"></a>
<span id="l875">     * @return a method type matching the bytecode-level type descriptor</span><a href="#l875"></a>
<span id="l876">     * @throws NullPointerException if the string is null</span><a href="#l876"></a>
<span id="l877">     * @throws IllegalArgumentException if the string is not well-formed</span><a href="#l877"></a>
<span id="l878">     * @throws TypeNotPresentException if a named type cannot be found</span><a href="#l878"></a>
<span id="l879">     */</span><a href="#l879"></a>
<span id="l880">    public static MethodType fromMethodDescriptorString(String descriptor, ClassLoader loader)</span><a href="#l880"></a>
<span id="l881">        throws IllegalArgumentException, TypeNotPresentException</span><a href="#l881"></a>
<span id="l882">    {</span><a href="#l882"></a>
<span id="l883">        if (!descriptor.startsWith(&quot;(&quot;) ||  // also generates NPE if needed</span><a href="#l883"></a>
<span id="l884">            descriptor.indexOf(')') &lt; 0 ||</span><a href="#l884"></a>
<span id="l885">            descriptor.indexOf('.') &gt;= 0)</span><a href="#l885"></a>
<span id="l886">            throw new IllegalArgumentException(&quot;not a method descriptor: &quot;+descriptor);</span><a href="#l886"></a>
<span id="l887">        List&lt;Class&lt;?&gt;&gt; types = BytecodeDescriptor.parseMethod(descriptor, loader);</span><a href="#l887"></a>
<span id="l888">        Class&lt;?&gt; rtype = types.remove(types.size() - 1);</span><a href="#l888"></a>
<span id="l889">        checkSlotCount(types.size());</span><a href="#l889"></a>
<span id="l890">        Class&lt;?&gt;[] ptypes = listToArray(types);</span><a href="#l890"></a>
<span id="l891">        return makeImpl(rtype, ptypes, true);</span><a href="#l891"></a>
<span id="l892">    }</span><a href="#l892"></a>
<span id="l893"></span><a href="#l893"></a>
<span id="l894">    /**</span><a href="#l894"></a>
<span id="l895">     * Produces a bytecode descriptor representation of the method type.</span><a href="#l895"></a>
<span id="l896">     * &lt;p&gt;</span><a href="#l896"></a>
<span id="l897">     * Note that this is not a strict inverse of {@link #fromMethodDescriptorString fromMethodDescriptorString}.</span><a href="#l897"></a>
<span id="l898">     * Two distinct classes which share a common name but have different class loaders</span><a href="#l898"></a>
<span id="l899">     * will appear identical when viewed within descriptor strings.</span><a href="#l899"></a>
<span id="l900">     * &lt;p&gt;</span><a href="#l900"></a>
<span id="l901">     * This method is included for the benefit of applications that must</span><a href="#l901"></a>
<span id="l902">     * generate bytecodes that process method handles and {@code invokedynamic}.</span><a href="#l902"></a>
<span id="l903">     * {@link #fromMethodDescriptorString(java.lang.String, java.lang.ClassLoader) fromMethodDescriptorString},</span><a href="#l903"></a>
<span id="l904">     * because the latter requires a suitable class loader argument.</span><a href="#l904"></a>
<span id="l905">     * @return the bytecode type descriptor representation</span><a href="#l905"></a>
<span id="l906">     */</span><a href="#l906"></a>
<span id="l907">    public String toMethodDescriptorString() {</span><a href="#l907"></a>
<span id="l908">        return BytecodeDescriptor.unparse(this);</span><a href="#l908"></a>
<span id="l909">    }</span><a href="#l909"></a>
<span id="l910"></span><a href="#l910"></a>
<span id="l911">    /*non-public*/ static String toFieldDescriptorString(Class&lt;?&gt; cls) {</span><a href="#l911"></a>
<span id="l912">        return BytecodeDescriptor.unparse(cls);</span><a href="#l912"></a>
<span id="l913">    }</span><a href="#l913"></a>
<span id="l914"></span><a href="#l914"></a>
<span id="l915">    /// Serialization.</span><a href="#l915"></a>
<span id="l916"></span><a href="#l916"></a>
<span id="l917">    /**</span><a href="#l917"></a>
<span id="l918">     * There are no serializable fields for {@code MethodType}.</span><a href="#l918"></a>
<span id="l919">     */</span><a href="#l919"></a>
<span id="l920">    private static final java.io.ObjectStreamField[] serialPersistentFields = { };</span><a href="#l920"></a>
<span id="l921"></span><a href="#l921"></a>
<span id="l922">    /**</span><a href="#l922"></a>
<span id="l923">     * Save the {@code MethodType} instance to a stream.</span><a href="#l923"></a>
<span id="l924">     *</span><a href="#l924"></a>
<span id="l925">     * @serialData</span><a href="#l925"></a>
<span id="l926">     * For portability, the serialized format does not refer to named fields.</span><a href="#l926"></a>
<span id="l927">     * Instead, the return type and parameter type arrays are written directly</span><a href="#l927"></a>
<span id="l928">     * from the {@code writeObject} method, using two calls to {@code s.writeObject}</span><a href="#l928"></a>
<span id="l929">     * as follows:</span><a href="#l929"></a>
<span id="l930">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l930"></a>
<span id="l931">s.writeObject(this.returnType());</span><a href="#l931"></a>
<span id="l932">s.writeObject(this.parameterArray());</span><a href="#l932"></a>
<span id="l933">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l933"></a>
<span id="l934">     * &lt;p&gt;</span><a href="#l934"></a>
<span id="l935">     * The deserialized field values are checked as if they were</span><a href="#l935"></a>
<span id="l936">     * provided to the factory method {@link #methodType(Class,Class[]) methodType}.</span><a href="#l936"></a>
<span id="l937">     * For example, null values, or {@code void} parameter types,</span><a href="#l937"></a>
<span id="l938">     * will lead to exceptions during deserialization.</span><a href="#l938"></a>
<span id="l939">     * @param the stream to write the object to</span><a href="#l939"></a>
<span id="l940">     */</span><a href="#l940"></a>
<span id="l941">    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {</span><a href="#l941"></a>
<span id="l942">        s.defaultWriteObject();  // requires serialPersistentFields to be an empty array</span><a href="#l942"></a>
<span id="l943">        s.writeObject(returnType());</span><a href="#l943"></a>
<span id="l944">        s.writeObject(parameterArray());</span><a href="#l944"></a>
<span id="l945">    }</span><a href="#l945"></a>
<span id="l946"></span><a href="#l946"></a>
<span id="l947">    /**</span><a href="#l947"></a>
<span id="l948">     * Reconstitute the {@code MethodType} instance from a stream (that is,</span><a href="#l948"></a>
<span id="l949">     * deserialize it).</span><a href="#l949"></a>
<span id="l950">     * This instance is a scratch object with bogus final fields.</span><a href="#l950"></a>
<span id="l951">     * It provides the parameters to the factory method called by</span><a href="#l951"></a>
<span id="l952">     * {@link #readResolve readResolve}.</span><a href="#l952"></a>
<span id="l953">     * After that call it is discarded.</span><a href="#l953"></a>
<span id="l954">     * @param the stream to read the object from</span><a href="#l954"></a>
<span id="l955">     * @see #readResolve</span><a href="#l955"></a>
<span id="l956">     * @see #writeObject</span><a href="#l956"></a>
<span id="l957">     */</span><a href="#l957"></a>
<span id="l958">    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {</span><a href="#l958"></a>
<span id="l959">        // Assign defaults in case this object escapes</span><a href="#l959"></a>
<span id="l960">        UNSAFE.putObject(this, rtypeOffset, void.class);</span><a href="#l960"></a>
<span id="l961">        UNSAFE.putObject(this, ptypesOffset, NO_PTYPES);</span><a href="#l961"></a>
<span id="l962"></span><a href="#l962"></a>
<span id="l963">        s.defaultReadObject();  // requires serialPersistentFields to be an empty array</span><a href="#l963"></a>
<span id="l964"></span><a href="#l964"></a>
<span id="l965">        Class&lt;?&gt;   returnType     = (Class&lt;?&gt;)   s.readObject();</span><a href="#l965"></a>
<span id="l966">        Class&lt;?&gt;[] parameterArray = (Class&lt;?&gt;[]) s.readObject();</span><a href="#l966"></a>
<span id="l967"></span><a href="#l967"></a>
<span id="l968">        // Verify all operands, and make sure ptypes is unshared</span><a href="#l968"></a>
<span id="l969">        // Cache the new MethodType for readResolve</span><a href="#l969"></a>
<span id="l970">        wrapAlt = new MethodType[]{MethodType.methodType(returnType, parameterArray)};</span><a href="#l970"></a>
<span id="l971">    }</span><a href="#l971"></a>
<span id="l972"></span><a href="#l972"></a>
<span id="l973">    // Support for resetting final fields while deserializing</span><a href="#l973"></a>
<span id="l974">    private static final long rtypeOffset, ptypesOffset;</span><a href="#l974"></a>
<span id="l975">    static {</span><a href="#l975"></a>
<span id="l976">        try {</span><a href="#l976"></a>
<span id="l977">            rtypeOffset = UNSAFE.objectFieldOffset</span><a href="#l977"></a>
<span id="l978">                (MethodType.class.getDeclaredField(&quot;rtype&quot;));</span><a href="#l978"></a>
<span id="l979">            ptypesOffset = UNSAFE.objectFieldOffset</span><a href="#l979"></a>
<span id="l980">                (MethodType.class.getDeclaredField(&quot;ptypes&quot;));</span><a href="#l980"></a>
<span id="l981">        } catch (Exception ex) {</span><a href="#l981"></a>
<span id="l982">            throw new Error(ex);</span><a href="#l982"></a>
<span id="l983">        }</span><a href="#l983"></a>
<span id="l984">    }</span><a href="#l984"></a>
<span id="l985"></span><a href="#l985"></a>
<span id="l986">    /**</span><a href="#l986"></a>
<span id="l987">     * Resolves and initializes a {@code MethodType} object</span><a href="#l987"></a>
<span id="l988">     * after serialization.</span><a href="#l988"></a>
<span id="l989">     * @return the fully initialized {@code MethodType} object</span><a href="#l989"></a>
<span id="l990">     */</span><a href="#l990"></a>
<span id="l991">    private Object readResolve() {</span><a href="#l991"></a>
<span id="l992">        // Do not use a trusted path for deserialization:</span><a href="#l992"></a>
<span id="l993">        //    return makeImpl(rtype, ptypes, true);</span><a href="#l993"></a>
<span id="l994">        // Verify all operands, and make sure ptypes is unshared:</span><a href="#l994"></a>
<span id="l995">        // Return a new validated MethodType for the rtype and ptypes passed from readObject.</span><a href="#l995"></a>
<span id="l996">        MethodType mt = ((MethodType[])wrapAlt)[0];</span><a href="#l996"></a>
<span id="l997">        wrapAlt = null;</span><a href="#l997"></a>
<span id="l998">        return mt;</span><a href="#l998"></a>
<span id="l999">    }</span><a href="#l999"></a>
<span id="l1000"></span><a href="#l1000"></a>
<span id="l1001">    /**</span><a href="#l1001"></a>
<span id="l1002">     * Weak intern set based on implementation of the &lt;tt&gt;HashSet&lt;/tt&gt; and</span><a href="#l1002"></a>
<span id="l1003">     * &lt;tt&gt;WeakHashMap&lt;/tt&gt;, with &lt;em&gt;weak values&lt;/em&gt;.  Note: &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l1003"></a>
<span id="l1004">     * values will yield &lt;tt&gt;NullPointerException&lt;/tt&gt;</span><a href="#l1004"></a>
<span id="l1005">     * Refer to implementation of WeakInternSet for details.</span><a href="#l1005"></a>
<span id="l1006">     *</span><a href="#l1006"></a>
<span id="l1007">     * @see         java.util.HashMap</span><a href="#l1007"></a>
<span id="l1008">     * @see         java.util.HashSet</span><a href="#l1008"></a>
<span id="l1009">     * @see         java.util.WeakHashMap</span><a href="#l1009"></a>
<span id="l1010">     * @see         java.lang.ref.WeakReference</span><a href="#l1010"></a>
<span id="l1011">     */</span><a href="#l1011"></a>
<span id="l1012">    private static class WeakInternSet {</span><a href="#l1012"></a>
<span id="l1013">        // The default initial capacity -- MUST be a power of two.</span><a href="#l1013"></a>
<span id="l1014">        private static final int DEFAULT_INITIAL_CAPACITY = 16;</span><a href="#l1014"></a>
<span id="l1015"></span><a href="#l1015"></a>
<span id="l1016">        // The maximum capacity, used if a higher value is implicitly specified</span><a href="#l1016"></a>
<span id="l1017">        // by either of the constructors with arguments.</span><a href="#l1017"></a>
<span id="l1018">        // MUST be a power of two &lt;= 1&lt;&lt;30.</span><a href="#l1018"></a>
<span id="l1019">        private static final int MAXIMUM_CAPACITY = 1 &lt;&lt; 30;</span><a href="#l1019"></a>
<span id="l1020"></span><a href="#l1020"></a>
<span id="l1021">        // The load factor used when none specified in constructor.</span><a href="#l1021"></a>
<span id="l1022">        private static final float DEFAULT_LOAD_FACTOR = 0.75f;</span><a href="#l1022"></a>
<span id="l1023"></span><a href="#l1023"></a>
<span id="l1024">        // The table, resized as necessary. Length MUST Always be a power of two.</span><a href="#l1024"></a>
<span id="l1025">        private Entry[] table;</span><a href="#l1025"></a>
<span id="l1026"></span><a href="#l1026"></a>
<span id="l1027">        // The number of entries contained in this set.</span><a href="#l1027"></a>
<span id="l1028">        private int size;</span><a href="#l1028"></a>
<span id="l1029"></span><a href="#l1029"></a>
<span id="l1030">        // The next size value at which to resize (capacity * load factor).</span><a href="#l1030"></a>
<span id="l1031">        private int threshold;</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">        // The load factor for the hash table.</span><a href="#l1033"></a>
<span id="l1034">        private final float loadFactor;</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">        // Reference queue for cleared WeakEntries</span><a href="#l1036"></a>
<span id="l1037">        private final ReferenceQueue&lt;Object&gt; queue = new ReferenceQueue&lt;&gt;();</span><a href="#l1037"></a>
<span id="l1038"></span><a href="#l1038"></a>
<span id="l1039">        private Entry[] newTable(int n) {</span><a href="#l1039"></a>
<span id="l1040">            return new Entry[n];</span><a href="#l1040"></a>
<span id="l1041">        }</span><a href="#l1041"></a>
<span id="l1042"></span><a href="#l1042"></a>
<span id="l1043">        /**</span><a href="#l1043"></a>
<span id="l1044">         * Constructs a new, empty &lt;tt&gt;WeakInternSet&lt;/tt&gt; with the default initial</span><a href="#l1044"></a>
<span id="l1045">         * capacity (16) and load factor (0.75).</span><a href="#l1045"></a>
<span id="l1046">         */</span><a href="#l1046"></a>
<span id="l1047">        WeakInternSet() {</span><a href="#l1047"></a>
<span id="l1048">            this.loadFactor = DEFAULT_LOAD_FACTOR;</span><a href="#l1048"></a>
<span id="l1049">            threshold = DEFAULT_INITIAL_CAPACITY;</span><a href="#l1049"></a>
<span id="l1050">            table = newTable(DEFAULT_INITIAL_CAPACITY);</span><a href="#l1050"></a>
<span id="l1051">        }</span><a href="#l1051"></a>
<span id="l1052"></span><a href="#l1052"></a>
<span id="l1053">        /**</span><a href="#l1053"></a>
<span id="l1054">         * Applies a supplemental hash function to a given hashCode, which</span><a href="#l1054"></a>
<span id="l1055">         * defends against poor quality hash functions.  This is critical</span><a href="#l1055"></a>
<span id="l1056">         * because hashing uses power-of-two length hash tables, that</span><a href="#l1056"></a>
<span id="l1057">         * otherwise encounter collisions for hashCodes that do not differ</span><a href="#l1057"></a>
<span id="l1058">         * in lower bits.</span><a href="#l1058"></a>
<span id="l1059">         * @param h preliminary hash code value</span><a href="#l1059"></a>
<span id="l1060">         * @return supplemental hash code value</span><a href="#l1060"></a>
<span id="l1061">         */</span><a href="#l1061"></a>
<span id="l1062">        private static int hash(int h) {</span><a href="#l1062"></a>
<span id="l1063">            // This function ensures that hashCodes that differ only by</span><a href="#l1063"></a>
<span id="l1064">            // constant multiples at each bit position have a bounded</span><a href="#l1064"></a>
<span id="l1065">            // number of collisions (approximately 8 at default load factor).</span><a href="#l1065"></a>
<span id="l1066">            h ^= (h &gt;&gt;&gt; 20) ^ (h &gt;&gt;&gt; 12);</span><a href="#l1066"></a>
<span id="l1067">            return h ^ (h &gt;&gt;&gt; 7) ^ (h &gt;&gt;&gt; 4);</span><a href="#l1067"></a>
<span id="l1068">        }</span><a href="#l1068"></a>
<span id="l1069"></span><a href="#l1069"></a>
<span id="l1070">        /**</span><a href="#l1070"></a>
<span id="l1071">         * Checks for equality of non-null reference x and possibly-null y.  By</span><a href="#l1071"></a>
<span id="l1072">         * default uses Object.equals.</span><a href="#l1072"></a>
<span id="l1073">         * @param x first object to compare</span><a href="#l1073"></a>
<span id="l1074">         * @param y second object to compare</span><a href="#l1074"></a>
<span id="l1075">         * @return &lt;tt&gt;true&lt;/tt&gt; if objects are equal</span><a href="#l1075"></a>
<span id="l1076">         */</span><a href="#l1076"></a>
<span id="l1077">        private static boolean eq(Object x, Object y) {</span><a href="#l1077"></a>
<span id="l1078">            return x == y || x.equals(y);</span><a href="#l1078"></a>
<span id="l1079">        }</span><a href="#l1079"></a>
<span id="l1080"></span><a href="#l1080"></a>
<span id="l1081">        /**</span><a href="#l1081"></a>
<span id="l1082">         * Returns index for hash code h.</span><a href="#l1082"></a>
<span id="l1083">         * @param h      raw hash code</span><a href="#l1083"></a>
<span id="l1084">         * @param length length of table (power of 2)</span><a href="#l1084"></a>
<span id="l1085">         * @return index in table</span><a href="#l1085"></a>
<span id="l1086">         */</span><a href="#l1086"></a>
<span id="l1087">        private static int indexFor(int h, int length) {</span><a href="#l1087"></a>
<span id="l1088">            return h &amp; (length-1);</span><a href="#l1088"></a>
<span id="l1089">        }</span><a href="#l1089"></a>
<span id="l1090"></span><a href="#l1090"></a>
<span id="l1091">        /**</span><a href="#l1091"></a>
<span id="l1092">         * Expunges stale entries from the table.</span><a href="#l1092"></a>
<span id="l1093">         */</span><a href="#l1093"></a>
<span id="l1094">        private void expungeStaleEntries() {</span><a href="#l1094"></a>
<span id="l1095">            for (Object x; (x = queue.poll()) != null; ) {</span><a href="#l1095"></a>
<span id="l1096">                synchronized (queue) {</span><a href="#l1096"></a>
<span id="l1097">                    Entry entry = (Entry) x;</span><a href="#l1097"></a>
<span id="l1098">                    int i = indexFor(entry.hash, table.length);</span><a href="#l1098"></a>
<span id="l1099">                    Entry prev = table[i];</span><a href="#l1099"></a>
<span id="l1100">                    Entry p = prev;</span><a href="#l1100"></a>
<span id="l1101">                    while (p != null) {</span><a href="#l1101"></a>
<span id="l1102">                        Entry next = p.next;</span><a href="#l1102"></a>
<span id="l1103">                        if (p == entry) {</span><a href="#l1103"></a>
<span id="l1104">                            if (prev == entry)</span><a href="#l1104"></a>
<span id="l1105">                                table[i] = next;</span><a href="#l1105"></a>
<span id="l1106">                            else</span><a href="#l1106"></a>
<span id="l1107">                                prev.next = next;</span><a href="#l1107"></a>
<span id="l1108">                            entry.next = null;</span><a href="#l1108"></a>
<span id="l1109">                            size--;</span><a href="#l1109"></a>
<span id="l1110">                            break;</span><a href="#l1110"></a>
<span id="l1111">                        }</span><a href="#l1111"></a>
<span id="l1112">                        prev = p;</span><a href="#l1112"></a>
<span id="l1113">                        p = next;</span><a href="#l1113"></a>
<span id="l1114">                    }</span><a href="#l1114"></a>
<span id="l1115">                }</span><a href="#l1115"></a>
<span id="l1116">            }</span><a href="#l1116"></a>
<span id="l1117">        }</span><a href="#l1117"></a>
<span id="l1118"></span><a href="#l1118"></a>
<span id="l1119">        /**</span><a href="#l1119"></a>
<span id="l1120">         * Returns the table after first expunging stale entries.</span><a href="#l1120"></a>
<span id="l1121">         * @return an expunged hash table</span><a href="#l1121"></a>
<span id="l1122">         */</span><a href="#l1122"></a>
<span id="l1123">        private Entry[] getTable() {</span><a href="#l1123"></a>
<span id="l1124">            expungeStaleEntries();</span><a href="#l1124"></a>
<span id="l1125">            return table;</span><a href="#l1125"></a>
<span id="l1126">        }</span><a href="#l1126"></a>
<span id="l1127"></span><a href="#l1127"></a>
<span id="l1128">        /**</span><a href="#l1128"></a>
<span id="l1129">         * Returns the entry to which the specified value is mapped,</span><a href="#l1129"></a>
<span id="l1130">         * or {@code null} if this set contains no entry for the value.</span><a href="#l1130"></a>
<span id="l1131">         *</span><a href="#l1131"></a>
<span id="l1132">         * &lt;p&gt;More formally, if this set contains an entry for value</span><a href="#l1132"></a>
<span id="l1133">         * {@code entry} to a value {@code value} such that</span><a href="#l1133"></a>
<span id="l1134">         * {@code entry.equals(value)}, then this method returns {@code entry};</span><a href="#l1134"></a>
<span id="l1135">         * otherwise it returns {@code null}.</span><a href="#l1135"></a>
<span id="l1136">         *</span><a href="#l1136"></a>
<span id="l1137">         * @param value value to search for in set</span><a href="#l1137"></a>
<span id="l1138">         * @return interned value if in set, otherwise &lt;tt&gt;null&lt;/tt&gt;</span><a href="#l1138"></a>
<span id="l1139">         */</span><a href="#l1139"></a>
<span id="l1140">        synchronized MethodType get(MethodType value) {</span><a href="#l1140"></a>
<span id="l1141">            int h = hash(value.hashCode());</span><a href="#l1141"></a>
<span id="l1142">            Entry[] tab = getTable();</span><a href="#l1142"></a>
<span id="l1143">            int index = indexFor(h, tab.length);</span><a href="#l1143"></a>
<span id="l1144">            Entry e = tab[index];</span><a href="#l1144"></a>
<span id="l1145">            MethodType g;</span><a href="#l1145"></a>
<span id="l1146">            while (e != null) {</span><a href="#l1146"></a>
<span id="l1147">                if (e.hash == h &amp;&amp; eq(value, g = e.get()))</span><a href="#l1147"></a>
<span id="l1148">                    return g;</span><a href="#l1148"></a>
<span id="l1149">                e = e.next;</span><a href="#l1149"></a>
<span id="l1150">            }</span><a href="#l1150"></a>
<span id="l1151">            return null;</span><a href="#l1151"></a>
<span id="l1152">        }</span><a href="#l1152"></a>
<span id="l1153"></span><a href="#l1153"></a>
<span id="l1154">        /**</span><a href="#l1154"></a>
<span id="l1155">         * Attempts to add the specified value to the set and returns same value.</span><a href="#l1155"></a>
<span id="l1156">         * If the set previously contained an entry for this value, the old</span><a href="#l1156"></a>
<span id="l1157">         * value is left untouched and returned as the result.</span><a href="#l1157"></a>
<span id="l1158">         *</span><a href="#l1158"></a>
<span id="l1159">         * @param value value to be added</span><a href="#l1159"></a>
<span id="l1160">         * @return the previous entry associated with &lt;tt&gt;value&lt;/tt&gt;, or</span><a href="#l1160"></a>
<span id="l1161">         *         &lt;tt&gt;value&lt;/tt&gt; if there was no previous entry found</span><a href="#l1161"></a>
<span id="l1162">         */</span><a href="#l1162"></a>
<span id="l1163">        synchronized MethodType add(MethodType value) {</span><a href="#l1163"></a>
<span id="l1164">            int h = hash(value.hashCode());</span><a href="#l1164"></a>
<span id="l1165">            Entry[] tab = getTable();</span><a href="#l1165"></a>
<span id="l1166">            int i = indexFor(h, tab.length);</span><a href="#l1166"></a>
<span id="l1167">            MethodType g;</span><a href="#l1167"></a>
<span id="l1168">            for (Entry e = tab[i]; e != null; e = e.next) {</span><a href="#l1168"></a>
<span id="l1169">                if (h == e.hash &amp;&amp; eq(value, g = e.get())) {</span><a href="#l1169"></a>
<span id="l1170">                    return g;</span><a href="#l1170"></a>
<span id="l1171">                }</span><a href="#l1171"></a>
<span id="l1172">            }</span><a href="#l1172"></a>
<span id="l1173">            Entry e = tab[i];</span><a href="#l1173"></a>
<span id="l1174">            tab[i] = new Entry(value, queue, h, e);</span><a href="#l1174"></a>
<span id="l1175">            if (++size &gt;= threshold)</span><a href="#l1175"></a>
<span id="l1176">                resize(tab.length * 2);</span><a href="#l1176"></a>
<span id="l1177">            return value;</span><a href="#l1177"></a>
<span id="l1178">        }</span><a href="#l1178"></a>
<span id="l1179"></span><a href="#l1179"></a>
<span id="l1180">        /**</span><a href="#l1180"></a>
<span id="l1181">         * Rehashes the contents of this set into a new array with a</span><a href="#l1181"></a>
<span id="l1182">         * larger capacity.  This method is called automatically when the</span><a href="#l1182"></a>
<span id="l1183">         * number of keys in this set reaches its threshold.</span><a href="#l1183"></a>
<span id="l1184">         *</span><a href="#l1184"></a>
<span id="l1185">         * If current capacity is MAXIMUM_CAPACITY, this method does not</span><a href="#l1185"></a>
<span id="l1186">         * resize the set, but sets threshold to Integer.MAX_VALUE.</span><a href="#l1186"></a>
<span id="l1187">         * This has the effect of preventing future calls.</span><a href="#l1187"></a>
<span id="l1188">         *</span><a href="#l1188"></a>
<span id="l1189">         * @param newCapacity the new capacity, MUST be a power of two;</span><a href="#l1189"></a>
<span id="l1190">         *        must be greater than current capacity unless current</span><a href="#l1190"></a>
<span id="l1191">         *        capacity is MAXIMUM_CAPACITY (in which case value</span><a href="#l1191"></a>
<span id="l1192">         *        is irrelevant)</span><a href="#l1192"></a>
<span id="l1193">         */</span><a href="#l1193"></a>
<span id="l1194">        private void resize(int newCapacity) {</span><a href="#l1194"></a>
<span id="l1195">            Entry[] oldTable = getTable();</span><a href="#l1195"></a>
<span id="l1196">            int oldCapacity = oldTable.length;</span><a href="#l1196"></a>
<span id="l1197">            if (oldCapacity == MAXIMUM_CAPACITY) {</span><a href="#l1197"></a>
<span id="l1198">                threshold = Integer.MAX_VALUE;</span><a href="#l1198"></a>
<span id="l1199">                return;</span><a href="#l1199"></a>
<span id="l1200">            }</span><a href="#l1200"></a>
<span id="l1201"></span><a href="#l1201"></a>
<span id="l1202">            Entry[] newTable = newTable(newCapacity);</span><a href="#l1202"></a>
<span id="l1203">            transfer(oldTable, newTable);</span><a href="#l1203"></a>
<span id="l1204">            table = newTable;</span><a href="#l1204"></a>
<span id="l1205"></span><a href="#l1205"></a>
<span id="l1206">            /*</span><a href="#l1206"></a>
<span id="l1207">             * If ignoring null elements and processing ref queue caused massive</span><a href="#l1207"></a>
<span id="l1208">             * shrinkage, then restore old table.  This should be rare, but avoids</span><a href="#l1208"></a>
<span id="l1209">             * unbounded expansion of garbage-filled tables.</span><a href="#l1209"></a>
<span id="l1210">             */</span><a href="#l1210"></a>
<span id="l1211">            if (size &gt;= threshold / 2) {</span><a href="#l1211"></a>
<span id="l1212">                threshold = (int)(newCapacity * loadFactor);</span><a href="#l1212"></a>
<span id="l1213">            } else {</span><a href="#l1213"></a>
<span id="l1214">                expungeStaleEntries();</span><a href="#l1214"></a>
<span id="l1215">                transfer(newTable, oldTable);</span><a href="#l1215"></a>
<span id="l1216">                table = oldTable;</span><a href="#l1216"></a>
<span id="l1217">            }</span><a href="#l1217"></a>
<span id="l1218">        }</span><a href="#l1218"></a>
<span id="l1219"></span><a href="#l1219"></a>
<span id="l1220">        /**</span><a href="#l1220"></a>
<span id="l1221">         * Transfers all entries from src to dest tables</span><a href="#l1221"></a>
<span id="l1222">         * @param src  original table</span><a href="#l1222"></a>
<span id="l1223">         * @param dest new table</span><a href="#l1223"></a>
<span id="l1224">         */</span><a href="#l1224"></a>
<span id="l1225">        private void transfer(Entry[] src, Entry[] dest) {</span><a href="#l1225"></a>
<span id="l1226">            for (int j = 0; j &lt; src.length; ++j) {</span><a href="#l1226"></a>
<span id="l1227">                Entry e = src[j];</span><a href="#l1227"></a>
<span id="l1228">                src[j] = null;</span><a href="#l1228"></a>
<span id="l1229">                while (e != null) {</span><a href="#l1229"></a>
<span id="l1230">                    Entry next = e.next;</span><a href="#l1230"></a>
<span id="l1231">                    MethodType key = e.get();</span><a href="#l1231"></a>
<span id="l1232">                    if (key == null) {</span><a href="#l1232"></a>
<span id="l1233">                        e.next = null;  // Help GC</span><a href="#l1233"></a>
<span id="l1234">                        size--;</span><a href="#l1234"></a>
<span id="l1235">                    } else {</span><a href="#l1235"></a>
<span id="l1236">                        int i = indexFor(e.hash, dest.length);</span><a href="#l1236"></a>
<span id="l1237">                        e.next = dest[i];</span><a href="#l1237"></a>
<span id="l1238">                        dest[i] = e;</span><a href="#l1238"></a>
<span id="l1239">                    }</span><a href="#l1239"></a>
<span id="l1240">                    e = next;</span><a href="#l1240"></a>
<span id="l1241">                }</span><a href="#l1241"></a>
<span id="l1242">            }</span><a href="#l1242"></a>
<span id="l1243">        }</span><a href="#l1243"></a>
<span id="l1244"></span><a href="#l1244"></a>
<span id="l1245">        /**</span><a href="#l1245"></a>
<span id="l1246">         * The entries in this hash table extend WeakReference, using its main ref</span><a href="#l1246"></a>
<span id="l1247">         * field as the key.</span><a href="#l1247"></a>
<span id="l1248">         */</span><a href="#l1248"></a>
<span id="l1249">        private static class Entry extends WeakReference&lt;MethodType&gt; {</span><a href="#l1249"></a>
<span id="l1250">            final int hash;</span><a href="#l1250"></a>
<span id="l1251">            Entry next;</span><a href="#l1251"></a>
<span id="l1252"></span><a href="#l1252"></a>
<span id="l1253">            /**</span><a href="#l1253"></a>
<span id="l1254">             * Creates new entry.</span><a href="#l1254"></a>
<span id="l1255">             */</span><a href="#l1255"></a>
<span id="l1256">            Entry(MethodType key,</span><a href="#l1256"></a>
<span id="l1257">                  ReferenceQueue&lt;Object&gt; queue,</span><a href="#l1257"></a>
<span id="l1258">                  int hash, Entry next) {</span><a href="#l1258"></a>
<span id="l1259">                super(key, queue);</span><a href="#l1259"></a>
<span id="l1260">                this.hash  = hash;</span><a href="#l1260"></a>
<span id="l1261">                this.next  = next;</span><a href="#l1261"></a>
<span id="l1262">            }</span><a href="#l1262"></a>
<span id="l1263">        }</span><a href="#l1263"></a>
<span id="l1264">    }</span><a href="#l1264"></a>
<span id="l1265">}</span><a href="#l1265"></a></pre>
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

