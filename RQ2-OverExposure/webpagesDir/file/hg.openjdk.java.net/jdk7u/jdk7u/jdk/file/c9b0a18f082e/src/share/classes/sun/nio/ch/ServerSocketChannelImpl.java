<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/c9b0a18f082e">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/c9b0a18f082e">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/c9b0a18f082e">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/c9b0a18f082e/src/share/classes/sun/nio/ch/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java @ 8936:c9b0a18f082e</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8196956: (ch) More channels cleanup
8231795: Enhance datagram socket support
Reviewed-by: rriggs, prappo, bpb</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#97;&#110;&#98;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 22 Jan 2020 06:51:23 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/ff8c7bdc6405/src/share/classes/sun/nio/ch/ServerSocketChannelImpl.java">ff8c7bdc6405</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2012, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.nio.ch;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.FileDescriptor;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.net.*;</span><a href="#l30"></a>
<span id="l31">import java.nio.channels.*;</span><a href="#l31"></a>
<span id="l32">import java.nio.channels.spi.*;</span><a href="#l32"></a>
<span id="l33">import java.util.*;</span><a href="#l33"></a>
<span id="l34">import sun.net.NetHooks;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">/**</span><a href="#l37"></a>
<span id="l38"> * An implementation of ServerSocketChannels</span><a href="#l38"></a>
<span id="l39"> */</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">class ServerSocketChannelImpl</span><a href="#l41"></a>
<span id="l42">    extends ServerSocketChannel</span><a href="#l42"></a>
<span id="l43">    implements SelChImpl</span><a href="#l43"></a>
<span id="l44">{</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">    // Used to make native close and configure calls</span><a href="#l46"></a>
<span id="l47">    private static NativeDispatcher nd;</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">    // Our file descriptor</span><a href="#l49"></a>
<span id="l50">    private final FileDescriptor fd;</span><a href="#l50"></a>
<span id="l51">    private final int fdVal;</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">    // ID of native thread currently blocked in this channel, for signalling</span><a href="#l53"></a>
<span id="l54">    private volatile long thread = 0;</span><a href="#l54"></a>
<span id="l55"></span><a href="#l55"></a>
<span id="l56">    // Lock held by thread currently blocked in this channel</span><a href="#l56"></a>
<span id="l57">    private final Object lock = new Object();</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">    // Lock held by any thread that modifies the state fields declared below</span><a href="#l59"></a>
<span id="l60">    // DO NOT invoke a blocking I/O operation while holding this lock!</span><a href="#l60"></a>
<span id="l61">    private final Object stateLock = new Object();</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    // -- The following fields are protected by stateLock</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    // Channel state, increases monotonically</span><a href="#l65"></a>
<span id="l66">    private static final int ST_UNINITIALIZED = -1;</span><a href="#l66"></a>
<span id="l67">    private static final int ST_INUSE = 0;</span><a href="#l67"></a>
<span id="l68">    private static final int ST_KILLED = 1;</span><a href="#l68"></a>
<span id="l69">    private int state = ST_UNINITIALIZED;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    // Binding</span><a href="#l71"></a>
<span id="l72">    private InetSocketAddress localAddress; // null =&gt; unbound</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    // set true when exclusive binding is on and SO_REUSEADDR is emulated</span><a href="#l74"></a>
<span id="l75">    private boolean isReuseAddress;</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    // Our socket adaptor, if any</span><a href="#l77"></a>
<span id="l78">    ServerSocket socket;</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    // -- End of fields protected by stateLock</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    ServerSocketChannelImpl(SelectorProvider sp) throws IOException {</span><a href="#l83"></a>
<span id="l84">        super(sp);</span><a href="#l84"></a>
<span id="l85">        this.fd =  Net.serverSocket(true);</span><a href="#l85"></a>
<span id="l86">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l86"></a>
<span id="l87">        this.state = ST_INUSE;</span><a href="#l87"></a>
<span id="l88">    }</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">    ServerSocketChannelImpl(SelectorProvider sp,</span><a href="#l90"></a>
<span id="l91">                            FileDescriptor fd,</span><a href="#l91"></a>
<span id="l92">                            boolean bound)</span><a href="#l92"></a>
<span id="l93">        throws IOException</span><a href="#l93"></a>
<span id="l94">    {</span><a href="#l94"></a>
<span id="l95">        super(sp);</span><a href="#l95"></a>
<span id="l96">        this.fd =  fd;</span><a href="#l96"></a>
<span id="l97">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l97"></a>
<span id="l98">        this.state = ST_INUSE;</span><a href="#l98"></a>
<span id="l99">        if (bound)</span><a href="#l99"></a>
<span id="l100">            localAddress = Net.localAddress(fd);</span><a href="#l100"></a>
<span id="l101">    }</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">    public ServerSocket socket() {</span><a href="#l103"></a>
<span id="l104">        synchronized (stateLock) {</span><a href="#l104"></a>
<span id="l105">            if (socket == null)</span><a href="#l105"></a>
<span id="l106">                socket = ServerSocketAdaptor.create(this);</span><a href="#l106"></a>
<span id="l107">            return socket;</span><a href="#l107"></a>
<span id="l108">        }</span><a href="#l108"></a>
<span id="l109">    }</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">    @Override</span><a href="#l111"></a>
<span id="l112">    public SocketAddress getLocalAddress() throws IOException {</span><a href="#l112"></a>
<span id="l113">        synchronized (stateLock) {</span><a href="#l113"></a>
<span id="l114">            if (!isOpen())</span><a href="#l114"></a>
<span id="l115">                throw new ClosedChannelException();</span><a href="#l115"></a>
<span id="l116">            return localAddress == null? localAddress</span><a href="#l116"></a>
<span id="l117">                    : Net.getRevealedLocalAddress(</span><a href="#l117"></a>
<span id="l118">                          Net.asInetSocketAddress(localAddress));</span><a href="#l118"></a>
<span id="l119">        }</span><a href="#l119"></a>
<span id="l120">    }</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">    @Override</span><a href="#l122"></a>
<span id="l123">    public &lt;T&gt; ServerSocketChannel setOption(SocketOption&lt;T&gt; name, T value)</span><a href="#l123"></a>
<span id="l124">        throws IOException</span><a href="#l124"></a>
<span id="l125">    {</span><a href="#l125"></a>
<span id="l126">        if (name == null)</span><a href="#l126"></a>
<span id="l127">            throw new NullPointerException();</span><a href="#l127"></a>
<span id="l128">        if (!supportedOptions().contains(name))</span><a href="#l128"></a>
<span id="l129">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l129"></a>
<span id="l130">        synchronized (stateLock) {</span><a href="#l130"></a>
<span id="l131">            if (!isOpen())</span><a href="#l131"></a>
<span id="l132">                throw new ClosedChannelException();</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">            if (name == StandardSocketOptions.IP_TOS) {</span><a href="#l134"></a>
<span id="l135">                ProtocolFamily family = Net.isIPv6Available() ?</span><a href="#l135"></a>
<span id="l136">                    StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;</span><a href="#l136"></a>
<span id="l137">                Net.setSocketOption(fd, family, name, value);</span><a href="#l137"></a>
<span id="l138">                return this;</span><a href="#l138"></a>
<span id="l139">            }</span><a href="#l139"></a>
<span id="l140"></span><a href="#l140"></a>
<span id="l141">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp;</span><a href="#l141"></a>
<span id="l142">                    Net.useExclusiveBind())</span><a href="#l142"></a>
<span id="l143">            {</span><a href="#l143"></a>
<span id="l144">                // SO_REUSEADDR emulated when using exclusive bind</span><a href="#l144"></a>
<span id="l145">                isReuseAddress = (Boolean)value;</span><a href="#l145"></a>
<span id="l146">            } else {</span><a href="#l146"></a>
<span id="l147">                // no options that require special handling</span><a href="#l147"></a>
<span id="l148">                Net.setSocketOption(fd, Net.UNSPEC, name, value);</span><a href="#l148"></a>
<span id="l149">            }</span><a href="#l149"></a>
<span id="l150">            return this;</span><a href="#l150"></a>
<span id="l151">        }</span><a href="#l151"></a>
<span id="l152">    }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">    @Override</span><a href="#l154"></a>
<span id="l155">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l155"></a>
<span id="l156">    public &lt;T&gt; T getOption(SocketOption&lt;T&gt; name)</span><a href="#l156"></a>
<span id="l157">        throws IOException</span><a href="#l157"></a>
<span id="l158">    {</span><a href="#l158"></a>
<span id="l159">        if (name == null)</span><a href="#l159"></a>
<span id="l160">            throw new NullPointerException();</span><a href="#l160"></a>
<span id="l161">        if (!supportedOptions().contains(name))</span><a href="#l161"></a>
<span id="l162">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">        synchronized (stateLock) {</span><a href="#l164"></a>
<span id="l165">            if (!isOpen())</span><a href="#l165"></a>
<span id="l166">                throw new ClosedChannelException();</span><a href="#l166"></a>
<span id="l167">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp;</span><a href="#l167"></a>
<span id="l168">                    Net.useExclusiveBind())</span><a href="#l168"></a>
<span id="l169">            {</span><a href="#l169"></a>
<span id="l170">                // SO_REUSEADDR emulated when using exclusive bind</span><a href="#l170"></a>
<span id="l171">                return (T)Boolean.valueOf(isReuseAddress);</span><a href="#l171"></a>
<span id="l172">            }</span><a href="#l172"></a>
<span id="l173">            // no options that require special handling</span><a href="#l173"></a>
<span id="l174">            return (T) Net.getSocketOption(fd, Net.UNSPEC, name);</span><a href="#l174"></a>
<span id="l175">        }</span><a href="#l175"></a>
<span id="l176">    }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    private static class DefaultOptionsHolder {</span><a href="#l178"></a>
<span id="l179">        static final Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions = defaultOptions();</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">        private static Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions() {</span><a href="#l181"></a>
<span id="l182">            HashSet&lt;SocketOption&lt;?&gt;&gt; set = new HashSet&lt;SocketOption&lt;?&gt;&gt;(2);</span><a href="#l182"></a>
<span id="l183">            set.add(StandardSocketOptions.SO_RCVBUF);</span><a href="#l183"></a>
<span id="l184">            set.add(StandardSocketOptions.SO_REUSEADDR);</span><a href="#l184"></a>
<span id="l185">            set.add(StandardSocketOptions.IP_TOS);</span><a href="#l185"></a>
<span id="l186">            return Collections.unmodifiableSet(set);</span><a href="#l186"></a>
<span id="l187">        }</span><a href="#l187"></a>
<span id="l188">    }</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">    @Override</span><a href="#l190"></a>
<span id="l191">    public final Set&lt;SocketOption&lt;?&gt;&gt; supportedOptions() {</span><a href="#l191"></a>
<span id="l192">        return DefaultOptionsHolder.defaultOptions;</span><a href="#l192"></a>
<span id="l193">    }</span><a href="#l193"></a>
<span id="l194"></span><a href="#l194"></a>
<span id="l195">    public boolean isBound() {</span><a href="#l195"></a>
<span id="l196">        synchronized (stateLock) {</span><a href="#l196"></a>
<span id="l197">            return localAddress != null;</span><a href="#l197"></a>
<span id="l198">        }</span><a href="#l198"></a>
<span id="l199">    }</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">    public InetSocketAddress localAddress() {</span><a href="#l201"></a>
<span id="l202">        synchronized (stateLock) {</span><a href="#l202"></a>
<span id="l203">            return localAddress;</span><a href="#l203"></a>
<span id="l204">        }</span><a href="#l204"></a>
<span id="l205">    }</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">    @Override</span><a href="#l207"></a>
<span id="l208">    public ServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {</span><a href="#l208"></a>
<span id="l209">        synchronized (lock) {</span><a href="#l209"></a>
<span id="l210">            if (!isOpen())</span><a href="#l210"></a>
<span id="l211">                throw new ClosedChannelException();</span><a href="#l211"></a>
<span id="l212">            if (isBound())</span><a href="#l212"></a>
<span id="l213">                throw new AlreadyBoundException();</span><a href="#l213"></a>
<span id="l214">            InetSocketAddress isa = (local == null) ? new InetSocketAddress(0) :</span><a href="#l214"></a>
<span id="l215">                Net.checkAddress(local);</span><a href="#l215"></a>
<span id="l216">            SecurityManager sm = System.getSecurityManager();</span><a href="#l216"></a>
<span id="l217">            if (sm != null)</span><a href="#l217"></a>
<span id="l218">                sm.checkListen(isa.getPort());</span><a href="#l218"></a>
<span id="l219">            NetHooks.beforeTcpBind(fd, isa.getAddress(), isa.getPort());</span><a href="#l219"></a>
<span id="l220">            Net.bind(fd, isa.getAddress(), isa.getPort());</span><a href="#l220"></a>
<span id="l221">            Net.listen(fd, backlog &lt; 1 ? 50 : backlog);</span><a href="#l221"></a>
<span id="l222">            synchronized (stateLock) {</span><a href="#l222"></a>
<span id="l223">                localAddress = Net.localAddress(fd);</span><a href="#l223"></a>
<span id="l224">            }</span><a href="#l224"></a>
<span id="l225">        }</span><a href="#l225"></a>
<span id="l226">        return this;</span><a href="#l226"></a>
<span id="l227">    }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    public SocketChannel accept() throws IOException {</span><a href="#l229"></a>
<span id="l230">        synchronized (lock) {</span><a href="#l230"></a>
<span id="l231">            if (!isOpen())</span><a href="#l231"></a>
<span id="l232">                throw new ClosedChannelException();</span><a href="#l232"></a>
<span id="l233">            if (!isBound())</span><a href="#l233"></a>
<span id="l234">                throw new NotYetBoundException();</span><a href="#l234"></a>
<span id="l235">            SocketChannel sc = null;</span><a href="#l235"></a>
<span id="l236"></span><a href="#l236"></a>
<span id="l237">            int n = 0;</span><a href="#l237"></a>
<span id="l238">            FileDescriptor newfd = new FileDescriptor();</span><a href="#l238"></a>
<span id="l239">            InetSocketAddress[] isaa = new InetSocketAddress[1];</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">            try {</span><a href="#l241"></a>
<span id="l242">                begin();</span><a href="#l242"></a>
<span id="l243">                if (!isOpen())</span><a href="#l243"></a>
<span id="l244">                    return null;</span><a href="#l244"></a>
<span id="l245">                thread = NativeThread.current();</span><a href="#l245"></a>
<span id="l246">                for (;;) {</span><a href="#l246"></a>
<span id="l247">                    n = accept0(this.fd, newfd, isaa);</span><a href="#l247"></a>
<span id="l248">                    if ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen())</span><a href="#l248"></a>
<span id="l249">                        continue;</span><a href="#l249"></a>
<span id="l250">                    break;</span><a href="#l250"></a>
<span id="l251">                }</span><a href="#l251"></a>
<span id="l252">            } finally {</span><a href="#l252"></a>
<span id="l253">                thread = 0;</span><a href="#l253"></a>
<span id="l254">                end(n &gt; 0);</span><a href="#l254"></a>
<span id="l255">                assert IOStatus.check(n);</span><a href="#l255"></a>
<span id="l256">            }</span><a href="#l256"></a>
<span id="l257"></span><a href="#l257"></a>
<span id="l258">            if (n &lt; 1)</span><a href="#l258"></a>
<span id="l259">                return null;</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">            IOUtil.configureBlocking(newfd, true);</span><a href="#l261"></a>
<span id="l262">            InetSocketAddress isa = isaa[0];</span><a href="#l262"></a>
<span id="l263">            sc = new SocketChannelImpl(provider(), newfd, isa);</span><a href="#l263"></a>
<span id="l264">            SecurityManager sm = System.getSecurityManager();</span><a href="#l264"></a>
<span id="l265">            if (sm != null) {</span><a href="#l265"></a>
<span id="l266">                try {</span><a href="#l266"></a>
<span id="l267">                    sm.checkAccept(isa.getAddress().getHostAddress(),</span><a href="#l267"></a>
<span id="l268">                                   isa.getPort());</span><a href="#l268"></a>
<span id="l269">                } catch (SecurityException x) {</span><a href="#l269"></a>
<span id="l270">                    sc.close();</span><a href="#l270"></a>
<span id="l271">                    throw x;</span><a href="#l271"></a>
<span id="l272">                }</span><a href="#l272"></a>
<span id="l273">            }</span><a href="#l273"></a>
<span id="l274">            return sc;</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">        }</span><a href="#l276"></a>
<span id="l277">    }</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">    protected void implConfigureBlocking(boolean block) throws IOException {</span><a href="#l279"></a>
<span id="l280">        IOUtil.configureBlocking(fd, block);</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    protected void implCloseSelectableChannel() throws IOException {</span><a href="#l283"></a>
<span id="l284">        synchronized (stateLock) {</span><a href="#l284"></a>
<span id="l285">            if (state != ST_KILLED)</span><a href="#l285"></a>
<span id="l286">                nd.preClose(fd);</span><a href="#l286"></a>
<span id="l287">            long th = thread;</span><a href="#l287"></a>
<span id="l288">            if (th != 0)</span><a href="#l288"></a>
<span id="l289">                NativeThread.signal(th);</span><a href="#l289"></a>
<span id="l290">            if (!isRegistered())</span><a href="#l290"></a>
<span id="l291">                kill();</span><a href="#l291"></a>
<span id="l292">        }</span><a href="#l292"></a>
<span id="l293">    }</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">    public void kill() throws IOException {</span><a href="#l295"></a>
<span id="l296">        synchronized (stateLock) {</span><a href="#l296"></a>
<span id="l297">            if (state == ST_KILLED)</span><a href="#l297"></a>
<span id="l298">                return;</span><a href="#l298"></a>
<span id="l299">            if (state == ST_UNINITIALIZED) {</span><a href="#l299"></a>
<span id="l300">                state = ST_KILLED;</span><a href="#l300"></a>
<span id="l301">                return;</span><a href="#l301"></a>
<span id="l302">            }</span><a href="#l302"></a>
<span id="l303">            assert !isOpen() &amp;&amp; !isRegistered();</span><a href="#l303"></a>
<span id="l304">            nd.close(fd);</span><a href="#l304"></a>
<span id="l305">            state = ST_KILLED;</span><a href="#l305"></a>
<span id="l306">        }</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    /**</span><a href="#l309"></a>
<span id="l310">     * Translates native poll revent set into a ready operation set</span><a href="#l310"></a>
<span id="l311">     */</span><a href="#l311"></a>
<span id="l312">    public boolean translateReadyOps(int ops, int initialOps,</span><a href="#l312"></a>
<span id="l313">                                     SelectionKeyImpl sk) {</span><a href="#l313"></a>
<span id="l314">        int intOps = sk.nioInterestOps(); // Do this just once, it synchronizes</span><a href="#l314"></a>
<span id="l315">        int oldOps = sk.nioReadyOps();</span><a href="#l315"></a>
<span id="l316">        int newOps = initialOps;</span><a href="#l316"></a>
<span id="l317"></span><a href="#l317"></a>
<span id="l318">        if ((ops &amp; Net.POLLNVAL) != 0) {</span><a href="#l318"></a>
<span id="l319">            // This should only happen if this channel is pre-closed while a</span><a href="#l319"></a>
<span id="l320">            // selection operation is in progress</span><a href="#l320"></a>
<span id="l321">            // ## Throw an error if this channel has not been pre-closed</span><a href="#l321"></a>
<span id="l322">            return false;</span><a href="#l322"></a>
<span id="l323">        }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">        if ((ops &amp; (Net.POLLERR | Net.POLLHUP)) != 0) {</span><a href="#l325"></a>
<span id="l326">            newOps = intOps;</span><a href="#l326"></a>
<span id="l327">            sk.nioReadyOps(newOps);</span><a href="#l327"></a>
<span id="l328">            return (newOps &amp; ~oldOps) != 0;</span><a href="#l328"></a>
<span id="l329">        }</span><a href="#l329"></a>
<span id="l330"></span><a href="#l330"></a>
<span id="l331">        if (((ops &amp; Net.POLLIN) != 0) &amp;&amp;</span><a href="#l331"></a>
<span id="l332">            ((intOps &amp; SelectionKey.OP_ACCEPT) != 0))</span><a href="#l332"></a>
<span id="l333">                newOps |= SelectionKey.OP_ACCEPT;</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">        sk.nioReadyOps(newOps);</span><a href="#l335"></a>
<span id="l336">        return (newOps &amp; ~oldOps) != 0;</span><a href="#l336"></a>
<span id="l337">    }</span><a href="#l337"></a>
<span id="l338"></span><a href="#l338"></a>
<span id="l339">    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l339"></a>
<span id="l340">        return translateReadyOps(ops, sk.nioReadyOps(), sk);</span><a href="#l340"></a>
<span id="l341">    }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l343"></a>
<span id="l344">        return translateReadyOps(ops, 0, sk);</span><a href="#l344"></a>
<span id="l345">    }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">    // package-private</span><a href="#l347"></a>
<span id="l348">    int poll(int events, long timeout) throws IOException {</span><a href="#l348"></a>
<span id="l349">        assert Thread.holdsLock(blockingLock()) &amp;&amp; !isBlocking();</span><a href="#l349"></a>
<span id="l350"></span><a href="#l350"></a>
<span id="l351">        synchronized (lock) {</span><a href="#l351"></a>
<span id="l352">            int n = 0;</span><a href="#l352"></a>
<span id="l353">            try {</span><a href="#l353"></a>
<span id="l354">                begin();</span><a href="#l354"></a>
<span id="l355">                synchronized (stateLock) {</span><a href="#l355"></a>
<span id="l356">                    if (!isOpen())</span><a href="#l356"></a>
<span id="l357">                        return 0;</span><a href="#l357"></a>
<span id="l358">                    thread = NativeThread.current();</span><a href="#l358"></a>
<span id="l359">                }</span><a href="#l359"></a>
<span id="l360">                n = Net.poll(fd, events, timeout);</span><a href="#l360"></a>
<span id="l361">            } finally {</span><a href="#l361"></a>
<span id="l362">                thread = 0;</span><a href="#l362"></a>
<span id="l363">                end(n &gt; 0);</span><a href="#l363"></a>
<span id="l364">            }</span><a href="#l364"></a>
<span id="l365">            return n;</span><a href="#l365"></a>
<span id="l366">        }</span><a href="#l366"></a>
<span id="l367">    }</span><a href="#l367"></a>
<span id="l368"></span><a href="#l368"></a>
<span id="l369">    /**</span><a href="#l369"></a>
<span id="l370">     * Translates an interest operation set into a native poll event set</span><a href="#l370"></a>
<span id="l371">     */</span><a href="#l371"></a>
<span id="l372">    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {</span><a href="#l372"></a>
<span id="l373">        int newOps = 0;</span><a href="#l373"></a>
<span id="l374"></span><a href="#l374"></a>
<span id="l375">        // Translate ops</span><a href="#l375"></a>
<span id="l376">        if ((ops &amp; SelectionKey.OP_ACCEPT) != 0)</span><a href="#l376"></a>
<span id="l377">            newOps |= Net.POLLIN;</span><a href="#l377"></a>
<span id="l378">        // Place ops into pollfd array</span><a href="#l378"></a>
<span id="l379">        sk.selector.putEventOps(sk, newOps);</span><a href="#l379"></a>
<span id="l380">    }</span><a href="#l380"></a>
<span id="l381"></span><a href="#l381"></a>
<span id="l382">    public FileDescriptor getFD() {</span><a href="#l382"></a>
<span id="l383">        return fd;</span><a href="#l383"></a>
<span id="l384">    }</span><a href="#l384"></a>
<span id="l385"></span><a href="#l385"></a>
<span id="l386">    public int getFDVal() {</span><a href="#l386"></a>
<span id="l387">        return fdVal;</span><a href="#l387"></a>
<span id="l388">    }</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">    public String toString() {</span><a href="#l390"></a>
<span id="l391">        StringBuffer sb = new StringBuffer();</span><a href="#l391"></a>
<span id="l392">        sb.append(this.getClass().getName());</span><a href="#l392"></a>
<span id="l393">        sb.append('[');</span><a href="#l393"></a>
<span id="l394">        if (!isOpen()) {</span><a href="#l394"></a>
<span id="l395">            sb.append(&quot;closed&quot;);</span><a href="#l395"></a>
<span id="l396">        } else {</span><a href="#l396"></a>
<span id="l397">            synchronized (stateLock) {</span><a href="#l397"></a>
<span id="l398">                InetSocketAddress addr = localAddress();</span><a href="#l398"></a>
<span id="l399">                if (addr == null) {</span><a href="#l399"></a>
<span id="l400">                    sb.append(&quot;unbound&quot;);</span><a href="#l400"></a>
<span id="l401">                } else {</span><a href="#l401"></a>
<span id="l402">                    sb.append(Net.getRevealedLocalAddressAsString(addr));</span><a href="#l402"></a>
<span id="l403">                }</span><a href="#l403"></a>
<span id="l404">            }</span><a href="#l404"></a>
<span id="l405">        }</span><a href="#l405"></a>
<span id="l406">        sb.append(']');</span><a href="#l406"></a>
<span id="l407">        return sb.toString();</span><a href="#l407"></a>
<span id="l408">    }</span><a href="#l408"></a>
<span id="l409"></span><a href="#l409"></a>
<span id="l410">    // -- Native methods --</span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">    // Accepts a new connection, setting the given file descriptor to refer to</span><a href="#l412"></a>
<span id="l413">    // the new socket and setting isaa[0] to the socket's remote address.</span><a href="#l413"></a>
<span id="l414">    // Returns 1 on success, or IOStatus.UNAVAILABLE (if non-blocking and no</span><a href="#l414"></a>
<span id="l415">    // connections are pending) or IOStatus.INTERRUPTED.</span><a href="#l415"></a>
<span id="l416">    //</span><a href="#l416"></a>
<span id="l417">    private native int accept0(FileDescriptor ssfd, FileDescriptor newfd,</span><a href="#l417"></a>
<span id="l418">                               InetSocketAddress[] isaa)</span><a href="#l418"></a>
<span id="l419">        throws IOException;</span><a href="#l419"></a>
<span id="l420"></span><a href="#l420"></a>
<span id="l421">    private static native void initIDs();</span><a href="#l421"></a>
<span id="l422"></span><a href="#l422"></a>
<span id="l423">    static {</span><a href="#l423"></a>
<span id="l424">        IOUtil.load();</span><a href="#l424"></a>
<span id="l425">        initIDs();</span><a href="#l425"></a>
<span id="l426">        nd = new SocketDispatcher();</span><a href="#l426"></a>
<span id="l427">    }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">}</span><a href="#l429"></a></pre>
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

