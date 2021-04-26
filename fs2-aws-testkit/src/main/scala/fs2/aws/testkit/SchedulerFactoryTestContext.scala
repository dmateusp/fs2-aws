package fs2.aws.testkit

import cats.Applicative

import java.util.concurrent.CountDownLatch
import org.mockito.Mockito.mock
import cats.syntax.applicative._
import software.amazon.kinesis.coordinator.Scheduler
import software.amazon.kinesis.processor.{ ShardRecordProcessor, ShardRecordProcessorFactory }

import scala.collection.mutable.ListBuffer

class SchedulerFactoryTestContext[F[_]: Applicative](shards: Int)
    extends (ShardRecordProcessorFactory => F[Scheduler]) {

  val processorsAreReady = new CountDownLatch(1)

  private[this] val mockScheduler: Scheduler = mock(classOf[Scheduler])

  private[this] val shardProcessors = ListBuffer.empty[ShardRecordProcessor]

  override def apply(pf: ShardRecordProcessorFactory): F[Scheduler] = {
    (0 until shards) foreach (_ => shardProcessors += pf.shardRecordProcessor())
    processorsAreReady.countDown()
    mockScheduler.pure[F]
  }

  def getShardProcessors: List[ShardRecordProcessor] = {
    processorsAreReady.await()
    shardProcessors.toList
  }
}
