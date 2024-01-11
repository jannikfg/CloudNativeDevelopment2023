<script>
  import BookingItem from "./BookingItem.svelte";
  export let bookings;
</script>

<div class="bookings_list">
  {#each bookings as booking}
    <BookingItem booking={booking} />
  {/each}
</div>

<style>
  .bookings_list {
    display: flex;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
  }
</style>