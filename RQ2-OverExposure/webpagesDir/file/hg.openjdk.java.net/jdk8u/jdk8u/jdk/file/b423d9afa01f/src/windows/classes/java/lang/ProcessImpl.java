<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: b423d9afa01f src/windows/classes/java/lang/ProcessImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/b423d9afa01f">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/b423d9afa01f">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/b423d9afa01f">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/b423d9afa01f/src/windows/classes/java/lang/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/windows/classes/java/lang/ProcessImpl.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/b423d9afa01f/src/windows/classes/java/lang/ProcessImpl.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/b423d9afa01f/src/windows/classes/java/lang/ProcessImpl.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/b423d9afa01f/src/windows/classes/java/lang/ProcessImpl.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/b423d9afa01f/src/windows/classes/java/lang/ProcessImpl.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/b423d9afa01f/src/windows/classes/java/lang/ProcessImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/windows/classes/java/lang/ProcessImpl.java @ 14392:b423d9afa01f</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8250568: Less ambiguous processing
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 04 Feb 2021 00:32:43 +0300</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/eb13744577fb/src/windows/classes/java/lang/ProcessImpl.java">eb13744577fb</a> </td>
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
<span id="l2"> * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.lang;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.File;</span><a href="#l29"></a>
<span id="l30">import java.io.InputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.OutputStream;</span><a href="#l31"></a>
<span id="l32">import java.io.FileInputStream;</span><a href="#l32"></a>
<span id="l33">import java.io.FileOutputStream;</span><a href="#l33"></a>
<span id="l34">import java.io.FileDescriptor;</span><a href="#l34"></a>
<span id="l35">import java.io.BufferedInputStream;</span><a href="#l35"></a>
<span id="l36">import java.io.BufferedOutputStream;</span><a href="#l36"></a>
<span id="l37">import java.lang.ProcessBuilder.Redirect;</span><a href="#l37"></a>
<span id="l38">import java.security.AccessController;</span><a href="#l38"></a>
<span id="l39">import java.security.PrivilegedAction;</span><a href="#l39"></a>
<span id="l40">import java.util.ArrayList;</span><a href="#l40"></a>
<span id="l41">import java.util.Locale;</span><a href="#l41"></a>
<span id="l42">import java.util.concurrent.TimeUnit;</span><a href="#l42"></a>
<span id="l43">import java.util.regex.Matcher;</span><a href="#l43"></a>
<span id="l44">import java.util.regex.Pattern;</span><a href="#l44"></a>
<span id="l45">import sun.security.action.GetPropertyAction;</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">/* This class is for the exclusive use of ProcessBuilder.start() to</span><a href="#l47"></a>
<span id="l48"> * create new processes.</span><a href="#l48"></a>
<span id="l49"> *</span><a href="#l49"></a>
<span id="l50"> * @author Martin Buchholz</span><a href="#l50"></a>
<span id="l51"> * @since   1.5</span><a href="#l51"></a>
<span id="l52"> */</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">final class ProcessImpl extends Process {</span><a href="#l54"></a>
<span id="l55">    private static final sun.misc.JavaIOFileDescriptorAccess fdAccess</span><a href="#l55"></a>
<span id="l56">        = sun.misc.SharedSecrets.getJavaIOFileDescriptorAccess();</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    /**</span><a href="#l58"></a>
<span id="l59">     * Open a file for writing. If {@code append} is {@code true} then the file</span><a href="#l59"></a>
<span id="l60">     * is opened for atomic append directly and a FileOutputStream constructed</span><a href="#l60"></a>
<span id="l61">     * with the resulting handle. This is because a FileOutputStream created</span><a href="#l61"></a>
<span id="l62">     * to append to a file does not open the file in a manner that guarantees</span><a href="#l62"></a>
<span id="l63">     * that writes by the child process will be atomic.</span><a href="#l63"></a>
<span id="l64">     */</span><a href="#l64"></a>
<span id="l65">    private static FileOutputStream newFileOutputStream(File f, boolean append)</span><a href="#l65"></a>
<span id="l66">        throws IOException</span><a href="#l66"></a>
<span id="l67">    {</span><a href="#l67"></a>
<span id="l68">        if (append) {</span><a href="#l68"></a>
<span id="l69">            String path = f.getPath();</span><a href="#l69"></a>
<span id="l70">            SecurityManager sm = System.getSecurityManager();</span><a href="#l70"></a>
<span id="l71">            if (sm != null)</span><a href="#l71"></a>
<span id="l72">                sm.checkWrite(path);</span><a href="#l72"></a>
<span id="l73">            long handle = openForAtomicAppend(path);</span><a href="#l73"></a>
<span id="l74">            final FileDescriptor fd = new FileDescriptor();</span><a href="#l74"></a>
<span id="l75">            fdAccess.setHandle(fd, handle);</span><a href="#l75"></a>
<span id="l76">            return AccessController.doPrivileged(</span><a href="#l76"></a>
<span id="l77">                new PrivilegedAction&lt;FileOutputStream&gt;() {</span><a href="#l77"></a>
<span id="l78">                    public FileOutputStream run() {</span><a href="#l78"></a>
<span id="l79">                        return new FileOutputStream(fd);</span><a href="#l79"></a>
<span id="l80">                    }</span><a href="#l80"></a>
<span id="l81">                }</span><a href="#l81"></a>
<span id="l82">            );</span><a href="#l82"></a>
<span id="l83">        } else {</span><a href="#l83"></a>
<span id="l84">            return new FileOutputStream(f);</span><a href="#l84"></a>
<span id="l85">        }</span><a href="#l85"></a>
<span id="l86">    }</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">    // System-dependent portion of ProcessBuilder.start()</span><a href="#l88"></a>
<span id="l89">    static Process start(String cmdarray[],</span><a href="#l89"></a>
<span id="l90">                         java.util.Map&lt;String,String&gt; environment,</span><a href="#l90"></a>
<span id="l91">                         String dir,</span><a href="#l91"></a>
<span id="l92">                         ProcessBuilder.Redirect[] redirects,</span><a href="#l92"></a>
<span id="l93">                         boolean redirectErrorStream)</span><a href="#l93"></a>
<span id="l94">        throws IOException</span><a href="#l94"></a>
<span id="l95">    {</span><a href="#l95"></a>
<span id="l96">        String envblock = ProcessEnvironment.toEnvironmentBlock(environment);</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">        FileInputStream  f0 = null;</span><a href="#l98"></a>
<span id="l99">        FileOutputStream f1 = null;</span><a href="#l99"></a>
<span id="l100">        FileOutputStream f2 = null;</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">        try {</span><a href="#l102"></a>
<span id="l103">            long[] stdHandles;</span><a href="#l103"></a>
<span id="l104">            if (redirects == null) {</span><a href="#l104"></a>
<span id="l105">                stdHandles = new long[] { -1L, -1L, -1L };</span><a href="#l105"></a>
<span id="l106">            } else {</span><a href="#l106"></a>
<span id="l107">                stdHandles = new long[3];</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">                if (redirects[0] == Redirect.PIPE)</span><a href="#l109"></a>
<span id="l110">                    stdHandles[0] = -1L;</span><a href="#l110"></a>
<span id="l111">                else if (redirects[0] == Redirect.INHERIT)</span><a href="#l111"></a>
<span id="l112">                    stdHandles[0] = fdAccess.getHandle(FileDescriptor.in);</span><a href="#l112"></a>
<span id="l113">                else {</span><a href="#l113"></a>
<span id="l114">                    f0 = new FileInputStream(redirects[0].file());</span><a href="#l114"></a>
<span id="l115">                    stdHandles[0] = fdAccess.getHandle(f0.getFD());</span><a href="#l115"></a>
<span id="l116">                }</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">                if (redirects[1] == Redirect.PIPE)</span><a href="#l118"></a>
<span id="l119">                    stdHandles[1] = -1L;</span><a href="#l119"></a>
<span id="l120">                else if (redirects[1] == Redirect.INHERIT)</span><a href="#l120"></a>
<span id="l121">                    stdHandles[1] = fdAccess.getHandle(FileDescriptor.out);</span><a href="#l121"></a>
<span id="l122">                else {</span><a href="#l122"></a>
<span id="l123">                    f1 = newFileOutputStream(redirects[1].file(),</span><a href="#l123"></a>
<span id="l124">                                             redirects[1].append());</span><a href="#l124"></a>
<span id="l125">                    stdHandles[1] = fdAccess.getHandle(f1.getFD());</span><a href="#l125"></a>
<span id="l126">                }</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128">                if (redirects[2] == Redirect.PIPE)</span><a href="#l128"></a>
<span id="l129">                    stdHandles[2] = -1L;</span><a href="#l129"></a>
<span id="l130">                else if (redirects[2] == Redirect.INHERIT)</span><a href="#l130"></a>
<span id="l131">                    stdHandles[2] = fdAccess.getHandle(FileDescriptor.err);</span><a href="#l131"></a>
<span id="l132">                else {</span><a href="#l132"></a>
<span id="l133">                    f2 = newFileOutputStream(redirects[2].file(),</span><a href="#l133"></a>
<span id="l134">                                             redirects[2].append());</span><a href="#l134"></a>
<span id="l135">                    stdHandles[2] = fdAccess.getHandle(f2.getFD());</span><a href="#l135"></a>
<span id="l136">                }</span><a href="#l136"></a>
<span id="l137">            }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">            return new ProcessImpl(cmdarray, envblock, dir,</span><a href="#l139"></a>
<span id="l140">                                   stdHandles, redirectErrorStream);</span><a href="#l140"></a>
<span id="l141">        } finally {</span><a href="#l141"></a>
<span id="l142">            // In theory, close() can throw IOException</span><a href="#l142"></a>
<span id="l143">            // (although it is rather unlikely to happen here)</span><a href="#l143"></a>
<span id="l144">            try { if (f0 != null) f0.close(); }</span><a href="#l144"></a>
<span id="l145">            finally {</span><a href="#l145"></a>
<span id="l146">                try { if (f1 != null) f1.close(); }</span><a href="#l146"></a>
<span id="l147">                finally { if (f2 != null) f2.close(); }</span><a href="#l147"></a>
<span id="l148">            }</span><a href="#l148"></a>
<span id="l149">        }</span><a href="#l149"></a>
<span id="l150"></span><a href="#l150"></a>
<span id="l151">    }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    private static class LazyPattern {</span><a href="#l153"></a>
<span id="l154">        // Escape-support version:</span><a href="#l154"></a>
<span id="l155">        //    &quot;(\&quot;)((?:\\\\\\1|.)+?)\\1|([^\\s\&quot;]+)&quot;;</span><a href="#l155"></a>
<span id="l156">        private static final Pattern PATTERN =</span><a href="#l156"></a>
<span id="l157">            Pattern.compile(&quot;[^\\s\&quot;]+|\&quot;[^\&quot;]*\&quot;&quot;);</span><a href="#l157"></a>
<span id="l158">    };</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">    /* Parses the command string parameter into the executable name and</span><a href="#l160"></a>
<span id="l161">     * program arguments.</span><a href="#l161"></a>
<span id="l162">     *</span><a href="#l162"></a>
<span id="l163">     * The command string is broken into tokens. The token separator is a space</span><a href="#l163"></a>
<span id="l164">     * or quota character. The space inside quotation is not a token separator.</span><a href="#l164"></a>
<span id="l165">     * There are no escape sequences.</span><a href="#l165"></a>
<span id="l166">     */</span><a href="#l166"></a>
<span id="l167">    private static String[] getTokensFromCommand(String command) {</span><a href="#l167"></a>
<span id="l168">        ArrayList&lt;String&gt; matchList = new ArrayList&lt;&gt;(8);</span><a href="#l168"></a>
<span id="l169">        Matcher regexMatcher = LazyPattern.PATTERN.matcher(command);</span><a href="#l169"></a>
<span id="l170">        while (regexMatcher.find())</span><a href="#l170"></a>
<span id="l171">            matchList.add(regexMatcher.group());</span><a href="#l171"></a>
<span id="l172">        return matchList.toArray(new String[matchList.size()]);</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    private static final int VERIFICATION_CMD_BAT = 0;</span><a href="#l175"></a>
<span id="l176">    private static final int VERIFICATION_WIN32 = 1;</span><a href="#l176"></a>
<span id="l177">    private static final int VERIFICATION_WIN32_SAFE = 2; // inside quotes not allowed</span><a href="#l177"></a>
<span id="l178">    private static final int VERIFICATION_LEGACY = 3;</span><a href="#l178"></a>
<span id="l179">    // See Command shell overview for documentation of special characters.</span><a href="#l179"></a>
<span id="l180">    // https://docs.microsoft.com/en-us/previous-versions/windows/it-pro/windows-xp/bb490954(v=technet.10)</span><a href="#l180"></a>
<span id="l181">    private static final char ESCAPE_VERIFICATION[][] = {</span><a href="#l181"></a>
<span id="l182">        // We guarantee the only command file execution for implicit [cmd.exe] run.</span><a href="#l182"></a>
<span id="l183">        //    http://technet.microsoft.com/en-us/library/bb490954.aspx</span><a href="#l183"></a>
<span id="l184">        {' ', '\t', '\&quot;', '&lt;', '&gt;', '&amp;', '|', '^'},</span><a href="#l184"></a>
<span id="l185">        {' ', '\t', '\&quot;', '&lt;', '&gt;'},</span><a href="#l185"></a>
<span id="l186">        {' ', '\t', '\&quot;', '&lt;', '&gt;'},</span><a href="#l186"></a>
<span id="l187">        {' ', '\t'}</span><a href="#l187"></a>
<span id="l188">    };</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">    private static String createCommandLine(int verificationType,</span><a href="#l190"></a>
<span id="l191">                                     final String executablePath,</span><a href="#l191"></a>
<span id="l192">                                     final String cmd[])</span><a href="#l192"></a>
<span id="l193">    {</span><a href="#l193"></a>
<span id="l194">        StringBuilder cmdbuf = new StringBuilder(80);</span><a href="#l194"></a>
<span id="l195"></span><a href="#l195"></a>
<span id="l196">        cmdbuf.append(executablePath);</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">        for (int i = 1; i &lt; cmd.length; ++i) {</span><a href="#l198"></a>
<span id="l199">            cmdbuf.append(' ');</span><a href="#l199"></a>
<span id="l200">            String s = cmd[i];</span><a href="#l200"></a>
<span id="l201">            if (needsEscaping(verificationType, s)) {</span><a href="#l201"></a>
<span id="l202">                cmdbuf.append('&quot;');</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">                if (verificationType == VERIFICATION_WIN32_SAFE) {</span><a href="#l204"></a>
<span id="l205">                    // Insert the argument, adding '\' to quote any interior quotes</span><a href="#l205"></a>
<span id="l206">                    int length = s.length();</span><a href="#l206"></a>
<span id="l207">                    for (int j = 0; j &lt; length; j++) {</span><a href="#l207"></a>
<span id="l208">                        char c = s.charAt(j);</span><a href="#l208"></a>
<span id="l209">                        if (c == DOUBLEQUOTE) {</span><a href="#l209"></a>
<span id="l210">                            int count = countLeadingBackslash(verificationType, s, j);</span><a href="#l210"></a>
<span id="l211">                            while (count-- &gt; 0) {</span><a href="#l211"></a>
<span id="l212">                                cmdbuf.append(BACKSLASH);   // double the number of backslashes</span><a href="#l212"></a>
<span id="l213">                            }</span><a href="#l213"></a>
<span id="l214">                            cmdbuf.append(BACKSLASH);       // backslash to quote the quote</span><a href="#l214"></a>
<span id="l215">                        }</span><a href="#l215"></a>
<span id="l216">                        cmdbuf.append(c);</span><a href="#l216"></a>
<span id="l217">                    }</span><a href="#l217"></a>
<span id="l218">                } else {</span><a href="#l218"></a>
<span id="l219">                    cmdbuf.append(s);</span><a href="#l219"></a>
<span id="l220">                }</span><a href="#l220"></a>
<span id="l221">                // The code protects the [java.exe] and console command line</span><a href="#l221"></a>
<span id="l222">                // parser, that interprets the [\&quot;] combination as an escape</span><a href="#l222"></a>
<span id="l223">                // sequence for the [&quot;] char.</span><a href="#l223"></a>
<span id="l224">                //     http://msdn.microsoft.com/en-us/library/17w5ykft.aspx</span><a href="#l224"></a>
<span id="l225">                //</span><a href="#l225"></a>
<span id="l226">                // If the argument is an FS path, doubling of the tail [\]</span><a href="#l226"></a>
<span id="l227">                // char is not a problem for non-console applications.</span><a href="#l227"></a>
<span id="l228">                //</span><a href="#l228"></a>
<span id="l229">                // The [\&quot;] sequence is not an escape sequence for the [cmd.exe]</span><a href="#l229"></a>
<span id="l230">                // command line parser. The case of the [&quot;&quot;] tail escape</span><a href="#l230"></a>
<span id="l231">                // sequence could not be realized due to the argument validation</span><a href="#l231"></a>
<span id="l232">                // procedure.</span><a href="#l232"></a>
<span id="l233">                int count = countLeadingBackslash(verificationType, s, s.length());</span><a href="#l233"></a>
<span id="l234">                while (count-- &gt; 0) {</span><a href="#l234"></a>
<span id="l235">                    cmdbuf.append(BACKSLASH);   // double the number of backslashes</span><a href="#l235"></a>
<span id="l236">                }</span><a href="#l236"></a>
<span id="l237">                cmdbuf.append('&quot;');</span><a href="#l237"></a>
<span id="l238">            } else {</span><a href="#l238"></a>
<span id="l239">                cmdbuf.append(s);</span><a href="#l239"></a>
<span id="l240">            }</span><a href="#l240"></a>
<span id="l241">        }</span><a href="#l241"></a>
<span id="l242">        return cmdbuf.toString();</span><a href="#l242"></a>
<span id="l243">    }</span><a href="#l243"></a>
<span id="l244"></span><a href="#l244"></a>
<span id="l245">    /**</span><a href="#l245"></a>
<span id="l246">     * Return the argument without quotes (1st and last) if properly quoted, else the arg.</span><a href="#l246"></a>
<span id="l247">     * A properly quoted string has first and last characters as quote and</span><a href="#l247"></a>
<span id="l248">     * the last quote is not escaped.</span><a href="#l248"></a>
<span id="l249">     * @param str a string</span><a href="#l249"></a>
<span id="l250">     * @return the string without quotes</span><a href="#l250"></a>
<span id="l251">     */</span><a href="#l251"></a>
<span id="l252">    private static String unQuote(String str) {</span><a href="#l252"></a>
<span id="l253">        if (!str.startsWith(&quot;\&quot;&quot;) || !str.endsWith(&quot;\&quot;&quot;) || str.length() &lt; 2)</span><a href="#l253"></a>
<span id="l254">            return str;    // no beginning or ending quote, or too short not quoted</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">        if (str.endsWith(&quot;\\\&quot;&quot;)) {</span><a href="#l256"></a>
<span id="l257">            return str;    // not properly quoted, treat as unquoted</span><a href="#l257"></a>
<span id="l258">        }</span><a href="#l258"></a>
<span id="l259">        // Strip leading and trailing quotes</span><a href="#l259"></a>
<span id="l260">        return str.substring(1, str.length() - 1);</span><a href="#l260"></a>
<span id="l261">    }</span><a href="#l261"></a>
<span id="l262"></span><a href="#l262"></a>
<span id="l263">    private static boolean needsEscaping(int verificationType, String arg) {</span><a href="#l263"></a>
<span id="l264">        if (arg.isEmpty())</span><a href="#l264"></a>
<span id="l265">            return true;            // Empty string is to be quoted</span><a href="#l265"></a>
<span id="l266"></span><a href="#l266"></a>
<span id="l267">        // Switch off MS heuristic for internal [&quot;].</span><a href="#l267"></a>
<span id="l268">        // Please, use the explicit [cmd.exe] call</span><a href="#l268"></a>
<span id="l269">        // if you need the internal [&quot;].</span><a href="#l269"></a>
<span id="l270">        //    Example: &quot;cmd.exe&quot;, &quot;/C&quot;, &quot;Extended_MS_Syntax&quot;</span><a href="#l270"></a>
<span id="l271"></span><a href="#l271"></a>
<span id="l272">        // For [.exe] or [.com] file the unpaired/internal [&quot;]</span><a href="#l272"></a>
<span id="l273">        // in the argument is not a problem.</span><a href="#l273"></a>
<span id="l274">        String unquotedArg = unQuote(arg);</span><a href="#l274"></a>
<span id="l275">        boolean argIsQuoted = !arg.equals(unquotedArg);</span><a href="#l275"></a>
<span id="l276">        boolean embeddedQuote = unquotedArg.indexOf(DOUBLEQUOTE) &gt;= 0;</span><a href="#l276"></a>
<span id="l277"></span><a href="#l277"></a>
<span id="l278">        switch (verificationType) {</span><a href="#l278"></a>
<span id="l279">            case VERIFICATION_CMD_BAT:</span><a href="#l279"></a>
<span id="l280">                if (embeddedQuote) {</span><a href="#l280"></a>
<span id="l281">                    throw new IllegalArgumentException(&quot;Argument has embedded quote, &quot; +</span><a href="#l281"></a>
<span id="l282">                            &quot;use the explicit CMD.EXE call.&quot;);</span><a href="#l282"></a>
<span id="l283">                }</span><a href="#l283"></a>
<span id="l284">                break;  // break determine whether to quote</span><a href="#l284"></a>
<span id="l285">            case VERIFICATION_WIN32_SAFE:</span><a href="#l285"></a>
<span id="l286">                if (argIsQuoted &amp;&amp; embeddedQuote)  {</span><a href="#l286"></a>
<span id="l287">                    throw new IllegalArgumentException(&quot;Malformed argument has embedded quote: &quot;</span><a href="#l287"></a>
<span id="l288">                            + unquotedArg);</span><a href="#l288"></a>
<span id="l289">                }</span><a href="#l289"></a>
<span id="l290">                break;</span><a href="#l290"></a>
<span id="l291">            default:</span><a href="#l291"></a>
<span id="l292">                break;</span><a href="#l292"></a>
<span id="l293">        }</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">        if (!argIsQuoted) {</span><a href="#l295"></a>
<span id="l296">            char testEscape[] = ESCAPE_VERIFICATION[verificationType];</span><a href="#l296"></a>
<span id="l297">            for (int i = 0; i &lt; testEscape.length; ++i) {</span><a href="#l297"></a>
<span id="l298">                if (arg.indexOf(testEscape[i]) &gt;= 0) {</span><a href="#l298"></a>
<span id="l299">                    return true;</span><a href="#l299"></a>
<span id="l300">                }</span><a href="#l300"></a>
<span id="l301">            }</span><a href="#l301"></a>
<span id="l302">        }</span><a href="#l302"></a>
<span id="l303">        return false;</span><a href="#l303"></a>
<span id="l304">    }</span><a href="#l304"></a>
<span id="l305"></span><a href="#l305"></a>
<span id="l306">    private static String getExecutablePath(String path)</span><a href="#l306"></a>
<span id="l307">        throws IOException</span><a href="#l307"></a>
<span id="l308">    {</span><a href="#l308"></a>
<span id="l309">        String name = unQuote(path);</span><a href="#l309"></a>
<span id="l310">        if (name.indexOf(DOUBLEQUOTE) &gt;= 0) {</span><a href="#l310"></a>
<span id="l311">            throw new IllegalArgumentException(&quot;Executable name has embedded quote, &quot; +</span><a href="#l311"></a>
<span id="l312">                    &quot;split the arguments: &quot; + name);</span><a href="#l312"></a>
<span id="l313">        }</span><a href="#l313"></a>
<span id="l314">        // Win32 CreateProcess requires path to be normalized</span><a href="#l314"></a>
<span id="l315">        File fileToRun = new File(name);</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">        // From the [CreateProcess] function documentation:</span><a href="#l317"></a>
<span id="l318">        //</span><a href="#l318"></a>
<span id="l319">        // &quot;If the file name does not contain an extension, .exe is appended.</span><a href="#l319"></a>
<span id="l320">        // Therefore, if the file name extension is .com, this parameter</span><a href="#l320"></a>
<span id="l321">        // must include the .com extension. If the file name ends in</span><a href="#l321"></a>
<span id="l322">        // a period (.) with no extension, or if the file name contains a path,</span><a href="#l322"></a>
<span id="l323">        // .exe is not appended.&quot;</span><a href="#l323"></a>
<span id="l324">        //</span><a href="#l324"></a>
<span id="l325">        // &quot;If the file name !does not contain a directory path!,</span><a href="#l325"></a>
<span id="l326">        // the system searches for the executable file in the following</span><a href="#l326"></a>
<span id="l327">        // sequence:...&quot;</span><a href="#l327"></a>
<span id="l328">        //</span><a href="#l328"></a>
<span id="l329">        // In practice ANY non-existent path is extended by [.exe] extension</span><a href="#l329"></a>
<span id="l330">        // in the [CreateProcess] function with the only exception:</span><a href="#l330"></a>
<span id="l331">        // the path ends by (.)</span><a href="#l331"></a>
<span id="l332"></span><a href="#l332"></a>
<span id="l333">        return fileToRun.getPath();</span><a href="#l333"></a>
<span id="l334">    }</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">    /**</span><a href="#l336"></a>
<span id="l337">     * An executable is any program that is an EXE or does not have an extension</span><a href="#l337"></a>
<span id="l338">     * and the Windows createProcess will be looking for .exe.</span><a href="#l338"></a>
<span id="l339">     * The comparison is case insensitive based on the name.</span><a href="#l339"></a>
<span id="l340">     * @param executablePath the executable file</span><a href="#l340"></a>
<span id="l341">     * @return true if the path ends in .exe or does not have an extension.</span><a href="#l341"></a>
<span id="l342">     */</span><a href="#l342"></a>
<span id="l343">    private boolean isExe(String executablePath) {</span><a href="#l343"></a>
<span id="l344">        File file = new File(executablePath);</span><a href="#l344"></a>
<span id="l345">        String upName = file.getName().toUpperCase(Locale.ROOT);</span><a href="#l345"></a>
<span id="l346">        return (upName.endsWith(&quot;.EXE&quot;) || upName.indexOf('.') &lt; 0);</span><a href="#l346"></a>
<span id="l347">    }</span><a href="#l347"></a>
<span id="l348"></span><a href="#l348"></a>
<span id="l349">    // Old version that can be bypassed</span><a href="#l349"></a>
<span id="l350">    private boolean isShellFile(String executablePath) {</span><a href="#l350"></a>
<span id="l351">        String upPath = executablePath.toUpperCase();</span><a href="#l351"></a>
<span id="l352">        return (upPath.endsWith(&quot;.CMD&quot;) || upPath.endsWith(&quot;.BAT&quot;));</span><a href="#l352"></a>
<span id="l353">    }</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">    private String quoteString(String arg) {</span><a href="#l355"></a>
<span id="l356">        StringBuilder argbuf = new StringBuilder(arg.length() + 2);</span><a href="#l356"></a>
<span id="l357">        return argbuf.append('&quot;').append(arg).append('&quot;').toString();</span><a href="#l357"></a>
<span id="l358">    }</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">    // Count backslashes before start index of string.</span><a href="#l360"></a>
<span id="l361">    // .bat files don't include backslashes as part of the quote</span><a href="#l361"></a>
<span id="l362">    private static int countLeadingBackslash(int verificationType,</span><a href="#l362"></a>
<span id="l363">                                             CharSequence input, int start) {</span><a href="#l363"></a>
<span id="l364">        if (verificationType == VERIFICATION_CMD_BAT)</span><a href="#l364"></a>
<span id="l365">            return 0;</span><a href="#l365"></a>
<span id="l366">        int j;</span><a href="#l366"></a>
<span id="l367">        for (j = start - 1; j &gt;= 0 &amp;&amp; input.charAt(j) == BACKSLASH; j--) {</span><a href="#l367"></a>
<span id="l368">            // just scanning backwards</span><a href="#l368"></a>
<span id="l369">        }</span><a href="#l369"></a>
<span id="l370">        return (start - 1) - j;  // number of BACKSLASHES</span><a href="#l370"></a>
<span id="l371">    }</span><a href="#l371"></a>
<span id="l372"></span><a href="#l372"></a>
<span id="l373">    private static final char DOUBLEQUOTE = '\&quot;';</span><a href="#l373"></a>
<span id="l374">    private static final char BACKSLASH = '\\';</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">    private long handle = 0;</span><a href="#l376"></a>
<span id="l377">    private OutputStream stdin_stream;</span><a href="#l377"></a>
<span id="l378">    private InputStream stdout_stream;</span><a href="#l378"></a>
<span id="l379">    private InputStream stderr_stream;</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">    private ProcessImpl(String cmd[],</span><a href="#l381"></a>
<span id="l382">                        final String envblock,</span><a href="#l382"></a>
<span id="l383">                        final String path,</span><a href="#l383"></a>
<span id="l384">                        final long[] stdHandles,</span><a href="#l384"></a>
<span id="l385">                        final boolean redirectErrorStream)</span><a href="#l385"></a>
<span id="l386">        throws IOException</span><a href="#l386"></a>
<span id="l387">    {</span><a href="#l387"></a>
<span id="l388">        String cmdstr;</span><a href="#l388"></a>
<span id="l389">        final SecurityManager security = System.getSecurityManager();</span><a href="#l389"></a>
<span id="l390">        String propertyValue = GetPropertyAction.</span><a href="#l390"></a>
<span id="l391">                privilegedGetProperty(&quot;jdk.lang.Process.allowAmbiguousCommands&quot;);</span><a href="#l391"></a>
<span id="l392">        final String value = propertyValue != null ? propertyValue</span><a href="#l392"></a>
<span id="l393">                                                   : (security == null ? &quot;true&quot; : &quot;false&quot;);</span><a href="#l393"></a>
<span id="l394">        final boolean allowAmbiguousCommands = !&quot;false&quot;.equalsIgnoreCase(value);</span><a href="#l394"></a>
<span id="l395"></span><a href="#l395"></a>
<span id="l396">        if (allowAmbiguousCommands &amp;&amp; security == null) {</span><a href="#l396"></a>
<span id="l397">            // Legacy mode.</span><a href="#l397"></a>
<span id="l398"></span><a href="#l398"></a>
<span id="l399">            // Normalize path if possible.</span><a href="#l399"></a>
<span id="l400">            String executablePath = new File(cmd[0]).getPath();</span><a href="#l400"></a>
<span id="l401"></span><a href="#l401"></a>
<span id="l402">            // No worry about internal, unpaired [&quot;], and redirection/piping.</span><a href="#l402"></a>
<span id="l403">            if (needsEscaping(VERIFICATION_LEGACY, executablePath) )</span><a href="#l403"></a>
<span id="l404">                executablePath = quoteString(executablePath);</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">            cmdstr = createCommandLine(</span><a href="#l406"></a>
<span id="l407">                //legacy mode doesn't worry about extended verification</span><a href="#l407"></a>
<span id="l408">                VERIFICATION_LEGACY,</span><a href="#l408"></a>
<span id="l409">                executablePath,</span><a href="#l409"></a>
<span id="l410">                cmd);</span><a href="#l410"></a>
<span id="l411">        } else {</span><a href="#l411"></a>
<span id="l412">            String executablePath;</span><a href="#l412"></a>
<span id="l413">            try {</span><a href="#l413"></a>
<span id="l414">                executablePath = getExecutablePath(cmd[0]);</span><a href="#l414"></a>
<span id="l415">            } catch (IllegalArgumentException e) {</span><a href="#l415"></a>
<span id="l416">                // Workaround for the calls like</span><a href="#l416"></a>
<span id="l417">                // Runtime.getRuntime().exec(&quot;\&quot;C:\\Program Files\\foo\&quot; bar&quot;)</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a>
<span id="l419">                // No chance to avoid CMD/BAT injection, except to do the work</span><a href="#l419"></a>
<span id="l420">                // right from the beginning. Otherwise we have too many corner</span><a href="#l420"></a>
<span id="l421">                // cases from</span><a href="#l421"></a>
<span id="l422">                //    Runtime.getRuntime().exec(String[] cmd [, ...])</span><a href="#l422"></a>
<span id="l423">                // calls with internal [&quot;] and escape sequences.</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">                // Restore original command line.</span><a href="#l425"></a>
<span id="l426">                StringBuilder join = new StringBuilder();</span><a href="#l426"></a>
<span id="l427">                // terminal space in command line is ok</span><a href="#l427"></a>
<span id="l428">                for (String s : cmd)</span><a href="#l428"></a>
<span id="l429">                    join.append(s).append(' ');</span><a href="#l429"></a>
<span id="l430"></span><a href="#l430"></a>
<span id="l431">                // Parse the command line again.</span><a href="#l431"></a>
<span id="l432">                cmd = getTokensFromCommand(join.toString());</span><a href="#l432"></a>
<span id="l433">                executablePath = getExecutablePath(cmd[0]);</span><a href="#l433"></a>
<span id="l434"></span><a href="#l434"></a>
<span id="l435">                // Check new executable name once more</span><a href="#l435"></a>
<span id="l436">                if (security != null)</span><a href="#l436"></a>
<span id="l437">                    security.checkExec(executablePath);</span><a href="#l437"></a>
<span id="l438">            }</span><a href="#l438"></a>
<span id="l439"></span><a href="#l439"></a>
<span id="l440">            // Quotation protects from interpretation of the [path] argument as</span><a href="#l440"></a>
<span id="l441">            // start of longer path with spaces. Quotation has no influence to</span><a href="#l441"></a>
<span id="l442">            // [.exe] extension heuristic.</span><a href="#l442"></a>
<span id="l443">            boolean isShell = allowAmbiguousCommands ? isShellFile(executablePath)</span><a href="#l443"></a>
<span id="l444">                    : !isExe(executablePath);</span><a href="#l444"></a>
<span id="l445">            cmdstr = createCommandLine(</span><a href="#l445"></a>
<span id="l446">                    // We need the extended verification procedures</span><a href="#l446"></a>
<span id="l447">                    isShell ? VERIFICATION_CMD_BAT</span><a href="#l447"></a>
<span id="l448">                            : (allowAmbiguousCommands ? VERIFICATION_WIN32 : VERIFICATION_WIN32_SAFE),</span><a href="#l448"></a>
<span id="l449">                    quoteString(executablePath),</span><a href="#l449"></a>
<span id="l450">                    cmd);</span><a href="#l450"></a>
<span id="l451">        }</span><a href="#l451"></a>
<span id="l452"></span><a href="#l452"></a>
<span id="l453">        handle = create(cmdstr, envblock, path,</span><a href="#l453"></a>
<span id="l454">                        stdHandles, redirectErrorStream);</span><a href="#l454"></a>
<span id="l455"></span><a href="#l455"></a>
<span id="l456">        java.security.AccessController.doPrivileged(</span><a href="#l456"></a>
<span id="l457">        new java.security.PrivilegedAction&lt;Void&gt;() {</span><a href="#l457"></a>
<span id="l458">        public Void run() {</span><a href="#l458"></a>
<span id="l459">            if (stdHandles[0] == -1L)</span><a href="#l459"></a>
<span id="l460">                stdin_stream = ProcessBuilder.NullOutputStream.INSTANCE;</span><a href="#l460"></a>
<span id="l461">            else {</span><a href="#l461"></a>
<span id="l462">                FileDescriptor stdin_fd = new FileDescriptor();</span><a href="#l462"></a>
<span id="l463">                fdAccess.setHandle(stdin_fd, stdHandles[0]);</span><a href="#l463"></a>
<span id="l464">                stdin_stream = new BufferedOutputStream(</span><a href="#l464"></a>
<span id="l465">                    new FileOutputStream(stdin_fd));</span><a href="#l465"></a>
<span id="l466">            }</span><a href="#l466"></a>
<span id="l467"></span><a href="#l467"></a>
<span id="l468">            if (stdHandles[1] == -1L)</span><a href="#l468"></a>
<span id="l469">                stdout_stream = ProcessBuilder.NullInputStream.INSTANCE;</span><a href="#l469"></a>
<span id="l470">            else {</span><a href="#l470"></a>
<span id="l471">                FileDescriptor stdout_fd = new FileDescriptor();</span><a href="#l471"></a>
<span id="l472">                fdAccess.setHandle(stdout_fd, stdHandles[1]);</span><a href="#l472"></a>
<span id="l473">                stdout_stream = new BufferedInputStream(</span><a href="#l473"></a>
<span id="l474">                    new FileInputStream(stdout_fd));</span><a href="#l474"></a>
<span id="l475">            }</span><a href="#l475"></a>
<span id="l476"></span><a href="#l476"></a>
<span id="l477">            if (stdHandles[2] == -1L)</span><a href="#l477"></a>
<span id="l478">                stderr_stream = ProcessBuilder.NullInputStream.INSTANCE;</span><a href="#l478"></a>
<span id="l479">            else {</span><a href="#l479"></a>
<span id="l480">                FileDescriptor stderr_fd = new FileDescriptor();</span><a href="#l480"></a>
<span id="l481">                fdAccess.setHandle(stderr_fd, stdHandles[2]);</span><a href="#l481"></a>
<span id="l482">                stderr_stream = new FileInputStream(stderr_fd);</span><a href="#l482"></a>
<span id="l483">            }</span><a href="#l483"></a>
<span id="l484"></span><a href="#l484"></a>
<span id="l485">            return null; }});</span><a href="#l485"></a>
<span id="l486">    }</span><a href="#l486"></a>
<span id="l487"></span><a href="#l487"></a>
<span id="l488">    public OutputStream getOutputStream() {</span><a href="#l488"></a>
<span id="l489">        return stdin_stream;</span><a href="#l489"></a>
<span id="l490">    }</span><a href="#l490"></a>
<span id="l491"></span><a href="#l491"></a>
<span id="l492">    public InputStream getInputStream() {</span><a href="#l492"></a>
<span id="l493">        return stdout_stream;</span><a href="#l493"></a>
<span id="l494">    }</span><a href="#l494"></a>
<span id="l495"></span><a href="#l495"></a>
<span id="l496">    public InputStream getErrorStream() {</span><a href="#l496"></a>
<span id="l497">        return stderr_stream;</span><a href="#l497"></a>
<span id="l498">    }</span><a href="#l498"></a>
<span id="l499"></span><a href="#l499"></a>
<span id="l500">    protected void finalize() {</span><a href="#l500"></a>
<span id="l501">        closeHandle(handle);</span><a href="#l501"></a>
<span id="l502">    }</span><a href="#l502"></a>
<span id="l503"></span><a href="#l503"></a>
<span id="l504">    private static final int STILL_ACTIVE = getStillActive();</span><a href="#l504"></a>
<span id="l505">    private static native int getStillActive();</span><a href="#l505"></a>
<span id="l506"></span><a href="#l506"></a>
<span id="l507">    public int exitValue() {</span><a href="#l507"></a>
<span id="l508">        int exitCode = getExitCodeProcess(handle);</span><a href="#l508"></a>
<span id="l509">        if (exitCode == STILL_ACTIVE)</span><a href="#l509"></a>
<span id="l510">            throw new IllegalThreadStateException(&quot;process has not exited&quot;);</span><a href="#l510"></a>
<span id="l511">        return exitCode;</span><a href="#l511"></a>
<span id="l512">    }</span><a href="#l512"></a>
<span id="l513">    private static native int getExitCodeProcess(long handle);</span><a href="#l513"></a>
<span id="l514"></span><a href="#l514"></a>
<span id="l515">    public int waitFor() throws InterruptedException {</span><a href="#l515"></a>
<span id="l516">        waitForInterruptibly(handle);</span><a href="#l516"></a>
<span id="l517">        if (Thread.interrupted())</span><a href="#l517"></a>
<span id="l518">            throw new InterruptedException();</span><a href="#l518"></a>
<span id="l519">        return exitValue();</span><a href="#l519"></a>
<span id="l520">    }</span><a href="#l520"></a>
<span id="l521"></span><a href="#l521"></a>
<span id="l522">    private static native void waitForInterruptibly(long handle);</span><a href="#l522"></a>
<span id="l523"></span><a href="#l523"></a>
<span id="l524">    @Override</span><a href="#l524"></a>
<span id="l525">    public boolean waitFor(long timeout, TimeUnit unit)</span><a href="#l525"></a>
<span id="l526">        throws InterruptedException</span><a href="#l526"></a>
<span id="l527">    {</span><a href="#l527"></a>
<span id="l528">        long remainingNanos = unit.toNanos(timeout);    // throw NPE before other conditions</span><a href="#l528"></a>
<span id="l529">        if (getExitCodeProcess(handle) != STILL_ACTIVE) return true;</span><a href="#l529"></a>
<span id="l530">        if (timeout &lt;= 0) return false;</span><a href="#l530"></a>
<span id="l531"></span><a href="#l531"></a>
<span id="l532">        long deadline = System.nanoTime() + remainingNanos;</span><a href="#l532"></a>
<span id="l533">        do {</span><a href="#l533"></a>
<span id="l534">            // Round up to next millisecond</span><a href="#l534"></a>
<span id="l535">            long msTimeout = TimeUnit.NANOSECONDS.toMillis(remainingNanos + 999_999L);</span><a href="#l535"></a>
<span id="l536">            if (msTimeout &lt; 0) {</span><a href="#l536"></a>
<span id="l537">                // if wraps around then wait a long while</span><a href="#l537"></a>
<span id="l538">                msTimeout = Integer.MAX_VALUE;</span><a href="#l538"></a>
<span id="l539">            }</span><a href="#l539"></a>
<span id="l540">            waitForTimeoutInterruptibly(handle, msTimeout);</span><a href="#l540"></a>
<span id="l541">            if (Thread.interrupted())</span><a href="#l541"></a>
<span id="l542">                throw new InterruptedException();</span><a href="#l542"></a>
<span id="l543">            if (getExitCodeProcess(handle) != STILL_ACTIVE) {</span><a href="#l543"></a>
<span id="l544">                return true;</span><a href="#l544"></a>
<span id="l545">            }</span><a href="#l545"></a>
<span id="l546">            remainingNanos = deadline - System.nanoTime();</span><a href="#l546"></a>
<span id="l547">        } while (remainingNanos &gt; 0);</span><a href="#l547"></a>
<span id="l548"></span><a href="#l548"></a>
<span id="l549">        return (getExitCodeProcess(handle) != STILL_ACTIVE);</span><a href="#l549"></a>
<span id="l550">    }</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">    private static native void waitForTimeoutInterruptibly(</span><a href="#l552"></a>
<span id="l553">        long handle, long timeoutMillis);</span><a href="#l553"></a>
<span id="l554"></span><a href="#l554"></a>
<span id="l555">    public void destroy() { terminateProcess(handle); }</span><a href="#l555"></a>
<span id="l556"></span><a href="#l556"></a>
<span id="l557">    @Override</span><a href="#l557"></a>
<span id="l558">    public Process destroyForcibly() {</span><a href="#l558"></a>
<span id="l559">        destroy();</span><a href="#l559"></a>
<span id="l560">        return this;</span><a href="#l560"></a>
<span id="l561">    }</span><a href="#l561"></a>
<span id="l562"></span><a href="#l562"></a>
<span id="l563">    private static native void terminateProcess(long handle);</span><a href="#l563"></a>
<span id="l564"></span><a href="#l564"></a>
<span id="l565">    @Override</span><a href="#l565"></a>
<span id="l566">    public boolean isAlive() {</span><a href="#l566"></a>
<span id="l567">        return isProcessAlive(handle);</span><a href="#l567"></a>
<span id="l568">    }</span><a href="#l568"></a>
<span id="l569"></span><a href="#l569"></a>
<span id="l570">    private static native boolean isProcessAlive(long handle);</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">    /**</span><a href="#l572"></a>
<span id="l573">     * Create a process using the win32 function CreateProcess.</span><a href="#l573"></a>
<span id="l574">     * The method is synchronized due to MS kb315939 problem.</span><a href="#l574"></a>
<span id="l575">     * All native handles should restore the inherit flag at the end of call.</span><a href="#l575"></a>
<span id="l576">     *</span><a href="#l576"></a>
<span id="l577">     * @param cmdstr the Windows command line</span><a href="#l577"></a>
<span id="l578">     * @param envblock NUL-separated, double-NUL-terminated list of</span><a href="#l578"></a>
<span id="l579">     *        environment strings in VAR=VALUE form</span><a href="#l579"></a>
<span id="l580">     * @param dir the working directory of the process, or null if</span><a href="#l580"></a>
<span id="l581">     *        inheriting the current directory from the parent process</span><a href="#l581"></a>
<span id="l582">     * @param stdHandles array of windows HANDLEs.  Indexes 0, 1, and</span><a href="#l582"></a>
<span id="l583">     *        2 correspond to standard input, standard output and</span><a href="#l583"></a>
<span id="l584">     *        standard error, respectively.  On input, a value of -1</span><a href="#l584"></a>
<span id="l585">     *        means to create a pipe to connect child and parent</span><a href="#l585"></a>
<span id="l586">     *        processes.  On output, a value which is not -1 is the</span><a href="#l586"></a>
<span id="l587">     *        parent pipe handle corresponding to the pipe which has</span><a href="#l587"></a>
<span id="l588">     *        been created.  An element of this array is -1 on input</span><a href="#l588"></a>
<span id="l589">     *        if and only if it is &lt;em&gt;not&lt;/em&gt; -1 on output.</span><a href="#l589"></a>
<span id="l590">     * @param redirectErrorStream redirectErrorStream attribute</span><a href="#l590"></a>
<span id="l591">     * @return the native subprocess HANDLE returned by CreateProcess</span><a href="#l591"></a>
<span id="l592">     */</span><a href="#l592"></a>
<span id="l593">    private static synchronized native long create(String cmdstr,</span><a href="#l593"></a>
<span id="l594">                                      String envblock,</span><a href="#l594"></a>
<span id="l595">                                      String dir,</span><a href="#l595"></a>
<span id="l596">                                      long[] stdHandles,</span><a href="#l596"></a>
<span id="l597">                                      boolean redirectErrorStream)</span><a href="#l597"></a>
<span id="l598">        throws IOException;</span><a href="#l598"></a>
<span id="l599"></span><a href="#l599"></a>
<span id="l600">    /**</span><a href="#l600"></a>
<span id="l601">     * Opens a file for atomic append. The file is created if it doesn't</span><a href="#l601"></a>
<span id="l602">     * already exist.</span><a href="#l602"></a>
<span id="l603">     *</span><a href="#l603"></a>
<span id="l604">     * @param file the file to open or create</span><a href="#l604"></a>
<span id="l605">     * @return the native HANDLE</span><a href="#l605"></a>
<span id="l606">     */</span><a href="#l606"></a>
<span id="l607">    private static native long openForAtomicAppend(String path)</span><a href="#l607"></a>
<span id="l608">        throws IOException;</span><a href="#l608"></a>
<span id="l609"></span><a href="#l609"></a>
<span id="l610">    private static native boolean closeHandle(long handle);</span><a href="#l610"></a>
<span id="l611">}</span><a href="#l611"></a></pre>
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

