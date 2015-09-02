    def validateProxy(self, proxy):
        """ Validates the proxy URL and returns either True or False.
        """
        if proxy:
            self.log.info("Validating the proxy address: %s" % proxy)
            # Validation Version 1 - Not using urlparse and only regex.
            # patternProxy = re.compile("(http|https)://(\d{1,3}.\d{1,3}.\d{1,3}.\d{1,3}|localhost):\d{2,6}")
            # matchesProxy = patternProxy.search(proxy)
            # try:
            #     self.log.debug("The proxy address: %s is valid." % matchesProxy.group())
            #     self.validProxy = True
            # except AttributeError as e:
            #     self.log.info("The proxy address being used is invalid.")
            #     self.validProxy = False
            # End of Validation Version 1
        else:
            self.log.info("No proxy is being used.")
            self.validProxy = False
