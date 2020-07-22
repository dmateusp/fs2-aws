package fs2.aws.sqs

import com.amazon.sqs.javamessaging.SQSConnection
import javax.jms.MessageListener

trait SQSConsumer {
  def callback: MessageListener
  def startConsumer(): Unit
  def shutdown(): Unit
  def connection: SQSConnection
}
