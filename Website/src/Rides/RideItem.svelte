<script>
    export let from;
    export let to;
    export let date;
    export let time;
    export let driver;
    export let description;
</script>

<div>
    <header>
        <h1>{from} - {to} / {date} / {time}</h1>
    </header>
    <div class="content">
        <p>{driver}</p>
        <p>{description}</p>
    </div>
</div>
