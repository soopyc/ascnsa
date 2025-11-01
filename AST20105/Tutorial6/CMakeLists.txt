add_library(T6CommonLib)
add_executable(Stack)
add_executable(Queue)

target_sources(T6CommonLib
	PRIVATE
		include/StackArr.cpp
	PUBLIC
		FILE_SET HEADERS
		BASE_DIRS
			include
		FILES
			include/StackArr.h
)

target_sources(Stack
	PRIVATE
		00-Stack/StackMain.cpp
)

target_sources(Queue
	PRIVATE
		01-Queue/QueueMain.cpp
		01-Queue/QueueArr.cpp
		01-Queue/QueueArr.h
)

target_link_libraries(Stack T6CommonLib)
target_link_libraries(Queue T6CommonLib)
