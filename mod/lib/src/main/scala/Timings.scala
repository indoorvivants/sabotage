package sabotage.lib

object Timings:
  inline def time[R](name: String)(block: => R)(using Log): R =
    val start = System.nanoTime()
    val result = block
    val finish = System.nanoTime()

    val inMillis = (finish - start).toFloat / 1000000

    val duration =
      if inMillis > 1000 then f"${inMillis / 1000}%.2f s"
      else f"${inMillis}%.2f ms"

    Log.info(s"$name: $duration")

    result
  end time
end Timings
