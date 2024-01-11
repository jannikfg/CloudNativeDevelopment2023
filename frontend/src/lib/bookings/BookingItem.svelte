<script>
  export let booking;
</script>

<div class="bookings_list_item">
  <div class="bookings_list_item_title">
    <img class="bookings_list_item_icon" src="src/lib/bookings/car_icon.png" alt="car icon">
    <p>{booking.ride.id}</p>
  </div>
  <div class="bookings_list_item_content">
    <div class="bookings_list_item_content_left">
      <p>Date: {booking.ride.date}</p>
      <p>From: {booking.ride.origin}</p>
      <p>To: {booking.ride.destination}</p>
    </div>
    <div class="bookings_list_item_content_right">
      <p>Time: {booking.ride.time}</p>
      <p>Driver: {booking.ride.driver}</p>
      <p>Seats: {booking.ride.capacity}</p>
    </div>
  </div>
</div>

<style>
  .bookings_list_item_icon {
    width: 50px;
    height: 50px;
    filter: invert(100%);
    margin-right: 1rem;
  }
  .bookings_list_item {
    border: 3px solid #cf0056;
    background-color: #cf0056;
    color: white;
    padding: 20px;
    border-radius: 5px;
  }

  .bookings_list_item_title {
    font-size: 18px;
    margin-bottom: 10px;
    display: flex;
    justify-content: space-between;
  }

  .bookings_list_item_content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid white;
  }

  .bookings_list_item_content_left {
    flex: 1;
    text-align: center;
  }

  .bookings_list_item_content_right {
    flex: 1;
    text-align: center;
  }
</style>