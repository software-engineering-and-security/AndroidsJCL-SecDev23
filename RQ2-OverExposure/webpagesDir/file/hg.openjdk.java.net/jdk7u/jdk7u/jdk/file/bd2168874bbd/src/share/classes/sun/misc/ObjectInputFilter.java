<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: bd2168874bbd src/share/classes/sun/misc/ObjectInputFilter.java</title>
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
<li><a href="/jdk7u/jdk7u/jdk/file/bd2168874bbd/src/share/classes/sun/misc/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/misc/ObjectInputFilter.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/bd2168874bbd/src/share/classes/sun/misc/ObjectInputFilter.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/bd2168874bbd/src/share/classes/sun/misc/ObjectInputFilter.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/bd2168874bbd/src/share/classes/sun/misc/ObjectInputFilter.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/bd2168874bbd/src/share/classes/sun/misc/ObjectInputFilter.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/bd2168874bbd/src/share/classes/sun/misc/ObjectInputFilter.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/misc/ObjectInputFilter.java @ 8938:bd2168874bbd</h3>

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
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/8425a2e77f33/src/share/classes/sun/misc/ObjectInputFilter.java">8425a2e77f33</a> </td>
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
<span id="l2"> * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.misc;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.ObjectInputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.SerializablePermission;</span><a href="#l29"></a>
<span id="l30">import java.security.AccessController;</span><a href="#l30"></a>
<span id="l31">import java.security.PrivilegedAction;</span><a href="#l31"></a>
<span id="l32">import java.security.Security;</span><a href="#l32"></a>
<span id="l33">import java.util.ArrayList;</span><a href="#l33"></a>
<span id="l34">import java.util.List;</span><a href="#l34"></a>
<span id="l35">import java.util.Objects;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">import sun.util.logging.PlatformLogger;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">import jdk.internal.util.StaticProperty;</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">/**</span><a href="#l41"></a>
<span id="l42"> * Filter classes, array lengths, and graph metrics during deserialization.</span><a href="#l42"></a>
<span id="l43"> * If set on an {@link ObjectInputStream}, the {@link #checkInput checkInput(FilterInfo)}</span><a href="#l43"></a>
<span id="l44"> * method is called to validate classes, the length of each array,</span><a href="#l44"></a>
<span id="l45"> * the number of objects being read from the stream, the depth of the graph,</span><a href="#l45"></a>
<span id="l46"> * and the total number of bytes read from the stream.</span><a href="#l46"></a>
<span id="l47"> * &lt;p&gt;</span><a href="#l47"></a>
<span id="l48"> * A filter can be set via {@link ObjectInputStream#setObjectInputFilter setObjectInputFilter}</span><a href="#l48"></a>
<span id="l49"> * for an individual ObjectInputStream.</span><a href="#l49"></a>
<span id="l50"> * A filter can be set via {@link Config#setSerialFilter(ObjectInputFilter) Config.setSerialFilter}</span><a href="#l50"></a>
<span id="l51"> * to affect every {@code ObjectInputStream} that does not otherwise set a filter.</span><a href="#l51"></a>
<span id="l52"> * &lt;p&gt;</span><a href="#l52"></a>
<span id="l53"> * A filter determines whether the arguments are {@link Status#ALLOWED ALLOWED}</span><a href="#l53"></a>
<span id="l54"> * or {@link Status#REJECTED REJECTED} and should return the appropriate status.</span><a href="#l54"></a>
<span id="l55"> * If the filter cannot determine the status it should return</span><a href="#l55"></a>
<span id="l56"> * {@link Status#UNDECIDED UNDECIDED}.</span><a href="#l56"></a>
<span id="l57"> * Filters should be designed for the specific use case and expected types.</span><a href="#l57"></a>
<span id="l58"> * A filter designed for a particular use may be passed a class that is outside</span><a href="#l58"></a>
<span id="l59"> * of the scope of the filter. If the purpose of the filter is to black-list classes</span><a href="#l59"></a>
<span id="l60"> * then it can reject a candidate class that matches and report UNDECIDED for others.</span><a href="#l60"></a>
<span id="l61"> * A filter may be called with class equals {@code null}, {@code arrayLength} equal -1,</span><a href="#l61"></a>
<span id="l62"> * the depth, number of references, and stream size and return a status</span><a href="#l62"></a>
<span id="l63"> * that reflects only one or only some of the values.</span><a href="#l63"></a>
<span id="l64"> * This allows a filter to specific about the choice it is reporting and</span><a href="#l64"></a>
<span id="l65"> * to use other filters without forcing either allowed or rejected status.</span><a href="#l65"></a>
<span id="l66"> *</span><a href="#l66"></a>
<span id="l67"> * &lt;p&gt;</span><a href="#l67"></a>
<span id="l68"> * Typically, a custom filter should check if a process-wide filter</span><a href="#l68"></a>
<span id="l69"> * is configured and defer to it if so. For example,</span><a href="#l69"></a>
<span id="l70"> * &lt;pre&gt;{@code</span><a href="#l70"></a>
<span id="l71"> * ObjectInputFilter.Status checkInput(FilterInfo info) {</span><a href="#l71"></a>
<span id="l72"> *     ObjectInputFilter serialFilter = ObjectInputFilter.Config.getSerialFilter();</span><a href="#l72"></a>
<span id="l73"> *     if (serialFilter != null) {</span><a href="#l73"></a>
<span id="l74"> *         ObjectInputFilter.Status status = serialFilter.checkInput(info);</span><a href="#l74"></a>
<span id="l75"> *         if (status != ObjectInputFilter.Status.UNDECIDED) {</span><a href="#l75"></a>
<span id="l76"> *             // The process-wide filter overrides this filter</span><a href="#l76"></a>
<span id="l77"> *             return status;</span><a href="#l77"></a>
<span id="l78"> *         }</span><a href="#l78"></a>
<span id="l79"> *     }</span><a href="#l79"></a>
<span id="l80"> *     if (info.serialClass() != null &amp;&amp;</span><a href="#l80"></a>
<span id="l81"> *         Remote.class.isAssignableFrom(info.serialClass())) {</span><a href="#l81"></a>
<span id="l82"> *         return Status.REJECTED;      // Do not allow Remote objects</span><a href="#l82"></a>
<span id="l83"> *     }</span><a href="#l83"></a>
<span id="l84"> *     return Status.UNDECIDED;</span><a href="#l84"></a>
<span id="l85"> * }</span><a href="#l85"></a>
<span id="l86"> *}&lt;/pre&gt;</span><a href="#l86"></a>
<span id="l87"> * &lt;p&gt;</span><a href="#l87"></a>
<span id="l88"> * Unless otherwise noted, passing a {@code null} argument to a</span><a href="#l88"></a>
<span id="l89"> * method in this interface and its nested classes will cause a</span><a href="#l89"></a>
<span id="l90"> * {@link NullPointerException} to be thrown.</span><a href="#l90"></a>
<span id="l91"> *</span><a href="#l91"></a>
<span id="l92"> * @since 8u</span><a href="#l92"></a>
<span id="l93"> */</span><a href="#l93"></a>
<span id="l94">public interface ObjectInputFilter {</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    /**</span><a href="#l96"></a>
<span id="l97">     * Check the class, array length, number of object references, depth,</span><a href="#l97"></a>
<span id="l98">     * stream size, and other available filtering information.</span><a href="#l98"></a>
<span id="l99">     * Implementations of this method check the contents of the object graph being created</span><a href="#l99"></a>
<span id="l100">     * during deserialization. The filter returns {@link Status#ALLOWED Status.ALLOWED},</span><a href="#l100"></a>
<span id="l101">     * {@link Status#REJECTED Status.REJECTED}, or {@link Status#UNDECIDED Status.UNDECIDED}.</span><a href="#l101"></a>
<span id="l102">     *</span><a href="#l102"></a>
<span id="l103">     * @param filterInfo provides information about the current object being deserialized,</span><a href="#l103"></a>
<span id="l104">     *             if any, and the status of the {@link ObjectInputStream}</span><a href="#l104"></a>
<span id="l105">     * @return  {@link Status#ALLOWED Status.ALLOWED} if accepted,</span><a href="#l105"></a>
<span id="l106">     *          {@link Status#REJECTED Status.REJECTED} if rejected,</span><a href="#l106"></a>
<span id="l107">     *          {@link Status#UNDECIDED Status.UNDECIDED} if undecided.</span><a href="#l107"></a>
<span id="l108">     */</span><a href="#l108"></a>
<span id="l109">    Status checkInput(FilterInfo filterInfo);</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">    /**</span><a href="#l111"></a>
<span id="l112">     * FilterInfo provides access to information about the current object</span><a href="#l112"></a>
<span id="l113">     * being deserialized and the status of the {@link ObjectInputStream}.</span><a href="#l113"></a>
<span id="l114">     * @since 9</span><a href="#l114"></a>
<span id="l115">     */</span><a href="#l115"></a>
<span id="l116">    interface FilterInfo {</span><a href="#l116"></a>
<span id="l117">        /**</span><a href="#l117"></a>
<span id="l118">         * The class of an object being deserialized.</span><a href="#l118"></a>
<span id="l119">         * For arrays, it is the array type.</span><a href="#l119"></a>
<span id="l120">         * For example, the array class name of a 2 dimensional array of strings is</span><a href="#l120"></a>
<span id="l121">         * &quot;{@code [[Ljava.lang.String;}&quot;.</span><a href="#l121"></a>
<span id="l122">         * To check the array's element type, iteratively use</span><a href="#l122"></a>
<span id="l123">         * {@link Class#getComponentType() Class.getComponentType} while the result</span><a href="#l123"></a>
<span id="l124">         * is an array and then check the class.</span><a href="#l124"></a>
<span id="l125">         * The {@code serialClass is null} in the case where a new object is not being</span><a href="#l125"></a>
<span id="l126">         * created and to give the filter a chance to check the depth, number of</span><a href="#l126"></a>
<span id="l127">         * references to existing objects, and the stream size.</span><a href="#l127"></a>
<span id="l128">         *</span><a href="#l128"></a>
<span id="l129">         * @return class of an object being deserialized; may be null</span><a href="#l129"></a>
<span id="l130">         */</span><a href="#l130"></a>
<span id="l131">        Class&lt;?&gt; serialClass();</span><a href="#l131"></a>
<span id="l132"></span><a href="#l132"></a>
<span id="l133">        /**</span><a href="#l133"></a>
<span id="l134">         * The number of array elements when deserializing an array of the class.</span><a href="#l134"></a>
<span id="l135">         *</span><a href="#l135"></a>
<span id="l136">         * @return the non-negative number of array elements when deserializing</span><a href="#l136"></a>
<span id="l137">         * an array of the class, otherwise -1</span><a href="#l137"></a>
<span id="l138">         */</span><a href="#l138"></a>
<span id="l139">        long arrayLength();</span><a href="#l139"></a>
<span id="l140"></span><a href="#l140"></a>
<span id="l141">        /**</span><a href="#l141"></a>
<span id="l142">         * The current depth.</span><a href="#l142"></a>
<span id="l143">         * The depth starts at {@code 1} and increases for each nested object and</span><a href="#l143"></a>
<span id="l144">         * decrements when each nested object returns.</span><a href="#l144"></a>
<span id="l145">         *</span><a href="#l145"></a>
<span id="l146">         * @return the current depth</span><a href="#l146"></a>
<span id="l147">         */</span><a href="#l147"></a>
<span id="l148">        long depth();</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">        /**</span><a href="#l150"></a>
<span id="l151">         * The current number of object references.</span><a href="#l151"></a>
<span id="l152">         *</span><a href="#l152"></a>
<span id="l153">         * @return the non-negative current number of object references</span><a href="#l153"></a>
<span id="l154">         */</span><a href="#l154"></a>
<span id="l155">        long references();</span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">        /**</span><a href="#l157"></a>
<span id="l158">         * The current number of bytes consumed.</span><a href="#l158"></a>
<span id="l159">         * @implSpec  {@code streamBytes} is implementation specific</span><a href="#l159"></a>
<span id="l160">         * and may not be directly related to the object in the stream</span><a href="#l160"></a>
<span id="l161">         * that caused the callback.</span><a href="#l161"></a>
<span id="l162">         *</span><a href="#l162"></a>
<span id="l163">         * @return the non-negative current number of bytes consumed</span><a href="#l163"></a>
<span id="l164">         */</span><a href="#l164"></a>
<span id="l165">        long streamBytes();</span><a href="#l165"></a>
<span id="l166">    }</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">    /**</span><a href="#l168"></a>
<span id="l169">     * The status of a check on the class, array length, number of references,</span><a href="#l169"></a>
<span id="l170">     * depth, and stream size.</span><a href="#l170"></a>
<span id="l171">     *</span><a href="#l171"></a>
<span id="l172">     * @since 8u</span><a href="#l172"></a>
<span id="l173">     */</span><a href="#l173"></a>
<span id="l174">    enum Status {</span><a href="#l174"></a>
<span id="l175">        /**</span><a href="#l175"></a>
<span id="l176">         * The status is undecided, not allowed and not rejected.</span><a href="#l176"></a>
<span id="l177">         */</span><a href="#l177"></a>
<span id="l178">        UNDECIDED,</span><a href="#l178"></a>
<span id="l179">        /**</span><a href="#l179"></a>
<span id="l180">         * The status is allowed.</span><a href="#l180"></a>
<span id="l181">         */</span><a href="#l181"></a>
<span id="l182">        ALLOWED,</span><a href="#l182"></a>
<span id="l183">        /**</span><a href="#l183"></a>
<span id="l184">         * The status is rejected.</span><a href="#l184"></a>
<span id="l185">         */</span><a href="#l185"></a>
<span id="l186">        REJECTED;</span><a href="#l186"></a>
<span id="l187">    }</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">    /**</span><a href="#l189"></a>
<span id="l190">     * A utility class to set and get the process-wide filter or create a filter</span><a href="#l190"></a>
<span id="l191">     * from a pattern string. If a process-wide filter is set, it will be</span><a href="#l191"></a>
<span id="l192">     * used for each {@link ObjectInputStream} that does not set its own filter.</span><a href="#l192"></a>
<span id="l193">     * &lt;p&gt;</span><a href="#l193"></a>
<span id="l194">     * When setting the filter, it should be stateless and idempotent,</span><a href="#l194"></a>
<span id="l195">     * reporting the same result when passed the same arguments.</span><a href="#l195"></a>
<span id="l196">     * &lt;p&gt;</span><a href="#l196"></a>
<span id="l197">     * The filter is configured using the {@link java.security.Security}</span><a href="#l197"></a>
<span id="l198">     * property {@code jdk.serialFilter} and can be overridden by</span><a href="#l198"></a>
<span id="l199">     * the System property {@code jdk.serialFilter}.</span><a href="#l199"></a>
<span id="l200">     *</span><a href="#l200"></a>
<span id="l201">     * The syntax is the same as for the {@link #createFilter(String) createFilter} method.</span><a href="#l201"></a>
<span id="l202">     *</span><a href="#l202"></a>
<span id="l203">     * @since 8u</span><a href="#l203"></a>
<span id="l204">     */</span><a href="#l204"></a>
<span id="l205">    final class Config {</span><a href="#l205"></a>
<span id="l206">        /* No instances. */</span><a href="#l206"></a>
<span id="l207">        private Config() {}</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">        /**</span><a href="#l209"></a>
<span id="l210">         * Lock object for process-wide filter.</span><a href="#l210"></a>
<span id="l211">         */</span><a href="#l211"></a>
<span id="l212">        private final static Object serialFilterLock = new Object();</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">        /**</span><a href="#l214"></a>
<span id="l215">         * Debug: Logger</span><a href="#l215"></a>
<span id="l216">         */</span><a href="#l216"></a>
<span id="l217">        private final static PlatformLogger configLog;</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">        /**</span><a href="#l219"></a>
<span id="l220">         * Logger for debugging.</span><a href="#l220"></a>
<span id="l221">         */</span><a href="#l221"></a>
<span id="l222">        static void filterLog(PlatformLogger.Level level, String msg, Object... args) {</span><a href="#l222"></a>
<span id="l223">            if (configLog != null) {</span><a href="#l223"></a>
<span id="l224">                if (PlatformLogger.Level.INFO.equals(level)) {</span><a href="#l224"></a>
<span id="l225">                    configLog.info(msg, args);</span><a href="#l225"></a>
<span id="l226">                } else if (PlatformLogger.Level.WARNING.equals(level)) {</span><a href="#l226"></a>
<span id="l227">                    configLog.warning(msg, args);</span><a href="#l227"></a>
<span id="l228">                } else {</span><a href="#l228"></a>
<span id="l229">                    configLog.severe(msg, args);</span><a href="#l229"></a>
<span id="l230">                }</span><a href="#l230"></a>
<span id="l231">            }</span><a href="#l231"></a>
<span id="l232">        }</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">        /**</span><a href="#l234"></a>
<span id="l235">         * The name for the process-wide deserialization filter.</span><a href="#l235"></a>
<span id="l236">         * Used as a system property and a java.security.Security property.</span><a href="#l236"></a>
<span id="l237">         */</span><a href="#l237"></a>
<span id="l238">        private final static String SERIAL_FILTER_PROPNAME = &quot;jdk.serialFilter&quot;;</span><a href="#l238"></a>
<span id="l239"></span><a href="#l239"></a>
<span id="l240">        /**</span><a href="#l240"></a>
<span id="l241">         * The process-wide filter; may be null.</span><a href="#l241"></a>
<span id="l242">         * Lookup the filter in java.security.Security or</span><a href="#l242"></a>
<span id="l243">         * the system property.</span><a href="#l243"></a>
<span id="l244">         */</span><a href="#l244"></a>
<span id="l245">        private final static ObjectInputFilter configuredFilter;</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">        static {</span><a href="#l247"></a>
<span id="l248">            configuredFilter = AccessController</span><a href="#l248"></a>
<span id="l249">                    .doPrivileged(new PrivilegedAction&lt;ObjectInputFilter&gt;() {</span><a href="#l249"></a>
<span id="l250">                        @Override</span><a href="#l250"></a>
<span id="l251">                        public ObjectInputFilter run() {</span><a href="#l251"></a>
<span id="l252">                            String props = StaticProperty.jdkSerialFilter();</span><a href="#l252"></a>
<span id="l253">                            if (props == null) {</span><a href="#l253"></a>
<span id="l254">                                props = Security.getProperty(SERIAL_FILTER_PROPNAME);</span><a href="#l254"></a>
<span id="l255">                            }</span><a href="#l255"></a>
<span id="l256">                            if (props != null) {</span><a href="#l256"></a>
<span id="l257">                                PlatformLogger log = PlatformLogger.getLogger(&quot;java.io.serialization&quot;);</span><a href="#l257"></a>
<span id="l258">                                log.info(&quot;Creating serialization filter from {0}&quot;, props);</span><a href="#l258"></a>
<span id="l259">                                try {</span><a href="#l259"></a>
<span id="l260">                                    return createFilter(props);</span><a href="#l260"></a>
<span id="l261">                                } catch (RuntimeException re) {</span><a href="#l261"></a>
<span id="l262">                                    log.warning(&quot;Error configuring filter: {0}&quot;, re);</span><a href="#l262"></a>
<span id="l263">                                }</span><a href="#l263"></a>
<span id="l264">                            }</span><a href="#l264"></a>
<span id="l265">                            return null;</span><a href="#l265"></a>
<span id="l266">                        }</span><a href="#l266"></a>
<span id="l267">                    });</span><a href="#l267"></a>
<span id="l268">            configLog = (configuredFilter != null) ? PlatformLogger.getLogger(&quot;java.io.serialization&quot;) : null;</span><a href="#l268"></a>
<span id="l269">        }</span><a href="#l269"></a>
<span id="l270"></span><a href="#l270"></a>
<span id="l271">        /**</span><a href="#l271"></a>
<span id="l272">         * Current configured filter.</span><a href="#l272"></a>
<span id="l273">         */</span><a href="#l273"></a>
<span id="l274">        private static ObjectInputFilter serialFilter = configuredFilter;</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">        /**</span><a href="#l276"></a>
<span id="l277">         * Get the filter for classes being deserialized on the ObjectInputStream.</span><a href="#l277"></a>
<span id="l278">         *</span><a href="#l278"></a>
<span id="l279">         * @param inputStream ObjectInputStream from which to get the filter; non-null</span><a href="#l279"></a>
<span id="l280">         * @throws RuntimeException if the filter rejects</span><a href="#l280"></a>
<span id="l281">         */</span><a href="#l281"></a>
<span id="l282">        public static ObjectInputFilter getObjectInputFilter(ObjectInputStream inputStream) {</span><a href="#l282"></a>
<span id="l283">            Objects.requireNonNull(inputStream, &quot;inputStream&quot;);</span><a href="#l283"></a>
<span id="l284">            return sun.misc.SharedSecrets.getJavaOISAccess().getObjectInputFilter(inputStream);</span><a href="#l284"></a>
<span id="l285">        }</span><a href="#l285"></a>
<span id="l286"></span><a href="#l286"></a>
<span id="l287">        /**</span><a href="#l287"></a>
<span id="l288">         * Set the process-wide filter if it has not already been configured or set.</span><a href="#l288"></a>
<span id="l289">         *</span><a href="#l289"></a>
<span id="l290">         * @param inputStream ObjectInputStream on which to set the filter; non-null</span><a href="#l290"></a>
<span id="l291">         * @param filter the serialization filter to set as the process-wide filter; not null</span><a href="#l291"></a>
<span id="l292">         * @throws SecurityException if there is security manager and the</span><a href="#l292"></a>
<span id="l293">         *       {@code SerializablePermission(&quot;serialFilter&quot;)} is not granted</span><a href="#l293"></a>
<span id="l294">         * @throws IllegalStateException if the filter has already been set {@code non-null}</span><a href="#l294"></a>
<span id="l295">         */</span><a href="#l295"></a>
<span id="l296">        public static void setObjectInputFilter(ObjectInputStream inputStream,</span><a href="#l296"></a>
<span id="l297">                ObjectInputFilter filter) {</span><a href="#l297"></a>
<span id="l298">            Objects.requireNonNull(inputStream, &quot;inputStream&quot;);</span><a href="#l298"></a>
<span id="l299">            sun.misc.SharedSecrets.getJavaOISAccess().setObjectInputFilter(inputStream, filter);</span><a href="#l299"></a>
<span id="l300">        }</span><a href="#l300"></a>
<span id="l301"></span><a href="#l301"></a>
<span id="l302">        /**</span><a href="#l302"></a>
<span id="l303">         * Returns the process-wide serialization filter or {@code null} if not configured.</span><a href="#l303"></a>
<span id="l304">         *</span><a href="#l304"></a>
<span id="l305">         * @return the process-wide serialization filter or {@code null} if not configured</span><a href="#l305"></a>
<span id="l306">         */</span><a href="#l306"></a>
<span id="l307">        public static ObjectInputFilter getSerialFilter() {</span><a href="#l307"></a>
<span id="l308">            synchronized (serialFilterLock) {</span><a href="#l308"></a>
<span id="l309">                return serialFilter;</span><a href="#l309"></a>
<span id="l310">            }</span><a href="#l310"></a>
<span id="l311">        }</span><a href="#l311"></a>
<span id="l312"></span><a href="#l312"></a>
<span id="l313">        /**</span><a href="#l313"></a>
<span id="l314">         * Set the process-wide filter if it has not already been configured or set.</span><a href="#l314"></a>
<span id="l315">         *</span><a href="#l315"></a>
<span id="l316">         * @param filter the serialization filter to set as the process-wide filter; not null</span><a href="#l316"></a>
<span id="l317">         * @throws SecurityException if there is security manager and the</span><a href="#l317"></a>
<span id="l318">         *       {@code SerializablePermission(&quot;serialFilter&quot;)} is not granted</span><a href="#l318"></a>
<span id="l319">         * @throws IllegalStateException if the filter has already been set {@code non-null}</span><a href="#l319"></a>
<span id="l320">         */</span><a href="#l320"></a>
<span id="l321">        public static void setSerialFilter(ObjectInputFilter filter) {</span><a href="#l321"></a>
<span id="l322">            Objects.requireNonNull(filter, &quot;filter&quot;);</span><a href="#l322"></a>
<span id="l323">            SecurityManager sm = System.getSecurityManager();</span><a href="#l323"></a>
<span id="l324">            if (sm != null) {</span><a href="#l324"></a>
<span id="l325">                sm.checkPermission(new SerializablePermission(&quot;serialFilter&quot;));</span><a href="#l325"></a>
<span id="l326">            }</span><a href="#l326"></a>
<span id="l327">            synchronized (serialFilterLock) {</span><a href="#l327"></a>
<span id="l328">                if (serialFilter != null) {</span><a href="#l328"></a>
<span id="l329">                    throw new IllegalStateException(&quot;Serial filter can only be set once&quot;);</span><a href="#l329"></a>
<span id="l330">                }</span><a href="#l330"></a>
<span id="l331">                serialFilter = filter;</span><a href="#l331"></a>
<span id="l332">            }</span><a href="#l332"></a>
<span id="l333">        }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">        /**</span><a href="#l335"></a>
<span id="l336">         * Returns an ObjectInputFilter from a string of patterns.</span><a href="#l336"></a>
<span id="l337">         * &lt;p&gt;</span><a href="#l337"></a>
<span id="l338">         * Patterns are separated by &quot;;&quot; (semicolon). Whitespace is significant and</span><a href="#l338"></a>
<span id="l339">         * is considered part of the pattern.</span><a href="#l339"></a>
<span id="l340">         * If a pattern includes an equals assignment, &quot;{@code =}&quot; it sets a limit.</span><a href="#l340"></a>
<span id="l341">         * If a limit appears more than once the last value is used.</span><a href="#l341"></a>
<span id="l342">         * &lt;ul&gt;</span><a href="#l342"></a>
<span id="l343">         *     &lt;li&gt;maxdepth={@code value} - the maximum depth of a graph&lt;/li&gt;</span><a href="#l343"></a>
<span id="l344">         *     &lt;li&gt;maxrefs={@code value}  - the maximum number of internal references&lt;/li&gt;</span><a href="#l344"></a>
<span id="l345">         *     &lt;li&gt;maxbytes={@code value} - the maximum number of bytes in the input stream&lt;/li&gt;</span><a href="#l345"></a>
<span id="l346">         *     &lt;li&gt;maxarray={@code value} - the maximum array length allowed&lt;/li&gt;</span><a href="#l346"></a>
<span id="l347">         * &lt;/ul&gt;</span><a href="#l347"></a>
<span id="l348">         * &lt;p&gt;</span><a href="#l348"></a>
<span id="l349">         * Other patterns match or reject class or package name</span><a href="#l349"></a>
<span id="l350">         * as returned from {@link Class#getName() Class.getName()}.</span><a href="#l350"></a>
<span id="l351">         * Note that for arrays the element type is used in the pattern,</span><a href="#l351"></a>
<span id="l352">         * not the array type.</span><a href="#l352"></a>
<span id="l353">         * &lt;ul&gt;</span><a href="#l353"></a>
<span id="l354">         * &lt;li&gt;If the pattern starts with &quot;!&quot;, the class is rejected if the remaining pattern is matched;</span><a href="#l354"></a>
<span id="l355">         *     otherwise the class is allowed if the pattern matches.</span><a href="#l355"></a>
<span id="l356">         * &lt;li&gt;If the pattern ends with &quot;.**&quot; it matches any class in the package and all subpackages.</span><a href="#l356"></a>
<span id="l357">         * &lt;li&gt;If the pattern ends with &quot;.*&quot; it matches any class in the package.</span><a href="#l357"></a>
<span id="l358">         * &lt;li&gt;If the pattern ends with &quot;*&quot;, it matches any class with the pattern as a prefix.</span><a href="#l358"></a>
<span id="l359">         * &lt;li&gt;If the pattern is equal to the class name, it matches.</span><a href="#l359"></a>
<span id="l360">         * &lt;li&gt;Otherwise, the pattern is not matched.</span><a href="#l360"></a>
<span id="l361">         * &lt;/ul&gt;</span><a href="#l361"></a>
<span id="l362">         * &lt;p&gt;</span><a href="#l362"></a>
<span id="l363">         * The resulting filter performs the limit checks and then</span><a href="#l363"></a>
<span id="l364">         * tries to match the class, if any. If any of the limits are exceeded,</span><a href="#l364"></a>
<span id="l365">         * the filter returns {@link Status#REJECTED Status.REJECTED}.</span><a href="#l365"></a>
<span id="l366">         * If the class is an array type, the class to be matched is the element type.</span><a href="#l366"></a>
<span id="l367">         * Arrays of any number of dimensions are treated the same as the element type.</span><a href="#l367"></a>
<span id="l368">         * For example, a pattern of &quot;{@code !example.Foo}&quot;,</span><a href="#l368"></a>
<span id="l369">         * rejects creation of any instance or array of {@code example.Foo}.</span><a href="#l369"></a>
<span id="l370">         * The first pattern that matches, working from left to right, determines</span><a href="#l370"></a>
<span id="l371">         * the {@link Status#ALLOWED Status.ALLOWED}</span><a href="#l371"></a>
<span id="l372">         * or {@link Status#REJECTED Status.REJECTED} result.</span><a href="#l372"></a>
<span id="l373">         * If nothing matches, the result is {@link Status#UNDECIDED Status.UNDECIDED}.</span><a href="#l373"></a>
<span id="l374">         *</span><a href="#l374"></a>
<span id="l375">         * @param pattern the pattern string to parse; not null</span><a href="#l375"></a>
<span id="l376">         * @return a filter to check a class being deserialized; may be null;</span><a href="#l376"></a>
<span id="l377">         *          {@code null} if no patterns</span><a href="#l377"></a>
<span id="l378">         * @throws IllegalArgumentException</span><a href="#l378"></a>
<span id="l379">         *                if a limit is missing the name, or the long value</span><a href="#l379"></a>
<span id="l380">         *                is not a number or is negative,</span><a href="#l380"></a>
<span id="l381">         *                or if the package is missing for &quot;.*&quot; and &quot;.**&quot;</span><a href="#l381"></a>
<span id="l382">         */</span><a href="#l382"></a>
<span id="l383">        public static ObjectInputFilter createFilter(String pattern) {</span><a href="#l383"></a>
<span id="l384">            Objects.requireNonNull(pattern, &quot;pattern&quot;);</span><a href="#l384"></a>
<span id="l385">            return Global.createFilter(pattern);</span><a href="#l385"></a>
<span id="l386">        }</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">        /**</span><a href="#l388"></a>
<span id="l389">         * Implementation of ObjectInputFilter that performs the checks of</span><a href="#l389"></a>
<span id="l390">         * the process-wide serialization filter. If configured, it will be</span><a href="#l390"></a>
<span id="l391">         * used for all ObjectInputStreams that do not set their own filters.</span><a href="#l391"></a>
<span id="l392">         *</span><a href="#l392"></a>
<span id="l393">         */</span><a href="#l393"></a>
<span id="l394">        final static class Global implements ObjectInputFilter {</span><a href="#l394"></a>
<span id="l395">            /**</span><a href="#l395"></a>
<span id="l396">             * The pattern used to create the filter.</span><a href="#l396"></a>
<span id="l397">             */</span><a href="#l397"></a>
<span id="l398">            private final String pattern;</span><a href="#l398"></a>
<span id="l399">            /**</span><a href="#l399"></a>
<span id="l400">             * The list of class filters.</span><a href="#l400"></a>
<span id="l401">             */</span><a href="#l401"></a>
<span id="l402">            private final List&lt;GlobalFilter&gt; filters;</span><a href="#l402"></a>
<span id="l403">            /**</span><a href="#l403"></a>
<span id="l404">             * Maximum allowed bytes in the stream.</span><a href="#l404"></a>
<span id="l405">             */</span><a href="#l405"></a>
<span id="l406">            private long maxStreamBytes;</span><a href="#l406"></a>
<span id="l407">            /**</span><a href="#l407"></a>
<span id="l408">             * Maximum depth of the graph allowed.</span><a href="#l408"></a>
<span id="l409">             */</span><a href="#l409"></a>
<span id="l410">            private long maxDepth;</span><a href="#l410"></a>
<span id="l411">            /**</span><a href="#l411"></a>
<span id="l412">             * Maximum number of references in a graph.</span><a href="#l412"></a>
<span id="l413">             */</span><a href="#l413"></a>
<span id="l414">            private long maxReferences;</span><a href="#l414"></a>
<span id="l415">            /**</span><a href="#l415"></a>
<span id="l416">             * Maximum length of any array.</span><a href="#l416"></a>
<span id="l417">             */</span><a href="#l417"></a>
<span id="l418">            private long maxArrayLength;</span><a href="#l418"></a>
<span id="l419"></span><a href="#l419"></a>
<span id="l420">            /**</span><a href="#l420"></a>
<span id="l421">             * Returns an ObjectInputFilter from a string of patterns.</span><a href="#l421"></a>
<span id="l422">             *</span><a href="#l422"></a>
<span id="l423">             * @param pattern the pattern string to parse</span><a href="#l423"></a>
<span id="l424">             * @return a filter to check a class being deserialized; not null</span><a href="#l424"></a>
<span id="l425">             * @throws IllegalArgumentException if the parameter is malformed</span><a href="#l425"></a>
<span id="l426">             *                if the pattern is missing the name, the long value</span><a href="#l426"></a>
<span id="l427">             *                is not a number or is negative.</span><a href="#l427"></a>
<span id="l428">             */</span><a href="#l428"></a>
<span id="l429">            static ObjectInputFilter createFilter(String pattern) {</span><a href="#l429"></a>
<span id="l430">                Global filter = new Global(pattern);</span><a href="#l430"></a>
<span id="l431">                return filter.isEmpty() ? null : filter;</span><a href="#l431"></a>
<span id="l432">            }</span><a href="#l432"></a>
<span id="l433"></span><a href="#l433"></a>
<span id="l434">            /**</span><a href="#l434"></a>
<span id="l435">             * Construct a new filter from the pattern String.</span><a href="#l435"></a>
<span id="l436">             *</span><a href="#l436"></a>
<span id="l437">             * @param pattern a pattern string of filters</span><a href="#l437"></a>
<span id="l438">             * @throws IllegalArgumentException if the pattern is malformed</span><a href="#l438"></a>
<span id="l439">             */</span><a href="#l439"></a>
<span id="l440">            private Global(String pattern) {</span><a href="#l440"></a>
<span id="l441">                this.pattern = pattern;</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">                maxArrayLength = Long.MAX_VALUE; // Default values are unlimited</span><a href="#l443"></a>
<span id="l444">                maxDepth = Long.MAX_VALUE;</span><a href="#l444"></a>
<span id="l445">                maxReferences = Long.MAX_VALUE;</span><a href="#l445"></a>
<span id="l446">                maxStreamBytes = Long.MAX_VALUE;</span><a href="#l446"></a>
<span id="l447"></span><a href="#l447"></a>
<span id="l448">                String[] patterns = pattern.split(&quot;;&quot;);</span><a href="#l448"></a>
<span id="l449">                filters = new ArrayList&lt;&gt;(patterns.length);</span><a href="#l449"></a>
<span id="l450">                for (int i = 0; i &lt; patterns.length; i++) {</span><a href="#l450"></a>
<span id="l451">                    String p = patterns[i];</span><a href="#l451"></a>
<span id="l452">                    int nameLen = p.length();</span><a href="#l452"></a>
<span id="l453">                    if (nameLen == 0) {</span><a href="#l453"></a>
<span id="l454">                        continue;</span><a href="#l454"></a>
<span id="l455">                    }</span><a href="#l455"></a>
<span id="l456">                    if (parseLimit(p)) {</span><a href="#l456"></a>
<span id="l457">                        // If the pattern contained a limit setting, i.e. type=value</span><a href="#l457"></a>
<span id="l458">                        continue;</span><a href="#l458"></a>
<span id="l459">                    }</span><a href="#l459"></a>
<span id="l460">                    boolean negate = p.charAt(0) == '!';</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">                    if (p.indexOf('/') &gt;= 0) {</span><a href="#l462"></a>
<span id="l463">                        throw new IllegalArgumentException(&quot;invalid character \&quot;/\&quot; in: \&quot;&quot; + pattern + &quot;\&quot;&quot;);</span><a href="#l463"></a>
<span id="l464">                    }</span><a href="#l464"></a>
<span id="l465"></span><a href="#l465"></a>
<span id="l466">                    if (p.endsWith(&quot;*&quot;)) {</span><a href="#l466"></a>
<span id="l467">                        // Wildcard cases</span><a href="#l467"></a>
<span id="l468">                        if (p.endsWith(&quot;.*&quot;)) {</span><a href="#l468"></a>
<span id="l469">                            // Pattern is a package name with a wildcard</span><a href="#l469"></a>
<span id="l470">                            final String pkg = p.substring(negate ? 1 : 0, nameLen - 1);</span><a href="#l470"></a>
<span id="l471">                            if (pkg.length() &lt; 2) {</span><a href="#l471"></a>
<span id="l472">                                throw new IllegalArgumentException(&quot;package missing in: \&quot;&quot; + pattern + &quot;\&quot;&quot;);</span><a href="#l472"></a>
<span id="l473">                            }</span><a href="#l473"></a>
<span id="l474">                            if (negate) {</span><a href="#l474"></a>
<span id="l475">                                // A Function that fails if the class starts with the pattern, otherwise don't care</span><a href="#l475"></a>
<span id="l476">                                filters.add(new GlobalFilter() {</span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">                                    @Override</span><a href="#l478"></a>
<span id="l479">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l479"></a>
<span id="l480">                                        return matchesPackage(c, pkg) ? Status.REJECTED : Status.UNDECIDED;</span><a href="#l480"></a>
<span id="l481">                                    }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">                                });</span><a href="#l483"></a>
<span id="l484">                            } else {</span><a href="#l484"></a>
<span id="l485">                                // A Function that succeeds if the class starts with the pattern, otherwise don't care</span><a href="#l485"></a>
<span id="l486">                                filters.add(new GlobalFilter() {</span><a href="#l486"></a>
<span id="l487"></span><a href="#l487"></a>
<span id="l488">                                    @Override</span><a href="#l488"></a>
<span id="l489">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l489"></a>
<span id="l490">                                        return matchesPackage(c, pkg) ? Status.ALLOWED : Status.UNDECIDED;</span><a href="#l490"></a>
<span id="l491">                                    }</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">                                });</span><a href="#l493"></a>
<span id="l494">                            }</span><a href="#l494"></a>
<span id="l495">                        } else if (p.endsWith(&quot;.**&quot;)) {</span><a href="#l495"></a>
<span id="l496">                            // Pattern is a package prefix with a double wildcard</span><a href="#l496"></a>
<span id="l497">                            final String pkgs = p.substring(negate ? 1 : 0, nameLen - 2);</span><a href="#l497"></a>
<span id="l498">                            if (pkgs.length() &lt; 2) {</span><a href="#l498"></a>
<span id="l499">                                throw new IllegalArgumentException(&quot;package missing in: \&quot;&quot; + pattern + &quot;\&quot;&quot;);</span><a href="#l499"></a>
<span id="l500">                            }</span><a href="#l500"></a>
<span id="l501">                            if (negate) {</span><a href="#l501"></a>
<span id="l502">                                // A Function that fails if the class starts with the pattern, otherwise don't care</span><a href="#l502"></a>
<span id="l503">                                filters.add(new GlobalFilter() {</span><a href="#l503"></a>
<span id="l504"></span><a href="#l504"></a>
<span id="l505">                                    @Override</span><a href="#l505"></a>
<span id="l506">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l506"></a>
<span id="l507">                                        return c.getName().startsWith(pkgs) ? Status.REJECTED : Status.UNDECIDED;</span><a href="#l507"></a>
<span id="l508">                                    }</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">                                });</span><a href="#l510"></a>
<span id="l511">                            } else {</span><a href="#l511"></a>
<span id="l512">                                // A Function that succeeds if the class starts with the pattern, otherwise don't care</span><a href="#l512"></a>
<span id="l513">                                filters.add(new GlobalFilter() {</span><a href="#l513"></a>
<span id="l514"></span><a href="#l514"></a>
<span id="l515">                                    @Override</span><a href="#l515"></a>
<span id="l516">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l516"></a>
<span id="l517">                                        return c.getName().startsWith(pkgs) ? Status.ALLOWED : Status.UNDECIDED;</span><a href="#l517"></a>
<span id="l518">                                    }</span><a href="#l518"></a>
<span id="l519"></span><a href="#l519"></a>
<span id="l520">                                });</span><a href="#l520"></a>
<span id="l521">                            }</span><a href="#l521"></a>
<span id="l522">                        } else {</span><a href="#l522"></a>
<span id="l523">                            // Pattern is a classname (possibly empty) with a trailing wildcard</span><a href="#l523"></a>
<span id="l524">                            final String className = p.substring(negate ? 1 : 0, nameLen - 1);</span><a href="#l524"></a>
<span id="l525">                            if (negate) {</span><a href="#l525"></a>
<span id="l526">                                // A Function that fails if the class starts with the pattern, otherwise don't care</span><a href="#l526"></a>
<span id="l527">                                filters.add(new GlobalFilter() {</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">                                    @Override</span><a href="#l529"></a>
<span id="l530">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l530"></a>
<span id="l531">                                        return c.getName().startsWith(className) ? Status.REJECTED : Status.UNDECIDED;</span><a href="#l531"></a>
<span id="l532">                                    }</span><a href="#l532"></a>
<span id="l533"></span><a href="#l533"></a>
<span id="l534">                                });</span><a href="#l534"></a>
<span id="l535">                            } else {</span><a href="#l535"></a>
<span id="l536">                                // A Function that succeeds if the class starts with the pattern, otherwise don't care</span><a href="#l536"></a>
<span id="l537">                                filters.add(new GlobalFilter() {</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">                                    @Override</span><a href="#l539"></a>
<span id="l540">                                    public Status apply(Class&lt;?&gt; c) {</span><a href="#l540"></a>
<span id="l541">                                        return c.getName().startsWith(className) ? Status.ALLOWED : Status.UNDECIDED;</span><a href="#l541"></a>
<span id="l542">                                    }</span><a href="#l542"></a>
<span id="l543"></span><a href="#l543"></a>
<span id="l544">                                });</span><a href="#l544"></a>
<span id="l545">                            }</span><a href="#l545"></a>
<span id="l546">                        }</span><a href="#l546"></a>
<span id="l547">                    } else {</span><a href="#l547"></a>
<span id="l548">                        final String name = p.substring(negate ? 1 : 0);</span><a href="#l548"></a>
<span id="l549">                        if (name.isEmpty()) {</span><a href="#l549"></a>
<span id="l550">                            throw new IllegalArgumentException(&quot;class or package missing in: \&quot;&quot; + pattern + &quot;\&quot;&quot;);</span><a href="#l550"></a>
<span id="l551">                        }</span><a href="#l551"></a>
<span id="l552">                        // Pattern is a class name</span><a href="#l552"></a>
<span id="l553">                        if (negate) {</span><a href="#l553"></a>
<span id="l554">                            // A Function that fails if the class equals the pattern, otherwise don't care</span><a href="#l554"></a>
<span id="l555">                            filters.add(new GlobalFilter() {</span><a href="#l555"></a>
<span id="l556"></span><a href="#l556"></a>
<span id="l557">                                @Override</span><a href="#l557"></a>
<span id="l558">                                public Status apply(Class&lt;?&gt; c) {</span><a href="#l558"></a>
<span id="l559">                                    return c.getName().equals(name) ? Status.REJECTED : Status.UNDECIDED;</span><a href="#l559"></a>
<span id="l560">                                }</span><a href="#l560"></a>
<span id="l561"></span><a href="#l561"></a>
<span id="l562">                            });</span><a href="#l562"></a>
<span id="l563">                        } else {</span><a href="#l563"></a>
<span id="l564">                            // A Function that succeeds if the class equals the pattern, otherwise don't care</span><a href="#l564"></a>
<span id="l565">                            filters.add(new GlobalFilter() {</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">                                @Override</span><a href="#l567"></a>
<span id="l568">                                public Status apply(Class&lt;?&gt; c) {</span><a href="#l568"></a>
<span id="l569">                                    return c.getName().equals(name) ? Status.ALLOWED : Status.UNDECIDED;</span><a href="#l569"></a>
<span id="l570">                                }</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">                            });</span><a href="#l572"></a>
<span id="l573">                        }</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">                    }</span><a href="#l575"></a>
<span id="l576">                }</span><a href="#l576"></a>
<span id="l577">            }</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">            /**</span><a href="#l579"></a>
<span id="l580">             * Returns if this filter has any checks.</span><a href="#l580"></a>
<span id="l581">             * @return {@code true} if the filter has any checks, {@code false} otherwise</span><a href="#l581"></a>
<span id="l582">             */</span><a href="#l582"></a>
<span id="l583">            private boolean isEmpty() {</span><a href="#l583"></a>
<span id="l584">                return filters.isEmpty() &amp;&amp;</span><a href="#l584"></a>
<span id="l585">                        maxArrayLength == Long.MAX_VALUE &amp;&amp;</span><a href="#l585"></a>
<span id="l586">                        maxDepth == Long.MAX_VALUE &amp;&amp;</span><a href="#l586"></a>
<span id="l587">                        maxReferences == Long.MAX_VALUE &amp;&amp;</span><a href="#l587"></a>
<span id="l588">                        maxStreamBytes == Long.MAX_VALUE;</span><a href="#l588"></a>
<span id="l589">            }</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">            /**</span><a href="#l591"></a>
<span id="l592">             * Parse out a limit for one of maxarray, maxdepth, maxbytes, maxreferences.</span><a href="#l592"></a>
<span id="l593">             *</span><a href="#l593"></a>
<span id="l594">             * @param pattern a string with a type name, '=' and a value</span><a href="#l594"></a>
<span id="l595">             * @return {@code true} if a limit was parsed, else {@code false}</span><a href="#l595"></a>
<span id="l596">             * @throws IllegalArgumentException if the pattern is missing</span><a href="#l596"></a>
<span id="l597">             *                the name, the Long value is not a number or is negative.</span><a href="#l597"></a>
<span id="l598">             */</span><a href="#l598"></a>
<span id="l599">            private boolean parseLimit(String pattern) {</span><a href="#l599"></a>
<span id="l600">                int eqNdx = pattern.indexOf('=');</span><a href="#l600"></a>
<span id="l601">                if (eqNdx &lt; 0) {</span><a href="#l601"></a>
<span id="l602">                    // not a limit pattern</span><a href="#l602"></a>
<span id="l603">                    return false;</span><a href="#l603"></a>
<span id="l604">                }</span><a href="#l604"></a>
<span id="l605">                String valueString = pattern.substring(eqNdx + 1);</span><a href="#l605"></a>
<span id="l606">                if (pattern.startsWith(&quot;maxdepth=&quot;)) {</span><a href="#l606"></a>
<span id="l607">                    maxDepth = parseValue(valueString);</span><a href="#l607"></a>
<span id="l608">                } else if (pattern.startsWith(&quot;maxarray=&quot;)) {</span><a href="#l608"></a>
<span id="l609">                    maxArrayLength = parseValue(valueString);</span><a href="#l609"></a>
<span id="l610">                } else if (pattern.startsWith(&quot;maxrefs=&quot;)) {</span><a href="#l610"></a>
<span id="l611">                    maxReferences = parseValue(valueString);</span><a href="#l611"></a>
<span id="l612">                } else if (pattern.startsWith(&quot;maxbytes=&quot;)) {</span><a href="#l612"></a>
<span id="l613">                    maxStreamBytes = parseValue(valueString);</span><a href="#l613"></a>
<span id="l614">                } else {</span><a href="#l614"></a>
<span id="l615">                    throw new IllegalArgumentException(&quot;unknown limit: &quot; + pattern.substring(0, eqNdx));</span><a href="#l615"></a>
<span id="l616">                }</span><a href="#l616"></a>
<span id="l617">                return true;</span><a href="#l617"></a>
<span id="l618">            }</span><a href="#l618"></a>
<span id="l619"></span><a href="#l619"></a>
<span id="l620">            /**</span><a href="#l620"></a>
<span id="l621">             * Parse the value of a limit and check that it is non-negative.</span><a href="#l621"></a>
<span id="l622">             * @param string inputstring</span><a href="#l622"></a>
<span id="l623">             * @return the parsed value</span><a href="#l623"></a>
<span id="l624">             * @throws IllegalArgumentException if parsing the value fails or the value is negative</span><a href="#l624"></a>
<span id="l625">             */</span><a href="#l625"></a>
<span id="l626">            private static long parseValue(String string) throws IllegalArgumentException {</span><a href="#l626"></a>
<span id="l627">                // Parse a Long from after the '=' to the end</span><a href="#l627"></a>
<span id="l628">                long value = Long.parseLong(string);</span><a href="#l628"></a>
<span id="l629">                if (value &lt; 0) {</span><a href="#l629"></a>
<span id="l630">                    throw new IllegalArgumentException(&quot;negative limit: &quot; + string);</span><a href="#l630"></a>
<span id="l631">                }</span><a href="#l631"></a>
<span id="l632">                return value;</span><a href="#l632"></a>
<span id="l633">            }</span><a href="#l633"></a>
<span id="l634"></span><a href="#l634"></a>
<span id="l635">            /**</span><a href="#l635"></a>
<span id="l636">             * {@inheritDoc}</span><a href="#l636"></a>
<span id="l637">             */</span><a href="#l637"></a>
<span id="l638">            @Override</span><a href="#l638"></a>
<span id="l639">            public Status checkInput(FilterInfo filterInfo) {</span><a href="#l639"></a>
<span id="l640">                if (filterInfo.references() &lt; 0</span><a href="#l640"></a>
<span id="l641">                        || filterInfo.depth() &lt; 0</span><a href="#l641"></a>
<span id="l642">                        || filterInfo.streamBytes() &lt; 0</span><a href="#l642"></a>
<span id="l643">                        || filterInfo.references() &gt; maxReferences</span><a href="#l643"></a>
<span id="l644">                        || filterInfo.depth() &gt; maxDepth</span><a href="#l644"></a>
<span id="l645">                        || filterInfo.streamBytes() &gt; maxStreamBytes) {</span><a href="#l645"></a>
<span id="l646">                    return Status.REJECTED;</span><a href="#l646"></a>
<span id="l647">                }</span><a href="#l647"></a>
<span id="l648"></span><a href="#l648"></a>
<span id="l649">                Class&lt;?&gt; clazz = filterInfo.serialClass();</span><a href="#l649"></a>
<span id="l650">                if (clazz != null) {</span><a href="#l650"></a>
<span id="l651">                    if (clazz.isArray()) {</span><a href="#l651"></a>
<span id="l652">                        if (filterInfo.arrayLength() &gt;= 0 &amp;&amp; filterInfo.arrayLength() &gt; maxArrayLength) {</span><a href="#l652"></a>
<span id="l653">                            // array length is too big</span><a href="#l653"></a>
<span id="l654">                            return Status.REJECTED;</span><a href="#l654"></a>
<span id="l655">                        }</span><a href="#l655"></a>
<span id="l656">                        do {</span><a href="#l656"></a>
<span id="l657">                            // Arrays are decided based on the component type</span><a href="#l657"></a>
<span id="l658">                            clazz = clazz.getComponentType();</span><a href="#l658"></a>
<span id="l659">                        } while (clazz.isArray());</span><a href="#l659"></a>
<span id="l660">                    }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">                    if (clazz.isPrimitive())  {</span><a href="#l662"></a>
<span id="l663">                        // Primitive types are undecided; let someone else decide</span><a href="#l663"></a>
<span id="l664">                        return Status.UNDECIDED;</span><a href="#l664"></a>
<span id="l665">                    } else {</span><a href="#l665"></a>
<span id="l666">                        // Find any filter that allowed or rejected the class</span><a href="#l666"></a>
<span id="l667">                        return findFirstNonUnDecided(clazz);</span><a href="#l667"></a>
<span id="l668">                    }</span><a href="#l668"></a>
<span id="l669">                }</span><a href="#l669"></a>
<span id="l670">                return Status.UNDECIDED;</span><a href="#l670"></a>
<span id="l671">            }</span><a href="#l671"></a>
<span id="l672"></span><a href="#l672"></a>
<span id="l673">            /**</span><a href="#l673"></a>
<span id="l674">             * Finds the status of the first global filter that if applied</span><a href="#l674"></a>
<span id="l675">             * to the class returns STATUS.ALLOWED or STATUS.REJECTED or</span><a href="#l675"></a>
<span id="l676">             * Status.UNDECIDED if there is no such global filter.</span><a href="#l676"></a>
<span id="l677">             *</span><a href="#l677"></a>
<span id="l678">             * @param clazz   a class</span><a href="#l678"></a>
<span id="l679">             *</span><a href="#l679"></a>
<span id="l680">             * @return The first status with a decision of an applied global</span><a href="#l680"></a>
<span id="l681">             *         filter or Status.UNDECIDED if no such global filter</span><a href="#l681"></a>
<span id="l682">             *         existed.</span><a href="#l682"></a>
<span id="l683">             */</span><a href="#l683"></a>
<span id="l684">            private Status findFirstNonUnDecided(Class&lt;?&gt; clazz) {</span><a href="#l684"></a>
<span id="l685">                for (GlobalFilter f: filters) {</span><a href="#l685"></a>
<span id="l686">                    Status s = f.apply(clazz);</span><a href="#l686"></a>
<span id="l687">                    if (s != Status.UNDECIDED) {</span><a href="#l687"></a>
<span id="l688">                        return s;</span><a href="#l688"></a>
<span id="l689">                    }</span><a href="#l689"></a>
<span id="l690">                }</span><a href="#l690"></a>
<span id="l691">                return Status.UNDECIDED;</span><a href="#l691"></a>
<span id="l692">            }</span><a href="#l692"></a>
<span id="l693"></span><a href="#l693"></a>
<span id="l694">            /**</span><a href="#l694"></a>
<span id="l695">             * Returns {@code true} if the class is in the package.</span><a href="#l695"></a>
<span id="l696">             *</span><a href="#l696"></a>
<span id="l697">             * @param c   a class</span><a href="#l697"></a>
<span id="l698">             * @param pkg a package name (including the trailing &quot;.&quot;)</span><a href="#l698"></a>
<span id="l699">             * @return {@code true} if the class is in the package,</span><a href="#l699"></a>
<span id="l700">             * otherwise {@code false}</span><a href="#l700"></a>
<span id="l701">             */</span><a href="#l701"></a>
<span id="l702">            private static boolean matchesPackage(Class&lt;?&gt; c, String pkg) {</span><a href="#l702"></a>
<span id="l703">                String n = c.getName();</span><a href="#l703"></a>
<span id="l704">                return n.startsWith(pkg) &amp;&amp; n.lastIndexOf('.') == pkg.length() - 1;</span><a href="#l704"></a>
<span id="l705">            }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">            /**</span><a href="#l707"></a>
<span id="l708">             * Returns the pattern used to create this filter.</span><a href="#l708"></a>
<span id="l709">             * @return the pattern used to create this filter</span><a href="#l709"></a>
<span id="l710">             */</span><a href="#l710"></a>
<span id="l711">            @Override</span><a href="#l711"></a>
<span id="l712">            public String toString() {</span><a href="#l712"></a>
<span id="l713">                return pattern;</span><a href="#l713"></a>
<span id="l714">            }</span><a href="#l714"></a>
<span id="l715"></span><a href="#l715"></a>
<span id="l716">            static interface GlobalFilter {</span><a href="#l716"></a>
<span id="l717">                Status apply(Class&lt;?&gt; cl);</span><a href="#l717"></a>
<span id="l718">            }</span><a href="#l718"></a>
<span id="l719">        }</span><a href="#l719"></a>
<span id="l720">    }</span><a href="#l720"></a>
<span id="l721">}</span><a href="#l721"></a></pre>
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

