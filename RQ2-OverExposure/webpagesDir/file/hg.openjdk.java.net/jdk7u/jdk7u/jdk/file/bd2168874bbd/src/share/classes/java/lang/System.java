<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: bd2168874bbd src/share/classes/java/lang/System.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/bd2168874bbd">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/bd2168874bbd">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/bd2168874bbd">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/bd2168874bbd/src/share/classes/java/lang/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/java/lang/System.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/bd2168874bbd/src/share/classes/java/lang/System.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/bd2168874bbd/src/share/classes/java/lang/System.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/bd2168874bbd/src/share/classes/java/lang/System.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/bd2168874bbd/src/share/classes/java/lang/System.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/bd2168874bbd/src/share/classes/java/lang/System.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/lang/System.java @ 8938:bd2168874bbd</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8231422: Better serial filter handling
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#118;&#100;&#97;&#118;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 23 Jan 2020 04:45:42 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/45638df14aff/src/share/classes/java/lang/System.java">45638df14aff</a> </td>
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
<span id="l2"> * Copyright (c) 1994, 2014, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l25">package java.lang;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.io.*;</span><a href="#l27"></a>
<span id="l28">import java.security.AccessControlContext;</span><a href="#l28"></a>
<span id="l29">import java.util.Properties;</span><a href="#l29"></a>
<span id="l30">import java.util.PropertyPermission;</span><a href="#l30"></a>
<span id="l31">import java.util.StringTokenizer;</span><a href="#l31"></a>
<span id="l32">import java.security.AccessController;</span><a href="#l32"></a>
<span id="l33">import java.security.PrivilegedAction;</span><a href="#l33"></a>
<span id="l34">import java.security.AllPermission;</span><a href="#l34"></a>
<span id="l35">import java.nio.channels.Channel;</span><a href="#l35"></a>
<span id="l36">import java.nio.channels.spi.SelectorProvider;</span><a href="#l36"></a>
<span id="l37">import sun.nio.ch.Interruptible;</span><a href="#l37"></a>
<span id="l38">import sun.reflect.CallerSensitive;</span><a href="#l38"></a>
<span id="l39">import sun.reflect.Reflection;</span><a href="#l39"></a>
<span id="l40">import sun.security.util.SecurityConstants;</span><a href="#l40"></a>
<span id="l41">import sun.reflect.annotation.AnnotationType;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">import jdk.internal.util.StaticProperty;</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">/**</span><a href="#l45"></a>
<span id="l46"> * The &lt;code&gt;System&lt;/code&gt; class contains several useful class fields</span><a href="#l46"></a>
<span id="l47"> * and methods. It cannot be instantiated.</span><a href="#l47"></a>
<span id="l48"> *</span><a href="#l48"></a>
<span id="l49"> * &lt;p&gt;Among the facilities provided by the &lt;code&gt;System&lt;/code&gt; class</span><a href="#l49"></a>
<span id="l50"> * are standard input, standard output, and error output streams;</span><a href="#l50"></a>
<span id="l51"> * access to externally defined properties and environment</span><a href="#l51"></a>
<span id="l52"> * variables; a means of loading files and libraries; and a utility</span><a href="#l52"></a>
<span id="l53"> * method for quickly copying a portion of an array.</span><a href="#l53"></a>
<span id="l54"> *</span><a href="#l54"></a>
<span id="l55"> * @author  unascribed</span><a href="#l55"></a>
<span id="l56"> * @since   JDK1.0</span><a href="#l56"></a>
<span id="l57"> */</span><a href="#l57"></a>
<span id="l58">public final class System {</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">    /* register the natives via the static initializer.</span><a href="#l60"></a>
<span id="l61">     *</span><a href="#l61"></a>
<span id="l62">     * VM will invoke the initializeSystemClass method to complete</span><a href="#l62"></a>
<span id="l63">     * the initialization for this class separated from clinit.</span><a href="#l63"></a>
<span id="l64">     * Note that to use properties set by the VM, see the constraints</span><a href="#l64"></a>
<span id="l65">     * described in the initializeSystemClass method.</span><a href="#l65"></a>
<span id="l66">     */</span><a href="#l66"></a>
<span id="l67">    private static native void registerNatives();</span><a href="#l67"></a>
<span id="l68">    static {</span><a href="#l68"></a>
<span id="l69">        registerNatives();</span><a href="#l69"></a>
<span id="l70">    }</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">    /** Don't let anyone instantiate this class */</span><a href="#l72"></a>
<span id="l73">    private System() {</span><a href="#l73"></a>
<span id="l74">    }</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    /**</span><a href="#l76"></a>
<span id="l77">     * The &quot;standard&quot; input stream. This stream is already</span><a href="#l77"></a>
<span id="l78">     * open and ready to supply input data. Typically this stream</span><a href="#l78"></a>
<span id="l79">     * corresponds to keyboard input or another input source specified by</span><a href="#l79"></a>
<span id="l80">     * the host environment or user.</span><a href="#l80"></a>
<span id="l81">     */</span><a href="#l81"></a>
<span id="l82">    public final static InputStream in = null;</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">    /**</span><a href="#l84"></a>
<span id="l85">     * The &quot;standard&quot; output stream. This stream is already</span><a href="#l85"></a>
<span id="l86">     * open and ready to accept output data. Typically this stream</span><a href="#l86"></a>
<span id="l87">     * corresponds to display output or another output destination</span><a href="#l87"></a>
<span id="l88">     * specified by the host environment or user.</span><a href="#l88"></a>
<span id="l89">     * &lt;p&gt;</span><a href="#l89"></a>
<span id="l90">     * For simple stand-alone Java applications, a typical way to write</span><a href="#l90"></a>
<span id="l91">     * a line of output data is:</span><a href="#l91"></a>
<span id="l92">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l92"></a>
<span id="l93">     *     System.out.println(data)</span><a href="#l93"></a>
<span id="l94">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l94"></a>
<span id="l95">     * &lt;p&gt;</span><a href="#l95"></a>
<span id="l96">     * See the &lt;code&gt;println&lt;/code&gt; methods in class &lt;code&gt;PrintStream&lt;/code&gt;.</span><a href="#l96"></a>
<span id="l97">     *</span><a href="#l97"></a>
<span id="l98">     * @see     java.io.PrintStream#println()</span><a href="#l98"></a>
<span id="l99">     * @see     java.io.PrintStream#println(boolean)</span><a href="#l99"></a>
<span id="l100">     * @see     java.io.PrintStream#println(char)</span><a href="#l100"></a>
<span id="l101">     * @see     java.io.PrintStream#println(char[])</span><a href="#l101"></a>
<span id="l102">     * @see     java.io.PrintStream#println(double)</span><a href="#l102"></a>
<span id="l103">     * @see     java.io.PrintStream#println(float)</span><a href="#l103"></a>
<span id="l104">     * @see     java.io.PrintStream#println(int)</span><a href="#l104"></a>
<span id="l105">     * @see     java.io.PrintStream#println(long)</span><a href="#l105"></a>
<span id="l106">     * @see     java.io.PrintStream#println(java.lang.Object)</span><a href="#l106"></a>
<span id="l107">     * @see     java.io.PrintStream#println(java.lang.String)</span><a href="#l107"></a>
<span id="l108">     */</span><a href="#l108"></a>
<span id="l109">    public final static PrintStream out = null;</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">    /**</span><a href="#l111"></a>
<span id="l112">     * The &quot;standard&quot; error output stream. This stream is already</span><a href="#l112"></a>
<span id="l113">     * open and ready to accept output data.</span><a href="#l113"></a>
<span id="l114">     * &lt;p&gt;</span><a href="#l114"></a>
<span id="l115">     * Typically this stream corresponds to display output or another</span><a href="#l115"></a>
<span id="l116">     * output destination specified by the host environment or user. By</span><a href="#l116"></a>
<span id="l117">     * convention, this output stream is used to display error messages</span><a href="#l117"></a>
<span id="l118">     * or other information that should come to the immediate attention</span><a href="#l118"></a>
<span id="l119">     * of a user even if the principal output stream, the value of the</span><a href="#l119"></a>
<span id="l120">     * variable &lt;code&gt;out&lt;/code&gt;, has been redirected to a file or other</span><a href="#l120"></a>
<span id="l121">     * destination that is typically not continuously monitored.</span><a href="#l121"></a>
<span id="l122">     */</span><a href="#l122"></a>
<span id="l123">    public final static PrintStream err = null;</span><a href="#l123"></a>
<span id="l124"></span><a href="#l124"></a>
<span id="l125">    /* The security manager for the system.</span><a href="#l125"></a>
<span id="l126">     */</span><a href="#l126"></a>
<span id="l127">    private static volatile SecurityManager security = null;</span><a href="#l127"></a>
<span id="l128"></span><a href="#l128"></a>
<span id="l129">    /**</span><a href="#l129"></a>
<span id="l130">     * Reassigns the &quot;standard&quot; input stream.</span><a href="#l130"></a>
<span id="l131">     *</span><a href="#l131"></a>
<span id="l132">     * &lt;p&gt;First, if there is a security manager, its &lt;code&gt;checkPermission&lt;/code&gt;</span><a href="#l132"></a>
<span id="l133">     * method is called with a &lt;code&gt;RuntimePermission(&quot;setIO&quot;)&lt;/code&gt; permission</span><a href="#l133"></a>
<span id="l134">     *  to see if it's ok to reassign the &quot;standard&quot; input stream.</span><a href="#l134"></a>
<span id="l135">     * &lt;p&gt;</span><a href="#l135"></a>
<span id="l136">     *</span><a href="#l136"></a>
<span id="l137">     * @param in the new standard input stream.</span><a href="#l137"></a>
<span id="l138">     *</span><a href="#l138"></a>
<span id="l139">     * @throws SecurityException</span><a href="#l139"></a>
<span id="l140">     *        if a security manager exists and its</span><a href="#l140"></a>
<span id="l141">     *        &lt;code&gt;checkPermission&lt;/code&gt; method doesn't allow</span><a href="#l141"></a>
<span id="l142">     *        reassigning of the standard input stream.</span><a href="#l142"></a>
<span id="l143">     *</span><a href="#l143"></a>
<span id="l144">     * @see SecurityManager#checkPermission</span><a href="#l144"></a>
<span id="l145">     * @see java.lang.RuntimePermission</span><a href="#l145"></a>
<span id="l146">     *</span><a href="#l146"></a>
<span id="l147">     * @since   JDK1.1</span><a href="#l147"></a>
<span id="l148">     */</span><a href="#l148"></a>
<span id="l149">    public static void setIn(InputStream in) {</span><a href="#l149"></a>
<span id="l150">        checkIO();</span><a href="#l150"></a>
<span id="l151">        setIn0(in);</span><a href="#l151"></a>
<span id="l152">    }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">    /**</span><a href="#l154"></a>
<span id="l155">     * Reassigns the &quot;standard&quot; output stream.</span><a href="#l155"></a>
<span id="l156">     *</span><a href="#l156"></a>
<span id="l157">     * &lt;p&gt;First, if there is a security manager, its &lt;code&gt;checkPermission&lt;/code&gt;</span><a href="#l157"></a>
<span id="l158">     * method is called with a &lt;code&gt;RuntimePermission(&quot;setIO&quot;)&lt;/code&gt; permission</span><a href="#l158"></a>
<span id="l159">     *  to see if it's ok to reassign the &quot;standard&quot; output stream.</span><a href="#l159"></a>
<span id="l160">     *</span><a href="#l160"></a>
<span id="l161">     * @param out the new standard output stream</span><a href="#l161"></a>
<span id="l162">     *</span><a href="#l162"></a>
<span id="l163">     * @throws SecurityException</span><a href="#l163"></a>
<span id="l164">     *        if a security manager exists and its</span><a href="#l164"></a>
<span id="l165">     *        &lt;code&gt;checkPermission&lt;/code&gt; method doesn't allow</span><a href="#l165"></a>
<span id="l166">     *        reassigning of the standard output stream.</span><a href="#l166"></a>
<span id="l167">     *</span><a href="#l167"></a>
<span id="l168">     * @see SecurityManager#checkPermission</span><a href="#l168"></a>
<span id="l169">     * @see java.lang.RuntimePermission</span><a href="#l169"></a>
<span id="l170">     *</span><a href="#l170"></a>
<span id="l171">     * @since   JDK1.1</span><a href="#l171"></a>
<span id="l172">     */</span><a href="#l172"></a>
<span id="l173">    public static void setOut(PrintStream out) {</span><a href="#l173"></a>
<span id="l174">        checkIO();</span><a href="#l174"></a>
<span id="l175">        setOut0(out);</span><a href="#l175"></a>
<span id="l176">    }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    /**</span><a href="#l178"></a>
<span id="l179">     * Reassigns the &quot;standard&quot; error output stream.</span><a href="#l179"></a>
<span id="l180">     *</span><a href="#l180"></a>
<span id="l181">     * &lt;p&gt;First, if there is a security manager, its &lt;code&gt;checkPermission&lt;/code&gt;</span><a href="#l181"></a>
<span id="l182">     * method is called with a &lt;code&gt;RuntimePermission(&quot;setIO&quot;)&lt;/code&gt; permission</span><a href="#l182"></a>
<span id="l183">     *  to see if it's ok to reassign the &quot;standard&quot; error output stream.</span><a href="#l183"></a>
<span id="l184">     *</span><a href="#l184"></a>
<span id="l185">     * @param err the new standard error output stream.</span><a href="#l185"></a>
<span id="l186">     *</span><a href="#l186"></a>
<span id="l187">     * @throws SecurityException</span><a href="#l187"></a>
<span id="l188">     *        if a security manager exists and its</span><a href="#l188"></a>
<span id="l189">     *        &lt;code&gt;checkPermission&lt;/code&gt; method doesn't allow</span><a href="#l189"></a>
<span id="l190">     *        reassigning of the standard error output stream.</span><a href="#l190"></a>
<span id="l191">     *</span><a href="#l191"></a>
<span id="l192">     * @see SecurityManager#checkPermission</span><a href="#l192"></a>
<span id="l193">     * @see java.lang.RuntimePermission</span><a href="#l193"></a>
<span id="l194">     *</span><a href="#l194"></a>
<span id="l195">     * @since   JDK1.1</span><a href="#l195"></a>
<span id="l196">     */</span><a href="#l196"></a>
<span id="l197">    public static void setErr(PrintStream err) {</span><a href="#l197"></a>
<span id="l198">        checkIO();</span><a href="#l198"></a>
<span id="l199">        setErr0(err);</span><a href="#l199"></a>
<span id="l200">    }</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">    private static volatile Console cons = null;</span><a href="#l202"></a>
<span id="l203">    /**</span><a href="#l203"></a>
<span id="l204">     * Returns the unique {@link java.io.Console Console} object associated</span><a href="#l204"></a>
<span id="l205">     * with the current Java virtual machine, if any.</span><a href="#l205"></a>
<span id="l206">     *</span><a href="#l206"></a>
<span id="l207">     * @return  The system console, if any, otherwise &lt;tt&gt;null&lt;/tt&gt;.</span><a href="#l207"></a>
<span id="l208">     *</span><a href="#l208"></a>
<span id="l209">     * @since   1.6</span><a href="#l209"></a>
<span id="l210">     */</span><a href="#l210"></a>
<span id="l211">     public static Console console() {</span><a href="#l211"></a>
<span id="l212">         if (cons == null) {</span><a href="#l212"></a>
<span id="l213">             synchronized (System.class) {</span><a href="#l213"></a>
<span id="l214">                 cons = sun.misc.SharedSecrets.getJavaIOAccess().console();</span><a href="#l214"></a>
<span id="l215">             }</span><a href="#l215"></a>
<span id="l216">         }</span><a href="#l216"></a>
<span id="l217">         return cons;</span><a href="#l217"></a>
<span id="l218">     }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">    /**</span><a href="#l220"></a>
<span id="l221">     * Returns the channel inherited from the entity that created this</span><a href="#l221"></a>
<span id="l222">     * Java virtual machine.</span><a href="#l222"></a>
<span id="l223">     *</span><a href="#l223"></a>
<span id="l224">     * &lt;p&gt; This method returns the channel obtained by invoking the</span><a href="#l224"></a>
<span id="l225">     * {@link java.nio.channels.spi.SelectorProvider#inheritedChannel</span><a href="#l225"></a>
<span id="l226">     * inheritedChannel} method of the system-wide default</span><a href="#l226"></a>
<span id="l227">     * {@link java.nio.channels.spi.SelectorProvider} object. &lt;/p&gt;</span><a href="#l227"></a>
<span id="l228">     *</span><a href="#l228"></a>
<span id="l229">     * &lt;p&gt; In addition to the network-oriented channels described in</span><a href="#l229"></a>
<span id="l230">     * {@link java.nio.channels.spi.SelectorProvider#inheritedChannel</span><a href="#l230"></a>
<span id="l231">     * inheritedChannel}, this method may return other kinds of</span><a href="#l231"></a>
<span id="l232">     * channels in the future.</span><a href="#l232"></a>
<span id="l233">     *</span><a href="#l233"></a>
<span id="l234">     * @return  The inherited channel, if any, otherwise &lt;tt&gt;null&lt;/tt&gt;.</span><a href="#l234"></a>
<span id="l235">     *</span><a href="#l235"></a>
<span id="l236">     * @throws  IOException</span><a href="#l236"></a>
<span id="l237">     *          If an I/O error occurs</span><a href="#l237"></a>
<span id="l238">     *</span><a href="#l238"></a>
<span id="l239">     * @throws  SecurityException</span><a href="#l239"></a>
<span id="l240">     *          If a security manager is present and it does not</span><a href="#l240"></a>
<span id="l241">     *          permit access to the channel.</span><a href="#l241"></a>
<span id="l242">     *</span><a href="#l242"></a>
<span id="l243">     * @since 1.5</span><a href="#l243"></a>
<span id="l244">     */</span><a href="#l244"></a>
<span id="l245">    public static Channel inheritedChannel() throws IOException {</span><a href="#l245"></a>
<span id="l246">        return SelectorProvider.provider().inheritedChannel();</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    private static void checkIO() {</span><a href="#l249"></a>
<span id="l250">        SecurityManager sm = getSecurityManager();</span><a href="#l250"></a>
<span id="l251">        if (sm != null) {</span><a href="#l251"></a>
<span id="l252">            sm.checkPermission(new RuntimePermission(&quot;setIO&quot;));</span><a href="#l252"></a>
<span id="l253">        }</span><a href="#l253"></a>
<span id="l254">    }</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">    private static native void setIn0(InputStream in);</span><a href="#l256"></a>
<span id="l257">    private static native void setOut0(PrintStream out);</span><a href="#l257"></a>
<span id="l258">    private static native void setErr0(PrintStream err);</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">    /**</span><a href="#l260"></a>
<span id="l261">     * Sets the System security.</span><a href="#l261"></a>
<span id="l262">     *</span><a href="#l262"></a>
<span id="l263">     * &lt;p&gt; If there is a security manager already installed, this method first</span><a href="#l263"></a>
<span id="l264">     * calls the security manager's &lt;code&gt;checkPermission&lt;/code&gt; method</span><a href="#l264"></a>
<span id="l265">     * with a &lt;code&gt;RuntimePermission(&quot;setSecurityManager&quot;)&lt;/code&gt;</span><a href="#l265"></a>
<span id="l266">     * permission to ensure it's ok to replace the existing</span><a href="#l266"></a>
<span id="l267">     * security manager.</span><a href="#l267"></a>
<span id="l268">     * This may result in throwing a &lt;code&gt;SecurityException&lt;/code&gt;.</span><a href="#l268"></a>
<span id="l269">     *</span><a href="#l269"></a>
<span id="l270">     * &lt;p&gt; Otherwise, the argument is established as the current</span><a href="#l270"></a>
<span id="l271">     * security manager. If the argument is &lt;code&gt;null&lt;/code&gt; and no</span><a href="#l271"></a>
<span id="l272">     * security manager has been established, then no action is taken and</span><a href="#l272"></a>
<span id="l273">     * the method simply returns.</span><a href="#l273"></a>
<span id="l274">     *</span><a href="#l274"></a>
<span id="l275">     * @param      s   the security manager.</span><a href="#l275"></a>
<span id="l276">     * @exception  SecurityException  if the security manager has already</span><a href="#l276"></a>
<span id="l277">     *             been set and its &lt;code&gt;checkPermission&lt;/code&gt; method</span><a href="#l277"></a>
<span id="l278">     *             doesn't allow it to be replaced.</span><a href="#l278"></a>
<span id="l279">     * @see #getSecurityManager</span><a href="#l279"></a>
<span id="l280">     * @see SecurityManager#checkPermission</span><a href="#l280"></a>
<span id="l281">     * @see java.lang.RuntimePermission</span><a href="#l281"></a>
<span id="l282">     */</span><a href="#l282"></a>
<span id="l283">    public static</span><a href="#l283"></a>
<span id="l284">    void setSecurityManager(final SecurityManager s) {</span><a href="#l284"></a>
<span id="l285">        try {</span><a href="#l285"></a>
<span id="l286">            s.checkPackageAccess(&quot;java.lang&quot;);</span><a href="#l286"></a>
<span id="l287">        } catch (Exception e) {</span><a href="#l287"></a>
<span id="l288">            // no-op</span><a href="#l288"></a>
<span id="l289">        }</span><a href="#l289"></a>
<span id="l290">        setSecurityManager0(s);</span><a href="#l290"></a>
<span id="l291">    }</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">    private static synchronized</span><a href="#l293"></a>
<span id="l294">    void setSecurityManager0(final SecurityManager s) {</span><a href="#l294"></a>
<span id="l295">        SecurityManager sm = getSecurityManager();</span><a href="#l295"></a>
<span id="l296">        if (sm != null) {</span><a href="#l296"></a>
<span id="l297">            // ask the currently installed security manager if we</span><a href="#l297"></a>
<span id="l298">            // can replace it.</span><a href="#l298"></a>
<span id="l299">            sm.checkPermission(new RuntimePermission</span><a href="#l299"></a>
<span id="l300">                                     (&quot;setSecurityManager&quot;));</span><a href="#l300"></a>
<span id="l301">        }</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        if ((s != null) &amp;&amp; (s.getClass().getClassLoader() != null)) {</span><a href="#l303"></a>
<span id="l304">            // New security manager class is not on bootstrap classpath.</span><a href="#l304"></a>
<span id="l305">            // Cause policy to get initialized before we install the new</span><a href="#l305"></a>
<span id="l306">            // security manager, in order to prevent infinite loops when</span><a href="#l306"></a>
<span id="l307">            // trying to initialize the policy (which usually involves</span><a href="#l307"></a>
<span id="l308">            // accessing some security and/or system properties, which in turn</span><a href="#l308"></a>
<span id="l309">            // calls the installed security manager's checkPermission method</span><a href="#l309"></a>
<span id="l310">            // which will loop infinitely if there is a non-system class</span><a href="#l310"></a>
<span id="l311">            // (in this case: the new security manager class) on the stack).</span><a href="#l311"></a>
<span id="l312">            AccessController.doPrivileged(new PrivilegedAction&lt;Object&gt;() {</span><a href="#l312"></a>
<span id="l313">                public Object run() {</span><a href="#l313"></a>
<span id="l314">                    s.getClass().getProtectionDomain().implies</span><a href="#l314"></a>
<span id="l315">                        (SecurityConstants.ALL_PERMISSION);</span><a href="#l315"></a>
<span id="l316">                    return null;</span><a href="#l316"></a>
<span id="l317">                }</span><a href="#l317"></a>
<span id="l318">            });</span><a href="#l318"></a>
<span id="l319">        }</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">        security = s;</span><a href="#l321"></a>
<span id="l322">    }</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">    /**</span><a href="#l324"></a>
<span id="l325">     * Gets the system security interface.</span><a href="#l325"></a>
<span id="l326">     *</span><a href="#l326"></a>
<span id="l327">     * @return  if a security manager has already been established for the</span><a href="#l327"></a>
<span id="l328">     *          current application, then that security manager is returned;</span><a href="#l328"></a>
<span id="l329">     *          otherwise, &lt;code&gt;null&lt;/code&gt; is returned.</span><a href="#l329"></a>
<span id="l330">     * @see     #setSecurityManager</span><a href="#l330"></a>
<span id="l331">     */</span><a href="#l331"></a>
<span id="l332">    public static SecurityManager getSecurityManager() {</span><a href="#l332"></a>
<span id="l333">        return security;</span><a href="#l333"></a>
<span id="l334">    }</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">    /**</span><a href="#l336"></a>
<span id="l337">     * Returns the current time in milliseconds.  Note that</span><a href="#l337"></a>
<span id="l338">     * while the unit of time of the return value is a millisecond,</span><a href="#l338"></a>
<span id="l339">     * the granularity of the value depends on the underlying</span><a href="#l339"></a>
<span id="l340">     * operating system and may be larger.  For example, many</span><a href="#l340"></a>
<span id="l341">     * operating systems measure time in units of tens of</span><a href="#l341"></a>
<span id="l342">     * milliseconds.</span><a href="#l342"></a>
<span id="l343">     *</span><a href="#l343"></a>
<span id="l344">     * &lt;p&gt; See the description of the class &lt;code&gt;Date&lt;/code&gt; for</span><a href="#l344"></a>
<span id="l345">     * a discussion of slight discrepancies that may arise between</span><a href="#l345"></a>
<span id="l346">     * &quot;computer time&quot; and coordinated universal time (UTC).</span><a href="#l346"></a>
<span id="l347">     *</span><a href="#l347"></a>
<span id="l348">     * @return  the difference, measured in milliseconds, between</span><a href="#l348"></a>
<span id="l349">     *          the current time and midnight, January 1, 1970 UTC.</span><a href="#l349"></a>
<span id="l350">     * @see     java.util.Date</span><a href="#l350"></a>
<span id="l351">     */</span><a href="#l351"></a>
<span id="l352">    public static native long currentTimeMillis();</span><a href="#l352"></a>
<span id="l353"></span><a href="#l353"></a>
<span id="l354">    /**</span><a href="#l354"></a>
<span id="l355">     * Returns the current value of the running Java Virtual Machine's</span><a href="#l355"></a>
<span id="l356">     * high-resolution time source, in nanoseconds.</span><a href="#l356"></a>
<span id="l357">     *</span><a href="#l357"></a>
<span id="l358">     * &lt;p&gt;This method can only be used to measure elapsed time and is</span><a href="#l358"></a>
<span id="l359">     * not related to any other notion of system or wall-clock time.</span><a href="#l359"></a>
<span id="l360">     * The value returned represents nanoseconds since some fixed but</span><a href="#l360"></a>
<span id="l361">     * arbitrary &lt;i&gt;origin&lt;/i&gt; time (perhaps in the future, so values</span><a href="#l361"></a>
<span id="l362">     * may be negative).  The same origin is used by all invocations of</span><a href="#l362"></a>
<span id="l363">     * this method in an instance of a Java virtual machine; other</span><a href="#l363"></a>
<span id="l364">     * virtual machine instances are likely to use a different origin.</span><a href="#l364"></a>
<span id="l365">     *</span><a href="#l365"></a>
<span id="l366">     * &lt;p&gt;This method provides nanosecond precision, but not necessarily</span><a href="#l366"></a>
<span id="l367">     * nanosecond resolution (that is, how frequently the value changes)</span><a href="#l367"></a>
<span id="l368">     * - no guarantees are made except that the resolution is at least as</span><a href="#l368"></a>
<span id="l369">     * good as that of {@link #currentTimeMillis()}.</span><a href="#l369"></a>
<span id="l370">     *</span><a href="#l370"></a>
<span id="l371">     * &lt;p&gt;Differences in successive calls that span greater than</span><a href="#l371"></a>
<span id="l372">     * approximately 292 years (2&lt;sup&gt;63&lt;/sup&gt; nanoseconds) will not</span><a href="#l372"></a>
<span id="l373">     * correctly compute elapsed time due to numerical overflow.</span><a href="#l373"></a>
<span id="l374">     *</span><a href="#l374"></a>
<span id="l375">     * &lt;p&gt;The values returned by this method become meaningful only when</span><a href="#l375"></a>
<span id="l376">     * the difference between two such values, obtained within the same</span><a href="#l376"></a>
<span id="l377">     * instance of a Java virtual machine, is computed.</span><a href="#l377"></a>
<span id="l378">     *</span><a href="#l378"></a>
<span id="l379">     * &lt;p&gt; For example, to measure how long some code takes to execute:</span><a href="#l379"></a>
<span id="l380">     *  &lt;pre&gt; {@code</span><a href="#l380"></a>
<span id="l381">     * long startTime = System.nanoTime();</span><a href="#l381"></a>
<span id="l382">     * // ... the code being measured ...</span><a href="#l382"></a>
<span id="l383">     * long estimatedTime = System.nanoTime() - startTime;}&lt;/pre&gt;</span><a href="#l383"></a>
<span id="l384">     *</span><a href="#l384"></a>
<span id="l385">     * &lt;p&gt;To compare two nanoTime values</span><a href="#l385"></a>
<span id="l386">     *  &lt;pre&gt; {@code</span><a href="#l386"></a>
<span id="l387">     * long t0 = System.nanoTime();</span><a href="#l387"></a>
<span id="l388">     * ...</span><a href="#l388"></a>
<span id="l389">     * long t1 = System.nanoTime();}&lt;/pre&gt;</span><a href="#l389"></a>
<span id="l390">     *</span><a href="#l390"></a>
<span id="l391">     * one should use {@code t1 - t0 &lt; 0}, not {@code t1 &lt; t0},</span><a href="#l391"></a>
<span id="l392">     * because of the possibility of numerical overflow.</span><a href="#l392"></a>
<span id="l393">     *</span><a href="#l393"></a>
<span id="l394">     * @return the current value of the running Java Virtual Machine's</span><a href="#l394"></a>
<span id="l395">     *         high-resolution time source, in nanoseconds</span><a href="#l395"></a>
<span id="l396">     * @since 1.5</span><a href="#l396"></a>
<span id="l397">     */</span><a href="#l397"></a>
<span id="l398">    public static native long nanoTime();</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">    /**</span><a href="#l400"></a>
<span id="l401">     * Copies an array from the specified source array, beginning at the</span><a href="#l401"></a>
<span id="l402">     * specified position, to the specified position of the destination array.</span><a href="#l402"></a>
<span id="l403">     * A subsequence of array components are copied from the source</span><a href="#l403"></a>
<span id="l404">     * array referenced by &lt;code&gt;src&lt;/code&gt; to the destination array</span><a href="#l404"></a>
<span id="l405">     * referenced by &lt;code&gt;dest&lt;/code&gt;. The number of components copied is</span><a href="#l405"></a>
<span id="l406">     * equal to the &lt;code&gt;length&lt;/code&gt; argument. The components at</span><a href="#l406"></a>
<span id="l407">     * positions &lt;code&gt;srcPos&lt;/code&gt; through</span><a href="#l407"></a>
<span id="l408">     * &lt;code&gt;srcPos+length-1&lt;/code&gt; in the source array are copied into</span><a href="#l408"></a>
<span id="l409">     * positions &lt;code&gt;destPos&lt;/code&gt; through</span><a href="#l409"></a>
<span id="l410">     * &lt;code&gt;destPos+length-1&lt;/code&gt;, respectively, of the destination</span><a href="#l410"></a>
<span id="l411">     * array.</span><a href="#l411"></a>
<span id="l412">     * &lt;p&gt;</span><a href="#l412"></a>
<span id="l413">     * If the &lt;code&gt;src&lt;/code&gt; and &lt;code&gt;dest&lt;/code&gt; arguments refer to the</span><a href="#l413"></a>
<span id="l414">     * same array object, then the copying is performed as if the</span><a href="#l414"></a>
<span id="l415">     * components at positions &lt;code&gt;srcPos&lt;/code&gt; through</span><a href="#l415"></a>
<span id="l416">     * &lt;code&gt;srcPos+length-1&lt;/code&gt; were first copied to a temporary</span><a href="#l416"></a>
<span id="l417">     * array with &lt;code&gt;length&lt;/code&gt; components and then the contents of</span><a href="#l417"></a>
<span id="l418">     * the temporary array were copied into positions</span><a href="#l418"></a>
<span id="l419">     * &lt;code&gt;destPos&lt;/code&gt; through &lt;code&gt;destPos+length-1&lt;/code&gt; of the</span><a href="#l419"></a>
<span id="l420">     * destination array.</span><a href="#l420"></a>
<span id="l421">     * &lt;p&gt;</span><a href="#l421"></a>
<span id="l422">     * If &lt;code&gt;dest&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;, then a</span><a href="#l422"></a>
<span id="l423">     * &lt;code&gt;NullPointerException&lt;/code&gt; is thrown.</span><a href="#l423"></a>
<span id="l424">     * &lt;p&gt;</span><a href="#l424"></a>
<span id="l425">     * If &lt;code&gt;src&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;, then a</span><a href="#l425"></a>
<span id="l426">     * &lt;code&gt;NullPointerException&lt;/code&gt; is thrown and the destination</span><a href="#l426"></a>
<span id="l427">     * array is not modified.</span><a href="#l427"></a>
<span id="l428">     * &lt;p&gt;</span><a href="#l428"></a>
<span id="l429">     * Otherwise, if any of the following is true, an</span><a href="#l429"></a>
<span id="l430">     * &lt;code&gt;ArrayStoreException&lt;/code&gt; is thrown and the destination is</span><a href="#l430"></a>
<span id="l431">     * not modified:</span><a href="#l431"></a>
<span id="l432">     * &lt;ul&gt;</span><a href="#l432"></a>
<span id="l433">     * &lt;li&gt;The &lt;code&gt;src&lt;/code&gt; argument refers to an object that is not an</span><a href="#l433"></a>
<span id="l434">     *     array.</span><a href="#l434"></a>
<span id="l435">     * &lt;li&gt;The &lt;code&gt;dest&lt;/code&gt; argument refers to an object that is not an</span><a href="#l435"></a>
<span id="l436">     *     array.</span><a href="#l436"></a>
<span id="l437">     * &lt;li&gt;The &lt;code&gt;src&lt;/code&gt; argument and &lt;code&gt;dest&lt;/code&gt; argument refer</span><a href="#l437"></a>
<span id="l438">     *     to arrays whose component types are different primitive types.</span><a href="#l438"></a>
<span id="l439">     * &lt;li&gt;The &lt;code&gt;src&lt;/code&gt; argument refers to an array with a primitive</span><a href="#l439"></a>
<span id="l440">     *    component type and the &lt;code&gt;dest&lt;/code&gt; argument refers to an array</span><a href="#l440"></a>
<span id="l441">     *     with a reference component type.</span><a href="#l441"></a>
<span id="l442">     * &lt;li&gt;The &lt;code&gt;src&lt;/code&gt; argument refers to an array with a reference</span><a href="#l442"></a>
<span id="l443">     *    component type and the &lt;code&gt;dest&lt;/code&gt; argument refers to an array</span><a href="#l443"></a>
<span id="l444">     *     with a primitive component type.</span><a href="#l444"></a>
<span id="l445">     * &lt;/ul&gt;</span><a href="#l445"></a>
<span id="l446">     * &lt;p&gt;</span><a href="#l446"></a>
<span id="l447">     * Otherwise, if any of the following is true, an</span><a href="#l447"></a>
<span id="l448">     * &lt;code&gt;IndexOutOfBoundsException&lt;/code&gt; is</span><a href="#l448"></a>
<span id="l449">     * thrown and the destination is not modified:</span><a href="#l449"></a>
<span id="l450">     * &lt;ul&gt;</span><a href="#l450"></a>
<span id="l451">     * &lt;li&gt;The &lt;code&gt;srcPos&lt;/code&gt; argument is negative.</span><a href="#l451"></a>
<span id="l452">     * &lt;li&gt;The &lt;code&gt;destPos&lt;/code&gt; argument is negative.</span><a href="#l452"></a>
<span id="l453">     * &lt;li&gt;The &lt;code&gt;length&lt;/code&gt; argument is negative.</span><a href="#l453"></a>
<span id="l454">     * &lt;li&gt;&lt;code&gt;srcPos+length&lt;/code&gt; is greater than</span><a href="#l454"></a>
<span id="l455">     *     &lt;code&gt;src.length&lt;/code&gt;, the length of the source array.</span><a href="#l455"></a>
<span id="l456">     * &lt;li&gt;&lt;code&gt;destPos+length&lt;/code&gt; is greater than</span><a href="#l456"></a>
<span id="l457">     *     &lt;code&gt;dest.length&lt;/code&gt;, the length of the destination array.</span><a href="#l457"></a>
<span id="l458">     * &lt;/ul&gt;</span><a href="#l458"></a>
<span id="l459">     * &lt;p&gt;</span><a href="#l459"></a>
<span id="l460">     * Otherwise, if any actual component of the source array from</span><a href="#l460"></a>
<span id="l461">     * position &lt;code&gt;srcPos&lt;/code&gt; through</span><a href="#l461"></a>
<span id="l462">     * &lt;code&gt;srcPos+length-1&lt;/code&gt; cannot be converted to the component</span><a href="#l462"></a>
<span id="l463">     * type of the destination array by assignment conversion, an</span><a href="#l463"></a>
<span id="l464">     * &lt;code&gt;ArrayStoreException&lt;/code&gt; is thrown. In this case, let</span><a href="#l464"></a>
<span id="l465">     * &lt;b&gt;&lt;i&gt;k&lt;/i&gt;&lt;/b&gt; be the smallest nonnegative integer less than</span><a href="#l465"></a>
<span id="l466">     * length such that &lt;code&gt;src[srcPos+&lt;/code&gt;&lt;i&gt;k&lt;/i&gt;&lt;code&gt;]&lt;/code&gt;</span><a href="#l466"></a>
<span id="l467">     * cannot be converted to the component type of the destination</span><a href="#l467"></a>
<span id="l468">     * array; when the exception is thrown, source array components from</span><a href="#l468"></a>
<span id="l469">     * positions &lt;code&gt;srcPos&lt;/code&gt; through</span><a href="#l469"></a>
<span id="l470">     * &lt;code&gt;srcPos+&lt;/code&gt;&lt;i&gt;k&lt;/i&gt;&lt;code&gt;-1&lt;/code&gt;</span><a href="#l470"></a>
<span id="l471">     * will already have been copied to destination array positions</span><a href="#l471"></a>
<span id="l472">     * &lt;code&gt;destPos&lt;/code&gt; through</span><a href="#l472"></a>
<span id="l473">     * &lt;code&gt;destPos+&lt;/code&gt;&lt;i&gt;k&lt;/I&gt;&lt;code&gt;-1&lt;/code&gt; and no other</span><a href="#l473"></a>
<span id="l474">     * positions of the destination array will have been modified.</span><a href="#l474"></a>
<span id="l475">     * (Because of the restrictions already itemized, this</span><a href="#l475"></a>
<span id="l476">     * paragraph effectively applies only to the situation where both</span><a href="#l476"></a>
<span id="l477">     * arrays have component types that are reference types.)</span><a href="#l477"></a>
<span id="l478">     *</span><a href="#l478"></a>
<span id="l479">     * @param      src      the source array.</span><a href="#l479"></a>
<span id="l480">     * @param      srcPos   starting position in the source array.</span><a href="#l480"></a>
<span id="l481">     * @param      dest     the destination array.</span><a href="#l481"></a>
<span id="l482">     * @param      destPos  starting position in the destination data.</span><a href="#l482"></a>
<span id="l483">     * @param      length   the number of array elements to be copied.</span><a href="#l483"></a>
<span id="l484">     * @exception  IndexOutOfBoundsException  if copying would cause</span><a href="#l484"></a>
<span id="l485">     *               access of data outside array bounds.</span><a href="#l485"></a>
<span id="l486">     * @exception  ArrayStoreException  if an element in the &lt;code&gt;src&lt;/code&gt;</span><a href="#l486"></a>
<span id="l487">     *               array could not be stored into the &lt;code&gt;dest&lt;/code&gt; array</span><a href="#l487"></a>
<span id="l488">     *               because of a type mismatch.</span><a href="#l488"></a>
<span id="l489">     * @exception  NullPointerException if either &lt;code&gt;src&lt;/code&gt; or</span><a href="#l489"></a>
<span id="l490">     *               &lt;code&gt;dest&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;.</span><a href="#l490"></a>
<span id="l491">     */</span><a href="#l491"></a>
<span id="l492">    public static native void arraycopy(Object src,  int  srcPos,</span><a href="#l492"></a>
<span id="l493">                                        Object dest, int destPos,</span><a href="#l493"></a>
<span id="l494">                                        int length);</span><a href="#l494"></a>
<span id="l495"></span><a href="#l495"></a>
<span id="l496">    /**</span><a href="#l496"></a>
<span id="l497">     * Returns the same hash code for the given object as</span><a href="#l497"></a>
<span id="l498">     * would be returned by the default method hashCode(),</span><a href="#l498"></a>
<span id="l499">     * whether or not the given object's class overrides</span><a href="#l499"></a>
<span id="l500">     * hashCode().</span><a href="#l500"></a>
<span id="l501">     * The hash code for the null reference is zero.</span><a href="#l501"></a>
<span id="l502">     *</span><a href="#l502"></a>
<span id="l503">     * @param x object for which the hashCode is to be calculated</span><a href="#l503"></a>
<span id="l504">     * @return  the hashCode</span><a href="#l504"></a>
<span id="l505">     * @since   JDK1.1</span><a href="#l505"></a>
<span id="l506">     */</span><a href="#l506"></a>
<span id="l507">    public static native int identityHashCode(Object x);</span><a href="#l507"></a>
<span id="l508"></span><a href="#l508"></a>
<span id="l509">    /**</span><a href="#l509"></a>
<span id="l510">     * System properties. The following properties are guaranteed to be defined:</span><a href="#l510"></a>
<span id="l511">     * &lt;dl&gt;</span><a href="#l511"></a>
<span id="l512">     * &lt;dt&gt;java.version         &lt;dd&gt;Java version number</span><a href="#l512"></a>
<span id="l513">     * &lt;dt&gt;java.vendor          &lt;dd&gt;Java vendor specific string</span><a href="#l513"></a>
<span id="l514">     * &lt;dt&gt;java.vendor.url      &lt;dd&gt;Java vendor URL</span><a href="#l514"></a>
<span id="l515">     * &lt;dt&gt;java.home            &lt;dd&gt;Java installation directory</span><a href="#l515"></a>
<span id="l516">     * &lt;dt&gt;java.class.version   &lt;dd&gt;Java class version number</span><a href="#l516"></a>
<span id="l517">     * &lt;dt&gt;java.class.path      &lt;dd&gt;Java classpath</span><a href="#l517"></a>
<span id="l518">     * &lt;dt&gt;os.name              &lt;dd&gt;Operating System Name</span><a href="#l518"></a>
<span id="l519">     * &lt;dt&gt;os.arch              &lt;dd&gt;Operating System Architecture</span><a href="#l519"></a>
<span id="l520">     * &lt;dt&gt;os.version           &lt;dd&gt;Operating System Version</span><a href="#l520"></a>
<span id="l521">     * &lt;dt&gt;file.separator       &lt;dd&gt;File separator (&quot;/&quot; on Unix)</span><a href="#l521"></a>
<span id="l522">     * &lt;dt&gt;path.separator       &lt;dd&gt;Path separator (&quot;:&quot; on Unix)</span><a href="#l522"></a>
<span id="l523">     * &lt;dt&gt;line.separator       &lt;dd&gt;Line separator (&quot;\n&quot; on Unix)</span><a href="#l523"></a>
<span id="l524">     * &lt;dt&gt;user.name            &lt;dd&gt;User account name</span><a href="#l524"></a>
<span id="l525">     * &lt;dt&gt;user.home            &lt;dd&gt;User home directory</span><a href="#l525"></a>
<span id="l526">     * &lt;dt&gt;user.dir             &lt;dd&gt;User's current working directory</span><a href="#l526"></a>
<span id="l527">     * &lt;/dl&gt;</span><a href="#l527"></a>
<span id="l528">     */</span><a href="#l528"></a>
<span id="l529"></span><a href="#l529"></a>
<span id="l530">    private static Properties props;</span><a href="#l530"></a>
<span id="l531">    private static native Properties initProperties(Properties props);</span><a href="#l531"></a>
<span id="l532"></span><a href="#l532"></a>
<span id="l533">    /**</span><a href="#l533"></a>
<span id="l534">     * Determines the current system properties.</span><a href="#l534"></a>
<span id="l535">     * &lt;p&gt;</span><a href="#l535"></a>
<span id="l536">     * First, if there is a security manager, its</span><a href="#l536"></a>
<span id="l537">     * &lt;code&gt;checkPropertiesAccess&lt;/code&gt; method is called with no</span><a href="#l537"></a>
<span id="l538">     * arguments. This may result in a security exception.</span><a href="#l538"></a>
<span id="l539">     * &lt;p&gt;</span><a href="#l539"></a>
<span id="l540">     * The current set of system properties for use by the</span><a href="#l540"></a>
<span id="l541">     * {@link #getProperty(String)} method is returned as a</span><a href="#l541"></a>
<span id="l542">     * &lt;code&gt;Properties&lt;/code&gt; object. If there is no current set of</span><a href="#l542"></a>
<span id="l543">     * system properties, a set of system properties is first created and</span><a href="#l543"></a>
<span id="l544">     * initialized. This set of system properties always includes values</span><a href="#l544"></a>
<span id="l545">     * for the following keys:</span><a href="#l545"></a>
<span id="l546">     * &lt;table summary=&quot;Shows property keys and associated values&quot;&gt;</span><a href="#l546"></a>
<span id="l547">     * &lt;tr&gt;&lt;th&gt;Key&lt;/th&gt;</span><a href="#l547"></a>
<span id="l548">     *     &lt;th&gt;Description of Associated Value&lt;/th&gt;&lt;/tr&gt;</span><a href="#l548"></a>
<span id="l549">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l549"></a>
<span id="l550">     *     &lt;td&gt;Java Runtime Environment version&lt;/td&gt;&lt;/tr&gt;</span><a href="#l550"></a>
<span id="l551">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vendor&lt;/code&gt;&lt;/td&gt;</span><a href="#l551"></a>
<span id="l552">     *     &lt;td&gt;Java Runtime Environment vendor&lt;/td&gt;&lt;/tr</span><a href="#l552"></a>
<span id="l553">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vendor.url&lt;/code&gt;&lt;/td&gt;</span><a href="#l553"></a>
<span id="l554">     *     &lt;td&gt;Java vendor URL&lt;/td&gt;&lt;/tr&gt;</span><a href="#l554"></a>
<span id="l555">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.home&lt;/code&gt;&lt;/td&gt;</span><a href="#l555"></a>
<span id="l556">     *     &lt;td&gt;Java installation directory&lt;/td&gt;&lt;/tr&gt;</span><a href="#l556"></a>
<span id="l557">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.specification.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l557"></a>
<span id="l558">     *     &lt;td&gt;Java Virtual Machine specification version&lt;/td&gt;&lt;/tr&gt;</span><a href="#l558"></a>
<span id="l559">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.specification.vendor&lt;/code&gt;&lt;/td&gt;</span><a href="#l559"></a>
<span id="l560">     *     &lt;td&gt;Java Virtual Machine specification vendor&lt;/td&gt;&lt;/tr&gt;</span><a href="#l560"></a>
<span id="l561">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.specification.name&lt;/code&gt;&lt;/td&gt;</span><a href="#l561"></a>
<span id="l562">     *     &lt;td&gt;Java Virtual Machine specification name&lt;/td&gt;&lt;/tr&gt;</span><a href="#l562"></a>
<span id="l563">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l563"></a>
<span id="l564">     *     &lt;td&gt;Java Virtual Machine implementation version&lt;/td&gt;&lt;/tr&gt;</span><a href="#l564"></a>
<span id="l565">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.vendor&lt;/code&gt;&lt;/td&gt;</span><a href="#l565"></a>
<span id="l566">     *     &lt;td&gt;Java Virtual Machine implementation vendor&lt;/td&gt;&lt;/tr&gt;</span><a href="#l566"></a>
<span id="l567">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.vm.name&lt;/code&gt;&lt;/td&gt;</span><a href="#l567"></a>
<span id="l568">     *     &lt;td&gt;Java Virtual Machine implementation name&lt;/td&gt;&lt;/tr&gt;</span><a href="#l568"></a>
<span id="l569">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.specification.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l569"></a>
<span id="l570">     *     &lt;td&gt;Java Runtime Environment specification  version&lt;/td&gt;&lt;/tr&gt;</span><a href="#l570"></a>
<span id="l571">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.specification.vendor&lt;/code&gt;&lt;/td&gt;</span><a href="#l571"></a>
<span id="l572">     *     &lt;td&gt;Java Runtime Environment specification  vendor&lt;/td&gt;&lt;/tr&gt;</span><a href="#l572"></a>
<span id="l573">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.specification.name&lt;/code&gt;&lt;/td&gt;</span><a href="#l573"></a>
<span id="l574">     *     &lt;td&gt;Java Runtime Environment specification  name&lt;/td&gt;&lt;/tr&gt;</span><a href="#l574"></a>
<span id="l575">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.class.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l575"></a>
<span id="l576">     *     &lt;td&gt;Java class format version number&lt;/td&gt;&lt;/tr&gt;</span><a href="#l576"></a>
<span id="l577">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.class.path&lt;/code&gt;&lt;/td&gt;</span><a href="#l577"></a>
<span id="l578">     *     &lt;td&gt;Java class path&lt;/td&gt;&lt;/tr&gt;</span><a href="#l578"></a>
<span id="l579">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.library.path&lt;/code&gt;&lt;/td&gt;</span><a href="#l579"></a>
<span id="l580">     *     &lt;td&gt;List of paths to search when loading libraries&lt;/td&gt;&lt;/tr&gt;</span><a href="#l580"></a>
<span id="l581">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.io.tmpdir&lt;/code&gt;&lt;/td&gt;</span><a href="#l581"></a>
<span id="l582">     *     &lt;td&gt;Default temp file path&lt;/td&gt;&lt;/tr&gt;</span><a href="#l582"></a>
<span id="l583">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.compiler&lt;/code&gt;&lt;/td&gt;</span><a href="#l583"></a>
<span id="l584">     *     &lt;td&gt;Name of JIT compiler to use&lt;/td&gt;&lt;/tr&gt;</span><a href="#l584"></a>
<span id="l585">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;java.ext.dirs&lt;/code&gt;&lt;/td&gt;</span><a href="#l585"></a>
<span id="l586">     *     &lt;td&gt;Path of extension directory or directories&lt;/td&gt;&lt;/tr&gt;</span><a href="#l586"></a>
<span id="l587">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;os.name&lt;/code&gt;&lt;/td&gt;</span><a href="#l587"></a>
<span id="l588">     *     &lt;td&gt;Operating system name&lt;/td&gt;&lt;/tr&gt;</span><a href="#l588"></a>
<span id="l589">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;os.arch&lt;/code&gt;&lt;/td&gt;</span><a href="#l589"></a>
<span id="l590">     *     &lt;td&gt;Operating system architecture&lt;/td&gt;&lt;/tr&gt;</span><a href="#l590"></a>
<span id="l591">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;os.version&lt;/code&gt;&lt;/td&gt;</span><a href="#l591"></a>
<span id="l592">     *     &lt;td&gt;Operating system version&lt;/td&gt;&lt;/tr&gt;</span><a href="#l592"></a>
<span id="l593">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;file.separator&lt;/code&gt;&lt;/td&gt;</span><a href="#l593"></a>
<span id="l594">     *     &lt;td&gt;File separator (&quot;/&quot; on UNIX)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l594"></a>
<span id="l595">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;path.separator&lt;/code&gt;&lt;/td&gt;</span><a href="#l595"></a>
<span id="l596">     *     &lt;td&gt;Path separator (&quot;:&quot; on UNIX)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l596"></a>
<span id="l597">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;line.separator&lt;/code&gt;&lt;/td&gt;</span><a href="#l597"></a>
<span id="l598">     *     &lt;td&gt;Line separator (&quot;\n&quot; on UNIX)&lt;/td&gt;&lt;/tr&gt;</span><a href="#l598"></a>
<span id="l599">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;user.name&lt;/code&gt;&lt;/td&gt;</span><a href="#l599"></a>
<span id="l600">     *     &lt;td&gt;User's account name&lt;/td&gt;&lt;/tr&gt;</span><a href="#l600"></a>
<span id="l601">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;user.home&lt;/code&gt;&lt;/td&gt;</span><a href="#l601"></a>
<span id="l602">     *     &lt;td&gt;User's home directory&lt;/td&gt;&lt;/tr&gt;</span><a href="#l602"></a>
<span id="l603">     * &lt;tr&gt;&lt;td&gt;&lt;code&gt;user.dir&lt;/code&gt;&lt;/td&gt;</span><a href="#l603"></a>
<span id="l604">     *     &lt;td&gt;User's current working directory&lt;/td&gt;&lt;/tr&gt;</span><a href="#l604"></a>
<span id="l605">     * &lt;/table&gt;</span><a href="#l605"></a>
<span id="l606">     * &lt;p&gt;</span><a href="#l606"></a>
<span id="l607">     * Multiple paths in a system property value are separated by the path</span><a href="#l607"></a>
<span id="l608">     * separator character of the platform.</span><a href="#l608"></a>
<span id="l609">     * &lt;p&gt;</span><a href="#l609"></a>
<span id="l610">     * Note that even if the security manager does not permit the</span><a href="#l610"></a>
<span id="l611">     * &lt;code&gt;getProperties&lt;/code&gt; operation, it may choose to permit the</span><a href="#l611"></a>
<span id="l612">     * {@link #getProperty(String)} operation.</span><a href="#l612"></a>
<span id="l613">     *</span><a href="#l613"></a>
<span id="l614">     * @return     the system properties</span><a href="#l614"></a>
<span id="l615">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l615"></a>
<span id="l616">     *             &lt;code&gt;checkPropertiesAccess&lt;/code&gt; method doesn't allow access</span><a href="#l616"></a>
<span id="l617">     *              to the system properties.</span><a href="#l617"></a>
<span id="l618">     * @see        #setProperties</span><a href="#l618"></a>
<span id="l619">     * @see        java.lang.SecurityException</span><a href="#l619"></a>
<span id="l620">     * @see        java.lang.SecurityManager#checkPropertiesAccess()</span><a href="#l620"></a>
<span id="l621">     * @see        java.util.Properties</span><a href="#l621"></a>
<span id="l622">     */</span><a href="#l622"></a>
<span id="l623">    public static Properties getProperties() {</span><a href="#l623"></a>
<span id="l624">        SecurityManager sm = getSecurityManager();</span><a href="#l624"></a>
<span id="l625">        if (sm != null) {</span><a href="#l625"></a>
<span id="l626">            sm.checkPropertiesAccess();</span><a href="#l626"></a>
<span id="l627">        }</span><a href="#l627"></a>
<span id="l628"></span><a href="#l628"></a>
<span id="l629">        return props;</span><a href="#l629"></a>
<span id="l630">    }</span><a href="#l630"></a>
<span id="l631"></span><a href="#l631"></a>
<span id="l632">    /**</span><a href="#l632"></a>
<span id="l633">     * Returns the system-dependent line separator string.  It always</span><a href="#l633"></a>
<span id="l634">     * returns the same value - the initial value of the {@linkplain</span><a href="#l634"></a>
<span id="l635">     * #getProperty(String) system property} {@code line.separator}.</span><a href="#l635"></a>
<span id="l636">     *</span><a href="#l636"></a>
<span id="l637">     * &lt;p&gt;On UNIX systems, it returns {@code &quot;\n&quot;}; on Microsoft</span><a href="#l637"></a>
<span id="l638">     * Windows systems it returns {@code &quot;\r\n&quot;}.</span><a href="#l638"></a>
<span id="l639">     */</span><a href="#l639"></a>
<span id="l640">    public static String lineSeparator() {</span><a href="#l640"></a>
<span id="l641">        return lineSeparator;</span><a href="#l641"></a>
<span id="l642">    }</span><a href="#l642"></a>
<span id="l643"></span><a href="#l643"></a>
<span id="l644">    private static String lineSeparator;</span><a href="#l644"></a>
<span id="l645"></span><a href="#l645"></a>
<span id="l646">    /**</span><a href="#l646"></a>
<span id="l647">     * Sets the system properties to the &lt;code&gt;Properties&lt;/code&gt;</span><a href="#l647"></a>
<span id="l648">     * argument.</span><a href="#l648"></a>
<span id="l649">     * &lt;p&gt;</span><a href="#l649"></a>
<span id="l650">     * First, if there is a security manager, its</span><a href="#l650"></a>
<span id="l651">     * &lt;code&gt;checkPropertiesAccess&lt;/code&gt; method is called with no</span><a href="#l651"></a>
<span id="l652">     * arguments. This may result in a security exception.</span><a href="#l652"></a>
<span id="l653">     * &lt;p&gt;</span><a href="#l653"></a>
<span id="l654">     * The argument becomes the current set of system properties for use</span><a href="#l654"></a>
<span id="l655">     * by the {@link #getProperty(String)} method. If the argument is</span><a href="#l655"></a>
<span id="l656">     * &lt;code&gt;null&lt;/code&gt;, then the current set of system properties is</span><a href="#l656"></a>
<span id="l657">     * forgotten.</span><a href="#l657"></a>
<span id="l658">     *</span><a href="#l658"></a>
<span id="l659">     * @param      props   the new system properties.</span><a href="#l659"></a>
<span id="l660">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l660"></a>
<span id="l661">     *             &lt;code&gt;checkPropertiesAccess&lt;/code&gt; method doesn't allow access</span><a href="#l661"></a>
<span id="l662">     *              to the system properties.</span><a href="#l662"></a>
<span id="l663">     * @see        #getProperties</span><a href="#l663"></a>
<span id="l664">     * @see        java.util.Properties</span><a href="#l664"></a>
<span id="l665">     * @see        java.lang.SecurityException</span><a href="#l665"></a>
<span id="l666">     * @see        java.lang.SecurityManager#checkPropertiesAccess()</span><a href="#l666"></a>
<span id="l667">     */</span><a href="#l667"></a>
<span id="l668">    public static void setProperties(Properties props) {</span><a href="#l668"></a>
<span id="l669">        SecurityManager sm = getSecurityManager();</span><a href="#l669"></a>
<span id="l670">        if (sm != null) {</span><a href="#l670"></a>
<span id="l671">            sm.checkPropertiesAccess();</span><a href="#l671"></a>
<span id="l672">        }</span><a href="#l672"></a>
<span id="l673">        if (props == null) {</span><a href="#l673"></a>
<span id="l674">            props = new Properties();</span><a href="#l674"></a>
<span id="l675">            initProperties(props);</span><a href="#l675"></a>
<span id="l676">        }</span><a href="#l676"></a>
<span id="l677">        System.props = props;</span><a href="#l677"></a>
<span id="l678">    }</span><a href="#l678"></a>
<span id="l679"></span><a href="#l679"></a>
<span id="l680">    /**</span><a href="#l680"></a>
<span id="l681">     * Gets the system property indicated by the specified key.</span><a href="#l681"></a>
<span id="l682">     * &lt;p&gt;</span><a href="#l682"></a>
<span id="l683">     * First, if there is a security manager, its</span><a href="#l683"></a>
<span id="l684">     * &lt;code&gt;checkPropertyAccess&lt;/code&gt; method is called with the key as</span><a href="#l684"></a>
<span id="l685">     * its argument. This may result in a SecurityException.</span><a href="#l685"></a>
<span id="l686">     * &lt;p&gt;</span><a href="#l686"></a>
<span id="l687">     * If there is no current set of system properties, a set of system</span><a href="#l687"></a>
<span id="l688">     * properties is first created and initialized in the same manner as</span><a href="#l688"></a>
<span id="l689">     * for the &lt;code&gt;getProperties&lt;/code&gt; method.</span><a href="#l689"></a>
<span id="l690">     *</span><a href="#l690"></a>
<span id="l691">     * @param      key   the name of the system property.</span><a href="#l691"></a>
<span id="l692">     * @return     the string value of the system property,</span><a href="#l692"></a>
<span id="l693">     *             or &lt;code&gt;null&lt;/code&gt; if there is no property with that key.</span><a href="#l693"></a>
<span id="l694">     *</span><a href="#l694"></a>
<span id="l695">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l695"></a>
<span id="l696">     *             &lt;code&gt;checkPropertyAccess&lt;/code&gt; method doesn't allow</span><a href="#l696"></a>
<span id="l697">     *              access to the specified system property.</span><a href="#l697"></a>
<span id="l698">     * @exception  NullPointerException if &lt;code&gt;key&lt;/code&gt; is</span><a href="#l698"></a>
<span id="l699">     *             &lt;code&gt;null&lt;/code&gt;.</span><a href="#l699"></a>
<span id="l700">     * @exception  IllegalArgumentException if &lt;code&gt;key&lt;/code&gt; is empty.</span><a href="#l700"></a>
<span id="l701">     * @see        #setProperty</span><a href="#l701"></a>
<span id="l702">     * @see        java.lang.SecurityException</span><a href="#l702"></a>
<span id="l703">     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)</span><a href="#l703"></a>
<span id="l704">     * @see        java.lang.System#getProperties()</span><a href="#l704"></a>
<span id="l705">     */</span><a href="#l705"></a>
<span id="l706">    public static String getProperty(String key) {</span><a href="#l706"></a>
<span id="l707">        checkKey(key);</span><a href="#l707"></a>
<span id="l708">        SecurityManager sm = getSecurityManager();</span><a href="#l708"></a>
<span id="l709">        if (sm != null) {</span><a href="#l709"></a>
<span id="l710">            sm.checkPropertyAccess(key);</span><a href="#l710"></a>
<span id="l711">        }</span><a href="#l711"></a>
<span id="l712"></span><a href="#l712"></a>
<span id="l713">        return props.getProperty(key);</span><a href="#l713"></a>
<span id="l714">    }</span><a href="#l714"></a>
<span id="l715"></span><a href="#l715"></a>
<span id="l716">    /**</span><a href="#l716"></a>
<span id="l717">     * Gets the system property indicated by the specified key.</span><a href="#l717"></a>
<span id="l718">     * &lt;p&gt;</span><a href="#l718"></a>
<span id="l719">     * First, if there is a security manager, its</span><a href="#l719"></a>
<span id="l720">     * &lt;code&gt;checkPropertyAccess&lt;/code&gt; method is called with the</span><a href="#l720"></a>
<span id="l721">     * &lt;code&gt;key&lt;/code&gt; as its argument.</span><a href="#l721"></a>
<span id="l722">     * &lt;p&gt;</span><a href="#l722"></a>
<span id="l723">     * If there is no current set of system properties, a set of system</span><a href="#l723"></a>
<span id="l724">     * properties is first created and initialized in the same manner as</span><a href="#l724"></a>
<span id="l725">     * for the &lt;code&gt;getProperties&lt;/code&gt; method.</span><a href="#l725"></a>
<span id="l726">     *</span><a href="#l726"></a>
<span id="l727">     * @param      key   the name of the system property.</span><a href="#l727"></a>
<span id="l728">     * @param      def   a default value.</span><a href="#l728"></a>
<span id="l729">     * @return     the string value of the system property,</span><a href="#l729"></a>
<span id="l730">     *             or the default value if there is no property with that key.</span><a href="#l730"></a>
<span id="l731">     *</span><a href="#l731"></a>
<span id="l732">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l732"></a>
<span id="l733">     *             &lt;code&gt;checkPropertyAccess&lt;/code&gt; method doesn't allow</span><a href="#l733"></a>
<span id="l734">     *             access to the specified system property.</span><a href="#l734"></a>
<span id="l735">     * @exception  NullPointerException if &lt;code&gt;key&lt;/code&gt; is</span><a href="#l735"></a>
<span id="l736">     *             &lt;code&gt;null&lt;/code&gt;.</span><a href="#l736"></a>
<span id="l737">     * @exception  IllegalArgumentException if &lt;code&gt;key&lt;/code&gt; is empty.</span><a href="#l737"></a>
<span id="l738">     * @see        #setProperty</span><a href="#l738"></a>
<span id="l739">     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)</span><a href="#l739"></a>
<span id="l740">     * @see        java.lang.System#getProperties()</span><a href="#l740"></a>
<span id="l741">     */</span><a href="#l741"></a>
<span id="l742">    public static String getProperty(String key, String def) {</span><a href="#l742"></a>
<span id="l743">        checkKey(key);</span><a href="#l743"></a>
<span id="l744">        SecurityManager sm = getSecurityManager();</span><a href="#l744"></a>
<span id="l745">        if (sm != null) {</span><a href="#l745"></a>
<span id="l746">            sm.checkPropertyAccess(key);</span><a href="#l746"></a>
<span id="l747">        }</span><a href="#l747"></a>
<span id="l748"></span><a href="#l748"></a>
<span id="l749">        return props.getProperty(key, def);</span><a href="#l749"></a>
<span id="l750">    }</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">    /**</span><a href="#l752"></a>
<span id="l753">     * Sets the system property indicated by the specified key.</span><a href="#l753"></a>
<span id="l754">     * &lt;p&gt;</span><a href="#l754"></a>
<span id="l755">     * First, if a security manager exists, its</span><a href="#l755"></a>
<span id="l756">     * &lt;code&gt;SecurityManager.checkPermission&lt;/code&gt; method</span><a href="#l756"></a>
<span id="l757">     * is called with a &lt;code&gt;PropertyPermission(key, &quot;write&quot;)&lt;/code&gt;</span><a href="#l757"></a>
<span id="l758">     * permission. This may result in a SecurityException being thrown.</span><a href="#l758"></a>
<span id="l759">     * If no exception is thrown, the specified property is set to the given</span><a href="#l759"></a>
<span id="l760">     * value.</span><a href="#l760"></a>
<span id="l761">     * &lt;p&gt;</span><a href="#l761"></a>
<span id="l762">     *</span><a href="#l762"></a>
<span id="l763">     * @param      key   the name of the system property.</span><a href="#l763"></a>
<span id="l764">     * @param      value the value of the system property.</span><a href="#l764"></a>
<span id="l765">     * @return     the previous value of the system property,</span><a href="#l765"></a>
<span id="l766">     *             or &lt;code&gt;null&lt;/code&gt; if it did not have one.</span><a href="#l766"></a>
<span id="l767">     *</span><a href="#l767"></a>
<span id="l768">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l768"></a>
<span id="l769">     *             &lt;code&gt;checkPermission&lt;/code&gt; method doesn't allow</span><a href="#l769"></a>
<span id="l770">     *             setting of the specified property.</span><a href="#l770"></a>
<span id="l771">     * @exception  NullPointerException if &lt;code&gt;key&lt;/code&gt; or</span><a href="#l771"></a>
<span id="l772">     *             &lt;code&gt;value&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;.</span><a href="#l772"></a>
<span id="l773">     * @exception  IllegalArgumentException if &lt;code&gt;key&lt;/code&gt; is empty.</span><a href="#l773"></a>
<span id="l774">     * @see        #getProperty</span><a href="#l774"></a>
<span id="l775">     * @see        java.lang.System#getProperty(java.lang.String)</span><a href="#l775"></a>
<span id="l776">     * @see        java.lang.System#getProperty(java.lang.String, java.lang.String)</span><a href="#l776"></a>
<span id="l777">     * @see        java.util.PropertyPermission</span><a href="#l777"></a>
<span id="l778">     * @see        SecurityManager#checkPermission</span><a href="#l778"></a>
<span id="l779">     * @since      1.2</span><a href="#l779"></a>
<span id="l780">     */</span><a href="#l780"></a>
<span id="l781">    public static String setProperty(String key, String value) {</span><a href="#l781"></a>
<span id="l782">        checkKey(key);</span><a href="#l782"></a>
<span id="l783">        SecurityManager sm = getSecurityManager();</span><a href="#l783"></a>
<span id="l784">        if (sm != null) {</span><a href="#l784"></a>
<span id="l785">            sm.checkPermission(new PropertyPermission(key,</span><a href="#l785"></a>
<span id="l786">                SecurityConstants.PROPERTY_WRITE_ACTION));</span><a href="#l786"></a>
<span id="l787">        }</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">        return (String) props.setProperty(key, value);</span><a href="#l789"></a>
<span id="l790">    }</span><a href="#l790"></a>
<span id="l791"></span><a href="#l791"></a>
<span id="l792">    /**</span><a href="#l792"></a>
<span id="l793">     * Removes the system property indicated by the specified key.</span><a href="#l793"></a>
<span id="l794">     * &lt;p&gt;</span><a href="#l794"></a>
<span id="l795">     * First, if a security manager exists, its</span><a href="#l795"></a>
<span id="l796">     * &lt;code&gt;SecurityManager.checkPermission&lt;/code&gt; method</span><a href="#l796"></a>
<span id="l797">     * is called with a &lt;code&gt;PropertyPermission(key, &quot;write&quot;)&lt;/code&gt;</span><a href="#l797"></a>
<span id="l798">     * permission. This may result in a SecurityException being thrown.</span><a href="#l798"></a>
<span id="l799">     * If no exception is thrown, the specified property is removed.</span><a href="#l799"></a>
<span id="l800">     * &lt;p&gt;</span><a href="#l800"></a>
<span id="l801">     *</span><a href="#l801"></a>
<span id="l802">     * @param      key   the name of the system property to be removed.</span><a href="#l802"></a>
<span id="l803">     * @return     the previous string value of the system property,</span><a href="#l803"></a>
<span id="l804">     *             or &lt;code&gt;null&lt;/code&gt; if there was no property with that key.</span><a href="#l804"></a>
<span id="l805">     *</span><a href="#l805"></a>
<span id="l806">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l806"></a>
<span id="l807">     *             &lt;code&gt;checkPropertyAccess&lt;/code&gt; method doesn't allow</span><a href="#l807"></a>
<span id="l808">     *              access to the specified system property.</span><a href="#l808"></a>
<span id="l809">     * @exception  NullPointerException if &lt;code&gt;key&lt;/code&gt; is</span><a href="#l809"></a>
<span id="l810">     *             &lt;code&gt;null&lt;/code&gt;.</span><a href="#l810"></a>
<span id="l811">     * @exception  IllegalArgumentException if &lt;code&gt;key&lt;/code&gt; is empty.</span><a href="#l811"></a>
<span id="l812">     * @see        #getProperty</span><a href="#l812"></a>
<span id="l813">     * @see        #setProperty</span><a href="#l813"></a>
<span id="l814">     * @see        java.util.Properties</span><a href="#l814"></a>
<span id="l815">     * @see        java.lang.SecurityException</span><a href="#l815"></a>
<span id="l816">     * @see        java.lang.SecurityManager#checkPropertiesAccess()</span><a href="#l816"></a>
<span id="l817">     * @since 1.5</span><a href="#l817"></a>
<span id="l818">     */</span><a href="#l818"></a>
<span id="l819">    public static String clearProperty(String key) {</span><a href="#l819"></a>
<span id="l820">        checkKey(key);</span><a href="#l820"></a>
<span id="l821">        SecurityManager sm = getSecurityManager();</span><a href="#l821"></a>
<span id="l822">        if (sm != null) {</span><a href="#l822"></a>
<span id="l823">            sm.checkPermission(new PropertyPermission(key, &quot;write&quot;));</span><a href="#l823"></a>
<span id="l824">        }</span><a href="#l824"></a>
<span id="l825"></span><a href="#l825"></a>
<span id="l826">        return (String) props.remove(key);</span><a href="#l826"></a>
<span id="l827">    }</span><a href="#l827"></a>
<span id="l828"></span><a href="#l828"></a>
<span id="l829">    private static void checkKey(String key) {</span><a href="#l829"></a>
<span id="l830">        if (key == null) {</span><a href="#l830"></a>
<span id="l831">            throw new NullPointerException(&quot;key can't be null&quot;);</span><a href="#l831"></a>
<span id="l832">        }</span><a href="#l832"></a>
<span id="l833">        if (key.equals(&quot;&quot;)) {</span><a href="#l833"></a>
<span id="l834">            throw new IllegalArgumentException(&quot;key can't be empty&quot;);</span><a href="#l834"></a>
<span id="l835">        }</span><a href="#l835"></a>
<span id="l836">    }</span><a href="#l836"></a>
<span id="l837"></span><a href="#l837"></a>
<span id="l838">    /**</span><a href="#l838"></a>
<span id="l839">     * Gets the value of the specified environment variable. An</span><a href="#l839"></a>
<span id="l840">     * environment variable is a system-dependent external named</span><a href="#l840"></a>
<span id="l841">     * value.</span><a href="#l841"></a>
<span id="l842">     *</span><a href="#l842"></a>
<span id="l843">     * &lt;p&gt;If a security manager exists, its</span><a href="#l843"></a>
<span id="l844">     * {@link SecurityManager#checkPermission checkPermission}</span><a href="#l844"></a>
<span id="l845">     * method is called with a</span><a href="#l845"></a>
<span id="l846">     * &lt;code&gt;{@link RuntimePermission}(&quot;getenv.&quot;+name)&lt;/code&gt;</span><a href="#l846"></a>
<span id="l847">     * permission.  This may result in a {@link SecurityException}</span><a href="#l847"></a>
<span id="l848">     * being thrown.  If no exception is thrown the value of the</span><a href="#l848"></a>
<span id="l849">     * variable &lt;code&gt;name&lt;/code&gt; is returned.</span><a href="#l849"></a>
<span id="l850">     *</span><a href="#l850"></a>
<span id="l851">     * &lt;p&gt;&lt;a name=&quot;EnvironmentVSSystemProperties&quot;&gt;&lt;i&gt;System</span><a href="#l851"></a>
<span id="l852">     * properties&lt;/i&gt; and &lt;i&gt;environment variables&lt;/i&gt;&lt;/a&gt; are both</span><a href="#l852"></a>
<span id="l853">     * conceptually mappings between names and values.  Both</span><a href="#l853"></a>
<span id="l854">     * mechanisms can be used to pass user-defined information to a</span><a href="#l854"></a>
<span id="l855">     * Java process.  Environment variables have a more global effect,</span><a href="#l855"></a>
<span id="l856">     * because they are visible to all descendants of the process</span><a href="#l856"></a>
<span id="l857">     * which defines them, not just the immediate Java subprocess.</span><a href="#l857"></a>
<span id="l858">     * They can have subtly different semantics, such as case</span><a href="#l858"></a>
<span id="l859">     * insensitivity, on different operating systems.  For these</span><a href="#l859"></a>
<span id="l860">     * reasons, environment variables are more likely to have</span><a href="#l860"></a>
<span id="l861">     * unintended side effects.  It is best to use system properties</span><a href="#l861"></a>
<span id="l862">     * where possible.  Environment variables should be used when a</span><a href="#l862"></a>
<span id="l863">     * global effect is desired, or when an external system interface</span><a href="#l863"></a>
<span id="l864">     * requires an environment variable (such as &lt;code&gt;PATH&lt;/code&gt;).</span><a href="#l864"></a>
<span id="l865">     *</span><a href="#l865"></a>
<span id="l866">     * &lt;p&gt;On UNIX systems the alphabetic case of &lt;code&gt;name&lt;/code&gt; is</span><a href="#l866"></a>
<span id="l867">     * typically significant, while on Microsoft Windows systems it is</span><a href="#l867"></a>
<span id="l868">     * typically not.  For example, the expression</span><a href="#l868"></a>
<span id="l869">     * &lt;code&gt;System.getenv(&quot;FOO&quot;).equals(System.getenv(&quot;foo&quot;))&lt;/code&gt;</span><a href="#l869"></a>
<span id="l870">     * is likely to be true on Microsoft Windows.</span><a href="#l870"></a>
<span id="l871">     *</span><a href="#l871"></a>
<span id="l872">     * @param  name the name of the environment variable</span><a href="#l872"></a>
<span id="l873">     * @return the string value of the variable, or &lt;code&gt;null&lt;/code&gt;</span><a href="#l873"></a>
<span id="l874">     *         if the variable is not defined in the system environment</span><a href="#l874"></a>
<span id="l875">     * @throws NullPointerException if &lt;code&gt;name&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;</span><a href="#l875"></a>
<span id="l876">     * @throws SecurityException</span><a href="#l876"></a>
<span id="l877">     *         if a security manager exists and its</span><a href="#l877"></a>
<span id="l878">     *         {@link SecurityManager#checkPermission checkPermission}</span><a href="#l878"></a>
<span id="l879">     *         method doesn't allow access to the environment variable</span><a href="#l879"></a>
<span id="l880">     *         &lt;code&gt;name&lt;/code&gt;</span><a href="#l880"></a>
<span id="l881">     * @see    #getenv()</span><a href="#l881"></a>
<span id="l882">     * @see    ProcessBuilder#environment()</span><a href="#l882"></a>
<span id="l883">     */</span><a href="#l883"></a>
<span id="l884">    public static String getenv(String name) {</span><a href="#l884"></a>
<span id="l885">        SecurityManager sm = getSecurityManager();</span><a href="#l885"></a>
<span id="l886">        if (sm != null) {</span><a href="#l886"></a>
<span id="l887">            sm.checkPermission(new RuntimePermission(&quot;getenv.&quot;+name));</span><a href="#l887"></a>
<span id="l888">        }</span><a href="#l888"></a>
<span id="l889"></span><a href="#l889"></a>
<span id="l890">        return ProcessEnvironment.getenv(name);</span><a href="#l890"></a>
<span id="l891">    }</span><a href="#l891"></a>
<span id="l892"></span><a href="#l892"></a>
<span id="l893"></span><a href="#l893"></a>
<span id="l894">    /**</span><a href="#l894"></a>
<span id="l895">     * Returns an unmodifiable string map view of the current system environment.</span><a href="#l895"></a>
<span id="l896">     * The environment is a system-dependent mapping from names to</span><a href="#l896"></a>
<span id="l897">     * values which is passed from parent to child processes.</span><a href="#l897"></a>
<span id="l898">     *</span><a href="#l898"></a>
<span id="l899">     * &lt;p&gt;If the system does not support environment variables, an</span><a href="#l899"></a>
<span id="l900">     * empty map is returned.</span><a href="#l900"></a>
<span id="l901">     *</span><a href="#l901"></a>
<span id="l902">     * &lt;p&gt;The returned map will never contain null keys or values.</span><a href="#l902"></a>
<span id="l903">     * Attempting to query the presence of a null key or value will</span><a href="#l903"></a>
<span id="l904">     * throw a {@link NullPointerException}.  Attempting to query</span><a href="#l904"></a>
<span id="l905">     * the presence of a key or value which is not of type</span><a href="#l905"></a>
<span id="l906">     * {@link String} will throw a {@link ClassCastException}.</span><a href="#l906"></a>
<span id="l907">     *</span><a href="#l907"></a>
<span id="l908">     * &lt;p&gt;The returned map and its collection views may not obey the</span><a href="#l908"></a>
<span id="l909">     * general contract of the {@link Object#equals} and</span><a href="#l909"></a>
<span id="l910">     * {@link Object#hashCode} methods.</span><a href="#l910"></a>
<span id="l911">     *</span><a href="#l911"></a>
<span id="l912">     * &lt;p&gt;The returned map is typically case-sensitive on all platforms.</span><a href="#l912"></a>
<span id="l913">     *</span><a href="#l913"></a>
<span id="l914">     * &lt;p&gt;If a security manager exists, its</span><a href="#l914"></a>
<span id="l915">     * {@link SecurityManager#checkPermission checkPermission}</span><a href="#l915"></a>
<span id="l916">     * method is called with a</span><a href="#l916"></a>
<span id="l917">     * &lt;code&gt;{@link RuntimePermission}(&quot;getenv.*&quot;)&lt;/code&gt;</span><a href="#l917"></a>
<span id="l918">     * permission.  This may result in a {@link SecurityException} being</span><a href="#l918"></a>
<span id="l919">     * thrown.</span><a href="#l919"></a>
<span id="l920">     *</span><a href="#l920"></a>
<span id="l921">     * &lt;p&gt;When passing information to a Java subprocess,</span><a href="#l921"></a>
<span id="l922">     * &lt;a href=#EnvironmentVSSystemProperties&gt;system properties&lt;/a&gt;</span><a href="#l922"></a>
<span id="l923">     * are generally preferred over environment variables.</span><a href="#l923"></a>
<span id="l924">     *</span><a href="#l924"></a>
<span id="l925">     * @return the environment as a map of variable names to values</span><a href="#l925"></a>
<span id="l926">     * @throws SecurityException</span><a href="#l926"></a>
<span id="l927">     *         if a security manager exists and its</span><a href="#l927"></a>
<span id="l928">     *         {@link SecurityManager#checkPermission checkPermission}</span><a href="#l928"></a>
<span id="l929">     *         method doesn't allow access to the process environment</span><a href="#l929"></a>
<span id="l930">     * @see    #getenv(String)</span><a href="#l930"></a>
<span id="l931">     * @see    ProcessBuilder#environment()</span><a href="#l931"></a>
<span id="l932">     * @since  1.5</span><a href="#l932"></a>
<span id="l933">     */</span><a href="#l933"></a>
<span id="l934">    public static java.util.Map&lt;String,String&gt; getenv() {</span><a href="#l934"></a>
<span id="l935">        SecurityManager sm = getSecurityManager();</span><a href="#l935"></a>
<span id="l936">        if (sm != null) {</span><a href="#l936"></a>
<span id="l937">            sm.checkPermission(new RuntimePermission(&quot;getenv.*&quot;));</span><a href="#l937"></a>
<span id="l938">        }</span><a href="#l938"></a>
<span id="l939"></span><a href="#l939"></a>
<span id="l940">        return ProcessEnvironment.getenv();</span><a href="#l940"></a>
<span id="l941">    }</span><a href="#l941"></a>
<span id="l942"></span><a href="#l942"></a>
<span id="l943">    /**</span><a href="#l943"></a>
<span id="l944">     * Terminates the currently running Java Virtual Machine. The</span><a href="#l944"></a>
<span id="l945">     * argument serves as a status code; by convention, a nonzero status</span><a href="#l945"></a>
<span id="l946">     * code indicates abnormal termination.</span><a href="#l946"></a>
<span id="l947">     * &lt;p&gt;</span><a href="#l947"></a>
<span id="l948">     * This method calls the &lt;code&gt;exit&lt;/code&gt; method in class</span><a href="#l948"></a>
<span id="l949">     * &lt;code&gt;Runtime&lt;/code&gt;. This method never returns normally.</span><a href="#l949"></a>
<span id="l950">     * &lt;p&gt;</span><a href="#l950"></a>
<span id="l951">     * The call &lt;code&gt;System.exit(n)&lt;/code&gt; is effectively equivalent to</span><a href="#l951"></a>
<span id="l952">     * the call:</span><a href="#l952"></a>
<span id="l953">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l953"></a>
<span id="l954">     * Runtime.getRuntime().exit(n)</span><a href="#l954"></a>
<span id="l955">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l955"></a>
<span id="l956">     *</span><a href="#l956"></a>
<span id="l957">     * @param      status   exit status.</span><a href="#l957"></a>
<span id="l958">     * @throws  SecurityException</span><a href="#l958"></a>
<span id="l959">     *        if a security manager exists and its &lt;code&gt;checkExit&lt;/code&gt;</span><a href="#l959"></a>
<span id="l960">     *        method doesn't allow exit with the specified status.</span><a href="#l960"></a>
<span id="l961">     * @see        java.lang.Runtime#exit(int)</span><a href="#l961"></a>
<span id="l962">     */</span><a href="#l962"></a>
<span id="l963">    public static void exit(int status) {</span><a href="#l963"></a>
<span id="l964">        Runtime.getRuntime().exit(status);</span><a href="#l964"></a>
<span id="l965">    }</span><a href="#l965"></a>
<span id="l966"></span><a href="#l966"></a>
<span id="l967">    /**</span><a href="#l967"></a>
<span id="l968">     * Runs the garbage collector.</span><a href="#l968"></a>
<span id="l969">     * &lt;p&gt;</span><a href="#l969"></a>
<span id="l970">     * Calling the &lt;code&gt;gc&lt;/code&gt; method suggests that the Java Virtual</span><a href="#l970"></a>
<span id="l971">     * Machine expend effort toward recycling unused objects in order to</span><a href="#l971"></a>
<span id="l972">     * make the memory they currently occupy available for quick reuse.</span><a href="#l972"></a>
<span id="l973">     * When control returns from the method call, the Java Virtual</span><a href="#l973"></a>
<span id="l974">     * Machine has made a best effort to reclaim space from all discarded</span><a href="#l974"></a>
<span id="l975">     * objects.</span><a href="#l975"></a>
<span id="l976">     * &lt;p&gt;</span><a href="#l976"></a>
<span id="l977">     * The call &lt;code&gt;System.gc()&lt;/code&gt; is effectively equivalent to the</span><a href="#l977"></a>
<span id="l978">     * call:</span><a href="#l978"></a>
<span id="l979">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l979"></a>
<span id="l980">     * Runtime.getRuntime().gc()</span><a href="#l980"></a>
<span id="l981">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l981"></a>
<span id="l982">     *</span><a href="#l982"></a>
<span id="l983">     * @see     java.lang.Runtime#gc()</span><a href="#l983"></a>
<span id="l984">     */</span><a href="#l984"></a>
<span id="l985">    public static void gc() {</span><a href="#l985"></a>
<span id="l986">        Runtime.getRuntime().gc();</span><a href="#l986"></a>
<span id="l987">    }</span><a href="#l987"></a>
<span id="l988"></span><a href="#l988"></a>
<span id="l989">    /**</span><a href="#l989"></a>
<span id="l990">     * Runs the finalization methods of any objects pending finalization.</span><a href="#l990"></a>
<span id="l991">     * &lt;p&gt;</span><a href="#l991"></a>
<span id="l992">     * Calling this method suggests that the Java Virtual Machine expend</span><a href="#l992"></a>
<span id="l993">     * effort toward running the &lt;code&gt;finalize&lt;/code&gt; methods of objects</span><a href="#l993"></a>
<span id="l994">     * that have been found to be discarded but whose &lt;code&gt;finalize&lt;/code&gt;</span><a href="#l994"></a>
<span id="l995">     * methods have not yet been run. When control returns from the</span><a href="#l995"></a>
<span id="l996">     * method call, the Java Virtual Machine has made a best effort to</span><a href="#l996"></a>
<span id="l997">     * complete all outstanding finalizations.</span><a href="#l997"></a>
<span id="l998">     * &lt;p&gt;</span><a href="#l998"></a>
<span id="l999">     * The call &lt;code&gt;System.runFinalization()&lt;/code&gt; is effectively</span><a href="#l999"></a>
<span id="l1000">     * equivalent to the call:</span><a href="#l1000"></a>
<span id="l1001">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1001"></a>
<span id="l1002">     * Runtime.getRuntime().runFinalization()</span><a href="#l1002"></a>
<span id="l1003">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1003"></a>
<span id="l1004">     *</span><a href="#l1004"></a>
<span id="l1005">     * @see     java.lang.Runtime#runFinalization()</span><a href="#l1005"></a>
<span id="l1006">     */</span><a href="#l1006"></a>
<span id="l1007">    public static void runFinalization() {</span><a href="#l1007"></a>
<span id="l1008">        Runtime.getRuntime().runFinalization();</span><a href="#l1008"></a>
<span id="l1009">    }</span><a href="#l1009"></a>
<span id="l1010"></span><a href="#l1010"></a>
<span id="l1011">    /**</span><a href="#l1011"></a>
<span id="l1012">     * Enable or disable finalization on exit; doing so specifies that the</span><a href="#l1012"></a>
<span id="l1013">     * finalizers of all objects that have finalizers that have not yet been</span><a href="#l1013"></a>
<span id="l1014">     * automatically invoked are to be run before the Java runtime exits.</span><a href="#l1014"></a>
<span id="l1015">     * By default, finalization on exit is disabled.</span><a href="#l1015"></a>
<span id="l1016">     *</span><a href="#l1016"></a>
<span id="l1017">     * &lt;p&gt;If there is a security manager,</span><a href="#l1017"></a>
<span id="l1018">     * its &lt;code&gt;checkExit&lt;/code&gt; method is first called</span><a href="#l1018"></a>
<span id="l1019">     * with 0 as its argument to ensure the exit is allowed.</span><a href="#l1019"></a>
<span id="l1020">     * This could result in a SecurityException.</span><a href="#l1020"></a>
<span id="l1021">     *</span><a href="#l1021"></a>
<span id="l1022">     * @deprecated  This method is inherently unsafe.  It may result in</span><a href="#l1022"></a>
<span id="l1023">     *      finalizers being called on live objects while other threads are</span><a href="#l1023"></a>
<span id="l1024">     *      concurrently manipulating those objects, resulting in erratic</span><a href="#l1024"></a>
<span id="l1025">     *      behavior or deadlock.</span><a href="#l1025"></a>
<span id="l1026">     * @param value indicating enabling or disabling of finalization</span><a href="#l1026"></a>
<span id="l1027">     * @throws  SecurityException</span><a href="#l1027"></a>
<span id="l1028">     *        if a security manager exists and its &lt;code&gt;checkExit&lt;/code&gt;</span><a href="#l1028"></a>
<span id="l1029">     *        method doesn't allow the exit.</span><a href="#l1029"></a>
<span id="l1030">     *</span><a href="#l1030"></a>
<span id="l1031">     * @see     java.lang.Runtime#exit(int)</span><a href="#l1031"></a>
<span id="l1032">     * @see     java.lang.Runtime#gc()</span><a href="#l1032"></a>
<span id="l1033">     * @see     java.lang.SecurityManager#checkExit(int)</span><a href="#l1033"></a>
<span id="l1034">     * @since   JDK1.1</span><a href="#l1034"></a>
<span id="l1035">     */</span><a href="#l1035"></a>
<span id="l1036">    @Deprecated</span><a href="#l1036"></a>
<span id="l1037">    public static void runFinalizersOnExit(boolean value) {</span><a href="#l1037"></a>
<span id="l1038">        Runtime.getRuntime().runFinalizersOnExit(value);</span><a href="#l1038"></a>
<span id="l1039">    }</span><a href="#l1039"></a>
<span id="l1040"></span><a href="#l1040"></a>
<span id="l1041">    /**</span><a href="#l1041"></a>
<span id="l1042">     * Loads a code file with the specified filename from the local file</span><a href="#l1042"></a>
<span id="l1043">     * system as a dynamic library. The filename</span><a href="#l1043"></a>
<span id="l1044">     * argument must be a complete path name.</span><a href="#l1044"></a>
<span id="l1045">     * &lt;p&gt;</span><a href="#l1045"></a>
<span id="l1046">     * The call &lt;code&gt;System.load(name)&lt;/code&gt; is effectively equivalent</span><a href="#l1046"></a>
<span id="l1047">     * to the call:</span><a href="#l1047"></a>
<span id="l1048">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1048"></a>
<span id="l1049">     * Runtime.getRuntime().load(name)</span><a href="#l1049"></a>
<span id="l1050">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1050"></a>
<span id="l1051">     *</span><a href="#l1051"></a>
<span id="l1052">     * @param      filename   the file to load.</span><a href="#l1052"></a>
<span id="l1053">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l1053"></a>
<span id="l1054">     *             &lt;code&gt;checkLink&lt;/code&gt; method doesn't allow</span><a href="#l1054"></a>
<span id="l1055">     *             loading of the specified dynamic library</span><a href="#l1055"></a>
<span id="l1056">     * @exception  UnsatisfiedLinkError  if the file does not exist.</span><a href="#l1056"></a>
<span id="l1057">     * @exception  NullPointerException if &lt;code&gt;filename&lt;/code&gt; is</span><a href="#l1057"></a>
<span id="l1058">     *             &lt;code&gt;null&lt;/code&gt;</span><a href="#l1058"></a>
<span id="l1059">     * @see        java.lang.Runtime#load(java.lang.String)</span><a href="#l1059"></a>
<span id="l1060">     * @see        java.lang.SecurityManager#checkLink(java.lang.String)</span><a href="#l1060"></a>
<span id="l1061">     */</span><a href="#l1061"></a>
<span id="l1062">    @CallerSensitive</span><a href="#l1062"></a>
<span id="l1063">    public static void load(String filename) {</span><a href="#l1063"></a>
<span id="l1064">        Runtime.getRuntime().load0(Reflection.getCallerClass(), filename);</span><a href="#l1064"></a>
<span id="l1065">    }</span><a href="#l1065"></a>
<span id="l1066"></span><a href="#l1066"></a>
<span id="l1067">    /**</span><a href="#l1067"></a>
<span id="l1068">     * Loads the system library specified by the &lt;code&gt;libname&lt;/code&gt;</span><a href="#l1068"></a>
<span id="l1069">     * argument. The manner in which a library name is mapped to the</span><a href="#l1069"></a>
<span id="l1070">     * actual system library is system dependent.</span><a href="#l1070"></a>
<span id="l1071">     * &lt;p&gt;</span><a href="#l1071"></a>
<span id="l1072">     * The call &lt;code&gt;System.loadLibrary(name)&lt;/code&gt; is effectively</span><a href="#l1072"></a>
<span id="l1073">     * equivalent to the call</span><a href="#l1073"></a>
<span id="l1074">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1074"></a>
<span id="l1075">     * Runtime.getRuntime().loadLibrary(name)</span><a href="#l1075"></a>
<span id="l1076">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1076"></a>
<span id="l1077">     *</span><a href="#l1077"></a>
<span id="l1078">     * @param      libname   the name of the library.</span><a href="#l1078"></a>
<span id="l1079">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l1079"></a>
<span id="l1080">     *             &lt;code&gt;checkLink&lt;/code&gt; method doesn't allow</span><a href="#l1080"></a>
<span id="l1081">     *             loading of the specified dynamic library</span><a href="#l1081"></a>
<span id="l1082">     * @exception  UnsatisfiedLinkError  if the library does not exist.</span><a href="#l1082"></a>
<span id="l1083">     * @exception  NullPointerException if &lt;code&gt;libname&lt;/code&gt; is</span><a href="#l1083"></a>
<span id="l1084">     *             &lt;code&gt;null&lt;/code&gt;</span><a href="#l1084"></a>
<span id="l1085">     * @see        java.lang.Runtime#loadLibrary(java.lang.String)</span><a href="#l1085"></a>
<span id="l1086">     * @see        java.lang.SecurityManager#checkLink(java.lang.String)</span><a href="#l1086"></a>
<span id="l1087">     */</span><a href="#l1087"></a>
<span id="l1088">    @CallerSensitive</span><a href="#l1088"></a>
<span id="l1089">    public static void loadLibrary(String libname) {</span><a href="#l1089"></a>
<span id="l1090">        Runtime.getRuntime().loadLibrary0(Reflection.getCallerClass(), libname);</span><a href="#l1090"></a>
<span id="l1091">    }</span><a href="#l1091"></a>
<span id="l1092"></span><a href="#l1092"></a>
<span id="l1093">    /**</span><a href="#l1093"></a>
<span id="l1094">     * Maps a library name into a platform-specific string representing</span><a href="#l1094"></a>
<span id="l1095">     * a native library.</span><a href="#l1095"></a>
<span id="l1096">     *</span><a href="#l1096"></a>
<span id="l1097">     * @param      libname the name of the library.</span><a href="#l1097"></a>
<span id="l1098">     * @return     a platform-dependent native library name.</span><a href="#l1098"></a>
<span id="l1099">     * @exception  NullPointerException if &lt;code&gt;libname&lt;/code&gt; is</span><a href="#l1099"></a>
<span id="l1100">     *             &lt;code&gt;null&lt;/code&gt;</span><a href="#l1100"></a>
<span id="l1101">     * @see        java.lang.System#loadLibrary(java.lang.String)</span><a href="#l1101"></a>
<span id="l1102">     * @see        java.lang.ClassLoader#findLibrary(java.lang.String)</span><a href="#l1102"></a>
<span id="l1103">     * @since      1.2</span><a href="#l1103"></a>
<span id="l1104">     */</span><a href="#l1104"></a>
<span id="l1105">    public static native String mapLibraryName(String libname);</span><a href="#l1105"></a>
<span id="l1106"></span><a href="#l1106"></a>
<span id="l1107">    /**</span><a href="#l1107"></a>
<span id="l1108">     * Initialize the system class.  Called after thread initialization.</span><a href="#l1108"></a>
<span id="l1109">     */</span><a href="#l1109"></a>
<span id="l1110">    private static void initializeSystemClass() {</span><a href="#l1110"></a>
<span id="l1111"></span><a href="#l1111"></a>
<span id="l1112">        // VM might invoke JNU_NewStringPlatform() to set those encoding</span><a href="#l1112"></a>
<span id="l1113">        // sensitive properties (user.home, user.name, boot.class.path, etc.)</span><a href="#l1113"></a>
<span id="l1114">        // during &quot;props&quot; initialization, in which it may need access, via</span><a href="#l1114"></a>
<span id="l1115">        // System.getProperty(), to the related system encoding property that</span><a href="#l1115"></a>
<span id="l1116">        // have been initialized (put into &quot;props&quot;) at early stage of the</span><a href="#l1116"></a>
<span id="l1117">        // initialization. So make sure the &quot;props&quot; is available at the</span><a href="#l1117"></a>
<span id="l1118">        // very beginning of the initialization and all system properties to</span><a href="#l1118"></a>
<span id="l1119">        // be put into it directly.</span><a href="#l1119"></a>
<span id="l1120">        props = new Properties();</span><a href="#l1120"></a>
<span id="l1121">        initProperties(props);  // initialized by the VM</span><a href="#l1121"></a>
<span id="l1122"></span><a href="#l1122"></a>
<span id="l1123">        // There are certain system configurations that may be controlled by</span><a href="#l1123"></a>
<span id="l1124">        // VM options such as the maximum amount of direct memory and</span><a href="#l1124"></a>
<span id="l1125">        // Integer cache size used to support the object identity semantics</span><a href="#l1125"></a>
<span id="l1126">        // of autoboxing.  Typically, the library will obtain these values</span><a href="#l1126"></a>
<span id="l1127">        // from the properties set by the VM.  If the properties are for</span><a href="#l1127"></a>
<span id="l1128">        // internal implementation use only, these properties should be</span><a href="#l1128"></a>
<span id="l1129">        // removed from the system properties.</span><a href="#l1129"></a>
<span id="l1130">        //</span><a href="#l1130"></a>
<span id="l1131">        // See java.lang.Integer.IntegerCache and the</span><a href="#l1131"></a>
<span id="l1132">        // sun.misc.VM.saveAndRemoveProperties method for example.</span><a href="#l1132"></a>
<span id="l1133">        //</span><a href="#l1133"></a>
<span id="l1134">        // Save a private copy of the system properties object that</span><a href="#l1134"></a>
<span id="l1135">        // can only be accessed by the internal implementation.  Remove</span><a href="#l1135"></a>
<span id="l1136">        // certain system properties that are not intended for public access.</span><a href="#l1136"></a>
<span id="l1137">        sun.misc.VM.saveAndRemoveProperties(props);</span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139"></span><a href="#l1139"></a>
<span id="l1140">        lineSeparator = props.getProperty(&quot;line.separator&quot;);</span><a href="#l1140"></a>
<span id="l1141">        StaticProperty.jdkSerialFilter();   // Load StaticProperty to cache the property values</span><a href="#l1141"></a>
<span id="l1142">        sun.misc.Version.init();</span><a href="#l1142"></a>
<span id="l1143"></span><a href="#l1143"></a>
<span id="l1144">        FileInputStream fdIn = new FileInputStream(FileDescriptor.in);</span><a href="#l1144"></a>
<span id="l1145">        FileOutputStream fdOut = new FileOutputStream(FileDescriptor.out);</span><a href="#l1145"></a>
<span id="l1146">        FileOutputStream fdErr = new FileOutputStream(FileDescriptor.err);</span><a href="#l1146"></a>
<span id="l1147">        setIn0(new BufferedInputStream(fdIn));</span><a href="#l1147"></a>
<span id="l1148">        setOut0(new PrintStream(new BufferedOutputStream(fdOut, 128), true));</span><a href="#l1148"></a>
<span id="l1149">        setErr0(new PrintStream(new BufferedOutputStream(fdErr, 128), true));</span><a href="#l1149"></a>
<span id="l1150">        // Load the zip library now in order to keep java.util.zip.ZipFile</span><a href="#l1150"></a>
<span id="l1151">        // from trying to use itself to load this library later.</span><a href="#l1151"></a>
<span id="l1152">        loadLibrary(&quot;zip&quot;);</span><a href="#l1152"></a>
<span id="l1153"></span><a href="#l1153"></a>
<span id="l1154">        // Setup Java signal handlers for HUP, TERM, and INT (where available).</span><a href="#l1154"></a>
<span id="l1155">        Terminator.setup();</span><a href="#l1155"></a>
<span id="l1156"></span><a href="#l1156"></a>
<span id="l1157">        // Initialize any miscellenous operating system settings that need to be</span><a href="#l1157"></a>
<span id="l1158">        // set for the class libraries. Currently this is no-op everywhere except</span><a href="#l1158"></a>
<span id="l1159">        // for Windows where the process-wide error mode is set before the java.io</span><a href="#l1159"></a>
<span id="l1160">        // classes are used.</span><a href="#l1160"></a>
<span id="l1161">        sun.misc.VM.initializeOSEnvironment();</span><a href="#l1161"></a>
<span id="l1162"></span><a href="#l1162"></a>
<span id="l1163">        // The main thread is not added to its thread group in the same</span><a href="#l1163"></a>
<span id="l1164">        // way as other threads; we must do it ourselves here.</span><a href="#l1164"></a>
<span id="l1165">        Thread current = Thread.currentThread();</span><a href="#l1165"></a>
<span id="l1166">        current.getThreadGroup().add(current);</span><a href="#l1166"></a>
<span id="l1167"></span><a href="#l1167"></a>
<span id="l1168">        // register shared secrets</span><a href="#l1168"></a>
<span id="l1169">        setJavaLangAccess();</span><a href="#l1169"></a>
<span id="l1170"></span><a href="#l1170"></a>
<span id="l1171">        // Subsystems that are invoked during initialization can invoke</span><a href="#l1171"></a>
<span id="l1172">        // sun.misc.VM.isBooted() in order to avoid doing things that should</span><a href="#l1172"></a>
<span id="l1173">        // wait until the application class loader has been set up.</span><a href="#l1173"></a>
<span id="l1174">        // IMPORTANT: Ensure that this remains the last initialization action!</span><a href="#l1174"></a>
<span id="l1175">        sun.misc.VM.booted();</span><a href="#l1175"></a>
<span id="l1176">    }</span><a href="#l1176"></a>
<span id="l1177"></span><a href="#l1177"></a>
<span id="l1178">    private static void setJavaLangAccess() {</span><a href="#l1178"></a>
<span id="l1179">        // Allow privileged classes outside of java.lang</span><a href="#l1179"></a>
<span id="l1180">        sun.misc.SharedSecrets.setJavaLangAccess(new sun.misc.JavaLangAccess(){</span><a href="#l1180"></a>
<span id="l1181">            public sun.reflect.ConstantPool getConstantPool(Class klass) {</span><a href="#l1181"></a>
<span id="l1182">                return klass.getConstantPool();</span><a href="#l1182"></a>
<span id="l1183">            }</span><a href="#l1183"></a>
<span id="l1184">            public boolean casAnnotationType(Class&lt;?&gt; klass, AnnotationType oldType, AnnotationType newType) {</span><a href="#l1184"></a>
<span id="l1185">                return klass.casAnnotationType(oldType, newType);</span><a href="#l1185"></a>
<span id="l1186">            }</span><a href="#l1186"></a>
<span id="l1187">            public AnnotationType getAnnotationType(Class klass) {</span><a href="#l1187"></a>
<span id="l1188">                return klass.getAnnotationType();</span><a href="#l1188"></a>
<span id="l1189">            }</span><a href="#l1189"></a>
<span id="l1190">            public byte[] getRawClassAnnotations(Class&lt;?&gt; klass) {</span><a href="#l1190"></a>
<span id="l1191">                return klass.getRawAnnotations();</span><a href="#l1191"></a>
<span id="l1192">            }</span><a href="#l1192"></a>
<span id="l1193">            public &lt;E extends Enum&lt;E&gt;&gt;</span><a href="#l1193"></a>
<span id="l1194">                    E[] getEnumConstantsShared(Class&lt;E&gt; klass) {</span><a href="#l1194"></a>
<span id="l1195">                return klass.getEnumConstantsShared();</span><a href="#l1195"></a>
<span id="l1196">            }</span><a href="#l1196"></a>
<span id="l1197">            public void blockedOn(Thread t, Interruptible b) {</span><a href="#l1197"></a>
<span id="l1198">                t.blockedOn(b);</span><a href="#l1198"></a>
<span id="l1199">            }</span><a href="#l1199"></a>
<span id="l1200">            public void registerShutdownHook(int slot, boolean registerShutdownInProgress, Runnable hook) {</span><a href="#l1200"></a>
<span id="l1201">                Shutdown.add(slot, registerShutdownInProgress, hook);</span><a href="#l1201"></a>
<span id="l1202">            }</span><a href="#l1202"></a>
<span id="l1203">            public int getStackTraceDepth(Throwable t) {</span><a href="#l1203"></a>
<span id="l1204">                return t.getStackTraceDepth();</span><a href="#l1204"></a>
<span id="l1205">            }</span><a href="#l1205"></a>
<span id="l1206">            public StackTraceElement getStackTraceElement(Throwable t, int i) {</span><a href="#l1206"></a>
<span id="l1207">                return t.getStackTraceElement(i);</span><a href="#l1207"></a>
<span id="l1208">            }</span><a href="#l1208"></a>
<span id="l1209">            public int getStringHash32(String string) {</span><a href="#l1209"></a>
<span id="l1210">                return string.hash32();</span><a href="#l1210"></a>
<span id="l1211">            }</span><a href="#l1211"></a>
<span id="l1212">            public Thread newThreadWithAcc(Runnable target, AccessControlContext acc) {</span><a href="#l1212"></a>
<span id="l1213">                return new Thread(target, acc);</span><a href="#l1213"></a>
<span id="l1214">            }</span><a href="#l1214"></a>
<span id="l1215">            public void invokeFinalize(Object o) throws Throwable {</span><a href="#l1215"></a>
<span id="l1216">                o.finalize();</span><a href="#l1216"></a>
<span id="l1217">            }</span><a href="#l1217"></a>
<span id="l1218">        });</span><a href="#l1218"></a>
<span id="l1219">    }</span><a href="#l1219"></a>
<span id="l1220">}</span><a href="#l1220"></a></pre>
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

